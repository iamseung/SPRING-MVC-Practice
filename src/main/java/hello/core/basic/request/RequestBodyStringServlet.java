package hello.core.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/*
    [텍스트 형식으로 데이터 전달]
    POST http://localhost:8080/request-body-string content-type: text/plain
    message body: `hello`
    결과: `messageBody = hello`
 */
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    /*
    raw > Body 에 일반 텍스트를 입력하기 때문에
    Header 에 Content-Type 은 text/plain 으로 잡힌다
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 메세지 바디의 내용을 바이트 코드로 얻음
        ServletInputStream inputStream = request.getInputStream();

        // 스프링이 제공하는 유틸리티 클래스
        // 바이트를 문자로 변환, 대부분 UTF-8
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
