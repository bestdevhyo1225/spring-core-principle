package hello.core.model;

public interface DiscountCondition {
    /*
     * 영화 상영 정보를 참고로 영화 할인 조건에 부합하는지 판단하여 반환한다.
     */
    boolean isSatisfiedBy(Screening screening);
}
