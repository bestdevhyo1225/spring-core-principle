package hello.core.model;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class Screening {
    private final Movie movie;
    private final int sequence;
    private final LocalDateTime startTime;

    // 영화 시작 시간 반환
    public LocalDateTime getStartTime() {
        return startTime;
    }

    // 회차가 같은지 판단
    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    // 상영되는 영화 관람 가격 반환
    public Money getMovieFee() {
        return movie.getFee();
    }

    // 상영 예약 정보 만들어 반환
    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    // 1인당 관람 비용을 구한 뒤 인원수대로 곱한 후 반한환다.
    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}
