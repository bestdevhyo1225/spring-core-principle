package hello.core.model;

/**
 * 할인 정책으로 특정 금액을 사용한다.
 */
public class AmountDiscountPolicy extends DefaultDiscountPolicy {

    /** * 할인 금액 정보 */
    private final Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... discountConditions) {
        super(discountConditions);
        this.discountAmount = discountAmount;
    }

    @Override
    Money getDiscountAmount(Screening screening) {
        return this.discountAmount;
    }
}