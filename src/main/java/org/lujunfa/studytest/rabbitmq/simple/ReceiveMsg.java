package org.lujunfa.studytest.rabbitmq.simple;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;
import com.rabbitmq.client.ShutdownSignalException;

/**
 * 消费消息
 * 
 * @Author lujunfa
 **/
public class ReceiveMsg {
	public static final String QUEUE_NAME = "test-simple_queue";

	public static void main(String[] args) throws ShutdownSignalException, ConsumerCancelledException, IOException, InterruptedException {

			//oldRabbitMqAPI();
			newRabbitMqAPI();
	}
	//旧api接受消息
	public static void oldRabbitMqAPI() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		Connection connection = ConnectUtil.getRabbitMQConnection();

		// 获取到一个通道
		Channel channel = connection.createChannel();
		// 创建队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null); //durable参数为true时代表该队列的消息可持久化，在队列服务器宕机可恢复

		// 定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);

		// 监听队列

		channel.basicConsume(QUEUE_NAME, true, consumer);
		while (true) {
			Delivery deliver = consumer.nextDelivery();
			String msg = new String(deliver.getBody());
			System.out.println("old API received msg：" + msg);

		}
	}
	//新api接受消息
	public static void newRabbitMqAPI() throws IOException {
		Connection connection = ConnectUtil.getRabbitMQConnection();

		// 获取到一个通道
		Channel channel = connection.createChannel();

		// 创建队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//定义一个消费者
		DefaultConsumer defaultConsumer =  new DefaultConsumer(channel) {
			public void handleDelivery(String consumerTag, com.rabbitmq.client.Envelope envelope,
					com.rabbitmq.client.AMQP.BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body);
				System.out.println("new API receive msg:" + msg);
			}

		};
		
		channel.basicConsume(QUEUE_NAME, true, defaultConsumer);
	}
}
