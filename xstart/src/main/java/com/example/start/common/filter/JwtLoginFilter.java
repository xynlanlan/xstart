package com.example.start.common.filter;

import com.example.start.common.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    public static final String USERNAME = "loginAccount";
    public static final String PASSWORD = "password";

    private AuthenticationManager authenticationManager;

    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username = "admin";//request.getParameter(USERNAME);
        String password = "123456"/*new BCryptPasswordEncoder().encode("123456")*/;//request.getParameter(PASSWORD);

        if(username == null || username == ""){
            throw new AuthenticationServiceException("请输入账号！");
        }else if(password == null || password == ""){
            throw new AuthenticationServiceException("请输入密码！");
        }
       /* SysUser user = null;
        try {
            user = sysUserService.findByAccount(username);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (user == null || !user.getPassword().equals(new BCryptPasswordEncoder().encode(password))) {
            throw new UsernameNotFoundException("用户名或密码不正确!");
        }*/
        /*String encode = new BCryptPasswordEncoder().encode(password);*/
        // 验证用户是否被启用
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password,new ArrayList<>());
        // 允许子类设置详细属性
        //this.setDetails(request, authRequest);
        // 运行UserDetailsService的loadUserByUsername 再次封装Authentication
        return authenticationManager.authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS512, "PrivateSecret")
                .compact();

        response.addHeader("Authorization", JwtUtils.getTokenHeader(token));
    }
}
