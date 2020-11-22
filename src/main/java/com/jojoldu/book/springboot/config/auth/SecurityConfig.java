package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                                                                            //h2-console 화면을 사용하기 위해 해당 옵션들을 disable 합니다.
            .and()
                .authorizeRequests()
                                                                            //URL별 권한 관리를 설정하는 옵션의 시작점. 이게 선언되야 antMatchers 옵션 사용가능
                .antMatchers("/","/css/**","/images/**","/js/**","h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                                                                            // antMatchers: 권한관리대상을 지정하는 옵션. Url. Http메소드별로 관리가능. "/"등 지정된 url은 permitall() 옵션을 통해 전체 열람권한을 주었다. "/api/v1/**" 주소를 가진 api는 user 권한을 가진 사람만 가능하도록 했다.
                .anyRequest().authenticated()
                                    //anyRequest(): 설정된 값들 이외 나머지 URL들을 나타냄. authenticated()을 추가해 나머지 URL은 모두 인증된 사용자들에게만 허용. 즉 로그인 한사람만.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // /profile이 인증없이도 호출할 수 있게 한 제외코드. 10장에서 추가됨.
            .and()
                .logout()
                    .logoutSuccessUrl("/") // 로그아웃 기능에 대한 여러설정의 진입점. 로그아웃 성공시 /주소로 이동
            .and()
                .oauth2Login()
                            //OAuth2 로그인 기능에 대한 여러 설정의 진입점.
                    .userInfoEndpoint()
                            //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정들을 담당.
                        .userService(customOAuth2UserService); //userService: 소셜 로그인 성공시 후속조치를 진행할 UserService 인터페이스의 구현체를 등록.
                                                                // 리소스서버(소셜서비스..)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능을 명시함.


    }
}
