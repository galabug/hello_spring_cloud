package com.gala.bug.rabbit.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class TopicSender {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    private int count = 0;

    @RequestMapping("/send2")
    public synchronized String send(@RequestParam String msg) {
        msg = msg +"-"+ count++;
        String msg1 = "I am email mesaage "+msg+"======";
        System.out.println("TopicSender send the 1st : " + msg1);
        this.rabbitTemplate.convertAndSend("sb.exchange", "sb.info.email", msg1);

        String msg2 = "I am user mesaages "+msg+"########";
        System.out.println("TopicSender send the 2nd : " + msg2);
        this.rabbitTemplate.convertAndSend("sb.exchange", "sb.info.user", msg2);

        String msg3 = "I am error mesaages "+msg;
        System.out.println("TopicSender send the 3rd : " + msg3);
        this.rabbitTemplate.convertAndSend("sb.exchange", "errorkey", msg3);
        return msg1+msg2+msg3;
    }

}
