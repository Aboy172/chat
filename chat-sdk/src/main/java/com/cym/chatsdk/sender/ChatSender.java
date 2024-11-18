package com.cym.chatsdk.sender;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cym.chatcommon.contant.ChatRedisKey;
import com.cym.chatcommon.enums.sender.ChatSendCode;
import com.cym.chatcommon.enums.cmd.ChatCmdType;
import com.cym.chatcommon.model.ChatPrivateMessage;
import com.cym.chatcommon.model.ChatRecvInfo;
import com.cym.chatcommon.model.ChatSendResult;
import com.cym.chatcommon.model.ChatUserInfo;
import com.cym.chatcommon.redismq.RedisMQTemplate;
import com.cym.chatsdk.listener.MessageListenerMulticaster;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: cym
 * @create: 2024-11-19 05:15
 * @description: 消息发送类
 * @version: 0.0.1
 **/

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatSender {


    private final MessageListenerMulticaster listenerMulticaster;

    private RedisMQTemplate redisMQTemplate;
    @Value("${spring.application.name}")
    private String appName;

    /**
     * 判断一个用户是否在线
     *
     * @param userId 用户id
     * @return 在线结果
     */
    public Boolean isOnline(Long userId) {
        String key = String.join(":", ChatRedisKey.CHAT_USER_SERVER_ID, userId.toString(), "*");
        return !Objects.requireNonNull(redisMQTemplate.keys(key)).isEmpty();
    }

    /**
     * 判断多个用户是否在线
     *
     * @param userIds 用户id列表
     * @return 用户在线列表
     */

    public List<Long> getBatchOnline(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        List<String> userIdKey = userIds.stream()
                .map(userId -> String.join(":", ChatRedisKey.CHAT_USER_SERVER_ID, userId.toString()))
                .collect(Collectors.toList());

        List<Object> serviceIds = redisMQTemplate.opsForValue().multiGet(userIdKey);
        //过滤出在线的用户
        List<Long> onlineUsers = new ArrayList<>();
        for (int i = 0; i < serviceIds.size(); i++) {
            if (ObjectUtil.isNotEmpty(serviceIds.get(i))) {
                onlineUsers.add(userIds.get(i));
            }
        }

        return onlineUsers;
    }


    public <T> void sendSystemMessage() {}

    public <T> void sendGroupMessage() {}


    public <T> void sendPrivateMessage(ChatPrivateMessage<T> message) {
        //离线消息列表
        LinkedList<ChatSendResult> offMessageList = new LinkedList<>();
        if (ObjectUtil.isNotEmpty(message.getRevId())){
            //获取用户正在连接的channelId
            Long revId = message.getRevId();

            String revKey = ChatRedisKey.getUserServiceIdKey(revId);
            Object serverId = redisMQTemplate.opsForValue().get(revKey);
            //对方在线
            if (ObjectUtil.isNotEmpty(serverId)){
                String sendKey = String.join(":", ChatRedisKey.CHAT_MESSAGE_PRIVATE_QUEUE, serverId.toString());
                ChatRecvInfo chatRecvInfo = new ChatRecvInfo();
                chatRecvInfo.setSender(message.getSender());
                chatRecvInfo.setCmd(ChatCmdType.PRIVATE_MESSAGE.code());
                chatRecvInfo.setServiceName(appName);
                chatRecvInfo.setReceivers(Collections.singletonList(new ChatUserInfo(message.getRevId(),0)));
                chatRecvInfo.setData(message.getData());
                redisMQTemplate.opsForList().rightPush(sendKey,chatRecvInfo);
            }else{
                ChatSendResult<Object> sendResult = new ChatSendResult<>();
                sendResult.setCode(ChatSendCode.NOT_ONLINE.getCode());
                sendResult.setReceiverUserInfo(new ChatUserInfo(message.getRevId(),0));
                sendResult.setData(message.getData());
                offMessageList.add(sendResult);
            }

        }
        //TODO 回退其他终端
        if(message.getSendToSelf()){

        }
        //TODO 消息回退
    }
}
