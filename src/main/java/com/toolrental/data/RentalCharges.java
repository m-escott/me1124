package com.toolrental.data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * <b>{@code RentalCharges}</b> is a bean class mainly with properties, setters and getters which contains the immediate output of the pricing lookup in <b>{@code ToolRentalManager.checkout()}</b>.
 * <p>
 * Once an instance is created, it is used by <b>{@code ToolRentalManager}</b> as the nucleus for building a <b>{@code RentalAgreement}</b> object.
 * <p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class RentalCharges {
    protected LocalDate dueDate;
    protected BigDecimal dailyCharge;
    protected int chargeDays;
    protected BigDecimal preDiscountCharge;
    protected BigDecimal discountAmount;
    protected BigDecimal finalCharge;

    public RentalCharges(LocalDate dueDate, BigDecimal dailyCharge, int chargeDays, BigDecimal preDiscountCharge, BigDecimal discountAmount, BigDecimal finalCharge) {
        this.dueDate = dueDate;
        this.dailyCharge = dailyCharge;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
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

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
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

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

}
