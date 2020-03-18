package com.how2java.service;

import com.how2java.dao.FolderDao;
import com.how2java.pojo.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FolderService {

    @Autowired
    private FolderDao folderDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        // 将反义字符\\都改为@来分割
        String filePathSplit = filePath.replace("\\", "@") + "@";
        // 将反义字符\\都路径改为%来分割
        filePath = filePath.replace("\\","_");
        List<Folder> fileList = this.folderDao.getFileList(filePath);
        // 筛选结果文件列表
        List<Folder> resultList = new ArrayList<>();
        // 用来给文件夹名去重
        Set<String> fDoubleSet = new HashSet<>();
        for(Folder folder : fileList) {
            // 替换取出的数据
            String folderUrl = folder.getFolderUrl();
            folderUrl = folderUrl.replace("\\", "@");
            folder.setFolderUrl(folderUrl);
            String temName = folderUrl.split(filePathSplit)[1];
            // 文件夹名去重
            if (temName.contains("@")) {
                folder.setFolderName(temName.split("@")[0]);
                folder.setFolderBool(1);
            }
            if (!fDoubleSet.contains(folder.getFolderName())) {
                fDoubleSet.add(folder.getFolderName());
                resultList.add(folder);
            }
        }
        return resultList;
    }
}
