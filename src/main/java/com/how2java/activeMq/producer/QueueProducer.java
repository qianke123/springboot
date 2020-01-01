package com.how2java.activeMq.producer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class QueueProducer {
    private static int id;

    private Logger logger = Logger.getLogger(QueueProducer.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "hello springBoot-activeMq");
    }

    /**
     * 定时投递消息
     */
    // @Scheduled(fixedDelay = 10000)
    public void produceMsgScheduled() {
        jmsMessagingTemplate.convertAndSend(queue, "Scheduled " + (id++) + " hello springBoot-activeMq");
        logger.info("定时投递第" + id + "个消息成功");
    }
}
