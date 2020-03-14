/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.CardLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import observateurs.Observateurs;
import observateurs.Observe;

/**
 *
 * @author Padovano
 */
public class Reaudit extends javax.swing.JInternalFrame implements Observateurs,Observe{
private final ConnectionDb conn = ConnectionDb.instance();
private DefaultTableModel tbm,tbm_error ;
private Map<Object,Integer> list;
private int batch_id;
private Set<String> listlpn,listonbatch;
private Set<Object[]> dataToSave;
private String ErrorLpn,po,style,color,customer;
    /**
     * Creates new form Audit_form
     */
    public Reaudit() {
        initComponents();
        init();
    }

    private void init(){
        batch.setModel(new javax.swing.DefaultComboBoxModel(loadBatches()));
        tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setRowCount(0);
        tbm_error= (DefaultTableModel) grid_error.getModel();
        tbm_error.setRowCount(0);
        listlpn=new LinkedHashSet<>();
        listonbatch=new LinkedHashSet<>();
        dataToSave=new LinkedHashSet();
    }
    
    private void loadData(int critere){
        
        DefaultTableModel tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setRowCount(0);
        String requete="select * from lpn_in_batch where batch_id=? and status_lpn<>'pass'";
        ResultSet rs=conn.select(requete, critere);
        boolean editablefield=false;
    try {
        while(rs.next()){
            listlpn.add(rs.getString("BOX_STICKERS").trim());
            editablefield=true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(Audit_form.class.getName()).log(Level.SEVERE, null, ex);
    }
    jTextField1.setEnabled(editablefield);
    count.setText("0/"+listlpn.size());
    }
    private boolean canScan(Object[] ob,String sticker){
        ErrorLpn=null;
        String lpn=sticker;
        if(lpn.contains("-"))
            lpn=lpn.split("-")[0];
        try{
            if(listlpn.contains(lpn)||listlpn.contains(sticker)){
                if(listonbatch.contains(lpn))
                {
                    ErrorLpn="Duplicate sticker.";
                    JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                        + "- already scan into this batch.", "error scanning", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                Object[] data=ob;
                int stat=Integer.parseInt(data[1].toString());
                System.out.println(stat);
                if(stat!=2){
                    return true;
                }else if(Integer.parseInt(data[2].toString())!=0){
                    System.out.println(data[2]);
                    ErrorLpn="already shipped.";
                    JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                        + "- already shipped.", "error scanning", JOptionPane.ERROR_MESSAGE);
                }else{
                    return true;
//                 JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
//                    + "- already scan on another batch.", "error scanning", JOptionPane.ERROR_MESSAGE); 
//                 ErrorLpn="already scan on another batch.";
                }
            }else{
             JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                    + "- it was on this batch.", "error scanning", JOptionPane.ERROR_MESSAGE);
             ErrorLpn="was not scan on this batch.";
            }
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                    + "- invalid sticker.", "error scanning", JOptionPane.ERROR_MESSAGE);
            ErrorLpn="Not Valid by WIP(Sticker invalid).";
        }
        return false;
    }
    private Object[] loadBatches(){
        list=new LinkedHashMap<>();
        Object[] ob;
        String requete="select * from batches where status='Audited' and pass=0 ";
        ResultSet rs=conn.select(requete);
    try {
        list.put("--selected--",0);
        while(rs.next()){
            list.put("Batch No "+rs.getInt("id"),rs.getInt("id"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(Audit_form.class.getName()).log(Level.SEVERE, null, ex);
    }
        ob=new Object[list.size()];
        ob=list.keySet().toArray(ob);
        return ob;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        count = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        batch = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        batch_no = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grid_error = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("RE-AUDIT");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeiconified(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Batch No.");

        jLabel4.setText("Box:");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("...");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        batch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        batch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                batchItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Scan Sticker");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jTextField1.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Select Batch No:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(batch, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 376, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(batch_no, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(240, 240, 240)
                .addComponent(jButton1)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(batch_no, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(batch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "LPN", "PO", "STYLE", "DESCRIPTION", "COLOR CODE", "COLOR", "SIZE", "QTY", "REPLACEMENT"
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
        jScrollPane1.setViewportView(grid_data);

        grid_error.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "lpn", "Error"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(grid_error);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void batchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_batchItemStateChanged
        // TODO add your handling code here:
        System.out.println("change");
        batch_id=list.get(evt.getItem());
        loadData(list.get(evt.getItem()));
        
    }//GEN-LAST:event_batchItemStateChanged

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeiconified
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_formInternalFrameDeiconified

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            System.out.println(evt.getKeyCode()==10);
            if(!jTextField1.getText().trim().isEmpty())
            if(set(jTextField1.getText().trim())){
//                //mostrar();
                count.setText(String.valueOf(grid_data.getRowCount()));
            }
            jTextField1.setText("");
        }

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        boolean istrue=true;
        for(Object[] ob:dataToSave){
            System.out.println(ob[1]);
            istrue=save(ob);
        } 
        String requete1="update batches set status='repacked' where id=?";
        istrue=conn.Update(requete1, 0,batch_id);
        conn.Update("insert into batch_transac (batch_id,status,user_id) values(?,?,?)", 0,batch_id,"repacked",Principal.user_id);
        String requete2="insert into TRANSAC(TRANSACT,ITEM,QTY,ACT_TYPE,ACT_NAME,SUB_ITEM,QTY_SUBITEM,user_id) values ('Re-INSPECTION',?,0,8,?,NULL,NULL,?)";
        conn.Update(requete2, 0, new Object[]{batch_id,"recreate batch",Principal.user_id});
        JOptionPane.showMessageDialog(this, "Save succesfully");
        init();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        batchSelect bs=new batchSelect(null, false);
        bs.ajouterObservateur(this);
        this.ajouterObservateur(bs);
        alerter("batches",loadBatches());
    }//GEN-LAST:event_jButton2ActionPerformed

    private boolean save(Object[] data){
        boolean istrue=true;
        if(istrue){
            String requete="update batches_lpn set status='reopen' where id=?";
            istrue=conn.Update(requete, 0,data[0]); 
        }
        if(istrue){
        String requete="update box_contain set status=1 where id=?";
        istrue=conn.Update(requete, 0, data[3]);
        conn.Update("insert into lpn_in_batch_transac(idbatch,idlpn,box_stickers,qty,status,action,user_id) values(?,?,?,?,?,?,?)", 0,batch_id, data[3],data[1],data[2],"open","rescan",Principal.user_id);
        String requete1="insert into TRANSAC(TRANSACT,ITEM,QTY,ACT_TYPE,ACT_NAME,SUB_ITEM,QTY_SUBITEM,user_id) values ('Re-Packed',?,?,8,?,?,?,?)";
        conn.Update(requete1, 0, new Object[]{batch_id,data[2],"reScan on batch",data[1],data[2],Principal.user_id});
        }
        return istrue;
    }
    private boolean set(String sticker){
        int idlpn=0;
        Object[] data=getLpn(sticker);
        boolean istrue=true;
        boolean isDash=false;
        try{
        String lpn=data[7].toString();
        if(canScan(data,lpn)){
            listonbatch.add(lpn);
            idlpn=Integer.parseInt(data[0].toString());
            dataToSave.add(new Object[]{idlpn,lpn,data[8],data[10],data[11]});
            tbm.addRow(new Object[]{
            lpn,data[3],data[5].toString().replace('.', '-').split("-")[0],"n/a",
                data[5].toString().replace('.', '-').split("-")[1],
            data[4].toString(),data[5].toString().replace('.', '-').split("-")[2],data[8]});
        }else{
            tbm_error.addRow(new Object[]{lpn,ErrorLpn});
        }
        }catch(NullPointerException e){
            tbm_error.addRow(new Object[]{sticker,"this box sticker was not scan yet on any batch"});
        }
        
        //String
        return istrue;
    }
    
    private Object[] getLpn(String lpn){
        String requete="select * from lpn_in_batch where lpn=? or box_stickers=?";
        ResultSet rs=conn.select(requete,jTextField1.getText().trim(),jTextField1.getText().trim());
    try {
        while(rs.next()){
            return new Object[]{
            rs.getString("id"),rs.getString("status"),rs.getInt("shipment_id"),rs.getString("ponum"),rs.getString("coldsp"),rs.getString("sku"),rs.getString("brand")
                    ,rs.getString("box_stickers").trim(),rs.getInt("last_qty"),rs.getInt("batch_id"),rs.getInt("lpn_id"),rs.getString("ordnum")};
            //listlpn.add(rs.getString("box_stickers").trim());
        }
    } catch (SQLException ex) {
        Logger.getLogger(create_batch.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox batch;
    private javax.swing.JLabel batch_no;
    private javax.swing.JLabel count;
    private javax.swing.JTable grid_data;
    private javax.swing.JTable grid_error;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private CardLayout cardlayout=new CardLayout();
    @Override
    public void executer(Object... obs) {
        
        if(obs[0].toString().equals("batches-return")){
            String batche=obs[1].toString();
            batch.setSelectedItem(batche);
        }
    }
    
    @Override
    public void ajouterObservateur(Observateurs ob) {
        obs.add(ob);
    }

    @Override
    public void retirerObservateur(Observateurs ob) {
        obs.remove(ob);
    }

    @Override
    public void alerter(Object... ob) {
        obs.forEach(o->{o.executer(ob);});
    }
}
