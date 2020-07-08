/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.util;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 *
 * @author Padovano
 */
public class PrefManager {
    private static PrefManager pref=null;
    private static Preferences preference;
    private PrefManager() {
        preference=Preferences.userRoot().node(PrefManager.class.getName());
    }
    
    public static synchronized PrefManager getInstance(){
        if(pref==null)
            pref=new PrefManager();
        return pref;
    }
    
    public String getString(String key){
        return preference.get(key, "");
    }

    public void put(String key, String value) {
        preference.put(key, value);
    }

    public void putInt(String key, int value) {
        preference.putInt(key, value);
    }

    public int getInt(String key, int def) {
        return preference.getInt(key, def);
    }

    public void putLong(String key, long value) {
        preference.putLong(key, value);
    }

    public long getLong(String key, long def) {
        return preference.getLong(key, def);
    }

    public void putBoolean(String key, boolean value) {
        preference.putBoolean(key, value);
    }

    public boolean getBoolean(String key, boolean def) {
        return preference.getBoolean(key, def);
    }

    public void putFloat(String key, float value) {
        preference.putFloat(key, value);
    }

    public float getFloat(String key, float def) {
        return preference.getFloat(key, def);
    }

    public void putDouble(String key, double value) {
        preference.putDouble(key, value);
    }

    public double getDouble(String key, double def) {
        return preference.getDouble(key, def);
    }

    public boolean isUserNode() {
        return preference.isUserNode();
    }

    public void clear() {
        try{
        preference.clear();
        }catch(BackingStoreException ex){
            System.err.println("error:  "+ex.getMessage());
        }
    }
    
    
}
