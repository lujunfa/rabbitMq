package org.lujunfa.studytest.rabbitmq.util;
/**
 * 获取mq的链接
*@Author lujunfa
**/

import java.io.IOException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectUtil {

		public static Connection getRabbitMQConnection() throws IOException {
			//定义一个链接工厂
			ConnectionFactory factory = new ConnectionFactory();
			//设置服务地址
			factory.setHost("127.0.0.1");
			
			//AMQP 5672
			
			factory.setPort(5672);
			
			//viturlhost
			factory.setVirtualHost("/vhost_mmr");
			
			//用户名+密码
			factory.setUsername("lujunfa");
			factory.setPassword("ljf895359");
			
			Connection  connection = factory.newConnection();
			
			
			return connection;
		}
}
