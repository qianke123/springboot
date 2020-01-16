package com.how2java.activeMq.consumer;

import org.apache.ibatis.logging.LogFactory;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class QueueConsumer {

    private Logger logger = LoggerFactory.getLogger(QueueConsumer.class);
    /**
     * 通过监听器的消费者
     * @param textMessage 接收文本类型的消息
     * @throws JMSException
     */
    // @JmsListener(destination = "${myTopic}")
    public void receive(TextMessage textMessage) throws JMSException {
        logger.info("消费者接受到消息：" + textMessage.getText());
    }
}
