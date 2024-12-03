package com.toolrental.dao;

import com.toolrental.data.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <b>{@code ToolDAOMapImpl}</b> is a map implementation of the <b>{@code ToolDAO}</b> interface.
 * It provides an implementations for methods to retrieve a <b>{@code Tool}</b> for a toolCode.
 * This class is typically used for testing or development when a real database implementation is not required.
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class ToolDAOMapImpl implements ToolDAO {
    protected HashMap<String, Tool> tools = new HashMap<>();

    public ToolDAOMapImpl() {
        initTools();
    }

    /**
     * Initializes the map of <b>{@code Tool} elements with hardcoded values</b>
     */
    protected void initTools() {
        tools.put("CHNS", new Tool("CHNS", "Chainsaw", "Stihl"));
        tools.put("LADW", new Tool("LADW", "Ladder", "Werner"));
        tools.put("JAKD", new Tool("JAKD", "Jackhammer", "DeWalt"));
        tools.put("JAKR", new Tool("JAKR", "Jackhammer", "Ridgid"));
    }

    public Tool getTool(String toolCode) {
        return tools.get(toolCode);
    }

    public List<Tool> getAll() {
        return new ArrayList<>(tools.values());
    };

}
