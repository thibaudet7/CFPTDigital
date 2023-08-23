package group.acan.cfptdigital;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class InfoUserActivity extends Activity {

    TextView txtShhowWelcome;
    TextView txtShhowMatricule;
    TextView txtShhowPaiement;
    TextView txtShhowDepartement;
    TextView txtShhowFiliere;
    TextView txtShhowClasse;
    TextView txtShhowPhone;
    ImageView imgViewTof;
    ImageView backBtn;
    ProgressDialog pd;
    private RequestQueue mQueue;

    String recuperoleEspace;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_info_user);

        mQueue = Volley.newRequestQueue(getApplicationContext());

        setupViews();

        pd = new ProgressDialog(InfoUserActivity.this);
        pd.setMessage("Chargement en cours...");
        pd.setCancelable(false);
        pd.show();

        recupData(getIntent().getExtras().getString("intentData"));
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        recuperoleEspace = sh.getString("roleConnected", "");
    }
    private void setupViews() {
        txtShhowWelcome = findViewById(R.id.txtShhowWelcome);
        txtShhowMatricule = findViewById(R.id.txt_1);
        txtShhowPaiement = findViewById(R.id.txt_2);
        txtShhowDepartement= findViewById(R.id.txt_3);
        txtShhowFiliere= findViewById(R.id.txt_4);
        txtShhowClasse= findViewById(R.id.txt_5);
        txtShhowPhone= findViewById(R.id.txt_6);
        imgViewTof = findViewById(R.id.image_pp);
        backBtn = findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(d);
                finish();
            }
        });
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

    private void recupData(String intentData) {
        String url = "Mettre l'url de votre api";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.wtf("URL ========== TV", url);
                        try {

                            String statutUser = response.getString("statut");
                            String messageUser = response.getString("message");

                            if (statutUser.equals("true")){
                                String matUser = response.getString("matricule");
                                String prenomUser = response.getString("prenom");
                                String nomUser = response.getString("nom");
                                String phoneUser = response.getString("telephone");
                                String roleUser = response.getString("role");
                                String lastPayUser = response.getString("dernierpay");
                                String departementUser = response.getString("departement");
                                String filiaireUser = response.getString("filiere");
                                String classeUser = response.getString("classe");
                                String imageUser = response.getString("image");

                                txtShhowWelcome.setText(prenomUser+" "+nomUser);
                                txtShhowMatricule.setText(matUser);
                                txtShhowPhone.setText(phoneUser);
                                txtShhowClasse.setText(classeUser);
                                txtShhowDepartement.setText(departementUser);
                                txtShhowFiliere.setText(filiaireUser);
                                txtShhowPaiement.setText(lastPayUser);
                                Picasso.get().load(imageUser).into(imgViewTof);
                            }else{
                                afficheMessagDialog(messageUser);
                            }

                            if (pd.isShowing()){
                                pd.dismiss();
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

    private void afficheMessagDialog(String messageUser) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Oups..!!!");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.error_icon);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage(messageUser);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Fermer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent o = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(o);
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("Réessayer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent o = new Intent(getApplicationContext(), ScanActivity.class);
                startActivity(o);
                finish();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
