package com.fincodehub.jpa.util;


/**
 * @author YangJian
 * @version 1.0.0
 * @title SnowflakeIdGenerator
 * @create 2025/5/12 8:31
 * @description <TODO description class purpose>
 */
public class SnowflakeIdGenerator {
    // 起始时间戳（2023-01-01）
    private final static long START_TIMESTAMP = 1672531200000L;

    // 机器ID占用的位数
    private final static long WORKER_ID_BITS = 10L;

    // 序列号占用的位数
    private final static long SEQUENCE_BITS = 12L;

    // 最大机器ID（1023）
    private final static long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);

    // 最大序列号（4095）
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);

    // 机器ID左移位数
    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;

    // 时间戳左移位数
    private final static long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;

    private long workerId; // 机器ID
    private long sequence = 0L; // 序列号
    private long lastTimestamp = -1L; // 上次生成ID的时间戳

    public SnowflakeIdGenerator(long workerId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException("Worker ID 必须在 0 到 " + MAX_WORKER_ID + " 之间");
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("时钟回拨，拒绝生成 ID");
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // 当前毫秒的序列号用尽，等待下一毫秒
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - START_TIMESTAMP) << TIMESTAMP_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

    private long waitNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
