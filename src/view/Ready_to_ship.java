/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
public class Ready_to_ship extends javax.swing.JInternalFrame {
private ConnectionDb conn = ConnectionDb.instance();
private DefaultTableModel tbm,tbm1;
private String Erreur="";
private JFileChooser file;
    /**
     * Creates new form packing
     */
    public Ready_to_ship() {
        initComponents();
        
        file=new JFileChooser("C:/",FileSystemView.getFileSystemView());
        file.setDialogTitle("enregistre le fichier");
        file.setFileFilter(new FileNameExtensionFilter("Workbook excel","xlsx","xls"));
        tbm1 = (DefaultTableModel) Log.getModel();
        tbm1.setRowCount(0);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Log = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Packed");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setText("Scan stickers");

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/export.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 290, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        Log.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "LPN", "STATUS", "MESSAGE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Log);
        if (Log.getColumnModel().getColumnCount() > 0) {
            Log.getColumnModel().getColumn(0).setMinWidth(200);
            Log.getColumnModel().getColumn(0).setMaxWidth(200);
            Log.getColumnModel().getColumn(1).setMinWidth(100);
            Log.getColumnModel().getColumn(1).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 86, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            System.out.println(evt.getKeyCode()==10);
            if(!jTextField1.getText().trim().isEmpty())
                act();
        }
        
    }//GEN-LAST:event_jTextField1KeyReleased

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
                }catch(NullPointerException e){
                    
                }
            }
                
        }
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        int returnAct=file.showSaveDialog(this);
        if(returnAct==JFileChooser.APPROVE_OPTION){
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("log");

            //Create some data to build the pivot table on
            setCellData(sheet,Log);

            FileOutputStream fileOut;
            try {
                String name=file.getSelectedFile().getAbsolutePath();
                if(!name.endsWith(".xlsx"))
                name=file.getSelectedFile().getAbsolutePath()+".xlsx";
                System.out.println(name);
                fileOut = new FileOutputStream(name);
                wb.write(fileOut);
                fileOut.close();
                wb.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "File saved with success");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void act(){
           
                String text=jTextField1.getText().trim();
                T ac=new T(text,text);
                Thread t=new Thread(ac);
                t.start();
                
            jTextField1.setText("");
        
    }
    private boolean alreadyscan(String cr){
        String requete="select count(lpn) n from ready_to_ship where lpn=? or STICKERS=?";
        ResultSet rs=conn.select(requete,cr,cr);
        boolean check=false;
        try {
            while(rs.next()){
                check=rs.getBoolean("n");
            }
        } catch (SQLException ex) {
            Logger.getLogger(packing.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return check;
    }
    
    
    private Set<Object[]> ready(String cr){
        String requete1="select ordnum,stats,ponum,sku,coldsp,size,lpn,box_stickers,qty,status_lpn,last_qty,status from lpn_in_batch"
                + " where  BOX_STICKERS=? or lpn=?";
        ResultSet rs=conn.select(requete1, cr,cr);
        Set<Object[]> val=new HashSet<>();
        int qtyconfirm=0;
        int qty=0;
        String po="",sku="";
        String status="";
    
    try {
        while(rs.next()){
            Object[] ob=new Object[10];
            String order=rs.getString("ordnum");
            String box_stickers=rs.getString("box_stickers");
            String lpn=rs.getString("lpn");
            sku=rs.getString("sku");
            po=rs.getString("ponum");
            qty=rs.getInt("qty");
            status=rs.getString("stats").trim();
            qtyconfirm=rs.getInt("last_qty");
            
                    ob[0]=qty;
                    ob[1]=qtyconfirm;
                    ob[2]=po;
                    ob[3]=sku;
                    ob[4]=status;
                    ob[5]=order;
                    ob[6]=box_stickers;
                    ob[7]=lpn;
                    ob[8]=rs.getString("status_lpn").trim();
                    ob[9]=rs.getInt("status");
                    val.add(ob);
            }
    } catch (SQLException ex) {
        Logger.getLogger(packing.class.getName()).log(Level.SEVERE, null, ex);
    }
       return val; 
    }
    private boolean canSave(Set<Object[]> val){
        Erreur="";
        if(val.isEmpty()){
            //JOptionPane.showMessageDialog(this, "this sticker is invalid", "Bad error", JOptionPane.ERROR_MESSAGE);
            Erreur="this sticker or lpn is invalid";
            return false;
        }
        
        for(Object[] ob:val){
            if(!ob[8].toString().equalsIgnoreCase("pass")){
                Erreur+="the lpn is not pass yet";
                return false;
            }
            if(Integer.parseInt(ob[9].toString())==4){
                Erreur+="the lpn is already in the warehouse";
                return false;
            }
            if(ob[4].equals("5"))
                {
                Erreur+="the po:"+ob[2].toString().trim()+" and the sku:"+ob[3].toString().trim()+" is already closed \n";
                return false;
            }
        }
      if(!Erreur.isEmpty()){
            JOptionPane.showMessageDialog(this, Erreur, "Bad error", JOptionPane.ERROR_MESSAGE); 
           return false;
      }
    
        return true;
    }

    private boolean exists(String lpn){
        String requete ="SELECT count(*) n from lpn_scan where lpnScan=? or BOX_STICKERS=?";
        ResultSet rs=conn.select(requete,lpn,lpn);
    try {
        while(rs.next())
        return rs.getInt("n")>0;
    } catch (SQLException ex) {
        Logger.getLogger(packing_mix.class.getName()).log(Level.SEVERE, null, ex);
    }
       return false; 
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Log;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
class T implements Runnable{

    private String stickers,lpn;
    private int lineSelected;
    
    public T(String stickers,String pack){
        this.stickers=stickers;
        lpn=pack;
        tbm1.addRow(new Object[]{lpn,"Pending","waiting"});
        lineSelected=tbm1.getRowCount()-1;
    }
        @Override
        public void run() {
            tbm1.setValueAt("Running", lineSelected, 1);
            System.out.println(lpn+" exist:"+exists(lpn));
            if(exists(lpn)){
           if(!alreadyscan(lpn)){
            if(canSave(ready(lpn))){
                conn.savecst("{call proc_ready(?,?,?)}",lpn,stickers,Principal.user_id);
                tbm1.setValueAt("Success", lineSelected, 2);
                tbm1.setValueAt("Ok", lineSelected, 1);
            }else{
                tbm1.setValueAt(Erreur, lineSelected, 2);
               tbm1.setValueAt("Fail", lineSelected, 1);
               //Log.getColumnModel().getColumn();
            }
            }else{
               tbm1.setValueAt("this stickers is already scanned", lineSelected, 2);
               tbm1.setValueAt("Fail", lineSelected, 1);
            //JOptionPane.showMessageDialog(packing_mix.this, "this stickers is already scanned", "error", JOptionPane.ERROR_MESSAGE);
            }
            }
            else{
                tbm1.setValueAt("this lpn is not exist", lineSelected, 2);
               tbm1.setValueAt("Fail", lineSelected, 1);
        }
        }
        
    
}
}

