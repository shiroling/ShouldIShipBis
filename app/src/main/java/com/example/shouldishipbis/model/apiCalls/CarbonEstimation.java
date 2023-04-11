package com.example.shouldishipbis.model.apiCalls;

import android.content.Context;

import com.example.shouldishipbis.R;
import com.example.shouldishipbis.model.localDatabase.EstimateDAO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class CarbonEstimation implements Serializable {
    private static final String API_URL = "https://www.carboninterface.com/api/v1/estimates";
    private String id;
    private String name;
    private Transport transport;
    private double weight;
    private double distance;
    private WeightUnit weightUnit;
    private DistanceUnit distanceUnit;
    // private  "estimated_at": "2020-07-31T13:00:04.446Z",  je sais pas quoi mettre comme datatype
    private String estimationDate;
    private double carbonLb;
    private double carbonKg;
    private double carbonMt;

    // Instance nécessaires au traitement (pour Retrofit)


    public CarbonEstimation() {
    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    public CarbonEstimation requestEstimation(Context context, String name, Transport transport, float weight, float distance, WeightUnit weightUnit, DistanceUnit distanceUnit) /*throws IOException, JSONException, ParseException */{
        this.name = name;
        this.transport = transport;
        this.weight = weight;
        this.distance = distance;
        this.weightUnit = weightUnit;
        this.distanceUnit = distanceUnit;

        setId(hashCode(this));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_HH:mm.ss", Locale.getDefault());
        setEstimationDate(sdf.format(new Date()));
        setCarbonLb(25 + Math.random() * (42 - 25));
        setCarbonKg(0.05 + Math.random() * (10 - 0.05));
        setCarbonMt(0.005 + Math.random() * (400 - 0.005));
        EstimateDAO eDao = new EstimateDAO(context);
        eDao.insertEstimate(this);

        return this;
    }

    private String hashCode(CarbonEstimation carbonEstimation) {
        return String.valueOf(Objects.hash(id, name, transport, weight, distance, weightUnit, distanceUnit, estimationDate, carbonLb, carbonKg, carbonMt));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(WeightUnit weightUnit) {
        this.weightUnit = weightUnit;
    }

    public DistanceUnit getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(DistanceUnit distanceUnit) {
        this.distanceUnit = distanceUnit;
    }
    public String getEstimationDate() {
        return estimationDate;
    }

    public void setEstimationDate(String estimationDate) {
        this.estimationDate = estimationDate;
    }

    public double getCarbonLb() {
        return carbonLb;
    }

    public void setCarbonLb(double carbonLb) {
        this.carbonLb = carbonLb;
    }

    public double getCarbonKg() {
        return carbonKg;
    }

    public void setCarbonKg(double carbonKg) {
        this.carbonKg = carbonKg;
    }

    public double getCarbonMt() {
        return carbonMt;
    }

    public void setCarbonMt(double carbonMt) {
        this.carbonMt = carbonMt;
    }

    @Override
    public String toString() {
        return "CarbonEstimation{" +
                "id='" + id + '\'' +
                ", name=" + name +
                ", transport=" + transport +
                ", weight=" + weight +
                ", distance=" + distance +
                ", weightUnit=" + weightUnit +
                ", distanceUnit=" + distanceUnit +
                ", estimationDate='" + estimationDate + '\'' +
                ", carbonLb=" + carbonLb +
                ", carbonKg=" + carbonKg +
                ", carbonMt=" + carbonMt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarbonEstimation that = (CarbonEstimation) o;
        return Double.compare(that.weight, weight) == 0 && Double.compare(that.distance, distance) == 0 && Double.compare(that.carbonLb, carbonLb) == 0 && Double.compare(that.carbonKg, carbonKg) == 0 && Double.compare(that.carbonMt, carbonMt) == 0 && id.equals(that.id) && transport == that.transport && weightUnit == that.weightUnit && distanceUnit == that.distanceUnit && estimationDate.equals(that.estimationDate);
    }

    public int getTransportEmojiId() {
        switch (transport) {
            case PLANE : { return R.string.planeEmoji; }
            case SHIP  : { return R.string.boatEmoji;  }
            case TRUCK : { return R.string.truckEmoji; }
            case TRAIN : { return R.string.trainEmoji; }
            default: throw new RuntimeException("Unexpected in switch");
        }
    }
}
