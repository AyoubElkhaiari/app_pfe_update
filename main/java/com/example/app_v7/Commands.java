package com.example.app_v7;
import android.content.Context;
import android.content.SharedPreferences;



import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Commands {


    private static Commands commands;
    private HashMap<String,Value> commandsMap;
    private State state;
    private HashMap<String,String> values;



    public static  SharedPreferences CMDS_PREFS ;
    public static SharedPreferences.Editor cmdsEditor ;
    private  Context context;

    //private constructor to keep only one instance in the app

    private Commands(Context context) {
        this.context = context;
        initializeStorage();
        init();
        initValues();
        state = state.getInstance(context);
    }
    public static Commands getInstance(Context context){
        if(commands==null){
            commands = new Commands(context);
        }
        return commands;
    }

    private void initializeStorage() {
        CMDS_PREFS = context.getSharedPreferences("cmds_saver", Context.MODE_PRIVATE);
        cmdsEditor = CMDS_PREFS.edit();

    }
    private void saveToStorage() {
        //rej3t hadi generic ch7al ma kano dles commandes
        for(Map.Entry<String,Value> entry :commandsMap.entrySet()){
            cmdsEditor.putString(String.valueOf(entry.getValue().getByteValue()), entry.getKey()).apply();
        }

    }





    private void init() {
        commandsMap = new HashMap<String, Value>();
        commandsMap.put(CMDS_PREFS.getString("1", "ouvre la porte"), new Value((byte) 1, false));
        commandsMap.put(CMDS_PREFS.getString("2", "ferme la porte"), new Value((byte) 2, true));
        commandsMap.put(CMDS_PREFS.getString("3", "ouvre les fenêtre"), new Value((byte) 3, false));
        commandsMap.put(CMDS_PREFS.getString("4", "ferme les fenêtre"), new Value((byte) 4, true));
        commandsMap.put(CMDS_PREFS.getString("5", "allume la lampe"), new Value((byte) 5, false));
        commandsMap.put(CMDS_PREFS.getString("6", "eteins la lampe"), new Value((byte) 6, true));
    }
    private void initValues(){
        values = new HashMap<String,String>();
        values.put("ouvre la porte",CMDS_PREFS.getString("1", "ouvre la porte"));
        values.put("ferme la porte",CMDS_PREFS.getString("2", "ferme la porte"));
        values.put("ouvre les fenêtres",CMDS_PREFS.getString("3", "ouvre les fenêtres"));
        values.put("ferme les fenêtres",CMDS_PREFS.getString("3", "ferme les fenêtres"));
        values.put("allume la lampe",CMDS_PREFS.getString("3", "allume la lampe"));
        values.put("eteins la lampe",CMDS_PREFS.getString("3", "eteins la lampe"));
    }
    public String getValue(String key){
        return values.get(key);
    }

    public void setCommand(String oldCommande, String newCommande) {
        if (commandsMap.containsKey(oldCommande)){
            Value value= commandsMap.get(oldCommande);
            commandsMap.put(newCommande, value);
            commandsMap.remove(oldCommande);
            //hadi drt mor makatbdl chi commande kitsejjlo
            saveToStorage();
        }
    }
    public Byte getCommande(String commande){
        commande.toLowerCase();
        Value value = commandsMap.get(commande);
        if(value != null && (value.getState() == state.getState(value.getByteValue()))){
            state.setState(value.getByteValue(), !value.getState());
            return value.getByteValue();
        }
        else{
            return null;
        }
    }






    public void resetCommands () {

        cmdsEditor.putString("1", "ouvre la porte").apply();
        cmdsEditor.putString("2", "ferme la porte").apply();
        cmdsEditor.putString("3", "ouvre la fenêtre").apply();
        cmdsEditor.putString("4", "ferme la fenêtre").apply();
        cmdsEditor.putString("5", "allume la lampe").apply();
        cmdsEditor.putString("6", "eteins la lampe").apply();
        init();

    }









}