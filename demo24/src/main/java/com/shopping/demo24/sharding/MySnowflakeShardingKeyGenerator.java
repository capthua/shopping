package com.shopping.demo24.sharding;//package com.shopping.demo.config.db.sharding;
//
//import com.google.common.base.Preconditions;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.SneakyThrows;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shardingsphere.core.strategy.keygen.TimeService;
//import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;
//
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//public final class MySnowflakeShardingKeyGenerator implements ShardingKeyGenerator {
//
//    StringBuilder sequenceSb=new StringBuilder();
//
//    public static final long EPOCH;
//
//    private static final long SEQUENCE_BITS = 12L;
//
//    private static final long WORKER_ID_BITS = 10L;
//
//    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;
//
//    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;
//
//    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;
//
//    private static final long WORKER_ID_MAX_VALUE = 1L << WORKER_ID_BITS;
//
//    private static final long WORKER_ID = 0;
//
//    private static final int DEFAULT_VIBRATION_VALUE = 1;
//
//    private static final int MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS = 10;
//
//    private static final Random RANDOM = new Random();
//
//    private static final int RANDOM_BOUND=16;
//
//    @Setter
//    private static TimeService timeService = new TimeService();
//
//    @Getter
//    @Setter
//    private Properties properties = new Properties();
//
//    private int sequenceOffset = -1;
//
//    private long sequence;
//
//    private long lastMilliseconds;
//
//    static {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2016, Calendar.NOVEMBER, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        EPOCH = calendar.getTimeInMillis();
//    }
//
//    @Override
//    public String getType() {
//        return "MY_SNOWFLAKE";
//    }
//
//    @Override
//    public synchronized Comparable<?> generateKey() {
//        long currentMilliseconds = timeService.getCurrentMillis();
//        if (waitTolerateTimeDifferenceIfNeed(currentMilliseconds)) {
//            currentMilliseconds = timeService.getCurrentMillis();
//        }
//        if (lastMilliseconds == currentMilliseconds) {
//            if (0L == (sequence = (sequence + 1) & SEQUENCE_MASK)) {
//                sequence=RANDOM.nextInt(RANDOM_BOUND);
//                currentMilliseconds = waitUntilNextTime(currentMilliseconds);
//            }
//        } else {
//            vibrateSequenceOffset();
//            sequence = sequenceOffset;
//            if(sequence==0){
//                sequence=RANDOM.nextInt(RANDOM_BOUND);
//            }
//        }
//        sequenceSb.append(sequence).append(",");
//        log.info(sequenceSb.toString());
//        try {
//            TimeUnit.MILLISECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        lastMilliseconds = currentMilliseconds;
//        return ((currentMilliseconds - EPOCH) << TIMESTAMP_LEFT_SHIFT_BITS) | (getWorkerId() << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
//    }
//
//
//    @SneakyThrows
//    private boolean waitTolerateTimeDifferenceIfNeed(final long currentMilliseconds) {
//        if (lastMilliseconds <= currentMilliseconds) {
//            return false;
//        }
//        long timeDifferenceMilliseconds = lastMilliseconds - currentMilliseconds;
//        Preconditions.checkState(timeDifferenceMilliseconds < getMaxTolerateTimeDifferenceMilliseconds(),
//                "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastMilliseconds, currentMilliseconds);
//        Thread.sleep(timeDifferenceMilliseconds);
//        return true;
//    }
//
//    private long getWorkerId() {
//        long result = Long.parseLong(properties.getProperty("worker.id", String.valueOf(WORKER_ID)));
//        Preconditions.checkArgument(result >= 0L && result < WORKER_ID_MAX_VALUE);
//        return result;
//    }
//
//    private int getMaxVibrationOffset() {
//        int result = Integer.parseInt(properties.getProperty("max.vibration.offset", String.valueOf(DEFAULT_VIBRATION_VALUE)));
//        Preconditions.checkArgument(result >= 0 && result <= SEQUENCE_MASK, "Illegal max vibration offset");
//        return result;
//    }
//
//    private int getMaxTolerateTimeDifferenceMilliseconds() {
//        return Integer.parseInt(properties.getProperty("max.tolerate.time.difference.milliseconds", String.valueOf(MAX_TOLERATE_TIME_DIFFERENCE_MILLISECONDS)));
//    }
//
//    private long waitUntilNextTime(final long lastTime) {
//        long result = timeService.getCurrentMillis();
//        while (result <= lastTime) {
//            result = timeService.getCurrentMillis();
//        }
//        return result;
//    }
//
//    private void vibrateSequenceOffset() {
//        sequenceOffset = sequenceOffset >= getMaxVibrationOffset() ? 0 : sequenceOffset + 1;
//    }
//}
