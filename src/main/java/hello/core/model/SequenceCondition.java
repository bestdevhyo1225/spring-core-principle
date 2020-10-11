package hello.core.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SequenceCondition implements DiscountCondition {
    private final int sequence;

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
