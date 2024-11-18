package com.cym.chatsdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: cym
 * @create: 2024-11-19 03:41
 * @description: chat-sdk依赖自动注入配置类
 * @version: 0.0.1
 **/
@Slf4j
@ComponentScan(basePackages = {"com.cym.chatcommon","com.cym.chatsdk"})
@Configuration
public class ChatAutoConfiguration {
}
