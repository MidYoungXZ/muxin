package com.muxin.demo.disruptor.demo2;

/**
 * @projectname: muxin
 * @filename: OrderWorkHandler
 * @author: yangxz
 * @data:2024/3/21 17:28
 * @description:
 */
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 消费者
 */
public class OrderWorkHandler implements WorkHandler<Order> {

    // 消费者编号
    private int id;




    public OrderWorkHandler(int id) {
        this.id = id;
    }

    @Override
    public void onEvent(Order order) throws Exception {
        System.out.println("消费者" + id + " 消费-->" + order);

    }

    public static void main(String[] args) {
        int a=5;
        for (int i = 0; i < 5; i++) {
            System.out.println(i);

        }

    }
}
