package org.csource.fastdfs;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ToString
@ConfigurationProperties(prefix = "fdfs")
public class FdfsClientProperties {

    private String connectTimeoutInSeconds = "5";

    private String networkTimeoutInSeconds = "30";

    private String charset = "UTF-8";

    private String httpAntiStealToken = "false";

    /**
     * 例子 FastDFS1234567890
     */
    private String httpSecretKey = "";

    private String httpTrackerHttpPort = "80";

    /**
     * 例子 172.0.0.1:22122,172.0.0.2:22122
     */
    private String trackerServers;

}
