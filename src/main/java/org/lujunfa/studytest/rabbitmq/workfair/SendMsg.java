package org.lujunfa.studytest.rabbitmq.workfair;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
*@Author lujunfa
**/
public class SendMsg {
		private static final String QUEUE_NAME = "test_workfair_queue";
		public static void main(String[] args) throws IOException, InterruptedException {
			Connection connection = ConnectUtil.getRabbitMQConnection();
			
			Channel channel = connection.createChannel();
			
			//申明队列
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			
			//每个消费者发送确认消息之前只发一个消息，直到得到消费者消费情况的回执才发送下一个
			int perfectchCount = 1;
			channel.basicQos(perfectchCount);
			
			for(int i=0; i <50;i++) {
				String msg = "hell0" + i;
				channel.basicPublish("", QUEUE_NAME,null, msg.getBytes() );
				Thread.sleep(i*20);
			}
			
			channel.close();
			connection.close();
		}
}
