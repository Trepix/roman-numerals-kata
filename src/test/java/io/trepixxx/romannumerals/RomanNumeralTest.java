package io.trepixxx.romannumerals;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static io.trepixxx.romannumerals.RomanNumeralSymbol.*;

class RomanNumeralTest {


    private static Stream<Arguments> allowedRepetitionsCasesGenerator() {
        return Stream.of(
                Arguments.of("IIII", 'I', 3),
                Arguments.of("XXXX", 'X', 3),
                Arguments.of("CCCC", 'C', 3),
                Arguments.of("VV", 'V', 1),
                Arguments.of("LL", 'L', 1),
                Arguments.of("DD", 'D', 1),
                Arguments.of("CLIIII", 'I', 3),
                Arguments.of("MCLL", 'L', 1)
        );
    }

    @Test
    void givenRomanNumeralWithInvalidSymbols_ThrowException() {
        Assertions.assertThrows(InvalidRomanNumeralSymbolException.class, () -> new RomanNumeral("XU"));
    }

    @Test
    void givenRomanNumeralWithFourSymbolsButInvalidSymbols_ThrowException() {
        Assertions.assertThrows(InvalidRomanNumeralSymbolException.class, () -> new RomanNumeral("XU"));
    }

    @ValueSource(strings = {"IC", "IL", "XM", "MIC", "XCL", "IXC", "XXXC"})
    @ParameterizedTest(name = "{0} is not a valid subtractive notation")
    void givenRomanNumeralWithInvalidSubtractiveNotation_ThrowInvalidSubtractiveNotationOnRomanNumeralException(String symbols) {
        Throwable exception = Assertions.assertThrows(
                InvalidSubtractiveNotationOnRomanNumeralException.class, () -> new RomanNumeral(symbols));
        Assertions.assertEquals("Roman numeral " + symbols + " is invalid because does not meet subtractive notation rules", exception.getMessage());
    }

    @ValueSource(strings = {"XCXC", "IVIV"})
    @ParameterizedTest(name = "{0} is not a valid subtractive notation")
    void givenRomanNumeralWithInvalidSubtractiveNotationForDuplicationOfIt_ThrowInvalidSubtractiveNotationOnRomanNumeralException(String symbols) {
        Throwable exception = Assertions.assertThrows(
                InvalidSubtractiveNotationOnRomanNumeralException.class, () -> new RomanNumeral(symbols));
        Assertions.assertEquals("Roman numeral " + symbols + " is invalid because does not meet subtractive notation rules", exception.getMessage());
    }

    @ValueSource(strings = {"LXXXIX", "DCCCXC"})
    @ParameterizedTest(name = "Roman numeral {0} is valid even with more than 4 repeated symbols")
    void givenRomanNumeralWithValid4RepeatedSymbols_CanInstanceRomanNumeral(String symbols) {
        new RomanNumeral(symbols);
    }

    @MethodSource("allowedRepetitionsCasesGenerator")
    @ParameterizedTest(name = "Roman numeral {0} is invalid because contains more than {2} repetitions of {1}")
    void givenRomanNumeralWithInvalidRepetitionOfSymbols_ThrowInvalidRomanNumeralSymbolRepetitionException(String symbols, Character repeatedCharacter, Integer maxRepetitionsAllowed) {
        Throwable exception = Assertions.assertThrows(
                InvalidRomanNumeralSymbolRepetitionException.class, () -> new RomanNumeral(symbols));
        Assertions.assertEquals("Symbol " + repeatedCharacter + " is found on " + symbols + " more than its maximum allowed: " + maxRepetitionsAllowed, exception.getMessage());
    }

    @Test
    void givenOneRomanNumeralSymbol_ReturnItsDecimalNumeral() {
        val romanNumeral = new RomanNumeral(C);
        val decimalNumeral = new DecimalNumeral(100);

        Assertions.assertEquals(decimalNumeral, romanNumeral.convertToDecimalNumeral());
    }

    @Test
    void givenMoreThanOneSymbolValueWithoutSubtractiveNotation_ReturnItsDecimalNumeral() {
        val romanNumeral = new RomanNumeral(M, M, C, C, L, V, I);
        val decimalNumeral = new DecimalNumeral(2256);

        Assertions.assertEquals(decimalNumeral, romanNumeral.convertToDecimalNumeral());
    }

    @Test
    void givenRomanNumeralWithOnlySubtractiveNotation_ReturnItsDecimalNumeral() {
        val romanNumeral = new RomanNumeral(I, X);
        val decimalNumeral = new DecimalNumeral(9);

        Assertions.assertEquals(decimalNumeral, romanNumeral.convertToDecimalNumeral());
    }

    @Test
    void givenRomanNumeralWithSubtractiveNotation_ReturnItsDecimalNumeral() {
        val romanNumeral = new RomanNumeral(X, I, V);
        val decimalNumeral = new DecimalNumeral(14);

        Assertions.assertEquals(decimalNumeral, romanNumeral.convertToDecimalNumeral());
    }

    @Test
    void givenMultipleSubtractiveNotations_ReturnItsDecimalNumeral() {
        val romanNumeral = new RomanNumeral(M, C, M, X, L, I, V);
        val decimalNumeral = new DecimalNumeral(1944);

        Assertions.assertEquals(decimalNumeral, romanNumeral.convertToDecimalNumeral());
    }

    @Test
    void givenStringRomanNumeral_CanReturnItsDecimalNumeral() {
        val romanNumeral = new RomanNumeral("CCC");
        val decimalNumeral = new DecimalNumeral(300);

        Assertions.assertEquals(decimalNumeral, romanNumeral.convertToDecimalNumeral());
    }
}
