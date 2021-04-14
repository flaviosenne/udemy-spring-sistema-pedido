package com.ordering.system.resources;

import javax.servlet.http.HttpServletResponse;

import com.ordering.system.domains.Client;
import com.ordering.system.dto.EmailDTO;
import com.ordering.system.dto.LoginDTO;
import com.ordering.system.dto.TokenDTO;
import com.ordering.system.security.UserSpringSecurity;
import com.ordering.system.security.utils.JWTUtil;
import com.ordering.system.services.AuthService;
import com.ordering.system.services.ClientService;
import com.ordering.system.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
    
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @Autowired
    private ClientService clientService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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


    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO user){
        Client client = clientService.getClientByEmail(user.getEmail()).getBody();
        if(client != null){
            Boolean match = bCryptPasswordEncoder.matches(user.getPassword(), client.getPassword());
            if(match){
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.status(200).body(
                        TokenDTO.builder().token(token).build());
            }

        }

        return ResponseEntity.status(401).body(null);

    }
}
