package com.cck.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {
    /**
     * 5天(毫秒)
     */
    private static final long EXPIRATION_TIME = 1000*60*60*24;
    /**
     * JWT密钥
     */
    private static final String SECRET = "cck1718";


    /**
     * 签发JWT
     */
    public static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    public static String createToken(String id){
        return Jwts.builder()
                .claim("id",id)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 验证JWT
     * 1.能不能解析
     * 2.能解析的话，是否过期
     */
    public static boolean verifyToken(String token) {
        if (StringUtils.isEmpty(token)){
            return false;
        }
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 如果验证成功了才能调用。根据key获取value。
     */
    public static String getValue(String token, String key) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.get(key, String.class);
    }

}
