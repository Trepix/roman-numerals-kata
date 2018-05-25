package io.trepixxx.romannumerals;

import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

import static io.trepixxx.romannumerals.RomanNumeralSymbol.*;


@RequiredArgsConstructor
enum AllowedSubtractiveNotations {
    IV(I, V),
    IX(I, X),
    XL(X, L),
    XC(X, C),
    CD(C, D),
    CM(C, M);

    private final RomanNumeralSymbol subtrahend;
    private final RomanNumeralSymbol minuend;

    public static boolean isSubtractiveNotation(RomanNumeralSymbol subtrahend, RomanNumeralSymbol minuend) {
        return subtrahend.isMinorThan(minuend);
    }

    public static boolean isNotValid(RomanNumeralSymbol subtrahend, RomanNumeralSymbol minuend) {
        return Stream.of(values())
                .noneMatch(f -> f.subtrahend.equals(subtrahend) && f.minuend.equals(minuend));
    }

}
