package io.trepixxx.romannumerals;

import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.trepixxx.romannumerals.DecimalNumeral.zero;
import static java.util.stream.Collectors.toList;

class RomanNumeral {

    private final List<RomanNumeralSymbol> number;

    RomanNumeral(String romanNumeralSymbols) {
        this(romanNumeralSymbols.chars()
                .mapToObj(c -> (char) c)
                .map(RomanNumeralSymbol::of)
                .collect(toList()));
    }

    RomanNumeral(RomanNumeralSymbol... romanNumeralSymbols) {
        this(Arrays.asList(romanNumeralSymbols));
    }

    private RomanNumeral(List<RomanNumeralSymbol> romanNumeralSymbols) {
        this.number = new ArrayList<>(romanNumeralSymbols);
    }

    DecimalNumeral convertToDecimalNumeral() {
        DecimalNumeral decimalValue = zero();
        int lastArrayPosition = number.size() - 1;
        for (int i = 0; i < lastArrayPosition; ++i) {
            val currentSymbol = number.get(i);
            val nextSymbol = number.get(i + 1);
            if (currentSymbol.isMinorThan(nextSymbol))
                decimalValue = decimalValue.subtract(currentSymbol.getDecimalNumeral());
            else decimalValue = decimalValue.add(currentSymbol.getDecimalNumeral());
        }
        return decimalValue.add(number.get(lastArrayPosition).getDecimalNumeral());
    }
}
