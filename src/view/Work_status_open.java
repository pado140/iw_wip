/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import connection.ConnectionDb;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
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
 * @author Duvers
 */
public class Work_status_open extends javax.swing.JInternalFrame {

    private ConnectionDb conn = ConnectionDb.instance();
    private Map<String , Integer> prod;
    private Map<String, String> ref=null;
    private Map<String, Integer> sewn,packed,plan,second,ship,soa,padprint,sew,sewing;
    private Set<Object[]> listeData,temp;
    private Map<String,Map<String,Map<String,Map<String,Integer>>>> cut_pro=new HashMap<>();
    private PopulateTable populate;
    private DefaultTableModel tbm;
    /**
     * Creates new form Work_status_
     */
    
    class PopulateTable extends SwingWorker<Void,Object[]>{
        int tot=0,line=0;
        int cut=0,aso=0,apad=0,first_tot=0,tplan=0,i=0,exception=0;
        int ij=0,qty_f=0,qty_e=0,qty_s=0;
        
        
        public PopulateTable(){
            //this.commande=commande;
            header.setEnabled(false);
            for(Component c:header.getComponents())
                c.setEnabled(false);
            //for(Component c:content.getComponents())
                //c.setEnabled(false);
            //content.setEnabled(false);
            progress.setVisible(true);
            export.setEnabled(false);
            state.setText("initiating");
            
        }

