package com.arthur;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueConsumer {

    public static void main(String[] args) throws Exception {
        String brokerURL = "tcp://localhost:61616";
        ConnectionFactory connFactory = new ActiveMQConnectionFactory(brokerURL);
        Connection conn = connFactory.createConnection();
        conn.start();
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("queue test");
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                String text = null;
                try {
                    text = msg.getText();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
                System.out.println(text);
            }
        });

        System.in.read();
        consumer.close();
        session.close();
        conn.close();
    }
}
