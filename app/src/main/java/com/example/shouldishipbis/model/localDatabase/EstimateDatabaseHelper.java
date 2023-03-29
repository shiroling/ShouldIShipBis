package com.example.shouldishipbis.model.localDatabase;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EstimateDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "estimates.db";
    public final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS carbon_estimations (id TEXT PRIMARY KEY, transport TEXT, weight REAL, weight_unit TEXT, distance REAL, distance_unit TEXT, estimated_at TEXT, carbon_lb REAL, carbon_kg REAL, carbon_mt REAL);";
    public final String SQL_DELETE = "DROP TABLE IF EXISTS carbon_estimations ;";

    public EstimateDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL_CREATE);
            Log.d("DBTEST", "CreationTable : OK");

        } catch (SQLException e ) {
            Log.d("DBTEST", "CreationTable : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE);
        onCreate(db);
    }
}
