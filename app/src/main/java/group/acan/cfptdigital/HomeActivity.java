package group.acan.cfptdigital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



public class HomeActivity extends Activity {

    LinearLayout seDeconnecter, btnScanner, btnHistorique, btnAddPaiement;
    LinearLayout btnBloquer, btnDebloquer, btnReclamation,btnRecouvrement,btnGestionPresence;
    TextView tourBi, txt_controle;

    String roleconnecter;
    String matriculeConnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        seDeconnecter = findViewById(R.id.seDeconnecter);
        btnScanner = findViewById(R.id.btnVerifierPay);
        btnHistorique = findViewById(R.id.btnHistorique);
        btnAddPaiement = findViewById(R.id.btnAddPaiement);
        btnBloquer = findViewById(R.id.btnBloquer);
        btnDebloquer = findViewById(R.id.btnDebloquer);
        btnReclamation = findViewById(R.id.btnReclamation);
        btnRecouvrement = findViewById(R.id.btnRecouvrement);
        txt_controle = findViewById(R.id.txt_controle);
       // btnGestionPresence = findViewById(R.id.btnGestionPresence);


        tourBi = findViewById(R.id.tourBi);

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String nameConnecter = sh.getString("nameConnected", "");
        String prenomConnecter = sh.getString("prenomConnected", "");
        roleconnecter = sh.getString("roleConnected", "");
        matriculeConnecter = sh.getString("matConnected", "");

        String recupEspace = getIntent().getExtras().getString("recupEspace");

        if (recupEspace.contains("Administrateur")){
        }else
        if (recupEspace.contains("Etudiant")){

            btnAddPaiement.setVisibility(View.GONE);
            btnBloquer.setVisibility(View.GONE);
            btnDebloquer.setVisibility(View.GONE);
            btnRecouvrement.setVisibility(View.GONE);
            txt_controle.setText("MES INFORMATIONS PERSO");

        }else if(recupEspace.contains("Vigile")){

            btnAddPaiement.setVisibility(View.GONE);
            btnBloquer.setVisibility(View.GONE);
            btnDebloquer.setVisibility(View.GONE);
            btnRecouvrement.setVisibility(View.GONE);
            btnReclamation.setVisibility(View.GONE);

        }else if(recupEspace.contains("Professeur")){

            btnAddPaiement.setVisibility(View.GONE);
            btnBloquer.setVisibility(View.GONE);
            btnDebloquer.setVisibility(View.GONE);
            btnRecouvrement.setVisibility(View.GONE);
            btnReclamation.setVisibility(View.GONE);
            btnHistorique.setVisibility(View.GONE);

        }else if(recupEspace.contains("Surveillant")){

            btnAddPaiement.setVisibility(View.GONE);
            btnBloquer.setVisibility(View.GONE);
            btnDebloquer.setVisibility(View.GONE);
            btnRecouvrement.setVisibility(View.GONE);
            btnReclamation.setVisibility(View.GONE);
            btnHistorique.setVisibility(View.GONE);
        }
        else if(recupEspace.contains("Comptable")){

            btnBloquer.setVisibility(View.GONE);
            btnDebloquer.setVisibility(View.GONE);
            btnReclamation.setVisibility(View.GONE);

        }

        tourBi.setText(prenomConnecter+" "+nameConnecter);

        seDeconnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.putString("isConnected", "false");
                myEdit.putString("roleConnected", "false");
                myEdit.apply();
                Toast.makeText(getApplicationContext(),"Vous etes deconnecté de votre compte.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), EspaceActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (roleconnecter.equals("etudiant")){
                    Intent iu = new Intent(getApplicationContext(), InfoUserActivity.class);
                    iu.putExtra("intentData",matriculeConnecter);
                    startActivity(iu);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), SaisiActivity.class);
                    intent.putExtra("btnName","showUserProfil");
                    startActivity(intent);
                }

            }
        });

        btnHistorique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roleconnecter.equals("etudiant")){
                    Intent iu = new Intent(getApplicationContext(), PaymentHistoryActivity.class);
                    iu.putExtra("intentData",matriculeConnecter);
                    startActivity(iu);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), SaisiActivity.class);
                    intent.putExtra("btnName","showPayHistory");
                    startActivity(intent);
                }

            }
        });

        btnAddPaiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SaisiActivity.class);
                intent.putExtra("btnName","showAddPaiement");
                startActivity(intent);
            }
        });

        btnBloquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SaisiActivity.class);
                intent.putExtra("btnName","showBlockUser");
                startActivity(intent);
            }
        });

        btnDebloquer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SaisiActivity.class);
                intent.putExtra("btnName","showDebloqueUser");
                startActivity(intent);
            }
        });
        btnReclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmailActivity.class));
            }
        });
        btnRecouvrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecouvrementActivity.class);
                startActivity(intent);
            }
        });

    }
}