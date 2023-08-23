package group.acan.cfptdigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PaymentHistoryActivity extends Activity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Adapter adapter;
    List<ModelClass> userList;
    private RequestQueue mQueue;
    ProgressDialog pd;
    String moisYi[];
    String dateYi[];
    String montantYi[];
    boolean amnaAyPaiement = false;
    String recuperoleEspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        recuperoleEspace = sh.getString("roleConnected", "");

        mQueue = Volley.newRequestQueue(getApplicationContext());

        pd = new ProgressDialog(PaymentHistoryActivity.this);
        pd.setMessage("Chargement en cours...");
        pd.setCancelable(false);
        pd.show();

        userList = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        Log.wtf("========", "TV");
        String intentData = getIntent().getExtras().getString("intentData");
        String url="Mettre l'url de votre api";
        JsonObjectRequest requestTV = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("allitems");
                            moisYi = new String[jsonArray.length()];
                            dateYi= new String[jsonArray.length()];
                            montantYi= new String[jsonArray.length()];

                            if (jsonArray.length()==0){
                                amnaAyPaiement = false;
                            }else{
                                amnaAyPaiement = true;
                            }

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject json = jsonArray.getJSONObject(i);
                                Log.wtf("JTV", "TV" + json.toString());
                                moisYi[i] = json.getString("mois");
                                montantYi[i] = json.getString("montant_paiement");
                                dateYi[i] = json.getString("date_paiement");
                            }

                            initData();
                            initRecycleView();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        mQueue.add(requestTV);
    }

    private void afficherDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Oups..!!!");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.error_icon);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage("Cette personne ne se trouve pas dans notre base de données actuelle.");
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent o = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(o);
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        if (recuperoleEspace.equals("etudiant")){

            fonctionretourAvecRole("Etudiant");

        }else if(recuperoleEspace.equals("comptable")){

            fonctionretourAvecRole("Comptable");

        }else if(recuperoleEspace.equals("vigile")){

            fonctionretourAvecRole("Vigile");

        }else if(recuperoleEspace.equals("administrateur")){

            fonctionretourAvecRole("Administrateur");

        }

    }

    private void fonctionretourAvecRole(String val) {
        Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        i.putExtra("recupEspace", val);
        startActivity(i);
        finish();
    }


    private void initData() {
        if (amnaAyPaiement==false){
            userList.add(new ModelClass(R.drawable.error_icon, "Introuvable", "Matricule introuvable dans la base.","", "-------------------------------------------------------------------"));
            if (pd.isShowing()){
                pd.dismiss();
            }

        }else{
            for (int i = 0; i<moisYi.length;i++){
                userList.add(new ModelClass(R.drawable.pay_icon, moisYi[i], dateYi[i],montantYi[i]+" FCFA", "-------------------------------------------------------------------"));
            }
            if (pd.isShowing()){
                pd.dismiss();
            }
        }
    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new Adapter(userList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}