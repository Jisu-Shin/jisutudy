# 💌 OO-SMS (Object-Oriented SMS)

OO-SMS는 공연을 예매한 고객들에게 맞춤형 문자를 발송하기 위한 MSA 기반의 메시지 전송 시스템입니다.  
이 프로젝트는 객체지향적인 코드 설계와 MSA 구조의 실전 적용을 목표로 시작되었습니다.

---

## 🧩 주요 기능

- 고객 등록 및 수정
- 공연 등록 및 수정
- 고객의 공연 예매
- 고객 대상 문자 발송
- 문자 템플릿 및 템플릿 변수 관리

---

## 🧱 기술 스택

- Java 17, Spring Boot 3.3
- Spring Cloud (Gateway, Config Server, Eureka) (진행 중)
- Spring Data JPA, QueryDSL,  H2 Database
- Mustache (웹 프론트)
- Docker, Docker Compose (진행 중)
- Swagger (OpenAPI 기반 API 문서 자동 생성)

---

## 🏗️ 아키텍처

### 전체 구성도

![architecture](./docs/images/architecture.png) <!-- ← 생성한 아키텍처 다이어그램 이미지 위치에 맞게 -->

| 서비스 이름       | 설명                              |
|------------------|-----------------------------------|
| `config-server`  | 공통 설정 파일 관리                |
| `eureka-server`  | 서비스 등록 및 디스커버리           |
| `gateway`        | 진입점, 라우팅 처리, Mustache 웹 포함 |
| `cust-service`   | 고객 등록 및 정보 관리              |
| `booking-service`| 공연 등록 및 예매 처리              |
| `sms-service`    | 문자 발송, 템플릿 및 변수 관리       |

---

## 📦 폴더 구조 (Mono 레포 예시)

```bash
OO-SMS/
├── booking-service/
├── cust-service/
├── sms-service/
├── gateway/
├── config-server/
├── eureka-server/
├── docker-compose.yml
└── README.md
