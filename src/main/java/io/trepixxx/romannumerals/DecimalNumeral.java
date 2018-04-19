package io.trepixxx.romannumerals;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@RequiredArgsConstructor
class DecimalNumeral {

    private final Integer value;

    static DecimalNumeral zero() {
        return new DecimalNumeral(0);
    }

    boolean isMinorThan(DecimalNumeral decimalNumeral) {
        return this.value < decimalNumeral.value;
    }

    DecimalNumeral add(DecimalNumeral decimalNumeral) {
        return new DecimalNumeral(this.value + decimalNumeral.value);
    }

    DecimalNumeral subtract(DecimalNumeral decimalNumeral) {
        return new DecimalNumeral(this.value - decimalNumeral.value);
    }
}
