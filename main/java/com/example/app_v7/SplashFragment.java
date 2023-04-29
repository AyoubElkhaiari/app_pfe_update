package com.example.app_v7;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class SplashFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int DELAY_SCREEN = 3000 ;
    ImageView micro, bluetooth, settings, lightBulb ;
    TextView textSplashScreen, desc ;



    private String mParam1;
    private String mParam2;

    public SplashFragment() {
        // Required empty public constructor
    }


    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);


         /* === Getting Views === */


        micro = view.findViewById(R.id.micro) ;
        bluetooth = view.findViewById(R.id.bluetooth) ;
        settings = view.findViewById(R.id.settings) ;
        lightBulb = view.findViewById(R.id.lightBulb) ;
        textSplashScreen = view.findViewById(R.id.textSplashScreen) ;
        desc = view.findViewById(R.id.desc) ;

        /* === Loading animations === */

        Animation anim_micro = AnimationUtils.loadAnimation(getContext(), R.anim.to_left);
        Animation anim_bluetooth = AnimationUtils.loadAnimation(getContext(), R.anim.to_left_bluetooth) ;
        Animation anim_settings= AnimationUtils.loadAnimation(getContext(), R.anim.to_right_settings) ;
        Animation anim_light_bulb= AnimationUtils.loadAnimation(getContext(), R.anim.to_right_light_bulb) ;
        Animation anim_text = AnimationUtils.loadAnimation(getContext(), R.anim.from_bottom) ;

        /* === Start Animation === */

        animation(anim_micro, micro) ;
        animation(anim_bluetooth, bluetooth) ;
        animation(anim_settings, settings);
        animation(anim_light_bulb, lightBulb);
        animation(anim_text, textSplashScreen);
        animation(anim_text, desc);

        /* =========== */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_startFragment);
            }
        }, DELAY_SCREEN);




        return view ;
    }

    private void animation(Animation anim, View view) {
        anim.setFillAfter(true);
        view.setAnimation(anim);

    }
}