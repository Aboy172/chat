package com.cym.chatsdk.listener;

import java.util.List;

/**
 * @Author: cym
 * @create: 2024-11-19 03:55
 * @description: 监听接口，定义处理方法
 * @version: 1.0
 **/

public interface MessageListener<T> {
    /**
     * 处理消息，之所以是泛型列表，因为是可能有多组不同的消息体
     * @param results
     */
     void process(List<T> results);
}
