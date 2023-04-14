package com.example.shouldishipbis.model.apiCalls;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCarbon {
    @POST("/api/v1/estimates")
    Call<CarbonData> getEstimation(
            @Header("Authorization") String authHeader,
            @Body BodyApi body
    );

    @FormUrlEncoded
    @POST("/api/v1/estimates")
    Call<CarbonData> getEstimationFeilds(
        @Header("Authorization") String authHeader,
        @Field("type")  String shipping,
        @Field("weight_value") Double weight_value,
        @Field("weight_unit") String weight_unit,
        @Field("distance_value") Double distance_value,
        @Field("distance_unit") String distance_unit,
        @Field("transport_method") String transport_method
    );

}

class BodyApi {
    private String type;
    private Double weight_value;
    private String weight_unit;
    private Double distance_value;
    private String distance_unit;
    private String transport_method;

    public BodyApi(String type, Double weight, String weightUnit, Double dist, String distUnit, String transMethod) {
        this.type = type;
        this.weight_value = weight;
        this.weight_unit = weightUnit;
        this.distance_value = dist;
        this.distance_unit = distUnit;
        this.transport_method = transMethod;
    }
}
