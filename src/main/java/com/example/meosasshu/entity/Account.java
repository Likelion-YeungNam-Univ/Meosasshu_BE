package com.example.meosasshu.entity;

import com.example.meosasshu.dto.request.SignupReqDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Account {
    @Id
    @Column(name="account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;
    @NotBlank
    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String mobileNumber;
    private LocalDate birthDate;
    private String gender;


    @ManyToMany
    @JoinTable(
            name = "account_authority",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;


    public Account(SignupReqDTO signupReqDto, Address address) {
        this.email = signupReqDto.getEmail();
        this.password = signupReqDto.getPassword();
        this.name = signupReqDto.getName();

        this.mobileNumber=signupReqDto.getMobileNumber();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.birthDate = LocalDate.parse(signupReqDto.getBirthDate(), dateFormatter);

        this.gender=signupReqDto.getGender();

        this.address=address;

    }
}