        @Override
        protected Void doInBackground(){
            //progress.setString("0%");
            count.setText("Waiting...");
            first.setText("Waiting...");
            secondlabel.setText("Waiting...");
            except.setText("Waiting...");
            
            data_cut();
            initPlan();
            
            //load();
            progress.setIndeterminate(false);
        state.setText("loading...");    
        listeData=new HashSet<>();
        String requete="select * from work_process where state<>'5'";
        ResultSet rs = conn.select(requete);
        int planqty=0,prodqty=0;
        
        
        try {
            rs.last();
            line=rs.getRow();
            rs.beforeFirst();
            while(rs.next()){
                String sku=rs.getString("sku").trim();
               String status="cut not start yet";
                String size=rs.getString("size");
                String desc=rs.getString("description").trim(),
                        po=rs.getString("po").trim(),
                        order=rs.getString("work_order").trim();
                
                if(!rs.getString("brand").equals("CHS")){
                if(desc.toLowerCase().contains("yth")||desc.toLowerCase().contains("youth")||
                        desc.toLowerCase().contains("girls")||desc.toLowerCase().contains("boys"))
                size="Y"+size;
                }
                int sobar=rs.getInt("at_sb");
                int pp=rs.getInt("at_pp");
                int se=rs.getInt("at_sew");
                int mod=rs.getInt("at_mod");
                int first=rs.getInt("sewn");
                int second=rs.getInt("second");
                int secondpost=rs.getInt("post_sewing")+rs.getInt("second_ps");
                int pack=rs.getInt("packed");
                int shipped=rs.getInt("shipped");
                int cutting=0;
                int bal=rs.getInt("qty")-shipped;
                exception=rs.getInt("exception");
                try{
                    planqty=plan.get(order);
                }catch(NullPointerException ex){
                    planqty=0;
                }
                try{
                    prodqty=prod.get(order);
                }catch(NullPointerException ex1){
                    prodqty=0;
                }
                if(prodqty>0){
                cutting=planqty-prodqty;
                planqty-=(prodqty+cutting);
                }
                prodqty-=sobar;
                sobar-=pp;
                pp-=se;
                se-=mod;
                mod-=(first+second+exception);
                first-=pack-secondpost;
                pack-=shipped;
                
                Object[] data=new Object[28];
                data[1]=rs.getString("brand");
                data[2]=po;
                data[3]=rs.getString("style").trim();
                data[4]=sku.substring(sku.indexOf(".")+1, sku.lastIndexOf("."));
                data[5]=rs.getString("color").trim();
                data[6]=size.trim();
                data[8]=rs.getInt("qty");
                
                data[7]=po+"-"+sku;
                data[10]=order;
                
                data[11]=planqty;
                data[12]=cutting;
                data[13]=prodqty;
                data[14]=sobar;
                data[15]=pp;
                data[16]=se;
                data[17]=mod;
                data[18]=first;
                data[19]=second;
                data[20]=secondpost;
                data[21]=0;
                data[22]=exception;
                data[23]=pack;
                data[24]=shipped;
                data[25]=bal;
                         
                        
                        
                            cut+=prodqty;
                            aso+=sobar;
                            apad+=pp;
                            first_tot+=first;
                            tplan+=planqty;
                            qty_f+=first;
                            qty_e+=exception;
                            qty_s+=second;
                            
                        
               data[9]=rs.getDate("last_production");
                data[26]=sku;
                data[27]=status;
                data[0]=rs.getDate("shipdate");
                ij++;
                setProgress((int)Math.ceil(ij*100/line));
                listeData.add(data);
                tbm.addRow(data);
               tot+=rs.getInt("qty");;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Work_status.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
            return null;
        }

        @Override
        protected void process(List<Object[]> chunks) {
            super.process(chunks); //To change body of generated methods, choose Tools | Templates.
            grid_data.notify();
            
        }

        @Override
        protected void done() {
            super.done(); //To change body of generated methods, choose Tools | Templates.
            for(Component c:header.getComponents())
                c.setEnabled(true);
            
            export.setEnabled(true);
            progress.setVisible(false);
            count.setText(line+" rows");
            first.setText(qty_f+" pieces");
            secondlabel.setText(qty_s+" pieces");
            orderlabel.setText(tot+" pieces");
            except.setText(qty_e+" pieces");
            state.setText("Ready");
            if(this.isCancelled()){
            progress.setVisible(true);
            progress.setForeground(Color.red);
            count.setText("Cancelled");
            first.setText("Cancelled");
            secondlabel.setText("Cancelled");
            except.setText("Cancelled");
            state.setText("Cancelled");
           System.out.println("nbr line:"+line);
            }
        }
        
        
        
    }
    
    /**
     * Creates new form Work_status
     */
    public Work_status_open() {
        initComponents();
        
        init();
        
    }

   
    private void initPlan(){
        String requete="select * from [plan1]";
        plan =new HashMap<>();
        ResultSet rs=conn.select(requete);
        try {
            while(rs.next()){
                //System.out.println("sku plan:"+rs.getString("po").trim().concat(".").concat(rs.getString("sku")).trim());
              plan.put(rs.getString("order").trim(),rs.getInt("pieces"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Work_status.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void init(){
        
        prod=new HashMap<>();
        tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setNumRows(0);
        grid_data.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println(evt.getNewValue());
            }
        });
        initpopcolumn();
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
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        POPUP = new javax.swing.JPopupMenu();
        DETAILS = new javax.swing.JMenuItem();
        column_menu = new javax.swing.JPopupMenu();
        header = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
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
        first = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        secondlabel = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        except = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        orderlabel = new javax.swing.JLabel();
        export = new javax.swing.JButton();

        DETAILS.setText("Close SKU");
        DETAILS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DETAILSActionPerformed(evt);
            }
        });
        POPUP.add(DETAILS);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Summary");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setText("PO FILTER");

        jLabel2.setText("STYLE FILTER");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jLabel3.setText("SIZE FILTER");

        jLabel4.setText("COLOR FILTER");

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jButton1.setText("refresh");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel5.setText("CLIENT FILTER");

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jLabel13.setText("SKU Filter");

        jButton3.setText("Column");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel5)
                        .addGap(119, 119, 119)
                        .addComponent(jLabel13)
                        .addGap(222, 222, 222)
                        .addComponent(jButton1))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                        .addComponent(jLabel5)
                        .addComponent(jLabel13))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        grid_data.setAutoCreateRowSorter(true);
        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "X_FACTORY", "CUSTOMER", "PO NUM", "STYLE", "CODE COLOR", "COLOR", "SIZE", "SKU", "QTY", "LAST PRODUCTION DATE", "WORK ORDER", "READY TO CUT", "CUTTING", "CUT", "AT SOBAR", "PAD PRINT", "AT SEWING", "SEW START", "FIRST", "SECOND", "OTFQPS", "SCRAP", "EXCEPTION", "PACKING", "SHIPPED", "BALANCE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grid_data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                grid_dataMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(grid_data);

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

        jLabel16.setText("Sum First:");

        first.setText("0 Pieces");

        jLabel17.setText("Sum Second:");

        secondlabel.setText("0 Pieces");

        jLabel18.setText("Sum Exception:");

        except.setText("0 Pieces");

        jLabel19.setText("Sum Order:");

        orderlabel.setText("0 Pieces");

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
                .addGap(40, 40, 40)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(except)
                .addGap(41, 41, 41)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderlabel)
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
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(orderlabel))
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(except))
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(count)
                        .addComponent(jLabel16)
                        .addComponent(first)
                        .addComponent(jLabel17)
                        .addComponent(secondlabel)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/export.png"))); // NOI18N
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(export)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        // TODO add your handling code here:
        //listData();
    }//GEN-LAST:event_formInternalFrameActivated

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void grid_dataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grid_dataMouseReleased
        // TODO add your handling code here:
        if(Principal.group.equals("Merchandizing")||Principal.group.equals("MIS")||
                Principal.group.equals("Planing")){
           
        if(evt.getButton()==MouseEvent.BUTTON3){
            if(!grid_data.getSelectionModel().isSelectionEmpty())
            POPUP.show(grid_data,evt.getX(),evt.getY());
            else
                JOptionPane.showMessageDialog(this, "Please select a row!", "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
        }
    }//GEN-LAST:event_grid_dataMouseReleased

    private void DETAILSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DETAILSActionPerformed
        // TODO add your handling code here:
        for(int i=0;i<grid_data.getSelectedRowCount();i++){
            int ligne=grid_data.getSelectedRows()[i];
        //grid_data.getValueAt(ligne, 0);
        changeStatus(grid_data.getValueAt(ligne, 10).toString());
            
        
        }
    }//GEN-LAST:event_DETAILSActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
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
            XSSFSheet sheet = wb.createSheet("Work Status");

            //Create some data to build the pivot table on
            setCellData(sheet,grid_data);

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

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        buscar(); 
    }//GEN-LAST:event_jTextField6KeyReleased

    private void progressPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_progressPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_progressPropertyChange

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        column_menu.show(jButton3, jButton3.getX(), jButton3.getY()+jButton3.getHeight());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void buscar(){
        int tot=0,line=0;
        int sec=0,ex=0,apad=0,f=0,tplan=0,i=0;
        String potxt=jTextField1.getText().trim().toLowerCase();
        String style=jTextField2.getText().trim().toLowerCase();
        String col=jTextField3.getText().trim().toLowerCase();
        String size=jTextField4.getText().trim().toLowerCase();
        String client=jTextField5.getText().trim().toLowerCase();
         String sku=jTextField6.getText().trim().toLowerCase();
        
        String data="";
        
        DefaultTableModel tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setRowCount(0);
        for(Object[] o:listeData){
            if(o[2].toString().toLowerCase().contains(potxt.trim().toLowerCase())&& o[3].toString().toLowerCase().contains(style.trim().toLowerCase())&&
                    (o[5].toString().toLowerCase().contains(col.trim())||o[4].toString().toLowerCase().contains(col.trim()))&&
                    o[6].toString().toLowerCase().contains(size.trim())&&o[1].toString().toLowerCase().contains(client)&&o[7].toString().toLowerCase().contains(sku)
               ){
                tot+=Integer.parseInt(o[8].toString());
                sec+=Integer.parseInt(o[19].toString());
                ex+=Integer.parseInt(o[20].toString());
                f+=Integer.parseInt(o[18].toString());
                line++;
                tbm.addRow(o);
            }
        }
        count.setText(line+" rows");
            first.setText(f+" pieces");
            secondlabel.setText(sec+" pieces");
            orderlabel.setText(tot+" pieces");
            except.setText(ex+" pieces");
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
    
    private void initpopcolumn(){
        for(int i=0;i<grid_data.getColumnCount();i++){
            String colname=grid_data.getColumnName(i);
            JCheckBoxMenuItem menu=new JCheckBoxMenuItem(colname);
            menu.setActionCommand(colname);
            menu.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent e) {
                    changeColumn(((JCheckBoxMenuItem)e.getItem()).getActionCommand(), ((JCheckBoxMenuItem)e.getItem()).isSelected());
                }
            });
            menu.setSelected(true);
            column_menu.add(menu);
        }
    }
    private void changeColumn(String col, boolean state){
        //grid_data.setVisible(false);
        SwingUtilities.invokeLater(new Thread() {

            @Override
            public void run() {
                if(state){
                    
                    grid_data.getColumn(col).sizeWidthToFit();
                }else{
                    System.out.println(state);
                    grid_data.getColumn(col).setMinWidth(0);
                    grid_data.getColumn(col).setMaxWidth(0);
                }
                
            }
        });
//        if(state)
//            grid_data.getColumn(col).sizeWidthToFit();
//        else{
//            grid_data.getColumn(col).setMinWidth(0);
//            grid_data.getColumn(col).setMaxWidth(0);
//        }
//        grid_data.revalidate();
//        grid_data.setVisible(true);
    }
    private Map<String,Integer> type(){
        Map<String,Integer> typ=new HashMap<>();
        String requete="select ordnum_10,count(type_id) n from data_cut2 where type_id NOT IN(4,6,7) group by ordnum_10";
        ResultSet rs=conn.select(requete);
        try {
            while(rs.next()){
                typ.put(rs.getString("ordnum_10").trim(),rs.getInt("n"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ready_to_sew.class.getName()).log(Level.SEVERE, null, ex);
        }
        return typ;
    }
    private Map<String,Integer> listType(){
        String query="select Distinct style,count(distinct[TYPE]) t FROM StyleMaster where type_id not in(4,6,7) group by style";
        ResultSet rs=conn.select(query);
        Map<String,Integer> lissty=new HashMap<>();
        try {
            while(rs.next()){
                String sty=rs.getString("style").trim();
                lissty.put(sty, rs.getInt("t"));
                       
            }
        } catch (SQLException ex) {
               //Logger.getLogger(Cansew.class.getName()).log(Level.SEVERE, null, ex);
           }
        return lissty;
    }
    private void data_cut(){
                 Map<String,Integer> listType=listType();
                 Map<String,Integer> typecount=type();
               Map<String,Integer> production=new HashMap<>();
               ref=new HashMap<>();
               String query = "SELECT * from SUM_CUT1";
                try {
               ResultSet rs = conn.select(query);
               
               while (rs.next()){
                       production.put(rs.getString("ORDNUM_10").trim(), rs.getInt("qty"));
                       ref.put(rs.getString("ORDNUM_10").trim(),rs.getString("style"));
               }
           } catch (SQLException ex) {
               Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           for(String order:production.keySet()){
               int value=0;
               try{
                   value=production.get(order);
                   }catch(NullPointerException e){
                       value=0;
                   }
               
               String style=ref.get(order);
               
               int typ=0,ty=0;
               try{
                   typ=listType.get(style.trim());
                   
               }catch(NullPointerException e){
                   typ=0;
               }
               
               try{
                   ty=typecount.get(order);
               }catch(NullPointerException e){
                   ty=0;
               }
               if(order.contains("30015")){
               System.out.println("type2:"+typecount.get(order));
               System.out.println("type:"+typ);
               }
               if(ty==typ)
               prod.put(order, value);
           }
    }
    
    private boolean changeStatus(String ordnum){
        String requete="update shoporder set status_147='5' where ordnum_147=?";
        return conn.Update(requete, 0, ordnum);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem DETAILS;
    private javax.swing.JPopupMenu POPUP;
    private javax.swing.JPopupMenu column_menu;
    private javax.swing.JLabel count;
    private javax.swing.JLabel except;
    private javax.swing.JButton export;
    private javax.swing.JLabel first;
    private javax.swing.JTable grid_data;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel orderlabel;
    private javax.swing.JProgressBar progress;
    private javax.swing.JLabel secondlabel;
    private javax.swing.JLabel state;
    private javax.swing.JPanel status;
    // End of variables declaration//GEN-END:variables
}
