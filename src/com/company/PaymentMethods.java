package com.company;

import com.sun.source.tree.BreakTree;

import java.util.Objects;

import static com.company.Graphics.printConfirmation;
import static com.company.Graphics.printReceipt;

public class PaymentMethods {

    public PaymentMethods() {
    }

    public boolean card(){
        if(AuxiliaryFunctions.getRandom(5)){
            System.out.println("Odczytanie karty nie poszło poprawnie!");
            return false;
        }
        String givenPin = AuxiliaryFunctions.getStringInput("Podaj PIN: ", 4);
        Card card =  AuxiliaryFunctions.getCard(Constants.CARD_NUMBER, false);
        if(card == null){
            System.out.println("Błąd karty!");
        } else if(!Objects.equals(card.getPin(), givenPin)){
            System.out.println("Błędny PIN!");
        } else if(card.getMoney() < Globals.price){
            System.out.println("Niewystarczające środki na karcie!");
        } else if(card.getDailyLimit() < Globals.price){
            System.out.println("Przekroczono dzienny limit karty!");
        } else if(AuxiliaryFunctions.getRandom(5)){
            System.out.println("Pobranie środków się nie udało!");
        } else {
            System.out.print("Czy drukować potwierdzenie (0 - nie, 1 - tak): ");
            if(AuxiliaryFunctions.getIntInput("Wpisz liczbę 0 lub 1 w zależności od twojego wyboru: ", new int[]{0, 1}) == 1){
                printConfirmation();
            }
            printReceipt();
        }
        return false;
    }

    public boolean blik(){
        String blikCode = AuxiliaryFunctions.getStringInput("Podaj kod BLIK: ", 4);
        Card card = AuxiliaryFunctions.getCard(blikCode, true);
        if(card == null){
            System.out.println("Niepoprawny kod BLIK");
        } else if(card.getMoney() < Globals.price){
            System.out.println("Niewystarczające środki na karcie!");
        } else if(card.getDailyLimit() < Globals.price) {
            System.out.println("Przekroczono dzienny limit karty!");
        } else if(AuxiliaryFunctions.getRandom(5)){
            System.out.println("Nie potwierdzono transakcji na telefonie!");
        } else if(AuxiliaryFunctions.getRandom(5)){
            System.out.println("Pobranie środków się nie udało!");
        } else {
            System.out.print("Czy drukować potwierdzenie (0 - nie, 1 - tak): ");
            if(AuxiliaryFunctions.getIntInput("Wpisz liczbę 0 lub 1 w zależności od twojego wyboru: ", new int[]{0, 1}) == 1){
                printConfirmation();
            }
            printReceipt();
        }
        return false;
    }

    public boolean cash(){
        System.out.print("Podaj banknoty: ");
        int payment = AuxiliaryFunctions.getIntsBySemicolons("Niepoprawna kwota, spróbuj jeszcze raz: ", false);
        int ret = cashHandler(payment);
        while(true){
            switch (ret){
                case -1:
                    return false;
                case -2:
                    return true;
                default:
                    payment += ret;
                    ret = cashHandler(payment);
            }
        }
    }

    private int cashHandler(int payment){
        float difference = payment - Globals.price;
        if(difference >= 0){
            System.out.println("Płatność zakończona sukcesem, reszta wynosi " + difference);
            printReceipt();
        } else {
            System.out.print("Za mało gotówki - brakuje ci " + Math.abs(AuxiliaryFunctions.cutFloat(difference, 2)) + ". Dołóż banknoty albo wpisz \"X\" żeby zakończyć transakcję albo wpisz \"Z\" żeby zmienić sposób płatności: ");
            return AuxiliaryFunctions.getIntsBySemicolons("Wybierz \"X\", \"Z\" lub podaj banknoty rozdzielone \";\": ", true);
        }
        return -1;
    }
}
