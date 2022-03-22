package kr.co.erang.configuration;

import kr.co.erang.auth.FtpUser;
import kr.co.erang.user.domain.UserDomain;
import kr.co.erang.user.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.ftplet.Authentication;
import org.apache.ftpserver.ftplet.AuthenticationFailedException;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.impl.AbstractUserManager;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

@Slf4j
@Component
@RequiredArgsConstructor
public class FtpUserManager extends AbstractUserManager {

    // get user persistence
    private final UserPersistence userPersistence;

    @Override
    public User getUserByName(String s) throws FtpException {
        log.info("get user name ::: {}", s);
        UserDomain findUser = userPersistence.findByUserId(s).orElse(null);
        return new FtpUser(findUser);

    }

    @Override
    public String[] getAllUserNames() throws FtpException {
        return new String[0];
    }

    @Override
    public void delete(String s) throws FtpException {

    }

    @Override
    public void save(User user) throws FtpException {

    }

    @Override
    public boolean doesExist(String s) throws FtpException {
        return userPersistence.existsByUserId(s);
    }

    @Override
    public User authenticate(Authentication authentication) throws AuthenticationFailedException {

        return null;
    }
}
