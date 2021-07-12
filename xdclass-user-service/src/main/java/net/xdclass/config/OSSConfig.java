package net.xdclass.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author WJH
 * @class xdclass-1024-shop OSSConfig
 * @date 2021/7/12 下午4:39
 * @QQ 1151777592
 */
@Data
@ConfigurationProperties(prefix = "aliyun.oss")
@Configuration
public class OSSConfig {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketname;
}
