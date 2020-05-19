package demo.fc.lifecycle.service.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author chao.fang@hand-china.com
 * @date 2020-05-19 19:47
 */
@Component
public class KafkaMessageHandler {

    @KafkaListener(topics = {"test-topic"})
    public void handleKafkaMessage(ConsumerRecord record) {
        System.out.println(record.value().toString());

    }
}