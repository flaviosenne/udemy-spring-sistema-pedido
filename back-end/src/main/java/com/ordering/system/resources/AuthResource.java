package com.ordering.system.resources;

import javax.servlet.http.HttpServletResponse;

import com.ordering.system.dto.EmailDTO;
import com.ordering.system.security.UserSpringSecurity;
import com.ordering.system.security.utils.JWTUtil;
import com.ordering.system.services.AuthService;
import com.ordering.system.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse res){
        UserSpringSecurity user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        res.addHeader("Authorization", "Bearer "+ token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@RequestBody EmailDTO user){
        this.authService.sendNewPassword(user.getEmail());
        return ResponseEntity.noContent().build();
    }
  
}
