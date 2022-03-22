package kr.co.erang.configuration.factory;

import kr.co.erang.auth.FtpUser;
import kr.co.erang.user.domain.UserDomain;
import kr.co.erang.user.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.usermanager.PasswordEncryptor;
import org.apache.ftpserver.usermanager.impl.DbUserManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Ftp User Login using this
 */
public class DBUserManager extends DbUserManager {

    private static final String SELECT_ALL_USER = "";
    private static final String SELECT_USER = "";
    private static final String INSERT_USER = "";
    private static final String UPDATED_USER = "";
    private static final String DELETED_USER = "";


    public DBUserManager(DataSource dataSource, String selectAllStmt, String selectUserStmt, String insertUserStmt, String updateUserStmt, String deleteUserStmt, String authenticateStmt, String isAdminStmt, PasswordEncryptor passwordEncryptor, String adminName) {
        super(dataSource, selectAllStmt, selectUserStmt, insertUserStmt, updateUserStmt, deleteUserStmt, authenticateStmt, isAdminStmt, passwordEncryptor, adminName);
    }
}
