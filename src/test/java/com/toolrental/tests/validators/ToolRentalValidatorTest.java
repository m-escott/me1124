package com.toolrental.tests.validators;

import com.toolrental.dao.ToolChargeDAO;
import com.toolrental.dao.ToolChargeDAOMapImpl;
import com.toolrental.dao.ToolDAO;
import com.toolrental.dao.ToolDAOMapImpl;
import com.toolrental.data.*;
import com.toolrental.validators.CheckoutRequestValidator;
import com.toolrental.validators.ToolChargeValidator;
import com.toolrental.validators.ToolValidator;
import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ToolRentalValidatorTest {
    protected ToolDAO toolDAO = new ToolDAOMapImpl();
    protected ToolChargeDAO toolChargeDAO = new ToolChargeDAOMapImpl();

    @Test
    public void testValidators() {
        String toolCode = "LADW";
        LocalDate checkoutDate = LocalDate.of(2024, 9, 1);
        CheckoutRequest request = new CheckoutRequest(toolCode, 5, 20, checkoutDate);
        Tool tool = new Tool("LADW", "Ladder", "Werner");
        ToolCharge toolCharge = new ToolCharge("Ladder", BigDecimal.valueOf(1.99), true, true, false);

        try {
            CheckoutRequestValidator checkoutValidator = new CheckoutRequestValidator();
            checkoutValidator.validate(request);

            tool = toolDAO.getTool(toolCode); //this is safe and toolCode is non-null having passed through checkoutValidator
            ToolValidator toolValidator = new ToolValidator();
            toolValidator.validate(tool);  //check null and check other stuff

            toolCharge = toolChargeDAO.getToolCharge(tool.getType()); //this is safe and toolType is non-null having passed through toolValidator
            ToolChargeValidator toolChargeValidator = new ToolChargeValidator();
            toolChargeValidator.validate(toolCharge);  //check null and check other stuff
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        assertNotNull(tool);
        assertNotNull(toolCharge);

        System.out.println("Tool found: {" + tool + "}");
        System.out.println("Tool charge found: {" + toolCharge + "}");
    }

}
