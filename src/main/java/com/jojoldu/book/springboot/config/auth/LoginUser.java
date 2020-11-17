package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //이 어노테이션이 생성 될 수 있는 위치를 지정. parameter로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용가능. 이외에도 클래스 선언문에 쓸 수 있는 type 등이 있음.
@Retention(RetentionPolicy.RUNTIME) //어느시점까지 어노테이션의 메모리를 가져갈지 설정. 런타임 종료시까지 사용.
public @interface LoginUser { //@interface: 이 파일을 어노테이션클래스로 지정. LoginUser라는 이름을 가진 어노테이션이 생성됨.
}
