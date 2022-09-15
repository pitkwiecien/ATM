package com.company;

import java.util.Arrays;
import java.util.Objects;

import static com.company.Constants.consoleScanner;

public class AuxiliaryFunctions {
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    public static float getFloatInput(String errorMessage){
        Float floatInput = null;
        while (floatInput == null){
            try {
                floatInput = Float.parseFloat(consoleScanner.nextLine());
            } catch (NumberFormatException ignored){
                System.out.print(errorMessage);
            }
        }
        return floatInput;
    }

    public static String getStringInput(String message, int length){
        while(true){
            System.out.print(message);
            String input = consoleScanner.nextLine();
            if(input.length() == length) return input;
        }
    }

    public static Card getCard(String number, Boolean searchByBlik){
        if(!searchByBlik)
            for(Card card : Constants.CARDS){
                if(Objects.equals(card.getNumber(), number)) return card;
            }
        else
            for(Card card : Constants.CARDS){
                if(Objects.equals(card.getBlikCode(), number)) return card;
            }
        return null;
    }

    public static float getRandomNumber(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }

    public static int getIntInput(String errorMessage){
        float input = getFloatInput(errorMessage);
        while (input != (int) input){
            System.out.print(errorMessage);
            input = getFloatInput(errorMessage);
        }
        return (int) input;
    }

    public static int getIntInput(String errorMessage, int[] inTable){
        int input = getIntInput(errorMessage);
        while (!contains(inTable, input)){
            System.out.print(errorMessage);
            input = getIntInput(errorMessage);
        }
        return input;
    }

    public static boolean getRandom(double percentChance){
        return Math.random() * 100 < percentChance;
    }
}
