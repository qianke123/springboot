package com.how2java.util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 文件上传的类
 */
public class FileUtils {

    public static final String fileUploadRootPath = "D:\\intellij_workspace\\finaldesign\\src\\main\\static\\file";
    /**
     * 文件上传方法
     * @param file 文件byte[]
     * @param filePath 文件上传路径
     * @param fileName 文件保存路径
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
