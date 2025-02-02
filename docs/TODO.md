> 🎯 목표 <br>
> - 도메인을 뭘로 할지 고민 *예시 (이니스프리, 인어교주해적단,인터파크) -> 케타포
> - 엑셀 문서를 읽어서 문자 발송하는 배치 프로그램 짜기 <br>

### [고객파트]
- [x] 고객 전화번호를 통해서 고객 id 조회하기 <br>
- [ ] 고객 문자 수신 유형 *(전체허용, 전체거부, 광고거부, 정보허용) <br>
- [x] 고객 등록 페이지 필요<br>
- [ ] 고객 등록 페이지에서 고객 등록이 완료되면 기존 페이지 열리게 하는 방법 HTTP 공부한거 써먹기..
- [ ] 고객 삭제 CustApiController 추가

### [문자파트]
- [ ] #{고객명} 과 같이 동적상용구를 만들어서 문자 내용 바꾸기 <br>
- [ ] 하루에 발송할 수 있는 광고개수 제한하기 <br>
- [ ] 문자 발송 조회 페이지 필요 <br>
- [ ] 문자 발송시 동일 문자 내용에 고객은 리스트로 받을 수 있게 처리 하기 <br> 
- [ ] smstype 처리 어떻게 할지 고민하고 적용하기. 현재 script에 "01" 로 고정되어 있음 <br>

### [시스템파트]
- [ ] 직원만 들어가서 화이트리스트 등록하는 페이지 <br>

### [프론트단]
- [ ] 네비게이션 바 사용하기

<details> 
	<summary><b>🏷️ 24.1.9.</b></summary>
	<div markdown = "1">
		<ul>
            <li> 마크다운보면서 복습 및 기술블로그 포스팅할만한 것들을 기록
            </li>
            <li> 빈 생명주기 콜백 이론 복습
            </li>
		</ul>
	</div>
</details>
<details> 
	<summary><b>🏷️ 24.1.18.</b></summary>
	<div markdown = "1">
		<ul>
            <li> JS+제이쿼리 찾아보면서 기본 페이지 세팅
            </li>
            <li> 전화번호 입력시 빈값인 경우 alert창 뜨게 세팅 
            </li>
            <li> 고객등록페이지 만들고 + ajax 연결
            </li>
		</ul>
	</div>
</details>
<details> 
	<summary><b>🏷️ 24.1.21.</b></summary>
	<div markdown = "1">
		<ul>
            <li> @RestController랑 @Controller 차이를 알 수 있었다
            </li>
            <li> 고객 저장하고 조회하는 기능까지 연결
            </li>
		</ul>
	</div>
</details>
<details> 
	<summary><b>🏷️ 24.1.29.</b></summary>
	<div markdown = "1">
		<ul>
            <li> Swagger 를 사용해 HTTP API 문서화하기
            </li>
            <li> 고객 전체 조회 페이지 생성
            </li>
            <li> 문자 발송 기능 프론트 연결 </li>
		</ul>
	</div>
</details>