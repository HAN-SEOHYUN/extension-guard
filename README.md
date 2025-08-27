# 📂 파일 확장자 차단

**파일 확장자를 차단/관리하는 웹 페이지** 구현

🌐 배포 주소: [https://flow.seohyun.site](https://flow.seohyun.site)

---

## 기능
### ✅ 기본요건
* 고정 확장자는 차단을 자주하는 확장자 리스트이며, default는 uncheck
* 고정 확장자를 check or uncheck를 할 경우 db에 저장됨
* 확장자 최대 입력 길이는 20자리  
* 추가버튼 클릭시 db 저장되며, 아래쪽 영역에 표현됨
* 커스텀 확장자는 최대 200개까지 추가가 가능  
* 확장자 옆 X를 클릭시 db에서 삭제  
* 등록된 커스텀 확장자 개수 표시: `n / 200`  

---

### ➕ 추가 요건
- 확장자는 **영문 + 숫자만 허용** (`한글, 특수문자, 공백 불가`)  
- **고정 확장자**는 커스텀 확장자로 등록 불가  
- **중복 등록 방지**: 이미 등록된 커스텀 확장자는 다시 추가 불가  
- **대소문자 구분 제거**: 예) `EXE` 입력 시 `exe`와 동일하게 처리  

---

## 🛠️ 기술 스택
- **Backend**: Spring Boot `3.5.0`, Spring Web
- **Frontend**: Thymeleaf, jquery
- **Database**: MySQL  
- **기타**: Lombok

---

## 🖥️ 개발 환경
- JDK: **Java 21**  
- Spring Boot: **3.5.0**  
- Build Tool: **Gradle**

---

## ☁️ 배포 환경
- **AWS EC2**  
- **Docker**: 단일 EC2 인스턴스에서  
  - 애플리케이션 컨테이너 1개  
  - 데이터베이스 컨테이너 1개  
  → 총 2개의 컨테이너로 운영  
- **Nginx**: Reverse Proxy  
- **HTTPS 적용** (Let’s Encrypt)  
- **Route 53**: 도메인 연결 관리  
- **도메인 연결**: [https://flow.seohyun.site](https://flow.seohyun.site)  
