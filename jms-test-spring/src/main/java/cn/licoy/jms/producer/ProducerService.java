package cn.licoy.jms.producer;

/**
 * @author licoy.cn
 * @version 2018/2/24
 */
public interface ProducerService {

    void sendMessage(String msg);

    void sendMessageOfTopic(String msg);
}
