package cn.itcast.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueueMsg(String msg) throws InterruptedException{
//        System.out.println("spring 消费者接收到消息：【" + msg + "】");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueueMsg1(String msg) throws InterruptedException{
        System.out.println("spring 消费者1接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueueMsg2(String msg) throws InterruptedException{
        System.err.println("spring 消费者2接收到消息：【" + msg + "】" + LocalTime.now());
        Thread.sleep(200);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        System.out.println("spring 消费者接收到fanout.queue1消息：【" + msg + "】");
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {
        System.out.println("spring 消费者接收到fanout.queue2消息：【" + msg + "】");
    }
}
