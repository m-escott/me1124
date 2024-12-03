package com.toolrental.dao;

import com.toolrental.data.ToolCharge;

import java.util.List;

/**
 * The <b>{@code ToolChargeDAO}</b> interface provides methods to retrieve pricing definitions
 * for a tool rental system. Implementations of this interface are responsible for fetching
 * the actual data.
 *
 * <p>Use this interface to abstract the retrieval of data, ensuring
 * that clients can access consistent and reusable configurations.<p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public interface ToolChargeDAO {
    /**
     * Retrieves the pricing rules for a tool type.
     *
     * <p>The <b>{@code ToolCharge}</b> provides the standard pricing structure
     * for tool rentals, including daily rates, fees, and any applicable discounts
     * or surcharges.<p>
     *
     * @return the <b>{@code ToolCharge}</b> for the toolType.
     */
    public ToolCharge getToolCharge(String toolType);

    /**
     * Retrieves all <b>{@code ToolCharge}</b> listings for the rental system.
     *
     * @return a <b>{@code List<ToolCharge>}</b> for all tool charges in the system.
     */
    public List<ToolCharge> getAll();

}
