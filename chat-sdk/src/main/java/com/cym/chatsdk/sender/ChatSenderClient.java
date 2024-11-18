package com.cym.chatsdk.sender;

import com.cym.chatcommon.model.ChatPrivateMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: cym
 * @create: 2024-11-19 06:12
 * @description: 发送者 采用门面模式
 * @version: 0.0.1
 **/

@Component
@RequiredArgsConstructor
public class ChatSenderClient {

    private final ChatSender chatSender;



    public Boolean isOnline(Long userId) {
        return chatSender.isOnline(userId);
    }

    public List<Long> getBatchOnline(List<Long> userIds) {
        return chatSender.getBatchOnline(userIds);
    }

    public <T> void sendSystemMessage() {}
    public <T> void sendPrivateMessage(ChatPrivateMessage<T> message) {
        //构建消息体
        chatSender.sendPrivateMessage(message);

    }
    public <T> void sendGroupMessage() {}



}
