package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.samskivert.mustache.Mustache;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor //20201116 postsService 생성자.
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession; // oauth2 로그인을 위해 추가.
    
    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("posts",postsService.findAllDesc());
        
        //20201117 추가: oauth2 구글 로그인을 위한 코드.
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null)
            model.addAttribute("userName",user.getName());
        // 끝
        return "index";
    }

    @GetMapping("/test")
    public String root(Model model)
    {
        model.addAttribute("title", "타이틀 테스트");
        model.addAttribute("msg", "Hello!!");

        return "test/index";
    }


    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}

