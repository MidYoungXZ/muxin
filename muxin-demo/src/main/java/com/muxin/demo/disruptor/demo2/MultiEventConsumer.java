package com.muxin.demo.disruptor.demo2;

import com.lmax.disruptor.BatchEventProcessor;

/**
 * @projectname: muxin
 * @filename: Myh
 * @author: yangxz
 * @data:2024/3/26 10:24
 * @description:
 */
// 使用WorkHandler实现批量处理的示例（伪代码）
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.LifecycleAware;
import com.lmax.disruptor.RingBuffer;

import java.util.ArrayList;
import java.util.List;

public class MultiEventConsumer<T> implements EventHandler<Event<T>>, LifecycleAware {
    private final int batchSize;
    private final List<Event<T>> batch = new ArrayList<>();
    private RingBuffer<Event<T>> ringBuffer;

    public MultiEventConsumer(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public void onEvent(Event<T> event, long sequence, boolean endOfBatch) throws Exception {
        batch.add(event);
        if (batch.size() >= batchSize || endOfBatch) {
            processBatch();
        }
    }

    private void processBatch() {
        // Process batch of events
        for (Event<T> event : batch) {
            // Process event here
            System.out.println("Processing event: " + event.getData());
        }
        // Clear batch after processing
        batch.clear();
    }

    @Override
    public void onStart() {
        // Initialize resources if needed
    }

    @Override
    public void onShutdown() {
        // Release any acquired resources
    }

    public void setRingBuffer(RingBuffer<Event<T>> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    // Method to start consuming events
    public void startConsuming() {
        if (ringBuffer == null) {
            throw new IllegalStateException("RingBuffer has not been set");
        }
//        ringBuffer.addGatingSequences(ringBuffer.getCursor(1L));
    }
}
