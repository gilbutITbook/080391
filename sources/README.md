# 한글판 소스코드 예제 

이 소스코드는 책에 나온 소스코드를 오류 표시 없이 살펴보기 위한(실행은 불가능함) 최소한의 코드 집합을 구현해 둔 것입니다.
컴파일이 되는 것만 확인할 수 있고 실제 동작은 확인할 수 없습니다. 


# 2장 - src/main/java/ch2/*

- ch2.v1 - `ImportEmployeesService` 초기 버전
- ch2.v2 - `ImportEmployeesService` 리팩토링 버전
- ch2.v3 - `UpdatedOffering`을 받아서 변경 내용 통지 결정하는 `update` 메서드 초기 버전(`OfferingUpdateService` 클래스를 볼것)
- ch2.v4 - `UpdatedOffering`을 받아서 변경 내용 통지 결정하는 `update` 메서드 리팩토링 버전(`OfferingUpdateService` 클래스를 볼것)
- ch2.v5 - `UnenrollEmployeeFromOfferingService` 초기 버전
- ch2.v6 - `UnenrollEmployeeFromOfferingService` 리팩토링 버전

# 3장 - src/main/java/ch3/*

- ch3.v1 - `Offering` 초기 버전
- ch3.v2 - `Offering` 리팩토링, `AddEmployeeToOfferingService`와 `AddEmployeeToOfferingValidator` 추가 및 변경 (3.4절까지 적용)
- ch3.v3 - `Enrollment` 도입, `Offering` 리팩토링(애그리게이트 루트)


# 4장 - src/main/java/ch4/*

- ch4.v1 - `Offering`정보를 `OfferingSummary`로 제공(4.2절까지 적용)
- ch4.v2 - `MessageSender`에 이메일 전송 추가(리스트 4.5 적용)
- ch4.v3 - `MessageBot`을 도입하고 `MessageSender`를 리팩토링

# 5장 - src/main/java/ch5/*

- ch5.v1 - `BadgeGiver` 초기 버전
- ch5.v3 - `BadgeGiver` 리팩토링 - `BagdesForTrainings`와 `BadgesForQuality` 도입(리스트 5.2까지)
- ch5.v2 - `BadgeRule`을 도입해 리팩토링
- ch5.v4 - 배지 부여 규칙을 일반화해 `BadegForTranings`와 `BadgesForQuality`를 만들어서 이들을 활용해 `BadgeGiver`를 초기화
- ch5.v5 - 팩토리 추가

# 6장 - src/main/java/ch6/*

- ch6.v1 - `Offering`, `CencelEnrollmentService` (리스트 6.4까지)
- ch6.v2 - `CancelEnrollmentService` 리팩토링, SDKBot 구현

# 7장  - src/main/java/ch7/*
- ch7.v1 - `NotificationAPI` 추가