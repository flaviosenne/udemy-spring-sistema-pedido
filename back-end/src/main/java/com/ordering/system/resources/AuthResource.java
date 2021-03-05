package com.ordering.system.resources;

import javax.servlet.http.HttpServletResponse;

import com.ordering.system.security.UserSpringSecurity;
import com.ordering.system.security.utils.JWTUtil;
import com.ordering.system.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    
    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse res){
        UserSpringSecurity user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        res.addHeader("Authorization", "Bearer "+ token);
        return ResponseEntity.noContent().build();
    }
}
