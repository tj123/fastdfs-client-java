package org.csource.fastdfs.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.api.FdfsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@Slf4j
@RestController
public class TestController {


    @Autowired
    FdfsClient client;


    @GetMapping("/")
    public String test() {
        for (int i = 0; i < 3; i++) {

            new Thread(() -> {
                try {
                    log.info(Thread.currentThread().getName());

                    log.info(client.upload(new File("D:\\default.xml")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();


        }
        return "hahah";
    }



}
