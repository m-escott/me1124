package com.toolrental.dao;

import com.toolrental.data.ToolCharge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <b>{@code ToolChargeDAOMapImpl}</b> is a map implementation of the <b>{@code ToolChargeDAO}</b> interface.
 * It provides an implementations for methods to retrieve a <b>{@code ToolCharge}</b> for a toolType.
 * This class is typically used for testing or development when a real database implementation is not required.
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class ToolChargeDAOMapImpl implements ToolChargeDAO {
    protected HashMap<String, ToolCharge> toolCharges = new HashMap<>();

    public ToolChargeDAOMapImpl() {
        initToolCharges();
    }

    /**
     * Initializes the map of <b>{@code ToolCharge} elements with hardcoded values</b>
     */
    protected void initToolCharges() {
        toolCharges.put("Ladder", new ToolCharge("Ladder", BigDecimal.valueOf(1.99), true, true, false));
        toolCharges.put("Chainsaw", new ToolCharge("Chainsaw", BigDecimal.valueOf(1.49), true, false, true));
        toolCharges.put("Jackhammer", new ToolCharge("Jackhammer", BigDecimal.valueOf(2.99), true, false, false));
    }

    public ToolCharge getToolCharge(String toolType) {
        return toolCharges.get(toolType);
    }

    public List<ToolCharge> getAll() {
        return new ArrayList<>(toolCharges.values());
    };

}
