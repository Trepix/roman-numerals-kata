package io.trepixxx.romannumerals;

import java.text.MessageFormat;
import java.util.List;

class InvalidSubtractiveNotationOnRomanNumeralException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Roman numeral {0} is invalid because does not meet subtractive notation rules";

    InvalidSubtractiveNotationOnRomanNumeralException(List<RomanNumeralSymbol> romanNumeralSymbols) {
        super(MessageFormat.format(MESSAGE_TEMPLATE, RomanNumeral.toString(romanNumeralSymbols)));
    }
}
