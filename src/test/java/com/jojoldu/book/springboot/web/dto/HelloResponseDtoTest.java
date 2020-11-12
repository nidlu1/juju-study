package com.jojoldu.book.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); // assertThat: assertj라는 테스트검증 라이브러리의 검증 메소드. 검증하고 싶은 대상을 메소드인자로 받음. 메소드체이닝 지원됨.
        assertThat(dto.getAmount()).isEqualTo(amount); //isEqualTo: assertj의 동등비교 메소드. assertThat에 있는 값과 isEqualTo의 값을 비교해 같으면 true반환.
        //그레이들 버전 gradle-4.10-bin.zip 일때만 사용가능한 함수임.
    }
}
