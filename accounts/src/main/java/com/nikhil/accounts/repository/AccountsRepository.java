package com.nikhil.accounts.repository;

import com.nikhil.accounts.entity.Accounts;
import com.nikhil.accounts.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

   Optional<Accounts> findByCustomerId(Long customerId);


   @Transactional
   @Modifying
   void deleteByCustomerId(Long customerId);
}
