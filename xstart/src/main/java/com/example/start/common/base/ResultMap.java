/*
 * Copyright (c) 2015. Bond(China)
 */

package com.example.start.common.base;

import com.alibaba.fastjson.JSON;
import com.example.start.common.constant.Constants;
import com.example.start.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 接口返回对象
 */
public final class ResultMap {

    private ResultMap() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultMap.class);

    /**
     * 缓存返回结果，控制线程安全
     */
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    /**
     * 加和一个结果，其默认KEY为result
     *
     * @param object 直接对象
     */
    public static void put(Object object) {
        put(Constants.REST_RESULT, object);
    }

    /**
     * 加入其它需要返回的结果
     *
     * @param key    String
     * @param object Object
     */
    public static void put(String key, Object object) {
        if (threadLocal.get() == null) {
            threadLocal.set(new HashMap<String, Object>());
        }
        if (threadLocal.get().get(key) != null && object != null) {
            LOGGER.warn("[Result Map 丢失部分消息]" + object.toString());
            return;
        }
        threadLocal.get().put(key, object);
    }

    /**
     * 返回失败处理结果
     *
     * @param message String
     * @param e       Exception
     * @return Map
     */
    public static Map<String, Object> failure(String message, Exception e) {
        put(Constants.REST_MESSAGE, message);
        return failure(e);
    }

    /**
     * 返回失败处理结果
     *
     * @param exceptionCode String 类似于:user.add.illegalArg
     * @param message       String
     * @param e             Exception
     * @return Map
     */
    public static Map<String, Object> failure(String exceptionCode, String message, Exception e) {
        put(Constants.REST_CODE, exceptionCode);
        if (message == null || "".equals(message)) {
            return failure("[系统警告]未定义消息", e);
        }
        return failure(message, e);
    }

    /**
     * 返回成功处理结果
     *
     * @return Map
     */

    public static Map<String, Object> success() {
        put(Constants.REST_STATUS, Constants.REST_ACK_OK);
        put(Constants.REST_MESSAGE, Constants.REST_OK_MESSAGE);
        return returnResult();
    }

    /**
     * 返回失败处理结果
     *
     * @param e Exception
     * @return Map
     */
    public static Map<String, Object> failure(Exception e) {
        if (e != null) {
            LOGGER.error(e.getMessage(), e);
        }
        put(Constants.REST_STATUS, Constants.REST_ACK_ERROR);
        put(Constants.REST_MESSAGE, Constants.REST_ERROR_MESSAGE);
        return returnResult();
    }

    /**
     * 返回失败处理结果
     *
     * @param e ServiceException
     * @return Map
     */
    public static Map<String, Object> failure(ServiceException e) {
        return failure(e.getCode(), e.getMessage(), e);
    }

    /**
     * @return Map
     */
    private static Map<String, Object> returnResult() {
        Map<String, Object> rs = threadLocal.get();
        threadLocal.set(null);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("[Result Map]" + JSON.toJSONString(rs));
        }
        return rs;
    }
}