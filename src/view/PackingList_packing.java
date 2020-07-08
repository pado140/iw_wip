/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Padovano
 */
public class PackingList_packing extends javax.swing.JInternalFrame {

    private final ConnectionDb conn; 
    private DefaultTableModel tbm;
    private final Set<Object[]> datas=new HashSet<>();
    private boolean initiated;
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
private Task task;
    
    private class Task extends SwingWorker<Void,Object>{

        int total,qty_f,qty_s,qty_e;
        String commande;

        public Task(String commande) {
            System.out.println("new task");
            this.commande=commande;
            header.setEnabled(false);
            visible(false);
            progress7.setVisible(true);
            export.setEnabled(false);
            state7.setText("initiating");
        }
        
        @Override
        protected Void doInBackground() {
            int i=0;
            
            progress7.setIndeterminate(false);
            progress7.setString("0%");
            
            state7.setText("loading...");
        tbm.setNumRows(0);
        String requete="select * from packed_lpn";
        ResultSet rs=conn.select(requete);
        try {
            rs.last();
            
            total=rs.getRow();
            rs.beforeFirst();
            while(rs.next()){
                Object[] result=new Object[11];
                result[0]=rs.getDate("scan_date");
                result[1]=rs.getString("PO");
                result[2]=rs.getString("sku");
                result[3]=rs.getString("coldsp");
                result[4]=rs.getString("box_stickers");
                result[5]=rs.getString("lpnScan");
                result[6]=rs.getInt("qty");
                result[7]=rs.getInt("status")==1?"IN BATCH":rs.getInt("status")==2?"PASSED AUDIT":
                        rs.getInt("status")==3?"SHIPPED":rs.getInt("status")==4?"IN SHIPPING":"PACKED";
                result[8]=rs.getString("tablepacking");
                result[9]=rs.getString("module")!=null?rs.getString("module"):"N/A";
                result[10]=rs.getString("BRAND");
                
                i++;
                setProgress((int)Math.ceil(100*i/total));
                datas.add(result);
                tbm.addRow(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PackingList_packing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return null;
        }
        
        @Override
        protected void done() {
            super.done(); 
            System.err.println("isCanceled"+this.isCancelled());
            System.err.println("state:"+this.getState().toString());
            state7.setText("ready");
            progress7.setValue(100);
            progress7.setForeground(Color.GREEN);
            progress7.setString("Done");
            //alerter("reload",data);
            visible(true);
            export.setEnabled(true);
            progress7.setVisible(false);
            initiated=true;
        }
        
        
    }
    /**
     * Creates new form PackingList_packing
     */
    public PackingList_packing() {
        initComponents();
        //datesearch.removePropertyChangeListener(title, datesearch);
        conn = ConnectionDb.instance();
        tbm = (DefaultTableModel) grid_data.getModel();
        inits();
    }
    private void visible(boolean status){
        header.setEnabled(status);
            for(Component c:header.getComponents()){
                c.setEnabled(status);
            }
    }

    private void inits(){
        state7.setText("initiating");
        progress7.setIndeterminate(true);
            progress7.setString("initiating");
            progress7.setStringPainted(true);
        task=new Task("load");
        task.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if(evt.getPropertyName().equals("progress")){
                System.err.println(evt.getPropertyName());
                System.err.println(evt.getNewValue());
                progress7.setValue((Integer)evt.getNewValue());
                progress7.setString(progress7.getValue()+"%");
            }
        });
        task.execute();
    }
    private void refresh(){
        progress7.setIndeterminate(true);
            progress7.setString("initiating");
            task=new Task("load");
        task.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if(evt.getPropertyName().equals("progress")){
                System.err.println(evt.getPropertyName());
                System.err.println(evt.getNewValue());
                progress7.setValue((Integer)evt.getNewValue());
                progress7.setString(progress7.getValue()+"%");
            }
        });
        task.execute();
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
        jPanel3 = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        refresh = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        colorsearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tablesearch = new javax.swing.JTextField();
        posearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        skusearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        datesearch = new com.toedter.calendar.JDateChooser();
        to = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        custSearch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        locateSearch = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lpnsearch = new javax.swing.JTextField();
        export = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        progress7 = new javax.swing.JProgressBar();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel45 = new javax.swing.JLabel();
        state7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jLabel5.setText("TABLE:");

        jLabel6.setText("COLOR:");

        colorsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                colorsearchKeyReleased(evt);
            }
        });

        jLabel1.setText("PO FILTER:");

        tablesearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablesearchKeyReleased(evt);
            }
        });

        posearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                posearchKeyReleased(evt);
            }
        });

        jLabel2.setText("SKU FILTER:");

        skusearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                skusearchKeyReleased(evt);
            }
        });

        jLabel3.setText("date");

        datesearch.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datesearchPropertyChange(evt);
            }
        });

        to.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                toPropertyChange(evt);
            }
        });

        jLabel4.setText("TO");

        jLabel7.setText("CUSTOMER:");

        custSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                custSearchKeyReleased(evt);
            }
        });

        jLabel8.setText("MODULE:");

        locateSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                locateSearchKeyReleased(evt);
            }
        });

        jLabel9.setText("LPN/STICKERS");

        lpnsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lpnsearchKeyReleased(evt);
            }
        });

        export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/export.png"))); // NOI18N
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(skusearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tablesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(datesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(22, 22, 22)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(custSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(refresh))
                    .addComponent(lpnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(headerLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(skusearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(headerLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(colorsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(headerLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tablesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(headerLayout.createSequentialGroup()
                                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(posearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(datesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))))
                            .addGroup(headerLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(custSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(headerLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(locateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(headerLayout.createSequentialGroup()
                                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(refresh))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lpnsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(export)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        grid_data.setAutoCreateRowSorter(true);
        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DATE SCAN", "PO", "SKU", "COLOR", "BOX STICKERS", "LPN", "QTY", "STATUS", "TABLE", "MODULE", "CUSTOMER"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(grid_data);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel45.setText("Status:");

        state7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        state7.setText("Status:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progress7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel45)
                .addGap(29, 29, 29)
                .addComponent(state7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(state7))
            .addComponent(jSeparator8)
            .addComponent(progress7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshActionPerformed(ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_refreshActionPerformed

    private void colorsearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_colorsearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_colorsearchKeyReleased

    private void tablesearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_tablesearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_tablesearchKeyReleased

    private void posearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_posearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_posearchKeyReleased

    private void skusearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_skusearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_skusearchKeyReleased

    private void datesearchPropertyChange(PropertyChangeEvent evt) {//GEN-FIRST:event_datesearchPropertyChange
        // TODO add your handling code here:
        if(initiated){
            try{
                //buscados();
            }catch(NullPointerException e){
            }
        }
    }//GEN-LAST:event_datesearchPropertyChange

    private void toPropertyChange(PropertyChangeEvent evt) {//GEN-FIRST:event_toPropertyChange
        // TODO add your handling code here:
        if(initiated)
        try{
            //buscados();
        }catch(NullPointerException e){
        }
    }//GEN-LAST:event_toPropertyChange

    private void custSearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_custSearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_custSearchKeyReleased

    private void locateSearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_locateSearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_locateSearchKeyReleased

    private void lpnsearchKeyReleased(KeyEvent evt) {//GEN-FIRST:event_lpnsearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_lpnsearchKeyReleased

    private void exportActionPerformed(ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        JFileChooser file=new JFileChooser("C:/",FileSystemView.getFileSystemView());
        file.setDialogTitle("enregistre le fichier");

        file.setFileFilter(new FileNameExtensionFilter("Workbook excel","xlsx","xls"));
        int returnAct=file.showSaveDialog(this);
        if(returnAct==JFileChooser.APPROVE_OPTION){
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Daily production report");

            //Create some data to build the pivot table on
            setCellData(sheet,grid_data);

            FileOutputStream fileOut;

            String name=file.getSelectedFile().getAbsolutePath();
            if(!name.endsWith(".xlsx"))
            name=file.getSelectedFile().getAbsolutePath()+".xlsx";
            try {
                System.out.println(name);
                fileOut = new FileOutputStream(name);
                wb.write(fileOut);
                fileOut.close();
                wb.close();
                //JOptionPane.showMessageDialog(null, "File saved with success");
                int option=JOptionPane.showConfirmDialog(this, "File saved with success! \n"
                    + "Do you want to open it?","confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(option==JOptionPane.YES_OPTION){
                    Desktop dsk=Desktop.getDesktop();
                    dsk.open(new File(name));
                }
            }catch (FileNotFoundException ex) {
                Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_exportActionPerformed

    public void setCellData(XSSFSheet sheet,JTable table){
        Row row10=sheet.createRow(0);
        for(int j=0;j<table.getColumnCount();j++){
                Cell cells=row10.createCell(j);
                cells.setCellValue(table.getColumnName(j));
            }
        for(int i=0;i<table.getRowCount();i++){
            Row rows=sheet.createRow(1+i);
            Cell cellMar=rows.createCell(0);
            for(int j=0;j<table.getColumnCount();j++){
                Cell cells=rows.createCell(j);
                try{
                cells.setCellValue(table.getValueAt(i, j).toString());
                
                    if(table.getValueAt(i, j) instanceof Double)
                cells.setCellValue(Double.parseDouble(table.getValueAt(i, j).toString()));
                    if(table.getValueAt(i, j) instanceof Integer)
                cells.setCellValue(Integer.parseInt(table.getValueAt(i, j).toString()));
                    if(table.getValueAt(i, j) instanceof Date)
                cells.setCellValue((Date)table.getValueAt(i, j));
                
                }catch(NullPointerException e){
                    
                }
                
            }
                
        }
    }
    
    private void buscados(){
        tbm.setRowCount(0);
        String po=posearch.getText().trim().toLowerCase();
        String style=skusearch.getText().trim().toLowerCase();
        String color=colorsearch.getText().trim().toLowerCase();
        String table=tablesearch.getText().trim().toLowerCase();
        String cust=custSearch.getText().trim().toLowerCase();
        String lo=locateSearch.getText().trim().toLowerCase();
        String lpn=lpnsearch.getText().trim().toLowerCase();
        System.out.println("date:"+datesearch.getDate());
        for(Object[] ob:datas){
            if(datesearch.getDate()!=null){
                if(ob[0]!=null){
                    if(to.getDate()!=null){
                        Date d=new Date();
                        Calendar cal=Calendar.getInstance(TimeZone.getDefault(), Locale.CANADA);
                        cal.setTime(datesearch.getDate());
                        cal.add(Calendar.DAY_OF_MONTH, -1);
                        d=cal.getTime();
            if(ob[1].toString().trim().toLowerCase().contains(po)&&
                    ob[2].toString().trim().toLowerCase().contains(style)&&
                    ob[3].toString().trim().toLowerCase().contains(color)&&
                    ob[8].toString().trim().toLowerCase().contains(table)&&
                    ob[10].toString().trim().toLowerCase().contains(cust)&&
                    (ob[4].toString().trim().toLowerCase().contains(lpn)||
                            ob[5].toString().trim().toLowerCase().contains(lpn))&&
                    ob[9].toString().trim().toLowerCase().contains(lo)
                    ){
                try {
                    Date dd=formatter.parse(ob[0].toString());
                    if(dd.after(d)&&dd.before(to.getDate())){
                        tbm.addRow(ob);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(SOABAR_report.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                
            }
                    }else{
                        if(ob[1].toString().trim().toLowerCase().contains(po)&&
                    ob[2].toString().trim().toLowerCase().contains(style)&&
                    ob[3].toString().trim().toLowerCase().contains(color)&&
                    ob[8].toString().trim().toLowerCase().contains(table)&&
                    ob[10].toString().trim().toLowerCase().contains(cust)&&
                    (ob[4].toString().trim().toLowerCase().contains(lpn)||
                            ob[5].toString().trim().toLowerCase().contains(lpn))&&
                    ob[9].toString().trim().toLowerCase().contains(lo)&&
                    ob[0].toString().equals(formatter.format(datesearch.getDate())))
                    
                tbm.addRow(ob);
                    }
                }
            }else{
                if(ob[1].toString().trim().toLowerCase().contains(po)&&
                    ob[2].toString().trim().toLowerCase().contains(style)&&
                    ob[3].toString().trim().toLowerCase().contains(color)&&
                    ob[8].toString().trim().toLowerCase().contains(table)&&
                    ob[10].toString().trim().toLowerCase().contains(cust)&&
                    (ob[4].toString().trim().toLowerCase().contains(lpn)||
                            ob[5].toString().trim().toLowerCase().contains(lpn))&&
                    ob[9].toString().trim().toLowerCase().contains(lo)){
                    
                tbm.addRow(ob);
                }
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField colorsearch;
    private javax.swing.JTextField custSearch;
    private com.toedter.calendar.JDateChooser datesearch;
    private javax.swing.JButton export;
    private javax.swing.JTable grid_data;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField locateSearch;
    private javax.swing.JTextField lpnsearch;
    private javax.swing.JTextField posearch;
    private javax.swing.JProgressBar progress7;
    private javax.swing.JButton refresh;
    private javax.swing.JTextField skusearch;
    private javax.swing.JLabel state7;
    private javax.swing.JTextField tablesearch;
    private com.toedter.calendar.JDateChooser to;
    // End of variables declaration//GEN-END:variables

    private void fillTable() {
        
    }
}
