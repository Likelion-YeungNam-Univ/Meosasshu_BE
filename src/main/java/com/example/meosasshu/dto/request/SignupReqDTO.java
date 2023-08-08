package com.example.meosasshu.dto.request;


import com.example.meosasshu.entity.Account;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupReqDTO {

    @NotBlank
    private String nickname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String mobileNumber;
    private String gender;
    private String birthDate;

    private String city;
    private String street;
    private String zipcode;


    public SignupReqDTO(Account account) {
        this.email=account.getEmail();
        this.nickname=account.getNickname();
//        this.password=account.getPassword(); 패스워드는 외부로 보내지 않음
    }

    public void setEncodePwd(String encodePwd) {
        this.password = encodePwd;
    }
}
