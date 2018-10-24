package org.lujunfa.studytest.rabbitmq.spring;
/**
*@Author lujunfa
**/
public class Consumer {
			public void listen(String msg) {
				System.out.println("消费者:"+msg);
			}
}
