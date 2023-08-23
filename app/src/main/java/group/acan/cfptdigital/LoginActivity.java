package group.acan.cfptdigital;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends Activity {

    TextView txtLabel;
    Button loginButton, retourButton;
    EditText edtMatricule, edtPassword;
    private RequestQueue mQueue;
    ProgressDialog pd;
    String result, message, nom,prenom, matriCule, role;
    String recupEspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        txtLabel = findViewById(R.id.txtLabel);
        loginButton = findViewById(R.id.loginButton);
        retourButton = findViewById(R.id.retourButton);
        edtMatricule = findViewById(R.id.edtMatricule);
        edtPassword = findViewById(R.id.edtPassword);
        mQueue = Volley.newRequestQueue(getApplicationContext());


        recupEspace = getIntent().getExtras().getString("MonEspace");

        txtLabel.setText(recupEspace);
        if (recupEspace.contains("Etudiant")){
            loginButton.setBackgroundColor(getResources().getColor(R.color.blue));
            txtLabel.setTextColor(getResources().getColor(R.color.blue));
        }else if (recupEspace.contains("Vigile")){
            loginButton.setBackgroundColor(getResources().getColor(R.color.blue_v));
            txtLabel.setTextColor(getResources().getColor(R.color.blue_v));
        }else if (recupEspace.contains("Comptable")){
            loginButton.setBackgroundColor(getResources().getColor(R.color.betterave));
            txtLabel.setTextColor(getResources().getColor(R.color.betterave));
        }
        else if (recupEspace.contains("Professeur")){
            loginButton.setBackgroundColor(getResources().getColor(R.color.prof));
            txtLabel.setTextColor(getResources().getColor(R.color.prof));
        }
        else if (recupEspace.contains("Surveillant")){
            loginButton.setBackgroundColor(getResources().getColor(R.color.surv));
            txtLabel.setTextColor(getResources().getColor(R.color.surv));
        }else{
            loginButton.setBackgroundColor(getResources().getColor(R.color.green));
            txtLabel.setTextColor(getResources().getColor(R.color.green));
        }

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String mouConnecter = sh.getString("isConnected", "");
        String mouConnecterSave = sh.getString("roleConnected", "");
        String roleConnecterSend = getIntent().getExtras().getString("roleConnecter");

        if (mouConnecter.equals("true")){
            if (mouConnecterSave.equals(roleConnecterSend)){
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("recupEspace", recupEspace);
                startActivity(intent);
                finish();
            }
        }else{

        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mat = edtMatricule.getText().toString().trim().toUpperCase();
                String pwd = edtPassword.getText().toString().trim();

                pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("Connexion en cours...");
                pd.setCancelable(false);
                pd.show();

                if (mat.equals("") || pwd.equals("")){
                    messageDialog("Il faut renseigner obligatoirement votre matricule et votre mot de passe.");
                    if (pd.isShowing()){
                        pd.dismiss();
                    }
                }else{
                    if(recupEspace.contains("Etudiant")){
                        String utl = "Mettre l'url de votre api";
                        myJsonFetch(utl);
                    }else if (recupEspace.contains("Vigile")){
                        String utl = "Mettre l'url de votre api";
                        myJsonFetch(utl);
                    } else if(recupEspace.contains("Comptable")){
                        String utl = "Mettre l'url de votre api";
                        myJsonFetch(utl);
                    } else if (recupEspace.contains("Admin")) {
                        String utl = "Mettre l'url de votre api";
                        myJsonFetch(utl);
                    }else if (recupEspace.contains("Professeur")) {
                        String utl = "Mettre l'url de votre api";
                        myJsonFetch(utl);
                    }else if (recupEspace.contains("Surveillant")) {
                        String utl = "Mettre l'url de votre api";
                        myJsonFetch(utl);
                    }

                }
            }
        });
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f = new Intent(getApplicationContext(), EspaceActivity.class);
                startActivity(f);
                finish();
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
                                nom = response.getString("nom");
                                matriCule = response.getString("matricule");
                                prenom = response.getString("prenom");
                                role = response.getString("role");
                                Intent t = new Intent(getApplicationContext(),HomeActivity.class);

                                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("isConnected", "true");
                                myEdit.putString("nameConnected", nom);
                                myEdit.putString("prenomConnected", prenom);
                                myEdit.putString("matConnected", matriCule);
                                myEdit.putString("roleConnected", role);
                                myEdit.apply();
                                t.putExtra("recupEspace", recupEspace);
                                startActivity(t);
                                finish();
                            }else{
                                messageDialog(message);
                                if (pd.isShowing()){
                                    pd.dismiss();
                                }
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

    private void messageDialog(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // Setting Alert Dialog Title
        alertDialogBuilder.setTitle("Oups..!!!");
        // Icon Of Alert Dialog
        alertDialogBuilder.setIcon(R.drawable.error_icon);
        // Setting Alert Dialog Message
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Fermer", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent u = new Intent(getApplicationContext(), EspaceActivity.class);
        startActivity(u);
        finish();
    }
}