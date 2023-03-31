package com.example.shouldishipbis.model.apiCalls;

public enum DistanceUnit {
    MILES("mi"),
    KILOMETERS("km");
    private final String sign;
    DistanceUnit(String sign) {
        this.sign = sign;
    }

    public static DistanceUnit stringToUnit(String s) {
        switch (s) {
            case "mi" : return MILES;
            case "km" : return KILOMETERS;
            default: throw new RuntimeException("Unexpected input into the Distance:stringToUnit : "+s);
        }
    }

    public String getSign() {
        return sign;
    }
}
