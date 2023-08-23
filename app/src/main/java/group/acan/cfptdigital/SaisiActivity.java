package group.acan.cfptdigital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SaisiActivity extends Activity {
    Button validerButton,scannerButton;
    EditText edtSaisi;
    EditText edtMatricule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisi);
        validerButton = findViewById(R.id.validerButton);
        scannerButton = findViewById(R.id.scannerButton);
        edtSaisi = findViewById(R.id.edtSaisi);

    validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mat = edtSaisi.getText().toString();
                String btnName = getIntent().getExtras().getString("btnName");
                String matRicEtu = edtSaisi.getText().toString().trim();
                if (mat.equals("")){
                    messageDialog("Il faut renseigner obligatoirement votre matricule .");

                }else{
                   if (btnName.equals("showUserProfil")){
                       Intent iu = new Intent(getApplicationContext(), InfoUserActivity.class);
                       iu.putExtra("intentData",mat);
                       startActivity(iu);
                       finish();
                   }else if (btnName.equals("showAddPaiement")){
                       Intent iu = new Intent(getApplicationContext(), AddPaiement.class);
                       iu.putExtra("intentData",mat);
                       startActivity(iu);
                       finish();
                   }else if (btnName.equals("showPayHistory")){
                       Intent iu = new Intent(getApplicationContext(), PaymentHistoryActivity.class);
                       iu.putExtra("intentData",mat);
                       startActivity(iu);
                       finish();
                   }else if (btnName.equals("showBlockUser")){
                       Intent iu = new Intent(getApplicationContext(), BlockUser.class);
                       iu.putExtra("queFaire","bloquer");
                       iu.putExtra("intentData",mat);
                       startActivity(iu);
                       finish();
                   }else if (btnName.equals("showDebloqueUser")){
                       Intent iu = new Intent(getApplicationContext(), BlockUser.class);
                       iu.putExtra("queFaire","debloquer");
                       iu.putExtra("intentData",mat);
                       startActivity(iu);
                       finish();
                   }

                }



            }
        });

        scannerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String btnName = getIntent().getExtras().getString("btnName");
                if (btnName.equals("showUserProfil")) {
                    Intent sc = new Intent(getApplicationContext(), ScanActivity.class);
                    sc.putExtra("btnName", "showUserProfil");
                    startActivity(sc);
                }else if (btnName.equals("showAddPaiement")){
                    Intent sci = new Intent(getApplicationContext(), ScanActivity.class);
                    sci.putExtra("btnName","showAddPaiement");
                    startActivity(sci);
                }else if (btnName.equals("showPayHistory")){
                    Intent sci = new Intent(getApplicationContext(), ScanActivity.class);
                    sci.putExtra("btnName","showPayHistory");
                    startActivity(sci);
                }else if (btnName.equals("showBlockUser")){
                    Intent sci = new Intent(getApplicationContext(), ScanActivity.class);
                    sci.putExtra("btnName","showBlockUser");
                    startActivity(sci);
                }else if (btnName.equals("showDebloqueUser")){
                    Intent sci = new Intent(getApplicationContext(), ScanActivity.class);
                    sci.putExtra("btnName","showDebloqueUser");
                    startActivity(sci);
                }



            }
        });
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

}