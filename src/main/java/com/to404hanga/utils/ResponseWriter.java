package com.to404hanga.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseWriter {
    // 提取出来的反馈方法
    public static void alertAndRelocation(HttpServletResponse response, String alertMessage, String locationHref) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('" + alertMessage + "');");
        out.println("window.location.reload();");
        out.println("window.location.href='" + locationHref + "'");
        out.println("</script>");
        out.close();
    }
}
