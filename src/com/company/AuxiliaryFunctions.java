package com.company;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static int getIntsBySemicolons(String errorMessage, boolean extendedVersion){
        int ret = 0;
        while (true){
            try {
                String input = consoleScanner.nextLine();
                if(extendedVersion){
                    if(Objects.equals(input.strip().toLowerCase(), "x")){
                        return -1;
                    } else  if(Objects.equals(input.strip().toLowerCase(), "z")){
                        return  -2;
                    }
                }
                String[] notes = input.split(";");
                int[] intNotes = Arrays.stream(notes).map(Integer::parseInt).mapToInt(i -> i).toArray();
                return IntStream.of(intNotes).sum();
            } catch (NumberFormatException ignored){
                System.out.print(errorMessage);
            }
        }
    }

    public static float cutFloat(float number, int digits){
        return Math.round(number * (float) Math.pow(10f, digits)) / (float) Math.pow(10f, digits);
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

    public static JSONObject getJsonObject(String urlString) throws IOException, JSONException {
        HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
        connection.setRequestProperty("access-token", "r392jnfv2-almf92nww1-ld:fwfa");
        connection.connect();
//        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder jsonStringBuilder = new StringBuilder();
        while((line = reader.readLine()) != null){
            jsonStringBuilder.append(line);
        }
        JSONObject jsonObj = new JSONObject(jsonStringBuilder.toString());
        reader.close();
        return jsonObj;
    }
}
