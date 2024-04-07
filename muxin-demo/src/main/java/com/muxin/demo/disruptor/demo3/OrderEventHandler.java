package com.muxin.demo.disruptor.demo3;

/**
 * @projectname: muxin
 * @filename: OrderEventHandler
 * @author: yangxz
 * @data:2024/3/21 17:30
 * @description:
 */
import com.lmax.disruptor.EventHandler;
import com.muxin.demo.disruptor.demo2.Order;

/**
 * 消费者
 */
public class OrderEventHandler implements EventHandler<Order> {

    // 消费者编号
    private int id;

    public OrderEventHandler(int id) {
        this.id = id;
    }

    @Override
    public void onEvent(Order order, long sqeueuence, boolean endOfBatch) throws Exception {
        System.out.println(
                "消费者" + id + "消费 -->" + order.getId() + " sqeueuence-->" + +sqeueuence
                        + "  endOfBatch-->"
                        + endOfBatch);
    }
}
