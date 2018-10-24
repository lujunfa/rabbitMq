package org.lujunfa.studytest.rabbitmq.transaction;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;

/**
 * @Author lujunfa
 **/
public class TXReceiveMsg {
	private static final String QUEUE_NAME = "transaction_queue";

	private static final String EXCHANGE_NAME = "exchange_dispatcher";

	public static void main(String[] args) throws IOException {
		Connection connection = ConnectUtil.getRabbitMQConnection();

		final Channel channel = connection.createChannel();

		// 申明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		DefaultConsumer consumer = new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope,
					com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "utf-8");
				System.out.println("sms receive msg :" + msg);
			};

		};

		channel.basicConsume(QUEUE_NAME, true, consumer);// 关闭自动应答
	}
}
