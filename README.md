# 패스트 캠퍼스 한번에 끝내는 Java/Spring 웹 개발 마스터 초격차 패키지 Online
패스트 캠퍼스 강의 중 IntelliJ가이드 부분의 TodoList Server를 만드는 강의를 정리한 내용입니다.

### 인텔리제이 실습 - To do List 구현하기

| 번호 | 필요기능                               |
| ---- | -------------------------------------- |
| 1    | Todo 리스트 목록에 아이템을 추가       |
| 2    | Todo 리스트 목록 중 특정 아이템을 조회 |
| 3    | Todo 리스트 전체 목록을 조회           |
| 4    | Todo 리스트 목록 중 특정 아이템을 수정 |
| 5    | Todo 리스트 목록 중 특정 아이템을 삭제 |
| 6    | Todo 리스트 전체 목록을 삭제           |

* API 스펙 문서

| method | endpoint | 기능             | request                                    | response                                                     |
| ------ | -------- | ---------------- | ------------------------------------------ | ------------------------------------------------------------ |
| POST   | /        | todo 아이템 추가 | {<br />"title": "자료구조 공부하기"<br />} | {<br />"id": 17,<br />"title": "자료구조 공부하기",<br />"order": 0,<br />"completed": false,<br />"url": "http://localhost:8080/17"<br />} |
|        |          |                  |                                            |                                                              |
|        |          |                  |                                            |                                                              |
|        |          |                  |                                            |                                                              |
|        |          |                  |                                            |                                                              |
|        |          |                  |                                            |                                                              |

​	API 스펙 문서가 있으면 Front 개발자와 협업할때 매우 유용하당





---

인텔리제이에서 롬복은 바로 사용 불가 몇가지 설정을 해줘야합니다.

1. 롬복 플러그인 설치

   Preferences - Plugins - lombok 검색 - Install 후 인텔리제이 재 실행

2. Preferences - Build, Execution, Deployment - Compiler - Annotation Processors - Enable annotation processing 체크 - Apply

API 서버를 만들때 Model이 정의 되어야합니다. 여기서는 크게 3가지(TodoEntity, TodoRequest, TodoResponse)를 만들었습니다.

Spring에서 Repository는 저장소가 아니라 데이터를 주고받는 방식인 인터페이스 를 뜻합니다.

서비스는 우리가 구현해야할 구체적인 기능들을 포함하기 때문에 기능 명세표를 다시 보고 필요한 로직이 뭐가 있을지 볼 수 있다.

컨트롤러는 서버에 들어오는 요청을 받는 부분, 요청에 따라서 어떤 작업을 처리하고 어떤 응답을 할지 구현한다. API 문서를 보면서 구현한다

테스트에서 Mock객체를 사용할 때 쓰는 어노테이션은 `@ExtendWith(MockitoExtension.class)` 을 붙여야 한다.

Mock 객체를 쓰는 이유는 외부시스템에 의존하지 않고 자체 테스트를 실행할 수 있어야 하기 때문에 Mock을 사용한다. 

Unit 테스트는 네트워크나 데이터베이스가 연결이 안된다고 해서 Unit 테스트도 실행이 불가능 하면 안되기 때문에 Mock을 사용해준다.

실제 데이터베이스를 사용하게 되면는 테스트를 하면서 값이 삭제되거나 수정 추가되는 경우가 많은데 DB에는 민감한 정보가 포함되어 있는 경우가 많고 서비스에서 사용중인 데이터가 함부로 변경되면 큰일이 난다. 테스트를 실행할때도 실제 DB와 연결하지는 않는다.

TodoRequest 자체로는 request body에 넣을 수가 없어서 ObjectMapper를 이용해 body에 넣어줌

### 디버깅의 이해

디버깅 : 내가 작성한 코드에 문제가 발생했을 때 문제를 찾는 과정

Step into : 메소드 안으로 들어가서 메소드를 한줄씩 실행

Step over : 메소드 안으로 들어가지 않음

Force Step into : 강제로 메소드안으로 들어감

Step out : 현재 함수를 호출한 부분으로 돌아감

