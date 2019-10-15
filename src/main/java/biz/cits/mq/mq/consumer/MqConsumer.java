package biz.cits.mq.mq.consumer;

import biz.cits.mq.db.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import java.util.HashMap;

@Component
public class MqConsumer {

    @Autowired
    private DataStore dataStore;

    @JmsListener(destination = "${activemq.queue.name}")
    public void receiveMessage(TextMessage message) throws JMSException {
        HashMap<String, String> records = new HashMap<>();
        String groupId = message.getStringProperty("JMSXGroupID");
        records.put(groupId, message.getText());
        dataStore.storeData(groupId, records);
        System.out.println("Received <" + message + ">");
    }
}
