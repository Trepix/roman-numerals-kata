package io.trepixxx.romannumerals;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

import static io.trepixxx.romannumerals.RomanNumeralSymbol.*;


@Getter
@RequiredArgsConstructor
enum AllowedSymbolRepetitions {
    III(I, 3),
    XXX(X, 3),
    CCC(C, 3),
    MMM(M, 3),
    V(RomanNumeralSymbol.V, 1),
    L(RomanNumeralSymbol.L, 1),
    D(RomanNumeralSymbol.D, 1);

    private final RomanNumeralSymbol symbol;
    private final Integer maxRepetitions;

    static Integer maxRepetitions(RomanNumeralSymbol symbol) {
        return Stream.of(values())
                .filter(v -> v.getSymbol().equals(symbol))
                .map(AllowedSymbolRepetitions::getMaxRepetitions)
                .findFirst()
                .orElse(0);
    }
}
