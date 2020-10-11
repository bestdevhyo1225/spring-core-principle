package hello.core.model;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class Money {

    public static final Money ZERO = Money.wons(0L);
    private final BigDecimal amount;

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public Money plus(Money money) {
        return new Money(this.amount.add(money.amount));
    }

    public Money minus(Money money) {
        return new Money(this.amount.subtract(money.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public boolean isLessThan(Money money) {
        return this.amount.compareTo(money.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money money) {
        return this.amount.compareTo(money.amount) >= 0;
    }

    @Override
    public String toString() {
        return "Money {" + "amount = " + amount + "}";
    }
}
