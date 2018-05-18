package com.example.start.common.config.jwt;

import com.example.start.module.entity.SysRole;
import com.example.start.module.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@SpringBootApplication
@RestController
public class JwtSampleApp {

    @Autowired
    private AwesomeJwt awesomeJwt;

    @GetMapping("/createJwt")
    public String createJwt(HttpServletResponse response) {
        SysUser user = new SysUser();
        user.setLoginAccount("admin");
        SysRole sysRole = new SysRole();
        sysRole.setAlias("admin");
        user.setRole(sysRole);
        Cookie cookie = new Cookie("token", awesomeJwt.createJwtToken(user));
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "createJwt success";
    }

    @GetMapping("/refreshJwt")
    public String refreshJwt(HttpServletResponse response, @CookieValue("token") String token) {
        Cookie cookie = new Cookie("token", awesomeJwt.refreshJwt(token));
        response.addCookie(cookie);
        cookie.setHttpOnly(true);
        return "refreshJwt success";
    }

    @GetMapping("/getUser")
    public SysUser getUser(@CookieValue("token") String token) {
        Optional<SysUser> user = awesomeJwt.extractUser(token);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtSampleApp.class, args);
    }
}
