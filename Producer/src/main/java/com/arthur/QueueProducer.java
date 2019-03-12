package com.arthur;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class QueueProducer {

    public static void main(String[] args) throws JMSException {
        Connection conn = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            String brokerURL = "tcp://localhost:61616";
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            conn= connectionFactory.createConnection();
            conn.start();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("queue test");
            producer = session.createProducer(queue);
            TextMessage msg = session.createTextMessage("Hello World");
            producer.send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            producer.close();
            session.close();
            conn.close();
        }
    }
}
