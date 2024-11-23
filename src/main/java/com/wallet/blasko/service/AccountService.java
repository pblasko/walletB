package com.wallet.blasko.service;

import com.wallet.blasko.model.Account;
import com.wallet.blasko.model.Item;
import com.wallet.blasko.repository.AccountRepository;
import com.wallet.blasko.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ItemRepository itemRepository;

    public List<Account> getAllAccounts() {
        checkAllAccountsBalance();
        return accountRepository.findAll();
    }

    private void checkAllAccountsBalance() {
        List<Item> allItems = itemRepository.findAll();
        Map<Long, BigDecimal> balancies = new HashMap<>();
        for (Item item : allItems) {
            if (item.getAccount() != null) {
                Long accountId = item.getAccount().getId();
                BigDecimal sum;
                if (item.getCharging() != null && item.getCharging().compareTo(BigDecimal.ZERO) > 0) {
                    sum = item.getCharging().negate();
                } else if (item.getCharging() == null || item.getCharging().compareTo(BigDecimal.ZERO) == 0) {
                    sum = item.getCrediting();
                } else {
                    sum = BigDecimal.ZERO;
                }
                balancies.put(accountId, balancies.getOrDefault(accountId, BigDecimal.ZERO).add(sum));
            }
        }
        createActualBalancies(balancies);
    }

    private void createActualBalancies(Map<Long, BigDecimal> balancies) {
        for (Map.Entry<Long, BigDecimal> entry : balancies.entrySet()) {
            Account actualAccount = accountRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Account not found with ID: " + entry.getKey()));
            actualAccount.setActualBalance(entry.getValue());
            accountRepository.save(actualAccount);
        }
    }

}
