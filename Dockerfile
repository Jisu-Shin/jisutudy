# Java 17 버전 기준 (본인 프로젝트 Java 버전에 맞게 수정)
FROM openjdk:17-jdk-slim

# Jar 파일을 컨테이너 안에 복사
COPY build/libs/jisutudy-0.0.1-SNAPSHOT.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
