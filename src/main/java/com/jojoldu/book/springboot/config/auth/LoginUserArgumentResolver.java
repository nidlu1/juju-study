package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component //빈에 등록함.
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    //HandlerMethodArgumentResolver : 조건에 맞는 경우, 메소드가 있다면 HandlerMethodArgumentResolver의 구현체가 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다.

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter){ //supportsParameter: 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단. 바인딩할 클래스를 지정하는 메서드
        // 여기선 파라미터에 @LoginUser 어노테이션이 붙어 있고, 파라미터클래스타입이 SessionUser.class인 경우 true 반환
        System.out.println("==========LoginUserArgumentResolver.supportsParameter==========");
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;

        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{
        System.out.println("==========LoginUserArgumentResolver.resolveArgument==========");
        return httpSession.getAttribute("user");
        // resolveArgument() : 파라미터에 전달할 객체를 생성. 여기선 세션에서 객체를 가져옴. 바인딩할 객체를 조작할 수 있는 메서드.
    }
}
