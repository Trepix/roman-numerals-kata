package io.trepixxx.romannumerals;

import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.trepixxx.romannumerals.AllowedSubtractiveNotations.isNotValid;
import static io.trepixxx.romannumerals.AllowedSubtractiveNotations.isSubtractiveNotation;
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
        validate(romanNumeralSymbols);
        this.number = new ArrayList<>(romanNumeralSymbols);
    }

    private static void validate(List<RomanNumeralSymbol> romanNumeralSymbols) {
        validateSubtractiveNotations(romanNumeralSymbols);
        validateRepetitions(romanNumeralSymbols);
        //regexValidate(romanNumeralSymbols);
    }

    private static void validateSubtractiveNotations(List<RomanNumeralSymbol> romanNumeralSymbols) {
        int lastArrayPosition = romanNumeralSymbols.size() - 1;
        for (int i = 0; i < lastArrayPosition; ++i) {
            val currentSymbol = romanNumeralSymbols.get(i);
            val nextSymbol = romanNumeralSymbols.get(i + 1);
            if (isSubtractiveNotation(currentSymbol, nextSymbol)) {
                if (isNotValid(currentSymbol, nextSymbol))
                    throw new InvalidSubtractiveNotationOnRomanNumeralException(romanNumeralSymbols);
                if (i > 0) {
                    val previousSymbol = romanNumeralSymbols.get(i - 1);
                    if (previousSymbol.equals(currentSymbol))
                        throw new InvalidSubtractiveNotationOnRomanNumeralException(romanNumeralSymbols);
                }
                if (i + 2 <= lastArrayPosition) {
                    val next2Symbol = romanNumeralSymbols.get(i + 2);
                    if (!next2Symbol.isMinorThan(currentSymbol))
                        throw new InvalidSubtractiveNotationOnRomanNumeralException(romanNumeralSymbols);
                }
            }
        }
    }

    private static void validateRepetitions(List<RomanNumeralSymbol> romanNumeralSymbols) {
        int currentRepetitions = 1;
        for (int i = 1; i < romanNumeralSymbols.size(); ++i) {
            val previousSymbol = romanNumeralSymbols.get(i - 1);
            val currentSymbol = romanNumeralSymbols.get(i);
            if (currentSymbol.equals(previousSymbol)) {
                ++currentRepetitions;
                val maxAllowedRepetitionsOfSymbol = AllowedSymbolRepetitions.maxRepetitions(currentSymbol);
                if (currentRepetitions > maxAllowedRepetitionsOfSymbol)
                    throw new InvalidRomanNumeralSymbolRepetitionException(toString(romanNumeralSymbols), currentSymbol, maxAllowedRepetitionsOfSymbol);
            } else currentRepetitions = 1;
        }
    }

    private static void regexValidate(List<RomanNumeralSymbol> romanNumeralSymbols) {
        val word = toString(romanNumeralSymbols);
        boolean valid = word.matches("^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");
        if (!valid)
            throw new InvalidSubtractiveNotationOnRomanNumeralException(romanNumeralSymbols);
    }

    static String toString(List<RomanNumeralSymbol> symbols) {
        return symbols.stream()
                .map(RomanNumeralSymbol::getSymbol)
                .map(String::valueOf)
                .collect(Collectors.joining());
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
