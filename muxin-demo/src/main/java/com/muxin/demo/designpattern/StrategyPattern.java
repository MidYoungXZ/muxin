package com.muxin.demo.designpattern;

import java.util.Arrays;
import java.util.List;

/**
 * @Projectname: muxin
 * @Filename: StrategyPattern
 * @Author: yangxz
 * @Data:2022/12/29 15:33
 * @Description: TODO
 */
public class StrategyPattern {


    public static void main(String[] args) {


        List<MessageNotifier> list = Arrays.asList(new SMSMessageNotifier(), new AppMessageNotifier());
        MessageStrategy messageStrategy = new MessageStrategy(list);
        messageStrategy.notifyMessage("张三","消息内容",1);
//        if (!"1".equals("1")){
//            return;
//        }
//        if (!"2".equals("2")){
//            return;
//        }
//        ArrayList<Integer> objects = new ArrayList<>();
//        for (Integer object : objects) {
//            if (object == 1){
//                System.out.println("1");
//            }
//        }
//
//
//        if ("1".equals("1")){
//            if ("2".equals("2")){
//                ArrayList<Integer> objects2 = new ArrayList<>();
//                for (Integer object : objects2) {
//                    if (object == 1){
//                        System.out.println("1");
//                    }
//                }
//            }
//        }


    }

    public static class MessageStrategy {
       private  List<MessageNotifier> messageNotifiers;

        public void notifyMessage(String user, String content, int notifyType) {
            for (MessageNotifier messageNotifier : messageNotifiers) {
                if (messageNotifier.support(notifyType)) {
                    messageNotifier.notify(user, content);
                }
            }
        }

        public MessageStrategy(List<MessageNotifier> messageNotifiers) {
            this.messageNotifiers = messageNotifiers;
        }
    }




    public static class AppMessageNotifier implements MessageNotifier {
        @Override
        public boolean support(int notifyType) {
            return notifyType == 1;
        }

        @Override
        public void notify(String user, String content) {
            //调用通知app通知的api
        }
    }


    public static class SMSMessageNotifier implements MessageNotifier {
        @Override
        public boolean support(int notifyType) {
            return notifyType == 0;
        }

        @Override
        public void notify(String user, String content) {
            //调用短信通知的api发送短信
        }
    }

    public interface MessageNotifier {

        /**
         * 是否支持改类型的通知的方式
         *
         * @param notifyType 0:短信 1:app
         * @return
         */
        boolean support(int notifyType);

        /**
         * 通知
         *
         * @param user
         * @param content
         */
        void notify(String user, String content);

    }

}
