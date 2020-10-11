package hello.core.model;

import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@RequiredArgsConstructor
public class PeriodCondition implements DiscountCondition {
    /** * 할인 해줄 요일 */
    private final DayOfWeek dayOfWeek;
    /** * 할인이 적용 될 시작 시간 */
    private final LocalTime startTime;
    /** * 할인이 적용 될 종료 시간 */
    private final LocalTime endTime;

    /**
     * 영화 상영이 특정 요일, 시간내에 상영되는지 판단하여 반환한다.
     * @param screening 영화 상영 정보
     * @return 특정 기간 여부
     */
    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.getStartTime().getDayOfWeek().equals(this.dayOfWeek)
                && startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0
                && endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
    }
}
