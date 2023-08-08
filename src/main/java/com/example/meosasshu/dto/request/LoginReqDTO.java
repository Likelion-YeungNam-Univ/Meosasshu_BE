package com.example.meosasshu.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginReqDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
