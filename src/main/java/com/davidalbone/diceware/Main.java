package com.davidalbone.diceware;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        DicewarePasscodeGenerator dpg = null;

        if (args.length == 0) {
            dpg = new DicewarePasscodeGenerator();
        } else {
            try {
                dpg = new DicewarePasscodeGenerator(Integer.parseInt(args[0]));
            } catch (NumberFormatException nfe) {
                logger.log(Level.SEVERE, "Invalid number passed as argument");
                System.exit(1);
            }
        }

        if (dpg != null) {
            String generatedPasscode = dpg.getLastPasscodeGenerated();

            System.out.println(String.format("Password is: %s%n", generatedPasscode)); // NOSONAR
        }
    }
}
