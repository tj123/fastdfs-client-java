package org.csource.fastdfs.api;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

public interface FdfsClient {

    /**
     * 上传文件
     * 返回 fileId
     */
    String upload(File file) throws Exception;

    /**
     * 上传文件
     * 返回 fileId
     */
    String upload(MultipartFile file) throws Exception;

    /**
     * 上传文件
     * 返回 fileId
     */
    String upload(File file, String fileName) throws Exception;

    /**
     * 上传文件
     * 返回 fileId
     */
    String upload(MultipartFile file, String fileName) throws Exception;

    /**
     * 根据组名和远程文件名来删除一个文件
     * 0为成功，非0为失败，具体为错误代码
     */
    int delete(String groupName, String fileName) throws Exception;

    /**
     * 根据fileId来删除一个文件
     * 0为成功，非0为失败，具体为错误代码
     */
    int delete(String fileId) throws Exception;

    /**
     * 更新一个已经存在的文件
     */
    String update(String oldFileId, File newFile) throws Exception;

    /**
     * 更新一个已经存在的文件
     */
    String update(String oldFileId, File newFile, String newFileName) throws Exception;

    /**
     * 文件下载
     */
    InputStream download(String fileId) throws Exception;

    /**
     * 下载
     */
    byte[] downloadB(String fileId) throws Exception;
}
