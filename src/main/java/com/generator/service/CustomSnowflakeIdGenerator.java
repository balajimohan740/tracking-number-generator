package com.generator.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.faulttolerance.Retry;
import java.util.concurrent.locks.ReentrantLock;

@ApplicationScoped
public class CustomSnowflakeIdGenerator {

    private final long workerIdBits = 5L;
    private final long sequenceBits = 12L;

    private final long maxWorkerId = (1L << workerIdBits) - 1;
    private final long maxSequence = (1L << sequenceBits) - 1;

    private final ReentrantLock lock = new ReentrantLock();

    private long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public CustomSnowflakeIdGenerator() {
        this.workerId = 1L;
        if (workerId > maxWorkerId) {
            throw new IllegalArgumentException("Worker ID exceeds the maximum limit");
        }
    }

    @Retry(maxRetries = 3, delay = 100, jitter = 50)
    public long generateUniqueId() {
        lock.lock();
        try {
            long timestamp = System.currentTimeMillis();

            if (timestamp < lastTimestamp) {
                throw new IllegalStateException("Clock moved backwards. Refusing to generate ID.");
            }

            if (timestamp == lastTimestamp) {
                sequence = (sequence + 1) & maxSequence;
                if (sequence == 0) {
                    // Sequence overflow, wait for next millisecond
                    timestamp = tilNextMillis(lastTimestamp);
                }
            } else {
                sequence = 0L;
            }

            lastTimestamp = timestamp;

            // Generate ID: timestamp - workerId - sequence
            long epoch = 1625000000000L;
            return ((timestamp - epoch) << (workerIdBits + sequenceBits))
                    | (workerId << sequenceBits)
                    | sequence;
        } finally {
            lock.unlock();
        }
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
