package org.lujunfa.studytest.rabbitmq.routing;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 路由模式 , 交换器根据routekey分发消息给对应的队列，然后传送给消费者消费
*@Author lujunfa
**/
public class SendMsg {
		private static final String EXCHANG_NAME = "route_exchange";
		
		public static void main(String[] args) throws IOException {
			Connection connection = ConnectUtil.getRabbitMQConnection();
			
			//申明管道
			Channel channel = connection.createChannel();
			//申明交换器
			channel.exchangeDeclare(EXCHANG_NAME, "direct");
			
			String msg = "hello , i am debug";
			
			String routeKey = "debug";
			//发送消息到指定路由的队列中去
			channel.basicPublish(EXCHANG_NAME, routeKey, null, msg.getBytes());
			System.out.println("send msg:"+msg);
			channel.close();
			connection.close();
		}
}
