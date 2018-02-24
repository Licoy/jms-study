package cn.licoy.jms.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author licoy.cn
 * @version 2018/2/23
 */
@Slf4j
public class AppProducer {

    private static final String url = "tcp://127.0.0.1:61616";

    private static final String queueName = "test-queue";

    public static void main(String[] args) throws JMSException {
        //创建connectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(url);

        //创建连接
        Connection connection = factory.createConnection();

        //启动连接
        connection.start();

        //创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //创建目标
        Destination destination = session.createQueue(queueName);

        //创建生产者
        MessageProducer producer = session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            String msg = "message-"+i;
            //创建消息体
            TextMessage message = session.createTextMessage(msg);
            //发布消息
            producer.send(message);
            //打印发送日志
            log.info("[消息发送成功] - "+msg);
        }

        //关闭连接
        connection.close();

    }
}
