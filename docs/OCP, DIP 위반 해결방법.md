# ✅ AppConfig :: OCP, DIP 위반 해결방법

- 별도의 설정 클래스가 구현 객체를 대신 생성하고 주입해줘야 한다

SmsServiceImpl 생성자 주입 코드 추가
![스크린샷 2024-10-30 오후 5 31 41](https://github.com/user-attachments/assets/0a14287d-142c-492b-8d64-2eb3c38309b6)

CustServiceImpl 생성자 주입 코드 추가
![스크린샷 2024-10-30 오후 5 32 00](https://github.com/user-attachments/assets/1e57b91d-4b29-42b3-ad4b-08970e028da5)


SmsFilterImpl 생성자 주입 코드 추가
![스크린샷 2024-10-30 오후 5 31 52](https://github.com/user-attachments/assets/a7ca3dee-ed8f-44af-812f-dfeccf30644b)


- AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
- AppConfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결) 해준다
- **DIP 완성**  : 클라이언트는 이제 인터페이스에만 의존한다. 
	- 추상에만 의존하면된다. 이제 구체 클래스를 몰라도 된다.
	- 클라이언트는 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중한다
- **관심사의 분리** : 객체의 생성과 연결은 AppConfig가 담당한다
	- 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
