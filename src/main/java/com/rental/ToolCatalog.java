package com.rental;

import com.rental.model.Tool;
import com.rental.model.ToolType;

import java.util.HashMap;
import java.util.Map;

public final class ToolCatalog {
    private static final Map<String, Tool> TOOLS = new HashMap<>();
    static {
        TOOLS.put("CHNS", new Tool("CHNS", ToolType.CHAINSAW, "Stihl"));
        TOOLS.put("LADW", new Tool("LADW", ToolType.LADDER, "Werner"));
        TOOLS.put("JAKD", new Tool("JAKD", ToolType.JACKHAMMER, "DeWalt"));
        TOOLS.put("JAKR", new Tool("JAKR", ToolType.JACKHAMMER, "Ridgid"));
    }

    private ToolCatalog() {}

    public static Tool get(String code) {
        Tool t = TOOLS.get(code);
        if (t == null) throw new IllegalArgumentException("Unknown tool code: " + code);
        return t;
    }
}
