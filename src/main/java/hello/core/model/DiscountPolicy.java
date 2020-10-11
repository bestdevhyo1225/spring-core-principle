package hello.core.model;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
