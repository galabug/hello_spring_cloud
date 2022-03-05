package com.gala.bug.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.websocket.SendResult;

//@Component
//@RestController
public class MQProducer {
    private  static final Logger LOGGER = LoggerFactory.getLogger(MQProducer.class);

    @Value("${rocketmq.namesrvAddr}")
    private String nameservAddr;
    private final DefaultMQProducer producer = new DefaultMQProducer("TestProducer");

    @PostConstruct // 初始化
    public void  start(){
        try {
            LOGGER.info("MQ:启动生产者");
            producer.setNamesrvAddr(nameservAddr);
            producer.setSendMsgTimeout(60000);
            producer.start();
        }catch (MQClientException e){
            LOGGER.error("MQ:启动生产者失败:{}-{}",e.getResponseCode(),e.getErrorMessage());
            throw  new RuntimeException(e.getErrorMessage(),e);
        }
    }
    int count = 0;
    @RequestMapping(value = "/send4")
    public String hello(@RequestParam String msg){
        msg = msg +"-"+ count++;
        sendMessage(msg,"TOPIC_TEST",null,null);
        return "hello "+msg;
    }
    /*
     *发送消息
     */
    int key = 0;
    public void sendMessage(String data,String topic,String tags,String keys){
        try {
            byte[] messageBody = data.getBytes(RemotingHelper.DEFAULT_CHARSET);
            Message message = new Message("TopicTest","TAG",String.valueOf(key++),messageBody);

            producer.send(message, new SendCallback() {
                public void onSuccess(SendResult sendResult) {
                    LOGGER.info("MQ: 生产者发送消息{}",sendResult);
                }
                public void onSuccess(org.apache.rocketmq.client.producer.SendResult sendResult) {
                    System.out.println("MQ生产者-发送结果："+sendResult);                    //业务处理

                }
                public void onException(Throwable e) {
                    LOGGER.error(e.getMessage(),e);
                }
            });

        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
    }
    @PreDestroy
    public void stop(){
        if(producer !=null){
            producer.shutdown();
            LOGGER.info("MQ:关闭生产者");
        }
    }
}

