package com.wallet.blasko.service;

import com.wallet.blasko.model.*;
import com.wallet.blasko.repository.AccountRepository;
import com.wallet.blasko.repository.CategoryRepository;
import com.wallet.blasko.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemRepository itemRepository;

    private LocalDate currentDate = LocalDate.now();

    public SendingItem createSendingItem () {
        SendingItem sendingItem = new SendingItem();
        sendingItem.setAccounts(getAllAccounts());
        sendingItem.setCategories(getAllCategories());
        sendingItem.setItems(getAllActualItems());
        return sendingItem;
    }

    private List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    private List<Category> getAllCategories() {
        return categoryRepository.findByIdGreaterThan(Long.valueOf(4));
    }

    private List<Item> getAllActualItems() {
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        return itemRepository.findAllByYearAndMonth(year, month);
    }

    public String createNewItem(NewItem newItem) {
        LocalDate date = LocalDate.parse(newItem.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Account account = accountRepository.findById(Long.valueOf(newItem.getAccountId()))
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + newItem.getAccountId()));
        Category category = categoryRepository.findById(Long.valueOf(newItem.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + newItem.getCategoryId()));
        Item item = new Item();
        item.setActualDate(date);
        item.setCity(newItem.getCity());
        item.setPlace(newItem.getPlace());
        item.setComment(newItem.getComment());
        item.setAccount(account);
        item.setCategory(category);
        if ("charging".equalsIgnoreCase(category.getType())) {
            item.setCharging(newItem.getNumber());
            item.setCrediting(BigDecimal.ZERO);
        } else if ("crediting".equalsIgnoreCase(category.getType())) {
            item.setCharging(BigDecimal.ZERO);
            item.setCrediting(newItem.getNumber());
        } else {
            item.setCharging(BigDecimal.ZERO);
            item.setCrediting(BigDecimal.ZERO);
        }
        itemRepository.save(item);
        return "successful saving new item";
    }

    public String settingItem(NewItem newItem) {
        LocalDate date = LocalDate.parse(newItem.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Account account = accountRepository.findById(Long.valueOf(newItem.getAccountId()))
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + newItem.getAccountId()));
        Category category = categoryRepository.findById(Long.valueOf(newItem.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + newItem.getCategoryId()));
        Optional<Item> optionalItem = itemRepository.findById(newItem.getId());
        Item item;
        if (optionalItem.isPresent()) {
            item = optionalItem.get();
        } else {
            throw new NoSuchElementException("Item not found!");
        }
        item.setActualDate(date);
        item.setCity(newItem.getCity());
        item.setPlace(newItem.getPlace());
        item.setComment(newItem.getComment());
        item.setAccount(account);
        item.setCategory(category);
        if ("charging".equalsIgnoreCase(category.getType())) {
            item.setCharging(newItem.getNumber());
            item.setCrediting(BigDecimal.ZERO);
        } else if ("crediting".equalsIgnoreCase(category.getType())) {
            item.setCharging(BigDecimal.ZERO);
            item.setCrediting(newItem.getNumber());
        } else {
            item.setCharging(BigDecimal.ZERO);
            item.setCrediting(BigDecimal.ZERO);
        }
        itemRepository.save(item);
        return "successful setting item";
    }

    public String deleteItem(NewItem newItem) {
        Optional<Item> optionalItem = itemRepository.findById(newItem.getId());
        Item item;
        if (optionalItem.isPresent()) {
            item = optionalItem.get();
        } else {
            throw new NoSuchElementException("Item not found!");
        }
        itemRepository.deleteById(newItem.getId());
        return "successful setting item";
    }

    public String setCurrentDate(LocalDate date) {
        this.currentDate = date;
        return "" + date;
    }

}
