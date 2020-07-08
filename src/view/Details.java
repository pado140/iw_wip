/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
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
import java.util.List;
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
public class Details extends javax.swing.JInternalFrame {
private final ConnectionDb conn = ConnectionDb.instance();
private Set<Object[]> liste;
    private SimpleDateFormat formatter=new SimpleDateFormat("MM-dd-yyyy");
    private DefaultTableModel tbm ;
    private Populatepack populate;
    
private boolean initiated;

    
    /**
     * Creates new form Details
     */
    
    class Populatepack extends SwingWorker<Void,Object[]>{
        int tot=0,line=0;
        int ij=0,qty_f=0,qty_b=0;
        
        
        public Populatepack(){
            System.out.println("new task");
            initiated=false;
            progress.setVisible(true);
            export.setEnabled(false);
            state.setText("initiating");
            
        }

        @Override
        protected Void doInBackground(){
            //progress.setString("0%");
            visible(false);
            count.setText("Waiting...");
            first.setText("Waiting...");
            secondlabel.setText("Waiting...");
            
            //load();
            progress.setIndeterminate(false);
        state.setText("loading...");    
        tbm.setRowCount(0);
    liste=new HashSet<>();
    String requete="select * from PACKING_after_sewing";
    System.out.println(requete);
    ResultSet rs=conn.select(requete);
        try {
            rs.last();
            int nb=rs.getRow();
            rs.beforeFirst();
            int i=0;
            while(rs.next()){
                String sku=rs.getString("sku");
                   String size=sku.substring(sku.lastIndexOf(".")+1).trim();
                   /*if(rs.getString("description").toLowerCase().contains("yth")||
                   rs.getString("description").toLowerCase().contains("youth")||
                   rs.getString("description").toLowerCase().contains("girls"))
                   size="Y"+size;*/
                String color,Code;
               color=rs.getString("coldsp").trim();
               Code=sku.substring(sku.indexOf(".")+1,sku.lastIndexOf("."));
             
                   Object[] datas=new Object[11];
                   datas[0]=rs.getString("brand");
                   datas[1]=rs.getString("ponum").trim();
                   datas[2]=rs.getString("style");
                   datas[3]=Code;
                   datas[4]=color;
                   datas[5]=size;
                   datas[6]=rs.getInt("val");
                   datas[7]=rs.getString("created").replace("/", "-").trim();
                   datas[8]=rs.getInt("nb");
                   datas[9]=rs.getString("tablepacking");
                   datas[10]=rs.getString("module");
                   qty_f+=rs.getInt("val");
                   qty_b+=rs.getInt("nb");
                  liste.add(datas);
                   //tbm.addRow(datas);
                  fillTable(datas);
                  setProgress((int)Math.ceil(100*i/nb));
                   i++;
                   
            }   } catch (SQLException ex) {
            Logger.getLogger(Sewing_prod.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            return null;
        }

        @Override
        protected void process(List<Object[]> chunks) {
            super.process(chunks); //To change body of generated methods, choose Tools | Templates.
            //GRID_DATA.notify();
            
        }

        @Override
        protected void done() {
            super.done(); //To change body of generated methods, choose Tools | Templates.
//            for(Component c:header.getComponents())
//                c.setEnabled(true);
            
            line=GRID_DATA.getRowCount();
            export.setEnabled(true);state.setText("ready");
            progress.setValue(100);
            progress.setForeground(Color.GREEN);
            progress.setString("Done");
            visible(true);
            export.setEnabled(true);
            progress.setVisible(false);
            count.setText(line+" rows");
            first.setText(qty_f+" pieces");
            secondlabel.setText(qty_b+" boxes");
            initiated=true;
            if(this.isCancelled()){
            progress.setVisible(true);
            progress.setForeground(Color.red);
            count.setText("Cancelled");
            first.setText("Cancelled");
            secondlabel.setText("Cancelled");
            state.setText("Cancelled");
           System.out.println("nbr line:"+line);
            }
        }
        
        
        
    }
    
    public Details() {
        initComponents();
        tbm= (DefaultTableModel) GRID_DATA.getModel();
        mostrar();
    }
    private void visible(boolean status){
        header.setEnabled(status);
            for(Component c:header.getComponents()){
                c.setEnabled(status);
            }
    }
    private void fillTable(Object[] data){
        tbm.addRow(data);
    }
    private void mostrar(){
//    
//    tbm.setRowCount(0);
//    liste=new HashSet<>();
//    String requete="select * from PACKING_after_sewing";
//    System.out.println(requete);
//    ResultSet rs=conn.select(requete);
//        try {
//            while(rs.next()){
//                String sku=rs.getString("sku");
//                   String size=sku.substring(sku.lastIndexOf(".")+1).trim();
//                   /*if(rs.getString("description").toLowerCase().contains("yth")||
//                   rs.getString("description").toLowerCase().contains("youth")||
//                   rs.getString("description").toLowerCase().contains("girls"))
//                   size="Y"+size;*/
//                String color,Code;
//               color=rs.getString("coldsp").trim();
//               Code=sku.substring(sku.indexOf(".")+1,sku.lastIndexOf("."));
//             
//                   Object[] data=new Object[11];
//                   data[0]=rs.getString("brand");
//                   data[1]=rs.getString("ponum").trim();
//                   data[2]=rs.getString("style");
//                   data[3]=Code;
//                   data[4]=color;
//                   data[5]=size;
//                   data[6]=rs.getInt("val");
//                   data[7]=rs.getString("created").replace("/", "-").trim();
//                   data[8]=rs.getInt("nb");
//                   data[9]="BLD "+rs.getInt("workcenter");
//                   data[10]=rs.getString("tablepacking");
//                  
//                  liste.add(data);
//                   tbm.addRow(data);
//                   
//            }   } catch (SQLException ex) {
//            Logger.getLogger(Sewing_prod.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
        progress.setIndeterminate(true);
            progress.setString("initiating");
            progress.setStringPainted(true);
            populate=new Populatepack();
        populate.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if(evt.getPropertyName().equals("progress")){
                System.err.println(evt.getPropertyName());
                System.err.println(evt.getNewValue());
                //if((Integer)evt.getNewValue()%20==0)
                    //alerter("reload",data);
                progress.setValue((Integer)evt.getNewValue());
                progress.setString(progress.getValue()+"%");
            }
        });
        populate.execute();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        GRID_DATA = new javax.swing.JTable();
        status = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        state = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        progress = new javax.swing.JProgressBar();
        jLabel15 = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        first = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        secondlabel = new javax.swing.JLabel();
        header = new javax.swing.JPanel();
        export = new javax.swing.JButton();
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
        jLabel7 = new javax.swing.JLabel();
        customer_search = new javax.swing.JTextField();
        table_search = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Packing");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameDeiconified(evt);
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        GRID_DATA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CUSTOMER", "PO", "STYLE", "COLOR CODE", "COLOR", "SIZE", "PACKED", "DATE", "BOX COUNT", "TABLE", "MODULE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GRID_DATA.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(GRID_DATA);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Status:");

