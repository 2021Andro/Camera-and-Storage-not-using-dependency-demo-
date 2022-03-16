package com.example.camerademo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_PERMISSION_CODE = 1;
    private static final int STORAGE_PERMISSION_CODE = 2;
    private Button btnCamera, btnStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStorage = findViewById(R.id.btnStorage);
        btnCamera = findViewById(R.id.btnCamera);



        btnStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE);
            }
        });


    }

    public void checkPermission(String permission, int requestCode)
    {

        if(ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED)
        {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

        }
        else {

            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();



        }




    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE)
        {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                btnCamera.setText("Camera Permission Granted");

                Toast.makeText(this, "Camera Permission GRANTED", Toast.LENGTH_SHORT).show();

            }
            else
            {

                Toast.makeText(this, "Camera Permission DENIED", Toast.LENGTH_SHORT).show();

            }

        }
        else
        {


            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                btnStorage.setText("Storage Permission Granted");

                Toast.makeText(this, "Storage Permission GRANTED", Toast.LENGTH_SHORT).show();

            }
            else
            {

                Toast.makeText(this, "CameraStorage Permission DENIED", Toast.LENGTH_SHORT).show();

            }
        }








    }
}