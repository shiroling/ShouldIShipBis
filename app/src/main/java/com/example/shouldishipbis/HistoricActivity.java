package com.example.shouldishipbis;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shouldishipbis.model.apiCalls.CarbonEstimation;
import com.example.shouldishipbis.model.localDatabase.EstimateDAO;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HistoricActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_historic);

        // Récupération de la liste
        ListView listeV = (ListView) findViewById(R.id.listViewHistoric);
        // lie l'adapter à la listeView
        List<CarbonEstimation> listeValeursDansLaListe = new ArrayList<>();

        // Création d'un adapter à partir de la liste
        ArrayAdapter<CarbonEstimation> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listeValeursDansLaListe);
        listeV.setAdapter(adapter);

        EstimateDAO estimateDAO = new EstimateDAO(this);
        try {
            for (CarbonEstimation ca :estimateDAO.getAllEstimates()) {
                // Ajout d'un élément à la liste de valeurs
                listeValeursDansLaListe.add(ca);
                // Mise à jour de la ListeView
                adapter.notifyDataSetChanged();

            }
        } catch (ParseException e) {
            Toast.makeText(this, R.string.errorMessageHistoricLoad, Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }

        // Code pour la gestion des clics sur ls items de la liste
        listeV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupère la valeur de l'item à la position sur laquelle on a cliqué
                CarbonEstimation ca = (CarbonEstimation) parent.getItemAtPosition(position);

                Toast.makeText(HistoricActivity.this, ca.toString() , Toast.LENGTH_SHORT).show();
            }
        });
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
