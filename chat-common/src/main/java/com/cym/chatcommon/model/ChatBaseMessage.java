package com.cym.chatcommon.model;

import lombok.Data;

/**
 * @author: cym
 * @create: 2024-11-19 06:10
 * @description:
 * @version: 0.0.1
 **/

@Data
public class ChatBaseMessage {

    /**
     * 是否发送给自己的其他终端,默认true
     */
    private Boolean sendToSelf = true;

    /**
     * 是否需要回推发送结果,默认true
     */
    private Boolean sendResult = true;
}
