package io.trepixxx.romannumerals;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class DecimalNumeralTest {

    @ToString
    @RequiredArgsConstructor
    static class Tuple {
        final Integer first;
        final Integer second;
        final boolean isFirstMinorThanSecond;
    }

    private static Stream<Tuple> firstIsMinorThanSecondGenerator() {
        return Stream.of(
                new Tuple(0, 5, true),
                new Tuple(5, 5, false),
                new Tuple(6, 5, false)
        );
    }

    @ParameterizedTest
    @MethodSource("firstIsMinorThanSecondGenerator")
    void givenATupleOfNumbers_ReturnFirstIsMinorThanSecond(Tuple tuple) {
        DecimalNumeral first = new DecimalNumeral(tuple.first);
        DecimalNumeral second = new DecimalNumeral(tuple.second);

        Assertions.assertEquals(tuple.isFirstMinorThanSecond, first.isMinorThan(second));
    }

    @Test
    void becauseDecimalNumeralIsImmutable_ShouldNotChangeAfterAdd() {
        DecimalNumeral first = new DecimalNumeral(5);
        DecimalNumeral originalValueFirst = new DecimalNumeral(5);
        DecimalNumeral second = new DecimalNumeral(6);

        first.add(second);

        Assertions.assertEquals(originalValueFirst, first);
    }

    @Test
    void shouldReturnNewInstanceWithTheResultOfFirstPlusSecond() {
        DecimalNumeral first = new DecimalNumeral(5);
        DecimalNumeral second = new DecimalNumeral(6);

        DecimalNumeral result = first.add(second);

        DecimalNumeral expected = new DecimalNumeral(11);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void shouldReturnNewInstanceWithTheResultOfFirstMinusSecond() {
        DecimalNumeral first = new DecimalNumeral(5);
        DecimalNumeral second = new DecimalNumeral(6);

        DecimalNumeral result = first.subtract(second);

        DecimalNumeral expected = new DecimalNumeral(-1);
        Assertions.assertEquals(expected, result);
    }

}
