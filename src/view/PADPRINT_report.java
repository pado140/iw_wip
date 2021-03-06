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
public class PADPRINT_report extends javax.swing.JInternalFrame {
private ConnectionDb conn = ConnectionDb.instance();
private Set<Object[]> liste;
    private SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");;
    /**
     * Creates new form delivered_report
     */
    public PADPRINT_report() {
        
        initComponents();
        mostrar();
    }

    public void mostrar(){
        liste=new HashSet<>();
        DefaultTableModel tbm = (DefaultTableModel) grid_data.getModel();
    tbm.setRowCount(0);
    String requete="select * from PAD_PRINT_DAILY_REPORT";
    System.out.println(requete);
    ResultSet rs=conn.select(requete);
        try {
            while(rs.next()){
                tbm.addRow(new Object[]{rs.getString("PO"),rs.getString("STYLE"),rs.getString("COLOR_CODE"),rs.getString("COLOR"),rs.getString("SIZE"),rs.getInt("QTY"),rs.getDate("DATE_in")});
                liste.add(new Object[]{rs.getString("PO"),rs.getString("STYLE"),rs.getString("COLOR_CODE"),rs.getString("COLOR"),rs.getString("SIZE"),rs.getInt("QTY"),rs.getDate("DATE_in")});
            }   } catch (SQLException ex) {
            Logger.getLogger(Sewing_prod.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        posearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        stylesearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        datesearch = new com.toedter.calendar.JDateChooser();
        to = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        colorsearch = new javax.swing.JTextField();
        sizesearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Pad print-Daily Report");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeactivated(evt);
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/export.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("PO FILTER:");

        posearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                posearchKeyReleased(evt);
            }
        });

        jLabel2.setText("STYLE FILTER:");

        stylesearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stylesearchKeyReleased(evt);
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

        jLabel5.setText("SIZE");

        jLabel6.setText("COLOR:");

        colorsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                colorsearchKeyReleased(evt);
            }
        });

        sizesearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sizesearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stylesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sizesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(datesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(22, 22, 22)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 266, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(stylesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(colorsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(sizesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(posearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(datesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))))))
                .addContainerGap())
        );

        grid_data.setAutoCreateRowSorter(true);
        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "PO", "STYLE", "COLOR_CODE", "COLOR", "SIZE", "QTY", "DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(grid_data);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        // TODO add your handling code here:
        mostrar();
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser file=new JFileChooser("C:/",FileSystemView.getFileSystemView());
        file.setDialogTitle("enregistre le fichier");

        file.setFileFilter(new FileNameExtensionFilter("Workbook excel","xlsx","xls"));
        int returnAct=file.showSaveDialog(this);
        if(returnAct==JFileChooser.APPROVE_OPTION){
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("PAD_PRINT DAILY report");

            //Create some data to build the pivot table on
            setCellData(sheet,grid_data);

            FileOutputStream fileOut;
            try {
                String name=file.getSelectedFile().getAbsolutePath();
                if(!name.endsWith(".xlsx"))
                name=file.getSelectedFile().getAbsolutePath()+".xlsx";
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

    }//GEN-LAST:event_jButton1ActionPerformed

    private void posearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_posearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_posearchKeyReleased

    private void stylesearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stylesearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_stylesearchKeyReleased

    private void datesearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datesearchPropertyChange
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_datesearchPropertyChange

    private void colorsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_colorsearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_colorsearchKeyReleased

    private void sizesearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sizesearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_sizesearchKeyReleased

    private void toPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_toPropertyChange
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_toPropertyChange

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

    private void buscados(){
         DefaultTableModel tbm = (DefaultTableModel) grid_data.getModel();
               tbm.setRowCount(0);
        String po=posearch.getText().trim().toLowerCase();
        String style=stylesearch.getText().trim().toLowerCase();
        String color=colorsearch.getText().trim().toLowerCase();
        String size=sizesearch.getText().trim().toLowerCase();
        System.out.println("date:"+datesearch.getDate());
        for(Object[] ob:liste){
            if(datesearch.getDate()!=null){
                if(ob[3]!=null){
                    if(to.getDate()!=null){
                        Date d=new Date();
                        Calendar cal=Calendar.getInstance(TimeZone.getDefault(), Locale.CANADA);
                        cal.setTime(datesearch.getDate());
                        cal.add(Calendar.DAY_OF_MONTH, -1);
                        d=cal.getTime();
            if(ob[0].toString().trim().toLowerCase().contains(po)&&
                    ob[1].toString().trim().toLowerCase().contains(style)&&
                    (ob[2].toString().trim().toLowerCase().contains(color)||
                    ob[3].toString().trim().toLowerCase().contains(color))&&
                    ob[4].toString().trim().toLowerCase().contains(size)){
                try {
                    Date dd=formatter.parse(ob[6].toString());
                    if(dd.after(d)&&dd.before(to.getDate()))
                        tbm.addRow(ob);
                } catch (ParseException ex) {
                    Logger.getLogger(SOABAR_report.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                
            }
                    }else{
                        if(ob[0].toString().trim().toLowerCase().contains(po)&&
                    ob[1].toString().trim().toLowerCase().contains(style)&&
                    (ob[2].toString().trim().toLowerCase().contains(color)||
                    ob[3].toString().trim().toLowerCase().contains(color))&&
                    ob[4].toString().trim().toLowerCase().contains(size)&&
                    ob[6].toString().equals(formatter.format(datesearch.getDate())))
                    
                tbm.addRow(ob);
                    }
                }
            }else{
                if(ob[0].toString().trim().toLowerCase().contains(po)&&
                    ob[1].toString().trim().toLowerCase().contains(style)&&
                    (ob[2].toString().trim().toLowerCase().contains(color)||
                    ob[3].toString().trim().toLowerCase().contains(color))&&
                    ob[4].toString().trim().toLowerCase().contains(size))
                    
                tbm.addRow(ob);
            }
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField colorsearch;
    private com.toedter.calendar.JDateChooser datesearch;
    private javax.swing.JTable grid_data;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField posearch;
    private javax.swing.JTextField sizesearch;
    private javax.swing.JTextField stylesearch;
    private com.toedter.calendar.JDateChooser to;
    // End of variables declaration//GEN-END:variables
}
