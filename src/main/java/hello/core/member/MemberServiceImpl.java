package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /*
     * 생성자를 통한 의존 관계 주입 방식(DI)으로 DIP 문제를 해결할 수 있다.
     *
     * MemberRepository을 찾아와서 자동으로 연결해준다.(Autowired) 또는 주입해준다.
     * @ComponentScan을 쓰게되면, @Autowired를 쓰게된다.
     *
     * 마치, 'applicationContext.getBean(MemberRepository.class)' 인 것 처럼 동작한다. (디테일하게는 더 많은 기능이 있음)
     *
     * 생성자가 딱 1개만 있으면, @Autowired를 생략해도 자동 주입 된다. (스프링 빈에만 해당됨)
     *
     * 생성자를 굳이 만들지 않고, @RequiredArgsConstructor 어노테이션으로 대체할 수 있다.
     * */
    // @Autowired
//    public MemberServiceImpl(MemberRepository memberRepository) {
//        // MemberRepository 타입에 맞는 MemoryMemberRepository를 주입해준다.
//        this.memberRepository = memberRepository;
//    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /* 테스트 용도 */
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
