package com.example.shouldishipbis.model.apiCalls;

import android.content.Context;
import android.net.ParseException;
import android.util.Log;

import com.example.shouldishipbis.R;
import com.example.shouldishipbis.model.localDatabase.EstimateDAO;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarbonEstimation implements Serializable {
    private static final String API_URL = "https://www.carboninterface.com/";
    private String id;
    private String name;
    private Transport transport;
    private double weight;
    private double distance;
    private WeightUnit weightUnit;
    private DistanceUnit distanceUnit;
    private String estimationDate;
    private double carbonLb;
    private double carbonKg;
    private double carbonMt;

    public CarbonEstimation() {
    }

    public CarbonEstimation requestEstimation(Context context, String name, Transport transport, double weight, double distance, WeightUnit weightUnit, DistanceUnit distanceUnit) {
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

    public CarbonEstimation requestEstimationRetrofit(Context context, String name, Transport transport, double weight, double distance, WeightUnit weightUnit, DistanceUnit distanceUnit) throws IOException, JSONException, ParseException {
        setName(name);
        Log.d("API", "demande à l'api");
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        ApiCarbon apiCarbon = retrofit.create(ApiCarbon.class);
        Call<CarbonData> callAsync = apiCarbon.getEstimationFeilds(
                "Bearer N1yDYHec4afQtPK7TlxSiA",
                "shipping",
                weight, weightUnit.getSign(),
                distance, distanceUnit.getSign(),
                transport.getTransportName()
        );



        /* tentative en créant un request body au préalable
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("type", "shipping")
                .addFormDataPart("weight_value", String.valueOf(weight))
                .addFormDataPart("weight_unit", weightUnit.getSign())
                .addFormDataPart("distance_value", String.valueOf(distance))
                .addFormDataPart("distance_unit", distanceUnit.getSign())
                .addFormDataPart("transport_method", transport.getTransportName())
                .build();

        Call<CarbonData> callAsync = apiCarbon.getEstimation(
                "Bearer N1yDYHec4afQtPK7TlxSiA",
                //requestBody
                new BodyApi("shipping", weight, weightUnit.getSign(), distance, distanceUnit.getSign(), transport.getTransportName())
        );*/

        callAsync.enqueue(new Callback<CarbonData>() {
            @Override
            public void onResponse(Call<CarbonData> call, Response<CarbonData> response) {
                CarbonData ca = response.body();
                setAll(ca);
            }

            @Override
            public void onFailure(Call<CarbonData> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });

        EstimateDAO eDao = new EstimateDAO(context);
        eDao.insertEstimate(this);

        return this;
    }

    private void setAll(CarbonData ca) {
        setId(ca.getId());
        setTransport(Transport.stringToTransport(ca.getTransport_method()));
        setWeight(ca.getWeight_value());
        setDistance(ca.getDistance_value());
        setWeightUnit(WeightUnit.stringToUnit(ca.getWeight_unit()));
        setDistanceUnit(DistanceUnit.stringToUnit(ca.getDistance_unit()));
        setEstimationDate(ca.getEstimated_at());
        setCarbonLb(ca.getCarbon_lb());
        setCarbonKg(ca.getCarbon_kg());
        setCarbonMt(ca.getCarbon_mt());
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
        return Math.round(weight*100)/100;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDistance() {
        return Math.round(distance*100)/100 ;
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
        return Math.round(carbonLb*100f)/100f;
    }

    public void setCarbonLb(double carbonLb) {
        this.carbonLb = carbonLb;
    }

    public double getCarbonKg() {
        return Math.round(carbonKg*100f)/100f;
    }

    public void setCarbonKg(double carbonKg) {
        this.carbonKg = carbonKg;
    }

    public double getCarbonMt() {
        return Math.round(carbonMt*100f)/100f;
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
