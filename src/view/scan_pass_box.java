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
public class scan_pass_box extends javax.swing.JInternalFrame {
private ConnectionDb conn = ConnectionDb.instance();
private Map<String,Integer>list_second;
private DefaultTableModel tbm,tbm1;
private String Erreur="";
private JFileChooser file;
    /**
     * Creates new form packing
     */
    public scan_pass_box() {
        initComponents();
        
        file=new JFileChooser("C:/",FileSystemView.getFileSystemView());
        file.setDialogTitle("enregistre le fichier");
        file.setFileFilter(new FileNameExtensionFilter("Workbook excel","xlsx","xls"));
        tbm = (DefaultTableModel) GRID_DATA.getModel();
        tbm1 = (DefaultTableModel) Log.getModel();
        mostrar();
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
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GRID_DATA = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Log = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Box Approved / ready to ship");

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

        jLabel1.setText("Scan lpn");

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
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        GRID_DATA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Customer", "PO", "SKU", "PACKED", "LPN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(GRID_DATA);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jSplitPane1.setLeftComponent(jPanel2);

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
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSplitPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 682, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 117, Short.MAX_VALUE)
                    .addComponent(jSplitPane1)))
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
            jTextField1.setText("");
        }
        
    }//GEN-LAST:event_jTextField1KeyReleased

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

    private void mostrar(){
    tbm.setRowCount(0);
    tbm1.setRowCount(0);
    String requete="select * from mix";
    System.out.println(requete);
    ResultSet rs=conn.select(requete);
        try {
            while(rs.next()){
             
                   Object[] data=new Object[5];
                   data[0]=rs.getString("brand");
                   data[1]=rs.getString("po");
                   data[2]="MIX";
                   data[3]=rs.getInt("qty");
                   data[4]=rs.getString("lpn_mix");
                  
                   tbm.addRow(data);
            }   } catch (SQLException ex) {
            Logger.getLogger(Sewing_prod.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
    private void act(){
                String text=jTextField1.getText().trim();
                T ac=new T(text);
                Thread t=new Thread(ac);
                t.start();
            //}
//        }.start();
        
    }
    private boolean alreadyPacked(String cr){
        String requete="select count(lpnScan) n from packed_lpn where lpnScan=? or BOX_STICKERS=?";
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
    
    
    private Set<Object[]> atpacking(String cr){
        String requete1="select work_order,p.sku,state,p.po,p.style,packed,sewn,at_match,at_press,at_wash,lpnScan,box_stickers,qty,second_matchbook,second_press,second_wash,second_ps from process_all p inner join lpn_scan l on(work_order=ORDNUM_147)"
                + " where  BOX_STICKERS=? or lpnscan=?";
        ResultSet rs=conn.select(requete1, cr,cr);
        Set<Object[]> val=new HashSet<>();
        int qtypack=0;
        int packed=0,qty=0;
        String po="",sku="";
    
    try {
        while(rs.next()){
            Object[] ob=new Object[4];
            String order=rs.getString("work_order");
            sku=rs.getString("sku");
            po=rs.getString("po");
            qty=rs.getInt("qty");
            packed=rs.getInt("packed");
            qtypack=rs.getInt("sewn")-packed;
            String style=rs.getString("style").trim();
            boolean match=step(style,"MATCHBOOK");
            boolean wash=step(style,"WASHING");
            boolean press=step(style,"PRESS");
            int second=0;
            if(!(match||wash||press) ){
                second=rs.getInt("second_ps");
                qtypack=rs.getInt("sewn")-(rs.getInt("packed")+second);
            }
            else{
                second=rs.getInt("second_matchbook")+rs.getInt("second_press")+rs.getInt("second_wash");
                qtypack=(match?rs.getInt("at_match"):(press?rs.getInt("at_press"):rs.getInt("at_wash")))-(rs.getInt("packed")+second);
            }
            System.out.println("pack qty:"+qtypack+"\t vs qty:"+qty);
                    //ob[0]=sticker;
                    ob[0]=qty;
                    ob[1]=qtypack;
                    ob[2]=po;
                    ob[3]=sku;
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
            Erreur="this sticker is invalid";
            return false;
        }
        
        for(Object[] ob:val){
            if((Integer)ob[0]>(Integer)ob[1]){
                Erreur+="the po:"+ob[2].toString().trim()+" and the sku:"+ob[3].toString().trim()+" can't scan \n";
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
        rs.first();
        return rs.getInt("n") != 0;
    } catch (SQLException ex) {
        Logger.getLogger(packing_mix.class.getName()).log(Level.SEVERE, null, ex);
    }
       return false; 
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GRID_DATA;
    private javax.swing.JTable Log;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
class T implements Runnable{

    private String text,packtable;
    private int lineSelected;
    
    public T(String text){
        this.text=text;
        tbm1.addRow(new Object[]{text,"Pending","waiting"});
        lineSelected=tbm1.getRowCount()-1;
    }
        @Override
        public void run() {
            tbm1.setValueAt("Running", lineSelected, 1);
            if(exists(text)){
           if(!alreadyPacked(text)){
            if(canSave(atpacking(text))){
                conn.savecst("{call proc_pack(?,?)}",text,packtable);
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

