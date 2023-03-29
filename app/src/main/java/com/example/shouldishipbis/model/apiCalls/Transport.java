package com.example.shouldishipbis.model.apiCalls;

public enum Transport {
    SHIP("ship"),
    TRAIN("train"),
    TRUCK("truck"),
    PLANE("plane");
    private final String transportName;
    Transport(String transportName) {
        this.transportName = transportName;
    }
    public String getTransportName() {
        return transportName;
    }
}
