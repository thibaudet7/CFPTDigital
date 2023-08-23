package group.acan.cfptdigital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EmailActivity extends Activity {

    EditText edtEmail, edtObjet, edtMessage;
    Button btnSendMail;
    ImageView btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        edtEmail = findViewById(R.id.edtEmail);
        edtObjet = findViewById(R.id.edtSujet);
        edtMessage = findViewById(R.id.edtMessage);
        btnSendMail = findViewById(R.id.btnSendMail);
        btnRetour = findViewById(R.id.btnRetour);

        btnSendMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = edtEmail.getText().toString().trim();
                String objet = edtObjet.getText().toString().trim();
                String message = edtMessage.getText().toString().trim();
                envoyerLeMail(mail, objet, message);
            }
        });
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                finish();
            }
        });

    }

    private void envoyerLeMail(String mail, String objet, String message) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{mail});
        i.putExtra(Intent.EXTRA_SUBJECT, objet);
        i.putExtra(Intent.EXTRA_TEXT   , message);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
            Toast.makeText(EmailActivity.this, "Votre message a été envoyé avec succés", Toast.LENGTH_SHORT).show();
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EmailActivity.this, "Aucun client de messagerie installé..", Toast.LENGTH_SHORT).show();
        }
    }
}