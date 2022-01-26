package com.legacy.posts.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass // Jpa Entity 클래스들이 이 클래스를 상속할 경우 필드들도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) // Audting 기능을 포함
public class BaseTimeEntity {
	
	@CreatedDate // Entity가 생성되어 저장될 때 시간이 자동 저장
	private LocalDateTime createdDate;
	
	@LastModifiedDate // Entitiy 값이 변경될 때 시간이 자동 저장됨
	private LocalDateTime modifiedDate;

}
