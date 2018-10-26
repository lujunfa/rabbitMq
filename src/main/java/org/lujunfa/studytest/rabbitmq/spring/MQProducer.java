package org.lujunfa.studytest.rabbitmq.spring;

//MQProducer.java
public interface MQProducer {
    /**
     * 发送消息到指定队列
     * @param queueKey
     * @param object
     */
     void sendDataToQueue(String queueKey, Object object);
}