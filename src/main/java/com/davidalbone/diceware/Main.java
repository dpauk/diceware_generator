package com.davidalbone.diceware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String args[]) throws IOException {

        StringBuilder dicewareBuilder = new StringBuilder();

        HashMap<String, String> bealeList = new HashMap<>();

        InputStream in = Main.class.getClassLoader().getResourceAsStream("beale.wordlist.txt");

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\t");
            bealeList.put(parts[0], parts[1]);
        }

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

        System.out.println(dicewareBuilder.toString());

    }
}
