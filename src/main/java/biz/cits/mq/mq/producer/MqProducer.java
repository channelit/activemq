package biz.cits.mq.mq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

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

    public void sendMessage(String key, String messageStr) {
        LOGGER.info("Sending message " + messageStr);
        jmsTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                Message message = session.createTextMessage(messageStr);
                message.setStringProperty("JMSXGroupID", key);
                return message;
            }
        });
    }
}
