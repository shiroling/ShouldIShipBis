package com.example.shouldishipbis;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shouldishipbis.model.apiCalls.CarbonEstimation;
import com.example.shouldishipbis.model.localDatabase.EstimateDAO;

import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;

public class HistoricActivity extends AppCompatActivity {
    private int code;
    private Intent intentCompare;
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_historic);

        // Récupération de la liste
        ListView listeV = findViewById(R.id.listViewHistoric);
        // lie l'adapter à la listeView
        List<CarbonEstimation> estimations = new ArrayList<>();

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
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_historic);

        code = 1;

        // Récupération de la liste
        ListView listView = findViewById(R.id.listViewHistoric);

        // Lie l'adapter personnalisé à la listeView
        CarbonEstimationAdapter adapter = new CarbonEstimationAdapter(this, new ArrayList<>());
        listView.setAdapter(adapter);

        EstimateDAO estimateDAO = new EstimateDAO(this);
        try {
            for (CarbonEstimation ca : estimateDAO.getAllEstimates()) {
                // Ajout d'un élément à la liste de valeurs
                adapter.add(ca);
            }
        } catch (ParseException e) {
            Toast.makeText(this, R.string.errorMessageHistoricLoad, Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }

        if(getCallingActivity() != null) {
            TextView titre = findViewById(R.id.titreHistoric);
            titre.setText(R.string.titreHistoriqueCompare1);
        }

        intentCompare = new Intent();
        // Code pour la gestion des clics sur les items de la liste
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupère la valeur de l'item à la position sur laquelle on a cliqué
                CarbonEstimation ca = (CarbonEstimation) parent.getItemAtPosition(position);
                if(getCallingActivity() != null) {
                    if(code == 1){
                        code = 2;
                        intentCompare.putExtra("valCarbone1", Double.toString(ca.getCarbonKg()));
                        intentCompare.putExtra("valDate1", ca.getEstimationDate());
                        intentCompare.putExtra("valPoids1", Double.toString(ca.getWeight()));
                        intentCompare.putExtra("valDistance1", Double.toString(ca.getDistance()));
                        intentCompare.putExtra("titreShipping1", ca.getName());
                        TextView titre = findViewById(R.id.titreHistoric);
                        titre.setText(R.string.titreHistoriqueCompare2);
                    } else {
                        intentCompare.putExtra("valCarbone2", Double.toString(ca.getCarbonKg()));
                        intentCompare.putExtra("valDate2", ca.getEstimationDate());
                        intentCompare.putExtra("valPoids2", Double.toString(ca.getWeight()));
                        intentCompare.putExtra("valDistance2", Double.toString(ca.getDistance()));
                        intentCompare.putExtra("titreShipping2", ca.getName());
                        setResult(RESULT_OK, intentCompare);
                        finish();
                    }
                } else {
                    goToEstimation(ca);
                }
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
                goToEstimation(null);
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

    public void goToEstimation(CarbonEstimation ca) {
        Intent intent = new Intent(HistoricActivity.this, EstimationActivity.class);
        if (ca != null) {
            intent.putExtra("carbonEstimation", ca);
        }
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
