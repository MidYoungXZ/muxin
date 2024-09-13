package com.muxin.demo.disruptor.demo1;

/**
 * @projectname: muxin
 * @filename: StringEventHandler
 * @author: yangxz
 * @data:2024/3/21 17:18
 * @description:
 */
import com.lmax.disruptor.EventHandler;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 消费者
 */
public class StringEventHandler implements EventHandler<StringEvent> {


    private List<StringEvent> events =new ArrayList<>(10);

    /**
     * 消费数据的方法
     *
     * @param stringEvent 被消费的数据
     * @param sqeueuence 消费序号 从0 到 2的64次幂-1（long类型的大小）
     * @param endOfBatch 当前是否已经消费到尽头了(此刻没有下一个需要消费的数据)
     */
    @Override
    public void onEvent(StringEvent stringEvent, long sqeueuence, boolean endOfBatch) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        events.add(stringEvent);
        if (events.size() >= 10 || endOfBatch) {
            System.out.println(
                    "消费 -->" +"events:"+events.size());
            events.clear();
        }
    }
}
