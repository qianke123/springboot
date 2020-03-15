package com.how2java.dao;

import com.how2java.pojo.Folder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface FolderDao {

    /**
     * 上传文件
     * @param folder
     * @return 文件上传是否成功
     */
    @Insert("insert into test.folder(userName, folderName, folderIs, date, authority, folderUrl)" +
            " values(#{folder.userName}, #{folder.folderName}, #{folder.folderBool}, #{folder.date}, #{folder.authority}, #{folder.folderUrl})")
    int uploadFile(@Param("folder") Folder folder);

    /**
     * 获取所有该文件夹下所有文件的文件名
     * @param filePath
     * @return
     */
    @Select("SELECT * FROM test.folder where folderUrl like replace(CONCAT('%','zhangsan\\\\程序员','%'),'\\\\','%' )")
    List<Folder> getFileList(@Param("filePath") String filePath);
}
