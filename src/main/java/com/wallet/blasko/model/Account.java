package com.wallet.blasko.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    private String name;
    @Column(name = "actual_balance")
    private BigDecimal actualBalance;
    private Boolean active;

}
