package cn.licoy.jms;

import cn.licoy.jms.producer.ProducerService;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * @author licoy.cn
 * @version 2018/2/24
 */
public class AppProducer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");

        ProducerService producerService = context.getBean(ProducerService.class);

        for (int i = 0; i < 100; i++) {
            producerService.sendMessage("test-msg-id-"+i);
        }

        /*for (int i = 0; i < 100; i++) {
            producerService.sendMessageOfTopic("test-msg-id-"+i);
        }*/

        context.close();


    }
}
