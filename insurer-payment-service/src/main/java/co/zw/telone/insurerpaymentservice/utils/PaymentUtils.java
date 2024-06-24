package co.zw.telone.insurerpaymentservice.utils;

import java.time.Year;

public class PaymentUtils {


    public static String generateReferenceNumber() {
        /*
         * year + anyRandomSixDigits
         * */

        Year currentYear = Year.now();

        int min = 100000;
        int max = 999999;

        // generate a random number between min and max
        int randNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);

        String year = String.valueOf(currentYear);
        String randomNumber = String.valueOf(randNumber);
        StringBuilder referenceNumber = new StringBuilder();

        return referenceNumber.append(year).append(randomNumber).toString();

    }
}