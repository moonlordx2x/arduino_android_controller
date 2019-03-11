package com.example.neilbryanlagrimas.remote_control;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button bluetooth,about,quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetooth = findViewById(R.id.bluetooth);
        about = findViewById(R.id.about);
        quit = findViewById(R.id.quit);


        bluetooth.setOnClickListener(this);
        about.setOnClickListener(this);
        quit.setOnClickListener(this);

    }


    public void Showdialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Credits");
        builder.setMessage("Jericho Erl B. Rañon\nNeil Bryan R. Lagrimas");
        builder.setCancelable(false);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


    @Override
    public void onClick(View v) {
        if(v == bluetooth){
            startActivity(new Intent(this,Bluetooth_info.class));
        }
        else if(v == about){
            Showdialog();
        }
        else if(v == quit){
            finish();
            System.exit(0);
        }
    }
}
