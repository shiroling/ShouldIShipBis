package com.example.shouldishipbis.model.apiCalls;

public class CarbonData {
    String id;
    String type;
    double distance_value;
    String weight_unit;
    String transport_method;
    Double weight_value;
    String distance_unit;
    String estimated_at;
    Double carbon_g;
    Double carbon_lb;
    Double carbon_kg;
    Double carbon_mt;

    public CarbonData(String id, String type, double distance_value, String weight_unit,
                      String transport_method, Double weight_value, String distance_unit,
                      String estimated_at, Double carbon_g, Double carbon_lb, Double carbon_kg,
                      Double carbon_mt) {
        this.id = id;
        this.type = type;
        this.distance_value = distance_value;
        this.weight_unit = weight_unit;
        this.transport_method = transport_method;
        this.weight_value = weight_value;
        this.distance_unit = distance_unit;
        this.estimated_at = estimated_at;
        this.carbon_g = carbon_g;
        this.carbon_lb = carbon_lb;
        this.carbon_kg = carbon_kg;
        this.carbon_mt = carbon_mt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDistance_value() {
        return distance_value;
    }

    public void setDistance_value(double distance_value) {
        this.distance_value = distance_value;
    }

    public String getWeight_unit() {
        return weight_unit;
    }

    public void setWeight_unit(String weight_unit) {
        this.weight_unit = weight_unit;
    }

    public String getTransport_method() {
        return transport_method;
    }

    public void setTransport_method(String transport_method) {
        this.transport_method = transport_method;
    }

    public Double getWeight_value() {
        return weight_value;
    }

    public void setWeight_value(Double weight_value) {
        this.weight_value = weight_value;
    }

    public String getDistance_unit() {
        return distance_unit;
    }

    public void setDistance_unit(String distance_unit) {
        this.distance_unit = distance_unit;
    }

    public String getEstimated_at() {
        return estimated_at;
    }

    public void setEstimated_at(String estimated_at) {
        this.estimated_at = estimated_at;
    }

    public Double getCarbon_g() {
        return carbon_g;
    }

    public void setCarbon_g(Double carbon_g) {
        this.carbon_g = carbon_g;
    }

    public Double getCarbon_lb() {
        return carbon_lb;
    }

    public void setCarbon_lb(Double carbon_lb) {
        this.carbon_lb = carbon_lb;
    }

    public Double getCarbon_kg() {
        return carbon_kg;
    }

    public void setCarbon_kg(Double carbon_kg) {
        this.carbon_kg = carbon_kg;
    }

    public Double getCarbon_mt() {
        return carbon_mt;
    }

    public void setCarbon_mt(Double carbon_mt) {
        this.carbon_mt = carbon_mt;
    }
}
