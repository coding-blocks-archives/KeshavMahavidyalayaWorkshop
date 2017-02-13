package com.app.kmv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    Button btn, btnSensor;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.etVar1);
        et2 = (EditText) findViewById(R.id.etVar2);
        btn = (Button) findViewById(R.id.btnAdd);
        tv = (TextView) findViewById(R.id.tvResult);

        btnSensor = (Button) findViewById(R.id.btnSensor);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.valueOf(et1.getText().toString());
                int b = Integer.valueOf(et2.getText().toString());

                int c = a + b;

                tv.setText(String.valueOf(c));

                Intent i = new Intent(MainActivity.this, AnotherActivity.class);
                i.putExtra("result", c);
                startActivity(i);
            }
        });

        btnSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SensorActivity.class);
                startActivity(i);
            }
        });



    }
}
