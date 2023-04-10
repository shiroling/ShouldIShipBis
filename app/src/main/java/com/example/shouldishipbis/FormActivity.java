package com.example.shouldishipbis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shouldishipbis.model.apiCalls.CarbonEstimation;
import com.example.shouldishipbis.model.apiCalls.DistanceUnit;
import com.example.shouldishipbis.model.apiCalls.Transport;
import com.example.shouldishipbis.model.apiCalls.WeightUnit;
import com.example.shouldishipbis.model.localDatabase.EstimateDAO;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_form);
        Button boutonEstimation = findViewById(R.id.button_estimer);
        boutonEstimation.setOnClickListener(v -> estimation());
    }

    @SuppressLint("NonConstantResourceId")
    public void estimation(){
        EditText saisieNom = findViewById(R.id.edit_text_nom);
        String valNom = String.valueOf(saisieNom.getText());

        EditText saisiePoids = findViewById(R.id.edit_text_poids);
        int valPoids = Integer.parseInt(saisiePoids.getText().toString());

        EditText saisieDistance = findViewById(R.id.edit_text_distance);
        int valDistance = Integer.parseInt(saisieDistance.getText().toString());

        RadioGroup radioTransport = findViewById(R.id.radio_transport);
        Transport transport;
        switch (radioTransport.getCheckedRadioButtonId()) {
            case R.id.radio_bateau: {
                transport = Transport.SHIP;
                break;
            }
            case R.id.radio_avion: {
                transport = Transport.PLANE;
                break;
            }
            case R.id.radio_camion: {
                transport = Transport.TRUCK;
                break;
            }
            case R.id.radio_train: {
                transport = Transport.TRAIN;
                break;
            }
            default: {
                throw new RuntimeException("unexpected value in switch");
            }
        }
        try {
            CarbonEstimation ca = new CarbonEstimation();
            ca.requestEstimation(this, valNom, transport, valPoids, valDistance, WeightUnit.KILOGRAMS, DistanceUnit.KILOMETERS);
            EstimateDAO estimateDAO = new EstimateDAO(this);
            estimateDAO.insertEstimate(ca);
            Intent intentRetour = new Intent();
            intentRetour.putExtra("carbonEstimation", ca);
            setResult(RESULT_OK, intentRetour);
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}