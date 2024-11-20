package com.pozitiftech.transactions.domain.wallet.impl;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = Wallet.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {
    public static final String TABLE = "wallet";
    private static final String COL_BALANCE = "balance";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = COL_BALANCE)
    private Double balance;
}
