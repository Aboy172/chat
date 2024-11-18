package com.cym.chatcommon.model;

import lombok.Data;

/**
 * @author: cym
 * @create: 2024-11-19 04:29
 * @description:
 * @version: 0.0.1
 **/
@Data
public class ChatSendResult<T> {

    /**
     * 发送消息方
     */
    private ChatUserInfo sendUserInfo;
    /**
     * 接收消息方
     */
    private ChatUserInfo receiverUserInfo;
    /**
     * 发送消息状态码
     */
    private  Integer code;

    private  T data;



}
