package hello.core.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PercentDiscountPolicy extends DefaultDiscountPolicy {
    private final double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... discountConditions) {
        super(discountConditions);
        this.percent = percent;
    }

    /**
     * 영화 가격에서 특정 비율만큼을 할인금액으로 계산해 반환한다.
     * @param screening 영화 상영 정보
     * @return 할인 금액
     */
    @Override
    Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(this.percent);
    }
}
