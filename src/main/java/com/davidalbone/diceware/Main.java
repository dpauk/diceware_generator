package com.davidalbone.diceware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        StringBuilder dicewareBuilder = new StringBuilder();

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

        System.out.println(String.format("Password is: %s%n", dicewareBuilder.toString())); // NOSONAR

    }

    private static StringBuilder generateDicewareString(StringBuilder dicewareBuilder, HashMap<String, String> bealeList) {

        int randomNumber;

        Random rand = new Random();

        for (int numberOfWords = 0; numberOfWords < 3; numberOfWords++) {
            StringBuilder bealeNumber = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                randomNumber = rand.nextInt(6) + 1;
                bealeNumber.append(randomNumber);
            }

            String bealeNumberString = bealeNumber.toString();
            dicewareBuilder.append(bealeList.get(bealeNumberString));
            if (numberOfWords < 2) {
                dicewareBuilder.append(" ");
            }
        }

        return dicewareBuilder;
    }

    private static HashMap<String, String> generateBealeList() throws IOException, NullPointerException {
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
