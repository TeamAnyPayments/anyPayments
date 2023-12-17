## WE:A(We are Artist!) : 우리동네 아티스트 후원 플랫폼

**앱 하나로 우리 동네 공연 정보를 확인하고, 쉽고 간편하게 후원하는 인디 공연 플랫폼**


## 📜 프로젝트 개요

### **_나랑 같이 우리 동네 공연보러 갈래?_**

- **WE:A(We are Artist)** 는 지역 공연 예술가들의 활동을 지원하고, 관객과 아티스트 간의 연결을 강화하는 인디 공연 앱 플랫폼입니다. 
- WE:A는 사용자에게 주변 지역의 다양한 공연 및 문화 이벤트 정보를 손쉽게 찾아보고, 참여하고 후원할 수 있습니다.
- 나만을 위한 비대면 공연 플랫폼, WE:A로 당신을 초대합니다.

<br/>

## 📃 주요기능

### 1. 공연 정보 검색
- WEA 앱을 통해 사용자는 주변 동네에서 진행되는 다양한 공연과 문화 이벤트 정보를 검색하고 살펴볼 수 있습니다. 편리한 검색 및 필터링 옵션을 통해 사용자는 자신의 관심사에 맞는 이벤트를 찾을 수 있습니다.

### 2. 간편결제(토스 API)를 사용한 공연 후원 시스템
- 오늘, 우리 동네에서 공연하는 아티스트의 정보를 확인하고 앱에서 간편하게 후원을 하실 수 있습니다. 관심 있는 공연에 후원을 하면 당신만을 위한 티켓 발급까지! 특별한 날 소중한 추억을 앱 하나로 간편하게 간직해보세요!

### 3. 아티스트 프로필 확인
- WE:A는 우리 동네 사는 누구나 아티스트가 될 수 있습니다. 간단하게 공연자의 프로필을 작성하고 심사를 요청하시면, 아티스트를 위한 프로필 페이지를 제공하여 관객들과 소통하고 나만의 히스토리를 기록해나갈 수 있습니다. 

### 4. 아티스트 순위 확인
- 오늘은 어떤 아티스트의 공연을 봐야할 지 고민이시라구요? WE:A는 누적된 데이터를 통해 아티스트의 순위 정보를 제공하여 아티스트와 관객이 더 적극적으로 이어질 수 있도록 만남의 장을 조성하고 있습니다. 지금 바로, 오늘의 인기스타가 되어보시는 것은 어떨까요?
<br>

## 🎬 프로젝트 기간

#### 2023. 07. 02. (일) ~ 2023. 11. 05. (일) (18주)

<br/>

## 🛠 프로젝트에 사용된 기술

**Back-end : Spring Boot**
```
- Java 17
- SpringBoot
- Spring Data JPA
- Spring Security
- MYSQL
- Redis
- Json Webtoken
- Lombok
- Tika
- gradle
```

**Android**

```
- Java 8
- Jetpack Compose
- Compose navigation
- Retrofit2
- okHttp3
- Compose Glide (com.github.skydoves:landscapist-glide:1.4.7)
- Naver Map SDK
- Tosspayments SDK
- gradle(Kotlin DSL, kts)
```

**CI/CD**

```
- Jenkins
- Dokcer
- Docker compose
```

<br/>

## 🚩 Project Info

### 1. 프로젝트 아키텍처(Project Architecture)
![프로젝트 아키텍처](./assets/wea_blueprint.png)


### 2. E-R Diagram
![E-R Diagram 보러가기](https://www.notion.so/E-R-Digram-da623c84b64b4db8972168da0e2261e4)

![E-R Diagram](./assets/erd.png)

### 3. 프로젝트 워크스페이스(노션, Notion)
[프로젝트 워크스페이스 보러가기](https://secret-nebula-014.notion.site/WE-A-152d47344b7d449290d44811aee37d5a?pvs=4)

![프로젝트 워크스페이스1](./assets/work1.PNG)
![프로젝트 워크스페이스2](./assets/work2.PNG)


### 4. 요구사항 명세서
[요구사항 명세서 보러가기](https://docs.google.com/spreadsheets/d/1xtZZIdOm18zNBqhUbdQQi8cfbaRAYBCD/edit?usp=sharing&ouid=102208980798385722229&rtpof=true&sd=true)

![요구사항 명세서](./assets/requirements_specification.PNG)


### 5. API 명세서
[API 명세서 보러 가기](https://www.notion.so/API-03cafb39cd8c4175bd26cbf86fc11f44)

![API 명세서](./assets/rest_api_specification.PNG)

### 6. 유스케이스(USE CASE)
[유스케이스 보러 가기](https://www.notion.so/USE-CASE-33c6d180ddf64566a773624e4d864ce3)

![유스케이스](./assets/use_case.png)


### 7. 메뉴 구조도(Menu Structure)
[메뉴 구조도 보러가기](https://www.notion.so/Menu-Structure-1b569cfd18654f418259a2eae92b1936)


![메뉴 구조도](./assets/menu_structure_diagram.png)

### 8. 깃 컨벤션 규칙(Git Convention rules)
[깃 컨벤션 규칙 보러가기](https://www.notion.so/GIT-4c22984719314bdb89e4fad741ed174e)

![깃 컨벤션 규칙](./assets/git_convention_1.PNG)
![깃 컨벤션 규칙](./assets/git_convention_2.PNG)


### 9. 와이어 프레임(Wire Frame)
[와이어 프레임 보러가기](https://www.notion.so/Wire-Frame-b567020010614f229b9b45fc19fd1740)

### 아티스트 와이어 프레임
![와이어 프레임](./assets/wire_frame_artist.png)

### 관객 와이어 프레임
![와이어 프레임](./assets/wire_frame_client.png)


### 10. 사용자 화면(UI, User Interface)
[사용자 화면 보러가기](https://www.notion.so/User-Interface-5742ab1b1c184330a0a9afb09c5d165c)

![사용자 화면](./assets/user_interface.png)

---

### Project Member
- 조원희 (Team Leader, Android Developer)
- 김성태(Android Developer, CI/CD)
- 조은서(BackEnd, Backend Leader),
- 김주하(BackEnd)
