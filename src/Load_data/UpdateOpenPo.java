package Load_data;


import connection.ConnectionDb;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import observateurs.Observateurs;
import observateurs.Observe;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class UpdateOpenPo extends javax.swing.JInternalFrame implements Observe{

     private String inputFile;
     private final ConnectionDb conn=ConnectionDb.instance();
     List<Object[]> data=null;
     private SimpleDateFormat formatter=new SimpleDateFormat("MM/dd/yy");
     DataFormatter formatdata;
     DefaultTableModel tbm;
     
     //private JInternalFrame summary

  
    public UpdateOpenPo() {
        initComponents();
        tbm=(DefaultTableModel)grid_po.getModel();
       init();
         formatdata=new DataFormatter();
       grid_po.getTableHeader().setFont( new Font( "Arial" , Font.BOLD, 13 ));
    }
    
    private void init(){
        tbm.setRowCount(0);
    }
    public void setInputFile(String inputFile) {
                this.inputFile = inputFile;
        }
     //										CONF QTY	EXT. MSG #	DEL. NOTE DATE	MSG #	SUPPLIER	PACKAGING	PO LINE	MO NUM
 	 		  	 	  	   	  

    private boolean checkHeader(List<Object> data){
         Map<Integer, String> titre=new HashMap<>();
            titre.put(0,"PO SKU");
            titre.put(1,"WORKORDER");
            titre.put(3,"NEW X-FACTORY");
            titre.put(2,"OLD X-FACTORY");
                
         boolean valid=true;
         String text="";
         if(data.isEmpty())
             valid=false;
         else{
         for(Map.Entry<Integer,String> st:titre.entrySet()){
             text+=st.getValue()+" \t ";
             if(!st.getValue().equals(data.get(st.getKey()))){
                valid=false; 
                
             }
         }
         }
         if(!valid)
         JOptionPane.showMessageDialog(this, "please use the right format, the header must be like below\n"+text);
        return valid;
    }
        
    public void read(int type) throws IOException  {
        FileInputStream fis = new FileInputStream(inputFile);
        Sheet sheet=null;
        int pieces=0;
        int linetoignore=0;
        Workbook book1=null;
        if(type==1)
            book1 = new XSSFWorkbook(fis);
        if(type==2)
            book1 = new HSSFWorkbook(fis);
        
        sheet = book1.getSheetAt(0);
        Map<Integer,String> obj=new HashMap<>();
        Iterator<Row> itr = sheet.iterator();
        List<Object> datarow=null;
        List<Object> titre=new ArrayList<>();
        try {
           // Iterating over Excel file in Java
            int i=0,count=0;
            Row tit=sheet.getRow(0);
            Cell ce=tit.getCell(i);
            if(!formatdata.formatCellValue(ce).trim().isEmpty()){
                tit=sheet.getRow(0);
                data=new ArrayList<>();
                datarow=new ArrayList<>();
                Iterator<Cell> cellIte=tit.cellIterator();
                boolean blank=false;
                while (cellIte.hasNext() && !blank) {
                    Cell cell = cellIte.next();
                    String val=formatdata.formatCellValue(cell).trim();
                    titre.add(val);
                    blank=val.isEmpty();
                }
            }else{
                return;
                }
            if(!checkHeader(titre))
                return;
            itr.next();
            int k=0;
            while (itr.hasNext()) {
                 Row row=itr.next();
                 Object[] data=new Object[4];
                 for(i=0;i<4;i++){
                     Cell c=row.getCell(i);
                     data[i]=formatdata.formatCellValue(c).trim();
                 }
                 tbm.addRow(data);
            }
                
 fis.close();
 } catch (FileNotFoundException fe) {
     fe.printStackTrace();
 } catch (IOException ie) {
     ie.printStackTrace(); 
 }  

 if(grid_po.getRowCount()>0)
     jButton3.setEnabled(true);
} 

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        grid_po = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_box = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_piece = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Update XFACTORY DATE");

        grid_po.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PO SKU", "WORKORDER", "OLD X-FACTORY", "NEW X-FACTORY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grid_po.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(grid_po);

        jButton1.setText("Load Excel File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nbre of lines");

        txt_box.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_box.setText("......");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Qtity of pieces:");

        txt_piece.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_piece.setText("......");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_box))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txt_piece)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_box))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_piece))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jButton2.setText("Clear Table");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Update");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        File selectedFile = null;
          JFileChooser fileChooser = new JFileChooser();
fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
int result1 = fileChooser.showOpenDialog(this);
int type=0;
if (result1 == JFileChooser.APPROVE_OPTION) {
    selectedFile = fileChooser.getSelectedFile();
    System.out.println("Selected file: " + selectedFile.getName());
    
    String ext="";
           ext =selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".")+1);
            System.out.println("Selected file ext: " + ext);
            
            if(ext.equals("xlsx"))
                type=1;
            if(ext.equals("xls"))
                type=2;
    if(type!=0){                
                 setInputFile(selectedFile.getAbsolutePath());
         try {
             read(type);
         } catch (IOException ex) {
             Logger.getLogger(loadFrame.class.getName()).log(Level.SEVERE, null, ex);
         }   
    }else{
        JOptionPane.showMessageDialog(this, "This file can't be open\nplease verify", "Error", JOptionPane.ERROR_MESSAGE);
    }      
            
           
}     
        
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        DefaultTableModel tbm = (DefaultTableModel) grid_po.getModel();
        tbm.setRowCount(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
//        String ord=lastOrdnum();
//        int ordernum=Integer.parseInt(ord);
//        int line=1,del=1;
//        Set<String> Orders=new HashSet<>();
//        Map<String,Object[]> datatoUpdate=new HashMap<>();
//        
//        String po=po_text.getText().trim();
//        
//        if(Po_Exist(po)!=null){
//            Set<String> toRemove=listOrder(po);
//            String brand=Po_Exist(po_text.getText().trim())[1].toString();
//            Date xfact=null;
//                Date poDate=null;
//                Date shipDate=null;
//            try {
//                xfact=formatter.parse(last_date.getText().trim());
//                poDate=formatter.parse(po_date.getText().trim());
//                shipDate=formatter.parse(ship_date.getText().trim());
//            } catch (ParseException ex) {
//                Logger.getLogger(loadlpn.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Map<String ,String> error =new LinkedHashMap<>();
//            
//            for(int i=0;i<grid_po.getRowCount();i++){
//                String prtid=grid_po.getValueAt(i, 3).toString().trim();
//                double po_price=grid_po.getValueAt(i, 12).toString().trim().isEmpty()?0:Double.parseDouble(grid_po.getValueAt(i, 12).toString());
//                double lbs=grid_po.getValueAt(i, 10).toString().trim().isEmpty()?0:Double.parseDouble(grid_po.getValueAt(i, 10).toString());
//                String ord1=String.valueOf(ordernum);
//                String color=grid_po.getValueAt(i, 2).toString().trim();
//                String code=prtid.substring(prtid.indexOf(".")+1,prtid.lastIndexOf("."));
//                color=color.substring(color.indexOf("-")+1);
//                String size=prtid.substring(prtid.lastIndexOf(".")+1);
//                String style=prtid.substring(0, prtid.indexOf("."));
//                int qty=(int)Double.parseDouble(grid_po.getValueAt(i, 4).toString());
//                String description =grid_po.getValueAt(i, 1).toString().trim();
//                String desc=description;
//                if(description.toLowerCase().contains("youth"))
//                        desc=description.replace("YOUTH ", "");
//                    else if(description.toLowerCase().contains("yth"))
//                        desc=description.replace("YTH ", "");
//                    else if(description.toLowerCase().contains("girls"))
//                        desc=description.replace("GIRLS ", "");
//                    else if(description.toLowerCase().contains("ladies"))
//                        desc=description.replace("LADIES ", "");
//                    else if(description.toLowerCase().contains("lds"))
//                        desc=description.replace("LDS ", "");
//                    else if(description.toLowerCase().contains("adult"))
//                        desc=description.replace("ADULT ", "");
//                if(Prtid_Exist(prtid)==null)
//                    prtid=savePartid(prtid,brand,description,desc,style,prtid,
//                                size,color);
//                 if(line>99){
//                     line=1;
//                     del++;
//                 }
//                if(Order_Exist(po,prtid)!=null){
//                    
//                    System.out.println("order_num="+Order_Exist(po,prtid));
//                    String order=Order_Exist(po,prtid).trim();
//                    String bran=comb_cust.getSelectedItem().toString();
//                    if(updateOrder(order,ship_date.getText().trim(),last_date.getText().trim(),po_price,lbs,qty,bran)){
//                        Orders.add(order);
//                        System.out.println("success");
//                    }else{
//                        error.put("order num:"+order+" ==> ", conn.getErreur());
//                        System.out.println("erreur:"+conn.getErreur());
//                    }
//                }else{
//                    ordernum++;
//                    ord1=String.valueOf(ordernum);
//                    if(!saveOrder(po,ord1,prtid,poDate,qty,brand,po.concat(".").concat(style).concat(".").concat(code),shipDate,xfact,po_price,lbs,line,del)){
//                        error.put("po num:"+po+"\t sku: "+prtid+" ==> ", conn.getErreur());
//                        System.out.println("erreur:"+conn.getErreur());
//                    }else{
//                        Orders.add(ord1); 
//                    }
//                
//                    /* System.out.println("po="+po+"   part_num="+prtid);
//                    error.put("po="+po+"/t part_num="+prtid+" ==> ", "line inexist in the database");*/
//                }
//               line++; 
//            }
//            if(!error.isEmpty()){
//                String er="";
//                for(String err:error.keySet()){
//                    er+="\n"+err+","+error.get(err);
//                }
//                JOptionPane.showMessageDialog(this, er, "error update", JOptionPane.ERROR_MESSAGE);
//            }else{
//                toRemove.removeAll(Orders);
//                if(!toRemove.isEmpty()){
//            String requete="delete from order_master where ordnum_10 =?";
//            for(String order:toRemove){
//                if(!conn.Update(requete, 0, order))
//                    error.put(order, conn.getErreur());
//            }
//            if(!error.isEmpty()){
//                String er="";
//                for(String err:error.keySet()){
//                    er+="\n"+err+","+error.get(err);
//                }
//                JOptionPane.showMessageDialog(this, er, "error delet", JOptionPane.ERROR_MESSAGE);
//                }
//                JOptionPane.showMessageDialog(this, "succesfully saved");
//                
//            }
//            }
//            jButton2.doClick();
//        }else
//            JOptionPane.showMessageDialog(this, "This PO is not in the database yet", "unknown", JOptionPane.ERROR_MESSAGE);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    
    public boolean updateOrder(String ord,String shipdate,String X_factory){
        String query="update Order_Master set ORGDUE_10=?,XDFDTE_10=? where ORDNUM_10=? ";
         System.out.println(query);
         System.out.println("shipdate:'"+shipdate+"' xfa:"+X_factory+" order:"+ ord);
        return conn.Update(query,1, shipdate,X_factory,ord);
    }
    
    private void Save(){
        Map<String,String> errors=new HashMap<>();
            
                Date shipDate=null;
        for(int a=0;a<grid_po.getRowCount();a++){
            String xfact=grid_po.getValueAt(a, 3).toString().trim();
            String newDate=grid_po.getValueAt(a, 4).toString().trim();  
            String order=grid_po.getValueAt(a, 2).toString();
            if(!newDate.isEmpty()){
                if(!updateOrder(order, newDate, newDate)){
                    errors.put(order,conn.getErreur());
                } 
            }
        }
        //if()
            JOptionPane.showMessageDialog(this, "Save with errors");
    }
    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable grid_po;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txt_box;
    private javax.swing.JLabel txt_piece;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ajouterObservateur(Observateurs ob) {
        obs.add(ob);
    }

    @Override
    public void retirerObservateur(Observateurs ob) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alerter(Object... ob) {
        for(Observateurs o:obs){
            o.executer(ob);
        }
    }
    private String  filtre(String a){
        String t=a;
        if(t.length()>8){
            String tt[]=t.split(" ");
            for(int ii=0;ii<tt.length;ii++){
                if(tt[ii].trim().length()==8)
                    t=tt[ii];
            }
        }
        return t;
    }
    
}
