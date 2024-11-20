package com.pozitiftech.transactions.domain.wallet.api;

public interface WalletService {
    void addBalanceReadCommitted(Long id, Double balance);

    Double getBalanceReadCommited(Long id);

    void addBalanceRepeatableRead(Long id, Double balance);

    Double getBalanceRepeatableRead(Long id);
}
