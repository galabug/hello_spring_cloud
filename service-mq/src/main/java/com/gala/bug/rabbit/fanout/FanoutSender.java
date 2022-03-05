package com.gala.bug.rabbit.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class FanoutSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private int count = 0;

    @RequestMapping("/send3")
    public String send(@RequestParam String msg) {
        String sendMsg = msg +"-"+ count++;
        System.out.println("FanoutSender : " + sendMsg);
        this.rabbitTemplate.convertAndSend("sb.fanout.exchange", "",sendMsg);
        return sendMsg;
    }

}