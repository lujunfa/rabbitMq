package org.lujunfa.studytest.rabbitmq.subscrib;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 消息订阅模式:一个消息生产者发送消息到一个交换机exchange中，该exchange绑定多个队列queue，每个queue绑定对应的队列，从而达到一个消息被多个消费者消费
 * 
 * @Author lujunfa
 **/
public class SendSmg {

	private static final String EXCHANGE_NAME = "exchange_dispatcher"; // 交换器名称

	public static void main(String[] args) throws IOException {
		Connection connection = ConnectUtil.getRabbitMQConnection();

		// 创建管道
		Channel channel = connection.createChannel(); //

		// 声明交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

		String msg = "hello exchange";

		channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());

		System.out.println("send:" + msg);

		channel.close();
		connection.close();
	}
}
