package io.trepixxx.romannumerals;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.trepixxx.romannumerals.RomanNumeralSymbol.*;

class RomanNumeralTest {

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


    @Test
    void givenDecimalNumeralWhichGenerateSubtractiveNotation_CanCreateRomanNumeral() {
        val romanNumeral = RomanNumeral.from(new DecimalNumeral(1999));
        val expectedRomanNumeral = new RomanNumeral(M,C,M,X,C,I,X);
        
        Assertions.assertEquals(expectedRomanNumeral, romanNumeral);
    }

    @Test
    void givenTwoRomanNumerals_CanSumIt() {
        val firstAddend = new RomanNumeral(C);
        val secondAddend = new RomanNumeral(M);
        val expectedSum = new RomanNumeral(M, C);

        Assertions.assertEquals(expectedSum, firstAddend.add(secondAddend));
    }

    @Test
    void givenTwoRomanNumeralsWhichGenerateSubtractiveNotationNumeral_CanSumIt() {
        val firstAddend = new RomanNumeral(D, C, C, C);
        val secondAddend = new RomanNumeral(C, X);
        val expectedSum = new RomanNumeral(C, M, X);

        Assertions.assertEquals(expectedSum, firstAddend.add(secondAddend));
    }

    @Test
    @Disabled("Validation on creation is not implemented yet. This is an invalid number using subtractive notation")
    void givenRomanNumeralWithInvalidSubtractiveNotation_ThrowException() {
        new RomanNumeral(I, C);
    }
}
