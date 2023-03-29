package com.example.shouldishipbis;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CompareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_compare);
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
