package com.example.start.common.utils;

import com.example.start.common.constant.Constants;
import org.apache.axis.types.HexBinary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * MD5 加密工具类
 * Created by Bond(China) on 2016/11/25.
 */
public final class MD5 {

    private static final Logger LOGGER = LoggerFactory.getLogger(MD5.class);
    private static final String ALGORITHM_NAME = "MD5";

    private MD5() {
    }

    /**
     * MD5 加密
     *
     * @param bts
     * @return
     */
    public static String encrypt(byte[] bts) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_NAME);
            digest.update(bts);
            return HexBinary.encode(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("[MD5 Encrypt]", e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * MD5 加密
     *
     * @param content
     * @param charset
     * @return
     */
    public static String encrypt(String content, String charset) {
        try {
            return encrypt(content.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("[MD5 Encrypt]", e);
            throw new IllegalStateException(e);
        }
    }

    /**
     * MD5 加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        return encrypt(content, Constants.ENCODING);
    }

    /**
     * MD5序列化实体类的值
     *
     * @param clz
     * @param t
     * @param key 追加Key值
     * @param <T>
     * @return
     */
    public static <T extends Serializable> String encrypt(Class<T> clz, T t, String key) {
        String result = "";
        if (t==null) {
            return result;
        }
        Field[] fields = clz.getDeclaredFields();
        Arrays.sort(fields, new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            result += getString(t, field);
        }
        if (key!=null) {
            result += key;
        }
        return encrypt(result, Constants.ENCODING);
    }

    private static <T extends Serializable> String getString(T t, Field field) {
        String result = "";
        try {
            field.setAccessible(true);
            Object value = field.get(t);
            if (value!=null) {
                result += value;
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("[MD5 Serialize]", e);
        } finally {
            field.setAccessible(false);
        }
        return result;
    }
}
