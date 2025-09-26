package vn.BAITAP.TestAPI.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.BAITAP.TestAPI.domain.dto.ResponseData;

@Configuration
public class CustomAuthenEntryPoint implements AuthenticationEntryPoint {

    AuthenticationEntryPoint delegate = new BearerTokenAuthenticationEntryPoint();

    private ObjectMapper mapper;

    public CustomAuthenEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        this.delegate.commence(request, response, authException);

        ResponseData<Object> rd = new ResponseData<>();
        rd.setSttCode(401);
        rd.setError("have some problems with JWT");
        rd.setMessage("JWT that it can expired, error, empty, ...");

        this.mapper.writeValue(response.getWriter(), rd);
    }
}
