package com.toolrental.managers;

import com.toolrental.dao.ToolChargeDAO;
import com.toolrental.dao.ToolChargeDAOMapImpl;
import com.toolrental.dao.ToolDAO;
import com.toolrental.dao.ToolDAOMapImpl;
import com.toolrental.data.*;
import com.toolrental.util.DateUtils;
import com.toolrental.validators.CheckoutRequestValidator;
import com.toolrental.validators.ToolChargeValidator;
import com.toolrental.validators.ToolValidator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * This is a class with the main public <b>{@code checkout(CheckoutRequest)}</b> <b>manager</b> method that will perform the actual business logic to either return a:<br>
 * - <b>{@code RentalAgreement}</b> if successful <b>OR</b><br>
 * - a meaningful error message if unsuccessful<br>
 * <p>
 * The code first validates the CheckoutRequest and its implicit Tool and ToolCharge data with their respective three validators.<BR>
 * Then it calls <b>{@code calculateCharges()}</b> and <b>{@code calculateChargeDays()}</b> with information from the request and lookup values.<BR>
 * It currently is only called from <b>{@code ToolRentalService.checkout()}</b>..
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class ToolRentalManager {
    protected ToolDAO toolDAO = new ToolDAOMapImpl();
    protected ToolChargeDAO toolChargeDAO = new ToolChargeDAOMapImpl();

    /**
     * Processes a rental checkout request and generates a rental agreement.
     * Validates the request, retrieves tool and pricing information, and calculates
     * rental charges based on the provided input.
     *
     * @param request the <b>{@code CheckoutRequest}</b> containing details about the tool rental.
     * @return a <b>{@code RentalAgreement}</b> detailing the rental terms and calculated charges.
     * @throws IllegalArgumentException if the request, tool, or pricing data is invalid.
     */
    public RentalAgreement checkout(CheckoutRequest request) throws IllegalArgumentException {
        //todo - in an actual live system, logging messages would be included at significant intervals using a framework such as Log4j. See more on this in the system architecture document.

        //validate the checkout request
        CheckoutRequestValidator checkoutValidator = new CheckoutRequestValidator();
        checkoutValidator.validate(request); //check for improper nulls and illegal values in the request, will throw exception if invalid

        //lookup and validate tool info
        Tool tool = toolDAO.getTool(request.getToolCode()); //toolCode is non-null having passed through checkoutValidator
        ToolValidator toolValidator = new ToolValidator();
        toolValidator.validate(tool); //check for improper nulls and illegal values in the tool definition found, will throw exception if invalid

        //lookup and validate toolCharge info
        ToolCharge toolCharge = toolChargeDAO.getToolCharge(tool.getType()); //tool is non-null having passed through toolValidator
        ToolChargeValidator toolChargeValidator = new ToolChargeValidator();
        toolChargeValidator.validate(toolCharge); //check for improper nulls and illegal values in the tool charge definition found, will throw exception if invalid

        RentalCharges charges = calculateCharges(request, toolCharge);

        return new RentalAgreement(
                request.getToolCode(), tool.getType(), tool.getBrand(), request.getRentalDays(), request.getCheckoutDate(),
                charges.getDueDate(), charges.getDailyCharge(), charges.getChargeDays(), charges.getPreDiscountCharge(),
                request.getDiscountPercent(), charges.getDiscountAmount(), charges.getFinalCharge()
        );
    }

    //keep business logic calculators in this class

    //this could have been protected but to enable unit tests this was made public
    /**
     * Calculates the rental charges based on the checkout request and tool pricing.
     * Includes calculations for total charges, discounts, and the final amount due.
     *
     * @param request the <b>{@code CheckoutRequest}</b> containing details about the rental.
     * @param pricing the <b>{@code ToolCharge}</b> object containing pricing rules for the tool.
     * @return a <b>{@code RentalCharges}</b> object encapsulating detailed rental cost breakdowns.
     */
    public RentalCharges calculateCharges(CheckoutRequest request, ToolCharge pricing) {
        //final charge = daily charge * charge days * ((100 - discount%)/100)
        LocalDate dueDate = request.getCheckoutDate().plusDays(request.getRentalDays());
        int chargeDays = calculateChargeDays(request.getCheckoutDate(), dueDate, pricing);
        BigDecimal dailyCharge = pricing.getDailyCharge();
        BigDecimal preDiscountCharge = dailyCharge.multiply(BigDecimal.valueOf(chargeDays)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(request.getDiscountPercent())).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);
        return new RentalCharges(dueDate, dailyCharge, chargeDays, preDiscountCharge, discountAmount, finalCharge);
    }

    //this could have been protected but to enable unit tests this was made public
    /**
     * Computes the number of chargeable days for a tool rental within the specified date range.
     * Takes into account weekdays, weekends, and holidays, based on the pricing configuration.
     *
     * @param checkoutDate the starting date of the rental period.
     * @param dueDate the ending date of the rental period.
     * @param pricing the <b>{@code ToolCharge}</b> object containing rules for chargeable days.
     * @return the number of days the tool is chargeable based on the pricing configuration.
     */
    public int calculateChargeDays(LocalDate checkoutDate, LocalDate dueDate, ToolCharge pricing) {
        int chargeableDays = 0;

        //loop through each day of the rental; identify the date type; add to chargeableDays if pricing rules determine that the day is chargeable
        for (LocalDate date = checkoutDate.plusDays(1); !date.isAfter(dueDate); date = date.plusDays(1)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            boolean isHoliday = DateUtils.isHoliday(date);
            boolean isWeekend = DateUtils.isWeekend(dayOfWeek);
            boolean isRegularWeekday = !isWeekend && !isHoliday; //accounts for Labor Day and other weekday holidays

            if ((pricing.getHasWeekdayCharge() && isRegularWeekday) ||
                (pricing.getHasWeekendCharge() && isWeekend) ||
                (pricing.getHasHolidayCharge() && isHoliday)) {
                chargeableDays++;
            }
        }
        return chargeableDays;
    }

}
