package hello.core.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod());             // GET
        System.out.println("request.getProtocol() = " + request.getProtocol());         // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme());             // http
        System.out.println("request.getRequestURL() = " + request.getRequestURL());     // http://localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());     // /request-header
        System.out.println("request.getQueryString() = " + request.getQueryString());   // username=hi
        System.out.println("request.isSecure() = " + request.isSecure());               // https 사용유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
        /*
         Enumeration<String> headerNames = request.getHeaderNames();
         while (headerNames.hasMoreElements()) {
         String headerName = headerNames.nextElement();
         System.out.println(headerName + ": " + request.getHeader(headerName));
         }
        */
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": "
                        + request.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
    }
}
