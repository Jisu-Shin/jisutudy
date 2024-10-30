> 이 포스팅은 인프런에 있는 김영한 강사님의 [스프링 핵심 원리](https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%ED%95%B5%EC%8B%AC-%EC%9B%90%EB%A6%AC-%EA%B8%B0%EB%B3%B8%ED%8E%B8) 강의를 들으며 개인 공부를 한 것입니다.
> 소스코드는 제가 직접 작성하였습니다. (참고 : [깃허브주소](https://github.com/Jisu-Shin/jisutudy))

# ✅ AppConfig :: OCP, DIP 위반 해결방법

- 별도의 설정 클래스가 구현 객체를 대신 생성하고 주입해줘야 한다

SmsServiceImpl 생성자 주입 코드 추가
`SmsServiceImpl`는 `SmsRepository`, `CustRepository`, `SmsFilter`에 의존한다 <br>
![스크린샷 2024-10-30 오후 5 31 41](https://github.com/user-attachments/assets/0a14287d-142c-492b-8d64-2eb3c38309b6)

CustServiceImpl 생성자 주입 코드 추가 
`CustServiceImpl`은  `CustRepository`에 의존한다 <br>
![스크린샷 2024-10-30 오후 5 32 00](https://github.com/user-attachments/assets/1e57b91d-4b29-42b3-ad4b-08970e028da5)

SmsFilterImpl 생성자 주입 코드 추가 
`SmsFilterImpl`은 는 `smsRepository`, `custRepository`에 의존한다<br>
![스크린샷 2024-10-30 오후 5 31 52](https://github.com/user-attachments/assets/a7ca3dee-ed8f-44af-812f-dfeccf30644b)

```java
public class AppConfig {  
    public SmsService smsService() {  
        return new SmsServiceImpl(  
                getSmsRepository(),  
                getCustRepository(),  
                smsFilter()  
        );  
    }  
  
    public CustService custService() {  
        return new CustServiceImpl(getCustRepository());  
    }  
  
    public SmsFilter smsFilter() {  
        return  new SmsFilterImpl(  
                getSmsRepository(),  
                getCustRepository()  
        );  
    }  
  
    public CustRepository getCustRepository() {  
        return new MemoryCustRepository();  
    }  
  
    public SmsRepository getSmsRepository() {  
        return new MemorySmsRepository();  
    }  
  
}
```
- AppConfig는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
- AppConfig는 생성한 객체 인스턴스의 참조를 생성자를 통해서 주입(연결) 해준다
- **DIP 완성**  : 클라이언트는 이제 인터페이스에만 의존한다. 
	- 추상에만 의존하면된다. 이제 구체 클래스를 몰라도 된다.
	- 클라이언트는 이제부터 의존관계에 대한 고민은 외부에 맡기고 실행(자신의 역할)에만 집중한다
- **관심사의 분리** : 객체의 생성과 연결은 AppConfig가 담당한다
	- 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.

# ✅ 좋은 객체 지향 설계의 5가지 원칙
### SRP 단일 책임 원칙
- 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당
- 클라이언트 객체는 실행하는 책임만 담당

### DIP 의존관계 역전 원칙
- 클라이언트 코드가 추상화 인터페이스만 의존하도록 코드 변경
- AppConfig가 클라이언트 코드에 의존관계 주입

### OCP 개방 폐쇄 원칙
- 다형성을 사용하고 클라이언트가 DIP를 지킴
- 애플리케이션을 사용 영역과 구성 영역으로 나눔
- 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀 있다

# ✅ IoC , DI , 컨테이너

### 제어의 역전
- 이전에는 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행했다. 구현 객체가 프로그램의 제어 흐름을 스스로 조종했다
- 5가지 원칙을 지키고 난 후, 프로그램에 대한 제어 흐름에 대한 권한은 모두 AppConfig가 가지고 있다
- 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전이라 한다.

### 프레임워크 vs 라이브러리
- 프레임워크 : 내가 작성한 코드를 제어하고 대신 실행하면 그것은 프레임워크가 맞다 (Junit)
- 라이브러리 : 내가 작성한 코드가 직접 제어의 흐름을 담당한다면 그것은 라이브러리다.

### DI ; 의존관계 주입
- 애플리케이션 실행 시점(런타임)에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결되는 것을 의존관계 주입이라 한다.
- 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결된다
- 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다 (AppConfig에서 변경가능)