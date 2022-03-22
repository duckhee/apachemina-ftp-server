package kr.co.erang.configuration.factory;

import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.usermanager.DbUserManagerFactory;
import org.apache.ftpserver.usermanager.PasswordEncryptor;

import javax.sql.DataSource;

public class DataBaseUserManagerFactory extends DbUserManagerFactory {

    public DataBaseUserManagerFactory() {
        super();
    }

    @Override
    public UserManager createUserManager() {
        return super.createUserManager();
    }

    @Override
    public String getAdminName() {
        return super.getAdminName();
    }

    @Override
    public void setAdminName(String adminName) {
        super.setAdminName(adminName);
    }

    @Override
    public DataSource getDataSource() {
        return super.getDataSource();
    }

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Override
    public String getSqlUserInsert() {
        return super.getSqlUserInsert();
    }

    @Override
    public void setSqlUserInsert(String sql) {
        super.setSqlUserInsert(sql);
    }

    @Override
    public String getSqlUserDelete() {
        return super.getSqlUserDelete();
    }

    @Override
    public void setSqlUserDelete(String sql) {
        super.setSqlUserDelete(sql);
    }

    @Override
    public String getSqlUserUpdate() {
        return super.getSqlUserUpdate();
    }

    @Override
    public void setSqlUserUpdate(String sql) {
        super.setSqlUserUpdate(sql);
    }

    @Override
    public String getSqlUserSelect() {
        return super.getSqlUserSelect();
    }

    @Override
    public void setSqlUserSelect(String sql) {
        super.setSqlUserSelect(sql);
    }

    @Override
    public String getSqlUserSelectAll() {
        return super.getSqlUserSelectAll();
    }

    @Override
    public void setSqlUserSelectAll(String sql) {
        super.setSqlUserSelectAll(sql);
    }

    @Override
    public String getSqlUserAuthenticate() {
        return super.getSqlUserAuthenticate();
    }

    @Override
    public void setSqlUserAuthenticate(String sql) {
        super.setSqlUserAuthenticate(sql);
    }

    @Override
    public String getSqlUserAdmin() {
        return super.getSqlUserAdmin();
    }

    @Override
    public void setSqlUserAdmin(String sql) {
        super.setSqlUserAdmin(sql);
    }

    @Override
    public PasswordEncryptor getPasswordEncryptor() {
        return super.getPasswordEncryptor();
    }

    @Override
    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        super.setPasswordEncryptor(passwordEncryptor);
    }
}
