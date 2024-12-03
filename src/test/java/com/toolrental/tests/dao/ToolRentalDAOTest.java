package com.toolrental.tests.dao;

import com.toolrental.dao.ToolChargeDAO;
import com.toolrental.dao.ToolChargeDAOMapImpl;
import com.toolrental.dao.ToolDAO;
import com.toolrental.dao.ToolDAOMapImpl;
import com.toolrental.data.Tool;
import com.toolrental.data.ToolCharge;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToolRentalDAOTest {
    protected ToolDAO toolDAO = new ToolDAOMapImpl();
    protected ToolChargeDAO toolChargeDAO = new ToolChargeDAOMapImpl();

    @BeforeEach
    protected void setUp() {
        toolDAO = new ToolDAOMapImpl();
        toolChargeDAO = new ToolChargeDAOMapImpl();
    }

    @Test
    public void testInitTools() {
        assertEquals(4, toolDAO.getAll().size(), "Tool map should be initialized with 4 tools");
    }

    @Test
    public void testGetTool_ValidToolCode() {
        Tool tool = toolDAO.getTool("CHNS");
        assertNotNull(tool, "Tool should not be null");
        assertEquals("CHNS", tool.getCode(), "Tool code should be CHNS");
        assertEquals("Chainsaw", tool.getType(), "Tool type should be Chainsaw");
        assertEquals("Stihl", tool.getBrand(), "Brand should be Stihl");
    }

    @Test
    public void testGetTool_InvalidToolCode() {
        Tool tool = toolDAO.getTool("INVALID");
        assertNull(tool, "Tool should be null for an invalid tool code");
    }

    @Test
    public void testTool_GetAll() {
        List<Tool> tools = toolDAO.getAll();
        assertNotNull(tools, "List of tools should not be null");
        assertEquals(4, tools.size(), "There should be 4 tools in the list");
        assertTrue(tools.stream().anyMatch(tool -> tool.getCode().equals("CHNS")), "List should contain a tool with code CHNS");
        assertTrue(tools.stream().anyMatch(tool -> tool.getCode().equals("JAKR")), "List should contain a tool with code JAKR");
    }

    @Test
    public void testGetToolCharge_ValidToolType() {
        ToolCharge ladderCharge = toolChargeDAO.getToolCharge("Ladder");
        assertNotNull(ladderCharge, "ToolCharge for Ladder should not be null");
        assertEquals("Ladder", ladderCharge.getToolType());
        assertEquals(BigDecimal.valueOf(1.99), ladderCharge.getDailyCharge());
        assertTrue(ladderCharge.getHasWeekdayCharge());
        assertTrue(ladderCharge.getHasWeekendCharge());
        assertFalse(ladderCharge.getHasHolidayCharge());
    }

    @Test
    public void testGetToolCharge_InvalidToolType() {
        ToolCharge unknownCharge = toolChargeDAO.getToolCharge("Unknown");
        assertNull(unknownCharge, "ToolCharge for unknown tool type should be null");
    }

    @Test
    public void testToolCharge_GetAll() {
        List<ToolCharge> allCharges = toolChargeDAO.getAll();
        assertNotNull(allCharges, "List of all ToolCharges should not be null");
        assertEquals(3, allCharges.size(), "There should be 3 ToolCharge entries");
    }

    @Test
    public void testGetToolCharge_Chainsaw() {
        ToolCharge chainsawCharge = toolChargeDAO.getToolCharge("Chainsaw");
        assertNotNull(chainsawCharge, "ToolCharge for Chainsaw should not be null");
        assertEquals("Chainsaw", chainsawCharge.getToolType());
        assertEquals(BigDecimal.valueOf(1.49), chainsawCharge.getDailyCharge());
        assertTrue(chainsawCharge.getHasWeekdayCharge());
        assertFalse(chainsawCharge.getHasWeekendCharge());
        assertTrue(chainsawCharge.getHasHolidayCharge());
    }

    @Test
    public void testGetToolCharge_Jackhammer() {
        ToolCharge jackhammerCharge = toolChargeDAO.getToolCharge("Jackhammer");
        assertNotNull(jackhammerCharge, "ToolCharge for Jackhammer should not be null");
        assertEquals("Jackhammer", jackhammerCharge.getToolType());
        assertEquals(BigDecimal.valueOf(2.99), jackhammerCharge.getDailyCharge());
        assertTrue(jackhammerCharge.getHasWeekdayCharge());
        assertFalse(jackhammerCharge.getHasWeekendCharge());
        assertFalse(jackhammerCharge.getHasHolidayCharge());
    }

    @Test
    public void testInitToolCharges() {
        assertEquals(3, toolChargeDAO.getAll().size(), "Tool Charge map should be initialized with 3 charges");
    }

}
