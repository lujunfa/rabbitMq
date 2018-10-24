package org.lujunfa.studytest.rabbitmq.transaction;

import java.io.IOException;

import org.lujunfa.studytest.rabbitmq.util.ConnectUtil;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 消息事物机制
 * 
 * @Author lujunfa
 **/
public class TXSendSmg {

	private static final String QUEUE_NAME = "transaction_queue"; // 交换器名称

	public static void main(String[] args) throws IOException {
		Connection connection = ConnectUtil.getRabbitMQConnection();

		// 创建管道
		Channel channel = connection.createChannel(); //

		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);

		String msg = "hello transation queue";
		try {
		//开启事物
		channel.txSelect();

		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		
		int i = 1/0;
		//提交
		channel.txCommit();

		System.out.println("send:" + msg);
		}catch (Exception e) {
			// TODO: handle exception
			channel.txRollback();
			System.out.println("send error ，rollback!");
		}finally {
			channel.close();
			connection.close();
		}
	}
}
