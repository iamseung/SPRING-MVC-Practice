package hello.core.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "reponseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK); // 200

        // [response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐시 무효화
        response.setHeader("Pragma", "no-cache"); // 과거 버전 캐시까지 초기화
        response.setHeader("my-header", "hello");

        // [Header 편의 메서드]
//        content(response);
        cookie(response);
        redirect(response);

        // [status-line]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse response) {
        /*
            [Set-Cookie:myCookie=ASDF; Max-Age=600; Expires=Thu, 28 Dec 2023 08:34:15 GMT]
            Set-Cookie:mmyCookie=asdf; Max-Age=600;
            response.setHeader("Set-Cookie", "myCookie=ASDF; Max-Age=600");1
         */

        Cookie cookie = new Cookie("myCookie", "ASDF");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // Status Code 302
        // Location : /basic/hello-form.html
        response.setStatus(HttpServletResponse.SC_FOUND); // 302
        response.sendRedirect("/basic/hello-form.html");
    }
}
