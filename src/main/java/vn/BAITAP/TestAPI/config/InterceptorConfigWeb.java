package vn.BAITAP.TestAPI.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfigWeb implements WebMvcConfigurer {
    private InterceptorConfig interceptorConfig;

    public InterceptorConfigWeb(InterceptorConfig interceptorConfig) {
        this.interceptorConfig = interceptorConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] endPoint = {
                "/user/find-by-email"
        };

        registry.addInterceptor(interceptorConfig).addPathPatterns(endPoint);
    }
}
