package com.toolrental.dao;

import com.toolrental.data.Tool;

import java.util.List;

/**
 * The <b>{@code ToolDAO}</b> interface provides methods to retrieve tool definitions
 * for a tool rental system. Implementations of this interface are responsible for fetching
 * the actual data.
 *
 * <p>Use this interface to abstract the retrieval of data, ensuring
 * that clients can access consistent and reusable configurations.<p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public interface ToolDAO {
    /**
     * Retrieves a tool definition for a tool code.
     *
     * <p>The <b>{@code Tool}</b> provides the properties of a tool.<p>
     *
     * @return the <b>{@code Tool}</b> for the toolCode.
     */
    public Tool getTool(String toolCode);

    /**
     * Retrieves all <b>{@code Tool}</b> listings for the rental system.
     *
     * @return a <b>{@code List<Tool>}</b> for all tools in the system.
     */
    public List<Tool> getAll();

}
