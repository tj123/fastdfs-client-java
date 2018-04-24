package org.csource.fastdfs;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.csource.fastdfs.api.FdfsClient;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 注意每次访问都重新 new 一个 storageClient 不然在多线程访问会出问题
 */
public class FdfsClientImpl implements FdfsClient {

    private static final Log log = LogFactory.getLog(FdfsClientImpl.class);

    public void init(Properties clientProperties) throws Exception {
        ClientGlobal.initByProperties(clientProperties);
    }

    @Override
    public String upload(File file) throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int len = fis.available();
            byte[]  file_buff = new byte[len];
            fis.read(file_buff);
            return new StorageClient1().upload_file1(file_buff, getFileExt(file.getName()), null);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }


    @Override
    public String upload(MultipartFile file) throws Exception {
        InputStream fis = null;
        try {
            fis = file.getInputStream();
            byte[] file_buff = null;
            int len = fis.available();
            file_buff = new byte[len];
            fis.read(file_buff);
            return new StorageClient1().upload_file1(file_buff, getFileExt(file.getOriginalFilename()), null);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    @Override
    public String upload(File file, String fileName) throws Exception {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int len = fis.available();
            byte[] file_buff = new byte[len];
            fis.read(file_buff);
            return new StorageClient1().upload_file1(file_buff, getFileExt(fileName), null);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    @Override
    public String upload(MultipartFile file, String fileName) throws Exception {
        InputStream fis = null;
        try {
            fis = file.getInputStream();
            int len = fis.available();
            byte[] file_buff = new byte[len];
            fis.read(file_buff);
            return new StorageClient1().upload_file1(file_buff, getFileExt(fileName), null);
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    @Override
    public int delete(String groupName, String fileName) throws Exception {
        return new StorageClient1().delete_file(groupName == null ? "group1" : groupName, fileName);
    }

    @Override
    public int delete(String fileId) throws Exception {
        return new StorageClient1().delete_file1(fileId);
    }

    @Override
    public String update(String oldFileId, File newFile) throws Exception {
        String fid = upload(newFile);
        delete(oldFileId);
        return fid;
    }

    @Override
    public String update(String oldFileId, File newFile, String newFileName) throws Exception {
        String fileId = upload(newFile, newFileName);
        delete(oldFileId);
        return fileId;
    }

    @Override
    public InputStream download(String fileId) throws Exception {
        byte[] bytes = new StorageClient1().download_file1(fileId);
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public byte[] downloadB(String fileId) throws Exception {
        return new StorageClient1().download_file1(fileId);
    }

    private String getFileExt(String fileName) {
        if (StringUtils.isBlank(fileName) || !fileName.contains(".")) {
            return fileName;
        } else {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
    }

}
