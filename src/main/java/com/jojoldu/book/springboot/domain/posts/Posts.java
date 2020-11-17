package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내 모든 필드에 getter메소드를 자동생성.
@NoArgsConstructor //기본생성자 자동추가. public Posts(){}
@Entity //테이블과 링크될 클래스임. 클래스의 카멜식 이름을 언더스코어로 변경함. theCat -> the_cat
public class Posts extends BaseTimeEntity {
    @Id //해당 테이블의 pk를 명시.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk의 생성규칙. GenerationType.IDENTITY 옵션: auto_increment
    private Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼. 굳이 선언 안해도 해당클래스의 필드는 모두 칼럼이 됨. 기본 값 외에 추가로 변경이 필요한 옵션이 있으면 시용.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함.
    public Posts(String title, String content, String author){
        System.out.println("==========Posts.Posts==========");
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        System.out.println("==========Posts.update==========");
        this.title = title;
        this.content = content;
    }
}
