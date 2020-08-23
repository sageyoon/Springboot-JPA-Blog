package com.sageyoon.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Getter
//@Setter
@Data	// Getter Setter 동시에 만들어줌
@NoArgsConstructor // 빈 생성자
public class Member {

	private int id;
	private String username;
	private String password;
	private String email;
	
	
	@Builder
	public Member(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
}
