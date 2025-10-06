package vn.BAITAP.TestAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class InterceptorConfig implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String endPoint = request.getRequestURI();
        String method = request.getMethod();
        String infoEmailAtRequest = request.getParameter("email");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println("CHECK: " + endPoint + " " + method + " " + infoEmailAtRequest);

        if (endPoint.equals("/user/find-by-email") && method.equals("GET")) {

        }

        return true;
    }
}
