package com.example.shouldishipbis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EstimationActivity extends AppCompatActivity {

    private final int REQ_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_estimation);
        Button boutonEstimer = findViewById(R.id.button_nouvelle_estimation);
        boutonEstimer.setOnClickListener(v -> goToForm());
    }

    private void goToForm() {
        Intent intent = new Intent(EstimationActivity.this, FormActivity.class);
        startActivityForResult(intent, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Afficher les résultats de l'estimation
            } else {
                // si erreur
            }
        }
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
