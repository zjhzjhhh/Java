package com.vuln;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.PrintWriter;


@WebServlet("/SsrfDemo")
public class SsrfDemo extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter print = response.getWriter();
        String url = request.getParameter("url");
        String htmlContent;
        try {
            //实例化
            URL u = new URL(url);
            //打开和url之间的连接
            URLConnection urlConnection = u.openConnection();
            HttpURLConnection httpUrl = (HttpURLConnection) urlConnection;
            //获取URL响应
            BufferedReader base = new BufferedReader(new InputStreamReader(httpUrl.getInputStream(), "UTF-8"));
            StringBuffer html = new StringBuffer();
            while ((htmlContent = base.readLine()) != null) {
                html.append(htmlContent);
            }
            base.close();
            print.println("<b>内网端口探测</b></br>");
            print.println("<b>url:" + url + "</b></br>");
            print.println(html.toString());
            print.flush();
        } catch (Exception e) {
            e.printStackTrace();
            print.println("端口不存在或其他原因");
            print.flush();
        }
    }

}
