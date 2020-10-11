package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
/*
 * @Component 어노테이션이 붙은 클래스들을 찾아서 스프링 컨테이너에 자동으로 등록해준다.
 * excludeFilters 통해서 @Configuration은 Scan 대상에서 제외되도록 설정한다.
 * @Configuration 내부에는 @Component 어노테이션이 붙어있는데 기존 AppConfig랑 충돌을 방지하기 위해서
 * 다음과 같이 설정했다.
 *
 * 실무에서는 설정 정보를 Component Scan 대상에서 제외하지 않는다. 기존 코드를 최대한 유지하려고 한 것임
 *
 * @ComponentScan(basePackages = "hello.core") -> default값은 AutoAppConfig가 있는 hello.core 패키지부터 시작한다.
 * 그런데 패키지 위치를 지정하지않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것을 권장한다.
 * 최근 스프링부트도 이 방법을 기본으로 제공한다.
 */
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
