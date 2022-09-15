package com.company;

public class Globals {
    public static float price;

    public static void initiatePrice(){
        float tempPrice = AuxiliaryFunctions.getRandomNumber(Constants.LOWEST_PRICE, Constants.HIGHEST_PRICE);
        price = (float) Math.round(tempPrice * 100.0f) / 100.0f;
    }
}
