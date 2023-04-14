package com.example.shouldishipbis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shouldishipbis.model.apiCalls.CarbonEstimation;

public class EstimationActivity extends AppCompatActivity {

    private final int REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_estimation);
        if (getIntent().hasExtra("carbonEstimation")) {
            CarbonEstimation ca = (CarbonEstimation) getIntent().getSerializableExtra("carbonEstimation");
            setEstimationInfo(ca);
        } else {
            Intent intent = new Intent(EstimationActivity.this, FormActivity.class);
            startActivityForResult(intent, REQ_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataIntent) {
        super.onActivityResult(requestCode, resultCode, dataIntent);
        if(requestCode == REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                CarbonEstimation ca = (CarbonEstimation) dataIntent.getSerializableExtra("carbonEstimation");
                setEstimationInfo(ca);
            } else {
                Toast.makeText(this, R.string.estimationActivityOnResultFailureText, Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void setEstimationInfo(CarbonEstimation ca) {
        TextView transport = findViewById(R.id.textEmoji);
        TextView nom = findViewById(R.id.nom_estimation);
        TextView date = findViewById(R.id.text_date);
        TextView carbone = findViewById(R.id.text_carbone);
        TextView mass = findViewById(R.id.text_masse);
        TextView distance = findViewById(R.id.text_distance);
        transport.setText( getResources().getText(ca.getTransportEmojiId()));
        nom.setText(ca.getName());
        date.setText(ca.getEstimationDate());
        carbone.setText(ca.getCarbonKg()+ "kg");
        mass.setText(" " +ca.getWeight()+ " " + ca.getWeightUnit().getSign());
        distance.setText(" " +ca.getDistance()+ " " + ca.getDistanceUnit().getSign());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_estimation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()) {
            case R.id.menu_quitter:
                System.exit(0);
                break;
            case R.id.menu_main:
                goToMain();
                break;
            case R.id.menu_comparer:
                goToComparer();
                break;
            case R.id.menu_historique:
                goToHistorique();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToMain(){
        Intent intent = new Intent(EstimationActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void goToComparer(){
        Intent intent = new Intent(EstimationActivity.this, CompareActivity.class);
        startActivity(intent);
    }

    public void goToHistorique(){
        Intent intent = new Intent(EstimationActivity.this, HistoricActivity.class);
        startActivity(intent);
    }

}
