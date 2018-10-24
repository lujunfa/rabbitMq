package org.lujunfa.studytest.rabbitmq.routing;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

/**
 * @Author lujunfa
 **/
public class ReceivError2 {
	private static final String EXCHANG_NAME = "route_exchange";

	private static final String QUEUE_NAME = "test_queue_route_mutibind";

	public static void main(String[] args) throws Exception {

		Connection connection = ConnectUtil.getRabbitMQConnection();

		// 申明管道
		final Channel channel = connection.createChannel();
		// 申明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 绑定到指定路由队列
		channel.queueBind(QUEUE_NAME, EXCHANG_NAME, "error");
		// 绑定到指定路由队列
		channel.queueBind(QUEUE_NAME, EXCHANG_NAME, "info");
		// 绑定到指定路由队列
		channel.queueBind(QUEUE_NAME, EXCHANG_NAME, "debug");

		// 一次只发送一个消息
		channel.basicQos(1);
		// 定义消费者z

		DefaultConsumer consumer = new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope,
					com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "utf-8");
				System.out.println("[2]receive msg :" + msg);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.out.println("[2]done");
					channel.basicAck(envelope.getDeliveryTag(), false); // 发送回执给rabbitmq说明执行消息完毕
				}
			};
		};
		channel.basicConsume(QUEUE_NAME, false, consumer);// false代表关闭自动应答
	}
}
