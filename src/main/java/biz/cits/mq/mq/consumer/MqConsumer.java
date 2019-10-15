package biz.cits.mq.mq.consumer;

import biz.cits.mq.db.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.HashMap;

@Component
public class MqConsumer {

    @Value("${activemq.queue.name}")
    String queueName;

    @Autowired
    private DataStore dataStore;


    @JmsListener(destination = "FIFO_QUEUE")
    public void receiveMessage(TextMessage message) throws JMSException {
        HashMap<String, String> records = new HashMap<>();
        records.put("test", message.getText());
        dataStore.storeData("test", records);
        System.out.println("Received <" + message + ">");
    }
}
