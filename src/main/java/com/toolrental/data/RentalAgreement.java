package com.toolrental.data;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

/**
 * <b>{@code RentalAgreement}</b> is a bean class mainly with properties, setters and getters which contains the final output of <b>{@code ToolRentalManager.checkout()}</b>.
 * <p>
 * Once an instance is created, it is passed by <b>{@code ToolRentalManager}</b> back up to <b>{@code ToolRentalService}</b>. Once there, its <b>{@code checkout()}</b> method calls the <b>{@code getFormattedAgreement()}</b> method here to prepare the final output and response delivered to a user.
 * <p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class RentalAgreement {
    protected String toolCode;
    protected String toolType;
    protected String toolBrand;
    protected int rentalDays;
    protected LocalDate checkoutDate;
    protected LocalDate dueDate;
    protected BigDecimal dailyCharge;
    protected int chargeDays;
    protected BigDecimal preDiscountCharge;
    protected int discountPercent;
    protected BigDecimal discountAmount;
    protected BigDecimal finalCharge;

    public RentalAgreement(String toolCode, String toolType, String toolBrand, int rentalDays,
                           LocalDate checkoutDate, LocalDate dueDate, BigDecimal dailyCharge, int chargeDays,
                           BigDecimal preDiscountCharge, int discountPercent, BigDecimal discountAmount, BigDecimal finalCharge) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.dailyCharge = dailyCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public void setChargeDays(int chargeDays) {
        this.chargeDays = chargeDays;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public String getToolCode() {
        return toolCode;
    }

    public String getToolType() {
        return toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public int getChargeDays() {
        return chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    /**
     * Formats the object so that it's easy to read.
     *
     * @return {@link java.lang.String}
     */
    public String getFormattedAgreement() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(Locale.US);

        percentFormat.setMaximumFractionDigits(0);
        
        String agreement =
            "Tool code: " + toolCode + "\n" +
            "Tool type: " + toolType + "\n" +
            "Tool brand: " + toolBrand + "\n" +
            "Rental days: " + numberFormat.format(rentalDays) + "\n" +
            "Check out date: " + dateFormat.format(java.sql.Date.valueOf(checkoutDate)) + "\n" +
            "Due date: " + dateFormat.format(java.sql.Date.valueOf(dueDate)) + "\n" +
            "Daily rental charge: " + currencyFormat.format(dailyCharge) + "\n" +
            "Charge days: " + numberFormat.format(chargeDays) + "\n" +
            "Pre-discount charge: " + currencyFormat.format(preDiscountCharge) + "\n" +
            "Discount percent: " + percentFormat.format(discountPercent / 100.0) + "\n" +
            "Discount amount: " + currencyFormat.format(discountAmount) + "\n" +
            "Final charge: " + currencyFormat.format(finalCharge);

        return agreement;
    }
    
}

