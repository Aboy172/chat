package com.cym.chatsdk.annoation;

import com.cym.chatcommon.enums.listener.ChatListenerType;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: cym
 * @create: 2024-11-19 03:52
 * @description: 实时系统监听
 * @version: 0.0.1
 **/
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface ChatListener {

    ChatListenerType type();
}
