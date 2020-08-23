package com.sageyoon.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일 리턴 기본 경로 : src/main/resources/static
		// 리턴명: /home.html
		// 풀경로: src/main/resources/static/home.html
		// static 폴더 아래에는 브라우저가 인식할 수 있는 정적 파일만 넣을 수 있음
		// JSP파일은 동적이기 때문에 못찾음
		// src>main> new folder로 webapp/WEB-INF/views 경로 만들어
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix: /WEB-INF/views
		// suffix: .jsp
		// 풀네임 : /WEB-INF/views/test/jsp
		return "/test";
	}
}
