package com.example.app_v7;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class ListDevicesFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int REQUEST_ENABLE_BT = 300;
    public static final String SERVICE_ID = "00001101-0000-1000-8000-00805F9B34FB"; //SPP UUID
    private BluetoothSocket bluetoothSocket;

    Button Next;
    ListView devicesList;

    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;

    List<String> deviceNames;


    private String mParam1;
    private String mParam2;

    public class ConnectThread extends Thread {

        private final BluetoothSocket thisSocket;
        private final BluetoothDevice thisDevice;
        private View view;


        @SuppressLint("MissingPermission")
        public ConnectThread(BluetoothDevice device, View view) {
            this.view = view;
            BluetoothSocket tmp = null;
            thisDevice = device;
            try {
                tmp = thisDevice.createInsecureRfcommSocketToServiceRecord(UUID.fromString(SERVICE_ID));
            } catch (IOException e) {
                Log.e("connectThread constructor", "Can't connect to service");
            }
            thisSocket = tmp;
        }

        @SuppressLint("MissingPermission")
        public void run() {
            // Cancel discovery because it otherwise slows down the connection.
            bluetoothAdapter.cancelDiscovery();

            try {
                thisSocket.connect();
                Log.d("ConnectThread::run", "connected");
            } catch (IOException connectException) {
                try {
                    connectException.printStackTrace();
                    Log.d("ConnectThread::run", "connection failed. Closing Socket");
                    thisSocket.close();
                } catch (IOException closeException) {
                    Log.e("ConnectThread::run", "Can't close socket");
                }
                return;
            }
            bluetoothSocket = thisSocket;
            MainActivity.bluetoothSocket = thisSocket;
        }

        public void cancel() {
            try {
                thisSocket.close();
            } catch (IOException e) {
                Log.e("TEST", "Can't close socket");
            }
        }
    }



    public ListDevicesFragment() {
        // Required empty public constructor
    }


    public static ListDevicesFragment newInstance(String param1, String param2) {
        ListDevicesFragment fragment = new ListDevicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bluetoothManager = getActivity().getSystemService(BluetoothManager.class);
        bluetoothAdapter = bluetoothManager.getAdapter();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private boolean connectDevice(BluetoothDevice device, View view) {
        //ConnectThread connectThread = new ConnectThread(device,view);
        //connectThread.start();
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        BluetoothSocket tmp = null;

        try {
            tmp = device.createInsecureRfcommSocketToServiceRecord(UUID.fromString(SERVICE_ID));
        } catch (IOException e) {
            Log.e("connectThread constructor", "Can't connect to service");
        }
        bluetoothSocket = tmp;
        bluetoothAdapter.cancelDiscovery();

        try {
            bluetoothSocket.connect();
            Log.d("ConnectThread::run", "connected");
        } catch (IOException connectException) {
            try {
                connectException.printStackTrace();
                Log.d("ConnectThread::run", "connection failed. Closing Socket");
                bluetoothSocket.close();
                return false;
            } catch (IOException closeException) {
                Log.e("ConnectThread::run", "Can't close socket");
                return false;
            }
        }
        MainActivity.bluetoothSocket=bluetoothSocket;
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_list_devices, container, false);
        devicesList=view.findViewById(R.id.deviceList);
        bondedDevices();
        devicesList.setOnItemClickListener((adapterView, view1, i, l) -> {
            String devName = deviceNames.get(i);
            BluetoothDevice device = getDeviceAddress(devName);
            boolean state = connectDevice(device,view1);
            if(state){
                Navigation.findNavController(view1).navigate(R.id.action_listDevicesFragment_to_btnControllerFragment);
            }
            else{
                Toast.makeText(getActivity(), "try again", Toast.LENGTH_LONG);
            }
        });

        return view;
    }

    private BluetoothDevice getDeviceAddress(String name) {
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
        }
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : pairedDevices) {
            String deviceName = device.getName();
            if (deviceName.equals(name)) {
                return device;
            }
        }
        return null;
    }

    private void bondedDevices() {


        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {

        }
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
        deviceNames = new ArrayList<>();
        if (pairedDevices.size() > 0) {
            // There are paired devices. Get the name and address of each paired device.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                deviceNames.add(deviceName);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1 , deviceNames);
        devicesList.setAdapter(adapter);
    }
}