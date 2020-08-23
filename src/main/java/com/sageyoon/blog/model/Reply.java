package com.sageyoon.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reply {

	@Id	//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;	// 시퀀스, auto increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	// 어느 게시물에 있는 답변인지
	// 하나의 게시물에는 여러개의 답변이 들어갈 수 있다.
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
	// 하나의 유저는 여러개의 답변을 달 수 있다.
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
