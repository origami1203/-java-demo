package com.origami.demo.springkafka.consumer;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yuanmenglong
 * @version 1.0.0
 * @date 2021-08-23 16:21
 */
@Component
public class KafkaConsumer {

    @Profile("single")
    @KafkaListener(topics = "single-topic")
    public void onSingleMessage(String msg) {
        System.out.println("单线程消费消息：" + msg);
    }

    @Profile("list")
    @KafkaListener(topics = "list-topic")
    public void onListMessage(List<String> messages) {
        for (String msg : messages) {
            System.out.println("批量消费：" + msg);
        }
        System.out.println("本次消费" + messages.size());
    }

    @Profile("concurrent")
    @KafkaListener(topics = "concurrent-topic")
    public void onConcurrentMessage(List<String> messages) {
        for (String msg : messages) {
            System.out.println("多线程批量消费：" + msg);
        }
        System.out.println("本次消费" + messages.size());
        System.out.println(Thread.currentThread().getName());
    }
}
