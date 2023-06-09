package com.example.app_v7;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class State {
    public static boolean doorOpen,windowsOpen,lightsOn;
    public static SharedPreferences STATE_PREFS;
    public static SharedPreferences.Editor stateEditor;
    private State state;
    private Context context;
    public State(Context context){
        this.context = context;
        initializeStorage();
        init();
    }
    public State getInstance(Context context){
        if(state==null){
            state = new State(context);
        }
        return state;
    }
    private  void initializeStorage() {
        STATE_PREFS = context.getSharedPreferences("states_saver", Context.MODE_PRIVATE);
        stateEditor = STATE_PREFS.edit();
    }
    private void init() {
        //hadi nfss l7aja b7al commands kat init state
        doorOpen = STATE_PREFS.getBoolean("doorOpen",false);
        windowsOpen = STATE_PREFS.getBoolean("windowOpen",false);
        lightsOn = STATE_PREFS.getBoolean("windowOpen",false);
    }


    public static Boolean getState(byte byteValue){
        if(byteValue == 1 || byteValue == 2){
            return doorOpen;
        }
        else if( byteValue == 3 || byteValue == 4){
            return windowsOpen;
        }
        else if(byteValue == 5 || byteValue == 6 ){
            return lightsOn;
        }
        return null;

    }
    public void setState(byte byteValue, boolean state){
        if(byteValue == 1 || byteValue == 2){
            doorOpen=state;
        }
        else if( byteValue == 3 || byteValue == 4){
            windowsOpen=state;
        }
        else if(byteValue == 5 || byteValue == 6 ){
            lightsOn=state;
        }
        saveState();
    }
    //hadi zedtha katsjjel l'etat dyal klshy fde9a w7da
    private void saveState(){
        stateEditor.putBoolean("doorOpen",doorOpen);
        stateEditor.putBoolean("windowOpen",windowsOpen);
        stateEditor.putBoolean("lightOpen",lightsOn);
    }
}
