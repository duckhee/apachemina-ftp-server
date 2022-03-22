package kr.co.erang;

import kr.co.erang.configuration.FTPConfig;
import kr.co.erang.example.FTPServerModule;
import lombok.RequiredArgsConstructor;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;

import java.net.UnknownHostException;


@SpringBootApplication
@RequiredArgsConstructor
public class CatchTrapApplication {


    public static void main(String[] args) throws UnknownHostException, FtpException {
        SpringApplication.run(CatchTrapApplication.class, args);
//        FTPServerModule.instance().startModule();
    }

}
