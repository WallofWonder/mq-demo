package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}
