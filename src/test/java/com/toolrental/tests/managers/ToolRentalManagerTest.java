package com.toolrental.tests.managers;

import com.toolrental.data.*;
import com.toolrental.managers.ToolRentalManager;

import org.junit.Test;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ToolRentalManagerTest {

    @Test
    public void testManager() {
        ToolRentalManager toolRentalManager = new ToolRentalManager();
        CheckoutRequest request;
        RentalAgreement agreement;
        String result;

        //test calculateChargeDays()
        int chargeDays = toolRentalManager.calculateChargeDays(
                LocalDate.of(2024, 9, 1),
                LocalDate.of(2024, 9, 6),
                new ToolCharge("Ladder", BigDecimal.valueOf(5.00), true, true, false));
        assertEquals(chargeDays, 4); //Labor Day reduces 5 days to 4
        System.out.println("\nTEST 01\nCharge Days = " + chargeDays);

        //test calculateCharges()
        //ok to leave toolCode blank here since manually defining a toolCharge and CheckoutRequest mainly needed for other attributes
        request = new CheckoutRequest("", 5, 20, LocalDate.of(2024, 9, 1));
        RentalCharges charges = toolRentalManager.calculateCharges(request,
                new ToolCharge("Ladder", BigDecimal.valueOf(5.00), true, true, false));
        assertTrue(charges.getFinalCharge().compareTo(BigDecimal.valueOf(16.00)) == 0); //Labor Day reduces 5 days to 4, 20% discount reduces $5/day to $4
        System.out.println("\nTEST 02\nFinal charge = " + charges.getFinalCharge());

        //base case for checkout()
        try {
            request = new CheckoutRequest("LADW", 5, 20, LocalDate.of(2024, 9, 1));
            agreement = toolRentalManager.checkout(request);
            result = agreement.getFormattedAgreement();
        }
        catch (IllegalArgumentException e) {
            result = e.getMessage(); //should never get here
        }
        System.out.println("\nTEST 03\n" + result);
        assertTrue(result.contains("Final charge: $6.37"));

        //null request case
        try {
            agreement = toolRentalManager.checkout(null);
            result = agreement.getFormattedAgreement(); //should never get here
        }
        catch (IllegalArgumentException e) {
            result = e.getMessage();
        }
        System.out.println("\nTEST 04\n" + result);
        assertTrue(result.contains("Error: Missing the checkout request"));

        //null values case
        try {
            request = new CheckoutRequest(null, null, null, null);
            agreement = toolRentalManager.checkout(request);
            result = agreement.getFormattedAgreement(); //should never get here
        }
        catch (IllegalArgumentException e) {
            result = e.getMessage();
        }
        System.out.println("\nTEST 05\n" + result);
        assertTrue(result.contains("Error:"));
        assertTrue(result.contains("Missing the tool code."));
        assertTrue(result.contains("Missing the rental day count."));
        assertTrue(result.contains("Missing the discount percent."));
        assertTrue(result.contains("Missing the checkout date."));
    }

}
