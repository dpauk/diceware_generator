package com.davidalbone.diceware;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DicewareMappingTest {

    @Test
    public void testgetWordMappedToNumberReturnsCorrectResult() throws IOException {
        DicewareMapping dicewareMapping = new DicewareMapping();
        String result = dicewareMapping.getWordMappedToNumber("12345");
        assertEquals("april", result);
    }

    @Test
    @Tag("exception-testing")
    public void testgetWordMappedToNumberNumberTooShortReturnsRuntimeError() throws IOException {
        DicewareMapping dicewareMapping = new DicewareMapping();
        Assertions.assertThrows(RuntimeException.class, () -> {
            dicewareMapping.getWordMappedToNumber("1234");
        });
    }

    @Test
    @Tag("exception-testing")
    public void testgetWordMappedToNumberNumberTooLongReturnsRuntimeError() throws IOException {
        DicewareMapping dicewareMapping = new DicewareMapping();
        Assertions.assertThrows(RuntimeException.class, () -> {
            dicewareMapping.getWordMappedToNumber("123456");
        });
    }

    @Test
    @Tag("exception-testing")
    public void testgetWordMappedToNumberFailsWithLetters() throws IOException {
        DicewareMapping dicewareMapping = new DicewareMapping();
        Assertions.assertThrows(RuntimeException.class, () -> {
            dicewareMapping.getWordMappedToNumber("12z34");
        });
    }
}
