package com.wallet.blasko.repository;

import com.wallet.blasko.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAll();

}
