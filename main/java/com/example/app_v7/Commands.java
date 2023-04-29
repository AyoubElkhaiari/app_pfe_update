package com.example.app_v7;
import java.util.HashMap;

public class Commands {

//    public void myCommands() {
//        SharedPreferences.Editor editor = getSharedPreferences("MyPrefsFile", MODE_PRIVATE).edit();
//        editor.putString("key", "value");
//        editor.apply();
//    }



//    public class Value{
//        byte byteValue;
//        boolean state;
//        public Value(byte byteValue, boolean state) {
//            this.byteValue = byteValue;
//            this.state = state;
//        }
//    }
//    static class State{
//        private static boolean doorOpen=false,windowsOpen=false,lightsOn=false;
//        public static Boolean getState(byte byteValue){
//            if(byteValue == 1 || byteValue == 2){
//                return doorOpen;
//            }
//            else if( byteValue == 3 || byteValue == 4){
//                return windowsOpen;
//            }
//            else if(byteValue == 5 || byteValue == 6 ){
//                return lightsOn;
//            }
//            return null;
//
//        }
//        public static void setState(byte byteValue,boolean state){
//            if(byteValue == 1 || byteValue == 2){
//                doorOpen=state;
//            }
//            else if( byteValue == 3 || byteValue == 4){
//                windowsOpen=state;
//            }
//            else if(byteValue == 5 || byteValue == 6 ){
//                lightsOn=state;
//            }
//        }
//    }

    private static HashMap<String,Value> commands;

    public Commands() {
        init();
    }
    private void init(){
        commands = new HashMap<String,Value>();
        commands.put("ouvre la porte",new Value((byte) 1,false));
        commands.put("ferme la porte",new Value((byte)2,true));
        commands.put("ouvre la fenêtre",new Value((byte)3,false));
        commands.put("ouvre la fenêtre",new Value((byte)4,true));
        commands.put("allume la lampe",new Value((byte)5,false));
        commands.put("eteins la lampe",new Value((byte)6,true));
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

//    private HashMap<String, String> commands;
//    private boolean isDoorOpen = false;
//    private boolean isWindowOpen = false;
//
//    public Commands() {
//
//        commands = new HashMap<>();
//        commands.put("openDoor", "open the door");
//        commands.put("closeDoor", "close the door");
//        commands.put("openWindow", "open the window");
//        commands.put("closeWindow", "close the window");
//    }
//
//    public void setCommand(String key, String newValue) {
//        if (commands.containsKey(key)) {
//            String oldValue = commands.get(key);
//            commands.replace(key, oldValue, newValue);
//        }
//    }
//
//    public boolean isDoorOpen() {
//        return isDoorOpen;
//    }
//
//    public boolean isWindowOpen() {
//        return isWindowOpen;
//    }
//
//    public void executeCommand(String command) {
//        boolean isOpen = false;
//        if (commands.containsValue(command)) {
//            for (Map.Entry<String, String> entry : commands.entrySet()) {
//                if (entry.getValue().toLowerCase().equals(command.toLowerCase())) {
//                    action(entry.getKey());
//                    break;
//                }
//            }
//        } else {
//            System.out.println("Invalid command!");
//        }
//    }
//    private void action(String commandKey) {
//
//        try {
//            Method method = this.getClass().getMethod(commandKey);
//            method.invoke(this);
//
//        } catch (NoSuchMethodException e) {
//            System.out.println("Invalid command!");
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            System.out.println("Error executing command!");
//        }
//
//    }
//
//    public void openDoor() {
//        // Send the open door command to the Arduino
//        // Send command over Bluetooth
//        isDoorOpen = true;
//    }
//
//    public void closeDoor() {
//        // Send the open door command to the Arduino
//        // Send command over Bluetooth
//        isDoorOpen = false;
//    }
//
//    public void openWindow() {
//        // Send the open door command to the Arduino
//        // Send command over Bluetooth
//        isWindowOpen = true;
//    }
//
//    public void closeWindow() {
//        // Send the open door command to the Arduino
//        // Send command over Bluetooth
//        isWindowOpen = false;
//    }





}
