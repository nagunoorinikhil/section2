package com.nikhil.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account number should not be empty or null")
    @Pattern(regexp = "^[0-9]{10}$",message = "Account number should be 10 digits")
    private Long accountNumber;
    @NotEmpty(message = "Account type should not be empty or null")
    private String accountType;
    @NotEmpty(message = "Branch address should not be empty or null")
    private String branchAddress;
}
