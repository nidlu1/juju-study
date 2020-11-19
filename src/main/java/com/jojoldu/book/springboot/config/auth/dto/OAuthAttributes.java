package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.Role;
import com.jojoldu.book.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture){
        System.out.println("==========OAuthAttributes.OAuthAttributes==========");
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes){
        System.out.println("==========OAuthAttributes.of==========");
        //구글인지 네이버인지 판단한다.
        if ("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }else
            
        return ofGoogle(userNameAttributeName, attributes);
    }
    //of(): OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야 함.

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
        System.out.println("==========OAuthAttributes.ofGoogle==========");
        return OAuthAttributes.builder()
                .name( (String) attributes.get("name") )
                .email( (String) attributes.get("email") )
                .picture( (String) attributes.get("picture") )
                .attributes( attributes )
                .nameAttributeKey( userNameAttributeName )
                .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        System.out.println("==========OAuthAttributes.ofNaver==========");
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        System.out.println("======================ofNaver리턴값: "+OAuthAttributes.builder()
                .name( (String) attributes.get("name"))
                .email( (String) attributes.get("email"))
                .picture( (String) attributes.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build());

        return OAuthAttributes.builder()
                .name( (String) attributes.get("name"))
                .email( (String) attributes.get("email"))
                .picture( (String) attributes.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity(){
        System.out.println("==========OAuthAttributes.toEntity==========");
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
    //toEntity(): User 엔티티를 생성. OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때.
    // 가입할 때 기본권한을 GUEST로 주기 위해 role 빌더 값에는 Role.GUEST를 사용.
    //OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성. User클래스를 이용하지 않고 따로 생성하는 이유는 SessionUser에서 설명.
}
