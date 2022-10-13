package com.cck.security.handler;

import com.alibaba.fastjson.JSON;
import com.cck.domain.ResponseResult;
import com.cck.enums.AppHttpCodeEnum;
import com.cck.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义权限异常处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(AppHttpCodeEnum.NO_OPERATOR_AUTH.getCode(),AppHttpCodeEnum.NO_OPERATOR_AUTH.getMsg());
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(httpServletResponse,json);
    }
}
