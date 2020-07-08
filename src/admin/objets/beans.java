/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.objets;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Padovano
 */
public abstract class beans implements ObjetsImp{
    protected int id;
    
    public beans(){
        id=0;
    }
    
    public beans(Map<String,Object> data){
        id=0;
        marshall(data);
    }
    
    protected synchronized boolean isNew(){
        return id==0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
     
    protected boolean isValid(){
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash=55*hash+Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final beans other = (beans) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
