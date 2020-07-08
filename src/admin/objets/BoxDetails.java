/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.objets;

import java.util.Map;

/**
 *
 * @author Padovano
 */
public class BoxDetails extends beans{
    private Lpn lpn;
    private int qty,confirmqt;
    private String stickers,lpndetails,ordnum;

    public BoxDetails() {
        super();
    }

    public BoxDetails(Map<String, Object> data) {
        super(data);
    }

    
    public String getOrdnum() {
        return ordnum;
    }

    public void setOrdnum(String ordernum) {
        this.ordnum = ordernum;
    }

    
    public Lpn getLpn() {
        return lpn;
    }

    public void setLpn(Lpn lpn) {
        this.lpn = lpn;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qtyDetails) {
        this.qty = qtyDetails;
    }

    public int getConfirmqt() {
        return confirmqt;
    }

    public void setConfirmqt(int confirm) {
        this.confirmqt = confirm;
    }

    public String getStickers() {
        return stickers;
    }

    public void setStickers(String stickers) {
        this.stickers = stickers;
    }

    public String getLpndetails() {
        return lpndetails;
    }

    public void setLpndetails(String details) {
        this.lpndetails = details;
    }

    @Override
    public String toString() {
        return "BoxDetails{" + "qty=" + qty + ", stickers=" + stickers + ", ordnum=" + ordnum + " \nbox="+ this.lpn.toString()+'}';
    }
    
    
}
