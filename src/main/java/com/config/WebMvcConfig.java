//package com.config;
//import com.Interceptor.TokenInterceptor  ;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.*;
//
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private TokenInterceptor tokenInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(tokenInterceptor)
//                // 拦截所有 /huanghe 路径下的请求
//                .addPathPatterns("/huanghe/**")
//                // 如果有部分接口不需要拦截，可以通过 excludePathPatterns() 方法排除，比如登录和 token 验证接口
//                .excludePathPatterns("/huanghe/login","/huanghe/SendVerificationCode","/huanghe/Register","/huanghe","swagger-ui.html");
//    }
//}
