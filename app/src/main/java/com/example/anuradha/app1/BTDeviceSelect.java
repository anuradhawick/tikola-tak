package com.example.anuradha.app1;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anuradha.app1.GameModes.HumanHumanNetwork;

import java.util.Set;


public class BTDeviceSelect extends Activity implements View.OnClickListener {

    private ImageView back, proceed;
    private Button play_second;
    private ListView btDevices;
    private ArrayAdapter<String> arrayAdapter;
    private BluetoothAdapter btAdapter;
    private BluetoothDevice selectedDevice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btdevice_select);

        back = (ImageView) findViewById(R.id.back);
        proceed = (ImageView) findViewById(R.id.proceed);
        btDevices = (ListView) findViewById(R.id.btdevices);
        play_second = (Button) findViewById(R.id.play_second);
        back.setOnClickListener(this);
        proceed.setOnClickListener(this);
        play_second.setOnClickListener(this);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        btDevices.setAdapter(arrayAdapter);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!btAdapter.isEnabled()) {
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(i);
        }
        //btAdapter.startDiscovery();
        final Set<BluetoothDevice> devices = btAdapter.getBondedDevices();

        for (BluetoothDevice b : devices) {
            arrayAdapter.add(b.getName());
        }

        btDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDevice = (BluetoothDevice) devices.toArray()[i];
                Toast.makeText(getApplicationContext(), "Selected " + selectedDevice.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.proceed:
                if (selectedDevice == null) {
                    Toast.makeText(this, "Select a device", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(getApplicationContext(), Game.class);
                    i.putExtra("type", 3);
                    i.putExtra("btDevice", selectedDevice);
                    startActivity(i);
                    break;
                }
            case R.id.play_second:
                Intent i = new Intent(this, Game.class);
                i.putExtra("type", 4);
                startActivity(i);
                break;
        }
    }
}
