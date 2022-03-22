package kr.co.erang.auth;

import kr.co.erang.user.domain.UserDomain;
import lombok.Getter;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.UsernamePasswordAuthentication;
import org.apache.ftpserver.usermanager.impl.UserMetadata;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Transactional
public class FtpUser implements User {

    private UserDomain user;

    public FtpUser(UserDomain user){
        this.user = user;
    }

    @Override
    public String getName() {
        return this.user.getUserId();
    }

    @Override
    public String getPassword() {
        return this.user.getUserPw();
    }

    @Override
    public List<? extends Authority> getAuthorities() {
        return null;
    }

    @Override
    public List<? extends Authority> getAuthorities(Class<? extends Authority> aClass) {
        return null;
    }

    @Override
    public AuthorizationRequest authorize(AuthorizationRequest authorizationRequest) {
        UsernamePasswordAuthentication usernamePasswordAuthentication = new UsernamePasswordAuthentication(this.user.getUserId(), this.user.getUserPw());
        return (AuthorizationRequest) usernamePasswordAuthentication;
    }

    @Override
    public int getMaxIdleTime() {
        return 0;
    }

    @Override
    public boolean getEnabled() {
        return this.user.isUsedFtp();
    }

    @Override
    public String getHomeDirectory() {
        return this.user.getRootPath();
    }
}
