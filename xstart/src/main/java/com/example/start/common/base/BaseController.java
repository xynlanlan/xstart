package com.example.start.common.base;
import com.alibaba.fastjson.JSON;

import java.util.Map;

public abstract class BaseController {

    /**
     * 加入结果
     *
     * @param key   KEY
     * @param value Value
     */
    protected void put(String key, Object value) {
        ResultMap.put(key, value);
    }

    /**
     * 加入结果
     *
     * @param key   KEY
     * @param value Value
     */
    protected void put(Integer key, Object value) {
        ResultMap.put(key.toString(), value);
    }

    /**
     * 加入结果，其KEY为result
     *
     * @param val Value
     */
    protected void put(Object val) {
        ResultMap.put(val);
    }

    /**
     * 设置成功的结果
     *
     * @return Map
     */
    protected Map<String, Object> success() {
        return ResultMap.success();
    }

    /**
     * 设置成功的结果
     *
     * @param value Value
     * @return Map
     */
    protected Map<String, Object> success(Object value) {
        put(value);
        return ResultMap.success();
    }

    protected Map<String, Object> failure(Object value) {
        put(value);
        return ResultMap.failure();
    }

    /**
     * 设置成功的结果
     *
     * @param key   KEY
     * @param value Value
     * @return Map
     */
    protected Map<String, Object> success(String key, Object value) {
        put(key, value);
        return ResultMap.success();
    }

    /**
     * 设置失败的结果
     *
     * @param e An exception
     * @return Map
     */
    protected Map<String, Object> failure(Exception e) {
        return ResultMap.failure(e);
    }

    /**
     * 设置失败的结果
     *
     * @param message Message
     * @param e       An exception
     * @return Map
     */
    protected Map<String, Object> failure(String message, Exception e) {
        return ResultMap.failure(message, e);
    }

    /**
     * 设置失败的结果
     *
     * @param code
     * @param message
     * @return
     */
    protected Map<String, Object> failure(String code, String message) {
        return ResultMap.failure(code, message, new Exception());
    }

    /**
     * 设置失败的结果
     *
     * @param code
     * @param message
     * @return
     */
    protected Map<String, Object> failure(Integer code, String message) {
        return failure(code.toString(), message);
    }

    /**
     * 设置失败的结果
     *
     * @param code    Code
     * @param message Message
     * @param e       An exception
     * @return Map
     */
    protected Map<String, Object> failure(String code, String message, Exception e) {
        return ResultMap.failure(code, message, e);
    }

    /**
     * 设置失败的结果
     *
     * @param code    Code
     * @param message Message
     * @param e       An exception
     * @return Map
     */
    protected Map<String, Object> failure(Integer code, String message, Exception e) {
        return ResultMap.failure(code.toString(), message, e);
    }

    /**
     * 将错误转换成JSON
     *
     * @param code
     * @param message
     * @return
     */
    protected String failureToJSON(String code, String message) {
        return JSON.toJSONString(this.failure(code, message));
    }

    /**
     * 将错误转换成JSON
     *
     * @param e
     * @return
     */
    protected String failureToJSON(Exception e) {
        return JSON.toJSONString(this.failure(e));
    }

    /**
     * 将结果转换成JSON
     *
     * @param o
     * @return
     */
    protected String successToJSON(Object o) {
        return JSON.toJSONString(success(o));
    }

    /**
     * 将结果转换成JSON
     *
     * @return
     */
    protected String successToJSON() {
        return JSON.toJSONString(success());
    }
}
