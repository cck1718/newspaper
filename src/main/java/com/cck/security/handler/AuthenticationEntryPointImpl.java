package com.cck.security.handler;

import com.alibaba.fastjson.JSON;
import com.cck.domain.ResponseResult;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证异常处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(AppHttpCodeEnum.NEED_LOGIN.getCode(),"用户认证失败");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(httpServletResponse,json);
    }
}
