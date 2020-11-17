package com.jojoldu.book.springboot.config;
// LoginUserArgumentResolver를 스프링에서 인식할 수 있도록 WebMvcConfigurer에 추가하는 클래스

import com.jojoldu.book.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolver){
        argumentResolver.add(loginUserArgumentResolver);
        /*
            HandlerMethodArgumentResolver는 항상 WebMvcConfigurer addArgumentResolvers()을 통해 추가해야함.
            https://jhkang-tech.tistory.com/49 참고.
         */
    }
}
