package com.arthur;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicConsumer {

    public static void main(String[] args) throws JMSException, IOException {
        String brokerURL = "tcp://localhost:61616";
        ConnectionFactory connFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection conn = connFactory.createConnection();
        conn.start();
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("topic test");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                try {
                    String text = tm.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        conn.close();
        session.close();
        consumer.close();
    }
}
