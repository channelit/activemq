package biz.cits.mq.mq;


import biz.cits.mq.message.MsgGenerator;
import biz.cits.mq.mq.producer.MqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("mq")
public class MqController {

    private final MqProducer mqProducer;

    @Autowired
    public MqController(MqProducer mqProducer) {
        this.mqProducer = mqProducer;
    }

    @GetMapping(path = "send", produces = "application/json")
    public String sendMessages(@RequestParam int numMessage) {
        ArrayList<Map.Entry<String, String>> messages = MsgGenerator.getMessages(numMessage);
        messages.forEach((e) -> mqProducer.sendMessage(e.getKey(), e.getValue()));
        return "done";
    }
}
