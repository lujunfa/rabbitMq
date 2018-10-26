package org.lujunfa.studytest.rabbitmq.simple;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 发送的simple队列，simple队列是一一对应的，而且我们实际开发中，生产者发消息是毫不费力的，而消费者一般是要跟业务相关的，处理花的时间比较多
 * 
 * @Author lujunfa
 **/
public class SendMsg {
	public static final String QUEUE_NAME = "test-simple_queue";

	public static void main(String[] args)throws IOException {
		Connection connection = ConnectUtil.getRabbitMQConnection();

		// 获取到一个通道
		Channel channel = connection.createChannel();

		// 创建队列声明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		String msg = "hello simple";

		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println("send msg:" + msg);
		channel.close();
		connection.close();
	}

}
