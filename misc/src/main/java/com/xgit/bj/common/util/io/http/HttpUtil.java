package com.xgit.bj.common.util.io.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Slf4j
public class HttpUtil {
    public static <T> boolean addJsonBodyToResponse(HttpServletResponse response, T value) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(value);
            response.addHeader("content-type", "application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(json);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return true;
    }
}
