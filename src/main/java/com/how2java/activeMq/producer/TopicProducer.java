package com.how2java.activeMq.producer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class TopicProducer {

    @Autowired
    private Topic topic;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    private Logger logger = Logger.getLogger(TopicProducer.class);

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(topic, "hello topic springboot-activeMq");
        logger.info("topic 消息发送成功");
    }
}
