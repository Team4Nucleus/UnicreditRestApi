package com.unicredit.cap.busineslogic;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicredit.cap.config.SecretKeyProvider;
import com.unicredit.cap.helper.JsonTestObj;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.ZoneOffset.UTC;

@Component
public class JwtService {

    private static final String ISSUER = "com.unicredit.cap";

    @Autowired
    private SecretKeyProvider secretKeyProvider;

    public String generateToken(String username) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Date expiration = Date.from(LocalDateTime.now(UTC).plusHours(2).toInstant(UTC));
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration)
                .setIssuer(ISSUER)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String verifyToken(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        //returning authenticated/verified username
        return claims.getBody().getSubject();
    }
    
    public List<String> verifyTokenAuthRoles(String token) throws JsonProcessingException, IOException {
        
    	 String[] split_string = token.split("\\.");
	        String base64EncodedHeader = split_string[0];
	        String base64EncodedBody = split_string[1];
	        String base64EncodedSignature = split_string[2];


	        Base64 base64Url = new Base64(true);
	        String header = new String(base64Url.decode(base64EncodedHeader));
	        String body = new String(base64Url.decode(base64EncodedBody));
	        
	     
	        	ObjectMapper mapper = new ObjectMapper();
	        
				JsonNode rootNode = mapper.readTree(body);
				
				Logger logger = Logger.getLogger(JsonTestObj.class);
				
			//	return rootNode.get("roles").asText();
				
				final JsonNode arrNode = new ObjectMapper().readTree(body).get("roles");
				final JsonNode arrNode1 = new ObjectMapper().readTree(body).path("roles");
				logger.info("roles get: " + arrNode.toString());
				List<String> roles = new ArrayList<String>();
				if (arrNode.isArray()) {
				    for (final JsonNode objNode : arrNode) {
				        roles.add(objNode.textValue());
				    }
				}
				
				return roles;
    	
    	
    }
    
    
}
