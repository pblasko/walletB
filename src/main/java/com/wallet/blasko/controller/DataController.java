package com.wallet.blasko.controller;

import com.wallet.blasko.model.Account;
import com.wallet.blasko.model.NewDate;
import com.wallet.blasko.model.NewItem;
import com.wallet.blasko.model.SendingItem;
import com.wallet.blasko.service.AccountService;
import com.wallet.blasko.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:8100", "http://192.168.1.239:8100"})
public class DataController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/items")
    public SendingItem getAllActualItems() {
        return itemService.createSendingItem();
    }

    @PostMapping("/newItem")
    public String GetNewItem(@RequestBody NewItem newItem){
        return itemService.createNewItem(newItem);
    }

    @PostMapping("/settingItem")
    public String settingItem(@RequestBody NewItem newItem){
        return itemService.settingItem(newItem);
    }

    @PostMapping("/deleteItem")
    public String deleteItem(@RequestBody NewItem newItem){
        return itemService.deleteItem(newItem);
    }

    @PostMapping("/setNewDate")
    public String setNewDate(@RequestBody NewDate newDate){
        return itemService.setCurrentDate(newDate.getDate());
    }

}
