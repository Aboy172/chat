package com.cym.chatcommon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: cym
 * @create: 2024-11-19 06:08
 * @description: 私聊消息
 * @version: 0.0.1
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class ChatPrivateMessage<T> extends ChatBaseMessage {
    /**
     * 发送消息方
     */
    private ChatUserInfo sender;
    /**
     * 消息接收方id
     */
    private Long revId;
    /**
     * 消息体
     */
    private T data;
}

