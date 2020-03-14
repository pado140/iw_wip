/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Padovano
 */
public class AuditReport extends javax.swing.JInternalFrame {
    private ConnectionDb conn = ConnectionDb.instance();
    private PopulateTable populate;
    private DefaultTableModel tbm;
    private Set<Object[]> listeData;
    private Map<String,Integer[]> sizes;
    private boolean ready=false;
    /**
     * Creates new form Work_status_
     */
    
    class PopulateTable extends SwingWorker<Void,Object[]>{
        int tot=0,line=0,f=0,p=0;
        int box=0,pie=0;
        
        
        public PopulateTable(){
            progress.setVisible(true);
            state.setText("initiating");
            
        }

        @Override
        protected Void doInBackground(){
            //progress.setString("0%");
            count.setText("Waiting...");
            auditT.setText("Waiting...");
            auditP.setText("Waiting...");
            auditF.setText("Waiting...");
            ready=false;
            
            //load();
            progress.setIndeterminate(false);
        state.setText("loading...");    
        listeData=new HashSet<>();
        String requete="select * from audit_report";
        ResultSet rs = conn.select(requete);
        int cc=0;
        
        
        try {
            rs.last();
            line=rs.getRow();
            rs.beforeFirst();
            while(rs.next()){
                int auditid=rs.getInt("id");
                int batchno=rs.getInt("batch_id");
                String result=rs.getBoolean("result")?"Passed":"Failed";
                String details=rs.getString("details");
                String auditorname=rs.getString("auditor_name");
                String username=rs.getString("username");
                Date created=rs.getDate("created");
                Date date=rs.getDate("date");
                int qty=rs.getInt("piecesbox");
                int boxes=rs.getInt("box_count");
                String cust=rs.getString("customer");
                String po=rs.getString("po");
                Object[] data=new Object[]{auditid,batchno,auditorname,date,boxes,qty,result,details,username,created,cust,po};
                if(rs.getBoolean("result"))
                    p+=qty;
                else
                    f+=qty;
                cc++;
                setProgress((int)Math.ceil(cc*100/line));
                listeData.add(data);
                tbm.addRow(data);
               tot+=qty;;
               box+=boxes;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Work_status.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
            return null;
        }

        @Override
        protected void process(List<Object[]> chunks) {
            super.process(chunks); //To change body of generated methods, choose Tools | Templates.
            //grid_data.notify();
            
        }

        @Override
        protected void done() {
            super.done(); 
            progress.setVisible(false);
            count.setText(line+" rows");
            auditT.setText(tot+" pieces");
            auditP.setText(p+" pieces");
            auditB.setText(box+" boxes");
            auditF.setText(f+" pieces");
            state.setText("Ready");
            
           ready=true;
            grid_data.getSelectionModel().setSelectionInterval(0, 0);
            details(grid_data.getValueAt(0, 0),grid_data.getValueAt(0, 1),
                grid_data.getValueAt(0, 2).toString(),grid_data.getValueAt(0, 8).toString(),
                grid_data.getValueAt(0, 3),grid_data.getValueAt(0, 9),
                grid_data.getValueAt(0, 10),grid_data.getValueAt(0, 11));
            if(this.isCancelled()){
            progress.setVisible(true);
            progress.setForeground(Color.red);
            count.setText("Cancelled");
            auditT.setText("Cancelled");
            auditP.setText("Cancelled");
            auditF.setText("Cancelled");
            state.setText("Cancelled");
           System.out.println("nbr line:"+line);
            }
        }
        
        
        
    }
    /**
     * Creates new form AuditReport
     */
    public AuditReport() {
        initComponents();
        init();
        
            
    }

    private void init(){
    tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setNumRows(0);
        grid_data.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println(evt.getNewValue());
            }
        });
        populate=new PopulateTable();
        progress.setIndeterminate(true);
            progress.setString("initiating");
            progress.setStringPainted(true);
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
        ready=true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        report_no = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        batch_no = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        auditor_name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        FROM = new com.toedter.calendar.JDateChooser();
        TO = new com.toedter.calendar.JDateChooser();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        noreport = new javax.swing.JLabel();
        nobatch = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        customer = new javax.swing.JLabel();
        auditor = new javax.swing.JLabel();
        create = new javax.swing.JLabel();
        enterby = new javax.swing.JLabel();
        enterdate = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        po = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        defect_table = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();
        status = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        state = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        progress = new javax.swing.JProgressBar();
        jLabel15 = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        auditT = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        auditP = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        auditF = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        auditB = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Report Outgoing Audit");

