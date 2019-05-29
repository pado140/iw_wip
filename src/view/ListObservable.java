/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import admin.util.tableDataAction;
import java.util.ArrayList;
import java.util.Collection;
import observateurs.Observateurs;
import observateurs.Observe;

/**
 *
 * @author Padovano
 */
public class ListObservable<T> extends ArrayList<T> implements Observe{

    tableDataAction tda;
    @Override
    public void ajouterObservateur(Observateurs ob) {
        this.obs.add(ob);
    }

    @Override
    public void retirerObservateur(Observateurs ob) {
        this.obs.remove(ob);
    }

    @Override
    public void alerter(Object... ob) {
        for(Observateurs o:this.obs)
            o.executer(ob);
    }

    @Override
    public void clear() {
        super.clear(); //To change body of generated methods, choose Tools | Templates.
        alerter("clear");
    }

    @Override
    public boolean remove(Object o) {
        boolean rem=super.remove(o); //To change body of generated methods, choose Tools | Templates.
        alerter(tda.REMOVE,o);
        return rem;
    }

    @Override
    public boolean add(T e) {
        boolean ad=super.add(e); //To change body of generated methods, choose Tools | Templates.
        alerter(tda.ADD,e);
        return ad;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean ad=super.addAll(c); //To change body of generated methods, choose Tools | Templates.
        
        alerter(tda.ADD,c);
        return ad;
    }
    
    
}
