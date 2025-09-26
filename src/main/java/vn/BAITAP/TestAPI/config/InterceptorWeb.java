// package vn.BAITAP.TestAPI.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class InterceptorWeb implements WebMvcConfigurer {

// private InterceptorConfiguration interceptorConfiguration;

// public InterceptorWeb(InterceptorConfiguration interceptorConfiguration) {
// this.interceptorConfiguration = interceptorConfiguration;
// }

// @Override
// public void addInterceptors(InterceptorRegistry registry) {
// String[] path = {
// "/user/**", "/users/**"
// };

// registry.addInterceptor(interceptorConfiguration).addPathPatterns(path);
// }
// }
