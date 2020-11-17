package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
/*
 * User 클래스를 이용하지 않고 SessionUser Dto를 생성한 이유.
 * 만약 User 클래스를 그대로 사용하면 다음에러가 발생함. : Failed to convert from type [java.lang.Object] to type [byte[]] for value 'com.jujuldu.book.springboot.domain.user.User@4a43d6
 * 뜻: 세션에 저장하기 위해 User클래스에 세션을 저장하려고 하니, 'User클래스에 직렬화를 구현하지 않았다'는 의미의 에러
 * User클래스에 직렬화코드를 넣기엔, 이 클래스가 엔티티라서 언제 다른 엔티티와 관계가 형성 될 지 모른다.
 * 예를 들어 @OneToMany, @ManyToMany 등 자식 엔티티를 갖고 있으면 직렬화대상에 자식들까지 포함되니 성능이슈, 부수효과가 발생할 확률이 높으니 차라리 직렬화기능을 가진 세션Dto를 하나 추가로 만드는게 운영, 유지보수에 많은 도움이 됨.
 */
