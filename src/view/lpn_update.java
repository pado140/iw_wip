/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import connection.ConnectionDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Padovano
 */
public class lpn_update extends javax.swing.JInternalFrame {

    private ConnectionDb conn = ConnectionDb.instance();
    private String box_stickers="",ordnum="";
    private int initVal=0;
    /**
     * Creates new form lpn_update
     */
    public lpn_update() {
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        updatebtn = new javax.swing.JRadioButton();
        dashbtn = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        qty = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();
        btn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lpn_val = new javax.swing.JLabel();

        setClosable(true);

        jLabel1.setText("Scan lpn");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        group.add(updatebtn);
        updatebtn.setSelected(true);
        updatebtn.setText("Update");
        updatebtn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updatebtnStateChanged(evt);
            }
        });

        group.add(dashbtn);
        dashbtn.setText("Dash");
        dashbtn.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dashbtnStateChanged(evt);
            }
        });

        jLabel2.setText("Qty:");

        qty.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                qtyStateChanged(evt);
            }
        });
        qty.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                qtyPropertyChange(evt);
            }
        });
        qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                qtyKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 436, Short.MAX_VALUE)
                        .addComponent(updatebtn)
                        .addGap(29, 29, 29)
                        .addComponent(dashbtn)
                        .addGap(251, 251, 251))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updatebtn)
                            .addComponent(dashbtn))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "PO", "SKU", "COLOR", "QTY", "LPN", "STICKERS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(grid_data);

        btn.setText("Save New Dash");
        btn.setActionCommand("Save");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });

        jLabel3.setText("LPN:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lpn_val, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lpn_val, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            System.out.println(evt.getKeyCode()==10);
            fillData();
                //jTextField1.setText("");
        }else{
            if(initVal==0&&
                ordnum!=null &&
                box_stickers!=null)
                initVal=0;
                ordnum="";
                box_stickers="";
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void updatebtnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_updatebtnStateChanged
        // TODO add your handling code here:
        if(((JRadioButton)evt.getSource()).isSelected()){
            lpn_val.setText(jTextField1.getText());
            btn.setText("Update Lpn");
            btn.setActionCommand("update");
        }
    }//GEN-LAST:event_updatebtnStateChanged

    private void dashbtnStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dashbtnStateChanged
        // TODO add your handling code here:
        if(((JRadioButton)evt.getSource()).isSelected()){
            lpn_val.setText(jTextField1.getText().trim()+"-"+qty.getValue());
            btn.setText("Save new Dash");
            btn.setActionCommand("Save");
        }
    }//GEN-LAST:event_dashbtnStateChanged

    private void qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_qtyKeyReleased
        // TODO add your handling code here:
        if((Integer)qty.getValue()>0){
            if(updatebtn.isSelected()){
                lpn_val.setText(jTextField1.getText());
            }else{
                lpn_val.setText(jTextField1.getText().trim()+"-"+qty.getValue());
            }
        }
        
    }//GEN-LAST:event_qtyKeyReleased

    private void qtyStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_qtyStateChanged
        // TODO add your handling code here:
        if((Integer)qty.getValue()>0){
            if(updatebtn.isSelected()){
                lpn_val.setText(jTextField1.getText());
            }else{
                lpn_val.setText(jTextField1.getText().trim()+"-"+qty.getValue());
            }
        }
    }//GEN-LAST:event_qtyStateChanged

    private void qtyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_qtyPropertyChange
        // TODO add your handling code here:
        /*if((Integer)evt.getNewValue()>0){
        if(updatebtn.isSelected()){
        lpn_val.setText(jTextField1.getText());
        }else{
        lpn_val.setText(jTextField1.getText().trim()+"-"+evt.getNewValue());
        }
        }*/
    }//GEN-LAST:event_qtyPropertyChange

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        // TODO add your handling code here:
        if(initVal>0 &&!ordnum.isEmpty()&&!box_stickers.isEmpty()){
            boolean check=false;
            if(evt.getActionCommand().equals("Save")){
                if(!box_stickers.contains("-"))
                    check=saveVal();
                else
                {
                    JOptionPane.showMessageDialog(this, "you cannot update an lpn dash create a new one");
                    return;
                }
                
            }else{
                check=updateVal();
            }
            if(check)
                JOptionPane.showMessageDialog(this, "Success");
            else{
                JOptionPane.showMessageDialog(this, this.conn.getErreur());
            }
            initVal=0;
            ordnum="";
            box_stickers="";
            ((DefaultTableModel)grid_data.getModel()).setNumRows(0);
            lpn_val.setText("");
        }
        else{
            JOptionPane.showMessageDialog(this, "selected an lpn first or press enter after typing the lpn");
        }
    }//GEN-LAST:event_btnActionPerformed

    private void fillData(){
        String requete="select ponum,style,sku,coldsp,size,lpn,box_stickers, ordnum_147,qty from lpn where lpn = ? or box_stickers=?";
        ResultSet rs=conn.select(requete, jTextField1.getText().trim(),jTextField1.getText().trim());
        DefaultTableModel tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setRowCount(0);
        try {
            while(rs.next())
            {
                qty.setValue(rs.getInt("qty"));
                initVal=rs.getInt("qty");
                ordnum=rs.getString("ordnum_147");
                box_stickers=rs.getString("box_stickers");
                tbm.addRow(new Object[]{rs.getString("ponum"),rs.getString("sku"),rs.getString("coldsp"),rs.getInt("qty"),rs.getString("lpn"),
                rs.getString("box_stickers")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(lpn_update.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean updateVal(){
        
        String last_num=box_stickers.substring((ordnum.trim()+initVal).length()-1);
        String new_stickers=ordnum.trim()+qty.getValue()+last_num;
        String requete="Update box_contain set box_stickers=? ,qty=? where lpn=?";
        String requete1="Update box_detail set stickers=? ,qty=? where lpndetails=?";
        conn.Update(requete, 0, new_stickers,qty.getValue(),lpn_val.getText());
        return conn.Update(requete1, 0, new_stickers,qty.getValue(),lpn_val.getText());
        //return false;
    }
    
    private boolean saveVal(){
        
        int last_num=nbLpn(ordnum.trim());
        String new_stickers=box_stickers+"-"+qty.getValue();
        String requete="INSERT INTO BOX_CONTAIN(ORDNUM,LPN,BOX_STICKERS,QTY) VALUES (?,?,?,?)";
        conn.savecst("{call create_box(?,?,?,?,?,?,?)}",ordnum.trim(),lpn_val.getText(),"",qty.getValue(),qty.getValue(),new_stickers,"");
        return conn.Update(requete, 1,ordnum.trim(),lpn_val.getText(), new_stickers,qty.getValue());
        //return false;
    }

    private int nbLpn(String ord){
        String requete="select count(ordnum) nb from BOX_CONTAIN where ordnum =?";
        ResultSet rs=conn.select(requete, ordnum);
        
        try {
            while(rs.next())
            {
                return rs.getInt("nb")+1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(lpn_update.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JRadioButton dashbtn;
    private javax.swing.JTable grid_data;
    private javax.swing.ButtonGroup group;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lpn_val;
    private javax.swing.JSpinner qty;
    private javax.swing.JRadioButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
