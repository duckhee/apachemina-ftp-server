package kr.co.erang.configuration.factory;

import kr.co.erang.auth.FtpUser;
import kr.co.erang.user.domain.UserDomain;
import kr.co.erang.user.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import org.apache.ftpserver.ftplet.*;
import org.springframework.stereotype.Component;

/**
 * Ftp User Login using this
 */
@Component
@RequiredArgsConstructor
public class DBUserManager implements UserManager {

    private final UserPersistence userPersistence;

    @Override
    public User getUserByName(String username) throws FtpException {
        UserDomain findUser = userPersistence.findByUserId(username).orElse(null);
        return new FtpUser(findUser);
    }

    @Override
    public String[] getAllUserNames() throws FtpException {
        return new String[0];
    }

    @Override
    public void delete(String username) throws FtpException {

    }

    @Override
    public void save(User user) throws FtpException {

    }

    @Override
    public boolean doesExist(String username) throws FtpException {
        return userPersistence.existsByUserId(username);
    }

    @Override
    public User authenticate(Authentication authentication) throws AuthenticationFailedException {

        return null;
    }

    @Override
    public String getAdminName() throws FtpException {
        return null;
    }

    @Override
    public boolean isAdmin(String username) throws FtpException {
        return false;
    }
}
