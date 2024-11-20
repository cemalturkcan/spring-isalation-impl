package com.pozitiftech.transactions.domain.test.impl;

import com.pozitiftech.transactions.domain.test.api.TestService;
import com.pozitiftech.transactions.domain.wallet.api.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestServiceImpl implements TestService {
    private final WalletService walletService;

    @Override
    public Double testSetBalanceReadCommitted() throws InterruptedException {
        Thread thread1 = new Thread(() -> walletService.addBalanceReadCommitted(1L, 200.0));
        Thread thread2 = new Thread(() -> walletService.addBalanceReadCommitted(1L, 500.0));
        makeAsyncCalls(
                thread1,
                thread2
        );
        return walletService.getBalanceReadCommited(1L);

    }

    @Override
    public Double testSetBalanceRepeatableRead() throws InterruptedException {
        Thread thread1 = new Thread(() -> walletService.addBalanceRepeatableRead(1L, 200.0));
        Thread thread2 = new Thread(() -> walletService.addBalanceRepeatableRead(1L, 500.0));
        makeAsyncCalls(
                thread1,
                thread2
        );
        return walletService.getBalanceRepeatableRead(1L);
    }

    private void makeAsyncCalls(
            Thread thread1,
            Thread thread2
    ) throws InterruptedException {
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

}
