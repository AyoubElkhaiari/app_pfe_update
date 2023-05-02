package com.example.app_v7;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class Commands {



    private static HashMap<String,Value> commands;




    public static SharedPreferences STATE_PREFS;
    public static SharedPreferences.Editor stateEditor;
    public static  SharedPreferences CMDS_PREFS ;
    public static SharedPreferences.Editor cmdsEditor ;
    private  Context context;



    public Commands(Context context) {
        this.context = context;
        initializeStorage();
        saveToStorage();
        init();


    }

    private  void initializeStorage() {
        STATE_PREFS = context.getSharedPreferences("states_saver", Context.MODE_PRIVATE);
        CMDS_PREFS = context.getSharedPreferences("cmds_saver", Context.MODE_PRIVATE);
        stateEditor = STATE_PREFS.edit();
        cmdsEditor = CMDS_PREFS.edit();
    }
    private void saveToStorage() {
        cmdsEditor.putString("1", "ouvre la porte" ).apply();
        cmdsEditor.putString("2", "ferme la porte" ).apply();
        cmdsEditor.putString("3", "ouvre la fenêtre").apply();
        cmdsEditor.putString("4", "ferme la fenêtre").apply();
        cmdsEditor.putString("5", "allume la lampe").apply();
        cmdsEditor.putString("6", "eteins la lampe").apply();

    }





    private void init() {
        commands = new HashMap<String, Value>();
        commands.put(CMDS_PREFS.getString("1", "-1"), new Value((byte) 1,false));
        commands.put(CMDS_PREFS.getString("2", "-1"), new Value((byte) 2,true));
        commands.put(CMDS_PREFS.getString("3", "-1"), new Value((byte) 3,false));
        commands.put(CMDS_PREFS.getString("4", "-1"), new Value((byte) 4,true));
        commands.put(CMDS_PREFS.getString("5", "-1"), new Value((byte) 5,false));
        commands.put(CMDS_PREFS.getString("6", "-1"), new Value((byte) 6,true));
    }


    public static void setCommand(String oldCommande, String newCommande) {
        if (commands.containsKey(oldCommande)){
            Value value=commands.get(oldCommande);
            commands.put(newCommande, value);
            commands.remove(oldCommande);
        }
    }
    public static Byte getCommande(String commande){
        commande.toLowerCase();
        Value value = commands.get(commande);
        if(value != null && (value.state == State.getState(value.byteValue))){
            State.setState(value.byteValue, !value.state);
            return value.byteValue;
        }
        else{
            return null;
        }
    }




    /* ==== States Section ==== */


    public void saveDoorState(boolean state) {

        stateEditor.putBoolean("door_state", state);
        stateEditor.apply();
    }

    public boolean getDoorState() {

        return STATE_PREFS.getBoolean("door_state", State.doorOpen);
    }


    public void saveWindowState(boolean state) {

        stateEditor.putBoolean("window_state", state);
        stateEditor.apply();
    }

    public boolean getWindowState() {

        return STATE_PREFS.getBoolean("window_state", State.windowsOpen);
    }

    public void saveLightState(boolean state) {

        stateEditor.putBoolean("light_state", state);
        stateEditor.apply();
    }

    public boolean getLightState() {

        return STATE_PREFS.getBoolean("light_state", State.lightsOn);
    }

    /* ==== Cmds Section ==== */

    public void resetCommands () {

        cmdsEditor.putString("1", "ouvre la porte").apply();
        cmdsEditor.putString("2", "ferme la porte").apply();
        cmdsEditor.putString("3", "ouvre la fenêtre").apply();
        cmdsEditor.putString("4", "ferme la fenêtre").apply();
        cmdsEditor.putString("5", "allume la lampe").apply();
        cmdsEditor.putString("6", "eteins la lampe").apply();

    }

    public void updateCmd(String key, String value) {

        cmdsEditor.putString(key, value).apply();
    }







}
