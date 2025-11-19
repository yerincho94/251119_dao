# 251119_dao 프로젝트 요약

## 1. 프로젝트 개요
이 프로젝트는 Spring MVC 프레임워크를 사용하여 기본적인 웹 애플리케이션을 구축하고, 사용자 인증(회원가입 및 로그인) 기능을 구현하는 실습입니다.

## 2. 프로젝트 구조
프로젝트는 역할에 따라 다음과 같은 패키지로 구성되어 있습니다.
- `kr.java.dao.config`: Spring 설정 관련 클래스
- `kr.java.dao.controller`: 웹 요청을 처리하는 컨트롤러
- `kr.java.dao.service`: 비즈니스 로직을 처리하는 서비스
- `kr.java.dao.model`: 데이터 관련 클래스 (DAO, DTO, Entity)
- `kr.java.dao.util`: 데이터베이스 연결 등 유틸리티 클래스

## 3. 주요 기능 및 구현 내용

### 3.1. Spring MVC 설정 (`WebConfig.java`)
- `@Configuration`, `@EnableWebMvc`, `@ComponentScan` 애노테이션을 사용하여 Spring MVC 설정을 Java 코드로 구현했습니다.
- `configureViewResolvers` 메서드를 오버라이드하여 JSP 파일의 위치와 확장자를 설정했습니다. (`/WEB-INF/views/`, `.jsp`)

### 3.2. 사용자 인증 (`AuthController`, `UserAccountService`)
- **회원가입**:
    - `/signup` GET 요청 시 회원가입 페이지를 보여줍니다.
    - `/signup` POST 요청 시 `UserAccountDTO`로 사용자 정보를 받아 `UserAccountService`의 `signup` 메서드를 호출하여 데이터베이스에 저장합니다.
- **로그인**:
    - `/login` GET 요청 시 로그인 페이지를 보여줍니다.
    - `/login` POST 요청 시 `UserAccountDTO`로 사용자 정보를 받아 `UserAccountService`의 `login` 메서드를 호출하여 인증을 수행합니다.
    - 로그인 성공 시 세션에 사용자 이름(`username`)을 저장하고 메인 페이지로 리다이렉트합니다.
    - 실패 시 로그인 페이지로 다시 리다이렉트합니다.

### 3.3. 데이터베이스 연동 (`UserAccountDAOImpl`, `DBUtil`)
- **DAO (Data Access Object)**:
    - `UserAccountDAO` 인터페이스를 정의하고, `UserAccountDAOImpl` 클래스에서 실제 데이터베이스 작업을 구현했습니다.
    - `PreparedStatement`를 사용하여 SQL Injection 공격을 방지했습니다.
    - `insertUserAccount`: 새로운 사용자를 `USER_ACCOUNT` 테이블에 추가합니다.
    - `selectByUsername`: `username`으로 사용자를 조회합니다.
- **데이터베이스 연결**:
    - `DBUtil` 클래스를 만들어 데이터베이스 연결을 위한 `Connection` 객체를 생성하는 기능을 구현했습니다.
    - `dotenv` 라이브러리를 사용하여 `.env` 파일로부터 데이터베이스 URL, 사용자 이름, 비밀번호를 안전하게 불러옵니다.
    - `Class.forName("com.mysql.cj.jdbc.Driver")`를 통해 MySQL JDBC 드라이버를 로드합니다.

### 3.4. 데이터 모델 (`UserAccountDTO`, `UserAccount`)
- **`UserAccountDTO`**: Controller와 Service 계층 간의 데이터 전송을 위해 사용됩니다.
- **`UserAccount`**: Service와 DAO 계층 간의 데이터 전송 및 데이터베이스 테이블과의 매핑을 위해 사용됩니다.

## 4. 결론
이번 실습을 통해 Spring MVC의 기본적인 구조와 요청 처리 흐름을 이해하고, JDBC를 이용한 데이터베이스 연동 및 계층형 아키텍처(Controller-Service-DAO)를 적용하여 사용자 인증 기능을 구현하는 방법을 학습했습니다.
