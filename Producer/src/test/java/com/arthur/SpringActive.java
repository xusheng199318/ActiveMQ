package com.arthur;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by xusheng on 2019/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-activemq.xml")
public class SpringActive {

    @Resource
    private JmsTemplate jmsTemplate;

    @Resource(name = "test-queue")
    private Destination destination;

    @Test
    public void testHelloWorld() {
        System.out.println("HelloWorld");
    }

    @Test
    public void testSpringProducer() {
        jmsTemplate.send(destination, session -> {
            String msg = "Hello Spring World";
            return session.createTextMessage(msg);
        });
    }


}
