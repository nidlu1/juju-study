package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//entity클래스와 기본repository는 함께 움직여야하므로 도메인패키지에 함께 관리.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
