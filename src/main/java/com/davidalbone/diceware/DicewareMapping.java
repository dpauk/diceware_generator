package com.davidalbone.diceware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class DicewareMapping {
    private HashMap<String, String> numberWordMapping = new HashMap<>();

    DicewareMapping() throws IOException {
        readBealeFile();
    }

    private void readBealeFile() throws IOException, NullPointerException {

        try (InputStream in = Main.class.getClassLoader().getResourceAsStream("beale.wordlist.txt")) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                numberWordMapping.put(parts[0], parts[1]);
            }
        }
    }

    public String getWordMappedToNumber(String numberToFindWordFor) {
        if (numberToFindWordFor.length() != 5) {
            throw new RuntimeException();
        }

        return numberWordMapping.get(numberToFindWordFor);
    }
}
