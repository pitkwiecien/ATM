package com.company;

public class Main {

    public static void main(String[] args) {
        PaymentMethods paymentMethods = new PaymentMethods();
        Globals.initiatePrice();

        System.out.print("Wybierz sposób płatności:\n1. Karta\n2. BLIK\n3. Gotówka\nWpisz liczbę odpowiadającą Twojemu wyborowi: ");

        int paymentMethodId = AuxiliaryFunctions.getIntInput("Nieobsługiwana metoda, wybierz numer 1-3: ", new int[]{1, 2, 3});
        PaymentMethodEnum paymentMethod = PaymentMethodEnum.getMethod(paymentMethodId);
        if(paymentMethod == PaymentMethodEnum.CARD || paymentMethod == PaymentMethodEnum.BLIK) {
            if(AuxiliaryFunctions.getRandom(5)) {
                System.out.println("Terminal nie działa, przyjdź jutro!");
                return;
            }
            if(AuxiliaryFunctions.getRandom(5)) {
                System.out.println("Internet nie działa, przyjdź jutro!");
                return;
            }
            if(paymentMethod == PaymentMethodEnum.CARD){
                paymentMethods.card();
            } else {
                paymentMethods.blik();
            }
        } else {
            paymentMethods.cash();
        }
    }
}
