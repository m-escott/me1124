package com.toolrental.services;

import com.toolrental.data.CheckoutRequest;
import com.toolrental.data.RentalAgreement;
import com.toolrental.managers.ToolRentalManager;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is a class with a single <b>{@code ToolRentalManager.checkout(CheckoutRequest)}</b> method that will return text based on a <b>{@code RentalAgreement}</b> if legal parameters are passed
 * and things behave as expected. If not, then it will return text containing basic <b>{@code CheckoutRequest}</b> details along with an error message.
 * <p>
 * It currently is called from <b>{@code ToolRentalApplication.main()}</b>.
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */

//todo - in the future, this can be turned into a Spring REST web service to listen for requests
public class ToolRentalService {
    /**
     * Adjust maximum threads for pool size as needed.
     */
    protected final int MAX_POOL_SIZE = 10;

    /**
     * Thread pool for handling concurrent requests (tunable for optimal performance).
     */
    protected final ExecutorService executorService = Executors.newFixedThreadPool(MAX_POOL_SIZE);

    /**
     * A manager responsible for handling the core tool rental operations.
     */
    protected ToolRentalManager toolRentalManager = new ToolRentalManager();

    //todo - if turn into web service can use Spring Web Services and establish an endpoint like @RequestMapping("/checkout") and restrict access by authenticating an API key

    /**
     * Processes the checkout of a tool by generating a rental agreement based on the provided rental parameters.
     *
     * <p>The method takes details such as the tool code, rental duration, discount percentage, and checkout date,
     * and attempts to create a rental agreement. In the case of errors, an appropriate message is returned.</p>
     *
     * @param toolCode       the code identifying the tool to be rented
     * @param rentalDays     the number of days the tool will be rented
     * @param discountPercent the discount percentage to be applied to the rental price
     * @param checkoutDate   the date when the rental begins
     * @return a <b>{code CompletableFuture<String>}</b> with a formatted rental agreement if successful; otherwise, an error message with details
     */
    public CompletableFuture<String> checkout(String toolCode, int rentalDays, int discountPercent, LocalDate checkoutDate) {
        return CompletableFuture.supplyAsync(() -> {
            String result;
            CheckoutRequest request = new CheckoutRequest(toolCode, rentalDays, discountPercent, checkoutDate);
            try {
                RentalAgreement agreement = toolRentalManager.checkout(request);
                result = agreement.getFormattedAgreement(); //will arrive here if no errors encountered
            }
            catch (IllegalArgumentException e) {
                result = request + "\n" + e.getMessage(); //seeing the request details can help understand the errors received
            }
            catch (Exception e) { //in case something unanticipated goes wrong
                result = request + "\n" + "The following unexpected error occurred: " + e.getMessage();
            }
            return result;
        }, executorService);
    }

    /**
     * Proper resource cleanup for the thread pool to prevent memory leaks.
     */
    public void shutdown() {
        executorService.shutdown();
    }

}
