package com.unicredit.cap.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.unicredit.cap.busineslogic.AppUserService;
import com.unicredit.cap.busineslogic.UserAuthenticationService;
import com.unicredit.cap.busineslogic.UserService;
import com.unicredit.cap.exception.FailedToLoginException;
import com.unicredit.cap.model.AppUser;
import com.unicredit.cap.model.AuthenticationResponse;
import com.unicredit.cap.model.UserCredentials;

@RestController
public class TokenController {

    @Autowired
    private UserAuthenticationService authenticationService;
    
    @Autowired
    private AppUserService appUserService;
    
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public AuthenticationResponse userLogin(@RequestBody UserCredentials userCredentials) throws FailedToLoginException {

        if (userCredentials == null || (userCredentials.getUsername() == null || userCredentials.getPassword() == null)) {
            throw new FailedToLoginException("Missing login credentials ");
        }

        String token = authenticationService.authenticateUser(userCredentials.getUsername(), userCredentials.getPassword());

        if (token != null) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setUsername(userCredentials.getUsername());
            authenticationResponse.setRoleNames(appUserService.getUserRoles(userCredentials.getUsername()));
            authenticationResponse.setUserId(appUserService.getUserByUsername(userCredentials.getUsername()).getId());
            authenticationResponse.setOrgId(userService.getUserByUsername(userCredentials.getUsername()).getHrOrganization());
            authenticationResponse.setUser(userService.getUserByUsername(userCredentials.getUsername()));
            authenticationResponse.setToken(token);
            
            
            return authenticationResponse;
        }
        throw new FailedToLoginException(String.format(" unable to authenticate user [%s] ", userCredentials.getUsername()));
    }
}
