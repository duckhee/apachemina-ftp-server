package kr.co.erang.auth.ftplet;

import kr.co.erang.auth.FtpUser;
import kr.co.erang.user.domain.UserDomain;
import kr.co.erang.user.persistence.UserPersistence;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ftpserver.ftplet.*;
import org.apache.ftpserver.usermanager.UserManagerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFtplet extends DefaultFtplet {

    private final UserManager userManager;

    @Override
    public FtpletResult beforeCommand(FtpSession session, FtpRequest request) throws FtpException, IOException {
        log.info("get command before ::: {}", session);
        return super.beforeCommand(session, request);
    }

    @Override
    public FtpletResult onConnect(FtpSession session) throws FtpException, IOException {
        log.info("get connect session :::: {}, session user ::: {}, and user home dir ::: {}", session, session.getUser().toString(), session.getUser().getHomeDirectory());
        InetSocketAddress clientAddress = session.getClientAddress();
        log.info("user address ::: {}", clientAddress.toString());
        return super.onConnect(session);
    }



    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException, IOException {
        log.info("get dis connect session :::: {}", session);
        InetSocketAddress clientAddress = session.getClientAddress();
//        log.info("user address ::: {}", clientAddress.toString());
        return super.onDisconnect(session);
    }

    // login process
    @Override
    public FtpletResult onLogin(FtpSession session, FtpRequest request) throws FtpException, IOException {
        log.info("login call ");
        System.out.println(String.format("User %s logged.", session.toString()));

        log.info("get login session :::: {}, get request :::: {}, request arg ::: {}", session, request, request.getArgument());
        User sessionUser = session.getUser();
        if(sessionUser == null){
            return FtpletResult.DISCONNECT;
        }
        FtpUser user = (FtpUser) sessionUser;
        if(user == null){
            return FtpletResult.DISCONNECT;
        }

        InetSocketAddress clientAddress = session.getClientAddress();
//        log.info("user address ::: {}", clientAddress.toString());
        return super.onLogin(session, request);
    }

    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        String command = request.getCommand();
        String requestLine = request.getRequestLine();
        String argument = request.getArgument();
        log.info("get upload start session ::: {}, request ::: {}, command ::: {}, requestLine :::: {}, argument :::: {}", session, request, command, requestLine, argument);
        InetSocketAddress clientAddress = session.getClientAddress();
//        log.info("user address ::: {}", clientAddress.toString());
        return super.onUploadStart(session, request);
    }

    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        String command = request.getCommand();
        String requestLine = request.getRequestLine();
        String argument = request.getArgument();
        log.info("get upload start session ::: {}, request ::: {}, command ::: {}, requestLine :::: {}, argument :::: {}", session, request, command, requestLine, argument);
        InetSocketAddress clientAddress = session.getClientAddress();
//        log.info("user address ::: {}", clientAddress.toString());
        return super.onUploadEnd(session, request);
    }
}
