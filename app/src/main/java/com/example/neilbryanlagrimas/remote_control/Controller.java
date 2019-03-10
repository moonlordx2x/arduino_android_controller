package com.example.neilbryanlagrimas.remote_control;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Controller extends AppCompatActivity implements View.OnClickListener {

    ImageView imageView;

    private ProgressDialog progress;
    String address = null;
    Button up,left,right,down,up_left,up_right,down_left,down_right,brake,led;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        Intent newint = getIntent();
        address = newint.getStringExtra(Bluetooth_info.EXTRA_ADDRESS); //receive the address of the bluetooth device
        imageView = findViewById(R.id.controller);

        up = findViewById(R.id.up);
        left = findViewById(R.id.left);
        right = findViewById(R.id.right);
        down = findViewById(R.id.down);

        up_left = findViewById(R.id.up_left);
        up_right = findViewById(R.id.up_right);
        down_left = findViewById(R.id.bot_left);
        down_right = findViewById(R.id.bot_right);

        brake = findViewById(R.id.brake);

        up.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        down.setOnClickListener(this);

        up_left.setOnClickListener(this);
        up_right.setOnClickListener(this);
        down_left.setOnClickListener(this);
        down_right.setOnClickListener(this);

        brake.setOnClickListener(this);

        connection();
    }

    private void up(){
        imageView.setImageResource(R.drawable.speedometer);
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

    private void left(){
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

    private void right(){
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

    private void up_left(){
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

    private void up_right(){
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

    private void down_left(){
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("7".toString().getBytes());
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
                btSocket.getOutputStream().write("8".toString().getBytes());
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
                btSocket.getOutputStream().write("9".toString().getBytes());
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


    @Override
    public void onClick(View v) {

            if (v == up){
                up();
            }
            else  if (v == down){
                down();
            }
            else  if (v == left){
                left();
            }
            else  if (v == right){
                right();
            }
            else if (v == up_left){
                up_left();
            }
            else if (v == up_right){
                up_right();
            }
            else if (v == down_left){
                down_left();
            }
            else if (v == down_right){
                down_right();
            }
            else if (v == brake){
                brakes();
            }
    }


}
