package com.wallet.blasko.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "items")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    @Column(name = "actual_date")
    private LocalDate actualDate;
    private BigDecimal charging;
    private String city;
    private String comment;
    private BigDecimal crediting;
    private String place;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
