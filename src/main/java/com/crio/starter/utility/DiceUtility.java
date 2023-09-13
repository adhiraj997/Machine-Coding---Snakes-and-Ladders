package com.crio.starter.utility;

import org.springframework.stereotype.Component;

import java.util.Random;

public class DiceUtility {
    public static int rollDice() {
        Random random = new Random();
        int diceNumber = random.nextInt(6 - 1 + 1) + 1;
        System.out.println("dice number is " + diceNumber);
        return diceNumber;
    }
}
