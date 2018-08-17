package com.example.start.common.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.start.common.constant.Constants;
import com.example.start.module.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限拦截器
 */
public class SecurityInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(SecurityInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 验证权限
        if (this.hasPermission(request,handler)) {
            return true;
        }
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Map<String,Object> map = new HashMap<>();
        map.put("status", Constants.REST_ACK_ERROR);
        map.put("message","未登陆!");
        response.getWriter().write(JSONUtils.toJSONString(map));
        return false;
    }
    /**
     * 是否有权限
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(HttpServletRequest request,Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            RequiredPermission requiredPermission = handlerMethod.getMethod().getAnnotation(RequiredPermission.class);
            // 如果方法上的注解为空 则获取类的注解
            if (requiredPermission == null) {
                requiredPermission = handlerMethod.getMethod().getDeclaringClass().getAnnotation(RequiredPermission.class);
            }
            // 如果标记了注解，则判断权限
           /* if (requiredPermission != null && StringUtils.isNotBlank(requiredPermission.value())) {
                // redis或数据库 中获取该用户的权限信息 并判断是否有权限
                Set<String> permissionSet = adminUserService.getPermissionSet();
                if (CollectionUtils.isEmpty(permissionSet) ){
                    return false;
                }
                return permissionSet.contains(requiredPermission.value());
            }*/
           if(requiredPermission == null){
               return true;
           }
            HttpSession session = request.getSession();
            log.info("=============sessionId : " + session.getId());
            SysUser user = (SysUser)session.getAttribute(session.getId());
            return user != null;
        }
        return false;
    }
}
