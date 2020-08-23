package com.sageyoon.blog.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.usertype.UserType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM -> Jave,다른언어 포함 Object -> 테이블로 매핑해주는 기술 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder	// 빌더 패턴
@Entity 	// User 클래스가 스프링이 읽어 MySQL에 테이블이 생성이 된다. 제일 아래 있는게 좋음
//@DynamicInsert // Insert할 때 null인 필드 제외 시킨다.
public class User {

		@Id	//primary key
		@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
		private int id;	// 시퀀스, auto increment
		
		@Column(nullable = false, length = 30, unique = true)
		private String username;
		
		@Column(nullable = false, length = 100)
		private String password;
		
		@Column(nullable = false, length = 50)
		private String email;
		
		// @ColumnDefault("user")
		// DB는 Role Type이라는게 없어 어노테이션으로 알려줘야한다.
		@Enumerated(EnumType.STRING)
		private RoleType role;	// Enum을 쓰는게 좋다. //admin, user, manager
		
		//@CreationTimestamp	// 시간 자동 입력
		@CreationTimestamp
		private Timestamp createDate;
		
		
}
