package com.davidalbone.diceware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DicewarePasscodeGenerator {

    private final Logger logger = Logger.getLogger(Main.class.getName());

    private Random rand = new Random();

    private int numberOfWordsInPasscode = 3;

    private StringBuilder dicewareBuilder = new StringBuilder();

    private String lastPasscodeGenerated;

    public String getLastPasscodeGenerated() {
        this.lastPasscodeGenerated = dicewareBuilder.toString();
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

        HashMap<String, String> bealeList = new HashMap<>();

        try {
            bealeList = generateBealeList();
        } catch (IOException ioe) {
            logger.log(Level.SEVERE, "Could not find Diceware file.", ioe);
            System.exit(1);
        } catch (NullPointerException npe) {
            logger.log(Level.SEVERE, "Diceware file empty.", npe);
            System.exit(1);
        }

        dicewareBuilder = generateDicewareString(dicewareBuilder, bealeList);
    }


    private StringBuilder generateDicewareString(StringBuilder dicewareBuilder, HashMap<String, String> bealeList) {

        for (int numberOfWords = 0; numberOfWords < numberOfWordsInPasscode; numberOfWords++) {
            StringBuilder bealeNumber = generateFiveDigitNumber();

            Boolean isLastWord = false;

            if (numberOfWords == (numberOfWordsInPasscode - 1)) {
                isLastWord = true;
            }

            dicewareBuilder.append(updatePasscodeString(bealeList, isLastWord, bealeNumber));
        }

        return dicewareBuilder;
    }

    private String updatePasscodeString(HashMap<String, String> bealeList, boolean isLastWord, StringBuilder bealeNumber) {
        String bealeNumberString = bealeNumber.toString();

        String wordToAdd = bealeList.get(bealeNumberString);

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

    private HashMap<String, String> generateBealeList() throws IOException, NullPointerException {
        HashMap<String, String> bealeList = new HashMap<>();

        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("beale.wordlist.txt")){

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                bealeList.put(parts[0], parts[1]);
            }
        }

        return bealeList;
    }
}
