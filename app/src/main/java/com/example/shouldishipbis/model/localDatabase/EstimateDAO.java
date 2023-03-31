package com.example.shouldishipbis.model.localDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shouldishipbis.model.apiCalls.CarbonEstimation;
import com.example.shouldishipbis.model.apiCalls.DistanceUnit;
import com.example.shouldishipbis.model.apiCalls.Transport;
import com.example.shouldishipbis.model.apiCalls.WeightUnit;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class EstimateDAO {
    private final SQLiteOpenHelper dbHelper;

    public EstimateDAO(Context context) {
        dbHelper = new EstimateDatabaseHelper(context);
    }

    public void insertEstimate(CarbonEstimation estimate) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", estimate.getId());
        values.put("transport", estimate.getTransport().getTransportName());
        values.put("weight", estimate.getWeight());
        values.put("weight_unit", estimate.getWeightUnit().getSign());
        values.put("distance", estimate.getDistance());
        values.put("distance_unit", estimate.getDistanceUnit().getSign());
        values.put("estimated_at", estimate.getEstimationDate());
        values.put("carbon_lb", estimate.getCarbonLb());
        values.put("carbon_kg", estimate.getCarbonKg());
        values.put("carbon_mt", estimate.getCarbonMt());
        db.insert("carbon_estimations", null, values);
        db.close();
        Log.d("DB-DAO", "inséré : "+estimate);
    }

    public List<CarbonEstimation> getAllEstimates() throws ParseException {
        List<CarbonEstimation> estimateList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM carbon_estimations";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CarbonEstimation estimate = new CarbonEstimation();
                estimate.setId(cursor.getString(0));
                estimate.setTransport(Transport.valueOf(cursor.getString(1)));
                estimate.setWeight(cursor.getFloat(2));
                estimate.setWeightUnit(WeightUnit.valueOf(cursor.getString(3)));
                estimate.setDistance(cursor.getFloat(4));
                estimate.setDistanceUnit(DistanceUnit.valueOf(cursor.getString(5)));
                estimate.setEstimationDate(cursor.getString(6));
                estimate.setCarbonLb(cursor.getFloat(7));
                estimate.setCarbonKg(cursor.getFloat(8));
                estimate.setCarbonMt(cursor.getFloat(9));
                estimateList.add(estimate);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return estimateList;
    }

    public void deleteEstimate(CarbonEstimation estimate) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("carbon_estimations", "id = ?", new String[]{estimate.getId()});
        db.close();
    }
    /*
        EstimateDatabaseHelper dbHelper = new EstimateDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        EstimateDAO dao = new EstimateDAO(this);
        CarbonEstimation ca = new CarbonEstimation();
        ca.setCarbonKg(1);
        ca.setCarbonLb(3);
        ca.setCarbonMt(0.234);
        ca.setDistance(2000);
        ca.setDistanceUnit(DistanceUnit.KILOMETERS);
        ca.setEstimationDate("BLABLADATE");
        ca.setWeightUnit(WeightUnit.GRAMS);
        ca.setTransport(Transport.PLANE);
        dao.insertEstimate(ca);
     */
}
