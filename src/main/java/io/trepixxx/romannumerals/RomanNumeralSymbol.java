package io.trepixxx.romannumerals;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
enum RomanNumeralSymbol {
    I("I", new DecimalNumeral(1)),
    V("V", new DecimalNumeral(5)),
    X("X", new DecimalNumeral(10)),
    L("L", new DecimalNumeral(50)),
    C("C", new DecimalNumeral(100)),
    D("D", new DecimalNumeral(500)),
    M("M", new DecimalNumeral(1000));

    private final String symbol;
    @Getter
    private final DecimalNumeral decimalNumeral;

    static RomanNumeralSymbol of(Character character) {
        return Arrays.stream(values())
                .filter(romanNumeralSymbol -> romanNumeralSymbol.getSymbol().equals(character))
                .findAny()
                .orElseThrow(() -> new InvalidRomanNumeralSymbolException(character));
    }

    Character getSymbol() {
        return symbol.charAt(0);
    }

    boolean isMinorThan(RomanNumeralSymbol romanNumeralSymbol) {
        return this.decimalNumeral.isMinorThan(romanNumeralSymbol.decimalNumeral);
    }
}
