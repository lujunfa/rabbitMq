package org.lujunfa.studytest.rabbitmq.Pull_Push;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.util.concurrent.TimeUnit;


/**
 * 我们知道，消费者有两种方式从消息中间件获取消息：
 * 推模式：消息中间件主动将消息推送给消费者
 * 拉模式：消费者主动从消息中间件拉取消息
 * 推模式将消息提前推送给消费者，消费者必须设置一个缓冲区缓存这些消息。好处很明显，消费者总是有一堆在内存中待处理的消息，所以效率高。缺点是缓冲区可能会溢出。
 * 拉模式在消费者需要时才去消息中间件拉取消息，这段网络开销会明显增加消息延迟，降低系统吞吐量。
 * 选择推模式还是拉模式需要考虑使用场景。
 *
 * 前面 HelloWorld 例子中使用 Consumer 接口的方式是推模式。RabbitMQ 会把队列中的消息全部推送给消费者。如果删除 Sender 的 sleep 代码行，只要
 * Receiver 连着 hello 队列，你就会发现 hello 队列根本存不住消息，全都发送给了 Receiver，哪怕 Receiver 处理的再慢。
 */

public class ReceiverByGet {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        while (true) {
            GetResponse resp = channel.basicGet(QUEUE_NAME, true);
            if (resp == null) {
                System.out.println("Get Nothing!");
                TimeUnit.MILLISECONDS.sleep(1000);
            } else {
                String message = new String(resp.getBody(), "UTF-8");
                System.out.printf(" [    %2$s<===](%1$s) %3$s\n", "Receiver", QUEUE_NAME, message);
                TimeUnit.MILLISECONDS.sleep(500);
            }
        }
    }

    /**
     * 代码第14行通过 basicGet 方法，主动去第一个参数指定的队列（hello队列）尝试获取一个消息，这是一个非阻塞方法，当从队列中获取消息失败时，会返回 null，成功则返回 GetResponse 实例。第二个参数 autoAck 指定消息确认模式，作用同前文 basicConsume 方法同名参数。
     *
     * 代码第15行判断返回为 null 的场景。当返回为 null 时一般是因为队列中无可用消息，所以需要让自己 sleep 一小段时间，以防止过于频繁地尝试获取消息。
     */
}