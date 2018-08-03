package com.vk.demo.api2.controllers.mocks;

import com.vk.demo.api2.model.Gadget;

public class GadgetMock {

    private static final String MOCK_ID = "mockId";
    private static final String MOCK_TYPE = "mockType";
    private static final String MOCK_SPECIFICATIONS = "mockSpecifications";

    public static Gadget createGadget() {
        return new Gadget(MOCK_ID, MOCK_TYPE, MOCK_SPECIFICATIONS);
    }

    public static Gadget createGadget(String type, String specs) {
        return new Gadget(MOCK_ID, type, specs);
    }

    public static String createGadgetRawJsonObject(Gadget gadget) {
        return "{\"id\":" + gadget.getId() + "," +
                "\"type\":" + gadget.getType() + "," +
                "\"specifications\":" + gadget.getSpecifications() + "}\n";
    }
    public static String createGadgetRawJsonObject() {
        return "{\"id\":" + MOCK_ID + "," +
                "\"type\":" + MOCK_TYPE + "," +
                "\"specifications\":" + MOCK_SPECIFICATIONS + "}\n";
    }


}
