package com.pozitiftech.transactions.domain.wallet.impl;

import com.pozitiftech.transactions.domain.wallet.api.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addBalanceReadCommitted(Long id, Double balance) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        log.info("addBalanceReadCommitted id: {}, balance: {}, wallet: {}", id, balance, wallet.getBalance());
        wallet.setBalance(wallet.getBalance() + balance);
        walletRepository.save(wallet);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    @Retryable(retryFor = PSQLException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    @Override
    public void addBalanceRepeatableRead(Long id, Double balance) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        log.info("addBalanceRepeatableRead id: {}, balance: {}, wallet: {}", id, balance, wallet.getBalance());

        wallet.setBalance(wallet.getBalance() + balance);
        walletRepository.save(wallet);
    }

    @Transactional(readOnly = true)
    @Override
    public Double getBalanceReadCommited(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        return wallet.getBalance();
    }

    @Transactional(readOnly = true)
    @Override
    public Double getBalanceRepeatableRead(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        return wallet.getBalance();
    }

}
