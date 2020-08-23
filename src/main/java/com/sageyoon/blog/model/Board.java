package com.sageyoon.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;

	@Lob	//대용량 데이터일 때 사용
	private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0")
	private int count; //조회수

	// EAGER 전략은 무조건 가져오는 것
	@ManyToOne(fetch = FetchType.EAGER) 	// Many = Board, User = One, 한명의 유저는 여러개 게시판 생성 가능
	@JoinColumn(name = "userId")
	private User user;	// DB는 오브젝트를 저장할 수 없어 FK 사용하는데 자바는 오브젝트를 저장할 수 있다.
	
	// 하나의 게시글은 여러개의 답변이 달릴 수 있다.
	// LAZY 전략은 안갸져 와도 되는 경우
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)	// One = Board, Reply = Many, mappedBy는 연관관계의 주인이 아니다 난 FK가 아니다 DB에 컬럼을 만들지 마세요,
	// Reply테이블의 Board(Field)가 FK
	//@JoinColumn(name = "userId")	// 데이터베이스에는 만들어지면 안되기 때문에 필요 없음
	// 단지 Board를 select 할 때  join문으로 값을 얻기 위해 필요한 것
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
