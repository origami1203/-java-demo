package com.origami.demo.springkafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author yuanmenglong
 * @version 1.0.0
 * @date 2021-08-23 15:14
 */
@RestController
@RequestMapping("/producer")
public class KafkaProducerController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/single/single")
    public void sendSingleMessage() {
        String message = "测试消息";
        kafkaTemplate.send("single-topic", message);
        System.out.println("发送消息成功：" + message);
    }

    @GetMapping("/single/list")
    public void sendListMessage() {
        for (int i = 0; i < 500000; i++) {
            kafkaTemplate.send("list-topic", "测试消息" + i);
        }
        System.out.println("发送list消息成功" );
    }

    @GetMapping("/concurrent/list")
    public void sendConcurrentMessage() {
        for (int i = 0; i < 500000; i++) {
            kafkaTemplate.send("concurrent-topic", "测试消息" + i);
        }
        System.out.println("发送list消息成功" );
    }


}
