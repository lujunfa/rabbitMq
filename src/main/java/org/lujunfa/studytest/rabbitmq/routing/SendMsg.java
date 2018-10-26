package org.lujunfa.studytest.rabbitmq.routing;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 路由模式 , 生产者生产消息时，会将routekey放到消息体里面，然后交换器根据routekey分发消息给对应的队列，最后订阅了该队列的消费者消费消息
*@Author lujunfa
**/
public class SendMsg {
		private static final String EXCHANG_NAME = "route_exchange";
		
		public static void main(String[] args) throws IOException {
			Connection connection = ConnectUtil.getRabbitMQConnection();
			
			//申明管道
			Channel channel = connection.createChannel();
			//申明交换器
			/**
			 * 1、消息分发规则：消息会被推送至绑定键（binding key）和消息发布附带的选择键（routing key）完全匹配的队列。 
			 * 2、图示说明：消息1附带路由键“error”、与绑定键“error”匹配，而队列Q4、Q5与交换机X间都存在绑定键“error”所以消息1被分发到Q4、Q5；消息2附带路由键“info”,而队列Q4与交换机间存在绑定建“info”,所以消息2被分发到队列Q4。
			 *
			 * 3、分发到队列的消息不再带有绑定键，事实上分发到队列的消息不再带有发送者的任何信息，当然如果消息实体里面包含了发送者消息，那么消费者可以获取发送者信息。
			 */
			channel.exchangeDeclare(EXCHANG_NAME, "direct");
			
			String msg = "hello , i am debug";
			
			String routeKey = "error";
			//发送消息到指定路由的分发器中，然后由分发器根据对应的规则进行分发到指定的队列中
			channel.basicPublish(EXCHANG_NAME, routeKey, null, msg.getBytes());
			System.out.println("send msg:"+msg);
			channel.close();
			connection.close();
		}
}
