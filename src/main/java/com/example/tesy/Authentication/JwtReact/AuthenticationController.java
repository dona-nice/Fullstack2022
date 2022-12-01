package com.example.tesy.Authentication.JwtReact;

import com.example.tesy.Authentication.ApplicationUser;
import com.example.tesy.Authentication.JwtReact.JwtUtil;
import com.example.tesy.Authentication.jwt.UsernameAndPasswordAuthenticationRequest;
import com.example.tesy.people.PeopleEntity;
import com.example.tesy.people.PeopleRepository;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.AbstractList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private String SECRET_KEY = "testy354jfiuufjcoof6securitytesty3546securitytesty3546securitytesty3546securitytesty3546security";

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody @Validated UsernameAndPasswordAuthenticationRequest request){
        System.out.println(request.getUsername());
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(),
                                    request.getPassword()
                            )
                    );

            ApplicationUser applicationUser = (ApplicationUser) authenticate.getPrincipal();

            String token = "Bearer "+ Jwts.builder()
                    .setSubject(applicationUser.getUsername())
                    .claim("authorities", applicationUser.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                    .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .compact();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION ,token)
                    .contentType(MediaType.valueOf("application/json"))
                    .body(Map.of("Token",token, "username",applicationUser.getUsername(),"Authorities",applicationUser.getAuthorities(),"realName", peopleRepository.findPeopleByUsername(applicationUser.getUsername()).get().getRealName()));



        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
