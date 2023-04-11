package com.example.shouldishipbis;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class CompareActivity extends AppCompatActivity {

    private TextView titreShipping1;
    private TextView titreShipping2;
    private TextView valCarbone1;
    private TextView valCarbone2;
    private TextView valDate1;
    private TextView valDate2;
    private TextView valPoids1;
    private TextView valPoids2;
    private TextView valDistance1;
    private TextView valDistance2;
    private TextView meilleurCompare;
    private final int REQ_CODE = 1;
    private Intent historicData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_compare);
        Intent intent = new Intent(CompareActivity.this, HistoricActivity.class);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        historicData = data;
        if(requestCode == REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    setContentView(R.layout.activity_compare);
                    initializeLandscape();
                    setTextLandscape(historicData);
                } else {
                    setContentView(R.layout.activity_compare);
                    initializePortrait();
                    setTextPortrait(historicData);
                }
            } else {
                // si erreur
            }
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_compare);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            initializeLandscape();
            setTextLandscape(historicData);
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            initializePortrait();
            setTextPortrait(historicData);
        }
    }

    private void initializePortrait(){
        titreShipping1 = findViewById(R.id.nom_compare1);
        valCarbone1 = findViewById(R.id.compare_carbone1);
        valDate1 = findViewById(R.id.compare_date1);
        titreShipping2 = findViewById(R.id.nom_compare2);
        valCarbone2 = findViewById(R.id.compare_carbone2);
        valDate2 = findViewById(R.id.compare_date2);
    }

    private void initializeLandscape(){
        initializePortrait();
        valPoids1 = findViewById(R.id.compare_poids1);
        valPoids2 = findViewById(R.id.compare_poids2);
        valDistance1 = findViewById(R.id.compare_distance1);
        valDistance2 = findViewById(R.id.compare_distance2);
        meilleurCompare = findViewById(R.id.nom_meilleur_compare);
    }

    private void setTextPortrait(Intent data){
        titreShipping1.setText(data.getStringExtra("titreShipping1"));
        valCarbone1.setText(data.getStringExtra("valCarbone1"));
        valDate1.setText(data.getStringExtra("valDate1"));
        titreShipping2.setText(data.getStringExtra("titreShipping2"));
        valCarbone2.setText(data.getStringExtra("valCarbone2"));
        valDate2.setText(data.getStringExtra("valDate2"));
    }

    private void setTextLandscape(Intent data){
        setTextPortrait(data);
        valPoids1.setText(data.getStringExtra("valPoids1"));
        valPoids2.setText(data.getStringExtra("valPoids2"));
        valDistance1.setText(data.getStringExtra("valDistance1"));
        valDistance2.setText(data.getStringExtra("valDistance2"));
        if(data.getStringExtra("valCarbone1").compareTo(data.getStringExtra("valCarbone2")) < 0) {
            meilleurCompare.setText(getIntent().getStringExtra("titreShipping1"));
        } else if(data.getStringExtra("valCarbone1").compareTo(data.getStringExtra("valCarbone2")) > 0){
            meilleurCompare.setText(getIntent().getStringExtra("titreShipping2"));
        } else {
            meilleurCompare.setText(R.string.egalite_compare);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_compare, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()) {
            case R.id.menu_quitter:
                System.exit(0);
                break;
            case R.id.menu_estimation:
                goToEstimation();
                break;
            case R.id.menu_main:
                goToMain();
                break;
            case R.id.menu_historique:
                goToHistorique();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToEstimation(){
        Intent intent = new Intent(CompareActivity.this, FormActivity.class);
        startActivity(intent);
    }

    public void goToMain(){
        Intent intent = new Intent(CompareActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToHistorique(){
        Intent intent = new Intent(CompareActivity.this, HistoricActivity.class);
        startActivity(intent);
    }

}
