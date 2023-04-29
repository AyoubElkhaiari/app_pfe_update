package com.example.app_v7;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView doorState, windowState, lightState ;
    private ImageView onlineStatusDoor, onlineStatusWindow, onlineStatusLight;

    public ConfigFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfigFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfigFragment newInstance(String param1, String param2) {
        ConfigFragment fragment = new ConfigFragment();
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
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, container, false);

        doorState = view.findViewById(R.id.doorState) ;
        windowState = view.findViewById(R.id.windowState);
        lightState = view.findViewById(R.id.lightState) ;
        onlineStatusDoor = (ImageView) view.findViewById(R.id.onlineStatusDoor) ;
        onlineStatusWindow = view.findViewById(R.id.onlineStatusWindow) ;
        onlineStatusLight = view.findViewById(R.id.onlineStatusLight);


        if(State.doorOpen) {
            doorState.setText("Opened");
            onlineStatusDoor.setImageResource(R.drawable.green_dot);
        }
        else{
            doorState.setText("Closed");
            onlineStatusDoor.setImageResource(R.drawable.gray_dot);
        }




        if(State.windowsOpen) {
            windowState.setText("Opened");
            onlineStatusWindow.setImageResource(R.drawable.green_dot);
        }
        else{
            windowState.setText("Closed");
            onlineStatusWindow.setImageResource(R.drawable.gray_dot);
        }





        if(State.lightsOn) {
            lightState.setText("On");
            onlineStatusLight.setImageResource(R.drawable.green_dot);

        }
        else{
            lightState.setText("Off");
            onlineStatusLight.setImageResource(R.drawable.gray_dot);
        }
        return view ;
    }



}