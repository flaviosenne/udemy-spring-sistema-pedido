package com.ordering.system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenDTO {
    @JsonProperty(value = "token")
    private String token;
}
