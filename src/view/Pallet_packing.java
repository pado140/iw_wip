/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import connection.ConnectionDb;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;import java.util.stream.Collectors;
;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import view.report.BatchDetails;

/**
 *
 * @author Duvers
 */
public class Pallet_packing extends javax.swing.JInternalFrame implements Printable{
private final ConnectionDb conn = ConnectionDb.instance();
   final Map<Integer,Object[]> datapallet=new HashMap<>();
   private ArrayList<String> li=new ArrayList<>();
   private int total=0, boxes=0;
   private int count=1;
   private String pallet;
   private DefaultTableModel tbm;
  

    /**
     * Creates new form RATIO
     */
    public Pallet_packing() {
        
        initComponents();
        tbm=(DefaultTableModel)GRID_PACKING_LIST.getModel();
        GRID_PACKING_LIST.setRowHeight(30);
        GRID_PACKING_LIST.setFont(new java.awt.Font("calibri",java.awt.Font.ROMAN_BASELINE,15));
       
        load_data();
        fill_list();
    }
    
    private void init(){
        tbm.setNumRows(0);
        load_data();
        fill_list();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GRID_PACKING_LIST = new javax.swing.JTable();
        panel_grid = new javax.swing.JPanel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cut plan report");

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

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jButton1.setText("Print lpn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Print sticker");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Filter Batch No");

        jButton3.setText("Refresh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(68, 68, 68))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jButton3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addGap(0, 0, 0))
        );

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setDoubleBuffered(false);

        GRID_PACKING_LIST.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PO", "STYLE", "COLOR CODE", "COLOR", "SIZE", "QTY", "LPN", "PASS", "stickers"
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
        jScrollPane1.setViewportView(GRID_PACKING_LIST);

        panel_grid.setDoubleBuffered(false);
        panel_grid.setName(""); // NOI18N
        panel_grid.setLayout(new javax.swing.BoxLayout(panel_grid, javax.swing.BoxLayout.PAGE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(panel_grid, javax.swing.GroupLayout.PREFERRED_SIZE, 1106, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_grid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane4.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        // TODO add your handling code here:
        pallet=((JList)evt.getSource()).getSelectedValue().toString();
        int id=Integer.parseInt(pallet.substring("Batch no ".length()).trim());
        fill_table_pieces(id);
    }//GEN-LAST:event_jList1ValueChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        /* 
        javax.print.attribute.HashPrintRequestAttributeSet att = 
new javax.print.attribute.HashPrintRequestAttributeSet();
        PrinterJob printerJob = PrinterJob.getPrinterJob();
 
        PageFormat pageFormat = printerJob.defaultPage();
// For landscape we should use PageFormat.LANDSCAPE instead
        pageFormat.setOrientation(PageFormat.LANDSCAPE);
// Show page format dialog with page format settings
        pageFormat = printerJob.pageDialog(pageFormat);
        printerJob.setPrintable(GRID_PACKING_LIST.getPrintable(javax.swing.JTable.PrintMode.FIT_WIDTH, 
new MessageFormat("Packing list for pallet:"+pallet), new MessageFormat("{0}")), pageFormat);
        try {
        // Show print dialog to allow the user to change the default printer settings
        if (printerJob.printDialog()) {
        printerJob.print();
        }
        } catch (PrinterException e) {
        System.out.println("Printer exception : " + e.getMessage());
        
        }
        //new PrintPreview(GRID_PIECES.getPrintable(javax.swing.JTable.PrintMode.FIT_WIDTH, 
//new MessageFormat("Capitals"), new MessageFormat("{0}")), printerJob.getPageFormat(att));
    */
        if(!jList1.getSelectionModel().isSelectionEmpty()){
            ArrayList<BatchDetails> batches=new ArrayList();
            int pieces=0;
            int boxs=0;
            if(GRID_PACKING_LIST.getRowCount()>0){
                for(int i=0;i<GRID_PACKING_LIST.getRowCount();i++){
                    BatchDetails bd=new BatchDetails();
                    bd.setSticker(GRID_PACKING_LIST.getValueAt(i, 6).toString());
                    
                    bd.setQty((Integer.parseInt(GRID_PACKING_LIST.getValueAt(i, 5).toString())));
                    bd.setSize(GRID_PACKING_LIST.getValueAt(i, 4).toString());
                    batches.add(bd);
                    boxs++;
                    pieces+=Integer.parseInt(GRID_PACKING_LIST.getValueAt(i, 5).toString());
                }
                
            }
            Map<String,String>Sizes=new HashMap();
            Map<String,Integer>values=new HashMap();
            Map<String,Integer>piece=new HashMap();
            count=1;
            Map<String,Long> BoxesBySizes=batches.parallelStream().collect(Collectors.groupingBy(BatchDetails::getSize,Collectors.counting()));
            Map<String,Integer> PiecesBySizes=batches.parallelStream().collect(Collectors.groupingBy(BatchDetails::getSize,Collectors.summingInt(BatchDetails::getQty)));
            BoxesBySizes.forEach((k,v)->{
                Sizes.put("size"+count,k);
                values.put("val"+count,v.intValue());
                piece.put("pieces"+count,PiecesBySizes.get(k));
                count++;
                
            });
            
            jList1.getSelectedValue().toString();
        int id=Integer.parseInt(pallet.substring("Batch no ".length()).trim());
        Object[] batchdetails=datapallet.get(id);
            printbatche(batchdetails[2].toString(),id,batchdetails[0].toString(),batchdetails[1].toString(),batchdetails[3].toString(),pieces,boxs,batches,
                    batchdetails[4].toString().equalsIgnoreCase("packed")?"for Audit":"for re-Audit",Sizes,values,piece,batchdetails[5].toString(),
                    batchdetails[4].toString().equalsIgnoreCase("packed")?(Date)batchdetails[6]:(Date)batchdetails[7]);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        ArrayList l2=new ArrayList();
        
        for(int i=0;i<li.size();i++){
            
            if(li.get(i).contains(jTextField1.getText().trim())){
                l2.add(li.get(i));
            }
        }
        jList1.setModel(new javax.swing.AbstractListModel() {
           Object[] strings = l2.toArray();
           @Override
            public int getSize() { return strings.length; }
           @Override
            public Object getElementAt(int i) { return strings[i]; }
        });
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(!jList1.getSelectionModel().isSelectionEmpty()){
            ArrayList<BatchDetails> batches=new ArrayList();
            int pieces=0;
            int boxs=0;
            if(GRID_PACKING_LIST.getRowCount()>0){
                for(int i=0;i<GRID_PACKING_LIST.getRowCount();i++){
                    BatchDetails bd=new BatchDetails();
                    bd.setSticker(GRID_PACKING_LIST.getValueAt(i, 8).toString());
                    
                    bd.setQty((Integer.parseInt(GRID_PACKING_LIST.getValueAt(i, 5).toString())));
                    bd.setSize(GRID_PACKING_LIST.getValueAt(i, 4).toString());
                    batches.add(bd);
                    boxs++;
                    pieces+=Integer.parseInt(GRID_PACKING_LIST.getValueAt(i, 5).toString());
                }
                
            }
            Map<String,String>Sizes=new HashMap();
            Map<String,Integer>values=new HashMap();
            Map<String,Integer>piece=new HashMap();
            count=1;
            Map<String,Long> BoxesBySizes=batches.parallelStream().collect(Collectors.groupingBy(BatchDetails::getSize,Collectors.counting()));
            Map<String,Integer> PiecesBySizes=batches.parallelStream().collect(Collectors.groupingBy(BatchDetails::getSize,Collectors.summingInt(BatchDetails::getQty)));
            BoxesBySizes.forEach((k,v)->{
                Sizes.put("size"+count,k);
                values.put("val"+count,v.intValue());
                piece.put("pieces"+count,PiecesBySizes.get(k));
                count++;
                
            });
            
            jList1.getSelectedValue().toString();
        int id=Integer.parseInt(pallet.substring("Batch no ".length()).trim());
        Object[] batchdetails=datapallet.get(id);
            printbatche(batchdetails[2].toString(),id,batchdetails[0].toString(),batchdetails[1].toString(),batchdetails[3].toString(),pieces,boxs,batches,
                    batchdetails[4].toString().equalsIgnoreCase("packed")?"for Audit":"for re-Audit",Sizes,values,piece,batchdetails[5].toString(),
                    batchdetails[4].toString().equalsIgnoreCase("packed")?(Date)batchdetails[6]:(Date)batchdetails[7]);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void load_data(){
        String requete="select * from batches where status like'%packed%'";
        li.clear();
        datapallet.clear();
        try{
            ResultSet rs=conn.select(requete);
            
            while(rs.next()){
                li.add("Batch no "+rs.getInt("id"));
                datapallet.put(rs.getInt("id"),new Object[]{rs.getString("po"),rs.getString("style"),rs.getString("color"),rs.getString("customer"),rs.getString("status"),rs.getInt("fail")
                        ,rs.getDate("created"),rs.getDate("modified")});
            }
        }catch(SQLException e){
            
        }
       // return li;
    }
   
    
    //private void 
    private void fill_list(){
        jList1.setModel(new javax.swing.AbstractListModel() {
           Object[] strings = li.toArray(new Object[li.size()]);
           @Override
            public int getSize() { return strings.length; }
           @Override
            public Object getElementAt(int i) { return strings[i]; }
         });
    }
   
    private void fill_table_pieces(int id){
        String requete="select * from lpn_in_batch where batch_id=? and status_lpn like'%open%'";
        ResultSet rs=conn.select(requete, id);
        tbm.setNumRows(0);
        total=0;boxes=0;
    try {
        while(rs.next()){
            tbm.addRow(new Object[]{rs.getString("ponum"),
            rs.getString("style"),
            rs.getString("sku").replace(".","-").split("-")[1],
            rs.getString("coldsp"),rs.getString("size"),rs.getInt("last_qty"),rs.getString("lpn"),false,rs.getString("box_stickers")});
            boxes++;
            total+=rs.getInt("last_qty");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Pallet_packing.class.getName()).log(Level.SEVERE, null, ex);
    }
    
   
    
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GRID_PACKING_LIST;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel panel_grid;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
       return 0;
    }
    
    private void printbatche(String color,int no,String po,String style,String client,int pieces,int boxes,ArrayList<BatchDetails> batchdetail,String type,Map<String,String>sizes,Map<String,Integer> val
    ,Map<String,Integer> piece,String time,Date create){
        
        try{ 
            URL  master= this.getClass().getResource("report/bacth_report.jasper");
                  Map param = new HashMap();
                  param.put("client",client);
                  param.put("pieces",pieces);
                  param.put("color",color);
                  param.put("boxes",boxes);
                  param.put("batchno",no);
                  param.put("po",po);
                  param.put("style",style);
                  param.put("type",type);
                  param.put("create",create);
                  param.putAll(sizes);
                  param.putAll(val);
                  param.putAll(piece);
                  JasperReport jasperReport = (JasperReport) JRLoader.loadObject(master);
                  //JRBeanCollectionDataSource beanColDataSource =new JRBeanCollectionDataSource(listdata);
                  JRBeanCollectionDataSource beanCutDataSource =new JRBeanCollectionDataSource(batchdetail);
                  //param.put("cutData",beanCutDataSource);
                  //String report=JasperFillManager.fillReportToFile(source.getAbsolutePath(),param,beanColDataSource);
                  JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,param,beanCutDataSource);
                  //JasperViewer.viewReport(jasperPrint);
                  String key=""+no;
                  if(type.contains("re-Audit"))
                      key+="(reAudit)"+(time.equals(0)?"":time);
                      
                  if(Files.exists(new File("K:\\BATCHES/").toPath())){
                      if(!Files.exists(new File("K:\\BATCHES/batch no "+key+".pdf").toPath()))
                            JasperExportManager.exportReportToPdfFile(jasperPrint, "K:\\BATCHES/batch no "+key+".pdf");
                      else
                          JOptionPane.showInternalMessageDialog(this, "File already exist");
                      
                  
                  Desktop dsk=Desktop.getDesktop();
                  dsk.open(new File("K:\\BATCHES/batch no "+key+".pdf"));
                  dsk.print(new File("K:\\BATCHES/batch no "+key+".pdf"));
                
                
                  
                  
                  //dsk.print(new File("P:\\BATCHES/"+code+"-"+no+".pdf"));
                 }else{
                      
                      JOptionPane.showMessageDialog(this, "please make sure that you can connect with the drive K:");
                      
                      if(!Files.exists(new File(System.getProperty("user.home").concat("/Documents/BATCHES")).toPath())){
                          //f.createNewFile();
                          File f=new File(System.getProperty("user.home").concat("/Documents/BATCHES"));
                          //f.createNewFile();
                          f.mkdirs();
                          //Files.createDirectory(new File(System.getProperty("user.home").concat("/Documents/BATCHES/barcode")).toPath()); 
                      }
                      JasperExportManager.exportReportToPdfFile(jasperPrint, System.getProperty("user.home").concat("/Documents/BATCHES/")+"batch no "+key+".pdf");
                  
                  Desktop dsk=Desktop.getDesktop();
                  dsk.open(new File(System.getProperty("user.home").concat("/Documents/BATCHES/")+"batch no "+key+".pdf"));
                  dsk.print(new File(System.getProperty("user.home").concat("/Documents/BATCHES/")+"batch no "+key+".pdf"));
                  }
                  
 
                }
 
                catch (Exception e)
                 {
                     e.printStackTrace();
                     System.out.println("Mensaje de Error:"+e.getMessage());
                     JOptionPane.showMessageDialog(this, e.getMessage());
                     
                     //JOptionPane.showMessageDialog(this, e.());
                 }
    }
    
}
