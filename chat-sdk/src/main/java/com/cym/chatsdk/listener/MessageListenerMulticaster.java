package com.cym.chatsdk.listener;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.cym.chatcommon.enums.listener.ChatListenerType;
import com.cym.chatcommon.model.ChatSendResult;
import com.cym.chatsdk.annoation.ChatListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * @author: cym
 * @create: 2024-11-19 04:12
 * @description: 消息组播器
 * @version: 0.0.1
 **/

@Component
public class MessageListenerMulticaster {

    @Autowired(required = false)
    private List<MessageListener> messageListeners = Collections.emptyList();

    /**
     * 组播方法，对消息类型进行转换并安排消息处理器
     *
     * @param chatListenerType 消息类型
     * @param results          多条消息体 （可以为一条）
     */

    public void multicast(ChatListenerType chatListenerType, List<ChatSendResult> results) {
        //没有消息不进行处理
        if (CollUtil.isEmpty(results)) {
            return;
        }
        for (MessageListener messageListener : messageListeners) {
            ChatListener annotation = messageListener.getClass().getAnnotation(ChatListener.class);
            if (ObjectUtil.isNotEmpty(annotation)) {
                if (annotation.type().equals(ChatListenerType.ALL) || annotation.type().equals(chatListenerType)) {
                    results.forEach(result -> {
                        if (result.getData() instanceof JSONObject) {
                            //因为处理器需要实现MessageListener，因此可以从messageListener入手拿到对应的泛型类型，从而完成转换
                            Type type = ((ParameterizedType) messageListener.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                            JSONObject data = (JSONObject) result.getData();
                            //转换回原来类型
                            result.setData(data.toJavaObject(type));

                        }
                    });
                }
                messageListener.process(results);
            }

        }
    }

}
