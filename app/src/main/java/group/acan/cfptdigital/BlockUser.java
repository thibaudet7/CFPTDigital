package group.acan.cfptdigital;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

// Main Activity implements Adapter view
public class BlockUser extends Activity{

   private RequestQueue mQueue;
   String result, message;
   ProgressDialog pd;
   Button blockUser;
   EditText edtMatricule;
   String queFaire, urlBi;
   TextView txtLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blockuser);

        blockUser = findViewById(R.id.btnBloquer);
        edtMatricule = findViewById(R.id.edtMatricule);
        txtLabel = findViewById(R.id.txtLabel);
        edtMatricule.setText(getIntent().getExtras().getString("intentData"));
        queFaire = getIntent().getExtras().getString("queFaire");

        if (queFaire.equals("debloquer")){
            txtLabel.setText("Déblocage Utilisateur");
            blockUser.setText("DEBLOQUER");
        }

        blockUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String matRicEtu = edtMatricule.getText().toString().trim();

                pd = new ProgressDialog(BlockUser.this);
                pd.setMessage("Deblocage utilisateur en cours...");
                pd.setCancelable(false);
                pd.show();

                if (matRicEtu.equals("")){
                    Toast.makeText(getApplicationContext(),"Le Matricule est obligatoire.",Toast.LENGTH_LONG).show();
                    if (pd.isShowing()){
                        pd.dismiss();
                    }
                }else{
                    if (queFaire.equals("bloquer")){
                        urlBi ="Mettre l'url de votre api";
                    }else {
                        urlBi ="Mettre l'url de votre api";
                    }

                    myJsonFetch(urlBi);
                }



            }
        });

        mQueue = Volley.newRequestQueue(getApplicationContext());
        
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
                                Toast.makeText(BlockUser.this, message, Toast.LENGTH_SHORT).show();

                                Intent t = new Intent(getApplicationContext(),HomeActivity.class);
                                t.putExtra("recupEspace","Administrateur");
                                startActivity(t);
                                finish();
                            }else{
                                Toast.makeText(BlockUser.this, message, Toast.LENGTH_SHORT).show();
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
}