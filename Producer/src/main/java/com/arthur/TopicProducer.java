package com.arthur;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {

    public static void main(String[] args) throws JMSException {
        Connection conn = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            String brokerURL = "tcp://localhost:61616";
            ConnectionFactory connFactory = new ActiveMQConnectionFactory(brokerURL);
            conn = connFactory.createConnection();
            conn.start();
            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("topic test");
            producer = session.createProducer(topic);
            TextMessage msg = session.createTextMessage("Hello Topic Test");
            producer.send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            conn.close();
            session.close();
            producer.close();
        }
    }
}
