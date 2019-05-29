/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.toedter.calendar.JDateChooser;
import connection.ConnectionDb;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import observateurs.Observateurs;
import observateurs.Observe;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Padovano
 */
public class WIPDAILLY extends javax.swing.JInternalFrame implements Observe,Observateurs{
private ConnectionDb conn = ConnectionDb.instance();
private Set<Object[]> lisData;
private Object[][] data;
private boolean initiated;
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
private task task;
    
    private class task extends SwingWorker<Void,Object>{

        int total,qty_f,qty_s,qty_e;
        String commande;

        public task(String commande) {
            System.out.println("new task");
            this.commande=commande;
            initiated=false;
            
            progress.setVisible(true);
            export.setEnabled(false);
            state.setText("initiating");
        }
        
        @Override
        protected Void doInBackground() {
            int i=0;
            visible(false);
            lisData=new HashSet<>();
            progress.setIndeterminate(false);
            progress.setString("0%");
            count.setText("Waiting...");
            first.setText("Waiting...");
            second.setText("Waiting...");
            except.setText("Waiting...");
            state.setText("loading...");
        String requete="select * from daily_prod";
    System.out.println(requete);
    ResultSet rs=conn.select(requete);
        try {
            rs.last();
            
            total=rs.getRow();
            qty_f=0;
            qty_e=0;
            qty_s=0;
            data=new Object[total][15];
            System.out.println(total);
            rs.beforeFirst();
            while(rs.next()){
                String sku=rs.getString("sku");
                   String size=rs.getString("size");
                   
                String color,Code="";
               color=rs.getString("color").trim();
               Code=sku.substring(sku.indexOf(".")+1,sku.lastIndexOf("."));
               System.out.println(Code);
                   Object[] datas=new Object[15];
                   datas[2]=rs.getString("Brand");
                   datas[3]=rs.getString("po");
                   datas[4]=rs.getString("style");
                   datas[12]=rs.getInt("first")+rs.getInt("second")+rs.getInt("exception");
                   datas[5]=Code;
                   datas[6]=color;
                   datas[7]=rs.getString("description");
                   datas[8]=size;
                   datas[1]="BLD "+rs.getInt("workcenter")+"- "+rs.getString("mod");
                   datas[0]=rs.getDate("date");
                   datas[9]=rs.getInt("first");
                   datas[10]=rs.getInt("second");
                   datas[11]=rs.getInt("exception") ;
                   datas[13]=rs.getString("order_num");
                   datas[14]=rs.getString("stickers");
                   data[i]=datas;
                   lisData.add(datas);
                    setProgress((int)Math.ceil(100*i/total));
                  qty_f+=rs.getInt("first");
                  qty_e+=rs.getInt("exception") ;
                  qty_s+=rs.getInt("second");
                   i++;
            }   } catch (SQLException ex) {
            Logger.getLogger(Sewing_prod.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                     
            return null;
        }

        @Override
        protected void process(List<Object> chunks) {
            //super.process(chunks); //To change body of generated methods, choose Tools | Templates.
            System.out.println(chunks.size());
        }
        
        
        @Override
        protected void done() {
            super.done();
           
            System.err.println("isCanceled"+this.isCancelled());
            System.err.println("state:"+this.getState().toString());
            state.setText("ready");
            progress.setValue(100);
            progress.setForeground(Color.GREEN);
            progress.setString("Done");
            alerter("reload",data);
            visible(true);
            export.setEnabled(true);
            progress.setVisible(false);
            count.setText(String.valueOf(total));
            first.setText(qty_f+ " Pieces");
            second.setText(qty_s+ " Pieces");
            except.setText(qty_e+ " Pieces");
            initiated=true;
        }
        
        
    }
    /**
     * Creates new form WIPCONTROL
     */
    public WIPDAILLY() {
        initComponents();
        
        inits();
    }
    
    private void visible(boolean status){
        header.setEnabled(status);
            for(Component c:header.getComponents()){
                c.setEnabled(status);
            }
    }

    private void inits(){
        this.ajouterObservateur(this);
        state.setText("initiating");
        progress.setIndeterminate(true);
            progress.setString("initiating");
            progress.setStringPainted(true);
        task=new task("load");
        task.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if(evt.getPropertyName().equals("progress")){
                System.err.println(evt.getPropertyName());
                System.err.println(evt.getNewValue());
                if((Integer)evt.getNewValue()%20==0)
                    alerter("reload",data);
                progress.setValue((Integer)evt.getNewValue());
                progress.setString(progress.getValue()+"%");
            }
        });
        task.execute();
    }
    private void refresh(){
        progress.setIndeterminate(true);
            progress.setString("initiating");
            task=new task("load");
        task.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if(evt.getPropertyName().equals("progress")){
                System.err.println(evt.getPropertyName());
                System.err.println(evt.getNewValue());
                if((Integer)evt.getNewValue()%02==0)
                    alerter("reload",data);
                progress.setValue((Integer)evt.getNewValue());
                progress.setString(progress.getValue()+"%");
            }
        });
        task.execute();
    }
    
    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop = new javax.swing.JPopupMenu();
        release = new javax.swing.JMenuItem();
        header = new javax.swing.JPanel();
        refresh = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        colorsearch = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        sizesearch = new javax.swing.JTextField();
        posearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        stylesearch = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        datesearch = new com.toedter.calendar.JDateChooser();
        to = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        custSearch = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        locateSearch = new javax.swing.JTextField();
        content = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        GRID_DATA = new javax.swing.JTable();
        export = new javax.swing.JButton();
        status = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        state = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        progress = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        first = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        second = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        except = new javax.swing.JLabel();

        release.setText("Release");
        release.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                releaseActionPerformed(evt);
            }
        });
        pop.add(release);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("WIP details report");
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

        refresh.setText("REFRESH");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jLabel5.setText("SIZE");

        jLabel6.setText("COLOR:");

        colorsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                colorsearchKeyReleased(evt);
            }
        });

        jLabel1.setText("PO FILTER:");

        sizesearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sizesearchKeyReleased(evt);
            }
        });

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

        jLabel7.setText("CUSTOMER:");

        custSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                custSearchKeyReleased(evt);
            }
        });

        jLabel8.setText("LOCATION:");

        locateSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                locateSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(29, 29, 29)
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
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(datesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(22, 22, 22)
                        .addComponent(to, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(custSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                .addComponent(refresh)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(sizesearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(custSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(headerLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(locateSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(refresh))
                .addGap(0, 22, Short.MAX_VALUE))
        );

        GRID_DATA.setAutoCreateRowSorter(true);
        GRID_DATA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Date", "MODULE", "CUSTOMER", "PO", "STYLE", "COLOR_CODE", "COLOR", "DESCRIPTION", "SIZE", "FIRST", "SEGOND", "EXCEPTION", "TOTAL PRODUCED", "WORK ORDER", "sticker"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        GRID_DATA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                GRID_DATAMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(GRID_DATA);
        if (GRID_DATA.getColumnModel().getColumnCount() > 0) {
            GRID_DATA.getColumnModel().getColumn(14).setMinWidth(0);
            GRID_DATA.getColumnModel().getColumn(14).setPreferredWidth(0);
            GRID_DATA.getColumnModel().getColumn(14).setMaxWidth(0);
        }

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        export.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/export.png"))); // NOI18N
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Status:");

        state.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        state.setText("Status:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel10.setText("Count:");

        count.setText("0");

        jLabel11.setText("Sum First:");

        first.setText("0 Pieces");

        jLabel12.setText("Sum Second:");

        second.setText("0 Pieces");

        jLabel13.setText("Sum Exception:");

        except.setText("0 Pieces");

        javax.swing.GroupLayout statusLayout = new javax.swing.GroupLayout(status);
        status.setLayout(statusLayout);
        statusLayout.setHorizontalGroup(
            statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(count)
                .addGap(74, 74, 74)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(first)
                .addGap(46, 46, 46)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(second)
                .addGap(40, 40, 40)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(except)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(progress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel9)
                .addGap(29, 29, 29)
                .addComponent(state, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        statusLayout.setVerticalGroup(
            statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(state, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addComponent(progress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(statusLayout.createSequentialGroup()
                .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(except))
                    .addGroup(statusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(count)
                        .addComponent(jLabel11)
                        .addComponent(first)
                        .addComponent(jLabel12)
                        .addComponent(second)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(export, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(export))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            setCellData(sheet,GRID_DATA);

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

    private void formInternalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeiconified
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_formInternalFrameDeiconified

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
        refresh();
    }//GEN-LAST:event_refreshActionPerformed

    private void colorsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_colorsearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_colorsearchKeyReleased

    private void sizesearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sizesearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_sizesearchKeyReleased

    private void posearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_posearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_posearchKeyReleased

    private void stylesearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stylesearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_stylesearchKeyReleased

    private void custSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custSearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_custSearchKeyReleased

    private void locateSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_locateSearchKeyReleased
        // TODO add your handling code here:
        buscados();
    }//GEN-LAST:event_locateSearchKeyReleased

    private void releaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_releaseActionPerformed
        // TODO add your handling code here:
        int option=JOptionPane.showConfirmDialog(this, "Do you really want to release this exception?", "confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option==JOptionPane.YES_OPTION){
        String requete="update sewing_production set type_sew='first' where lot_stickers=? and type_sew='exception'";
        if(conn.Update(requete, 0, GRID_DATA.getValueAt(GRID_DATA.getSelectedRow(), 14))){
            GRID_DATA.setValueAt(Integer.parseInt(GRID_DATA.getValueAt(GRID_DATA.getSelectedRow(), 11).toString())+
                    Integer.parseInt(GRID_DATA.getValueAt(GRID_DATA.getSelectedRow(), 9).toString()), GRID_DATA.getSelectedRow(), 9);
            GRID_DATA.setValueAt(0, GRID_DATA.getSelectedRow(), 11);
            JOptionPane.showMessageDialog(this, "success");
        }else{
            JOptionPane.showMessageDialog(this, "error");
        }
        }
    }//GEN-LAST:event_releaseActionPerformed

    private void GRID_DATAMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GRID_DATAMouseReleased
        // TODO add your handling code here:
        if(evt.getButton()==3){
            if(!GRID_DATA.getSelectionModel().isSelectionEmpty()){
                if(Integer.parseInt(GRID_DATA.getValueAt(GRID_DATA.getSelectedRow(), 11).toString())>0){
                    pop.show(GRID_DATA, evt.getX(), evt.getY());
                }
            }
        }
    }//GEN-LAST:event_GRID_DATAMouseReleased

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
        if(initiated){
        try{
            buscados();
        }catch(NullPointerException e){
        }
        }
    }//GEN-LAST:event_datesearchPropertyChange

    public void setCellData(XSSFSheet sheet,JTable table){
        int colexcept=table.getColumnModel().getColumnIndex("sticker");
        Row row10=sheet.createRow(0);
        for(int j=0;j<table.getColumnCount();j++){
                Cell cells=row10.createCell(j);
                cells.setCellValue(table.getColumnName(j));
            }
        for(int i=0;i<table.getRowCount();i++){
            Row rows=sheet.createRow(1+i);
            Cell cellMar=rows.createCell(0);
            for(int j=0;j<table.getColumnCount();j++){
                if(j!=colexcept){
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
    }
    
    private void buscados(){
         DefaultTableModel tbm = (DefaultTableModel) GRID_DATA.getModel();
               tbm.setRowCount(0);
               int total,qty_f,qty_s,qty_e;
        String po=posearch.getText().trim().toLowerCase();
        String style=stylesearch.getText().trim().toLowerCase();
        String color=colorsearch.getText().trim().toLowerCase();
        String size=sizesearch.getText().trim().toLowerCase();
        String cust=custSearch.getText().trim().toLowerCase();
        String lo=locateSearch.getText().trim().toLowerCase();
        System.out.println("date:"+datesearch.getDate());
        total=0;
        qty_f=0;
        qty_e=0;
        qty_s=0;
        for(Object[] ob:lisData){
            int f=(Integer)ob[9];
            int s=(Integer)ob[10];
            int e=(Integer)ob[11];
            if(datesearch.getDate()!=null){
                if(ob[0]!=null){
                    if(to.getDate()!=null){
                        Date d=new Date();
                        Calendar cal=Calendar.getInstance(TimeZone.getDefault(), Locale.CANADA);
                        cal.setTime(datesearch.getDate());
                        cal.add(Calendar.DAY_OF_MONTH, -1);
                        d=cal.getTime();
            if(ob[3].toString().trim().toLowerCase().contains(po)&&
                    ob[4].toString().trim().toLowerCase().contains(style)&&
                    (ob[5].toString().trim().toLowerCase().contains(color)||
                    ob[6].toString().trim().toLowerCase().contains(color))&&
                    ob[8].toString().trim().toLowerCase().contains(size)&&
                    ob[2].toString().trim().toLowerCase().contains(cust)&&
                    ob[1].toString().trim().toLowerCase().contains(lo)
                    ){
                try {
                    Date dd=formatter.parse(ob[0].toString());
                    if(dd.after(d)&&dd.before(to.getDate())){
                        total+=1;
                        qty_f+=f;
                        qty_e+=e;
                        qty_s+=s;
                        tbm.addRow(ob);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(SOABAR_report.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
                
            }
                    }else{
                        if(ob[3].toString().trim().toLowerCase().contains(po)&&
                    ob[4].toString().trim().toLowerCase().contains(style)&&
                    (ob[5].toString().trim().toLowerCase().contains(color)||
                    ob[6].toString().trim().toLowerCase().contains(color))&&
                    ob[8].toString().trim().toLowerCase().contains(size)&&
                    ob[2].toString().trim().toLowerCase().contains(cust)&&
                    ob[1].toString().trim().toLowerCase().contains(lo)&&
                    ob[0].toString().equals(formatter.format(datesearch.getDate())))
                    
                tbm.addRow(ob);
                        total+=1;
                        qty_f+=f;
                        qty_e+=e;
                        qty_s+=s;
                    }
                }
            }else{
                if(ob[3].toString().trim().toLowerCase().contains(po)&&
                    ob[4].toString().trim().toLowerCase().contains(style)&&
                    (ob[5].toString().trim().toLowerCase().contains(color)||
                    ob[6].toString().trim().toLowerCase().contains(color))&&
                    ob[8].toString().trim().toLowerCase().contains(size)&&
                    ob[2].toString().trim().toLowerCase().contains(cust)&&
                    ob[1].toString().trim().toLowerCase().contains(lo)){
                    
                tbm.addRow(ob);
                total+=1;
                        qty_f+=f;
                        qty_e+=e;
                        qty_s+=s;
                }
            }
        }
        count.setText(String.valueOf(total));
            first.setText(qty_f+ " Pieces");
            second.setText(qty_s+ " Pieces");
            except.setText(qty_e+ " Pieces");
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable GRID_DATA;
    private javax.swing.JTextField colorsearch;
    private javax.swing.JPanel content;
    private javax.swing.JLabel count;
    private javax.swing.JTextField custSearch;
    private com.toedter.calendar.JDateChooser datesearch;
    private javax.swing.JLabel except;
    private javax.swing.JButton export;
    private javax.swing.JLabel first;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField locateSearch;
    private javax.swing.JPopupMenu pop;
    private javax.swing.JTextField posearch;
    private javax.swing.JProgressBar progress;
    private javax.swing.JButton refresh;
    private javax.swing.JMenuItem release;
    private javax.swing.JLabel second;
    private javax.swing.JTextField sizesearch;
    private javax.swing.JLabel state;
    private javax.swing.JPanel status;
    private javax.swing.JTextField stylesearch;
    private com.toedter.calendar.JDateChooser to;
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
        for(Observateurs o:obs)
            o.executer(ob);
    }

    @Override
    public void executer(Object... obs) {
        
        if(obs[0].equals("reload")){
            
            GRID_DATA.setModel(new javax.swing.table.DefaultTableModel(
            (Object[][])obs[1],
            new String [] {
                "Date","MODULE","CUSTOMER", "PO", "STYLE", "COLOR_CODE", "COLOR","DESCRIPTION", "SIZE", "FIRST", "SEGOND", "EXECEPTION","TOTAL PRODUCED",  "WORK ORDER","sticker"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false,false,false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
            GRID_DATA.getColumnModel().getColumn(14).setMinWidth(0);
            GRID_DATA.getColumnModel().getColumn(14).setMaxWidth(0);
        jScrollPane1.setViewportView(GRID_DATA);
        }
    }
}
