package com.toolrental.validators;

import com.toolrental.data.ToolCharge;

/**
 * <p>
 * Validates the properties of a {@code ToolCharge} instance to ensure all required
 * attributes are properly populated. This class extends {@code BaseValidator} and
 * provides a concrete implementation of the validation logic for tool charge data.
 * </p>
 *
 * <p>
 * The validation checks include:
 * <ul>
 *   <li>Presence of the tool type.</li>
 *   <li>Presence of the daily charge.</li>
 *   <li>Presence of flags for weekday, weekend, and holiday charges.</li>
 * </ul>
 *
 * <p>
 * If any of the validations fail, the {@code validate} method throws an
 * {@code IllegalArgumentException} containing detailed error messages.
 * <p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class ToolChargeValidator extends BaseValidator {
    /**
     * Validates a {@code ToolCharge} instance to ensure all necessary attributes
     * are defined and meet the required conditions.
     *
     * @param toolCharge the {@code ToolCharge} object to be validated
     * @throws IllegalArgumentException if the {@code toolCharge} object is null or
     *         any of its required fields are missing or invalid. The exception
     *         message contains a detailed summary of validation errors.
     *
     * <p>
     * The following validations are performed:
     * <ul>
     *   <li>Checks if the {@code toolCharge} object is null.</li>
     *   <li>Ensures that the {@code toolType} field is not null.</li>
     *   <li>Ensures that the {@code dailyCharge} field is not null.</li>
     *   <li>Validates the {@code hasWeekdayCharge}, {@code hasWeekendCharge},
     *       and {@code hasHolidayCharge} flags.</li>
     * </ul>
     * <p>
     *
     * Example usage:
     * <pre>{@code
     * ToolCharge toolCharge = new ToolCharge();
     * toolCharge.setToolType("Ladder");
     * toolCharge.setDailyCharge(100.0);
     * toolCharge.setHasWeekdayCharge(true);
     * toolCharge.setHasWeekendCharge(false);
     * toolCharge.setHasHolidayCharge(false);
     *
     * ToolChargeValidator validator = new ToolChargeValidator();
     * try {
     *     validator.validate(toolCharge);
     * } catch (IllegalArgumentException e) {
     *     System.err.println(e.getMessage());
     * }
     * }</pre>
     */
    public void validate(ToolCharge toolCharge) throws IllegalArgumentException {
        if (toolCharge == null)
            throw new IllegalArgumentException("Error: Missing the tool charge or tool charge not found.");

        if (toolCharge.getToolType() == null)
            addError("The tool charge missing its tool type.");

        if (toolCharge.getDailyCharge() == null)
            addError("The tool charge is missing its daily charge.");

        if (toolCharge.getHasWeekdayCharge() == null)
            addError("The tool charge is missing its weekday charge flag.");

        if (toolCharge.getHasWeekendCharge() == null)
            addError("The tool charge is missing its weekend charge flag.");

        if (toolCharge.getHasHolidayCharge() == null)
            addError("The tool charge is missing its holiday charge flag.");

        if (!errorMessages.isEmpty())
            throw new IllegalArgumentException(formatErrorMessage());
    }
}
