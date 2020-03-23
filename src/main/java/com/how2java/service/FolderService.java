package com.how2java.service;

import com.how2java.dao.FolderDao;
import com.how2java.pojo.Folder;
import com.how2java.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class FolderService {

    private Logger logger = LoggerFactory.getLogger(FolderService.class);

    @Autowired
    private FolderDao folderDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 创建文件（夹）
     * @param targetFilePath 文件的目标路径
     * @param folderName 文件名
     * @param userName 用户名
     * @param fileType 文件类型
     * @return 结果信息
     */
    public String creatFolder(String targetFilePath, String folderName, String userName, int fileType) {
        String folderPath = FileUtils.fileUploadRootPath + "\\" + targetFilePath + "\\" + folderName;
        File file = new File(folderPath);
        file.mkdir();
        Folder folder = new Folder(userName, folderName, fileType, new Date(), 0, folderPath);
        // 保存文件夹到数据库
        this.folderDao.uploadFile(folder);
        return "文件夹创建成功";
    }


    /**
     * 上传文件
     * @param folder
     * @return 是否成功
     */
    public int uploadFile(Folder folder) {
        return this.folderDao.uploadFile(folder);
    }

    /**
     * 获取当前文件夹下的所有的文件or文件夹
     * @param filePath
     * @return
     */
    public List<Folder> getCurrentPathFiles (String filePath) {
        List<Folder> folderList = this.folderDao.getFileList(filePath.replace("\\", "_"));
        filePath = filePath.replace("\\", "@");
        List<Folder> resultList = new ArrayList<>();
        // 去重
        Set<String> set = new HashSet<>();
        for (Folder folder : folderList) {
            folder.setFolderUrl(folder.getFolderUrl().replace("\\", "@"));
            if (folder.getFolderUrl().split(filePath).length > 1) {
                String fileNameTemp = folder.getFolderUrl().split(filePath + "@")[1];
                if (fileNameTemp.contains("@")) {
                    String fileName = fileNameTemp.split("@")[0];
                    if (!set.contains(fileName)) {
                        folder.setFolderBool(1);
                        folder.setFolderName(fileName);
                        resultList.add(folder);
                        set.add(fileName);
                    }
                } else {
                    if (!set.contains(folder.getFolderName())) {
                        set.add(folder.getFolderName());
                        resultList.add(folder);
                    }
                }
            }
        }
        Collections.sort(resultList);
        return resultList;
    }

    /**
     * 删除文件
     * @param folderUrl 要删除文件的路径 路径中时@@@
     * @return 返回成功信息
     */
    public String deleteFile (String folderUrl) {
        folderUrl = folderUrl.replace("@", "\\");
        logger.info("folderUrl: " + folderUrl);
        File file = new File(folderUrl);
        if (!file.exists()) {
            this.folderDao.deleteFile(folderUrl.replace("\\", "_"));
            return "文件不存在";
        } else {
            file.delete();
            this.folderDao.deleteFile(folderUrl.replace("\\", "_"));
            return "文件删除成功";
        }
    }
}
