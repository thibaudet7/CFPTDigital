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

public class AbsenceActivity extends Activity {
    Button btnValider;

    String  valueAnnee, valueClasse;

    String[] departement = new String[] {
            "INFORMATIQUE",
            "MECANIQUE",
            "ELECTRICITE"
    };
    String[] filiere = new String[]{
            "INFORMATIQUE INDUSTRIELLE ",
            "AUTOMATIQUE",
            "ELECTRO-MECANIQUE",
            "ELECTRO-TECHNIQUE",
            "ENGINS-LOURDS",
            "FROID-INDUSTRIELLE" ,
            "MAINTENANCE DES ENGINS LOURDS",
    };
    String[] cours = new String[]{
            //Recuperer api des cours du prof
            "Systeme de Numeration",
            "Algorithme",
            "Java",
            "Reseaux",

    };
    String[] classe = new String[]{
            //Recuperer api des classes
            "BTS INFO1 CJ",
            "BTS INFO2 CJ",
            "BTS INFO1 FPJ",
            "BTS INFO2 FPJ",

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.professeur);
        View btnValider = findViewById(R.id.btnValider);
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lAnnee = valueAnnee;
                String laClasse = valueClasse;
                String url = "Mettre l'url de votre api";
                Intent i = new Intent(getApplicationContext(), AllStudentsActivity.class);
                i.putExtra("urlRecouv",url);
                Log.wtf("urlRecouv=",url);
                startActivity(i);
                finish();
            }
        });
        // lister les departements
        ArrayAdapter<String> adDep
                = new ArrayAdapter(
                this,
                R.layout.drop_down_mois,
                departement);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.listDesDep);
        autoCompleteTextView.setAdapter(adDep);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                valueClasse = autoCompleteTextView.getText().toString();
            }
        });

        // lister les filieres
        ArrayAdapter<String> adFil
                = new ArrayAdapter(
                this,
                R.layout.drop_down_mois,
                filiere);

        AutoCompleteTextView autoCompleteTextView1 = findViewById(R.id.listDesFiliere);
        autoCompleteTextView1.setAdapter(adFil);
        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                valueAnnee = autoCompleteTextView1.getText().toString();
            }
        });
        //lister les cours
        ArrayAdapter<String> adCours
                = new ArrayAdapter(
                this,
                R.layout.drop_down_mois,
                cours);

        AutoCompleteTextView autoCompleteTextView2 = findViewById(R.id.listDesCours);
        autoCompleteTextView2.setAdapter(adCours);
        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                valueClasse = autoCompleteTextView2.getText().toString();
            }
        });
        // lister les classes
        ArrayAdapter<String> adClasse
                = new ArrayAdapter(
                this,
                R.layout.drop_down_mois,
                classe);

        AutoCompleteTextView autoCompleteTextView3 = findViewById(R.id.listDesClasses);
        autoCompleteTextView3.setAdapter(adClasse);
        autoCompleteTextView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                valueClasse = autoCompleteTextView3.getText().toString();
            }
        });
        // lister les classes
        ArrayAdapter<String> adAnnee
                = new ArrayAdapter(
                this,
                R.layout.drop_down_mois,
                annee);

        AutoCompleteTextView autoCompleteTextView4 = findViewById(R.id.listDesAnnées);
        autoCompleteTextView4.setAdapter(adAnnee);
        autoCompleteTextView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                valueClasse = autoCompleteTextView3.getText().toString();
            }
        });


    }
}