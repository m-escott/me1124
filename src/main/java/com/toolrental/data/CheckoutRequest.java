package com.toolrental.data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * <b>{@code CheckoutRequest}</b> is a bean class mainly with properties, setters and getters which contains a user request for a tool rental.
 * <p>
 * It is passed from <b>{@code ToolRentalService.checkout()}</b> to <b>{@code ToolRentalService.checkout()}</b> where it is processed into a <b>{@code RentalAgreement}</b>.
 * <p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class CheckoutRequest {
    protected String toolCode;
    protected Integer rentalDays;
    protected Integer discountPercent;
    protected LocalDate checkoutDate;

    public CheckoutRequest(String toolCode, Integer rentalDays, Integer discountPercent, LocalDate checkoutDate) {
        this.toolCode = toolCode;
        this.rentalDays = rentalDays;
        this.discountPercent = discountPercent;
        this.checkoutDate = checkoutDate;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public String getToolCode() {
        return toolCode;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        return "Checkout request received: " +
               "Tool code = " + (toolCode != null ? toolCode : "N.A.") + "; " +
               "Rental days = " + (rentalDays != null ? rentalDays : "N.A.") + "; " +
               "Discount % = " + (discountPercent != null ? discountPercent : "N.A.") + "; " +
               "Checkout date = " + (checkoutDate != null ? dateFormat.format(java.sql.Date.valueOf(checkoutDate)) : "N.A.");
    }
}

