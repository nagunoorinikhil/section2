package com.nikhil.accounts.repository;

import com.nikhil.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepositiry extends JpaRepository<Accounts, Long> {

}
