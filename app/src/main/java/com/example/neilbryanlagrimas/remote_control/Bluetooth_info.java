package com.example.neilbryanlagrimas.remote_control;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Bluetooth_info extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    Button onBtn,offBtn,discoverBtn, pairedBtn;
    TextView status;
    ListView bluelist;

    BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_info);

        status = findViewById(R.id.status);
        bluelist = findViewById(R.id.bluelist);

        onBtn = findViewById(R.id.onBtn);
        offBtn = findViewById(R.id.offBtn);
        discoverBtn = findViewById(R.id.discover);
        pairedBtn = findViewById(R.id.paired);

        status.setText("");

        // Bluetooth Adapter & Bluetooth available
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bluetooth is available", Toast.LENGTH_SHORT).show();
        }

        // Bluetooth Status (On/Off)

        if (bluetoothAdapter.isEnabled()) {
            status.setText("Bluetooth is On");
        } else {
            status.setText("Bluetooth is Off");
        }

        onBtn.setOnClickListener(this);
        offBtn.setOnClickListener(this);
        discoverBtn.setOnClickListener(this);
        pairedBtn.setOnClickListener(this);
    }

    private void pairedDevicesList()
    {
        pairedDevices = bluetoothAdapter.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        bluelist.setAdapter(adapter);
        bluelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            Showdialog();
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);


            // Make an intent to start next activity.
            Intent i = new Intent(Bluetooth_info.this, Controller.class);
            //Change the activity.
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
            startActivity(i);
            finish();
        }
    };


    public void Showdialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pairing Device");
        builder.setCancelable(false);
        builder.setMessage("Connecting Bluetooth......");
        builder.create().show();
    }

    @Override
    public void onClick(View v) {
        //   On Bluetooth
        if (v == onBtn) {
            if (!bluetoothAdapter.isEnabled()) {
                Toast.makeText(this, "Turning on Bluetooth....", Toast.LENGTH_SHORT).show();
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                status.setText("Bluetooth is On");

            } else {
                Toast.makeText(this, "Bluetooth is already on", Toast.LENGTH_SHORT).show();
            }

        }

        if (v == offBtn) {
            if (bluetoothAdapter.isEnabled()) {
                bluetoothAdapter.disable();
                status.setText("Bluetooth is Off");

            } else {
                Toast.makeText(this, "Bluetooth is already off", Toast.LENGTH_SHORT).show();
            }

        }

        //   Paired Bluetooth
        else if (v == pairedBtn) {

            pairedDevicesList();
            bluelist.setBackgroundColor(Color.parseColor("#4BC2BF"));
//            if (bluetoothAdapter.isEnabled()) {
//                pairedlist.setText("Paired Devices\n\n");
//                Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
//                for (BluetoothDevice device : devices) {
//                    pairedlist.append("\nDevice :" + device.getName() + "," + device.getAddress() + "," +device + "\n\n");
//                }
//            } else {
//                pairedlist.setText("Turn on bluetooth to get paired devices");
//            }
        }
        //   Discover Bluetooth
        else if (v == discoverBtn) {
            if (!bluetoothAdapter.isDiscovering()) {
                Toast.makeText(this, "Bluetooth discovery....", Toast.LENGTH_SHORT).show();
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(enableBtIntent, REQUEST_DISCOVER_BT);

            }
            else{
                Toast.makeText(this, "Already Discovered....", Toast.LENGTH_SHORT).show();

            }
        }


    }
}
