# 유지보수하기 좋은 코드 디자인


> [패스트 캠퍼스 유지보수하기 좋은 코드 디자인](https://github.com/cheese10yun/code-design)  
> [Spring Guide - Directory 패키기 구조 가이드](https://cheese10yun.github.io/spring-guide-directory/#null)  

## 패키지 구조

### 권장하는 도메인 구조
- domain : 도메인을 담당
- global : 프로젝트의 전체 담당
- infra : 외부 인프라스트럭처 담당

### Domain 하부 구조
#### domain
- common
  - 공통으로 사용되는 Value 객체들로 구성
  - 페이징 처리를 위한 Request, 공통된 응답을 주는 Response 객체들이 있음
- config
  - 스프링 각종 설정들로 구성
- api
    - 컨트롤러 클래스들이 존재 합니다.
- application
  - Service 클래스  
  - 도메인 객체와 외부 영역을 연결해주는 파사드와 같은 역할을 주로 담당하는 클래스로 구성  
- dao
  - repository 와 비슷
  - repository로 하지 않은 이유는 조회 전용 구현체들이 많이 작성
- domain
  - 도메인 엔티티에 대한 클래스로 구성,
  - 특정 도메인에만 속하는 Embeddable, Enum 같은 클래스도 구성
- dto
  - 주로 Request, Response 객체들로 구성
- exception
  - 해당 도메인이 발생시키는 Exception 으로 구성
#### global
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
#### infra
    - infrastructure 관련된 코드들로 구성  
    - 대표적으로 이메일 알림, SMS 알림 등 외부 서비스에 대한 코드들이 존재  
    - 그렇기 때문에 domain, global에 속하지 않고, 이 계층도 잘 관리해야 하는 대상이기에 별도의 디렉터리로 분리  
    - SMS 메시지를 보내는 클라이언트   
        - 국내 사용자에게는 KT SMS  
        - 해외 사용자에게는 Amazon SMS  

![spring-initialzr.png](./img/spring-initialzr.png)

## 코드
- com.code.design
    - domain
        - member
        - model
        - order

## 객체를 풍부하게 표현하기
- 본인의 역활과 책임, 그 의도를 잘 드러내는 것
### Lombok 사용시 주의 사항
- Lombok 을 잘 사용해야 객체 디자인을 망치지 않는다.
#### `@Data` 는 지양하자
#### 무분별한 @Setter 남용  
- Setter 는 그 의도가 분명하지 않고 객체를 언제든지 변경할 수 있는 상태가 되어서 객체의 안전성이 보장받기 힘듭니다.
#### ToString 양방향 순환 참조 문제
#### @EqualsAndHashCode 의 남발 ...
#### 클래스 상낟의 @Builder 는 지양하자  - 무엇이 문제이고
#### lombok.config 설정을 통해서 제한하자

---
Exception 처리를 왜 해야할까요?  
Check Exception VS UnChecked Exception  

##  오류 코드 코드보다 예외를 사용하라
### Clean Code 예제
```java
public class DeviceController {
    ...
    public void sendShutDown() {
        DeviceHandle handle = getHandle(DEV1);
        // 디바이스 상태를 점검한댜.
        if (handle != DeviceHandle.INVALID) {
            // 레코드 필드에 디바이스 상태를 저장한다.
            retrieveDeviceRecord(handle);
            // 디바이스가 일시정지 상태가 아니라면 종료한다.
            if (record.getStatus() != DEVICE_SUSPENDED) {
                pauseDevice(handle);
                clearDeviceWorkQueue(handle);
                closeDevice(handle);
            } else {
                logger.log("Device suspended. Unable to shut down");
            }
        } else {
            logger.log("Invalid handle for: " + DEV1.toString());
        }
    }
    ...
}
```
- 비지니스 로직과 오류 처리 코드가 함께 있어 코드가 복잡하하다
- 무슨 오류가 있는지 명확하게 파악이 힘들다.

### 예외를 사용하여 로직은 끊음
```java
public class DeviceController {
	...
	public void sendShutDown() {
		try {
			tryToShutDown();
		}
        catch (DeviceShutDownError e) {          
			// 적절한 Exception을 발생시키는것이 더 바람직하다.
            logger.log(e);
		}
	}

	private void tryToShutDown() throws DeviceShutDownError {
		DeviceHandle handle = getHandle(DEV1);
		DeviceRecord record = retrieveDeviceRecord(handle); 
		pauseDevice(handle); 
		clearDeviceWorkQueue(handle); 
		closeDevice(handle);
	}

	private DeviceHandle getHandle(DeviceID id) {
		...
		throw new DeviceShutDownError("Invalid handle for: " + id.toString());
		...
	}
```
- 비지니스 코드와 오류 처리 코드가 분리되어 가독성이 좋다
- 무슨 예외가 왜 발생하는지 명확해짐

#### Try/Catch
1. try catch를 최대한 지양해라.(로직으로 예외 처리가 가능하다면)
2. try catch를 하는데 아무런 처리가 없다면 로그라도 추가하자
3. try catch를 사용하게 된다면, 더 구체적인 예외를 발생시키는것이 좋다. (Exception 직접 정의 or Error Message를 명확하게)

### Check Exception VS UnChecked Exception
| 항목 | Checked Exception | Uncheked Exception |    
|:---|:---|:---|  
| 처리 여부            | 반드시 예외 처리 해야함 | 예외 처리 하지 않아도 됨 |  
| 트랜잭션 Rollback 여부 | Rollback 안됨 | Rollback 진행 |  
| 대표 Exception     | IOException, </br> SQLException | NullPointerException, </br> IllegalArgmentException |  

---

## 통일된 Error Response 를 가져야하는 이유
- 모양이 틀리면 Client 에서 처리해야 하는 
  - 200
  - 4XX
    - 400
    - 401
    - 404
  - 5XX
- 시스템 내부적인 오류는 숨기고, 가공된 메시지가 내려가는 것이 좋음

## 비지니스 예외릉 위한 최상위 BusinessException 클래스
- 풍부한 메시지, 상태값을 전달하여 추가적인 처리할 수 있도록 함
- Exception 을 늘리기보다는 가능하면 최상위 클래스로 처리하는 것이 좋음
### 예시
- Exception
  - RuntimeException
    - BusinessException
      - InvalidValueException
        - CouponAlreadyUseException
        - CouponExpireException
      - EntityNotFoundException
        - MemberNotFoundException
        - CouponNotFoundException
        - EmailNotFoundException

![CouponTest.png](./img/CouponTest.png)  
![CouponTest-쿠폰할인적용.png](./img/CouponTest-쿠폰할인적용.png)  
![CouponTest-쿠폰할인적용시_이미사용했을경우](./img/CouponTest-%EC%BF%A0%ED%8F%B0%ED%95%A0%EC%9D%B8%EC%A0%81%EC%9A%A9%EC%8B%9C_%EC%9D%B4%EB%AF%B8%EC%82%AC%EC%9A%A9%ED%96%88%EC%9D%84%EA%B2%BD%EC%9A%B0.png)


## Custom Validation 어노테이션 만들기
- 카드결제, 무통장 결제 예시에서는 NonNull Annotation 을 같은 항목에 적용 불가능한 경우를 예시로 들어 작성  
- 중복된 코드 제거
- email custom annotation 을 사용한 validation
  ![email-custom-validation.png](./img/email-custom-validation.png)    
- card/bank custom annotation 을 사용한 validation  
  ![card-custom-validation.png](./img/card-custom-validation.png)  
  ![bank-custom-validation.png](./img/bank-custom-validation.png)  

## 자신의 책임과 의도가 명확한 객체 디자인
### 인터페이스 이유
- 세부 구현체를 숨기고 인터페이스를 바라보게 함으로써 클래스간의 의존관계를 줄이는 것
- 인터페이스를 구현하는 여러 구현체가 있고 기능에 따라 적적한 구현체가 들어가서 다형성을 주기 위함
1. 적절한 객체의 크기를 찾아가는 여정
2. 객체는 협력 관계를 유지해야 한다
3. 묻지 말고 시켜라!
    - 객체는 협력적이다.
    - 복종하는 관계가 아님

---

## 시스템 내 강결합 문제 해결
### ApplicationEventPublisher 를 이용한 시스템 내의 강결합 문제 해결
- Kafka
- RabbitMQ
- Spring 제공
  - ApplicationEventPublisher
  - ApplicationEventListener
- 회원가입
  - 회원 가입
  - 회원가입 쿠폰발행
  - ApplicationEventPushisher
- 회원 가입 완료 이후
  - ApplicationEventListner
  - 회원 가입 이메일 전송

### @TransactionalEventListener 를 이용한 트랜잭션 문제 해결
- 해당 트랜잭션이 Commit 된 이후에 리스너가 동작
- 예외가 발생하게 된다면 트랜잭션 Commit 이 진행되지 않기 때문에 해당 리스너가 동자가지 않게 되어 트랜잭션 문제를 해결할 수 있음

### @Async Annotation 사용으로 @Transactional 로 묶인 Transaction 의 성능, 


## gradle
```
gradle clean build --warning-mode all     
gradle clean build --warning-mode all --debug  
gradle bootRun  
```

### gradle bootRun
```
PS > ./gradlew bootRun

> Task :bootRun
 
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.0)

2023-06-01T15:10:51.398+09:00  INFO 13096 --- [  restartedMain] com.code.design.CodeDesignApplication    : Starting CodeDesignApplication using Java 17.0.6 with PID 13096 (D:\workspace\Fastcampus\code_design\build\classes\java\main started by 07456 in D:\workspace\Fastcampus\code_design)
2023-06-01T15:10:51.401+09:00  INFO 13096 --- [  restartedMain] com.code.design.CodeDesignApplication    : No active profile set, falling back to 1 default profile: "default"
2023-06-01T15:10:51.444+09:00  INFO 13096 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : Devtools property defaults active! Set 'spring.devtools.add-properties' to 'false' to disable
2023-06-01T15:10:51.444+09:00  INFO 13096 --- [  restartedMain] .e.DevToolsPropertyDefaultsPostProcessor : For additional web related logging consider setting the 'logging.level.web' property to 'DEBUG'
2023-06-01T15:10:52.270+09:00  INFO 13096 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data JPA repositories in DEFAULT mode.
2023-06-01T15:10:52.322+09:00  INFO 13096 --- [  restartedMain] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 48 ms. Found 4 JPA repository interfaces.
2023-06-01T15:10:52.846+09:00  INFO 13096 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8181 (http)
2023-06-01T15:10:52.854+09:00  INFO 13096 --- [  restartedMain] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-06-01T15:10:52.854+09:00  INFO 13096 --- [  restartedMain] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.8]
2023-06-01T15:10:52.915+09:00  INFO 13096 --- [  restartedMain] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-06-01T15:10:52.916+09:00  INFO 13096 --- [  restartedMain] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1470 ms
2023-06-01T15:10:53.091+09:00  INFO 13096 --- [  restartedMain] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2023-06-01T15:10:53.127+09:00  INFO 13096 --- [  restartedMain] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 6.2.2.Final
2023-06-01T15:10:53.129+09:00  INFO 13096 --- [  restartedMain] org.hibernate.cfg.Environment            : HHH000406: Using bytecode reflection optimizer
2023-06-01T15:10:53.227+09:00  INFO 13096 --- [  restartedMain] o.h.b.i.BytecodeProviderInitiator        : HHH000021: Bytecode provider name : bytebuddy
2023-06-01T15:10:53.322+09:00  INFO 13096 --- [  restartedMain] o.s.o.j.p.SpringPersistenceUnitInfo      : No LoadTimeWeaver setup: ignoring JPA class transformer
2023-06-01T15:10:53.333+09:00  INFO 13096 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2023-06-01T15:10:53.568+09:00  INFO 13096 --- [  restartedMain] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Added connection com.mysql.cj.jdbc.ConnectionImpl@7a638979
2023-06-01T15:10:53.570+09:00  INFO 13096 --- [  restartedMain] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2023-06-01T15:10:53.612+09:00  INFO 13096 --- [  restartedMain] org.hibernate.orm.dialect                : HHH035001: Using dialect: org.hibernate.dialect.MySQLDialect, version: 8.0.32
2023-06-01T15:10:53.877+09:00  INFO 13096 --- [  restartedMain] o.h.b.i.BytecodeProviderInitiator        : HHH000021: Bytecode provider name : bytebuddy
2023-06-01T15:10:54.457+09:00  INFO 13096 --- [  restartedMain] o.h.m.i.EntityInstantiatorPojoStandard   : HHH000182: No default (no-argument) constructor for class: com.code.design.doamin.member.domain.Student (class must be instantiated by Interceptor)
2023-06-01T15:10:54.534+09:00  INFO 13096 --- [  restartedMain] o.h.m.internal.PojoInstantiatorImpl      : HHH000182: No default (no-argument) constructor for class: com.code.design.doamin.order.order.domain.OrderMessage (class must be instantiated by Interceptor)
2023-06-01T15:10:54.738+09:00  INFO 13096 --- [  restartedMain] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
Hibernate:
    alter table coupon
       drop
       foreign key FK6dvv9mu361hxtdwtujs8ics6s
Hibernate:
    alter table coupon
       drop
       foreign key FK25fj1yhf802nysi2b4wka0wt6
Hibernate:
    alter table refund
       drop
       foreign key FK80vls36avhp4yl7h8apkqm0ek
Hibernate:
    drop table if exists cart
Hibernate:
    drop table if exists coupon
Hibernate:
    drop table if exists member
Hibernate:
    drop table if exists orderitem
Hibernate:
    drop table if exists orders
Hibernate:
    drop table if exists refund
Hibernate:
    drop table if exists student
Hibernate:
    create table cart (
        id bigint not null auto_increment,
        product_id bigint not null,
        primary key (id)
    ) engine=InnoDB
Hibernate:
    create table coupon (
        amount decimal(38,2) not null,
        expiration_date date,
        used bit not null,
        coupon_id bigint,
        create_at datetime(6) not null,
        id bigint not null auto_increment,
        member bigint,
        member_id bigint not null,
        update_at datetime(6) not null,
        primary key (id)
    ) engine=InnoDB
Hibernate:
    create table member (
        create_at datetime(6) not null,
        id bigint not null auto_increment,
        update_at datetime(6) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        password varchar(255) not null,
        primary key (id)
    ) engine=InnoDB
Hibernate:
    create table orderitem (
        id bigint not null,
        name varchar(255),
        primary key (id)
    ) engine=InnoDB
Hibernate:
    create table orders (
        product_amount decimal(38,2) not null,
        id bigint,
        member_id bigint not null,
        order_id bigint not null auto_increment,
        product_id bigint not null,
        address1 varchar(255) not null,
        address2 varchar(255) not null,
        email varchar(255) not null,
        name varchar(255),
        type varchar(255) not null,
        zip varchar(255) not null,
        primary key (order_id)
    ) engine=InnoDB
Hibernate:
    create table refund (
        id bigint not null auto_increment,
        order_id bigint not null,
        account_holder varchar(255) not null,
        account_number varchar(255) not null,
        bank_name varchar(255) not null,
        credit__holder varchar(255) not null,
        credit_number varchar(255) not null,
        primary key (id)
    ) engine=InnoDB
Hibernate:
    create table student (
        creat_at datetime(6) not null,
        id bigint not null auto_increment,
        update_at datetime(6) not null,
        email varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    ) engine=InnoDB
Hibernate:
    alter table member
       add constraint UK_mbmcqelty0fbrvxp1q58dn57t unique (email)
Hibernate:
    alter table refund
       add constraint UK_d3xe8qeaax5oaffr670iofthk unique (order_id)
Hibernate:
    alter table student
       add constraint UK_fe0i52si7ybu0wjedj6motiim unique (email)
Hibernate:
    alter table coupon
       add constraint FK6dvv9mu361hxtdwtujs8ics6s
       foreign key (member)
       references member (id)
Hibernate:
    alter table coupon
       add constraint FK25fj1yhf802nysi2b4wka0wt6
       foreign key (coupon_id)
       references member (id)
Hibernate:
    alter table refund
       add constraint FK80vls36avhp4yl7h8apkqm0ek
       foreign key (order_id)
       references orders (order_id)
2023-06-01T15:10:55.177+09:00  INFO 13096 --- [  restartedMain] o.h.t.s.i.e.GenerationTargetToDatabase   : HHH000476: Executing script '[injected ScriptSourceInputNonExistentImpl script]'
2023-06-01T15:10:55.179+09:00  INFO 13096 --- [  restartedMain] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2023-06-01T15:10:56.074+09:00  WARN 13096 --- [  restartedMain] ion$DefaultTemplateResolverConfiguration : Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
2023-06-01T15:10:56.352+09:00  INFO 13096 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
2023-06-01T15:10:56.357+09:00  INFO 13096 --- [  restartedMain] o.s.b.a.e.web.EndpointLinksResolver      : Exposing 13 endpoint(s) beneath base path '/actuator'
2023-06-01T15:10:56.428+09:00  INFO 13096 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8181 (http) with context path ''
2023-06-01T15:10:56.440+09:00  INFO 13096 --- [  restartedMain] com.code.design.CodeDesignApplication    : Started CodeDesignApplication in 5.341 seconds (process running for 6.116)
Hibernate:
    /* insert for
        com.code.design.doamin.member.domain.Member */insert
    into
        member (create_at,email,name,password,update_at)
    values
        (?,?,?,?,?)
Hibernate:
    /* insert for
        com.code.design.doamin.member.domain.Member */insert
    into
        member (create_at,email,name,password,update_at)
    values
        (?,?,?,?,?)
Hibernate:
    /* insert for
        com.code.design.doamin.member.domain.Member */insert
    into
        member (create_at,email,name,password,update_at)
    values
        (?,?,?,?,?)
Hibernate:
    /* insert for
        com.code.design.doamin.order.cart.domain.Cart */insert
    into
        cart (product_id)
    values
        (?)
Hibernate:
    /* insert for
        com.code.design.doamin.order.cart.domain.Cart */insert
    into
        cart (product_id)
    values
        (?)
Hibernate:
    /* insert for
        com.code.design.doamin.order.cart.domain.Cart */insert
    into
        cart (product_id)
    values
        (?)
Hibernate:
    /* insert for
        com.code.design.doamin.order.cart.domain.Cart */insert
    into
        cart (product_id)
    values
        (?)
Hibernate:
    /* insert for
        com.code.design.doamin.order.cart.domain.Cart */insert
    into
        cart (product_id)
    values
        (?)
Hibernate:
    /* insert for
        com.code.design.doamin.order.cart.domain.Cart */insert
    into
        cart (product_id)
    values
        (?)
<==========---> 80% EXECUTING [21s]
> :bootRun
```

