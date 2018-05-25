package io.trepixxx.romannumerals;

import lombok.EqualsAndHashCode;
import lombok.val;

import java.util.*;
import java.util.stream.Stream;

import static io.trepixxx.romannumerals.DecimalNumeral.zero;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

@EqualsAndHashCode
class RomanNumeral {

    private static final TreeMap<DecimalNumeral, List<RomanNumeralSymbol>> CONVERSION_TABLE = generateConversionTable();

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

    private static TreeMap<DecimalNumeral, List<RomanNumeralSymbol>> generateConversionTable() {
        Map<DecimalNumeral, List<RomanNumeralSymbol>> map = Stream.concat(
                Stream.of(RomanNumeralSymbol.values()).map(x -> Map.of(x.getDecimalNumeral(), singletonList(x))),
                Stream.of(AllowedSubtractiveNotations.values()).map(x -> Map.of(x.getDecimalNumeral(), x.getRomanNumeralSymbols()))
        ).collect(HashMap::new, Map::putAll, Map::putAll);
        return new TreeMap<>(map);
    }

    static RomanNumeral from(DecimalNumeral number) {
        DecimalNumeral floorDecimalNumeral = CONVERSION_TABLE.floorKey(number);
        if (floorDecimalNumeral.equals(number)) {
            return new RomanNumeral(CONVERSION_TABLE.get(number).toArray(new RomanNumeralSymbol[1]));
        }
        val symbols = new ArrayList<RomanNumeralSymbol>(CONVERSION_TABLE.get(floorDecimalNumeral));
        symbols.addAll(from(number.subtract(floorDecimalNumeral)).number);

        return new RomanNumeral(symbols);
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

    RomanNumeral add(RomanNumeral addend) {
        val sumResult = this.convertToDecimalNumeral().add(addend.convertToDecimalNumeral());
        return from(sumResult);
    }

}
