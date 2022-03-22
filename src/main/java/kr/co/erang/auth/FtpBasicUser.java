package kr.co.erang.auth;

import kr.co.erang.user.domain.UserDomain;
import lombok.Data;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.usermanager.impl.BaseUser;

@Data
public class FtpBasicUser extends BaseUser {

    private FtpUser user;




}
