package io.trepixxx.romannumerals;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
class DecimalNumeral implements Comparable<DecimalNumeral> {

    private final Integer value;

    static DecimalNumeral zero() {
        return new DecimalNumeral(0);
    }

    boolean isMinorThan(DecimalNumeral decimalNumeral) {
        return this.value < decimalNumeral.value;
    }

    DecimalNumeral add(DecimalNumeral decimalNumber) {
        return new DecimalNumeral(this.value + decimalNumber.value);
    }

    DecimalNumeral subtract(DecimalNumeral decimalNumber) {
        return new DecimalNumeral(this.value - decimalNumber.value);
    }

    @Override
    public int compareTo(DecimalNumeral o) {
        return this.value.compareTo(o.value);
    }
}
