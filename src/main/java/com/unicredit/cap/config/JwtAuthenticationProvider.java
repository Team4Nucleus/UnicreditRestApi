package com.unicredit.cap.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.unicredit.cap.busineslogic.UserAuthenticationService;
import com.unicredit.cap.model.AppRola;
import com.unicredit.cap.model.AppUser;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAuthenticationService authenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AppUser user = authenticationService.authenticateToken((String) authentication.getCredentials());
        
        List<String> roles = new ArrayList<String>();
        
        for(AppRola rol : user.getRoles())
        {
        	roles.add(rol.getRolename());
        }
        
        return new JwtAuthenticatedUserToken(user.getUsername(), roles);
    }

    /*
     Returns true if this AuthenticationProvider supports the indicated Authentication object.
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthToken.class.equals(aClass);
    }
}
