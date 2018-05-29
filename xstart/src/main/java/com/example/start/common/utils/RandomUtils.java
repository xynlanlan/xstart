package com.example.start.common.utils;

/**
 * 生成随机数工具类
 * Created by Bond(China) on 2016/11/24.
 */
public final class RandomUtils {

    private static char[] chs = {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    };

    private RandomUtils() {
    }

    /**
     * 生成指定长度字符串的验证码
     *
     * @param len
     * @return
     */
    public static String randomCodeStr(int len) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * chs.length);
            result.append(chs[index]);
        }
        return result.toString();
    }

    /**
     * 生成指定长度数字的验证码
     *
     * @param len
     * @return
     */
    public static String randomCode(int len) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < len; i++) {
            result.append(randomInt());
        }
        return result.toString();
    }

    /**
     * 生成6位默认的数字验证码
     *
     * @return
     */
    public static String randomDefault() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            result.append(randomInt());
        }
        return result.toString();
    }

    /**
     * 生成一位数字随机值
     */
    private static Integer randomInt() {
        return (int) (Math.random() * 10);
    }

    /**
     * 生成随机整形值
     *
     * @param limit
     * @return
     */
    public static Integer randomInt(int limit) {
        return (int) (Math.random() * limit);
    }
}
