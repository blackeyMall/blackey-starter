package com.blackey.common.mdc;

import java.util.concurrent.atomic.AtomicLong;

public final class TransactionId extends AtomicLong {

    private static final TransactionId INSTANCE = new TransactionId();
    private static final long serialVersionUID = -3581601740123663611L;

    private TransactionId() {
        super(0);
    }

    public static String next() {
        return Long.toString(INSTANCE.incrementAndGet());
    }
}
