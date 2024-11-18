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
 * @create: 2024-11-19 04:04
 * @description: 群组消息结果处理工作类
 * @version: 0.0.1
 **/
@RedisMQListener(queue = ChatRedisKey.CHAT_RESULT_GROUP_QUEUE, batchSize = 100)
@Component
@RequiredArgsConstructor
public class GroupMessageResultTask extends AbstractMessageResultTask<ChatSendResult> {
    private final MessageListenerMulticaster messageListenerMulticaster;

    @Override
    public void onMessage(List<ChatSendResult> data) {
        messageListenerMulticaster.multicast(ChatListenerType.GROUP_MESSAGE, data);
    }
}
