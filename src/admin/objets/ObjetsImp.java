/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.objets;

import admin.util.AppUtils;
import java.util.Map;

/**
 *
 * @author Padovano
 */
public interface ObjetsImp {
    default void marshall(Object... data){
        for(int i=0;i<data.length;i+=2){
            AppUtils.hydrated(this,data[i].toString(),data[i+1]);
        }
    }
    default void marshall(Map<String,Object> data){
        for (String key :
                data.keySet()) {
            AppUtils.hydrated(this,key,data.getOrDefault(key,null));
        }
    }

    default Map<String, Object> toData(){
        return AppUtils.transform(this);
    }

}
