package com.example.start.common.utils;

import com.example.start.common.constant.Constants;
import com.example.start.common.exception.ExceptionCode;
import com.example.start.common.exception.ServiceException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 常用的操作工具集锦
 */
public final class $ {

    private static final Logger LOGGER = LoggerFactory.getLogger($.class);

    private $() {
    }

    /**
     * 判断是否为空
     *
     * @param object 需要被判定的对象
     * @return Boolean
     */
    public static boolean isNull(Object object) {
        return object == null;
    }
    /**
     * 判断是否为空及空字符
     *
     * @param object 需要被判定的对象
     * @return Boolean
     */
    public static boolean isNullStr(Object object) {
    	if(object instanceof String){
    		return object == null ||((String) object).trim().length()==0;
    	}
        return object == null;
    }

    /**
     * 判断是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(String object) {
        return StringUtils.isEmpty(object);
    }

    /**
     * 判断是否为空
     *
     * @param object
     * @return
     */
    public static boolean isEmpty(List object) {
        return CollectionUtils.isEmpty(object);
    }

    /**
     * 是否相等
     *
     * @param v1 Value1
     * @param v2 Value2
     * @return Boolean
     */
    public static boolean isEqual(Integer v1, Integer v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    /**
     * 是否相等
     *
     * @param v1 Value1
     * @param v2 Value2
     * @return Boolean
     */
    public static boolean isEqual(Byte v1, Byte v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    /**
     * 是否相等
     *
     * @param v1 Value1
     * @param v2 Value2
     * @return Boolean
     */
    public static boolean isEqual(Long v1, Long v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    /**
     * 是否相等
     *
     * @param v1
     * @param v2
     * @return Boolean
     */

    public static boolean isEqual(Boolean v1, Boolean v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    /**
     * 是否相等
     *
     * @param v1 Value1
     * @param v2 Value2
     * @return Boolean
     */
    public static boolean isEqual(String v1, String v2) {

        if (isNull(v1) || isNull(v2)) {
            return false;
        }
        return v1.equals(v2);
    }

    public static boolean isTrue(Boolean val) {

        if (isNull(val) || val == false) {
            return false;
        }
        return true;
    }


    /**
     * 非0均为true, 0 为false
     *
     * @param integer
     * @return
     */
    public static Boolean toBool(Integer integer) {
        if ($.isNull(integer)) {
            return null;
        }
        if ($.isEqual(integer, 0)) {
            return false;
        }
        return true;
    }

    /**
     * "false" To boolean false, "true" to boolean true, other of null
     *
     * @param str
     * @return
     */
    public static Boolean toBool(String str) {
        if ($.isNull(str)) {
            return null;
        }
        if ($.isEqual(str, "true")) {
            return true;
        }
        if ($.isEqual(str, "false")) {
            return false;
        }
        return null;
    }

    /**
     * 判断数组是否为空
     *
     * @param ignoreFields
     * @return
     */
    public static boolean isEmpty(Object[] ignoreFields) {
        return ArrayUtils.isEmpty(ignoreFields);
    }


    /**
     * URL 转码
     *
     * @param url
     * @return
     */
    public static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Url encode", e);
            return URLEncoder.encode(url);
        }
    }

    /**
     * URL 转码
     *
     * @param url
     * @return
     */
    public static String decodeUrl(String url) {
        try {
            return URLDecoder.decode(url, Constants.ENCODING);
        } catch (UnsupportedEncodingException e) {
            LOGGER.warn("Url decode", e);
            return URLDecoder.decode(url);
        }
    }

    /**
     * 判断字符串是否是数组
     *
     * @param o1
     * @throws ServiceException
     */
    public static boolean isJSON(String o1) throws ServiceException {
        try {
            if (o1.startsWith("[")) {
                JSONArray.fromObject(o1);
            } else {
                JSONObject.fromObject(o1);
            }
            return true;
        } catch (Exception e) {
            LOGGER.warn("$.isJSON", e);
            return false;
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param o1
     * @param o2
     * @throws ServiceException
     */
    public static void assertEquals(String o1, String o2) throws ServiceException {
        if ($.isEqual(o1, o2)) {
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param o1
     * @param o2
     * @throws ServiceException
     */
    public static void assertEquals(Integer o1, Integer o2) throws ServiceException {
        if ($.isEqual(o1, o2)) {
            throw new ServiceException(ExceptionCode.ILLEGAL_PARAMETER);
        }
    }

    /**
     * 将数组转换成字符串
     *
     * @param arr
     * @return
     */
    public static String toString(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * 将StringList数组转成传统Array
     *
     * @param list
     * @return
     */
    public static String[] toArray(List<String> list) {
        if ($.isEmpty(list)) {
            return new String[0];
        }
        String[] rs = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rs[i] = list.get(i);
        }
        return rs;
    }

    /**
     * 生成hashCode
     *
     * @param params
     * @return
     */
    public static int generateHashCode(String[] params) {

        if ($.isEmpty(params)) {
            return (int) (Math.random() * 10000);
        }

        int code = 10000;
        for (int i = 0; i < params.length; i++) {
            char[] chars = params[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                code = code + chars[j] << 3 + chars[j] >> 2;
            }
        }
        return code;
    }


    /**
     * 截取
     *
     * @param str
     * @param len
     * @return
     */
    public static String trim(String str, int len) {

        if ($.isEmpty(str)) {
            return str;
        }

        if (str.length() > len) {
            return str.substring(0, len);
        } else {
            return str;
        }
    }

    /**
     * 交集扩展操作, 将collection2中的元素的不为NULL的值覆盖到collection1中
     *
     * @param collection1
     * @param collection2
     * @param clz
     * @param ignores
     * @param <T>
     * @return
     * @throws ServiceException
     */
    public static <T> List<T> intersection(List<T> collection1, List<T> collection2, String[] ignores, Class<T> clz) throws ServiceException {

        List<T> intersection = (List<T>) CollectionUtils.intersection(collection1, collection2);

        if (CollectionUtils.isEmpty(intersection)) {
            return intersection;
        }

        List<T> result = new ArrayList();
        for (int i = 0; i < intersection.size(); i++) {
            final T old = intersection.get(i);
            T main = (T) CollectionUtils.find(collection2, new Predicate() {
                @Override
                public boolean evaluate(Object o) {
                    return old.equals(o);
                }
            });
            result.add($.merge(old, main, ignores, clz));
        }
        return result;
    }

    /**
     * 将o2中不为NULL的值 且值与o1的值不相等，全部赋于o1， 两个类应属于同一个类, 最终返回o1
     *
     * @param o1
     * @param o2
     */
    public static <T> T merge(T o1, T o2, String[] ignoreFields, Class<T> clz) throws ServiceException {
        try {
            T result = clz.newInstance();
            Field[] fields = clz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                field.setAccessible(true);
                Object value = null;
                if (isIgnore(ignoreFields, field)) {
                    value = field.get(o1);
                } else {
                    value = field.get(o2);
                }
                field.set(result, value);
                field.setAccessible(false);
                field.setAccessible(false);
            }
            return result;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new ServiceException(ExceptionCode.SYSTEM_ERROR, e);
        }
    }

    private static boolean isIgnore(String[] ignoreFields, Field field) {
        boolean ignore = false;
        if ($.isEmpty(ignoreFields)) {
            return ignore;
        }
        for (int e = 0; e < ignoreFields.length; e++) {
            if ($.isEqual(field.getName(), ignoreFields[e])) {
                ignore = true;
            }
        }
        return ignore;
    }


    /**
     * 校验
     */
    public static enum Validator {

        ALPHA_CHINESE("([\\u4e00-\\u9fa5]|[A-Za-z])+"),
        EMOJI("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]"),
        PHONE("[1][3465789][0-9]{9}"),
        EMAIL("(\\w+((-\\w+)|(\\.\\w+))*)\\+\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+"),
        CHINESE("[\\u4e00-\\u9fa5]+");

        private String reg;

        private Validator(String reg) {
            this.reg = reg;
        }

        public boolean match(String val) {
            Pattern pattern = Pattern.compile(this.reg);
            return pattern.matcher(val).matches();
        }

        public String match(String val, String replace) {
            if ($.isNull(val)) {
                return val;
            }
            Pattern pattern = Pattern.compile(this.reg);
            if ($.isEmpty(replace)) {
                pattern.matcher(val).replaceAll("[表情]");
            }
            return pattern.matcher(val).replaceAll(replace);
        }

        public static boolean customMatch(String reg, String val) {
            Pattern pattern = Pattern.compile(reg);
            return pattern.matcher(val).matches();
        }
    }
    /**
	 * 获取项目路径
	 * @param request
	 * @return
	 */
	public static String getProjectUrl(HttpServletRequest request){
		String scheme = request.getScheme();//当前链接使用的协议
        String serverName = request.getServerName();//服务器地址
        int port = request.getServerPort();//端口号
        String path = request.getContextPath();//应用名称
        String basePath = scheme + "://" + serverName + ":" + port + path;
        return basePath;
	}
	
    /**
     * 本机ip
     * @return
     */
     public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		return ipAddrStr;
	}
}
