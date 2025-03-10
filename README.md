# ✏️ 스프링 핵심원리 개인 공부

1. [문자 발송 서비스 설계 및 구현](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/spring/1.%20문자%20발송%20서비스%20설계%20및%20구현.md)
2. [OCP, DIP 위반 해결방법](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/spring/2.%20OCP,%20DIP%20위반%20해결방법.md)
3. [스프링으로 전환하기](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/spring/3.%20스프링으로%20전환하기.md)
4. [싱글톤 컨테이너](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/spring/4.%20싱글톤%20컨테이너.md)
5. [컴포넌트 스캔](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/spring/5.%20컴포넌트%20스캔.md)
6. [의존관계 자동주입](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/spring/6.%20의존관계%20자동주입.md)
<br><br>
# ✏️ 스프링 부트와 AWS로 혼자 구현하는 서비스

1. [테스트 코드 작성하기](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/springboot+AWS/1.%20테스트%20코드%20작성하기.md)
2. [JPA로 데이터베이스 다루기](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/springboot+AWS/2.%20JPA로%20데이터베이스%20다루기.md)
<br><br>
# 🚫 문제-해결
<details> 
	<summary><b>🔍 1) 광고 필터링 테스트 중 저장된 문자 내역이 조회가 되지 않음</b></summary>
	<div markdown = "1">
		<ul>
		<li>원인 : MemorySmsRepository에 메모리 저장 객체가 private final 로 선언되어 있었다. 저장하는 거랑 조회하는 거랑... 다른 객체를 보고 있었다는 것...?</li>
			<ul>
			<li>final 키워드는 선언된 대상의 변경을 금지한다. 변수->상수 / 메소드->오버라이딩금지 / 클래스 -> 상속금지 / 객체 -> 주소재할당금지 </li>
			<li>static 키워드는 모든 인스턴스에서 공유되므로 한번만 메모리에 로딩한다.</li>
			</ul>
		<li>해결책 : private static 으로 선언했다</li>
		</ul>
	</div>
</details>
<details> 
	<summary><b>🔍 5) NoSuchBeanDefinitonException</b></summary>
	<div markdown = "1">
	<img src ="https://github.com/user-attachments/assets/0d7a47b7-9e69-4fec-b983-513bcd884f12">
		<ul>
		<li>원인 : 스프링컨테이너에서 빈 이름을 찾을때 custService로 찾고 있었다</li>
		<li>해결책 : 빈이름 파라미터를 빼고 타입으로만 스프링빈을 찾게 코드를 수정했다</li>
			<ul>
				<li>또 다른 해결책 : value를 이용해 빈이름을 지정해준다 @Component("smsFilter") </li>
			</ul>
		</ul>
	</div>
</details>
<details> 
	<summary><b>🔍 @Controller 와 @RestController 차이</b></summary>
	<div markdown = "1">
1. **`@Controller`**
    - 웹 애플리케이션에서 뷰(HTML)를 반환할 때 사용됩니다.
    - `Model` 객체를 사용해 데이터를 뷰로 전달하고, 메서드의 반환값은 뷰의 이름이 됩니다.
    - 주로 Mustache, Thymeleaf와 같은 템플릿 엔진과 함께 사용됩니다.

2. **`@RestController`**
    - REST API를 작성할 때 사용됩니다.
    - `@Controller`에 `@ResponseBody`를 추가한 형태로, 메서드의 반환값이 JSON, XML 같은 데이터로 변환되어 클라이언트에 전송됩니다.
    - 뷰를 렌더링하지 않습니다.

**사용 예시**:
- 웹 페이지 렌더링 → `@Controller`
- 데이터 API 응답 → `@RestController`
	</div>
</details>
