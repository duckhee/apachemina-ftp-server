package kr.co.erang.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "ftp")
public class FtpProperties {

    private String host = "";

    // testing window path
    private String rootPath = "C:/Users/CANDH105/Desktop";
}
