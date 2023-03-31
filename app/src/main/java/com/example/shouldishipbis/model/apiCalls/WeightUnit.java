package com.example.shouldishipbis.model.apiCalls;

public enum WeightUnit {
    GRAMS("g"),
    POUNDS("lb"),
    KILOGRAMS("kg"),
    TONNES("mt");
    private final String sign;
    WeightUnit(String sign) {
        this.sign = sign;
    }

    public static WeightUnit stringToUnit(String s) {
        switch (s) {
            case "g" : return GRAMS;
            case "kg" : return KILOGRAMS;
            case "lb" : return POUNDS;
            case "mt" : return TONNES;
            default: throw new RuntimeException("Unexpected input into the Weight:stringToTransport : "+s);
        }
    }

    public String getSign() {
        return sign;
    }
}
