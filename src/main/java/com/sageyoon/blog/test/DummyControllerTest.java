package com.sageyoon.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sageyoon.blog.model.RoleType;
import com.sageyoon.blog.model.User;
import com.sageyoon.blog.repository.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

// html 파일이 아니라 Data를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTest {

	// DummyControllerTest가 메모리에 뜰 때 UserRepository도 메모리에 뜬다
	// 의존성 주입
	@Autowired
	private UserRepository userRepository;
	
	//삭제
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch (Exception e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}
		return "삭제되었습니다." + id;
	}
	
	// save는 id를 전달하지 않으면 insert해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update해주고
	// save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
	// email, password 업데이트
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {	//json데이터를 요청 -> java object로 변환하여 요청 (messageConverter의 jackson라이브러리 그때 필요한게 requestbody)
	
		System.out.println("id " + id);
		System.out.println("password " + requestUser.getPassword());
		System.out.println("email " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		//DB에 조회한 user로 password, email 수정
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		// 더티체킹: update할 때는 save를 사용하지 않고 transactional을 사용하여 함수 종료 시 자동 commit되게 한다
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건에 데이터를 리턴받아 볼 예정
	// http://localhost:8000/blog/dummy/user?page=0
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable){
		Page<User> pagingUser = 	userRepository.findAll(pageable);
		
		//		if(pagingUser.isLast()) {
		//			
		//		}
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	// {id} 주소로 파라메터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// user/4를 찾으면 데이터베이스에서 못찾아오게 되면 user가 null이 된다.
		// 그럼 문제가 있으니 optional로 User 객체를 찾아와 null인지 아닌지 판단
		// User user = userRepository.findById(id).get(); // 해당 객체가 무조건 있을 경우
		//		User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
		//			@Override
		//			public User get() {
		//				return new User();
		//			}
		//		});	// 없으면 객체 새로 만들어 리턴
		// 아래 방식 선호함
		//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		//		@Override
		//		public IllegalArgumentException get() {
		//			return new IllegalArgumentException("해당 유저는 없습니다. id: " + id);
		//			}
		//		});
		
		// 람다식
		User user = userRepository.findById(id).orElseThrow(()->{
			return new  IllegalArgumentException("해당 유저는 없습니다. id: " + id);
		});
		
		// 요청은 웹 브라우저
		// user 객체 = 자바 오브젝트
		// 변환(웹브라우저가 이해할 수 있는 데이터) -> json (GSON 라이브러리)
		// 스프링부트 = MessageConvert가 응답시에 자동 작동됨
		// 만약에 자바 오브젝트 리턴하게 되면 MessageConvert가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
		
	}
	
	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username, password, email (데이터 가지고 요청)	
	@PostMapping("/dummy/join")
	public String join(User user) {	
		// key = value (약속된 규칙) x-www-form-unlencoded 로 mime타입 설정하면 spring 이 함수의 파라메터로 파싱해서 집어 넣어준다
		System.out.println("username" + user.getUsername());
		System.out.println("password" + user.getPassword());
		System.out.println(" email" +  user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
