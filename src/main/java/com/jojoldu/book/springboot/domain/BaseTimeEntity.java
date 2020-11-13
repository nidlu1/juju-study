package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//JPA Auditing으로 생성시간, 수정시간 자동화하기.
@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseEntity을 상속할 경우 필드(createDate,modifiedDate)들도 칼럼으로 인식하도록함.
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity클래스에 Auditing기능 추가.
public class BaseTimeEntity {
    @CreatedDate //Entity가 생성되어 저장될 때 시간이 자동저장됩니다.
    private LocalDateTime createDate;

    @LastModifiedDate //조회한 Entity가 값을 변경할 때 시간이 자동저장됩니다.
    private LocalDateTime modifiedDate;
}