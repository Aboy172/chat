package com.cym.chatcommon.contant;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChatRedisKey {
    private ChatRedisKey() {
    }

    /**
     * Chat-server最大id,从0开始递增
     */
    public static final String CHAT_MAX_SERVER_ID = "Chat:max_server_id";
    /**
     * 用户ID所连接的Chat-server的ID
     */
    public static final String CHAT_USER_SERVER_ID = "Chat:user:server_id";
    /**
     * 系统消息队列
     */
    public static final String CHAT_MESSAGE_SYSTEM_QUEUE = "Chat:message:system";
    /**
     * 私聊消息队列
     */
    public static final String CHAT_MESSAGE_PRIVATE_QUEUE = "Chat:message:private";
    /**
     * 群聊消息队列
     */
    public static final String CHAT_MESSAGE_GROUP_QUEUE = "Chat:message:group";

    /**
     * 系统消息发送结果队列
     */
    public static final String CHAT_RESULT_SYSTEM_QUEUE = "Chat:result:system";
    /**
     * 私聊消息发送结果队列
     */
    public static final String CHAT_RESULT_PRIVATE_QUEUE = "Chat:result:private";
    /**
     * 群聊消息发送结果队列
     */
    public static final String CHAT_RESULT_GROUP_QUEUE = "Chat:result:group";


    public static String getUserServiceIdKey(Long userId) {
        return String.join(":", CHAT_USER_SERVER_ID, userId.toString());
    }

    public static List<String> getUserServiceIdKeyList(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        List<String> keys = new ArrayList<>();
        for (Long userId : userIds) {
            keys.add(getUserServiceIdKey(userId));
        }
        return keys;
    }

}
