package com.example.start.common.config.jwt;

import com.example.start.module.entity.SysRole;
import com.example.start.module.entity.SysUser;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class AwesomeJwt {

    @Value("jwt.secret")
    private String secret;

    public String createJwtToken(SysUser user) {
        Date expiration = new Date(System.currentTimeMillis() + 600000L);
        String token = Jwts.builder().claim("username", user.getLoginAccount()).claim("role", user.getRole().getAlias())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret).compact();
        return token;
    }

    public Optional<Claims> getClaims(String token) {
        try {
            return Optional.of(Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody());
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public Optional<SysUser> extractUser(String token) {
        Optional<Claims> claims = getClaims(token);
        if (claims.isPresent()) {
            String username = (String) claims.get().get("username");
            String role = (String) claims.get().get("role");
            SysUser user = new SysUser();
            user.setLoginAccount(username);
            SysRole sysRole = new SysRole();
            sysRole.setAlias(role);
            user.setRole(sysRole);
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    public boolean isExpired(String token) {
        Optional<Claims> claims = getClaims(token);
        if (claims.isPresent()) {
            Date expiration = claims.get().getExpiration();
            return expiration.before(new Date());
        }
        return false;
    }

    public String refreshJwt(String token) {
        Optional<SysUser> user = extractUser(token);
        if (user.isPresent()) {
            return createJwtToken(user.get());
        }
        return null;
    }
}
