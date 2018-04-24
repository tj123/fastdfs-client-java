package org.csource.fastdfs.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FdfsClientImpl;
import org.csource.fastdfs.FdfsClientProperties;
import org.csource.fastdfs.api.FdfsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Slf4j
@Configuration
@EnableConfigurationProperties(FdfsClientProperties.class)
public class FdfsAutoConfig {

    @Autowired
    FdfsClientProperties properties;

    @Bean
    public FdfsClient fdfsClient() {
        FdfsClientImpl client = new FdfsClientImpl();
        if (StringUtils.isBlank(properties.getTrackerServers())) {
            throw new RuntimeException("trackerServers 不能为空");
        }
        Properties prop = new Properties();
        try {
            prop.setProperty(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, properties.getConnectTimeoutInSeconds());
            prop.setProperty(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, properties.getNetworkTimeoutInSeconds());
            prop.setProperty(ClientGlobal.PROP_KEY_CHARSET, properties.getCharset());
            prop.setProperty(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, properties.getHttpAntiStealToken());
            prop.setProperty(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, properties.getHttpSecretKey());
            prop.setProperty(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, properties.getHttpTrackerHttpPort());
            prop.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS, properties.getTrackerServers());
            client.init(prop);
        } catch (Exception e) {
            log.error("初始化fastdfs失败!", e);
        }
        return client;
    }


}
