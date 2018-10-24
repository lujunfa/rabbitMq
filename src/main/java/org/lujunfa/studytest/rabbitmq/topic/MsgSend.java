package org.lujunfa.studytest.rabbitmq.topic;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * topic模式，相对于route模式，前者可根据routekey模糊匹配分发消息
*@Author lujunfa
**/
public class MsgSend {
		private static final String EXCHANGE_NAME = "test_exchange_topic";

		public static void main(String[] args) throws IOException {
			Connection connection = ConnectUtil.getRabbitMQConnection();

			// 获取到一个通道
			Channel channel = connection.createChannel();

			//exchange
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");
			
			String msg_String = "商品.....";
			
			channel.basicPublish(EXCHANGE_NAME, "goods.update", null, msg_String.getBytes());
			
			System.out.println("send msg:"+msg_String);
			
			channel.close();
			connection.close();
		}
}
