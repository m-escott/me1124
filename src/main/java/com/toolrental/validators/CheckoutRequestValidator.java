package com.toolrental.validators;

import com.toolrental.data.CheckoutRequest;

/**
 * <b>{@code CheckoutRequestValidator}</b> is responsible for validating the fields of a
 * <b>{@code CheckoutRequest}</b>. It ensures that the input data adheres to defined constraints
 * such as rental days, discount percentage, tool code, and checkout date.
 * If any field is invalid or missing, it throws an <b>{@code IllegalArgumentException}</b>
 * with a detailed error message.
 *
 * <p>Validation constraints include:</p>
 * <ul>
 *     <li>Rental days must be between <b>{@code MIN_RENTAL_DAYS}</b> and
 *         <b>{@code MAX_RENTAL_DAYS}</b>.</li>
 *     <li>Discount percentage must be between <b>{@code MIN_DISCOUNT_PCT}</b> and
 *         <b>{@code MAX_DISCOUNT_PCT}</b>.</li>
 *     <li>All required fields such as tool code, rental days, discount percentage,
 *         and checkout date must be present.</li>
 * </ul>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class CheckoutRequestValidator extends BaseValidator {
    public static final Integer MIN_RENTAL_DAYS = 1;
    public static final Integer MAX_RENTAL_DAYS = 4000; //this amount done as a sanity check - allows for max ~ 11 year rental
    public static final Integer MIN_DISCOUNT_PCT = 0;
    public static final Integer MAX_DISCOUNT_PCT = 100;

    /**
     * Validates the specified <b>{@code CheckoutRequest}</b>.
     * <p>This method checks that all required fields of the request are present
     * and conform to the defined constraints. If any validation fails,
     * an <b>{@code IllegalArgumentException}</b> is thrown with a detailed error message.</p>
     *
     * @param request the <b>{@code CheckoutRequest}</b> object to validate
     * @throws IllegalArgumentException if the request is null or any field violates
     *                                  the validation constraints
     */
    public void validate(CheckoutRequest request) throws IllegalArgumentException {
        if (request == null)
            throw new IllegalArgumentException("Error: Missing the checkout request.");

        Integer rentalDays, discountPercent;

        if (request.getToolCode() == null)
            addError("Missing the tool code.");

        if ((rentalDays = request.getRentalDays()) == null)
            addError("Missing the rental day count.");
        else if (rentalDays  < MIN_RENTAL_DAYS || rentalDays > MAX_RENTAL_DAYS)
            addError("Rental day count entered is " + rentalDays + " but it must be between " + MIN_RENTAL_DAYS + " and " + MAX_RENTAL_DAYS + ".");

        if ((discountPercent = request.getDiscountPercent()) == null)
            addError("Missing the discount percent.");
        else if (discountPercent < MIN_DISCOUNT_PCT || discountPercent > MAX_DISCOUNT_PCT)
            addError("Discount percent entered is " + discountPercent + " but it must be between " + MIN_DISCOUNT_PCT + " and " + MAX_DISCOUNT_PCT + ".");

        if (request.getCheckoutDate() == null)
            addError("Missing the checkout date.");

        if (!errorMessages.isEmpty())
            throw new IllegalArgumentException(formatErrorMessage());
    }

}
