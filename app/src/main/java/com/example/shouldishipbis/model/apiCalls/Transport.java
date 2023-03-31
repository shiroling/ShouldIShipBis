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

    public static Transport stringToTransport(String s) {
        switch (s) {
            case "ship" : return Transport.SHIP;
            case "train" : return Transport.TRAIN;
            case "truck" : return Transport.TRUCK;
            case "plane" : return Transport.PLANE;
            default: throw new RuntimeException("Unexpected input into the stringToTransport : "+s);
        }
    }
}
