package com.nikhil.accounts.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "name should not be empty or null")
    @Size(min = 5,max=30 , message = "name should be between 5 and 30 characters")
    private String name;

    @NotEmpty(message = "email should not be empty")
    @Email(message = "email should be valid")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$",message = "mobile number should be 10 digits")
    private String mobileNumber;

    private  AccountsDto accountsDto;
}
