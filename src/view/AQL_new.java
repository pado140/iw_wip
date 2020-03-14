/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
public class AQL_new extends javax.swing.JInternalFrame {
private ConnectionDb conn = ConnectionDb.instance();
    private Map<Integer,Map<String , Integer>> defts;
    private Map<String,String> defect_code_name;
    private List<String> defectName;
    private String[] columnName;
    private Set<Object[]> listeData;
    private DefaultTableModel tbm;
    private fillData loader;
    
    class fillData extends SwingWorker<Void,Void>{
        int tot=0,line=0;
        int piece=0,box=0,carton=0,revie=0;
        Object[][] datatable;
        
        public fillData(){
            progression.setVisible(true);
            state4.setText("initiating");
            System.out.println("start");
        }

        @Override
        protected Void doInBackground(){
            defects();
            listeData=new HashSet<>();
            String requete="select * from audit_report";
            ResultSet rs = conn.select(requete);
            int ij=0;
            defect_name();
            title(defectName);
            grid.setModel(new DefaultTableModel(columnName,0));
            tbm = (DefaultTableModel) grid.getModel();
            System.out.println("start load");
            progression.setIndeterminate(false);
            state4.setText("loading...");
        try {
            rs.last();
            line=rs.getRow();
            rs.beforeFirst();
            while(rs.next()){
                String po=rs.getString("po").trim(),
                        style=rs.getString("style").trim(),
                        auditor=rs.getString("AUDITOR_NAME").trim(),
                        customer=rs.getString("customer").trim(),
                        result=rs.getInt("result")>0?"Passed":"Failed",
                        color=rs.getString("color").trim();
                
                int boxes=rs.getInt("box_count");
                int pieces=rs.getInt("piecesBox");
                int cartons=rs.getInt("cartons");
                int review=rs.getInt("pieces");
                int auditId=rs.getInt("id");
                int batch=rs.getInt("batch_id");
                int defs=rs.getInt("defects");
                
                Object[] data=new Object[columnName.length];
                data[0]=auditId;
                data[1]=rs.getDate("date");
                data[2]=batch;
                data[3]=po;
                data[4]=style;
                data[5]=color;
                data[6]=customer;
                data[7]=boxes;
                data[8]=pieces;
                data[9]=cartons;
                data[10]=review;
                data[11]=result;
                data[12]=auditor;
                data[13]=defs;
                if(defts.get(auditId)!=null){
                    Map<String,Integer> v=defts.get(auditId);
                    for(String d:v.keySet()){
                        if(v.get(d)!=null)
                            data[grid.getColumn(d).getModelIndex()]=v.get(d);
                    }
                }
                ij++;
                System.out.println("ij:"+ij);
                setProgress((int)Math.ceil(ij*100/line));
                listeData.add(data);
                tbm.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Work_status.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
            return null;
        
        }

        @Override
        protected void process(List<Void> chunks) {
            super.process(chunks); //To change body of generated methods, choose Tools | Templates.
            //grid.notify();
            
        }

        @Override
        protected void done() {
            super.done(); //To change body of generated methods, choose Tools | Templates.
            if(this.isDone()){
            progression.setVisible(false);
                System.out.println("ok");
            state4.setText("Ready");
            }
            if(this.isCancelled()){
            progression.setVisible(true);
            progression.setForeground(Color.red);
            
            state4.setText("Cancelled");
           System.out.println("nbr line:"+line);
            }
        }

        
    }
    /**
     * Creates new form AQL
     */
    public AQL_new() {
        initComponents();
        init();
    }
    
    private void init(){
        
        grid.addPropertyChangeListener((evt)-> {
                System.out.println(evt.getNewValue());
            }
        );
        loader=new fillData();
        progression.setIndeterminate(true);
            progression.setString("initiating");
            progression.setStringPainted(true);
        loader.addPropertyChangeListener((evt) -> {
            if(evt.getPropertyName().equals("progress")){
                System.err.println(evt.getPropertyName());
                System.err.println(evt.getNewValue());
                //if((Integer)evt.getNewValue()%20==0)
                    //alerter("reload",data);
                progression.setValue((Integer)evt.getNewValue());
                progression.setString(progression.getValue()+"%");
            }
        });
        loader.execute();
        
    }

    private Map<Integer,Map<String,Integer>> defects(){
        defts=new HashMap<>();
        String req="select * from audit_defect_summary";
        ResultSet rs=conn.select(req);
        try {
            while(rs.next()){
                if(defts.get(rs.getInt("audit_id"))!=null){
                    defts.get(rs.getInt("audit_id")).put(rs.getString("DEFECT_CODE"), rs.getInt("qty"));
                }else{
                    Map<String,Integer> v=new HashMap<>();
                    v.put(rs.getString("DEFECT_CODE"), rs.getInt("qty"));
                    defts.put(rs.getInt("audit_id"),v);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return defts;
    }
    
    private List<String> defect_name(){
        defectName=new ArrayList<>();
        defect_code_name=new HashMap<>();
        String req="select distinct DEFECT_CODE,defect from audit_defect_summary";
        ResultSet rs=conn.select(req);
        try {
            while(rs.next()){
                defectName.add(rs.getString("DEFECT_CODE"));
                defect_code_name.put(rs.getString("DEFECT_CODE").trim(), rs.getString("DEFECT"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return defectName;
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
        export = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        FROM = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        TO = new com.toedter.calendar.JDateChooser();
        grid_data = new javax.swing.JScrollPane();
        grid = new javax.swing.JTable();
        status4 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        state4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        progression = new javax.swing.JProgressBar();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("AQL");

        export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/export.png"))); // NOI18N
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("From:");

        FROM.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                FROMPropertyChange(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("To:");

        TO.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TOPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(FROM, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(TO, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(export)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FROM, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );

        grid.setAutoCreateRowSorter(true);
        grid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        grid.getTableHeader().setReorderingAllowed(false);
        grid_data.setViewportView(grid);

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel38.setText("Status:");

        state4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        state4.setText("Status:");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        progression.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                progressionPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout status4Layout = new javax.swing.GroupLayout(status4);
        status4.setLayout(status4Layout);
        status4Layout.setHorizontalGroup(
            status4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progression, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel38)
                .addGap(29, 29, 29)
                .addComponent(state4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        status4Layout.setVerticalGroup(
            status4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(state4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator5)
            .addComponent(progression, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(grid_data, javax.swing.GroupLayout.DEFAULT_SIZE, 1154, Short.MAX_VALUE)
            .addComponent(status4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(grid_data, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(status4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void progressionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_progressionPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_progressionPropertyChange

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        JFileChooser file=new JFileChooser("C:/",FileSystemView.getFileSystemView());
        file.setDialogTitle("enregistre le fichier");

        file.setFileFilter(new FileNameExtensionFilter("Workbook excel","xlsx","xls"));
        int returnAct=file.showSaveDialog(this);
        if(returnAct==JFileChooser.APPROVE_OPTION){
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("AQL");

            //Create some data to build the pivot table on
            setCellData(sheet,grid);

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
            }catch (FileNotFoundException ex) {
                Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
            }catch (IOException ex) {
                Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(null, "File saved with success");
        }
    }//GEN-LAST:event_exportActionPerformed

    private void FROMPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FROMPropertyChange
        // TODO add your handling code here:
        if(listeData!=null&&listeData.size()>0)
            buscar();
    }//GEN-LAST:event_FROMPropertyChange

    private void TOPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TOPropertyChange
        // TODO add your handling code here:
        if(listeData!=null&&listeData.size()>0)
            buscar(); 
    }//GEN-LAST:event_TOPropertyChange

    public void setCellData(XSSFSheet sheet,JTable table){
        Row row10=sheet.createRow(0);
        for(int j=0;j<table.getColumnCount();j++){
                Cell cells=row10.createCell(j);
                cells.setCellValue(defect_code_name.getOrDefault(table.getColumnName(j),table.getColumnName(j)));
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
                    System.out.println(e);
                }
            }
                
        }
    }
    
    private void buscar(){
        tbm.setNumRows(0);
        for(Object[] dataRow : listeData){
                DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
                Date date=(Date)dataRow[1];
            
                if(FROM.getDate()!=null){
                            Calendar cal=Calendar.getInstance(TimeZone.getDefault(), Locale.CANADA);
                            cal.setTime(FROM.getDate());
                            cal.add(Calendar.DAY_OF_MONTH, -1);
                            Date d=cal.getTime();
                            Date dd=null;
                            
                    if(dataRow[1]!=null){
                        try {
                                dd=dateformat.parse(dataRow[1].toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(SOABAR_report.class.getName()).log(Level.SEVERE, null, ex);
                            }   
                        if(TO.getDate()!=null){
                            if( dd.after(d) && dd.before(TO.getDate())){
                                tbm.addRow(dataRow);
                            }   
                        }else{
                            if(dataRow[1].toString().equals(dateformat.format(FROM.getDate()))){
                                tbm.addRow(dataRow);
                            }   
                        }
                    }
                }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FROM;
    private com.toedter.calendar.JDateChooser TO;
    private javax.swing.JButton export;
    private javax.swing.JTable grid;
    private javax.swing.JScrollPane grid_data;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JProgressBar progression;
    private javax.swing.JLabel state4;
    private javax.swing.JPanel status4;
    // End of variables declaration//GEN-END:variables

    private String[] title(List<String> title){
        String[] titre=new String[]{"Audit No","Date Audit","Batch No","PO","STYLE","COLOR","CUSTOMER","Box count","Pieces Count","Cartons pulled","Pieces reviewed","Result","Auditor","Defects"};
        ArrayList<String> list=new ArrayList<>();
        list.addAll(Arrays.asList(titre));
        
        list.addAll(title);
        columnName=list.toArray(new String[list.size()]);
        return columnName;
    }
}
