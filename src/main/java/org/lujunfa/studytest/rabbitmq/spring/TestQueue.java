package org.lujunfa.studytest.rabbitmq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring_mq.xml"})

public class TestQueue{
    @Autowired
    MQProducer mqProducer;

    final String queue_key = "test_queue_key";

    @Test
    public void send(){
        Map<String,Object> msg = new HashMap();
        msg.put("data","hello,rabbmitmq!");
        mqProducer.sendDataToQueue(queue_key,msg);
    }
}