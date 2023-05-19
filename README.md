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

