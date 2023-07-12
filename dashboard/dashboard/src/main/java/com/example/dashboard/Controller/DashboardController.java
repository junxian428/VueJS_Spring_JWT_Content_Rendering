package com.example.dashboard.Controller;

import java.util.HashMap;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dashboard.Entity.Token;
import com.example.dashboard.Entity.User;
import com.example.dashboard.Mapper.TokenMapper;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin(origins = "http://localhost:8081")
public class DashboardController {

    @Autowired
    private TokenMapper tokenMapper;

    public DashboardController(TokenMapper tokenMapper){
        this.tokenMapper = tokenMapper;
    }

    @GetMapping
    public String getDashboardData(@RequestHeader("Authorization") String authorizationHeader) {
        // Logic to retrieve and process dashboard data
        String username = "";


       // Logic to retrieve and process dashboard data
       System.out.println("Authorization Header: " + authorizationHeader);
       if (authorizationHeader != null && authorizationHeader.length() > 7) {
          authorizationHeader = authorizationHeader.substring(7);
     }
       // Retrieve the latest token using MyBatis
        // Retrieve token data from the database
   // Retrieve the latest token from the database
        try{
                 Token latestToken = tokenMapper.getTokenByTokenValue(authorizationHeader);

                 User userid = tokenMapper.getUserByUserID(Integer.toString(latestToken.getUser_Id()));


                if (latestToken != null && userid != null) {
                    // Process the token data as needed
                    System.out.println("Latest Token: " + latestToken.getToken());
                    System.out.println("User ID: " + latestToken.getUser_Id());
                    System.out.println("Username " + userid.getFirstname() + userid.getLastname());
                    username = userid.getFirstname() + userid.getLastname();
                    // ...
                } else {
                    // Token not found, handle the case accordingly
                    System.out.println("null");
                }
        
        }catch(Exception e){
            System.out.println(e);
        }
                

       
       return username;
    }
}