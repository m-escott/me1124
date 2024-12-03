package com.toolrental.tests.services;

import com.toolrental.services.ToolRentalService;

import org.junit.Test;

import static com.toolrental.validators.CheckoutRequestValidator.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

public class ToolRentalServiceTest {

    @Test
    public void testService() {
        ToolRentalService toolRentalService = new ToolRentalService();

        CompletableFuture<String> checkoutResult;

        try {
            //base case
            checkoutResult = toolRentalService.checkout("LADW", 5, 20, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 01\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Final charge: $6.37"));

            //bad tool code
            checkoutResult = toolRentalService.checkout("LADZ", 5, 20, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 02\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Error: Missing the tool"));

            //low rental days
            checkoutResult = toolRentalService.checkout("LADW", MIN_RENTAL_DAYS - 1, 20, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 03\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Error: Rental day count entered"));

            //high rental days
            checkoutResult = toolRentalService.checkout("LADW", MAX_RENTAL_DAYS + 1, 20, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 03\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Error: Rental day count entered"));

            //low discount percent
            checkoutResult = toolRentalService.checkout("LADW", 5, MIN_DISCOUNT_PCT - 1, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 04\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Error: Discount percent entered"));

            //high discount percent
            checkoutResult = toolRentalService.checkout("LADW", 5, MAX_DISCOUNT_PCT + 1, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 05\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Error: Discount percent entered"));

            //bad everything
            checkoutResult = toolRentalService.checkout("LADZ", -2, -7, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 06\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Error:"));
            assertTrue(checkoutResult.get().contains("Rental day count entered"));
            assertTrue(checkoutResult.get().contains("Discount percent entered"));

            //test chainsaw
            checkoutResult = toolRentalService.checkout("CHNS", 5, 20, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 07\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Final charge: $5.96"));

            //test jackhammer
            checkoutResult = toolRentalService.checkout("JAKD", 5, 20, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 08\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Final charge: $9.57"));

            //test ladder July 4, 20 days, w/o discount (charged for weekends, not for holiday)
            checkoutResult = toolRentalService.checkout("LADW", 20, 0, LocalDate.of(2024, 7, 1));
            System.out.println("\nTEST 09\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Final charge: $37.81"));

            //test chainsaw July 4, 20 days, w/o discount (charged for holiday, not for weekends)
            checkoutResult = toolRentalService.checkout("CHNS", 20, 0, LocalDate.of(2024, 7, 1));
            System.out.println("\nTEST 10\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Final charge: $20.86"));

            //test jackhammer July 4, 20 days, w/o discount (not charged for holiday or weekends)
            checkoutResult = toolRentalService.checkout("JAKD", 20, 0, LocalDate.of(2024, 7, 1));
            System.out.println("\nTEST 11\n" + checkoutResult.get());
            assertTrue(checkoutResult.get().contains("Final charge: $38.87"));

            //base case but with very large rentalDays value (before MAX_RENTAL_DAYS existed)
            /*
            long time1 = System.currentTimeMillis();
            checkoutResult = toolRentalService.checkout("LADW", 50000000, 20, LocalDate.of(2024, 9, 1));
            System.out.println("\nTEST 12\n" + checkoutResult.get());
            long time2 = System.currentTimeMillis();
            System.out.println("ELAPSED milliseconds = " + (time2 - time1)); //5*10^2:1, 5*10^3:4, 5*10^4:14, 5*10^5:72, 5*10^6:413, 5*10^7:3,739, 5*10^8:36,705
            */
            //tried rentalDays = Integer.MAX_VALUE and was very slow - probably around 3 minutes
        }
        catch (Exception e) {
            fail("The following error occurred: " + e.getMessage());
        }
        finally {
            toolRentalService.shutdown();
        }
    }

}
