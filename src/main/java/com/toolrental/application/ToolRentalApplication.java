package com.toolrental.application;

import com.toolrental.services.ToolRentalService;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

/**
 * This is a class with a static <b>{@code main()}</b> method that runs the application.<p>
 * It currently runs a single call to <b>{@code ToolRentalService.checkout()}</b> with some hardcoded parameters.
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class ToolRentalApplication {

    public static void main(String[] args) {
        ToolRentalService toolRentalService = new ToolRentalService();

        //call the toolRentalService asynchronously for the goal of scalability and better throughput
        CompletableFuture<String> checkoutResult = toolRentalService.checkout("LADW", 5, 20, LocalDate.of(2024, 9, 1));

        try {
            System.out.println(checkoutResult.get());
        }
        catch (Exception e) {
            System.out.println("The following error occurred: " + e.getMessage());
        }

        toolRentalService.shutdown();
    }

}
