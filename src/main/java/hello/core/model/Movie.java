package hello.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Duration;

@RequiredArgsConstructor
public class Movie {
    private final String title;
    private final Duration runningTime;
    @Getter private final Money fee;
    private final DiscountPolicy discountPolicy;

    /**
     * 상영 정보에 따른 할인 정책을 고려해 영화 관람 가격을 계산후 반환한다.
     */
    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
