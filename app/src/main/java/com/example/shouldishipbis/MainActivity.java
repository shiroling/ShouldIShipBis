package com.example.shouldishipbis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.shouldishipbis.model.apiCalls.CarbonEstimation;
import com.example.shouldishipbis.model.apiCalls.DistanceUnit;
import com.example.shouldishipbis.model.apiCalls.Transport;
import com.example.shouldishipbis.model.apiCalls.WeightUnit;
import com.example.shouldishipbis.model.localDatabase.EstimateDAO;
import com.example.shouldishipbis.model.localDatabase.EstimateDatabaseHelper;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_main);
        Button boutonEstimation = findViewById(R.id.BtnEstimations);
        boutonEstimation.setOnClickListener(v -> goToActivity(EstimationActivity.class));

        Button boutonComparer = findViewById(R.id.BtnComparer);
        boutonComparer.setOnClickListener(v -> goToActivity(CompareActivity.class));

        Button boutonHistorique = findViewById(R.id.BtnHistorique);
        boutonHistorique.setOnClickListener(v -> goToActivity(HistoricActivity.class));

        // Create a new instance of CarbonEstimation
        CarbonEstimation carbonEstimation = new CarbonEstimation();

        // Call requestEstimation method
        carbonEstimation.requestEstimation(
                this,
                Transport.SHIP,
                100,
                1000,
                WeightUnit.KILOGRAMS,
                DistanceUnit.KILOMETERS
            );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()) {
            case R.id.menu_quitter:
                System.exit(0);
                break;
            case R.id.menu_estimation:
                goToActivity(EstimationActivity.class);
                break;
            case R.id.menu_comparer:
                goToActivity(CompareActivity.class);
                break;
            case R.id.menu_historique:
                goToActivity(HistoricActivity.class);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToActivity(Class<?> activity) {
        Intent intent = new Intent(MainActivity.this, activity);
        startActivity(intent);
    }
}