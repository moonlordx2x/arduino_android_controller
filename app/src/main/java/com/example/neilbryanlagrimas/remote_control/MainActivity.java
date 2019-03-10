package com.example.neilbryanlagrimas.remote_control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button bluetooth,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetooth = findViewById(R.id.bluetooth);
        quit = findViewById(R.id.quit);


        bluetooth.setOnClickListener(this);
        quit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == bluetooth){
            startActivity(new Intent(this,Bluetooth_info.class));
        }
        else if(v == quit){
            finish();
            System.exit(0);
        }
    }
}
