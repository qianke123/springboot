package com.how2java.dao;

import com.how2java.pojo.Folder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FolderDao {

    /**
     * 获取共享文件列表
     * @param fileName
     * @return
     */
    @Select("SELECT * FROM test.folder where folderUrl like CONCAT('%',#{fileName},'%') and authority = 1 And folderBool = 0;")
    List<Folder> getTableData(@Param("fileName") String fileName);

    /**
     * 上传文件
     * @param folder
     * @return 文件上传是否成功
     */
    @Insert("insert into test.folder(userName, folderName, folderBool, date, authority, folderUrl)" +
            " values(#{folder.userName}, #{folder.folderName}, #{folder.folderBool}, #{folder.date}, #{folder.authority}, #{folder.folderUrl})")
    int uploadFile(@Param("folder") Folder folder);

    /**
     * 获取所有该文件夹下所有文件的文件名
     * @param filePath
     * @return
     */
    @Select("SELECT * FROM test.folder where folderUrl like CONCAT('%',#{filePath},'%') ")
    List<Folder> getFileList(@Param("filePath") String filePath);

    /**
     * 文件删除
     * @param folderUrl
     * @return 0 失败 >0 成功
     */
    @Delete("delete from test.folder where folderUrl like #{folderUrl}")
    int deleteFile(@Param("folderUrl") String folderUrl);

    /**
     * 更新文件权限
     * @param filePath 文件全路径
     * @param authority 改变之后的权限
     * @return  0 失败 >0 成功
     */
    @Update("update test.folder set folder.authority = #{authority} where folderUrl = #{filePath}")
    int changeFolderAuthority(@Param("filePath") String filePath, @Param("authority") int authority);

}
