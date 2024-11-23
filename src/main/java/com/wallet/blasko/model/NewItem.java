package com.wallet.blasko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewItem {

    private Long id;
    private String date;
    private String accountId;
    private String place;
    private String city;
    private String categoryId;
    private BigDecimal number;
    private String comment;

}
