package org.lujunfa.studytest.rabbitmq.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
*@Author lujunfa
**/
public class SpringSender {
		public static void main(String[] args) throws InterruptedException {
			AbstractApplicationContext context =new ClassPathXmlApplicationContext("classpath:rabbitmq.xml");
			RabbitTemplate rabbitTemplate = context.getBean(RabbitTemplate.class);
			//发送消息
			rabbitTemplate.convertAndSend("hello word");
			Thread.sleep(2000);
			context.destroy();
		}
}
