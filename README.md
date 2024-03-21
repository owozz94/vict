## [VisitCount] 프로젝트 소개

---

- 블로그 조회수를 원하는대로 올려주는 어플

## 1. 개요

- 기존에 조회수 올려주는 시스템과 달리, 블로그를 이용하는 사람이 웹에 가입하고 결제함으로서 원하는 조회수로 블로그 조회수를 조작할 수 있다.

---

## 2. 기획

1. 사용자는 회원 가입 후 무료 버전(1회) 이용할 수 있다.
2. 가입 후 결제를 하면 원하는 숫자로 조회수 조작이 가능하다.(하루 100 ~ 1000)
3. 일반 회원(USER)/관리자(ADMIN)로 나누어 져있다.

---

## 3. 화면

### 1) 회원 가입

1. 일반 회원 가입
- 이메일, 비밀번호
1. 네이버 회원 가입

### 2) 로그인

- 일반 회원/네이버 회원 로그인시 jwt 이용해 사용자 관리.

---

## 3.1 화면 유효성 검사(Validation)

### 1. 로그인

- 회원 가입이 안되어 있는 경우 : 가입하지 않은 회원입니다.
- 이메일/패스워드가 틀린 경우 :  이메일/비밀번호를 다시 확인해주세요.
- 그 외 : 로그인에 실패했습니다.

### 2. 회원 가입

### 3. 공통

- null 인 경우 : 0     null이 아닌 경우 : 1
- 값이 같은 경우 : 1 값이 다른 경우 : 0

---

## DB Table

- 데이터베이스 테이블

### TB_USER

| Column Name | Data Type | Nullable | Default | Comment |  |
| --- | --- | --- | --- | --- | --- |
| USER_UNIQUE_NUM | NUMBER | o |  | 회원 고유 번호 |  |
| EMAIL | VARCHAR2(100) | x |  | 이메일 |  |
| PWD | VARCHAR2(200) | x |  | 비밀번호 |  |
| NAME | VARCHAR2(20) | x |  | 회원 이름 |  |
| YEAR | VARCHAR2(8) | o |  | 연도 |  |
| GENDER | NUMBER(2,0) | o |  | 성별 |  |
| PHONE_NUM | VARCHAR2(11) | o |  | 핸드폰 번호 |  |
| BLOG_URL | VARCHAR2(100) | o |  | 블로그 주소 |  |
| REG_DATE | DATE | x |  | 회원 가입 날짜 |  |
| LEAVE_DATE | DATE | o |  | 회원 탈퇴 날짜 |  |
| USE_YN | VARCHAR2(2) | x | 1 | 사용여부 |  |
| LOGIN_TYPE | NUMBER(2,0) | x |  | 로그인 유형(1:일반 2: 네이버 ) |  |
| SOCIAL_KEY | VARCHAR2(100) | o |  | 소 로그인 키 |  |
| SALT | VARCHAR2(100) | x |  | SALT |  |

---

[VICT 개발일지](https://www.notion.so/269f9229b7f34fa0bc314c1ba4ab312e?pvs=21)
