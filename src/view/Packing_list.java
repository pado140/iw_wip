/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import observateurs.Observateurs;
import observateurs.Observe;

/**
 *
 * @author Padovano
 */
public class Packing_list extends javax.swing.JInternalFrame implements Observe,Observateurs{

    private ConnectionDb conn; 
    private DefaultTableModel tbm;
    private packing_by_shipment pbs;
    private EDIT_SHIPMENT EDS;
    
    private Set<Object[]> datas=new HashSet<>();
    /**
     * Creates new form Packing_list
     */
    public Packing_list() {
        initComponents();
        init();
    }

    private void init(){
        conn = ConnectionDb.instance();
        tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setRowCount(0);
        fillTable();
        pbs=new packing_by_shipment(new JFrame(), false);
        EDS= new EDIT_SHIPMENT(new JFrame(),false);
        this.ajouterObservateur(pbs);
        this.ajouterObservateur(EDS);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop_up = new javax.swing.JPopupMenu();
        pack = new javax.swing.JMenuItem();
        close = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();

        pack.setText("Packing List");
        pack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packActionPerformed(evt);
            }
        });
        pop_up.add(pack);

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        pop_up.add(close);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        pop_up.add(edit);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("All Shipments");

        jLabel1.setText("Shipment number:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        grid_data.setAutoCreateRowSorter(true);
        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Shipment #", "Container #", "Boxes", "Pieces", "Date", "Customer", "shipment_id", "status_shipment", "type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grid_data.getTableHeader().setReorderingAllowed(false);
        grid_data.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                grid_dataFocusLost(evt);
            }
        });
        grid_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grid_dataMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                grid_dataMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(grid_data);
        if (grid_data.getColumnModel().getColumnCount() > 0) {
            grid_data.getColumnModel().getColumn(6).setMinWidth(0);
            grid_data.getColumnModel().getColumn(6).setPreferredWidth(0);
            grid_data.getColumnModel().getColumn(6).setMaxWidth(0);
            grid_data.getColumnModel().getColumn(7).setPreferredWidth(0);
            grid_data.getColumnModel().getColumn(8).setMinWidth(0);
            grid_data.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
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

    private void grid_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grid_dataMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_grid_dataMouseClicked

    private void grid_dataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grid_dataMouseReleased
        // TODO add your handling code here:
        pop_up.removeAll();
        if(evt.getButton()==MouseEvent.BUTTON3){
            pop_up.add(pack);
            pop_up.add(edit);
            if(!grid_data.getSelectionModel().isSelectionEmpty()){
                if(grid_data.getValueAt(grid_data.getSelectedRow(), 7).toString().equals("Open"))
                    pop_up.add(close);
            pop_up.show(grid_data,evt.getX(),evt.getY());
            
            }
            else
                JOptionPane.showMessageDialog(this, "Please select a row!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_grid_dataMouseReleased

    private void grid_dataFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_grid_dataFocusLost
        // TODO add your handling code here:
        //grid_data.clearSelection();
    }//GEN-LAST:event_grid_dataFocusLost

    private void packActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packActionPerformed
        // TODO add your handling code here:
        ArrayList<String> shipids=new ArrayList<>();
        for(int i=0;i<grid_data.getSelectedRowCount();i++){
            shipids.add(grid_data.getValueAt(grid_data.getSelectedRows()[i],6).toString());
        }
        alerter("packing_list_shipment",shipids);
        pbs.setModal(true);
        pbs.setVisible(true);
    }//GEN-LAST:event_packActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        // TODO add your handling code here:
        if(grid_data.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this, "you must select one column a time");
            grid_data.getSelectionModel().clearSelection();
            return;
            
        }
        int id_shipment=Integer.parseInt(grid_data.getValueAt(grid_data.getSelectedRow(), 6).toString());
        String shipment_number=grid_data.getValueAt(grid_data.getSelectedRow(), 0).toString();
        String requete="update Shipments set status=0 where id=?";
        int choix=JOptionPane.showConfirmDialog(this, "are you sure to close those shipment "+shipment_number+" ?", "confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(choix==JOptionPane.YES_OPTION){
        if(conn.Update(requete, 0,id_shipment)){
            JOptionPane.showMessageDialog(this, "Shipment closed");
            grid_data.setValueAt("Closed",grid_data.getSelectedRow(), 7);
        }
        }
    }//GEN-LAST:event_closeActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
        if(grid_data.getSelectedRowCount()>1){
            JOptionPane.showMessageDialog(this, "you must select one column a time");
            grid_data.getSelectionModel().clearSelection();
            return;
            
        }
        alerter("edit_shipment",grid_data.getValueAt(grid_data.getSelectedRow(), 6),grid_data.getSelectedRow());
        EDS.setModal(true);
        EDS.setVisible(true);
    }//GEN-LAST:event_editActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        tbm.setNumRows(0);
        for(Object[] obj:datas){
            if(obj[0].toString().toLowerCase().contains(jTextField1.getText().trim().toLowerCase())){
                tbm.addRow(obj);
            }
        }
    }//GEN-LAST:event_jTextField1KeyReleased
    
    private void fillTable(){
        String requete="select shipment_id,shipment_number,container_number,status_shipment,date_shipment,customer,count(lpn) nb_lpn,sum(qty) pieces"
                + ",typeShipment from packing_list_shipment group by shipment_id,shipment_number,container_number,date_shipment,customer,"
                + "status_shipment,typeShipment";
        ResultSet rs=conn.select(requete);
        try {
            while(rs.next()){
                tbm.addRow(new Object[]{rs.getString("shipment_number"),rs.getString("container_number"),rs.getInt("nb_lpn"),
                rs.getInt("pieces"),rs.getString("date_shipment"),rs.getString("customer"),rs.getInt("shipment_id"),
                rs.getInt("status_shipment")==1?"Open":"Closed",rs.getString("typeShipment")});
                datas.add(new Object[]{rs.getString("shipment_number"),rs.getString("container_number"),rs.getInt("nb_lpn"),
                rs.getInt("pieces"),rs.getString("date_shipment"),rs.getString("customer"),rs.getInt("shipment_id"),
                rs.getInt("status_shipment")==1?"Open":"Closed",rs.getString("typeShipment")});
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(New_shipment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem close;
    private javax.swing.JMenuItem edit;
    private javax.swing.JTable grid_data;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuItem pack;
    private javax.swing.JPopupMenu pop_up;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ajouterObservateur(Observateurs ob) {
        obs.add(ob);
    }

    @Override
    public void retirerObservateur(Observateurs ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alerter(Object... ob) {
        for(Observateurs o:obs)
            o.executer(ob);
    }

    @Override
    public void executer(Object... os) {
        if(os[0].equals("update shipment")){
            int ligne=Integer.parseInt(os[1].toString());
            grid_data.setValueAt(os[2], ligne, 0);
            grid_data.setValueAt(os[3], ligne, 1);
            grid_data.setValueAt(os[4], ligne, 5);
        }
    }
}
