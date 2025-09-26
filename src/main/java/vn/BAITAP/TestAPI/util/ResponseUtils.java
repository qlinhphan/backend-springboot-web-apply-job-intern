package vn.BAITAP.TestAPI.util;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletResponse;
import vn.BAITAP.TestAPI.domain.dto.ResponseData;

@RestControllerAdvice
public class ResponseUtils implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int stt = servletResponse.getStatus();

        if (body instanceof String) {
            return body;
        }

        if (stt >= 400) {
            return body;
        } else {
            ResponseData<Object> rd = new ResponseData<>();
            rd.setSttCode(stt);
            rd.setData(body);
            rd.setMessage("Call api success");
            return rd;
        }

    }

}
