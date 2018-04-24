package org.csource.fastdfs.demo;


import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.api.FdfsClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class FdfsTest {

    @Autowired
    FdfsClient client;

    @Test
    public void upload() throws Exception{

        System.out.println(Thread.currentThread().getName());

        System.out.println(client.upload(new File("D:\\default.xml")));


    }

    @Test
    public void download() throws Exception{

        System.out.println(new String(client.downloadB("group1/M00/00/00/rBAIZVrewTqAZhaXAAAEXEJQQ_w952.xml")));


    }

    @Test
    public void uploadMulti() throws Exception{
        for (int i = 0; i < 3; i++) {


            new Thread(() ->{

                try {
                    upload();
                } catch (Exception e) {
                   log.error(e.getMessage(),e);
                }

            }).start();


        }
    }


}
