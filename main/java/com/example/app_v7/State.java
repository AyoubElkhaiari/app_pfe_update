package com.example.app_v7;

public class State {
    public static boolean doorOpen=true,windowsOpen=false,lightsOn=false;
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
    public static void setState(byte byteValue,boolean state){
        if(byteValue == 1 || byteValue == 2){
            doorOpen=state;
        }
        else if( byteValue == 3 || byteValue == 4){
            windowsOpen=state;
        }
        else if(byteValue == 5 || byteValue == 6 ){
            lightsOn=state;
        }
    }
}