package com.nikhil.accounts.controller;

import com.nikhil.accounts.constants.AccountsConstants;
import com.nikhil.accounts.dto.CustomerDto;
import com.nikhil.accounts.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {


    return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
  }


}
