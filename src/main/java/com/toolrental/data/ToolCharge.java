package com.toolrental.data;

import java.math.BigDecimal;

/**
 * <b>{@code ToolCharge}</b> is a bean class mainly with properties, setters and getters which contains definitions for tool rental pricing.
 * <p>
 * The main repository of tool charge definitions is accessible from <b>{@code ToolChargeDAO.getToolCharge(toolType)}</b>.
 * <p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class ToolCharge {
    protected String toolType;
    protected BigDecimal dailyCharge;
    protected Boolean hasWeekdayCharge;
    protected Boolean hasWeekendCharge;
    protected Boolean hasHolidayCharge;

    public ToolCharge(String toolType, BigDecimal dailyCharge, Boolean hasWeekdayCharge, Boolean hasWeekendCharge, Boolean hasHolidayCharge) {
        this.toolType = toolType;
        this.dailyCharge = dailyCharge;
        this.hasWeekdayCharge = hasWeekdayCharge;
        this.hasWeekendCharge = hasWeekendCharge;
        this.hasHolidayCharge = hasHolidayCharge;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public void setDailyCharge(BigDecimal dailyCharge) {
        this.dailyCharge = dailyCharge;
    }

    public void setHasWeekdayCharge(Boolean hasWeekdayCharge) {
        this.hasWeekdayCharge = hasWeekdayCharge;
    }

    public void setHasWeekendCharge(Boolean hasWeekendCharge) {
        this.hasWeekendCharge = hasWeekendCharge;
    }

    public void setHasHolidayCharge(Boolean hasHolidayCharge) {
        this.hasHolidayCharge = hasHolidayCharge;
    }

    public String getToolType() {
        return toolType;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public Boolean getHasWeekdayCharge() {
        return hasWeekdayCharge;
    }

    public Boolean getHasWeekendCharge() {
        return hasWeekendCharge;
    }

    public Boolean getHasHolidayCharge() {
        return hasHolidayCharge;
    }

    @Override
    public String toString() { //mainly for testing purposes now
        return toolType + ":" + dailyCharge + ":" + hasWeekdayCharge + ":" + hasWeekendCharge+ ":" + hasHolidayCharge;
    }
}
