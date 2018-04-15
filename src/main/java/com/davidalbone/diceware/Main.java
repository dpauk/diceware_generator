package com.davidalbone.diceware;

public class Main {

    public static void main(String[] args) {
        DicewarePasscodeGenerator dpg = new DicewarePasscodeGenerator();
        String generatedPasscode = dpg.getLastPasscodeGenerated();

        System.out.println(String.format("Password is: %s%n", generatedPasscode)); // NOSONAR
    }
}
