package com.nikhil.accounts.service;

import com.nikhil.accounts.dto.CustomerDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

public interface IAccountsService {

    /**
     *
     * @param customerDto-CustomerDto Object
     * This method will create the new account for the given customer
     *
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fethchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);


    boolean deleteAccount(String mobileNumber);

}
