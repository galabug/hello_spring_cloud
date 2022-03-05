package com.gala.bug.rocketmq.main;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ConsumerClusterA {
    private  static final Logger LOGGER = LoggerFactory.getLogger(ConsumerClusterA.class);

//    public static void main(String[] args) throws InterruptedException, MQClientException {
//        startCusumerA();
//    }

    private static void startCusumerA() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumers");
        consumer.subscribe("TOPIC_TEST", "*");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);//每次从最后一次消费的地址
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                MessageExt msg = msgs.get(0);
                String logInfo = Thread.currentThread().getName()+"  :  queueID="+ msg.getQueueId()+"  {"+new String(msg.getBody())+"}  "+String.valueOf(msg);
                LOGGER.info(logInfo);
                //业务处理
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //提交成功
                //return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试（次数过多。就会变成死信  对应到消费组。）
            }
        });
        consumer.start();
        System.out.printf("ConsumerPartOrder Started.%n");
    }
}

