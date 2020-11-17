package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    PostsSaveRequestDto(String title, String content, String author){
        System.out.println("==========PostsSaveRequestDto.PostsSaveRequestDto==========");
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        System.out.println("==========PostsSaveRequestDto.toEntity==========");
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
