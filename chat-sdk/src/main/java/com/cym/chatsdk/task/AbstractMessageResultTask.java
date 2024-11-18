package com.cym.chatsdk.task;

import com.cym.chatcommon.redismq.RedisMQConsumer;

/**
 * @author: cym
 * @create: 2024-11-19 05:00
 * @description: 抽象消息结果工作类  提取公共方法，代码复用
 * @version: 0.0.1
 **/

public class AbstractMessageResultTask<T>  extends RedisMQConsumer<T> {

}
