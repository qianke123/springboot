package com.how2java.controller;

import com.how2java.pojo.Folder;
import com.how2java.service.FolderService;
import com.how2java.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/folder")
public class FolderController {

    @Autowired
    private FolderService folderService;
    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    /**
     * 获取当前文件夹下所有文件名
     * @param filePath
     * @return
     */
    @RequestMapping("/getCurrentPathFiles")
    public List<Folder> getCurrentPathFiles(@RequestParam("filePath") String filePath) {
        return this.folderService.getCurrentPathFiles(filePath);
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam("userName") String userName,
                             @RequestParam("file") MultipartFile[] files,
                             HttpServletRequest request) throws Exception {
        for (int i = 0; i < files.length; i++) {
            // 保存文件
            String filePath = FileUtils.fileUploadRootPath + "\\" + userName;
            String fileName = files[i].getOriginalFilename();
            FileUtils.uploadFile(files[i].getBytes(), filePath, fileName);
            logger.info(fileName + ": 上传成功");
            // 保存信息到数据库
            Folder folder = new Folder();
            folder.setAuthority(0);
            String fileUrl = filePath + "\\" + fileName;
            File file = new File(fileUrl);
            if (file.isDirectory()) {
                folder.setFolderBool(1);
            } else {
                folder.setFolderBool(0);
            }
            folder.setDate(new Date());
            folder.setFolderName(fileName);
            folder.setUserName(userName);
            folder.setFolderUrl(fileUrl);
            folderService.uploadFile(folder);
        }
        return "文件上传成功";
    }
}