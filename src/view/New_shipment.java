/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Padovano
 */
public class New_shipment extends javax.swing.JInternalFrame {

    private ConnectionDb conn; 
    private Map<String,Object[]>listData;
    private DefaultTableModel tbm,tbm1;
    private Set<String> listLpn;
    private int boxes,pieces;
    private JFileChooser file;
    private Task task;
    
    private class Task extends SwingWorker<Integer,Object>{

        private AbstractTableModel tablemodel;
        private List<Object[]> donnees;
        long total;

        public AbstractTableModel getTablemodel() {
            return tablemodel;
        }

        public void setTablemodel(AbstractTableModel tablemodel) {
            this.tablemodel = tablemodel;
        }

        public List<Object[]> getDonnees() {
            return donnees;
        }

        public void setDonnees(List<Object[]> donnees) {
            this.donnees = donnees;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public Task() {
            System.out.println("new task");
        }
        
        public Task(AbstractTableModel tm,long total,List<Object[]> data){
            tablemodel=tm;
            donnees=data;
            this.total=total;
        }
        @Override
        protected Integer doInBackground() {
            int i=0;
            progress_bar.setIndeterminate(false);
            progress_bar.setString("0%");
            System.out.println("size="+donnees.size());
            
                String ship_no=donnees.get(0)[1].toString().trim();
                int newship=0,ship_id=0;
                ship_id=shipmentInfos(ship_no, donnees.get(0)[2].toString().trim());
            for(Object[] ob:donnees ){
                System.out.println("lpn no:"+ob[0]);
                if(!ship_no.equals(ob[1].toString().trim())){
                ship_id=shipmentInfos(ob[1].toString(), ob[2].toString());
                
                    ship_no=ob[1].toString().trim();
                }
                    if(ship_id!=newship){
                        newship=ship_id;
                        initialized(newship);
                        choose_ship.setSelectedItem(ship_no);
                    }
                    jTextField1.setText(ob[0].toString());
                    
                    
                    set(ob[0].toString().trim());
                //publish(100*i/total);
                setProgress((int)Math.ceil(100*i/total));
                i++;
                
                   
                System.out.println("lpn no:"+ob[0]);
            }
                    
            return i;
        }
        
        @Override
        protected void done() {
            super.done(); 
            System.err.println("isCanceled"+this.isCancelled());
            System.err.println("state:"+this.getState().toString());
            progress_bar.setValue(100);
            progress_bar.setForeground(Color.GREEN);
            progress_bar.setString("Done");
            
        }
        
        
    }
    private int shipmentInfos(String shipment_no,String cont){
        ResultSet rs=conn.select("select id from shipments where shipment_number=?", shipment_no);
            int ship_id=0;
            try {
                while(rs.next())
                    ship_id=rs.getInt("id");
            } catch (SQLException ex) {
                Logger.getLogger(New_shipment.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(ship_id==0){
                if(conn.Update("insert into shipments(shipment_number,container_number,typeShipment,customer) "
                                + "values (?,?,?,?)", 1, shipment_no,cont,"ALL","GBG")){
                    ship_id=conn.getLast();
                    //JOptionPane.showMessageDialog(New_shipment.this, "Saved with success");
                }else{
                    JOptionPane.showMessageDialog(New_shipment.this, conn.getErreur());
                }
            }
            System.out.println("ship:"+ship_id);
            return ship_id;
    }
    /**
     * Creates new form New_shipment
     */
    public New_shipment() {
        initComponents();
        init();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void init(){
        conn = ConnectionDb.instance();
        file=new JFileChooser("C:/",FileSystemView.getFileSystemView());
        listData=new HashMap<>();
        choose_ship.setModel(new javax.swing.DefaultComboBoxModel(loadCombo()));
        tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setRowCount(0);
        tbm1 = (DefaultTableModel) grid_error.getModel();
        tbm1.setRowCount(0);
        //tbm.f
        boxes=0;
        pieces=0;
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        choose_ship = new javax.swing.JComboBox();
        customer_lab = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lab_ship = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();
        lab_box_aug = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lab_piece_aug = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_cont = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_type = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_date = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        grid_error = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        progress_bar = new javax.swing.JProgressBar();
        infos_loading = new javax.swing.JLabel();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Scan Lpn:");

        jTextField1.setEnabled(false);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel2.setText("Select Shipment:");

        choose_ship.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        choose_ship.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choose_shipItemStateChanged(evt);
            }
        });
        choose_ship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choose_shipActionPerformed(evt);
            }
        });

        customer_lab.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        customer_lab.setText("jLabel1");
        customer_lab.setVisible(false);

        jButton1.setText("Load gbg shipment");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(choose_ship, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(199, 199, 199)
                .addComponent(customer_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(choose_ship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customer_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jTextField1))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );

        lab_ship.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lab_ship.setText("jLabel2");

        jLabel11.setText("Shipment #:");

        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "LPN", "PO", "Style", "COLOR CODE", "COLOR", "SIZE", "QTY", "MIX?", "LPN_MIX"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(grid_data);

        lab_box_aug.setText("jLabel13");

        jLabel18.setText("PIECES:");

        jLabel16.setText("BOXES:");

        lab_piece_aug.setText("jLabel15");

        jLabel4.setText("Container #:");

        txt_cont.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_cont.setText("jLabel4");

        jLabel6.setText("Type:");

        txt_type.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_type.setText("jLabel6");

        jLabel8.setText("Date:");

        txt_date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_date.setText("jLabel8");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_ship, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cont, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_type, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_date, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lab_box_aug)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lab_piece_aug)
                .addGap(36, 36, 36))
            .addComponent(jScrollPane4)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(lab_box_aug)
                        .addComponent(jLabel18)
                        .addComponent(lab_piece_aug))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txt_date))
                    .addComponent(txt_cont, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lab_ship, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 98, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setTopComponent(jPanel1);

        grid_error.setForeground(new java.awt.Color(255, 0, 0));
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

        infos_loading.setText("jLabel1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(infos_loading, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(progress_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progress_bar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infos_loading, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void choose_shipItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choose_shipItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_choose_shipItemStateChanged

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            System.out.println(evt.getKeyCode()==10);
            if(!jTextField1.getText().trim().isEmpty())
              set(jTextField1.getText().trim());
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void choose_shipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choose_shipActionPerformed
        // TODO add your handling code here:
        jTextField1.setEnabled(false);
        if(choose_ship.getSelectedIndex()>0){
            jTextField1.setEnabled(true);
            
            tbm.setRowCount(0);
            //tbm1.setRowCount(0);
            System.out.println("size:"+listData.get(choose_ship.getSelectedItem().toString())[0]);
            initialized(Integer.parseInt(listData.get(choose_ship.getSelectedItem().toString())[0].toString()));
            fillTable(Integer.parseInt(listData.get(choose_ship.getSelectedItem().toString())[0].toString()),customer_lab.getText());
        }else{
            JOptionPane.showMessageDialog(this, "please select a shipment");
            customer_lab.setVisible(false);
        }
    }//GEN-LAST:event_choose_shipActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        file.setDialogTitle("Open file");
        file.setMultiSelectionEnabled(true);
        long nline=0;
        file.setFileFilter(new FileNameExtensionFilter("text file","txt","TXT"));
        List<Object[]> lpn=new ArrayList<>();
        String cust="GBG";
        int returnAct=file.showOpenDialog(this);
        if(returnAct==JFileChooser.APPROVE_OPTION){
            progress_bar.setIndeterminate(true);
            progress_bar.setString("loading");
            progress_bar.setStringPainted(true);
            try {
                String lpn_no="",shipment_no="",billing="",container="",date="";
                File[] files=file.getSelectedFiles();
                InputStream fileIn;
                int n=0,ship_id=0;
                for(int j=0;j<files.length;j++){
                boolean firstline=true;
                fileIn = new FileInputStream(files[j]);
                InputStreamReader rea=new FileReader(files[j]);
                BufferedReader reader=new BufferedReader(rea);
                
                String line="";
                Object infos[]=new Object[11];
                
                 while((line=reader.readLine())!=null){
                         if(firstline){
                             firstline=false;
                             int i=0;
                             while(!line.trim().isEmpty()){
                                 line=line.trim();
                                 try{
                                 infos[i]=line.substring(0, line.indexOf("  "));
                                 line=line.substring(line.indexOf("  "));
                                 }catch(StringIndexOutOfBoundsException e){
                                        infos[i]=line;
                                        line="";
                                 }
                                 System.out.println(infos[i]);
                                 System.out.println(line);
                                 i++;
                             }
                             date=infos[0].toString();
                             container=infos[2].toString();
                             shipment_no=infos[3].toString();
                             String typ="ALL";
                             cust="GBG";
                         }else{
                             lpn_no=line.trim().substring(3).trim();
                             Object[] lineinfos=new Object[4];
                             lineinfos[0]=lpn_no;
                             lineinfos[1]=shipment_no;
                             lineinfos[2]=container;
                             lineinfos[3]=date;
                             lpn.add(lineinfos);
                             nline++;
                         }
                 }
                }
            }catch(IOException e){
                
            }
            System.out.println("nline:"+lpn.size());
            
             task=new Task();
            task.addPropertyChangeListener(new PropertyChangeListener() {

                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    if(evt.getPropertyName().equals("progress")){
                        progress_bar.setValue((Integer)evt.getNewValue());
                        progress_bar.setString(progress_bar.getValue()+"%");
                    }
                }
            });
             task.setDonnees(lpn);
             task.setTablemodel(tbm);
             task.setTotal(nline);
             task.execute();

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void initialized(int id){
        String requete="select * from shipments where id=?";
        ResultSet rs=conn.select(requete, id);
        try {
            while(rs.next()){
                lab_ship.setText(rs.getString("shipment_number"));
                txt_cont.setText(rs.getString("container_number"));
                txt_date.setText(rs.getDate("created").toString());
                txt_type.setText(rs.getString("typeShipment"));
                customer_lab.setText(rs.getString("customer"));
                customer_lab.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(New_shipment.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    private void fillTable(int id,String brand){
        listLpn=new HashSet<>();
        boxes=0;
        pieces=0;
        String requete="select * from lpn where shipment_id=? and brand=?";
        ResultSet rs=conn.select(requete, id,brand);
        try {
            while(rs.next()){
                tbm.addRow(new Object[]{rs.getString("lpn"),rs.getString("ponum"),rs.getString("style"),
                rs.getString("sku").substring(rs.getString("sku").indexOf(".")+1, rs.getString("sku").lastIndexOf(".")),
                rs.getString("coldsp"),rs.getString("size"),
                rs.getInt("qty"),(rs.getInt("boxmix_id")!=0)?true:false,rs.getString("lpn_mix")});
                if(rs.getInt("boxmix_id")==0)
                    listLpn.add(rs.getString("lpn"));
                else
                    listLpn.add(rs.getString("lpn_mix"));
                //boxes++;
                pieces+=rs.getInt("qty");
            }
        } catch (SQLException ex) {
            Logger.getLogger(New_shipment.class.getName()).log(Level.SEVERE, null, ex);
        }
        boxes=listLpn.size();
        lab_box_aug.setText(String.valueOf(boxes));
        lab_piece_aug.setText(String.valueOf(pieces));
    }
    
    private Object[] loadCombo(){
        String requete="select * from shipments where status=1";
        ResultSet rs=conn.select(requete);
        Object[] data=null;
        try {
            rs.last();
            data=new Object[rs.getRow()+1];
            data[0]="--Select shipment#--";
            rs.beforeFirst();
            int i=1;
            while(rs.next()){
                data[i]=rs.getString("shipment_number");
                listData.put(data[i].toString(), new Object[]{
                    rs.getInt("id"),data[i].toString(),rs.getDate("created"),rs.getString("container_number"),
                    rs.getString("typeShipment"),rs.getString("customer")
                });
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(New_shipment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    private boolean canScan(Object[][] ob,String lpn){
        String ErrorLpn=null;
        //if(ob!=null){
        try{
            if(!listLpn.contains(lpn)){
                Object[][] data=ob;
                int stat=Integer.parseInt(data[0][1].toString());
                if(data[0][3].toString().equals(customer_lab.getText())){
                if(Integer.parseInt(data[0][2].toString())!=0){
                    System.out.println(data[0][2]);
                    ErrorLpn="already shipped.";
                    ////JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                        //+ "- already shipped.", "error scanning", JOptionPane.ERROR_MESSAGE);
                }else
                return true;
                }else
            {
                //JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                    //+ "- you can't scan a "+data[3].toString().trim()+" lpn in a "+customer_lab.getText().trim()+".", "error scanning", JOptionPane.ERROR_MESSAGE);
             ErrorLpn="invalid lpn.";
            }
            }else{
             //JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                    //+ "- already scan this shipment.", "error scanning", JOptionPane.ERROR_MESSAGE);
             ErrorLpn="Duplicate lpn.";
            }
        }catch(NullPointerException e){
            //JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                    //+ "- not packed yet from wip.", "error scanning", JOptionPane.ERROR_MESSAGE);
            ErrorLpn="Not Valid by WIP.";
            e.printStackTrace();
        }
        //}else{
           // ErrorLpn="invalid lpn.";
        //}
            if(ErrorLpn!=null){
            tbm1.addRow(new Object[]{lpn,ErrorLpn});
            System.err.println(lpn+" : "+ErrorLpn);
            }
        return false;
    }
    
    private boolean canScanGBG(Object[][] ob,String lpn){
        String ErrorLpn=null;
        //if(ob!=null){
        try{
            if(!listLpn.contains(lpn)){
                Object[][] data=ob;
                int stat=Integer.parseInt(data[0][1].toString());
                if(data[0][3].toString().equals(customer_lab.getText())){
                if(Integer.parseInt(data[0][2].toString())!=0){
                    System.out.println(data[0][2]);
                    ErrorLpn="already shipped.";
                    //JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                        //+ "- already shipped.", "error scanning", JOptionPane.ERROR_MESSAGE);
                }
                return true;
                }else
            {
                //JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                    //+ "- you can't scan a "+data[3].toString().trim()+" lpn in a "+customer_lab.getText().trim()+".", "error scanning", JOptionPane.ERROR_MESSAGE);
             ErrorLpn="invalid lpn.";
            }
            }else{
             //JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                   // + "- already scan this shipment.", "error scanning", JOptionPane.ERROR_MESSAGE);
             ErrorLpn="Duplicate lpn.";
            }
        }catch(NullPointerException e){
            //JOptionPane.showMessageDialog(this, "This Lpn cant be scan \n\t "
                    //+ "- not packed yet from wip.", "error scanning", JOptionPane.ERROR_MESSAGE);
            ErrorLpn="Not Valid by WIP.";
        }
        //}else{
            //ErrorLpn="invalid lpn.";
        //}
            if(ErrorLpn!=null){
            tbm1.addRow(new Object[]{lpn,ErrorLpn});
            System.err.println(lpn+" : "+ErrorLpn);
            }
        return false;
    }
    
    private boolean set(String lpn){
        Object[][] data=getLpn(lpn,customer_lab.getText());
        int idlpn=0,idlpn_mix=0,idbox=0;
        int shipment_id=Integer.parseInt(listData.get(choose_ship.getSelectedItem().toString())[0].toString());
        boolean istrue=false,ismix=false;
        String lpn_mix="";
        
        
        if(canScan(data,lpn)){
            
            if((Integer)data[0][12]!=0){
                ismix=true;
                idlpn_mix=(Integer)data[0][12];
                lpn_mix=data[0][11].toString();
                listLpn.add(lpn_mix);
                String requete="update mix_box set scanned=1,shipment_id=? where id=?";
                conn.Update(requete, 0, shipment_id,idlpn_mix);
            }else
                listLpn.add(lpn);
            for(Object[] o:data){
            idlpn=Integer.parseInt(o[0].toString());
            String requete="update box_contain set status=3,scan_date=?,shipment_id=? where id=?";
            tbm.addRow(new Object[]{o[8],o[4],o[5],o[6].toString().substring(o[6].toString().indexOf(".")+1, o[6].toString().lastIndexOf(".")),o[7],o[9],o[10],ismix,lpn_mix});
            
            istrue=conn.Update(requete, 0, new Date(),shipment_id,idlpn);
            //boxes++;
            pieces+=Integer.parseInt(o[10].toString());
            }
            boxes++;
            lab_box_aug.setText(String.valueOf(boxes));
            lab_piece_aug.setText(String.valueOf(pieces));
        }
        //String
        return istrue;
    }
    
    private boolean set(String lpn,String customer,int ship){
        Thread t=new Thread(new T(lpn,customer,ship));
        //String
        return true;
    }
    
    private Object[][] getLpn(String lpn,String customer){
        Object[][] data=null;
        String requete="select * from shipproom where lpn_to_scan=? or BOX_STICKERS=?";
        ResultSet rs=conn.select(requete,jTextField1.getText().trim());
        
        
    try {
        rs.last();
        if(rs.getRow()>0){
        data=new Object[rs.getRow()][];
        rs.beforeFirst();
        int i=0;
        while(rs.next()){
            Object[] o=new Object[]{
            rs.getString("id"),rs.getString("status"),rs.getInt("shipment_id"),rs.getString("brand"),rs.getString("ponum"),
            rs.getString("style"),rs.getString("sku"),rs.getString("coldsp"),rs.getString("lpn"),rs.getString("size"),
            rs.getInt("qty"),rs.getString("lpn_mix"),rs.getInt("boxmix_id"),rs.getInt("box_id")};
            data[i]=o;
            i++;
        }
        }
    } catch (SQLException ex) {
        Logger.getLogger(New_shipment.class.getName()).log(Level.SEVERE, null, ex);
    }
    return data;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox choose_ship;
    private javax.swing.JLabel customer_lab;
    private javax.swing.JTable grid_data;
    private javax.swing.JTable grid_error;
    private javax.swing.JLabel infos_loading;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lab_box_aug;
    private javax.swing.JLabel lab_piece_aug;
    private javax.swing.JLabel lab_ship;
    private javax.swing.JProgressBar progress_bar;
    private javax.swing.JLabel txt_cont;
    private javax.swing.JLabel txt_date;
    private javax.swing.JLabel txt_type;
    // End of variables declaration//GEN-END:variables
    
    class T implements Runnable{

    private String lpn;
    private String cus;
    private int ship;
    
    public T(String text,String cust,int ship_id){
        this.lpn=text;
        cus=cust;
        ship=ship_id;
    }
        @Override
        public void run() {
            Object[][] data=getLpn(lpn,cus);
            int idlpn=0,idlpn_mix=0;
            int shipment_id=ship;
            boolean istrue=false,ismix=false;
            String lpn_mix="";
            if(canScanGBG(data,lpn)){
            
            if((Integer)data[0][12]!=0){
                ismix=true;
                idlpn_mix=(Integer)data[0][12];
                lpn_mix=data[0][11].toString();
                listLpn.add(lpn_mix);
                String requete="update mix_box set scanned=1,shipment_id=? where id=?";
                conn.Update(requete, 0, shipment_id,idlpn_mix);
            }else
                listLpn.add(lpn);
            for(Object[] o:data){
            idlpn=Integer.parseInt(o[0].toString());
            String requete="update box_contain set status=3,scan_date=?,shipment_id=? where id=?";
            tbm.addRow(new Object[]{o[8],o[4],o[5],o[6].toString().substring(o[6].toString().indexOf(".")+1, o[6].toString().lastIndexOf(".")),o[7],o[9],o[10],ismix,lpn_mix});
            
            istrue=conn.Update(requete, 0, new Date(),shipment_id,idlpn);
            //boxes++;
            pieces+=Integer.parseInt(o[10].toString());
            }
            boxes++;
            lab_box_aug.setText(String.valueOf(boxes));
            lab_piece_aug.setText(String.valueOf(pieces));
        }
        }
    
}
}
