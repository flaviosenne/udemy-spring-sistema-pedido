package com.ordering.system.dto;

import lombok.*;

@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String email;
    private String password;

}
