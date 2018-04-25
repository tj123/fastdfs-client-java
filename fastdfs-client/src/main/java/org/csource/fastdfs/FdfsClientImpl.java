package org.csource.fastdfs;

import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.api.FdfsClient;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 注意每次访问都重新 new 一个 storageClient 不然在多线程访问会出问题
 */
public class FdfsClientImpl implements FdfsClient {

    public void init(Properties clientProperties) throws Exception {
        ClientGlobal.initByProperties(clientProperties);
    }

    @Override
    public String upload(File file) throws Exception {
        return upload(new FileInputStream(file), file.getName());
    }

    @Override
    public String upload(byte[] fileContent, String fileName) throws Exception {
        return new StorageClient1().upload_file1(fileContent, getFileExt(fileName), null);
    }

    @Override
    public String upload(InputStream inputStream, String fileName) throws Exception {
        try {
            byte[] buff = new byte[inputStream.available()];
            inputStream.read(buff);
            return upload(buff, fileName);
        } finally {
            if (inputStream != null) {
                inputStream.close();
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
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
