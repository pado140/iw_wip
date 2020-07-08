/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.Manager;

import admin.objets.Lpn;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Padovano
 */
public class LpnManager extends ModelManager<Lpn>{
    private static LpnManager manager=null;

    
    public synchronized static LpnManager getManager() {
        if(manager==null)
            manager=new LpnManager();
        return manager;
    }


    @Override
    public boolean ajouter(Lpn objet) {
        requete="insert into box_id(lpn,masterctn,qty,confirm_qty) values(?,?,?,?)"; 
        return false;
    }

    @Override
    public boolean supprimer(Lpn Objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Lpn> Dynamicrecherche(String objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Lpn> Dynamicsearch(Lpn objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Lpn> liste() {
        Set<Lpn> listLpn=new HashSet<>();
        requete="select id,lpn,created,masterctn,qty,status,confirm_qty confirmqty from box_id";
        List<Map<String,Object>> data=connection.selectlist(requete); 
        data.forEach(d->{
            Lpn lpn=new Lpn(d);
            lpn.addAll(ManagerFactory.createModel(Managerlist.detaillpn).Dynamicrecherche(lpn.getId()+""));
            listLpn.add(lpn);
        });
        return listLpn;
    }

    @Override
    public Lpn search(Lpn objet) {
        List<Lpn> listLpn=new ArrayList<>();
        requete="select id,lpn,created,masterctn,qty,status,confirm_qty confirmqty from box_id where lpn=?";
        List<Map<String,Object>> data=connection.selectlist(requete,objet.getLpn()); 
        data.forEach(d->{
            System.out.println(d);
            Lpn lpn=new Lpn(d);
            lpn.addAll(ManagerFactory.createModel(Managerlist.detaillpn).Dynamicrecherche(lpn.getId()+""));
            listLpn.add(lpn);
        });
        return listLpn.get(0);
    }

    @Override
    public Lpn searchById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifier(Lpn ancien, Lpn nouveau) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
