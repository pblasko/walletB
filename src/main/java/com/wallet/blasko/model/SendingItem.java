package com.wallet.blasko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SendingItem {

    private List<Account> accounts;
    private List<Category> categories;
    private List<Item> items;

}
