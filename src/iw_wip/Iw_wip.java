/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iw_wip;

import admin.Manager.ManagerFactory;
import admin.Manager.Managerlist;
import admin.objets.Lpn;
import admin.util.DateUtils;
import java.util.Calendar;
import java.util.concurrent.Executors;
import view.Principal;

/**
 *
 * @author Duvers
 */
public class Iw_wip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DateUtils du=DateUtils.getInstance();
        du.tString();
        Calendar c=Calendar.getInstance();
        c.set(2020, 6, 27);
        du.setDate(c.getTime());
        du.tString();
        
        Lpn lpn=new Lpn();
        lpn.setLpn("iw2016102167");
        
        lpn=(Lpn)ManagerFactory.createModel(Managerlist.lpn).search(lpn);
        
        System.out.println(lpn);
        lpn.getDetails().forEach(bd->{
            System.out.println(bd.toString());
        });
//        int cores = Runtime.getRuntime().availableProcessors();
//        System.out.println("Number of cores present: " + cores);
//        System.out.println("Using number of threads: " + cores/2);
//        Executors.newFixedThreadPool(cores);
//        Executors.newWorkStealingPool();
//        Principal.main(args);
    }
    
}
