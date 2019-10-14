/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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
public class post_sewing_scan extends javax.swing.JInternalFrame {
    private ConnectionDb conn = ConnectionDb.instance();
    private DefaultTableModel tbm;

    /**
     * Creates new form post_sewing_scan
     */
    public post_sewing_scan() {
        initComponents();
        tbm=(DefaultTableModel)grid_sew.getModel();
        buttonGroup1.add(jRadioButton1);
        buttonGroup1.add(jRadioButton2);
        buttonGroup1.add(jRadioButton3);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        export = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_sew = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();

        setClosable(true);

        export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/export.png"))); // NOI18N
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        grid_sew.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "PO NUM", "STYLE", "COLOR CODE", "COLOR", "SIZE", "QTY", "TICKET", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(grid_sew);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jTextField1.setFocusCycleRoot(true);

        jLabel1.setText("Sewing Traveler");

        jRadioButton1.setText("wash");

        jRadioButton2.setText("press");

        jRadioButton3.setText("matchbook");

        jLabel2.setText("Quantity:");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jRadioButton1)
                        .addGap(71, 71, 71)
                        .addComponent(jRadioButton2)
                        .addGap(104, 104, 104)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 389, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addGap(10, 10, 10)
                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton3)
                            .addComponent(jButton1)))
                    .addComponent(export))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void init(){
        
        jTextField1.setText("");
        buttonGroup1.clearSelection();
        jSpinner1.setValue(0);
        mostrar();
    }
    
    
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
//        if(evt.getKeyCode()==10){
//            System.out.println(evt.getKeyCode()==10);
//            if(jTextField1.getText().trim().isEmpty()){
//                return;
//            }
//            //get(jTextField1.getText().trim());
//            //mostrar();
//            //load();
//            jTextField1.setText("");
//        }

    }//GEN-LAST:event_jTextField1KeyReleased

    private String getSelectedRadio(){
        Enumeration radio=buttonGroup1.getElements();
        while(radio.hasMoreElements()){
            JRadioButton but=(JRadioButton)radio.nextElement();
            if(but.isSelected())
                return but.getText();
        }
        return null;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String lot=jTextField1.getText().trim();
        String type=getSelectedRadio();
        int qty=(Integer)jSpinner1.getValue();
        if(lot.isEmpty()){
            JOptionPane.showMessageDialog(this, "please sewing traveler field must not be empty","information missing",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(qty==0){
            JOptionPane.showMessageDialog(this, "the quantity must up to 0","information missing",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!exist(type,lot)){
            if(exec(type,lot,qty))
                JOptionPane.showMessageDialog(this, "save successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        init();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        JFileChooser file=new JFileChooser("C:/",FileSystemView.getFileSystemView());
        file.setDialogTitle("enregistre le fichier");

        file.setFileFilter(new FileNameExtensionFilter("Workbook excel","xlsx","xls"));
        int returnAct=file.showSaveDialog(this);
        if(returnAct==JFileChooser.APPROVE_OPTION){
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Daily production report");

            //Create some data to build the pivot table on
            setCellData(sheet,grid_sew);

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
        //int colexcept=table.getColumnModel().getColumnIndex("sticker");
        Row row10=sheet.createRow(0);
        for(int j=0;j<table.getColumnCount();j++){
                Cell cells=row10.createCell(j);
                cells.setCellValue(table.getColumnName(j));
            }
        for(int i=0;i<table.getRowCount();i++){
            Row rows=sheet.createRow(1+i);
            Cell cellMar=rows.createCell(0);
            for(int j=0;j<table.getColumnCount();j++){
                //if(j!=colexcept){
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
                //}
            }
                
        }
    }
    private boolean exist(String t,String s){
        String type="washing";
        String check="isWash";
        System.out.println(t);
        if(t!=null){
        if(t.equals("press")){
            type="press";
            check="pressed";
        }
        if(t.equals("matchbook")){
            type="Matchbook";
            check="booked";
        }
        
        String requete="select * from "+type+ " where stickers=?";
            System.out.println(requete);
        ResultSet rs=conn.select(requete, s);
        try {
            rs.last();
            System.err.println(rs.getRow());
            if(rs.getRow()>0){
                rs.beforeFirst();
                while(rs.next()){
                    if(rs.getBoolean(check)){
                        JOptionPane.showMessageDialog(this, "this sticker is already scan!\n in the "+t, "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
                        return true;
                    }
                    if(!rs.getString("type").trim().equals("second")){
                        JOptionPane.showMessageDialog(this, "this sticker cannot be scanned in this window!", "wrong Entry", JOptionPane.ERROR_MESSAGE);
                        return true;
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "this sticker is not able to process into "+t+"!", "wrong Entry", JOptionPane.ERROR_MESSAGE);
                        return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(post_sewing_scan.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
            JOptionPane.showMessageDialog(this,"Please select a location","Uncheck location",JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
        
    }
    
    private boolean exec(String t,String st,int qty){
        String type="washing";
        String check="isWash";
        System.out.println(t);
        if(t.equals("press")){
            type="press";
            check="pressed";
        }
        if(t.equals("matchbook")){
            type="Matchbook";
            check="booked";
        }
        String requete="update "+type+" set qty=?,"+check+"=1,modified=getDate(),user_id="+Principal.user_id+" where stickers=?";
        
        return conn.Update(requete, 0, qty,st);
        //return true;
    }
    
    private void mostrar(){
        String requete="select * from post_sewing_final where booked=1 and status<>'5' and type='second'";
        ResultSet rs=conn.select(requete);
        tbm.setRowCount(0);
        try {
            while(rs.next()){
                Object[] ob=new Object[8];
                ob[0]=rs.getString("po").trim();
                ob[01]=rs.getString("style").trim();
                ob[02]=rs.getString("sku").trim().replace(".", "-").split("-")[1];
                ob[03]=rs.getString("color").trim();
                ob[04]=rs.getString("size").trim();
                ob[05]=rs.getInt("qty");
                ob[06]=rs.getString("stickers").trim();
                ob[07]=rs.getString("category").trim();
              tbm.addRow(ob);
            }
        } catch (SQLException ex) {
            Logger.getLogger(post_sewing_scan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton export;
    private javax.swing.JTable grid_sew;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
