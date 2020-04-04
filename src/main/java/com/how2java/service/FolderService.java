package com.how2java.service;

import com.how2java.dao.FolderDao;
import com.how2java.pojo.Folder;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class FolderService {

    private Logger logger = LoggerFactory.getLogger(FolderService.class);

    @Autowired
    private FolderDao folderDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * hdfs 文件系统
     */
    @Autowired
    private FileSystem fileSystem;

    /**
     * shareCenter: search files
     * @param fileName
     * @return
     */
    public List<Folder> getTableData(String fileName) {
        return this.folderDao.getTableData(fileName);
    }

    /**
     * 更新文件权限
     * @param filePath 文件全路径
     * @param authority 改变之后的权限
     * @return 结果信息
     */
    public String changeFolderAuthority(String filePath, int authority) {
        if (authority == 0) {
            authority = 1;
        } else {
            authority = 0;
        }
        if (this.folderDao.changeFolderAuthority(filePath, authority) > 0) {

            String s = authority == 1 ? "文件修改为公开" : "文件修改为私有";
            return s;
        } else {
            return "权限修改失败";
        }
    }

    /**
     * 创建文件（夹）
     * @param targetFilePath 文件的目标路径
     * @param folderName 文件名
     * @param userName 用户名
     * @param fileType 文件类型
     * @return 结果信息
     */
    public String creatFolder(String targetFilePath, String folderName, String userName, int fileType) {
        String folderPath = "/" + targetFilePath + "/" + folderName;
        try {
            fileSystem.mkdirs(new Path(folderPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Folder folder = new Folder(userName, folderName, fileType, new Date(), 0, folderPath);
        // 保存文件夹到数据库
        this.folderDao.uploadFile(folder);
        return "文件夹创建成功";
    }

    /**
     * 上传文件
     * @param
     * @return 是否成功
     */
    public String uploadFile(String targetFilePath, String userName, MultipartFile[] files) throws IOException {
        for (int i = 0; i < files.length; i++) {
            // 保存文件到本地
            String fileName = files[i].getOriginalFilename();
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            out.flush();
            out.close();
            // 从本地上传到hdfs
            String localPath = fileName;
            String remotePath = "/" + targetFilePath + "/" + fileName;
            logger.info("上传到的路径：" + remotePath);
            fileSystem.moveFromLocalFile(new Path(localPath), new Path(remotePath));
            // 保存信息到数据库
            Folder folder = new Folder();
            folder.setAuthority(0);
            folder.setFolderBool(0);
            folder.setDate(new Date());
            folder.setFolderName(fileName);
            folder.setUserName(userName);
            folder.setFolderUrl(remotePath);
            this.folderDao.uploadFile(folder);
        }
        return "文件上传成功";
    }

    /**
     * 获取当前文件夹下的所有的文件or文件夹
     * @param filePath
     * @return
     */
    public List<Folder> getCurrentPathFiles (String filePath) {
        List<Folder> folderList = this.folderDao.getFileList(filePath);
        List<Folder> resultList = new ArrayList<>();
        // 去重
        Set<String> set = new HashSet<>();
        for (Folder folder : folderList) {
            if (folder.getFolderUrl().split(filePath).length > 1) {
                String fileNameTemp = folder.getFolderUrl().split(filePath + "/")[1];
                if (fileNameTemp.contains("/")) {
                    String fileName = fileNameTemp.split("/")[0];
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
     * @param folderUrl 要删除文件的路径
     * @return 返回成功信息
     */
    public String deleteFile (String folderUrl) {
        try {
            logger.info("folderUrl: " + folderUrl);
            if (!fileSystem.exists(new Path(folderUrl))) {
                fileSystem.delete(new Path(folderUrl));
                this.folderDao.deleteFile(folderUrl);
                return "文件不存在";
            } else {
                fileSystem.delete(new Path(folderUrl));
                this.folderDao.deleteFile(folderUrl);
                return "文件删除成功";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "文件删除失败";
        }
    }
}
