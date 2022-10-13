package com.cck.security.filter;

import com.cck.security.LoginUser;
import com.cck.utils.JwtUtils;
import com.cck.utils.RedisUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * jwt拦截器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        //如果没有token则放行
        if (!StringUtils.hasText(token)){
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        if (!JwtUtils.verifyToken(token))throw new RuntimeException("token不合法");
        String id = JwtUtils.getValue(token, "id");
        //从redis中获取用户信息
        LoginUser loginUser = (LoginUser) RedisUtils.getRedisTemplate().opsForValue().get("login" + id);
        if (Objects.isNull(loginUser))throw new RuntimeException("用户未登录");
        //存入SecurityContextHolder 该用户已认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
