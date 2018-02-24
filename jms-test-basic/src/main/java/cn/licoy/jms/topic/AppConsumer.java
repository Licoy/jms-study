package cn.licoy.jms.topic;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author licoy.cn
 * @version 2018/2/23
 */
@Slf4j
public class AppConsumer {

    private static final String url = "tcp://127.0.0.1:61616";

    private static final String topicName = "test-queue";

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
        Destination destination = session.createTopic(topicName);

        //创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        //创建监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        log.info("[接收到新消息] - "+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }else{
                    log.error("[警告] - 消息内容无法识别");
                }

            }
        });



        //关闭连接
        //connection.close();
    }

}
