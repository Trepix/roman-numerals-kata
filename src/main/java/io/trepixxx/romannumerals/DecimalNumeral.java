package io.trepixxx.romannumerals;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class DecimalNumeral {

    private final BigInteger value;

    DecimalNumeral(Integer number) {
        this.value = BigInteger.valueOf(number);
    }

    static DecimalNumeral zero() {
        return new DecimalNumeral(0);
    }

    boolean isMinorThan(DecimalNumeral decimalNumeral) {
        return this.value.compareTo(decimalNumeral.value) < 0;
    }

    DecimalNumeral add(DecimalNumeral decimalNumeral) {
        return new DecimalNumeral(this.value.add(decimalNumeral.value));
    }

    DecimalNumeral subtract(DecimalNumeral decimalNumeral) {
        return new DecimalNumeral(this.value.subtract(decimalNumeral.value));
    }
}
