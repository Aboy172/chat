package com.cym.chatsdk.task;

import com.cym.chatcommon.contant.ChatRedisKey;
import com.cym.chatcommon.enums.listener.ChatListenerType;
import com.cym.chatcommon.model.ChatSendResult;
import com.cym.chatcommon.redismq.RedisMQListener;
import com.cym.chatsdk.listener.MessageListenerMulticaster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: cym
 * @create: 2024-11-19 04:03
 * @description: 系统消息结果处理工作类
 * @version: 0.0.1
 **/
@RedisMQListener(queue = ChatRedisKey.CHAT_RESULT_SYSTEM_QUEUE, batchSize = 100)
@Component
@RequiredArgsConstructor

public class SystemMessageResultTask extends AbstractMessageResultTask<ChatSendResult>{

    private final MessageListenerMulticaster messageListenerMulticaster;

    @Override
    public void onMessage(List<ChatSendResult> data) {
        messageListenerMulticaster.multicast(ChatListenerType.SYSTEM_MESSAGE, data);
    }
}
