package com.pozitiftech.transactions.domain.test.api;

public interface TestService {
    Double testSetBalanceReadCommitted() throws InterruptedException;

    Double testSetBalanceRepeatableRead() throws InterruptedException;
}
