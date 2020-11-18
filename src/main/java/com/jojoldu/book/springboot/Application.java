package com.jojoldu.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//@EnableJpaAuditing // JPA Auditing 활성화// 5.7 시큐리티에서 분리함. JpaConfig에 생성
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        System.out.println("==========Application.main==========");
        SpringApplication.run(Application.class, args);
    }

}
        // application.properties
        //spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect -> sql문
        // spring.h2.console.enabled=true -> http://localhost:8080/h2-console
        //     jdbcUrl= jdbc:h2:mem:testdb

