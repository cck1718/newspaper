package com.cck.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    public static String renderString(HttpServletResponse response,String sting){
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println(sting);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
