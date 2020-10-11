package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    /*
    * 할인 정책이 변경 된다면?? 기존 FixDiscountPolicy에서 RateDiscountPolicy로 변경해야한다.
    *
    * OCP(개방-폐쇄 원칙) -> "소프트웨어 요소는 확장에는 열려 있으나, 변경에는 닫혀 있어야 한다."
    * DIP(의존관계 역전 원칙) -> "추상화에 의존해야지, 구체화에 의존하면 안된다."
    *
    * DiscountPolicy 인터페이스로 기능을 새롭게 구현
    * -> 역할과 구현을 충실하게 분리했다.
    *
    * 그러나 클라이언트인 OrderServiceImpl에서 할인 정책을 FixDiscountPolicy에서 RateDiscountPolicy로 변경해야 한다.
    * -> 클라이언트 코드에 영향을 주고 있기 때문에 OCP를 위반했다.
    *
    * OrderServiceImpl가 추상화(DiscountPolicy)에 의존하고 있을뿐만 아니라, 구체화(RateDiscountPolicy)에도 의존하고 있다.
    * -> DIP를 위반했다.
    * ex) private final DiscountPolicy discountPolicy = new FixDiscountPolicy()
    *     private final DiscountPolicy discountPolicy = new RateDiscountPolicy()
    *
    * 그렇다면 해결 방법은?
    * -> DiscountPolicy 인터페이스에만 의존하도록 변경하면 된다. 그런데 실행하면, NullPointException이 발생하면서 문제를 일으킨다.
    *
    * 왜 NullPointException이 발생할까?
    * -> 생성된 인스턴스가 없기 때문에 발생한다.
    *
    * 그렇다면 어떻게 DIP를 지키면서, 구현체가 없는 코드를 실행할 수 있을까?
    * -> 이 문제를 해결하려면, 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고, 주입해주어야 한다.
    *
    * */
    private final DiscountPolicy discountPolicy;

    /*
    * 생성자를 통한 의존 관계 주입 방식(DI)으로 DIP 문제를 해결할 수 있다.
    *
    * 생성자가 딱 1개만 있으면, @Autowired를 생략해도 자동 주입 된다. (스프링 빈에만 해당됨)
    *
    * 생성자를 굳이 만들지 않고, @RequiredArgsConstructor 어노테이션으로 대체할 수 있다.
    * */
    //@Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /* 테스트 용도 */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
