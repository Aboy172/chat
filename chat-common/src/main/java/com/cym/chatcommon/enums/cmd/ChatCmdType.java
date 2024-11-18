package com.cym.chatcommon.enums.cmd;

import lombok.AllArgsConstructor;


/**
 * @author: cym
 * @create: 2024-11-19 06:33
 * @description:
 * @version: 0.0.1
 **/
@AllArgsConstructor
public enum ChatCmdType {

    /**
     * 登陆
     */
    LOGIN(0, "登陆"),
    /**
     * 心跳
     */
    HEART_BEAT(1, "心跳"),
    /**
     * 强制下线
     */
    FORCE_LOGUT(2, "强制下线"),
    /**
     * 私聊消息
     */
    PRIVATE_MESSAGE(3, "私聊消息"),
    /**
     * 群发消息
     */
    GROUP_MESSAGE(4, "群发消息"),
    /**
     * 系统消息
     */
    SYSTEM_MESSAGE(5,"系统消息");


    private final Integer code;

    private final String desc;


    public static ChatCmdType format(Integer code) {
        for (ChatCmdType typeEnum : values()) {
            if (typeEnum.code.equals(code)) {
                return typeEnum;
            }
        }
        return null;
    }


    public Integer code() {
        return this.code;
    }

    public String  desc(){
        return this.desc;
    }
}
