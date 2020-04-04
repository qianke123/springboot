package com.how2java.controller;

import com.how2java.pojo.Folder;
import com.how2java.service.FolderService;
import org.apache.hadoop.fs.FileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/folder")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private FileSystem fileSystem;

    private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    /**
     * 获取分享文件列表
     * @param fileName
     * @return
     */
    @RequestMapping("/getTableData")
    public List<Folder> getTableData(@RequestParam("fileName") String fileName) {
        return this.folderService.getTableData(fileName);
    }

    /**
     * 更新文件权限
     * @param filePath 文件全路径
     * @param authority 改变之后的权限
     * @return 结果信息
     */
    @RequestMapping("/changeFolderAuthority")
    public String changeFolderAuthority(@RequestParam("filePath") String filePath,
                                        @RequestParam("authority") int authority) {
        return this.folderService.changeFolderAuthority(filePath, authority);
    }


    /**
     * 创建文件（夹）
     * @param targetFilePath 文件的目标路径
     * @param folderName 文件名
     * @param userName 用户名
     * @param fileType 文件类型
     * @return 结果信息
     */
    @RequestMapping("/createFolder")
    public String createFolder(@RequestParam("targetFilePath") String targetFilePath,
                               @RequestParam("folderName") String folderName,
                               @RequestParam("userName") String userName,
                               @RequestParam("fileType") int fileType) {
        return this.folderService.creatFolder(targetFilePath, folderName, userName, fileType);
    }

    /**
     * 删除文件
     * @param folderUrl 要删除文件路径
     * @return 结果信息
     */
    @RequestMapping("/deleteFile")
    public String deleteFile(@RequestParam("folderUrl") String folderUrl) {
        return this.folderService.deleteFile(folderUrl);
    }

    /**
     * 获取当前文件夹下所有文件名
     * @param filePath
     * @return
     */
    @RequestMapping("/getCurrentPathFiles")
    public List<Folder> getCurrentPathFiles(@RequestParam("filePath") String filePath) {
        return this.folderService.getCurrentPathFiles(filePath);
    }

    /**
     * 上传文件
     * @param targetFilePath 目标路径
     * @param userName 用户名
     * @param files 文件
     * @return 结果信息
     * @throws Exception
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(@RequestParam("targetFilePath") String targetFilePath,
                             @RequestParam("userName") String userName,
                             @RequestParam("file") MultipartFile[] files) throws Exception {
        return this.folderService.uploadFile(targetFilePath, userName, files);
    }
}
