package io.trepixxx.romannumerals;

import java.text.MessageFormat;

class InvalidRomanNumeralSymbolRepetitionException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Symbol {0} is found on {1} more than its maximum allowed: {2}";

    InvalidRomanNumeralSymbolRepetitionException(String romanNumeral, RomanNumeralSymbol symbol, Integer maxTimesAllowed) {
        super(MessageFormat.format(MESSAGE_TEMPLATE, symbol.getSymbol(), romanNumeral, maxTimesAllowed));
    }
}