        state.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        state.setText("Status:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        progress.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                progressPropertyChange(evt);
            }
        });

        jLabel15.setText("Count:");

        count.setText("0");

        jLabel16.setText("Sum pieces:");

        first.setText("0 Pieces");

        jLabel17.setText("Sum box:");

        secondlabel.setText("0 Boxes");

        javax.swing.GroupLayout statusLayout = new javax.swing.GroupLayout(status);
        status.setLayout(statusLayout);
        statusLayout.setHorizontalGroup(
            statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(count)
                .addGap(74, 74, 74)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(first)
                .addGap(46, 46, 46)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secondlabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel14)
                .addGap(29, 29, 29)
                .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        statusLayout.setVerticalGroup(
            statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(state, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(statusLayout.createSequentialGroup()
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(count)
                    .addComponent(jLabel16)
                    .addComponent(first)
                    .addComponent(jLabel17)
                    .addComponent(secondlabel))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1341, Short.MAX_VALUE)
            .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/export.png"))); // NOI18N
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
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

        jLabel3.setText("DATE:");

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

        jLabel5.setText("SIZE:");

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

        jLabel7.setText("CUSTOMER:");

        customer_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                customer_searchKeyReleased(evt);
            }
        });

        table_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                table_searchActionPerformed(evt);
            }
        });
        table_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table_searchKeyReleased(evt);
            }
        });

        jLabel9.setText("TABLE:");

        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(posearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stylesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sizesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customer_search, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(136, 136, 136)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(datesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(22, 22, 22)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(table_search, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                        .addComponent(refresh)))
                .addGap(10, 10, 10)
                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(export)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(headerLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(stylesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(headerLayout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(colorsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(headerLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(sizesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(headerLayout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(customer_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(table_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(refresh)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeiconified
        // TODO add your handling code here:
        //mostrar();
    }//GEN-LAST:event_formInternalFrameDeiconified

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed
        // TODO add your handling code here:
        JFileChooser file=new JFileChooser("C:/",FileSystemView.getFileSystemView());
        file.setDialogTitle("enregistre le fichier");

        file.setFileFilter(new FileNameExtensionFilter("Workbook excel","xlsx","xls"));
        int returnAct=file.showSaveDialog(this);
        if(returnAct==JFileChooser.APPROVE_OPTION){
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("delivered report");

            //Create some data to build the pivot table on
            setCellData(sheet,GRID_DATA);

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
    }//GEN-LAST:event_exportActionPerformed

    private void posearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_posearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_posearchKeyReleased

    private void stylesearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stylesearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_stylesearchKeyReleased

    private void colorsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_colorsearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_colorsearchKeyReleased

    private void sizesearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sizesearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_sizesearchKeyReleased

    private void customer_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customer_searchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_customer_searchKeyReleased

    private void table_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_searchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_table_searchKeyReleased

    private void table_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_table_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_table_searchActionPerformed

    private void progressPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_progressPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_progressPropertyChange

    private void toPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_toPropertyChange
        // TODO add your handling code here:
        if(initiated)
        try{
            buscados();
        }catch(NullPointerException e){
        }
    }//GEN-LAST:event_toPropertyChange

    private void datesearchPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datesearchPropertyChange
        // TODO add your handling code here:
        if(initiated)
        try{
            buscados();
        }catch(NullPointerException e){
        }
    }//GEN-LAST:event_datesearchPropertyChange

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        mostrar();
    }//GEN-LAST:event_refreshActionPerformed

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
        int line=0;
        int qty_f=0,qty_b=0;
        tbm.setRowCount(0);
        String po=posearch.getText().trim().toLowerCase();
        String style=stylesearch.getText().trim().toLowerCase();
        String color=colorsearch.getText().trim().toLowerCase();
        String size=sizesearch.getText().trim().toLowerCase();
        String client=customer_search.getText().trim().toLowerCase();
        String tablepack=table_search.getText().trim().toLowerCase();
        System.out.println("date:"+datesearch.getDate());
        for(Object[] ob:liste){
            if(datesearch.getDate()!=null){
                if(ob[7]!=null){
                    if(to.getDate()!=null){
                        Date d=new Date();
                        Calendar cal=Calendar.getInstance(TimeZone.getDefault(), Locale.CANADA);
                        cal.setTime(datesearch.getDate());
                        cal.add(Calendar.DAY_OF_MONTH, -1);
                        d=cal.getTime();
            if(ob[1].toString().trim().toLowerCase().contains(po)&&
                    ob[2].toString().trim().toLowerCase().contains(style)&&
                    (ob[3].toString().trim().toLowerCase().contains(color)||
                    ob[4].toString().trim().toLowerCase().contains(color))&&
                    ob[5].toString().trim().toLowerCase().contains(size)&&
                    ob[0].toString().trim().toLowerCase().contains(client)&&
                    ob[9].toString().trim().toLowerCase().contains(tablepack)){
                try {
                    Date dd=formatter.parse(ob[7].toString());
                    if(dd.after(d)&&dd.before(to.getDate()))
                        tbm.addRow(ob);
                    line++;
                    qty_f+=(Integer)ob[6];
                    qty_b+=(Integer)ob[8];
                } catch (ParseException ex) {
                    Logger.getLogger(SOABAR_report.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                
            }
                    }else{
                        if(ob[1].toString().trim().toLowerCase().contains(po)&&
                    ob[2].toString().trim().toLowerCase().contains(style)&&
                    (ob[3].toString().trim().toLowerCase().contains(color)||
                    ob[4].toString().trim().toLowerCase().contains(color))&&
                    ob[5].toString().trim().toLowerCase().contains(size)&&
                    ob[0].toString().trim().toLowerCase().contains(client)&&
                    ob[9].toString().trim().toLowerCase().contains(tablepack)&&
                    ob[7].toString().equals(formatter.format(datesearch.getDate()))){
                    
                tbm.addRow(ob);
                        line++;
                        qty_f+=(Integer)ob[6];
                    qty_b+=(Integer)ob[8];
                        }
                    }
                }
            }else{
                if(ob[1].toString().trim().toLowerCase().contains(po)&&
                    ob[2].toString().trim().toLowerCase().contains(style)&&
                    (ob[3].toString().trim().toLowerCase().contains(color)||
                    ob[4].toString().trim().toLowerCase().contains(color))&&
                    ob[5].toString().trim().toLowerCase().contains(size)&&
                    ob[0].toString().trim().toLowerCase().contains(client)&&
                    ob[9].toString().trim().toLowerCase().contains(tablepack)){
                    
                tbm.addRow(ob);
                line++;
                qty_f+=(Integer)ob[6];
                    qty_b+=(Integer)ob[8];
                }
            }
        }
        count.setText(line+" rows");
            first.setText(qty_f+" pieces");
            secondlabel.setText(qty_b+" boxes");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GRID_DATA;
    private javax.swing.JTextField colorsearch;
    private javax.swing.JLabel count;
    private javax.swing.JTextField customer_search;
    private com.toedter.calendar.JDateChooser datesearch;
    private javax.swing.JButton export;
    private javax.swing.JLabel first;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField posearch;
    private javax.swing.JProgressBar progress;
    private javax.swing.JButton refresh;
    private javax.swing.JLabel secondlabel;
    private javax.swing.JTextField sizesearch;
    private javax.swing.JLabel state;
    private javax.swing.JPanel status;
    private javax.swing.JTextField stylesearch;
    private javax.swing.JTextField table_search;
    private com.toedter.calendar.JDateChooser to;
    // End of variables declaration//GEN-END:variables
}
