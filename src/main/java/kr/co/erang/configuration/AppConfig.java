package kr.co.erang.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.usermanager.PasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public PasswordEncryptor passwordEncryptor() {
        PasswordEncoder passwordEncoder = passwordEncoder();
        // password encoding decoding using spring security
        return new PasswordEncryptor() {
            @Override
            public String encrypt(String password) {
//                return passwordEncoder.encode(password);
                log.info("get encoding :: {}", password);
                return password;
            }

            @Override
            public boolean matches(String passwordToCheck, String storedPassword) {
//                return passwordEncoder.matches(storedPassword, passwordToCheck);
                log.info("user password match ::: {}, {}", passwordToCheck, storedPassword);
                return true;
            }
        };
    }
}
