/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Padovano
 */
public class update_production extends javax.swing.JInternalFrame {
private final ConnectionDb conn = ConnectionDb.instance();
private DefaultTableModel tbm;
private Object [][] data=null;
private String order,travel,stravel;
private int lot=0,qty=0,sewn=0;
    /**
     * Creates new form update_production
     */
    public update_production() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        unscan = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        scrollpane = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();
        empty_label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        unscan.setText("jMenuItem1");
        jPopupMenu1.add(unscan);

        setClosable(true);
        setIconifiable(true);
        setTitle("First / OTFQ Upgrade");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Scan the Order code:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Generate blank sticker(First)");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Generate Exception sticker");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Generate blank sticker(Second)");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Generate Scrap sticker");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Generate Post Sewing sticker");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(737, 737, 737)
                        .addComponent(jButton4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Order Number", "SEWING TRAVELLER", "STICKER", "LOT", "TYPE", "STATUS", "value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grid_data.setColumnSelectionAllowed(true);
        grid_data.getTableHeader().setReorderingAllowed(false);
        scrollpane.setViewportView(grid_data);
        grid_data.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (grid_data.getColumnModel().getColumnCount() > 0) {
            grid_data.getColumnModel().getColumn(0).setMinWidth(0);
            grid_data.getColumnModel().getColumn(0).setMaxWidth(0);
            grid_data.getColumnModel().getColumn(4).setMinWidth(100);
            grid_data.getColumnModel().getColumn(4).setMaxWidth(100);
            grid_data.getColumnModel().getColumn(7).setMinWidth(0);
            grid_data.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        empty_label.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        empty_label.setForeground(new java.awt.Color(255, 0, 0));
        empty_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        empty_label.setText("No data found");

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollpane)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(empty_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(392, 392, 392))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(empty_label)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(scrollpane, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(17, 17, 17)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            System.out.println(evt.getKeyCode()==10);
            //if(!jTextField1.getText().trim().isEmpty())
                get(jTextField1.getText().trim());
                jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // TODO add your handling code here:
        String Code=JOptionPane.showInputDialog(this,"Please confirm your Access key","Confim",JOptionPane.WARNING_MESSAGE);
        String requete="select * from security_access where "
                + "code=?";
        JTable target=grid_data;
        if(target.isEditing())
            target.getCellEditor().stopCellEditing();
        target.clearSelection();
        boolean check=false;
        ResultSet rs=conn.select(requete, Code.trim());
    try {
        while(rs.next()){
            check=true;
        }
    } catch (SQLException ex) {
        Logger.getLogger(update_production.class.getName()).log(Level.SEVERE, null, ex);
    }
           if(check){
               String requete1="update sewing_production set qty_updated=? ,QTY_PER_LOT=? ,date_updated=getDate() where sew_id=?";
               String requete2="insert into TRANSAC(TRANSACT,ITEM,QTY,ACT_TYPE,ACT_NAME,SUB_ITEM,QTY_SUBITEM,user_id) values ('sewing Updated',?,?,3,?,?,?,?)";
               String error="";
               for(int i=0;i<target.getRowCount();i++){
                   Object[] data=value(target,i);
                   int oldqty=Integer.parseInt(data[7].toString());
                   int newqty=Integer.parseInt(data[4].toString());
                   if(!data[6].toString().equals("Invalid")&&!data[4].equals(data[7])){
                        if((sewn-oldqty+newqty)>qty){
                            JOptionPane.showMessageDialog(this, "you exceed the amount planned for this travel card");
                            return;
                        }else{
                            if((sewn-oldqty+newqty)<sewn){
                                if(JOptionPane.showConfirmDialog(this, "Are you sure you want to reduce the amount of production?", 
                                       "Downgrade production", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.NO_OPTION)
                                     return;
                                }
                            if(conn.Update(requete1, 0, data[7],data[4],data[0])){
                                conn.Update(requete2, 0, data[1],data[7],"Update qty to",data[3],data[4],Principal.user_id);
                            }else{
                                error+=conn.getErreur()+"\n";
                            }
                        }
                    }
                }
                   
               if(error.isEmpty()){
                   JOptionPane.showMessageDialog(this, "Succesfully saved");
                   init();
               }else{
                   JOptionPane.showMessageDialog(this, error);
               }
           }
           else
           {
               JOptionPane.showMessageDialog(this, "please enter a valid passkey");
           }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void savewash(Object... ob){
        String requete="INSERT INTO washing (stickers,QTY,type,ordernum,travel_no) VALUES(?,?,?,?,?)";
        if(!conn.Update(requete, 0, ob))
            System.err.println(conn.getErreur());
    }
    
    private void savepress(Object... ob){
        String requete="INSERT INTO press (stickers,QTY,type,ordnum,travel_no) VALUES(?,?,?,?,?)";
        if(!conn.Update(requete, 0, ob))
            System.err.println(conn.getErreur());
    }
    
    private void savematchbook(Object... ob){
        String requete="INSERT INTO matchbook (stickers,QTY,type,ordernum,travel_no) VALUES(?,?,?,?,?)";
        if(!conn.Update(requete, 0, ob))
            System.err.println(conn.getErreur());
    }
    
    private boolean step(String style,String step){
        String requete="select * from style_operations where style=? and name=?";
        ResultSet rs=conn.select(requete, style,step);
        try {
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(lot.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String requete="insert into sewing_production (qty_per_lot,s_traveller,slot,status,type_sew,order_num,lot_stickers) values(0,?,?,0,'first',?,?)";
         boolean match=false,wash=false,press=false;
         String style=stravel.replace(".", "-").split("-")[1];
                match=step(style,"MATCHBOOK");
                wash=step(style,"WASHING");
                press=step(style,"PRESS");
            
                //int A=alpha.
                String code=travel;
                if(lot<10)
                    code+="000"+(lot);
                else
                    code+="00"+(lot);
                
                    
                        if(match)
                            savematchbook(code,0,"first",order,travel);
                        if(wash)
                            savewash(code,0,"first",order,travel);
                        if(press)
                            savepress(code,0,"first",order,travel);
                        conn.Update(requete, 0, stravel,code,order,travel);
                        
                    
                
                        
            
            //System.out.println(data[5].toString());
            //
            //int line=Integer.parseInt(obs[1][5].toString());
        get(travel);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String option="";
        do{
            option=JOptionPane.showInputDialog("set the quantity:");
        }while(option.trim().isEmpty()|| option.matches("[A-Za-z ]+"));
        int val=Integer.parseInt(option.trim());
        if(val<=balance(travel, sewn)){
        String requete="insert into sewing_production (qty_per_lot,s_traveller,slot,status,type_sew,order_num,lot_stickers) values(?,?,?,0,'exception',?,?)";
         boolean match=false,wash=false,press=false;
         String style=stravel.replace(".", "-").split("-")[1];
                match=step(style,"MATCHBOOK");
                wash=step(style,"WASHING");
                press=step(style,"PRESS");
            
                //int A=alpha.
                String code=travel;
                if(lot<10)
                    code+="000"+(lot);
                else
                    code+="00"+(lot);
                
                    
//                        if(match)
//                            savematchbook(code,0,"first",order,travel);
//                        if(wash)
//                            savewash(code,0,"first",order,travel);
//                        if(press)
//                            savepress(code,0,"first",order,travel);
                        conn.Update(requete, 0, val,stravel,code,order,travel);
                       
        get(travel); 
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
        String requete="insert into sewing_production (qty_per_lot,s_traveller,slot,status,type_sew,order_num,lot_stickers) values(0,?,?,0,'second',?,?)";
        boolean match=false,wash=false,press=false;
         String style=stravel.replace(".", "-").split("-")[1];
                match=step(style,"MATCHBOOK");
                wash=step(style,"WASHING");
                press=step(style,"PRESS");
            
                //int A=alpha.
                String code=travel;
                if(lot<10)
                    code+="000"+(lot);
                else
                    code+="00"+(lot);
                
                    
                        if(match)
                            savematchbook(code,0,"second",order,travel);
                        if(wash)
                            savewash(code,0,"second",order,travel);
                        if(press)
                            savepress(code,0,"second",order,travel);
                
                        conn.Update(requete, 0, stravel,code,order,travel);
                       
        get(travel); 
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String option="";
//        do{
//            option=JOptionPane.showInputDialog("set the quantity:");
//        }while(option.trim().isEmpty()|| option.matches("[A-Za-z ]+"));
//        int val=Integer.parseInt(option.trim());
//        if(val<=balance(travel, sewn)){
        String requete="insert into sewing_production (qty_per_lot,s_traveller,slot,status,type_sew,order_num,lot_stickers) values(?,?,?,0,'scrap',?,?)";
         boolean match=false,wash=false,press=false;
         String style=stravel.replace(".", "-").split("-")[1];
                match=step(style,"MATCHBOOK");
                wash=step(style,"WASHING");
                press=step(style,"PRESS");
            
                //int A=alpha.
                String code=travel;
                if(lot<10)
                    code+="000"+(lot);
                else
                    code+="00"+(lot);
                
                    
                        if(match)
                            savematchbook(code,0,"scrap",order,travel);
                        if(wash)
                            savewash(code,0,"scrap",order,travel);
                        if(press)
                            savepress(code,0,"scrap",order,travel);
                        conn.Update(requete, 0, 0,stravel,code,order,travel);
                       
        get(travel); 
        //}
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
       String requete="insert into sewing_production (qty_per_lot,s_traveller,slot,status,type_sew,order_num,lot_stickers) values(?,?,?,0,'ps',?,?)";

            
                //int A=alpha.
                String code=travel;
                if(lot<10)
                    code+="000"+(lot);
                else
                    code+="00"+(lot);
                
                    
                        conn.Update(requete, 0, 0,stravel,code,order,travel);
                       
        get(travel); 
//        String requete="insert into POST_SECOND (QTY,s_traveller,STICKERS,status,type_sew,order_num,lot_stickers) values(?,?,?,0,'ps',?,?)";
//                    
//                String code=travel;
//                if(lot<10)
//                    code+="000"+(lot);
//                else
//                    code+="00"+(lot);
//                
//                System.err.println(code);
//                
//                        conn.Update(requete, 0, 0,stravel,code,order,travel);
//                         
//        //}
//        get(travel);
    }//GEN-LAST:event_jButton6ActionPerformed

    private Object[] value(JTable a,int ligne){
        Object[] ob=new Object[a.getColumnCount()];
        for(int i=0;i<a.getColumnCount();i++){
           ob[i]=a.getValueAt(ligne,i);
        }
        return ob;
    }
    
    private void get(String code){
        //code=code.substring(0,8);
        //tbm.setRowCount(0);
        System.out.println(code);
        data=null;
        order=null;
        travel=null;
        lot=1;
        stravel=null;
        sewn=0;
        String requete="select * from sewing_production where lot_stickers=?";
        ResultSet rs=conn.select(requete, code);
        try {
            rs.last();
        data=new Object[rs.getRow()][9];
        rs.beforeFirst();
        int i=0;
            while(rs.next()){
               data[i][0]=rs.getInt("sew_id");
               data[i][2]=rs.getString("s_traveller");
               data[i][1]=rs.getString("order_num");
               data[i][3]=rs.getString("slot");
               data[i][4]=rs.getInt("qty_per_lot");
                data[i][5]=rs.getString("type_sew");
               data[i][6]=rs.getInt("STATUS")==1?"Scanned":rs.getInt("STATUS")==2?"Invalid":"empty";
               data[i][7]=rs.getInt("qty_per_lot");
               data[i][8]=rs.getBoolean("blank");
               order=rs.getString("order_num");
               travel=rs.getString("lot_stickers");
               stravel=rs.getString("s_traveller");
               i++;
               lot++;
               sewn+=rs.getInt("STATUS")==1?rs.getInt("qty_per_lot"):0;
                }
            
               qty=entry(code);
            //lot=i;
                //((DefaultTableModel)grid_data.getModel())
        } catch (SQLException ex) {
            Logger.getLogger(Heat_pad.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(data!=null && data.length!=0){
        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            data,
                new String [] {
                "id", "Order Number", "SEWING TRAVELLER", "STICKER", "LOT", "TYPE", "STATUS","values","IS BLANK"
            }
            ){
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                //if(Principal_iw.can_edit==true){
                     if(data[rowIndex][6].equals("Invalid") || data[rowIndex][8].equals(false))
                            return false;
                    return true;
                
                //}
                //return false;
            }
            });
        if (grid_data.getColumnModel().getColumnCount() > 0) {
            grid_data.getColumnModel().getColumn(0).setMinWidth(0);
            grid_data.getColumnModel().getColumn(0).setMaxWidth(0);
            grid_data.getColumnModel().getColumn(4).setMinWidth(100);
            grid_data.getColumnModel().getColumn(4).setMaxWidth(100);
            grid_data.getColumnModel().getColumn(7).setMinWidth(0);
            grid_data.getColumnModel().getColumn(7).setMaxWidth(0);
        }
        scrollpane.setViewportView(grid_data);
        jButton1.setEnabled(true);
        }else
            init();
    }
    
    private void init(){
        scrollpane.setViewportView(empty_label);
        jButton1.setEnabled(false);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empty_label;
    private javax.swing.JTable grid_data;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane scrollpane;
    private javax.swing.JMenuItem unscan;
    // End of variables declaration//GEN-END:variables

    private int entry(String travel){
        String requete="select qty from sew_start where stickers=?";
        ResultSet rs=conn.select(requete,travel);
        int id=0;
        try {
            while(rs.next()){
                id=rs.getInt("qty");
                 }   
        } catch (SQLException ex) {
            Logger.getLogger(Sewing_prod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    private int balance(String trave,int val){
        return entry(trave)-val;
    }
}
