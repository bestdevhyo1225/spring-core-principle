package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* DI 컨테이너다.
* AppConfig는 공연 기획자 -> 배우들을 관리하고, 교체할 수 있음
* AppConfig를 통해 관심사를 확실히 분리할 수 있다. -> 객체를 생성하고, 연결하는 역할과 실행하는 역할이 명확하게 분리되었다.
* */
@Configuration
public class AppConfig {

    /*
    * 메소드명과 리턴타입을 보면 '역할'이 보인다.
    * 또한 내부를 보면 어떤 '구현'을 사용하는지 알 수 있다.
    *
    * -> 애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
    * */

    /*
    * [ 의문점 ]
    *
    * @Bean memberService 호출 -> new MemoryMemberRepository()
    * @Bean orderService 호출 -> new MemoryMemberRepository()
    *
    * Sigleton 패턴은 하나의 객체만 생성되는거 아닌가?? 이렇게 되면, Singleton이 깨지는거 아닌가?
    * -> 왜냐하면 각각 다른 2개의  MemoryMemberRepository가 생성되었기 때문에 Singleton이 깨졌다고 생각하게 됨...
    *
    * 그런데 테스트를 해보면, MemoryMemberRepository의 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다...! 스프링은 어떻게 처리하는걸까...?
    *
    * < 호출 순서 예상 >
    * 1. Call AppConfig.memberService
    * 2. Call AppConfig.memberRepository
    * 3. Call AppConfig.memberRepository
    * 4. Call AppConfig.orderService
    * 5. Call AppConfig.memberRepository
    *
    * < 실제 호출 순서 >
    * 1. Call AppConfig.memberService
    * 2. Call AppConfig.memberRepository
    * 3. Call AppConfig.orderService
    *
    * 위의 결과를 통해 스프링이 어떠한 방법을 써서 Singleton을 보장해준다는 것을 알 수 있음
    *
    * AppConfig 클래스를 들여다보면 다음과 같은 내용이 출력된다.
    * -> class hello.core.AppConfig$$EnhancerBySpringCGLIB$$e9ae3f16
    *
    * 여기서 'EnhancerBySpringCGLIB' 이거는 뭘까?
    * -> 스프링의 @Configuration 내부에는 클래스의 바이트코드를 조작하는 라이브러리를 사용하는데 그게 바로 CGLIB이다.
    *
    * 만약 순수한 클래스라면, class hello.core.AppConfig라고 출력되어야 한다.
    * 그런데 스프링은 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 클래스를 생성하고,
    * 그 임의의 클래스를 스프링 빈으로 등록한 것이다. 그리고 그 임의의 클래스가 Singleton이 되도록 보장해준다.
    *
    * AppConfig@CGLIB 클래스의 내부 동작을 예상해본다면...?
    * @Bean이 붙은 메소드마다 해당 빈이 존재하면, 존재하는 빈을 반환하고
    * 빈이 없으면, 빈을 생성해서 스프링 컨테이너에 등록하고 반환하는 코드가 동적으로 만들어진다.
    *
    * 참고) AppConfig@CGLIB은 AppConfig의 자식이므로, AppConfig타입으로 조회할 수 있다.
    * */

    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
