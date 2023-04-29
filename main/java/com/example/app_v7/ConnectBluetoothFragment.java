package com.example.app_v7;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;


public class ConnectBluetoothFragment extends Fragment {

    BluetoothManager bluetoothManager;
    BluetoothAdapter bluetoothAdapter;
    Button nextButton;
    RelativeLayout layout;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public ConnectBluetoothFragment() {
        // Required empty public constructor
    }


    public static ConnectBluetoothFragment newInstance(String param1, String param2) {
        ConnectBluetoothFragment fragment = new ConnectBluetoothFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        bluetoothManager = getActivity().getSystemService(BluetoothManager.class);
        bluetoothAdapter = bluetoothManager.getAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connect_bluetooth, container, false);

        layout = view.findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable)layout.getBackground();
        animationDrawable.setEnterFadeDuration(200);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();


        nextButton=view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bluetoothAdapter.isEnabled()){
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, 100);
                }
                else{
                    Navigation.findNavController(view).navigate(R.id.action_connectBluetoothFragment_to_listDevicesFragment);
                }
            }
        });


        return view ;


    }
}