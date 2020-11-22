package com.jojoldu.book.springboot;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile(){
        List<String> profiles = Arrays.asList(env.getActiveProfiles()); //env.getActiveProfiles(): 현재 실행중인 activeprofile을 모두 가져온다. 즉 real, oauth, real-db등이 활성화되어있다면 3개 모두 담겨 있음. 여기서 real, real1, real2는 모두 배포에 사용될 profile이라 이중 하나라도 있으면 그 값을 반환하도록 함. 실제로 이번 무중단배포는 real1과 real2만 사용되나 step2를 다시 사용할 수 있으니 real도 남겨둔다.
        List<String> realProfiles = Arrays.asList("real","real1","real2");
        String defaultProfile = profiles.isEmpty()? "default": profiles.get(0);

        return profiles.stream().filter(realProfiles::contains).findAny().orElse(defaultProfile);

    }
}
