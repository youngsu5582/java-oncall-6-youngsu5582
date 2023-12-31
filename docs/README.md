# java-oncall

## 개발 기능 목록

### 배정할 월 과 시작 요일을 입력한다.

#### 배정할 월은 1월 부터 12월 까지 있다.

- 2월달은 항상 28일 까지 있다.

- 월은 숫자로만 입력한다.

#### 시작 요일은 월요일 부터 일요일 까지 있다.

- 요일은 문자로만 입력한다.

올바르지 않게 입력할 경우 "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." 라는 예외 메시지를 출력한다.

### 평일 비상 , 휴일 비상 근무자 명단을 입력한다.

- 최소 5명 , 최대 35명 까지 입력받는다.

- 평일 근무자와 , 휴일 근무자는 동일해야 한다.

- 각 근무자의 닉네임은 중복되면 안된다.

- 각 근무자의 닉네임은 최대 5자여야 한다.

올바르지 않게 입력할 경우 "[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요." 라는 예외 메시지를 출력한다.

### 비상 근무표를 출력한다.

- 평일은 휴일을 제외한 나머지를 의미한다.

- 휴일은 토요일 , 일요일 , 법정공휴일을 의미한다.

- 특정 근무자가 , 2일 연속 근무할 수 없다.

- 휴일 근무 후 , 평일 겹칠 시 평일 다음 근무자와 교대한다.

- 평일 근무 후 , 휴일 겹칠 시 휴일 다음 근무자와 교대한다.

## 🕶️ 구현 후 체크리스트

##### ✅ System.exit() 를 호출하지 않는가?

##### ✅ 프로그램 구현 완료 시 ApplicationTest 의 모든 테스트가 성공하는가?

##### ✅ build.gradle 및 외부 라이브러리를 사용하지 않았는가?

##### ✅ `camp.nextstep.edu.missionutils`에서 제공하는 `Randoms` 및 `Console` API를 사용하여 구현했는가?

##### ✅ Java 코드 컨벤션 가이드 준수해 프로그래밍 했는가?
