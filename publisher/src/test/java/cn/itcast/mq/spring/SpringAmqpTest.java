package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        String queueName = "simple.queue";
        String msg = "hello, Spring AMQP!";
        rabbitTemplate.convertAndSend(queueName, msg);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String msg = "hello, Spring AMQP__";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, msg + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testFanoutExchange() {
        String exchangeName = "itcast.fanout";
        String msg = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, "", msg);
    }

    @Test
    public void testSendDirectExchange() {
        String exchangeName = "itcast.direct";
        String redMsg = "红色警报！";
        String yellowMsg = "黄色警报！";
        String blueMsg = "蓝色警报！";

        rabbitTemplate.convertAndSend(exchangeName, "red", redMsg);
        rabbitTemplate.convertAndSend(exchangeName, "blue", blueMsg);
        rabbitTemplate.convertAndSend(exchangeName, "yellow", yellowMsg);
    }

    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "itcast.topic";
        // 消息
        String message = "喜报！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }

    @Test
    public void testSendObjectQueue() {
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "柳岩");
        msg.put("age", 20);
        rabbitTemplate.convertAndSend("object.queue", msg);
    }
}
