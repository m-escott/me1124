package com.toolrental.data;

/**
 * <b>{@code Tool}</b> is a bean class mainly with properties, setters and getters which contains the tool definitions.
 * <p>
 * Tool definitions are accessible from <b>{@code ToolDAO.getTool(toolCode)}</b>.
 * <p>
 * @author Michael Escott
 * @version $Revision: 1.0
 * $Date: 2024/12/03 12:00:00
 */
public class Tool {
    protected String code;
    protected String type;
    protected String brand;

    public void setCode(String code) {
        this.code = code;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Tool(String code, String type, String brand) {
        this.code = code;
        this.type = type;
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() { //mainly for testing purposes now
        return code + ":" + type + ":" + brand;
    }
}
