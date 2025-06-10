package com.nikhil.accounts.repository;

import com.nikhil.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepositiry  extends JpaRepository<Customer, Long> {

}
