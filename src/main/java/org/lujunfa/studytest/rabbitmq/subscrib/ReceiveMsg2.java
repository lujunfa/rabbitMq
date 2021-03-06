package org.lujunfa.studytest.rabbitmq.subscrib;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

/**
 * @Author lujunfa
 **/
public class ReceiveMsg2 {
	private static final String QUEUE_NAME = "public_subscribe_email";

	private static final String EXCHANGE_NAME = "exchange_dispatcher";

	public static void main(String[] args) throws IOException {
		Connection connection = ConnectUtil.getRabbitMQConnection();

		final Channel channel = connection.createChannel();

		// 申明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 绑定队列到指定交换机
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

		channel.basicQos(1);

		DefaultConsumer consumer = new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope,
					com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "utf-8");
				System.out.println("email receive msg :" + msg);
				try {
					Thread.sleep(1000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.out.println("done");
					//确认接收到
					channel.basicAck(envelope.getDeliveryTag(), false);
				}

			};

		};

		boolean autoACK = false;
		//启动本地消费
		channel.basicConsume(QUEUE_NAME, autoACK, consumer);
	}
}
