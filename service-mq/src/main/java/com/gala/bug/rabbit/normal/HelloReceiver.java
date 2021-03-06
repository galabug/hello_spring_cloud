package com.gala.bug.rabbit.normal;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "sb.hello")
public class HelloReceiver {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("HelloReceiver : " + hello);
    }

}