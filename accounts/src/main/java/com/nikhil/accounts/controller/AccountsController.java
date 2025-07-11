package com.nikhil.accounts.controller;

import com.nikhil.accounts.constants.AccountsConstants;
import com.nikhil.accounts.dto.CustomerDto;
import com.nikhil.accounts.dto.ResponseDto;
import com.nikhil.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {


  private IAccountsService iAccountsService;

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    //pojo to json or json to pojo by jackson libaries in spring
    iAccountsService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
  }


  @GetMapping("/fetch")
  public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "^[0-9]{10}$",message = "mobile number should be 10 digits")
                                                           String mobileNumber) {
    CustomerDto customerDto = iAccountsService.fethchAccount(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
  }



  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
    boolean isUpdated = iAccountsService.updateAccount(customerDto);
    if(isUpdated) {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }else{
      return ResponseEntity
              .status(HttpStatus.EXPECTATION_FAILED)
              .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
    }
  }

  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
          @Pattern(regexp = "^[0-9]{10}$",message = "mobile number should be 10 digits")
                                                          String mobileNumber) {
    boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
    if(isDeleted) {
      return ResponseEntity
              .status(HttpStatus.OK)
              .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
    }else{
      return ResponseEntity
              .status(HttpStatus.EXPECTATION_FAILED)
              .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
    }
  }




}
