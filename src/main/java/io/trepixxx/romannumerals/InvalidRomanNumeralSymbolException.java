package io.trepixxx.romannumerals;

class InvalidRomanNumeralSymbolException extends RuntimeException {
    InvalidRomanNumeralSymbolException(Character c) {
        super("The character " + c + " is not a valid Roman Symbol");
    }
}
