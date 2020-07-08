/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.objets;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Padovano
 */
public class Shipment extends beans{
    private String shipNo,container;
    private ShipmentType type;
    private Customers customer;
    private Date created,modified;
    private final List<Lpn> box_scan=new ArrayList<>();

    public String getShipNo() {
        return shipNo;
    }

    public List<Lpn> getBox_scan() {
        return box_scan;
    }

    public boolean addScanBox(Lpn e) {
        return box_scan.add(e);
    }

    public boolean addAllBox(Collection<? extends Lpn> c) {
        return box_scan.addAll(c);
    }
    
    public void setShipNo(String shipNo) {
        this.shipNo = shipNo;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public ShipmentType getType() {
        return type;
    }

    public void setType(ShipmentType type) {
        this.type = type;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
    
}
