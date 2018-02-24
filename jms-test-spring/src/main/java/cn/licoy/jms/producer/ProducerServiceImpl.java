package cn.licoy.jms.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @author licoy.cn
 * @version 2018/2/24
 */
@Service(value = "producerService")
@Slf4j
public class ProducerServiceImpl implements ProducerService{

    @Resource
    private JmsTemplate jmsTemplate;
    @Resource(name = "queueDestination")
    private Destination queueDestination;
    @Resource(name = "topicDestination")
    private Destination topicDestination;


    public void sendMessage(final String msg) {
        //使用jmsTemplate发送Message
        jmsTemplate.send(queueDestination,new MessageCreator() { //创建一个消息
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(msg);
                log.info("[QUEUE - 消息发送成功] - {}",textMessage.getText());
                return textMessage;
            }
        });
    }

    public void sendMessageOfTopic(final String msg) {
        //使用jmsTemplate发送Message
        jmsTemplate.send(topicDestination,new MessageCreator() { //创建一个消息
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(msg);
                log.info("[TOPIC - 消息发送成功] - {}",textMessage.getText());
                return textMessage;
            }
        });
    }
}
