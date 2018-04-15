package com.davidalbone.diceware;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DicewarePasscodeGenerator {

    private final Logger logger = Logger.getLogger(Main.class.getName());
    DicewareMapping dicewareMapping;
    private Random rand = new Random();
    private int numberOfWordsInPassphrase = 3;
    private StringBuilder passphraseBuilder = new StringBuilder();
    private String lastPasscodeGenerated;

    DicewarePasscodeGenerator(int numberOfWordsInPasscode) {
        this.numberOfWordsInPassphrase = numberOfWordsInPasscode;
        generatePassphrase();
    }

    DicewarePasscodeGenerator() {
        this(3);
    }

    public String getLastPasscodeGenerated() {
        this.lastPasscodeGenerated = passphraseBuilder.toString();
        return this.lastPasscodeGenerated;
    }

    public void generatePassphrase() {

        try {
            dicewareMapping = new DicewareMapping();
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "Could not find Diceware file.", ioe);
            System.exit(1);
        }

        buildPassphrase();
    }

    private void buildPassphrase() {

        for (int numberOfWords = 0; numberOfWords < numberOfWordsInPassphrase; numberOfWords++) {
            StringBuilder bealeNumber = generateFiveDigitNumber();

            Boolean isLastWord = false;

            if (numberOfWords == (numberOfWordsInPassphrase - 1)) {
                isLastWord = true;
            }

            passphraseBuilder.append(updatePasscodeString(isLastWord, bealeNumber));
        }
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
