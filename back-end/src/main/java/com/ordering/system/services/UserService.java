package com.ordering.system.services;

import com.ordering.system.security.UserSpringSecurity;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {
    
    public static UserSpringSecurity authenticated(){
        try{

            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch(Exception e){
            return null;
        }
    }
}
