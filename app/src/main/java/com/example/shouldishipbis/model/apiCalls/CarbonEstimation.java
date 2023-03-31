package com.example.shouldishipbis.model.apiCalls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CarbonEstimation {
    private static final String API_URL = "https://www.carboninterface.com/api/v1/estimates";
    private String id;
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
    public CarbonEstimation requestEstimation(Transport transport, float weight, float distance, WeightUnit weightUnit, DistanceUnit distanceUnit) /*throws IOException, JSONException, ParseException */{
        this.transport = transport;
        this.weight = weight;
        this.distance = distance;
        this.weightUnit = weightUnit;
        this.distanceUnit = distanceUnit;

        /* Create the request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("type", "shipping");
        requestBody.put("weight_value", weight);
        requestBody.put("weight_unit", weightUnit.toString());
        requestBody.put("distance_value", distance);
        requestBody.put("distance_unit", distanceUnit.toString());
        requestBody.put("transport_method", transport.toString());

        // Open a connection to the API endpoint
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer N1yDYHec4afQtPK7TlxSiA");

        // Send the request body
        connection.setDoOutput(true);
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(requestBody.toString().getBytes());
        outputStream.flush();
        outputStream.close();

        // Read the response
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder responseStringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            responseStringBuilder.append(line);
        }
        bufferedReader.close();
        inputStream.close();

        // Parse the JSON response
        JSONObject responseJson = new JSONObject(responseStringBuilder.toString());
        JSONObject dataJson = responseJson.getJSONObject("data");
        JSONObject attributesJson = dataJson.getJSONObject("attributes");

        // Get the estimated carbon emissions value

        setEstimationDate(attributesJson.getString("estimated_at"));
        setCarbonLb(attributesJson.getDouble("carbon_lb"));
        setCarbonKg(attributesJson.getDouble("carbon_kg"));
        setCarbonMt(attributesJson.getDouble("carbon_mt"));
*/
        setId(String.valueOf(0 + Math.random() * (4000000 - 0)));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy_HH:mm.ss", Locale.getDefault());
        setEstimationDate(sdf.format(new Date()));
        setCarbonLb(25 + Math.random() * (42 - 25));
        setCarbonKg(0.05 + Math.random() * (10 - 0.05));
        setCarbonMt(0.005 + Math.random() * (400 - 0.005));
        return this;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
