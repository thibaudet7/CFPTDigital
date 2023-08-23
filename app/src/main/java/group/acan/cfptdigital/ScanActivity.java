package group.acan.cfptdigital;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class ScanActivity extends Activity {

    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    String intentData = "";
    String btnName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        btnName = getIntent().getExtras().getString("btnName");
        initViews();
    }

    private void initViews() {
        surfaceView = findViewById(R.id.surfaceView);

    }
    private void initialiseDetectorsAndSources() {

        //Toast.makeText(getApplicationContext(), "Ticket Control enregistré", Toast.LENGTH_SHORT).show();
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScanActivity.this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScanActivity.this, new
                                String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    //Toast.makeText(ScanActivity.this, intentData, Toast.LENGTH_SHORT).show();
                    txtBarcodeValue.post(new Runnable() {
                        @Override
                        public void run() {
                            intentData = barcodes.valueAt(0).displayValue;
                            if (btnName.equals("showUserProfil")){
                                Intent iu = new Intent(getApplicationContext(), InfoUserActivity.class);
                                iu.putExtra("intentData",intentData);
                                startActivity(iu);
                                finish();
                            }else if(btnName.equals("showPayHistory")){
                                Intent iu = new Intent(getApplicationContext(), PaymentHistoryActivity.class);
                                iu.putExtra("intentData",intentData);
                                startActivity(iu);
                                finish();
                            }else if(btnName.equals("showAddPaiement")){
                                Intent iu = new Intent(getApplicationContext(), AddPaiement.class);
                                iu.putExtra("intentData",intentData);
                                startActivity(iu);
                                finish();
                            }else if (btnName.equals("showBlockUser")){
                                Intent iu = new Intent(getApplicationContext(), BlockUser.class);
                                iu.putExtra("intentData",intentData);
                                iu.putExtra("queFaire","bloquer");
                                startActivity(iu);
                                finish();
                            }else if (btnName.equals("showDebloqueUser")){
                                Intent iu = new Intent(getApplicationContext(), BlockUser.class);
                                iu.putExtra("intentData",intentData);
                                iu.putExtra("queFaire","debloquer");
                                startActivity(iu);
                                finish();
                            }

                        }
                    });
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }
}
