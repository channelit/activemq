package biz.cits.mq.mq.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class MqConsumer {

    @Value("${activemq.queue.name}")
    String queueName;

    @JmsListener(destination = "FIFO_QUEUE")
    public void receiveMessage(TextMessage message) {
        System.out.println("Received <" + message + ">");
    }
}
