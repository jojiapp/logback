# Logback

## Logback 이란?

`Logback`은 `Slf4j`이 구현체 중 하나로 로깅을 위한 라이브러리 입니다.

`Logback` 은 `log4j` 에 비해 향상된 `필터링 정책`, `기능`, `로그 레벨 변경 등`에 대해
서버를 재시작할 필요 없이 자동 리로딩을 지원하기 때문에 최근에는 `Logback`을 더 많이 사용합니다.

`spring-boot-starter-web`를 사용하면 기본적으로 `Logback Dependencies`가 받아집니다.

## 로그 레벨

| Level   | 설명                                                   |
|---------|------------------------------------------------------|
| `Fatal` | 매우 심각한 에러로 프로그램이 종료되는 경우가 많음                         |
| `Error` | 의도치 않은 에러가 발생한 경우. 프로그램이 종료되지는 않음                    |
| `Warn`  | 에러가 될 수 있는 잠재적 가능성이 있는 경우                            |
| `Info`  | 명확한 의도가 있는 에러. 요구사항에 따라 시스템 동작을 보여줄 때                |
| `Debug` | Info 레벨보다 더 자세한 정보가 필요한 경우. Dev 환경                   |
| `Trace` | Debug 레벨보다 더 자세한 정보가 필요한 경우. Dev 환경에서 버그를 해결하기 위해 사용 |

- `Logback`은 `Fatal`을 제외하고 5개의 레벨을 가집니다.

## 로깅 해보기

```java

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {

	@GetMapping
	private void getMember() {
		log.trace("trace: member");
		log.debug("debug: member");
		log.info("info: member");
		log.warn("warn: member");
		log.error("error: member");
	}
}
```

```zsh
2022-08-14 16:31:13.682  INFO 1951 --- [nio-8080-exec-1] c.j.logback.member.MemberController      : info: member
2022-08-14 16:31:13.682  WARN 1951 --- [nio-8080-exec-1] c.j.logback.member.MemberController      : warn: member
2022-08-14 16:31:13.682 ERROR 1951 --- [nio-8080-exec-1] c.j.logback.member.MemberController      : error: member
```

아무런 설정을 하지 않고 각 레벨별로 기록했는데 `Info` 레벨 부터 나오는 이유는 기본 값이 `Info`이기 때문입니다.

> `@Slf4j`는 `Lombok`에서 제공하는 기능으로 아래 처럼 로깅 필드를 생성해주는 역할을 합니다.
>
> ```java
> private Logger log=LoggerFactory.getLogger(MemberController.class);
> ```

## 로그 레벨 설정

로그 레벨을 변경하는 방법 두 가지가 있습니다.

- `application.yml(properties)`
- `logback-spring.xml`

```properties
logging.level.root='trace'
```

`application.properties` 파일에 위 처럼 설정하면 `trace`까지 모두 출력되는 것을 확인할 수 있습니다.

`application.properties`은 간단하지만 세부적인 설정을 하기에는 한계가 있기 때문에
`logback-spring.xml`을 사용하여 로그를 관리하는 것이 더 좋습니다.

## Logback 설정 요소

`logback-spring.xml`을 설정하기 전에 먼저 `Logback`의 설정 요소가 어떻게 이루어져 있는지 알아보겠습니다.

### Logger - 어떻게 기록할까?

실제 로깅을 수행하는 구성요소 입니다.

> `Error` > `Warn` > `Info` > `Debug` > `Trace`
>
> 예를 들어 `Info` 레벨로 설정 시, `Debug`, `Trace` 레벨은 출력 되지 않습니다.

### Appender - 어디에 기록할까?

로그 메세지가 출력 될 대상을 결정합니다.

`Logger`는 `Appender`에게 로그 이벤트를 `위임`합니다

- `ConsoleAppender`: 콘솔에 출력
- `FileAppender`: 파일에 출력
- `RollingFileAppender`: 파일을 일정 조건에 맞게 따로 저장

외에도 로그를 `메일로 보내는 Appender` 등 더 있지만, 
주로 사용 될 Appender는 위의 3개이므로 이외에 Appender는 필요시 찾아서 적용하면 될 것 같습니다.

### Encoder(Layout) - 어떻게 출력할까?

로그 이벤트를 바이트 배열로 변환하고 해당 바이트 배열을 `OutPutStream에 쓰는 작업을 담당`합니다.

즉, `Appender`에 포함되며 `사용자가 지정한 형식으로 로그를 변환`하는 역할을 합니다.

## logback-spring.xml 설정

- `src/main/resources`하위에 `logback-spring.xml`으로 파일을 생성합니다.

> 여기서 파일 위치와 이름은 정해져있는 것이기 떄문에 다르게 사용하면 안됩니다.

### configuration

`configuration` 태그 내부에 모든 기능을 정의합니다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!--  로깅 기능 추가  -->
</configuration>
```

## 참고 사이트

- [Logback 으로 쉽고 편리하게 로그 관리를 해볼까요?](https://tecoble.techcourse.co.kr/post/2021-08-07-logback-tutorial/)
- [[10분 테코톡] ☂️ 검프의 Logging(로깅) #1](https://www.youtube.com/watch?v=1MD5xbwznlI)
- [[10분 테코톡] ☂️ 검프의 Logging(로깅) #2](https://www.youtube.com/watch?v=JqZzy7RyudI)