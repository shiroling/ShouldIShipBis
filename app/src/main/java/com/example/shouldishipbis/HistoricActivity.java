package com.example.shouldishipbis;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HistoricActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_historic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_historique, menu);
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
            case R.id.menu_comparer:
                goToComparer();
                break;
            case R.id.menu_main:
                goToMain();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToEstimation(){
        Intent intent = new Intent(HistoricActivity.this, FormActivity.class);
        startActivity(intent);
    }

    public void goToComparer(){
        Intent intent = new Intent(HistoricActivity.this, CompareActivity.class);
        startActivity(intent);
    }

    public void goToMain(){
        Intent intent = new Intent(HistoricActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
