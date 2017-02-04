package com.yt.commons.utils;

import com.yt.commons.ContextType;
import com.yt.commons.exceptions.CustomException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ResponseUtils
 * 处理请求返回结果
 *
 * @author yitao
 * @version 1.0.0
 * @date 2016/8/26 14:10
 */
public final class ResponseUtils {
    private static final String CHARSET = ";charset=UTF-8";

    private ResponseUtils() {

    }

    public static void renderText(HttpServletResponse response, String text) {
        render(response, ContextType.TXT + CHARSET, text);
    }

    public static void renderJson(HttpServletResponse response, String text) {
        render(response, ContextType.JSON + CHARSET, text);
    }

    public static void renderXml(HttpServletResponse response, String text) {
        render(response, ContextType.XML + CHARSET, text);
    }

    public static void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);

        try {
            response.getWriter().write(text);
        } catch (IOException ex) {
            throw new CustomException(50032, "返回数据失败！", ex);
        }

    }
}
