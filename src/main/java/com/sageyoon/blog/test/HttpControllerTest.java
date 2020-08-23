package com.sageyoon.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

	private static final String TAG ="HttpControllerTest: ";
	
	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다.
	// http://localhost:8080/http/get(select)
	//	@GetMapping("/http/get")
	//	public String getTest(@RequestParam int id, @RequestParam String username) {
	//		return "get 요청" + id +", " + username;
	//	}
	
	//	@GetMapping("/http/get")
	//	public String getTest(Member m) {	//id=1&username=ssar&password=1234 member에 넣어주는 것도 spring이 함
	//		return "get 요청" + m.getId() +", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	//	}
	
	@GetMapping("/http/lombok")
	public String lombokTest() {	//id=1&username=ssar&password=1234 member에 넣어주는 것도 spring이 함
//		Member m = new Member(1, "ssar","1234", "email");
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();	// builder 사용하면 위에 코드 처럼 순서 맞춰도 됨
		System.out.println(TAG+"getter: " + m.getId()) ;
		m.setId(5000);
		System.out.println(TAG+"setter: " +m.getId() ) ;
		return "lombok test 완료";
	}
	
	// http://localhost:8080/http/post(insert)
	//	@PostMapping("/http/post")
	//	public String postTest(Member m) {
	//		return  "post 요청"+ m.getId() +", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	//	}
	
	//	@PostMapping("/http/post")	// mime 타입이 text/plain
	//	public String postTest(@RequestBody String text) {
	//		return  "post 요청"+ text;
	//	}
	
	@PostMapping("/http/post")	// mime 타입이 application/json	// spring에 MessageConvert가 자동으로 변환해줌
	public String postTest(@RequestBody Member m) {
		return  "post 요청"+ m.getId() +", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	// http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return  "put 요청"+ m.getId() +", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
	}
	
	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return  "delete 요청";
	}
	
}
