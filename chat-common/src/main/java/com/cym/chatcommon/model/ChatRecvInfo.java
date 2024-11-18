package com.cym.chatcommon.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author: cym
 * @create: 2024-11-19 06:29
 * @description: 接收方消息
 * @version: 0.0.1
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class ChatRecvInfo  extends ChatBaseMessage{
    /**
     *  发送方
     */
    private  ChatUserInfo sender;
    /**
     * 消息操作类型
     */
    private  Integer cmd;

    /**
     * 接收方用户列表
     */
    List<ChatUserInfo> receivers;

    /**
     * 是否需要回调发送结果
     */

    /**
     * 当前服务名（回调发送结果使用）
     */
    private String serviceName;
    /**
     * 推送消息体
     */
    private Object data;

}