        report_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                report_noKeyReleased(evt);
            }
        });

        jLabel1.setText("REPORT NO");

        jLabel2.setText("BATCH NO");

        batch_no.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                batch_noKeyReleased(evt);
            }
        });

        jLabel3.setText("DATE FROM");

        jLabel4.setText("AUDITOR");

        auditor_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                auditor_nameKeyReleased(evt);
            }
        });

        jButton1.setText("refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("DATE TO");

        FROM.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                FROMPropertyChange(evt);
            }
        });

        TO.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TOPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(report_no, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(batch_no, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(auditor_name, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FROM, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(387, 387, 387)
                        .addComponent(jButton1))
                    .addComponent(TO, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(report_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(batch_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(auditor_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(FROM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jSplitPane1.setDividerLocation(800);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Report No:");

        noreport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        noreport.setText("Report No:");

        nobatch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nobatch.setText("No");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Batch No:");

        customer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        customer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        customer.setText("  Customer");
        customer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Customer", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        auditor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        auditor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        auditor.setText("  Audited by");
        auditor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Audited by", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        create.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        create.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        create.setText("  Created on");
        create.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Created on", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        enterby.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        enterby.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        enterby.setText("  Entered by");
        enterby.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Entered by", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        enterdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        enterdate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        enterdate.setText("  Entered Date");
        enterdate.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Entered date", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel37.setText("SUMMARY");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Box Sticker", "SKU", "QTY", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane3.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setMinWidth(80);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(3).setMinWidth(100);
            jTable1.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Sizes", "Qty Box", "Pieces"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setRowHeight(20);
        jScrollPane4.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(2).setMinWidth(80);
            jTable2.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        po.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        po.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        po.setText("  PO #");
        po.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "PO #", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        defect_table.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        defect_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DEFECT CODE", "DEFO/DEFECT", "QTY", "OPERATION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        defect_table.setRowHeight(20);
        defect_table.getTableHeader().setResizingAllowed(false);
        defect_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(defect_table);
        if (defect_table.getColumnModel().getColumnCount() > 0) {
            defect_table.getColumnModel().getColumn(0).setMinWidth(100);
            defect_table.getColumnModel().getColumn(0).setMaxWidth(100);
            defect_table.getColumnModel().getColumn(2).setMinWidth(50);
            defect_table.getColumnModel().getColumn(2).setMaxWidth(50);
        }

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel38.setText("DEFECTS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(noreport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nobatch)
                .addGap(53, 53, 53))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(po, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(auditor, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(create, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enterby, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enterdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel38)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addGap(23, 23, 23))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(noreport)
                    .addComponent(jLabel20)
                    .addComponent(nobatch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(po, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(auditor, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(create, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enterdate, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enterby, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        jSplitPane1.setRightComponent(jPanel1);

        grid_data.setAutoCreateRowSorter(true);
        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "REPORT NO", "BATCH NO", "AUDITOR", "DATE", "BOX IN BATCH", "TOTAL PIECES", "RESULT", "RESULT DETAILS", "USER", "CREATED", "cust", "po"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grid_data.getTableHeader().setReorderingAllowed(false);
        grid_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grid_dataMouseClicked(evt);
            }
        });
        grid_data.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                grid_dataPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(grid_data);
        if (grid_data.getColumnModel().getColumnCount() > 0) {
            grid_data.getColumnModel().getColumn(10).setMinWidth(0);
            grid_data.getColumnModel().getColumn(10).setMaxWidth(0);
            grid_data.getColumnModel().getColumn(11).setMinWidth(0);
            grid_data.getColumnModel().getColumn(11).setMaxWidth(0);
        }

        jSplitPane1.setLeftComponent(jScrollPane1);

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

        jLabel16.setText("Total Audit:");

        auditT.setText("0 Pieces");

        jLabel17.setText("Total Passed:");

        auditP.setText("0 Pieces");

        jLabel18.setText("Total failed:");

        auditF.setText("0 Pieces");

        jLabel19.setText("Total Boxes:");

        auditB.setText("0 Pieces");

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
                .addComponent(auditT, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(auditP)
                .addGap(40, 40, 40)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(auditF)
                .addGap(41, 41, 41)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(auditB)
                .addGap(435, 435, 435)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel14)
                .addGap(29, 29, 29)
                .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statusLayout.setVerticalGroup(
            statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(state, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(statusLayout.createSequentialGroup()
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(auditB))
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(auditF))
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(count)
                        .addComponent(jLabel16)
                        .addComponent(auditT)
                        .addComponent(jLabel17)
                        .addComponent(auditP)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1)
            .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1)
                .addGap(2, 2, 2)
                .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void report_noKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_report_noKeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_report_noKeyReleased

    private void batch_noKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_batch_noKeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_batch_noKeyReleased

    private void auditor_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_auditor_nameKeyReleased
        // TODO add your handling code here:
        if(ready)
        buscar();
    }//GEN-LAST:event_auditor_nameKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        init();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void progressPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_progressPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_progressPropertyChange

    private void grid_dataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grid_dataMouseClicked
        // TODO add your handling code here:
        details(grid_data.getValueAt(grid_data.getSelectedRow(), 0),grid_data.getValueAt(grid_data.getSelectedRow(), 1),
                grid_data.getValueAt(grid_data.getSelectedRow(), 2).toString(),grid_data.getValueAt(grid_data.getSelectedRow(), 8).toString(),
                grid_data.getValueAt(grid_data.getSelectedRow(), 3),grid_data.getValueAt(grid_data.getSelectedRow(), 9),
                grid_data.getValueAt(grid_data.getSelectedRow(), 10),grid_data.getValueAt(grid_data.getSelectedRow(), 11));
    }//GEN-LAST:event_grid_dataMouseClicked

    private void grid_dataPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_grid_dataPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_grid_dataPropertyChange

    private void FROMPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FROMPropertyChange
        // TODO add your handling code here:
        if(ready && listeData!=null)
        buscar();
    }//GEN-LAST:event_FROMPropertyChange

    private void TOPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TOPropertyChange
        // TODO add your handling code here:
        if(ready && listeData!=null)
        buscar();
    }//GEN-LAST:event_TOPropertyChange

    private void buscar(){
        tbm.setNumRows(0);
        int tot=0,line=0,f=0,p=0;
        int box=0,pie=0;
        for(Object[] dataRow : listeData){
                String rep=dataRow[0].toString().toLowerCase();
                String bat=dataRow[1].toString().toLowerCase();
                String auditor=dataRow[2].toString().toLowerCase();
                DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
                Date date=(Date)dataRow[3];
                int qty=Integer.parseInt(dataRow[5].toString());
                int boxes=Integer.parseInt(dataRow[4].toString());
            
                if(FROM.getDate()!=null){
                            Calendar cal=Calendar.getInstance(TimeZone.getDefault(), Locale.CANADA);
                            cal.setTime(FROM.getDate());
                            cal.add(Calendar.DAY_OF_MONTH, -1);
                            Date d=cal.getTime();
                            Date dd=null;
                            
                    if(dataRow[3]!=null){
                        try {
                                dd=dateformat.parse(dataRow[3].toString());
                            } catch (ParseException ex) {
                                Logger.getLogger(SOABAR_report.class.getName()).log(Level.SEVERE, null, ex);
                            }   
                        if(TO.getDate()!=null){
                            if(rep.contains(report_no.getText()) && bat.contains(batch_no.getText())
                            && auditor.contains(auditor_name.getText().trim().toLowerCase()) && dd.after(d) && dd.before(TO.getDate())){
                                tbm.addRow(dataRow);
                                tot+=qty;
                                line++;
                                p+=dataRow[6].toString().equalsIgnoreCase("Passed")?qty:0;
                                f+=dataRow[6].toString().equalsIgnoreCase("Failed")?qty:0;
                                box+=boxes;
                            }   
                        }else{
                            if(rep.contains(report_no.getText()) && bat.contains(batch_no.getText())
                            && auditor.contains(auditor_name.getText().trim().toLowerCase()) && dd.equals(d)){
                                tbm.addRow(dataRow);
                                tot+=qty;
                                line++;
                                p+=dataRow[6].toString().equalsIgnoreCase("Passed")?qty:0;
                                f+=dataRow[6].toString().equalsIgnoreCase("Failed")?qty:0;
                                box+=boxes;
                            }   
                        }
                    }
                }else{
                if(rep.contains(report_no.getText()) && bat.contains(batch_no.getText())
                        && auditor.contains(auditor_name.getText().trim().toLowerCase())){
                        tbm.addRow(dataRow);
                        tot+=qty;
                                line++;
                                p+=dataRow[6].toString().equalsIgnoreCase("Passed")?qty:0;
                                f+=dataRow[6].toString().equalsIgnoreCase("Failed")?qty:0;
                                box+=boxes;
                 }
                }
        }
        grid_data.getSelectionModel().setSelectionInterval(0, 0);
            details(grid_data.getValueAt(0, 0),grid_data.getValueAt(0, 1),
                grid_data.getValueAt(0, 2).toString(),grid_data.getValueAt(0, 8).toString(),
                grid_data.getValueAt(0, 3),grid_data.getValueAt(0, 9),
                grid_data.getValueAt(0, 10),grid_data.getValueAt(0, 11));
        count.setText(line+" rows");
            auditT.setText(tot+" pieces");
            auditP.setText(p+" pieces");
            auditB.setText(box+" boxes");
            auditF.setText(f+" pieces");
    }
    
    private void details(Object idreport,Object idbatch,String auditord,String userd,Object created,Object enter,Object cus,Object pod){
        noreport.setText(""+idreport);
        nobatch.setText(""+idbatch);
        customer.setText(""+cus);
        po.setText(""+pod);
        auditor.setText(""+auditord);
        create.setText(""+created);
        enterby.setText(""+userd);
        enterdate.setText(""+enter);
        detail(Integer.parseInt(idreport.toString()));
        detaildefect(Integer.parseInt(idreport.toString()));
        
    }
    
    private void detail(int idreport){
     String requete="select * from audit_lpn_batch where audit_id=?";
        ResultSet rs = conn.select(requete,idreport);;
        DefaultTableModel tablemodel=(DefaultTableModel)jTable1.getModel();
        DefaultTableModel tablemodel2=(DefaultTableModel)jTable2.getModel();
        tablemodel.setNumRows(0);
        tablemodel2.setNumRows(0);
        sizes=new LinkedHashMap<>();
        try {
            while(rs.next()){
                tablemodel.addRow(new Object[]{rs.getString("box_stickers"),rs.getString("prtnum_10").trim(),rs.getInt("qty"),rs.getString("status")});
                String size=rs.getString("prtnum_10").trim();
                size=size.substring(size.lastIndexOf(".")+1);
                if(sizes.keySet().contains(size)){
                    sizes.get(size)[0]++;
                    sizes.get(size)[1]+=rs.getInt("qty");
                }else
                    sizes.put(size, new Integer[]{1,rs.getInt("qty")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Work_status.class.getName()).log(Level.SEVERE, null, ex);
        }
        sizes.forEach((s,k)->{
            tablemodel2.addRow(new Object[]{s,k[0],k[1]});
        });
        }
    private void detaildefect(int idreport){
     String requete="select * from defect_by_audit where audit_id=?";
        ResultSet rs = conn.select(requete,idreport);;
        DefaultTableModel tablemodel=(DefaultTableModel)defect_table.getModel();
        tablemodel.setNumRows(0);
        try {
            while(rs.next()){
                tablemodel.addRow(new Object[]{rs.getString("defect_code"),rs.getString("DEFECT").trim(),rs.getInt("qty"),rs.getString("operation")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Work_status.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser FROM;
    private com.toedter.calendar.JDateChooser TO;
    private javax.swing.JLabel auditB;
    private javax.swing.JLabel auditF;
    private javax.swing.JLabel auditP;
    private javax.swing.JLabel auditT;
    private javax.swing.JLabel auditor;
    private javax.swing.JTextField auditor_name;
    private javax.swing.JTextField batch_no;
    private javax.swing.JLabel count;
    private javax.swing.JLabel create;
    private javax.swing.JLabel customer;
    private javax.swing.JTable defect_table;
    private javax.swing.JLabel enterby;
    private javax.swing.JLabel enterdate;
    private javax.swing.JTable grid_data;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel nobatch;
    private javax.swing.JLabel noreport;
    private javax.swing.JLabel po;
    private javax.swing.JProgressBar progress;
    private javax.swing.JTextField report_no;
    private javax.swing.JLabel state;
    private javax.swing.JPanel status;
    // End of variables declaration//GEN-END:variables
}
