package demo.fc.lifecycle.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chao.fang@hand-china.com
 * @date 2021-01-22 15:44
 */
@Component
public class RocketMQConsumeMsgListenerProcessor implements MessageListenerConcurrently {

    public static final Logger LOGGER = LoggerFactory.getLogger(RocketMQConsumeMsgListenerProcessor.class);

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(list)) {
            LOGGER.info("MQ接收消息为空，直接返回成功");
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        LOGGER.info("MQ接收到的消息为：" + messageExt.toString());
        try {
            String topic = messageExt.getTopic();
            String tags = messageExt.getTags();
            String body = new String(messageExt.getBody(), "utf-8");

            LOGGER.info("MQ消息topic={}, tags={}, 消息内容={}", topic,tags,body);
        } catch (Exception e) {
            LOGGER.error("获取MQ消息内容异常{}",e);
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
