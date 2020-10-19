# 카카오페이 사전과제  - 카카오페이 뿌리기 기능 
## 목차
- [개발 환경](#개발-환경)
- [빌드 및 실행하기](#빌드-및-실행하기)
- [기능 요구사항](#기능-요구사항)
- [개발 제약사항](#개발-제약사항)
- [해결방법](#해결방법)

---

## 개발 환경
- 기본 환경
    - IDE: IntelliJ IDEA Ultimate
    - OS: Mac OS X
    - GIT
- Server
    - Java8
    - Spring Boot 2.3.4
    - JPA
    - H2
    - Gradle
    - Junit5


## 빌드 및 실행하기
### 터미널 환경
- Git, Java 는 설치되어 있다고 가정한다.

```
$ git clone https://github.com/yeungjunkim/restplustask.git
$ cd restplustask
$ ./gradlew clean build
$ java -jar build/libs/kakaoTask3-0.0.1-SNAPSHOT.jar
```

- 접속 Base URI: `http://localhost:8080`

## 기능 요구사항
### 필수사항


- 뿌리기API를 콜하면 뿌릴 금액, 뿌릴 인원을 요청값으로 받습니다. 
  토큰값을 만들기 전에 랜덤으로 금액을 사람수대로 배분하고 
  임의의 토큰 값을 만듭니다. 
  그런 다음 리턴값으로 TOKEN값을 반환받습니다. 
  
  request(get방식) : http://localhost:8080/givers/50000/3

```
{"token":"9cA"}
```

- 받기 API를 호출하면 token값으로 요청값으로 받습니다. 
  토큰에 해당하는 뿌리기 건 중 비어있는 한건의 금액을 조회한뒤 해당 데이터에 
  받는 사람의 사용자 아이디를 할당하고 그 금액을 응답값으로 내려줍니다. 
  - 뿌리기당 한번만 받을 수 있습니다. 
  - 자신이 뿌리기한 건은 자신이 받을 수 없습니다. 
  - 뿌리기를 호출한 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다. 
  - 뿌린 건은 10분간만 유효합니다. 뿌린지 10분이 지난 요청에 대해서는 받기 실패
    오류가 납니다. 
  - 위에서 발생한 token값을 마지막 입력값으로 넣습니다. 
  
  request(get방식) : http://localhost:8080/rcvmoney/9cA
  
```
{
    "receiverAmt":12189
}
```
- 조회 API를 
  - 뿌리기당 한번만 받을 수 있습니다. 
  - 자신이 뿌리기한 건은 자신이 받을 수 없습니다. 
  - 뿌리기를 호출한 대화방과 동일한 대화방에 속한 사용자만이 받을 수 있습니다. 
  - 뿌린 건은 10분간만 유효합니다. 뿌린지 10분이 지난 요청에 대해서는 받기 실패
    오류가 납니다. 
  - 초기 뿌리기에서 생성한 토큰 값을 마지막부분에 넣습니다. 
  
  request(get방식) : http://localhost:8080/givers/status/9cA
  
```
{
[
  {
    "CREATEDAT": "2020-10-19T10:37:06.353+00:00",
    "GIVERAMT": 50000,
    "RECEIVERTOTAMT": 50000
  },
  [
    {
      "receiverAmt": 11786,
      "receiverXUserID": 11111
    },
    {
      "receiverAmt": 35722,
      "receiverXUserID": 11111
    },
    {
      "receiverAmt": 2492,
      "receiverXUserID": 11111
    }
  ]
]
}
``` 

