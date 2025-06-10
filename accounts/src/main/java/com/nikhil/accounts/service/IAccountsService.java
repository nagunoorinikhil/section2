package com.nikhil.accounts.service;

import com.nikhil.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto-CustomerDto Object
     * This method will create the new account for the given customer
     *
     */
    void createAccount(CustomerDto customerDto);


}
