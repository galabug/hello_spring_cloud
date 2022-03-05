package com.gala.bug.rabbit.normal;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
public class DefaultSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    private int count = 0;

    @RequestMapping("/send")
    public String send(@RequestParam String msg) {
        String sendMsg = msg +"-"+ count++;
        System.out.println("Sender : " + sendMsg);
        //TODO 普通消息处理
        //this.rabbitTemplate.convertAndSend(RmConst.QUEUE_HELLO, sendMsg);
        //TODO 消息处理--(消费者处理时，有手动应答)
        this.rabbitTemplate.convertAndSend("sb.hello", sendMsg);
//        this.rabbitTemplate.convertAndSend("sb.user", sendMsg);
        return sendMsg;
    }

}
