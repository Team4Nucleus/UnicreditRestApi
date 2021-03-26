package com.unicredit.cap.controller;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.unicredit.cap.busineslogic.AppUserService;
import com.unicredit.cap.busineslogic.JwtService;
import com.unicredit.cap.busineslogic.PlacementService;
import com.unicredit.cap.busineslogic.UserAuthenticationService;
import com.unicredit.cap.busineslogic.UserService;
import com.unicredit.cap.exception.FailedToLoginException;
import com.unicredit.cap.helper.AuthRestProvider;
import com.unicredit.cap.helper.AuthRestReq;
import com.unicredit.cap.helper.JsonTestObj;
import com.unicredit.cap.model.AppRola;
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
    
	@Autowired
	private Environment env;

	@Autowired
	private JwtService jwtService;
	
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public AuthenticationResponse userLogin(@RequestBody UserCredentials userCredentials) throws FailedToLoginException {

        if (userCredentials == null || (userCredentials.getUsername() == null || userCredentials.getPassword() == null)) {
            throw new FailedToLoginException("Missing login credentials ");
        }

        
        RestTemplate restTemplate = new RestTemplate();
        String OK = "";
       
       // final String baseUrl = "http://srvdevapp:8080/authserverapi/auth/login";
        final String baseUrl = env.getProperty("adservice.auth.link");
        URI uri;
        
		try {
			
			uri = new URI(baseUrl);
     
			if (env.getProperty("adservice.auth.host").equals("nukleus"))
			{
				JsonTestObj obj = new JsonTestObj("asas", "sasas", "2");
				JsonTestObj result = restTemplate.postForObject(uri, obj, JsonTestObj.class);
				OK = result.getTitle();
			}
			else
			{
				AuthRestReq obj = new AuthRestReq(userCredentials.getUsername(), userCredentials.getPassword(), env.getProperty("adservice.auth.module"));
				AuthRestProvider result = restTemplate.postForObject(uri, obj, AuthRestProvider.class);
				OK = result.getAccessToken();
			}
			
			
			
        	} catch (Exception e) {

        		 Logger logger = Logger.getLogger(JsonTestObj.class);
        	        logger.error("Auth Service Error: " + e.getMessage().toString());

		}
		
		
		  if ( OK.equals("") || OK.isEmpty())
	        	throw new FailedToLoginException(String.format(" unable to authenticate user [%s] ", userCredentials.getUsername()));
		
		  List<String> roles = new ArrayList<String>();
		  
		try {

		roles = jwtService.verifyTokenAuthRoles(OK);
		//"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb3ZhbmEubWlsamtvdmljIiwidXNlcklkIjozODg4ODYxNjcsInVzZXJOYW1lIjoiSk9WQU5BLk1JTEpLT1ZJQyIsInJvbGVzIjpbIlJPTEVfQURNSU4iLCJDQVAgcHJpc3R1cCIsIlJPTEVfUk0iLCJST0xFX01BTkFHRVIiLCJST0xFX1VTRVIiXSwiaWF0IjoxNjEyOTQyNDc5LCJleHAiOjE2MTM1NDcyNzgsImlzcyI6IkF1dGggU2VydmVyIFVCQiJ9.Tz0fkIY_jMeb8x-4hAbtY0430BA_wVGk8sgG-JKDQyoNS1DPE8BvSfQxzVui7q7sGRdj_YKCPdTnF7Lk1Md3Ng"

		} catch(Exception ex) {
			 Logger logger = Logger.getLogger(JsonTestObj.class);
			 logger.error("Auth Token Verification: " + ex.getMessage());
		}
		
		
        String token = authenticationService.authenticateUser(userCredentials.getUsername(), userCredentials.getPassword());

        if (token != null) {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setUsername(userCredentials.getUsername());
            
            List<AppRola> lst = new ArrayList<AppRola>();
            for (String s : roles ) {
            	AppRola r = appUserService.getRolaByName(s);
            	if (r != null)
            		lst.add(r);
            }
            
            // authenticationResponse.setRoleNames(appUserService.getUserRoles(userCredentials.getUsername()));
            authenticationResponse.setRoleNames(lst);
            authenticationResponse.setUserId(appUserService.getUserByUsername(userCredentials.getUsername()).getId());
            authenticationResponse.setOrgId(userService.getUserByUsername(userCredentials.getUsername()).getHrOrganization());
            authenticationResponse.setUser(userService.getUserByUsername(userCredentials.getUsername()));
            authenticationResponse.setToken(token);
            
            
            return authenticationResponse;
        }
        
        throw new FailedToLoginException(String.format(" unable to authenticate user [%s] ", userCredentials.getUsername()));
    }
}
