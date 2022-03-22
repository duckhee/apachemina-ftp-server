package kr.co.erang.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.ftplet.*;

import java.io.IOException;

@Slf4j
public class FtpletCustom extends DefaultFtplet {

    private FTPServerModule module = null;
    private UserManager userManager = null;

    public FtpletCustom(FTPServerModule module, UserManager userManager) {
        this.module = module;
        this.userManager = userManager;
    }

/*

    public FtpletResult beforeCommand(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        String command = request.getCommand().toUpperCase();
        log.info("before command ::: {}", command);
        if ("DELE".equals(command)) {
            return onDeleteStart(session, request);
        } else if ("STOR".equals(command)) {
            return onUploadStart(session, request);
        } else if ("RETR".equals(command)) {
            return onDownloadStart(session, request);
        } else if ("RMD".equals(command)) {
            return onRmdirStart(session, request);
        } else if ("MKD".equals(command)) {
            return onMkdirStart(session, request);
        } else if ("APPE".equals(command)) {
            return onAppendStart(session, request);
        } else if ("STOU".equals(command)) {
            return onUploadUniqueStart(session, request);
        } else if ("RNTO".equals(command)) {
            return onRenameStart(session, request);
        } else if ("SITE".equals(command)) {
            return onSite(session, request);
        } else {
            // TODO should we call a catch all?
            return null;
        }
    }

    public FtpletResult afterCommand(FtpSession session, FtpRequest request, FtpReply reply)
            throws FtpException, IOException {

        // the reply is ignored for these callbacks

        String command = request.getCommand().toUpperCase();
        log.info("after command ::: {}", command);
        if ("PASS".equals(command)) {
            return onLogin(session, request);
        } else if ("DELE".equals(command)) {
            return onDeleteEnd(session, request);
        } else if ("STOR".equals(command)) {
            return onUploadEnd(session, request);
        } else if ("RETR".equals(command)) {
            return onDownloadEnd(session, request);
        } else if ("RMD".equals(command)) {
            return onRmdirEnd(session, request);
        } else if ("MKD".equals(command)) {
            return onMkdirEnd(session, request);
        } else if ("APPE".equals(command)) {
            return onAppendEnd(session, request);
        } else if ("STOU".equals(command)) {
            return onUploadUniqueEnd(session, request);
        } else if ("RNTO".equals(command)) {
            return onRenameEnd(session, request);
        } else {
            // TODO should we call a catch all?

            return null;
        }
    }
*/


    @Override
    public FtpletResult onLogin(FtpSession session, FtpRequest request) throws FtpException, IOException {
        // ATTEMPTION: The object 'session.getUserArgument()' is null in this event.
        String ftpUsername = session.getUser().getName();
        log.info(String.format("User %s logged.", ftpUsername));
        return super.onLogin(session, request);
    }

    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        String ftpUsername = session.getUser().getName();
        if (!userManager.doesExist(ftpUsername)) {
            return FtpletResult.DISCONNECT;
        }
        return super.onUploadStart(session, request);
    }

    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        String username = session.getUser().getName();
        String directory = session.getUser().getHomeDirectory();
        String filename = request.getArgument();

        log.info(String.format("File received: %s", filename));

        FileData fileData = new FileData(username, directory, filename);
        module.processFileData(fileData);

        return super.onUploadEnd(session, request);
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException, IOException {
        // The object 'User' will be null when connection to FTP Server failed by password wrong, e.g
        if (session.getUser() != null) {
            String ftpUsername = session.getUser().getName();
            log.info(String.format("User %s disconnected", ftpUsername));
        }
        return super.onDisconnect(session);
    }

}
