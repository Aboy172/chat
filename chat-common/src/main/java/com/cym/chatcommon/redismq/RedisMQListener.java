package com.cym.chatcommon.redismq;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RedisMQListener {
    /**
     * 队列key
     *
     */
    String queue();

    /**
     * 批量拉取信息数量
     *
     */
    int batchSize() default 1;

    /**
     * 消息拉取间隔
     */
    int period() default 100;

}
