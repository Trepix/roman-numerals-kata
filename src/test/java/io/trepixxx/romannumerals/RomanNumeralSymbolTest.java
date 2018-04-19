package io.trepixxx.romannumerals;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


class RomanNumeralSymbolTest {

    @ParameterizedTest
    @EnumSource(RomanNumeralSymbol.class)
    void givenCharacter_CanCreateItsRomanNumeralSymbol(RomanNumeralSymbol symbol) {
        val symbolFound = RomanNumeralSymbol.of(symbol.getSymbol());

        Assertions.assertEquals(symbol, symbolFound);
    }

    @Test
    void givenNonValidCharacter_ThrowException() {
        Executable function = () -> RomanNumeralSymbol.of('U');
        Assertions.assertThrows(InvalidRomanNumeralSymbolException.class, function);
    }
}
