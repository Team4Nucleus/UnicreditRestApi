package com.unicredit.cap.busineslogic;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unicredit.cap.exception.FailedToLoginException;
import com.unicredit.cap.exception.JwtAuthenticationException;
import com.unicredit.cap.model.AppRola;
import com.unicredit.cap.model.AppUser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Component
public class UserAuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AppUserService userService;

    public String authenticateUser(String username, String password) throws FailedToLoginException {
        boolean isAuthenticated = false;
        
        //String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt(10)); 
        
        String pw_hash = userService.getUserByUsername(username).getPassword(); 
        
        if (BCrypt.checkpw(password, pw_hash)) {
            isAuthenticated = true;
        } 
        if (isAuthenticated) {
            try {
                return jwtService.generateToken(username);
            } catch (URISyntaxException | IOException e) {
                throw new FailedToLoginException(e.getMessage());
            }
        }
        throw new FailedToLoginException(String.format("Unable to authenticate user [%s]", username));
    }

    public AppUser authenticateToken(String jwtToken) throws JwtAuthenticationException {

        try {
            String username = jwtService.verifyToken(jwtToken);
            List<AppRola> userRoles = userService.getUserRoles(username);

            AppUser user = new AppUser();
            user.setUsername(username);
            user.setRoles(userRoles);
            return user;
        } catch (IOException | URISyntaxException e) {
            throw new JwtAuthenticationException(e.getMessage(), e);
        }
    }
}
