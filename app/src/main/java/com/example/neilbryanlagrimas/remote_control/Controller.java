package com.example.neilbryanlagrimas.remote_control;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Controller extends AppCompatActivity {

    ImageView imageView;

    private ProgressDialog progress;
    String address = null;
    Button up,down,up_left,up_right,down_left,down_right,brake,led;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        Intent newint = getIntent();
        address = newint.getStringExtra(Bluetooth_info.EXTRA_ADDRESS); //receive the address of the bluetooth device
        imageView = findViewById(R.id.controller);

        up = findViewById(R.id.up);
        down = findViewById(R.id.down);

        up_left = findViewById(R.id.up_left);
        up_right = findViewById(R.id.up_right);
        down_left = findViewById(R.id.bot_left);
        down_right = findViewById(R.id.bot_right);

        brake = findViewById(R.id.brake);

        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // PRESSED
                    up();
                    Toast.makeText(Controller.this,"BUTTON UP HOLD",Toast.LENGTH_SHORT).show();
                    return true;
               case MotionEvent.ACTION_UP:
                   // RELEASED
                   brakes();
                   Toast.makeText(Controller.this,"BUTTON UP RELEASE",Toast.LENGTH_SHORT).show();
                   return true; // if you want to handle the touch event
           }
                return false;
            }
        });

        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        down();
                        Toast.makeText(Controller.this,"BUTTON DOWN HOLD",Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        Toast.makeText(Controller.this,"BUTTON DOWN RELEASE",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        up_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        up_left();
                        Toast.makeText(Controller.this,"BUTTON up_left HOLD",Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        Toast.makeText(Controller.this,"BUTTON up_left RELEASE",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        up_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        up_right();
                        Toast.makeText(Controller.this,"BUTTON up_right HOLD",Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        Toast.makeText(Controller.this,"BUTTON up_right RELEASE",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        down_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        down_left();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        Toast.makeText(Controller.this,"BUTTON down_left RELEASE",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });

        down_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        down_right();
                        Toast.makeText(Controller.this,"BUTTON down_right HOLD",Toast.LENGTH_SHORT).show();
                        return true;
                    case MotionEvent.ACTION_UP:
                        // RELEASED
                        brakes();
                        Toast.makeText(Controller.this,"BUTTON down_right RELEASE",Toast.LENGTH_SHORT).show();
                        return true; // if you want to handle the touch event
                }
                return false;
            }
        });


//        down.setOnClickListener(this);
//
//        up_left.setOnClickListener(this);
//        up_right.setOnClickListener(this);
//        down_left.setOnClickListener(this);
//        down_right.setOnClickListener(this);
//
//        brake.setOnClickListener(this);

        connection();
    }

    private void up(){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("1".toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,"Error cant sent data",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void down(){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("2".toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,"Error cant sent data",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void up_left(){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("3".toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,"Error cant sent data",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void up_right(){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("4".toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,"Error cant sent data",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void down_left(){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("5".toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,"Error cant sent data",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void down_right(){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("6".toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,"Error cant sent data",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void brakes(){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("0".toString().getBytes());
            }
            catch (IOException e)
            {
                Toast.makeText(this,"Error cant sent data",Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void connection(){
        try
        {
            if (btSocket == null)
            {
                myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                btSocket.connect();//start connection

                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Error btsocket failed",Toast.LENGTH_SHORT).show();
            }
        }
        catch (IOException e)
        {
            Toast.makeText(this,"Error not connected",Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//
//
//        if (v == up){
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    // PRESSED
//                    up();
//                    Toast.makeText(Controller.this,"BUTTON UP HOLD",Toast.LENGTH_SHORT).show();
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    // RELEASED
//                    brakes();
//                    Toast.makeText(Controller.this,"BUTTON UP RELEASE",Toast.LENGTH_SHORT).show();
//                    return true; // if you want to handle the touch event
//            }
//        }
//        else if (v == down){
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    // PRESSED
//                    down();
//                    Toast.makeText(Controller.this,"BUTTON DOWN HOLD",Toast.LENGTH_SHORT).show();
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    // RELEASED
//                    brakes();
//                    Toast.makeText(Controller.this,"BUTTON DOWN RELEASE",Toast.LENGTH_SHORT).show();
//                    return true; // if you want to handle the touch event
//            }
//        }
//
//        else if (v == up_left){
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    // PRESSED
//                    up_left();
//                    Toast.makeText(Controller.this,"BUTTON up_left HOLD",Toast.LENGTH_SHORT).show();
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    // RELEASED
//                    brakes();
//                    Toast.makeText(Controller.this,"BUTTON up_left RELEASE",Toast.LENGTH_SHORT).show();
//                    return true; // if you want to handle the touch event
//            }
//        }
//        else if (v == up_right){
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    // PRESSED
//                    up_right();
//                    Toast.makeText(Controller.this,"BUTTON up_right HOLD",Toast.LENGTH_SHORT).show();
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    // RELEASED
//                    brakes();
//                    Toast.makeText(Controller.this,"BUTTON up_right RELEASE",Toast.LENGTH_SHORT).show();
//                    return true; // if you want to handle the touch event
//            }
//        }
//        else if (v == down_left){
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    // PRESSED
//                    down_left();
//                    Toast.makeText(Controller.this,"BUTTON down_left HOLD",Toast.LENGTH_SHORT).show();
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    // RELEASED
//                    brakes();
//                    Toast.makeText(Controller.this,"BUTTON down_left RELEASE",Toast.LENGTH_SHORT).show();
//                    return true; // if you want to handle the touch event
//            }
//        }
//        else if (v == down_right){
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    // PRESSED
//                    down_right();
//                    Toast.makeText(Controller.this,"BUTTON down_right HOLD",Toast.LENGTH_SHORT).show();
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    // RELEASED
//                    brakes();
//                    Toast.makeText(Controller.this,"BUTTON down_right RELEASE",Toast.LENGTH_SHORT).show();
//                    return true; // if you want to handle the touch event
//            }
//        }
//        else if (v == brake){
//            switch (event.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    // PRESSED
//                    brakes();
//                    Toast.makeText(Controller.this,"BUTTON brake HOLD",Toast.LENGTH_SHORT).show();
//                    return true;
//                case MotionEvent.ACTION_UP:
//                    // RELEASED
//                    brakes();
//                    Toast.makeText(Controller.this,"BUTTON brake RELEASE",Toast.LENGTH_SHORT).show();
//                    return true; // if you want to handle the touch event
//            }
//        }
//
//
//
//        return false;
//    }


//    @Override
//    public void onClick(View v) {
//
//            if (v == up){
//                up();
//            }
////            else  if (v == down){
////                down();
////            }
//            else if (v == up_left){
//                up_left();
//            }
//            else if (v == up_right){
//                up_right();
//            }
//            else if (v == down_left){
//                down_left();
//            }
//            else if (v == down_right){
//                down_right();
//            }
//            else if (v == brake){
//                brakes();
//            }
//    }


}
