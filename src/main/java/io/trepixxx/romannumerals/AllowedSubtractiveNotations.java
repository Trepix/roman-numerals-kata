package io.trepixxx.romannumerals;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

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

    public List<RomanNumeralSymbol> getRomanNumeralSymbols() {
        return Arrays.asList(subtrahend, minuend);
    }

    public DecimalNumeral getDecimalNumeral() {
        return minuend.getDecimalNumeral().subtract(subtrahend.getDecimalNumeral());
    }

}
