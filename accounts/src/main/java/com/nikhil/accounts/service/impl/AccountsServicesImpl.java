package com.nikhil.accounts.service.impl;


import com.nikhil.accounts.constants.AccountsConstants;
import com.nikhil.accounts.dto.AccountsDto;
import com.nikhil.accounts.dto.CustomerDto;
import com.nikhil.accounts.dto.ResponseDto;
import com.nikhil.accounts.entity.Accounts;
import com.nikhil.accounts.entity.Customer;
import com.nikhil.accounts.exception.CustomerAlreadyExistsException;
import com.nikhil.accounts.exception.ResourceNotFoundException;
import com.nikhil.accounts.mapper.AccountsMapper;
import com.nikhil.accounts.mapper.CustomerMapper;
import com.nikhil.accounts.repository.AccountsRepository;
import com.nikhil.accounts.repository.CustomerRepositiry;
import com.nikhil.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServicesImpl implements IAccountsService {
    // shortcut key to format code in intellij: Ctrl + Alt + I
    // show all methods Alt+7

    private AccountsRepository accountsRepository;
    private CustomerRepositiry customerRepository;


    /**
     * This method creates a new account for the given customer.
     *
     * @param customerDto The CustomerDto object containing customer details.
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer=CustomerMapper.mapToCustomer(customerDto,new Customer()) ;
        Optional<Customer> customerOptional =customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        //derived named methods customerRepositiry.findByMobileNumberAndEmsil(customerDto.getMobileNumber(),email);

        if(customerOptional.isPresent()){
            throw  new CustomerAlreadyExistsException("Customer already exists with mobile number"+customerDto.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Annaonyms");

        Customer savedCustomer= customerRepository.save(customer);

        Accounts newAccount=  createNewAccount(savedCustomer);
        accountsRepository.save(newAccount);


    }


    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

//        newAccount.setAccountNumber(String.valueOf(randomAccNumber));
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Annaonyms");
        return newAccount;
    }





    @Override
    public CustomerDto fethchAccount(String mobileNumber) {


        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
        );

        Accounts accounts=accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Customer ID", customer.getCustomerId().toString())
        );

        CustomerDto customerDto= CustomerMapper.mapToCustomerDto(customer,new CustomerDto());

        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));

        return customerDto;

    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {

        Customer customer=customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }


}
