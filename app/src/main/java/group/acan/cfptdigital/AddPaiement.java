package group.acan.cfptdigital;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.AdapterView;
import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

// Main Activity implements Adapter view
public class AddPaiement extends Activity{

   String matriculeEtudiant, matriculeComptable;
   TextView txtMatricule;
   TextView txtComptable , txtLabel;
   private RequestQueue mQueue;
   String result, message, valueMois, valueAnnee, montantPaiemt;
   ProgressDialog pd;
   Button addPay;
   EditText edtMontant;
   boolean paiementAdded = false;


    // and store name of courses
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_payment);
        txtMatricule = findViewById(R.id.txtMatricule);
        txtComptable = findViewById(R.id.txtComptable);
        addPay = findViewById(R.id.btnAd);
        edtMontant = findViewById(R.id.edtMontant);
        txtLabel = findViewById(R.id.txtLabel);


        addPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //les infos a envoyer
                String matEtu = matriculeEtudiant;
                String matCom = matriculeComptable;
                String leMois = valueMois;
                String lAnnee = valueAnnee;
                String lePrix = edtMontant.getText().toString().trim();

                pd = new ProgressDialog(AddPaiement.this);
                pd.setMessage("Ajout de paiement en cours...");
                pd.setCancelable(false);
                pd.show();

                if (lePrix.equals("")){
                    Toast.makeText(getApplicationContext(),"Le montant est obligatoire.",Toast.LENGTH_LONG).show();
                    if (pd.isShowing()){
                        pd.dismiss();
                    }
                }else{
                    leMois = leMois.toLowerCase();
                    String url="Mettre l'url de votre api";
                    myJsonFetch(url);
                }



            }
        });

        mQueue = Volley.newRequestQueue(getApplicationContext());

        matriculeEtudiant = getIntent().getExtras().getString("intentData");
        txtMatricule.setText("Matricule Etudiant : "+ matriculeEtudiant);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        matriculeComptable = sh.getString("matConnected", "");

        txtComptable.setText("Matricule Comptable : "+ matriculeComptable);

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
        // lister les anne
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
        
    }

    public void myJsonFetch(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf("URL ========== TV", url);
                        try {
                            if (pd.isShowing()){
                                pd.dismiss();
                            }
                            result = response.getString("statut");
                            message = response.getString("message");

                            if (result.equals("true")){
                                Toast.makeText(AddPaiement.this, message, Toast.LENGTH_SHORT).show();

                                Intent t = new Intent(getApplicationContext(),HomeActivity.class);
                                t.putExtra("recupEspace","Comptable");
                                startActivity(t);
                                finish();
                            }else{
                                Toast.makeText(AddPaiement.this, message, Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(jsonObjectRequest);
    }
}