/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.objets;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Padovano
 */
public class Lpn extends beans{
    private String lpn,masterctn;
    private int qty,confirmqty;
    private final Set<BoxDetails> details=new HashSet<>();
    private LocalDateTime created;
    private STATUSLPN status;

    public Lpn() {
        super();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Set<BoxDetails> getDetails() {
        return details;
    }

    public boolean add(BoxDetails e) {
        return details.add(e);
    }

    public boolean addAll(Set<BoxDetails> c) {
        return details.addAll(c);
    }

    public void clear() {
        details.clear();
    }

    public Lpn(Map<String, Object> data) {
        super(data);
    }
    

    public String getLpn() {
        return lpn;
    }

    public void setLpn(String lpn) {
        this.lpn = lpn;
    }

    public String getMasterctn() {
        return masterctn;
    }

    public void setMasterctn(String masterctn) {
        this.masterctn = masterctn;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getConfirmqty() {
        return confirmqty;
    }

    public void setConfirmqty(int confirmqty) {
        this.confirmqty = confirmqty;
    }

    public STATUSLPN getStatus() {
        return status;
    }

    public void setStatus(STATUSLPN status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lpn{ Id=" +this.id + " , lpn=" + lpn + ", qty=" + qty + '}';
    }
    
    
}
