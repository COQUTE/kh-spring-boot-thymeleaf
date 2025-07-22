package edu.kh.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// SpringBoot 프로젝트 생성 시 기본으로 만들어지는 테스트 클래스

@SpringBootTest
// @SpringBootTest : SpringBoot Application Context를 로드해서 테스트한다는 의미
// Application Context : Spring이 실행될 때 자동으로 만들어지는 애플리케이션 전반의
// 객체(Bean)을 관리하는 컨테이너
// == 스프링공장(필요한 곳에 IOC/DI 하고 생명주기 관리까지 하는 공장)
class BoardProjectBootApplicationTests {

	@Test
	void contextLoads() {
		/* 이 테스트가 하는 일 : 해당 애플리케이션이 정상적으로 부팅 가능한지 테스트
		 *
		 * <정상>
		 * - 애플리케이션 컨텍스트가 문제없이 로드됨
		 * - 의존성, 설정 파일, 컴포넌트 스캔, 빈 생성 등이 정상
		 *
		 * <실패>
		 * 잘못된 @Autowired (Bean이 없는데 주입 시도)
		 * - application.properties 파일에 치명적 오류 등
		 *
		 */
	}

}
