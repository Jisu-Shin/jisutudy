
# 스프링 핵심원리 개인 공부
---

### [1. 문자 발송 서비스 설계 및 구현](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/문자%20발송%20서비스%20설계%20및%20구현.md)
### [2. OCP, DIP 위반 해결방법](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/OCP,%20DIP%20위반%20해결방법.md)
### [3. 스프링으로 전환하기](https://github.com/Jisu-Shin/jisutudy/blob/main/docs/스프링으로%20전환하기.md)


# 🚫 문제-해결
<details> 
	<summary><b>🔍 1) 광고 필터링 테스트 중 저장된 문자 내역이 조회가 되지 않음</b></summary>
	<div markdown = "1">
		<ul>
		<li>원인 : MemorySmsRepository에 메모리 저장 객체가 private final 로 선언되어 있었다</li>
			<ul>
			<li>final 키워드는 선언된 대상의 변경을 금지한다. 변수->상수 / 메소드->오버라이딩금지 / 클래스 -> 상속금지 </li>
			<li>static 키워드는 모든 인스턴스에서 공유되므로 한번만 메모리에 로딩한다.</li>
			</ul>
		<li>해결책 : private static 으로 선언했다</li>
		</ul>
	</div>
</details>

