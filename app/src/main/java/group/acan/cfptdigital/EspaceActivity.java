package group.acan.cfptdigital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class EspaceActivity extends Activity implements View.OnClickListener {

    Button btnEtudiant, btnVigile, btnComptabe, btnAdmin, btnProfesseur, btnSurveillant;
    String tuEsQui = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_espace);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String roleConnecter = sh.getString("roleConnected", "");

        if(roleConnecter.equals("etudiant")){
            tuEsQui = roleConnecter;
        }else if (roleConnecter.equals("vigile")){
            tuEsQui = roleConnecter;
        }else if (roleConnecter.equals("comptable")){
            tuEsQui = roleConnecter;
        }else if (roleConnecter.equals("administrateur")){
            tuEsQui = roleConnecter;
        }else if (roleConnecter.equals("surveillant")){
            tuEsQui = roleConnecter;
        }else if (roleConnecter.equals("professeur")){
            tuEsQui = roleConnecter;
        }else{
            tuEsQui = roleConnecter;
        }

        setUpElement();


    }

    private void setUpElement() {
        btnEtudiant = findViewById(R.id.btnEdutiant);
        btnVigile = findViewById(R.id.btnVigile);
        btnComptabe = findViewById(R.id.btnComptable);
        btnAdmin = findViewById(R.id.btnAdministrateur);
        btnSurveillant = findViewById(R.id.btnSurveillant);
        btnProfesseur = findViewById(R.id.btnProfessseur);

        btnEtudiant.setOnClickListener(this);
        btnVigile.setOnClickListener(this);
        btnComptabe.setOnClickListener(this);
        btnAdmin.setOnClickListener(this);
        btnProfesseur.setOnClickListener(this);
        btnSurveillant.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEdutiant:
                if(tuEsQui.equals("etudiant")){
                    Intent a = new Intent(getApplicationContext(),HomeActivity.class);
                    a.putExtra("recupEspace","Espace Etudiant");
                    startActivity(a);
                    finish();
                }else{
                    if(!tuEsQui.equals("false")){
                        Toast.makeText(this, "Vous etes connecté en tant que "+tuEsQui, Toast.LENGTH_SHORT).show();
                    }else{
                    Intent a = new Intent(getApplicationContext(),LoginActivity.class);
                    a.putExtra("MonEspace","Espace Etudiant");
                    a.putExtra("roleConnecter", "etudiant");
                    startActivity(a);
                    finish();
                    }
                }
                break;
            case R.id.btnVigile:
                if(tuEsQui.equals("vigile")){
                    Intent a = new Intent(getApplicationContext(),HomeActivity.class);
                    a.putExtra("recupEspace","Espace Vigile");
                    startActivity(a);
                    finish();
                }else{
                if(!tuEsQui.equals("false")){
                    Toast.makeText(this, "Vous etes  déjà connecté en tant que "+tuEsQui, Toast.LENGTH_SHORT).show();
                }else{
                    Intent a = new Intent(getApplicationContext(),LoginActivity.class);
                    a.putExtra("MonEspace","Espace Vigile");
                    a.putExtra("roleConnecter", "vigile");
                    startActivity(a);
                    finish();
                }
            }
                break;
            case R.id.btnComptable:

                if(tuEsQui.equals("comptable")){
                    Intent a = new Intent(getApplicationContext(),HomeActivity.class);
                    a.putExtra("recupEspace","Espace Comptable");

                    startActivity(a);
                    finish();
                }else{
                    if(!tuEsQui.equals("false")){
                        Toast.makeText(this, "Vous etes connecté en tant que "+tuEsQui, Toast.LENGTH_SHORT).show();
                    }else{
                        Intent a = new Intent(getApplicationContext(),LoginActivity.class);
                        a.putExtra("MonEspace","Espace Comptable");
                        a.putExtra("roleConnecter", "comptable");
                        startActivity(a);
                        finish();
                    }
                }
                break;
            case R.id.btnAdministrateur:

                if(tuEsQui.equals("administrateur")){
                    Intent a = new Intent(getApplicationContext(),HomeActivity.class);
                    a.putExtra("recupEspace","Espace Administrateur");
                    startActivity(a);
                    finish();
                }else{
                    if(!tuEsQui.equals("false")){
                        Toast.makeText(this, "Vous etes connecté en tant que "+tuEsQui, Toast.LENGTH_SHORT).show();
                    }else{
                        Intent a = new Intent(getApplicationContext(),LoginActivity.class);
                        a.putExtra("MonEspace","Espace Administrateur");
                        a.putExtra("roleConnecter", "administrateur");
                        startActivity(a);
                        finish();
                    }
                }
                break;
            case R.id.btnProfessseur:

                Toast.makeText(getApplicationContext(), "Ce module sera bientôt ajouté", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSurveillant:

                break;
        }
    }
}