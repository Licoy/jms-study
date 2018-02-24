package cn.licoy.jms.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author licoy.cn
 * @version 2018/2/24
 */
@Slf4j
@Component
public class ConsumerMessageListener implements MessageListener {
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage msg = (TextMessage) message;
            try {
                log.info("[QUEUE - 接收到消息] - {}",msg.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }else{
            log.info("[QUEUE - 接收到消息] - 非TextMessage对象");
        }
    }
}
