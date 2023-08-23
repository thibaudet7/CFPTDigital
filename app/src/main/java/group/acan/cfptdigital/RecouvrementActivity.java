package group.acan.cfptdigital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.annotation.Nullable;

import group.acan.cfptdigital.R;

public class RecouvrementActivity extends Activity {
    Button btnValider;
    String valueMois, valueAnnee, valueClasse;

    String[] mois = new String[] {
            "JANVIER",
            "FEVRIER",
            "MARS",
            "AVRIL",
            "MAI",
            "JUIN" ,
            "JUILLET",
            "AOUT",
            "SEPTEMBRE",
            "OCTOBRE",
            "NOVEMBRE",
            "DECEMBRE"
    };
    String[] annee = new String[]{
            "2022-2023",
            "2023-2024",
            "2024-2025",
            "2025-2026",
            "2026-2027",
            "2027-2028" ,
            "2028-2029",
            "2029-2030",
            "2030-2031",
            "2031-2032",
            "2032-2033"
    };
    String[] classe = new String[]{
            //Recuperer api des classes
            "BTS-INFO1-CJ",
            "BTS-INFO2-CJ",
            "BTS-INFO1-FPJ",
            "BTS-INFO2-FPJ",
            "BTS-AUTO1-FPJ",
            "BTS-AUTO2-FPJ",
            "BTS-AUTO1-CJ",
            "BTS-AUTO2-CJ",

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recouvrement);
        View btnValider = findViewById(R.id.btnValider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String leMois = valueMois;
                String lAnnee = valueAnnee;
                String laClasse = valueClasse;

                String urlRecouv = "Mettre l'url de votre api";
                Intent i = new Intent(getApplicationContext(), ListeRecouv.class);
                i.putExtra("urlRecouv",urlRecouv);
                Log.wtf("urlRecouv=",urlRecouv);
                startActivity(i);
                finish();

            }
        });
        // lister les mois
        ArrayAdapter<String> adMois
                = new ArrayAdapter(
                this,
                R.layout.drop_down_mois,
                mois);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.listDesMois);
        autoCompleteTextView.setAdapter(adMois);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                valueMois = autoCompleteTextView.getText().toString();
            }
        });
        // lister les annees
        ArrayAdapter<String> adAnnee
                = new ArrayAdapter(
                this,
                R.layout.drop_down_mois,
                annee);

        AutoCompleteTextView autoCompleteTextView1 = findViewById(R.id.listDesAnnee);
        autoCompleteTextView1.setAdapter(adAnnee);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                valueAnnee = autoCompleteTextView1.getText().toString();
            }
        });
        // lister les classes
        ArrayAdapter<String> adClasse
                = new ArrayAdapter(
                this,
                R.layout.drop_down_mois,
                classe);

        AutoCompleteTextView autoCompleteTextView2 = findViewById(R.id.listDesClasse);
        autoCompleteTextView2.setAdapter(adClasse);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                valueClasse = autoCompleteTextView2.getText().toString();
            }
        });


    }
}