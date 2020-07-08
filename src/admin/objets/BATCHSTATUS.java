/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.objets;

/**
 *
 * @author Padovano
 */
public enum BATCHSTATUS {
    OPEN("open"),
    FAIL("fail"),
    PASS("pass");
     private String value;
    private BATCHSTATUS(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
}
