package org.lujunfa.studytest.rabbitmq.confirm;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
*@Author lujunfa
*confirm 普通模式
**/
public class Send1 {
			private final static String  QUEUE_NAME = "queue_confirm1";
			public static void main(String[] args) throws IOException {
				
				Connection connection = ConnectUtil.getRabbitMQConnection();
				
				Channel channel = connection.createChannel();
				
				channel.queueDeclare(QUEUE_NAME, false, false, false, null);
				
				//将channel设置成confirm模式，如果该队列已开启事物，那么该操作将导致异常
				channel.confirmSelect();
				
				String msg = "hello transation queue";
				
				channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
				
				/*if(!channel.waitForConfirms()) {
					System.out.println("发送失败");
				}else {
					System.out.println("发送成功");
				}*/
			}
}
