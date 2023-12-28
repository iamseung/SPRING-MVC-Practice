package hello.core.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.core.basic.HelloData;
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
    [Json 형식으로 데이터 전달]
    POST http://localhost:8080/request-body-json
    content-type: **application/json**
    message body: `{"username": "hello", "age": 20}`
    결과: `messageBody = {"username": "hello", "age": 20}`
 */
@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 메세지 바디의 내용을 바이트 코드로 얻음
        ServletInputStream inputStream = request.getInputStream();

        // 스프링이 제공하는 유틸리티 클래스
        // 바이트를 문자로 변환, 대부분 UTF-8
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        /*
        JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson, Gson 같은 JSON 변환 라이브러리 를 추가해서 사용해야 한다.
        스프링 부트로 Spring MVC를 선택하면 기본으로 Jackson 라이브러리( `ObjectMapper` )를 함께 제공한다.
         */
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("getUsername = " + helloData.getUsername());
        System.out.println("getAge = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}
