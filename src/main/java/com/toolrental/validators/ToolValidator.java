package com.toolrental.validators;

import com.toolrental.data.Tool;

/**
 * <b>{@code ToolValidator}</b> is responsible for validating instances of
 * the <b>{@code Tool}</b> class. This class extends <b>{@code BaseValidator}</b>,
 * utilizing its error-handling functionality to accumulate and format error messages
 * when validation rules are violated.
 *
 * <p>
 * The validation ensures that required attributes of a <b>{@code Tool}</b> object, such as
 * code, type, and brand, are not null. If any attribute is missing, a corresponding error
 * message is added to the error list, and an <b>{@code IllegalArgumentException}</b> is thrown.
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class ToolValidator extends BaseValidator {
    /**
     * Validates the attributes of a given <b>{@code Tool}</b> object.
     *
     * <p>
     * This method performs the following checks:
     * <ul>
     *     <li>Ensures the <b>{@code Tool}</b> object itself is not null.</li>
     *     <li>Checks that the <b>{@code code}</b>, <b>{@code type}</b>, and <b>{@code brand}</b>
     *         attributes of the <b>{@code Tool}</b> object are not null.</li>
     *     <li>If any validation rules are violated, an error message is added to
     *         the error list maintained by the <b>{@code BaseValidator}</b>.</li>
     *     <li>If there are errors, an <b>{@code IllegalArgumentException}</b> is thrown with
     *         a formatted error message.</li>
     * </ul>
     * <p>
     *
     * @param tool the <b>{@code Tool}</b> object to validate.
     * @throws IllegalArgumentException if the <b>{@code tool}</b> is null or any
     *                                  required attribute is missing.
     */
    public void validate(Tool tool) throws IllegalArgumentException {
        if (tool == null)
            throw new IllegalArgumentException("Error: Missing the tool or tool not found.");

        if (tool.getCode() == null)
            addError("The tool is missing its code.");

        if (tool.getType() == null)
            addError("The tool is missing its type.");

        if (tool.getBrand() == null)
            addError("The tool is missing its brand.");

        if (!errorMessages.isEmpty())
            throw new IllegalArgumentException(formatErrorMessage());
    }
}
