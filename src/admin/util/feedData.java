/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.util;

import connection.ConnectionDbMYSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import view.Principal;

/**
 *
 * @author Padovano
 */
public class feedData extends Thread{
    ConnectionDbMYSQL conn=ConnectionDbMYSQL.instance();
    private int qty,auditor,supervisor,mod_id,hour,quality;
    private String stickers,module,building,date;

    public feedData(int qty, String stickers, String module, int hour, int quality ,String building,String date) {
        this.qty = qty;
        this.stickers = stickers;
        this.module = module;
        this.hour = hour;
        this.quality = quality;
        this.building=building;
        this.date=date;
    }
    

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        String requete="select staff_id,mod_id,role from new_view where f_id=4 and module=? and building_name=?";
        
        try(ResultSet rs=conn.select(requete,module,building);){
            while(rs.next()){
               mod_id=rs.getInt("mod_id");
               if(rs.getString("role").trim().equalsIgnoreCase("auditor")){
                   auditor=rs.getInt("staff_id");
               }
               if(rs.getString("role").trim().equalsIgnoreCase("supervisor")){
                   supervisor=rs.getInt("staff_id");
               }
            }
        }catch(SQLException ex){
            
        }
        
        String requete2="insert into aga_production_repports (rp_date,rh_id,qty_producted_units,qty_produced_dozens,cls_id,mod_id,supervisor,auditor,reporter,sewingnum) "
                + "values(?,?,?,?,?,?,?,?,?,?)";
        conn.Update(requete2, 0, date,hour,qty,qty/12,quality,mod_id,supervisor,auditor,Principal.staff_id,stickers);
        //String requete="select staff_id,mod_id from new_view where f_id=4 and module=?";
        
    }
    
    
}
