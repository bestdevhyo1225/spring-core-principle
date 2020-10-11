package hello.core.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Reservation {
    private final Customer customer; // 고객 정보
    private final Screening screening; // 상영 정보
    private final Money fee; // 금액
    private final int audienceCount; // 인원 수

    @Override
    public String toString() {
        return "Reservation{" +
                ", fee=" + fee +
                ", audienceCount=" + audienceCount +
                '}';
    }
}
