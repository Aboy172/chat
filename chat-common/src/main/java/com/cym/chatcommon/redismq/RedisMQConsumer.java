package com.cym.chatcommon.redismq;

import java.util.List;

/**
 * @author: cym
 * @create: 2024-11-19 05:01
 * @description: redis 消息队列消费者抽象类
 * @version: 0.0.1
 **/

public abstract class RedisMQConsumer<T> {
    /**
     * 消费单条消息回调
     * @param data 消息体
     */
    public void onMessage(T data) {}


    public void onMessage(List<T>data){}

    /**
     * 队列名就是注解上key
     * @return
     */
    public String getKey(){
        RedisMQListener annotation = this.getClass().getAnnotation(RedisMQListener.class);
        return annotation.queue();
    }

    /**
     * 队列就绪
     */
    public Boolean isReady(){
        return true;
    }

}
