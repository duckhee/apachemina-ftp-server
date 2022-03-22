package kr.co.erang.configuration;

import kr.co.erang.auth.ftplet.AuthFtplet;
import kr.co.erang.configuration.factory.DBUserManager;
import kr.co.erang.user.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.*;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.ftpletcontainer.FtpletContainer;
import org.apache.ftpserver.ftpletcontainer.impl.DefaultFtpletContainer;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.UserManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class FTPConfig {

//    private final DatabaseUserManagerFactory userManagerFactory;
    private final UserPersistence userPersistence;
    private final AppConfig appConfig;

    @Bean
    public DataConnectionConfiguration connectionConfiguration() {
        DataConnectionConfigurationFactory dataConnectionConfigurationFactory = new DataConnectionConfigurationFactory();
        dataConnectionConfigurationFactory.setPassivePorts("10000-10500");
        DataConnectionConfiguration dataConnectionConfiguration = dataConnectionConfigurationFactory.createDataConnectionConfiguration();

        return dataConnectionConfiguration;
    }

    /*  @Bean
      public SessionFilter sessionFilter() throws UnknownHostException {
          RemoteIpFilter subnets = new RemoteIpFilter(IpFilterType.ALLOW, "127.0.0.1");
          return subnets;
      }
  */
    @Bean
    public ListenerFactory listenerFactory() throws UnknownHostException {
        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setDataConnectionConfiguration(connectionConfiguration());
//        listenerFactory.setSessionFilter(sessionFilter());
        log.info("get ftp listener factory");
        return listenerFactory;
    }

    @Bean
    public ConnectionConfigFactory connectionConfigFactory() {
        ConnectionConfigFactory connectionConfigFactory = new ConnectionConfigFactory();
//        connectionConfigFactory.setMaxLogins(0);
        // TODO Change get core and thread
//        connectionConfigFactory.setMaxThreads(12);
        connectionConfigFactory.setAnonymousLoginEnabled(true);
        log.info("setting ftp factory");
        return connectionConfigFactory;
    }


    @Bean
    public FtpletContainer ftpletContainer() {
        Map<String, Ftplet> stringFtpletMap = new HashMap<>();
        stringFtpletMap.put("default", new AuthFtplet(userManager()));
        DefaultFtpletContainer defaultFtpletContainer = new DefaultFtpletContainer(stringFtpletMap);
        return defaultFtpletContainer;
    }

    @Bean
    public FtpServerFactory ftpServerFactory() {
        FtpServerFactory ftpServerFactory = new FtpServerFactory();
        FtpletContainer ftpletContainer = ftpletContainer();
        log.info("get ftp let container ::: {}", ftpletContainer.getFtplets().get("default"));
        ftpServerFactory.getFileSystem();
        ftpServerFactory.setFtplets(ftpletContainer.getFtplets());
        // user setting
//        ftpServerFactory.setUserManager();
        log.info("user manager ::: {}", ftpServerFactory.getUserManager().toString());
        return ftpServerFactory;
    }

    @Bean
    public UserManagerFactory userManagerFactory() {
        PropertiesUserManagerFactory propertiesUserManagerFactory = new PropertiesUserManagerFactory();
        propertiesUserManagerFactory.setAdminName("admin");
        propertiesUserManagerFactory.setPasswordEncryptor(appConfig.passwordEncryptor());
        return propertiesUserManagerFactory;
    }

    @Bean
    public UserManager userManager() {
        UserManager userManager = userManagerFactory().createUserManager();
        return userManager;
    }


    @Bean
    public FtpServer ftpServer() throws UnknownHostException, FtpException {
        ListenerFactory listenerFactory = listenerFactory();
        listenerFactory.setPort(232);
        // configuration factory
        ConnectionConfigFactory connectionConfigFactory = connectionConfigFactory();
        log.info("get connection config :::: {}", connectionConfigFactory.getMaxAnonymousLogins());
        // listener setting
        Listener listener = listenerFactory.createListener();
        // ftp server factory
        FtpServerFactory ftpServerFactory = ftpServerFactory();
        // ftp server setting user manager
//        ftpServerFactory.setUserManager(new DBUserManager());
        // ftp server setting config
        ftpServerFactory.setConnectionConfig(connectionConfigFactory.createConnectionConfig());
        // registe listener
        ftpServerFactory.addListener("default", listener);
        FtpServer server = ftpServerFactory.createServer();
        // get ftp server port
        log.info("get ftp server port ::: {} ", listenerFactory.getPort());
        server.start();
        return server;
    }
/*
    @Bean
    public FtpServerContext defaultFtpServerContext() throws Exception {
        DefaultFtpServerContext defaultFtpServerContext = new DefaultFtpServerContext();
        defaultFtpServerContext.setUserManager(userManager);
        defaultFtpServerContext.setConnectionConfig(connectionConfigFactory().createConnectionConfig());
        return defaultFtpServerContext;
    }*/

}
