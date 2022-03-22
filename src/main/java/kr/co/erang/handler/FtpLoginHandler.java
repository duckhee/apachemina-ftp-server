package kr.co.erang.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.impl.FtpHandler;
import org.apache.ftpserver.impl.FtpIoSession;
import org.apache.ftpserver.impl.FtpServerContext;
import org.apache.ftpserver.listener.Listener;
import org.apache.mina.core.session.IdleStatus;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class FtpLoginHandler implements FtpHandler {

    @Override
    public void init(FtpServerContext ftpServerContext, Listener listener) {
        log.info("get ftp Init ::: {}", ftpServerContext.toString());

        UserManager userManager = ftpServerContext.getUserManager();

    }

    @Override
    public void sessionCreated(FtpIoSession ftpIoSession) throws Exception {
        User user = ftpIoSession.getFtpletSession().getUser();
        if(!user.getEnabled()){
            log.info("user can not used :::: {}", user.toString());
            ftpIoSession.close();
            return;
        }


    }

    @Override
    public void sessionOpened(FtpIoSession ftpIoSession) throws Exception {
        ftpIoSession.getFtpletSession().getUser();
    }

    @Override
    public void sessionClosed(FtpIoSession ftpIoSession) throws Exception {

    }

    @Override
    public void sessionIdle(FtpIoSession ftpIoSession, IdleStatus idleStatus) throws Exception {

    }

    @Override
    public void exceptionCaught(FtpIoSession ftpIoSession, Throwable throwable) throws Exception {

    }

    @Override
    public void messageReceived(FtpIoSession ftpIoSession, FtpRequest ftpRequest) throws Exception {

    }

    @Override
    public void messageSent(FtpIoSession ftpIoSession, FtpReply ftpReply) throws Exception {

    }
}
