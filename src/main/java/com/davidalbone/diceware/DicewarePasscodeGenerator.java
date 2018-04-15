package com.davidalbone.diceware;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DicewarePasscodeGenerator {

    private final Logger logger = Logger.getLogger(Main.class.getName());

    private Random rand = new Random();

    private int numberOfWordsInPasscode = 3;

    private StringBuilder passphraseBuilder = new StringBuilder();

    DicewareMapping dicewareMapping;

    private String lastPasscodeGenerated;

    public String getLastPasscodeGenerated() {
        this.lastPasscodeGenerated = passphraseBuilder.toString();
        return this.lastPasscodeGenerated;
    }

    DicewarePasscodeGenerator(int numberOfWordsInPasscode) {
        this.numberOfWordsInPasscode = numberOfWordsInPasscode;
        generatePasscode();
    }

    DicewarePasscodeGenerator() {
        this(3);
    }

    public void generatePasscode() {

        try {
            dicewareMapping = new DicewareMapping();
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "Could not find Diceware file.", ioe);
            System.exit(1);
        }

        passphraseBuilder = generateDicewareString(passphraseBuilder);
    }


    private StringBuilder generateDicewareString(StringBuilder dicewareBuilder) {

        for (int numberOfWords = 0; numberOfWords < numberOfWordsInPasscode; numberOfWords++) {
            StringBuilder bealeNumber = generateFiveDigitNumber();

            Boolean isLastWord = false;

            if (numberOfWords == (numberOfWordsInPasscode - 1)) {
                isLastWord = true;
            }

            dicewareBuilder.append(updatePasscodeString(isLastWord, bealeNumber));
        }

        return dicewareBuilder;
    }

    private String updatePasscodeString(boolean isLastWord, StringBuilder bealeNumber) {
        String bealeNumberString = bealeNumber.toString();

        String wordToAdd = dicewareMapping.getWordMappedToNumber(bealeNumberString);

        if (!isLastWord) {
            wordToAdd += " ";
        }

        return wordToAdd;
    }

    private StringBuilder generateFiveDigitNumber() {
        int randomNumber;

        StringBuilder bealeNumber = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            randomNumber = rand.nextInt(6) + 1;
            bealeNumber.append(randomNumber);
        }
        return bealeNumber;
    }
}
