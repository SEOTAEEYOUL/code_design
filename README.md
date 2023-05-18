# 유지보수하기 좋은 코드 디자인


> [패스트 캠퍼스 유지보수하기 좋은 코드 디자인](https://github.com/cheese10yun/code-design)

## 패키지 구조

### 권장하는 도메인 구조
- domain : 도메인을 담당
- global : 프로젝트의 전체 담당
- infra : 외부 인프라스트럭처 담당

### domain
- api
    - 컨트롤러 클래스들이 존재 합니다.
- domain
    - 도메인 엔티티에 대한 클래스로 구성,
    - 특정 도메인에만 속하는 Embeddable, Enum 같은 클래스도 구성
- dto
    - 주로 Request, Response 객체들로 구성
- exception
    - 해당 도메인이 발생시키는 Exception 으로 구성
- global
    - 프로젝트 전제에서 사용되는 객체들로 구성
- common
    - 공통으로 사용되는 Value 객체들로 구성
    - 페이징 처리를 위한 Request
    - 공통된 응답을 주는 Response 객체
- config
    - 스프링 각종 설정들로 구성
- error
    - 예외 핸들링을 담당하는 클래스로 구성
    - Exception Guide 에서 설명했던 코드들이 있음
- util
    - 유틸성 클래스
- infra
    - infrastructure 관련된 코드들로 구성

## 코드
- com.code.design
    - domain
        - member
        - model
        - order
        - 