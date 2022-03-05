package com.gala.bug.rocketmq.main;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ProducerTest {
//    public static void main(String[] args) throws Exception{
//        sendSyncMsg();
////        sendAsyncMsg();
////        sendOnewayMsg();
//    }

    public static void sendSyncMsg() throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("productGroupSync");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setSendMsgTimeout(60000);
        producer.start();

        try {
            Message msg = new Message("TOPIC_TEST" /* Topic */,
                    "TagB" /* Tag */,
                    ("Hello sendSync SYNC:"+LocalDateTime.now().toLocalTime().toString()).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //这个点就是消息的生产的关键（生产的时候有几种方式：3，  可靠同步发送）
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n",  new String(msg.getBody()));
            System.out.printf("%s%n",  sendResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(1000);
        }finally {
            producer.shutdown();
        }
    }

    public static void sendAsyncMsg() throws Exception {
        //生产者实例化
        DefaultMQProducer producer = new DefaultMQProducer("productGroupAsync");
        producer.setNamesrvAddr("127.0.0.1:9876");//指定rocket服务器地址
        producer.setSendMsgTimeout(60000);
        producer.start();//启动实例
        //发送异步失败时的重试次数(默认值2)
        //producer.setRetryTimesWhenSendAsyncFailed(2);
        int messageCount = 20;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        try {
            for (int i = 0; i < messageCount; i++) {
                try {
                    final int index = i;
                    Message msg = new Message("TOPIC_TEST",
                            "TagC",
                            String.valueOf(Math.random()),
                            ("Hello sendAsync "+index).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    //生产者异步发送
                    producer.send(msg, new SendCallback() {
                            @Override
                            public void onSuccess(SendResult sendResult) {
                                countDownLatch.countDown();
                                System.out.printf("%-10d OK %s %n", index, new String(msg.getBody()));
                            }
                            @Override
                            public void onException(Throwable e) {
                                countDownLatch.countDown();
                                System.out.printf("%-10d Exception %s %n", index, e);
                                e.printStackTrace();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        } catch (Exception e3) {
            e3.printStackTrace();
        }finally {
            //Thread.sleep(1000);
            countDownLatch.await(100, TimeUnit.SECONDS);
            producer.shutdown();
        }
    }

    public static void sendOnewayMsg() throws Exception{
        //生产者实例化
        DefaultMQProducer producer = new DefaultMQProducer("productGroupOneway");
        producer.setNamesrvAddr("127.0.0.1:9876");//指定rocket服务器地址
        producer.setSendMsgTimeout(60000);
        producer.start();//启动实例
        try {
            //创建一个消息实例，指定topic、tag和消息体
            Message msg = new Message("TOPIC_TEST" /* Topic */,
                    "TagA" /* Tag */,String.valueOf(Math.random()),
                    ("Hello sendOneway " +
                            LocalDateTime.now().toLocalTime().toString()).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );

            producer.sendOneway(msg);//发送消息

            System.out.printf("%s%n",  new String(msg.getBody()));
        } catch (Exception e) {
            e.printStackTrace();
            Thread.sleep(1000);
        }finally {
            producer.shutdown();//生产者实例不再使用时关闭.
        }
    }
}
