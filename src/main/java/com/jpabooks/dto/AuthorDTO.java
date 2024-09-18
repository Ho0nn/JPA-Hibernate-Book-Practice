package com.jpabooks.dto;

import com.jpabooks.base.BaseDTO;
import com.jpabooks.validator.IpAddress;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthorDTO extends BaseDTO<Long> {

    @NotBlank
    private String name;


    @IpAddress()
    private String ipAddress;

    @Email(message = "{validation.constraints.email.message}")
    private String email;

    private String imgPath;




}
