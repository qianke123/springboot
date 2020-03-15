package com.how2java.service;

import com.how2java.dao.FolderDao;
import com.how2java.pojo.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FolderService {

    @Autowired
    private FolderDao folderDao;

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
        List<Folder> fileList = this.folderDao.getFileList(filePath);
        // 筛选结果文件列表
        List<Folder> resultList = new ArrayList<>();
        // 用来给文件夹名去重
        Set<String> fDoubleSet = new HashSet<>();
        for(Folder folder : fileList) {
            System.out.println(folder.getFolderUrl());
            String temName = folder.getFolderUrl().split(filePath + "\\\\")[1];
            if (temName.contains("\\")) {
                folder.setFolderName(temName.split("\\\\")[0]);
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
