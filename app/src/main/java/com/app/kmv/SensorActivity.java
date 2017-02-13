package com.app.kmv;

import android.content.DialogInterface;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    public static final String TAG = "SENS";
    RelativeLayout rLayout;

    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        rLayout = (RelativeLayout) findViewById(R.id.activity_sensor);

        b1 = (Button) findViewById(R.id.btnDialog);
        b2 = (Button) findViewById(R.id.btnToast);

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensorList = sm.getSensorList(Sensor.TYPE_ALL);

        for (Sensor s : sensorList) {
            Log.d(TAG, "name " + s.getName());
            Log.d(TAG, "name " + s.getVendor());
        }

        Sensor accelSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorEventListener sListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
//                Log.d(TAG, "ax: " + event.values[0]);
//                Log.d(TAG, "ay: " + event.values[1]);
//                Log.d(TAG, "az: " + event.values[2]);

                rLayout.setBackgroundColor(
                        Color.rgb(
                                a2c(event.values[0]),
                                a2c(event.values[1]),
                                a2c(event.values[2])
                        )
                );
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        sm.registerListener(sListener, accelSensor, 1000 * 1000 * 5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SensorActivity.this, "Hello there !", Toast.LENGTH_SHORT).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialogBuilder =
                        new AlertDialog.Builder(SensorActivity.this);
                dialogBuilder.setTitle("DIALOG");
                dialogBuilder.setMessage("This is a dialog");
                dialogBuilder.setPositiveButton("Cool", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SensorActivity.this, "Cool pressed", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogBuilder.setCancelable(false);
                dialogBuilder.show();


            }
        });

    }

    int a2c (float a) {
        return (int) (((a + 12) / 24) * 255);
    }
}
