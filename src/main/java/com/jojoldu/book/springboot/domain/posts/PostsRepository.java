package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository; //20201112
import org.springframework.data.jpa.repository.Query; //20201116

import java.util.List;  //20201116
import java.util.stream.Stream;

//entity클래스와 기본repository는 함께 움직여야하므로 도메인패키지에 함께 관리.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query(value = "SELECT s FROM Posts s ORDER BY s.id DESC") //왜 select p이지? s는 안되나? s도 된다. 알리아스만 된다.
    List<Posts> findAllDesc();

}
