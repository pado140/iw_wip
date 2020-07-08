/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.Manager;

import admin.objets.BoxDetails;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Padovano
 */
public class BoxDetailManager extends ModelManager<BoxDetails>{
    private static BoxDetailManager manager=null;

    
    public synchronized static BoxDetailManager getManager() {
        if(manager==null)
            manager=new BoxDetailManager();
        return manager;
    }

    @Override
    public boolean ajouter(BoxDetails objet) {
        requete="insert into box_detail(box_id,ordnum,qty,stickers,lpndetails,confirmqt) values(?,?,?,?,?,?)";
        if(connection.Update(requete, 1, objet.getLpn().getId(),objet.getOrdnum(),objet.getQty(),
                objet.getStickers(),objet.getLpndetails(),objet.getConfirmqt())){
            return true;
        }
        return false;
    }

    @Override
    public boolean supprimer(BoxDetails Objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<BoxDetails> Dynamicrecherche(String objet) {
        Liste=new HashSet<>();
        requete="select id,ordnum,qty,stickers,lpndetails,box_id as lpn_id from box_detail where box_id=?";
        List<Map<String,Object>> data=this.connection.selectlist(requete, objet);
        data.forEach(d->{
            BoxDetails bd=new BoxDetails(d);
            Liste.add(bd);
        });
        return Liste;
    }

    @Override
    public Set<BoxDetails> Dynamicsearch(BoxDetails objet) {
        return null;
    }

    @Override
    public Set<BoxDetails> liste() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BoxDetails search(BoxDetails objet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BoxDetails searchById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modifier(BoxDetails ancien, BoxDetails nouveau) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
