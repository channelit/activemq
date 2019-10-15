package biz.cits.mq.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MqProducer {

    private final Logger LOGGER = LoggerFactory.getLogger(MqProducer.class);

    private final JmsTemplate jmsTemplate;

    @Value("${activemq.queue.name}")
    String queueName;

    @Autowired
    public MqProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String message) {
        LOGGER.info("Sending message " + message);
        jmsTemplate.convertAndSend(queueName, message);
    }
}
