package com.toolrental.validators;

import java.util.ArrayList;

/**
 * <b>{@code BaseValidator}</b> serves as an abstract base class for implementing custom validation logic.
 * It provides common functionality for managing and formatting error messages encountered during validation.
 *
 * <p>This class maintains a list of error messages and offers utility methods to add errors and
 * generate a formatted string representation of these errors.</p>
 *
 * <p>To utilize this class, extend it and implement specific validation logic within a subclass.</p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public abstract class BaseValidator {
    /**
     * A list of error messages collected during the validation process.
     */
    protected ArrayList<String> errorMessages = new ArrayList<>();

    /**
     * Adds an error message to the list of {@code errorMessages}.
     *
     * @param messageItem the error message to be added.
     *                    This message provides details about a specific validation failure.
     */
    protected void addError(String messageItem) {
        errorMessages.add(messageItem);
    }

    /**
     * Formats the accumulated error messages into a single string representation.
     *
     * <p>If there is only one error message, it is appended directly after "Error:".
     * Otherwise, each error message is placed on a new line following "Error:".</p>
     *
     * @return a formatted string containing all the error messages, prefixed by "Error:".
     */
    protected String formatErrorMessage() {
        String formattedMessage = "Error: ";

        for (String messageItem : errorMessages)
            formattedMessage += (errorMessages.size() == 1 ? "" : "\n") + messageItem; //don't add a new line if just a single message

        return formattedMessage;
    }
}
