/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import admin.util.DateUtils;
import connection.ConnectionDb;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import observateurs.Observateurs;

/**
 *
 * @author Padovano
 */
public class planification extends javax.swing.JInternalFrame implements Observateurs{

    /**
     * Creates new form planification
     */
    private final ConnectionDb conn=ConnectionDb.instance();
    private final DefaultTableModel model,mainModel;
    private JComboBox<String> speed,curveChoice;
    private JComboBox<Double> startEfficiency,endEfficiency;
    public planification() {
        initComponents();
        model=(DefaultTableModel) critereTable.getModel();
        mainModel=(DefaultTableModel) mainTable.getModel();
        speed=new JComboBox<>(new String[]{"STABLE","RAMP UP"});
        curveChoice=new JComboBox<>(new String[]{"CURVE A","CURVE B","CURVE C"});
        startEfficiency=new JComboBox<>();
        endEfficiency=new JComboBox<>();
        speed.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                speedItemStateChanged(e);
            }
        });
        curveChoice.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                curveChoiceItemStateChanged(e);
            }
        });
        startEfficiency.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                startEfficiencyItemStateChanged(e);
            }
        });
        A.add(0.0);
        A.add(18.0);
        A.add(32.0);
        A.add(44.0);
        A.add(55.0);
        A.add(65.0);
        A.add(73.0);
        A.add(80.0);
        A.add(87.0);
        A.add(92.0);
        A.add(96.0);
        A.add(98.0);
        A.add(100.0);
        
        B.add(00.0);
        B.add(13.0);
        B.add(25.0);
        B.add(36.0);
        B.add(45.0);
        B.add(54.0);
        B.add(61.0);
        B.add(68.0);
        B.add(74.0);
        B.add(80.0);
        B.add(85.0);
        B.add(90.0);
        B.add(94.0);
        B.add(97.0);
        B.add(99.0);
        B.add(100.0);
        
        C.add(0.0);
        C.add(10.0);
        C.add(19.0);
        C.add(28.0);
        C.add(37.0);
        C.add(46.0);
        C.add(53.0);
        C.add(60.0);
        C.add(66.0);
        C.add(71.0);
        C.add(77.0);
        C.add(82.0);
        C.add(86.0);
        C.add(90.0);
        C.add(93.0);
        C.add(95.0);
        C.add(97.0);
        C.add(98.0);
        C.add(99.0);
        C.add(100.0);
        startEfficiency.setModel(new DefaultComboBoxModel(A.toArray()));
        endEfficiency.setModel(new DefaultComboBoxModel(A.toArray()));
        initiateCritereTable();
        initiateEfficiency();
    }
    
    private void initiateCritereTable(){
        critereTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(speed));
        critereTable.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(curveChoice));
        critereTable.getColumnModel().getColumn(6).setCellEditor(new DefaultCellEditor(startEfficiency));
        critereTable.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(endEfficiency));
        //model.addTableModelListener(new );
    }
    private final List<Double> A=new LinkedList<>();
    private final List<Double> B=new LinkedList<>();
    private final List<Double> C=new LinkedList<>();
    private DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
    private final Map<String,Integer> efficiency=new HashMap<>();
     
    private void initiateEfficiency(){
        String requete="select * from styleEfficiency";
        ResultSet rs=conn.select(requete);
        efficiency.clear();
        try {
            while(rs.next()){
                efficiency.put(rs.getString("STYFAM").trim(), rs.getInt("qty_per_day"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(planification.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        style = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        critereTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        days = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Planification");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Select Style");

        style.setEditable(false);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        critereTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "X-FACTORY", "CUSTOMER", "STYLE GROUP", "QTY", "SPEED", "CURVE", "START", "END", "PRODUCED"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean check=true;
                if(columnIndex>4){
                    if(getValueAt(rowIndex, 4).equals("STABLE")){
                        if(columnIndex<8)
                        return false;
                    }else{
                        if(columnIndex==8)
                        return false;
                    }
                    check=true;
                }
                return canEdit [columnIndex]&&check;
            }
        });
        critereTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                critereTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(critereTable);

        jLabel2.setText("WORKS DAY:");

        days.setModel(new javax.swing.SpinnerNumberModel(5, 1, 7, 1));

        jLabel3.setText("DAYS");

        jButton2.setText("generate plan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(style, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addGap(175, 175, 175)
                .addComponent(jButton2)
                .addGap(91, 91, 91))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton1)
                            .addComponent(style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "X-FACTORY"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(mainTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        AllStyles as=new AllStyles(new JFrame(), true);
        as.ajouterObservateur(this);
        as.setVisible(true);
        System.out.println("ok");
        as.retirerObservateur(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       mainModel.setColumnCount(1);
       mainModel.setRowCount(0);
         if(style.getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(this, "you must choose the familly style first");
             return;
         }else{
             Date max=new Date();
             Date min=DateUtils.getWeek(new Date()).getFromDate();
             try {
                 max=df.parse(model.getValueAt(0, 0).toString());
                 System.out.println("max before:"+max);
                 for(int i=1;i<model.getRowCount();i++){
                     Date d=df.parse(model.getValueAt(i, 0).toString());
                     System.out.println("date:"+d.after(max));
                     if(d.after(max))
                         max=d;
                 }
                 max=DateUtils.getWeek(max).getFromDate();
                 Date m=min;
                 System.out.println("max after:"+max);
                 while(m.compareTo(max)<=0){
                      System.out.println("m before:"+m);
                     mainModel.addColumn(df.format(m));
                     Calendar cal=Calendar.getInstance();
                     cal.setTime(m);
                     cal.add(Calendar.DATE, 7);
                     m=cal.getTime();
                 }
             } catch (ParseException ex) {
                 Logger.getLogger(planification.class.getName()).log(Level.SEVERE, null, ex);
             }
             for(int i=0;i<model.getRowCount();i++){
                 int qtyPerDay=0,qty=0,qtyOrder=0;
                 try{
                     qtyOrder=(Integer)model.getValueAt(i, 3);
                     qtyPerDay=(Integer)model.getValueAt(i, 8);
                     qty=Integer.parseInt(days.getValue().toString())*qtyPerDay;
                 }catch(NullPointerException e){
                     JOptionPane.showMessageDialog(this, "qty per day must greater than 0");
                     //return; 
                 }
                 //mainModel.addColumn(model.getValueAt(i, 0));
                 mainModel.addRow(new Object[]{model.getValueAt(i, 0)});
             }
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void critereTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_critereTableMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_critereTableMouseReleased

    private void curveChoiceItemStateChanged(java.awt.event.ItemEvent evt) {                                             
        // TODO add your handling code here:
        boolean check=false;
        if(evt.getStateChange()==ItemEvent.SELECTED){
            check=true;
            if(evt.getItem().equals("CURVE A")){
                startEfficiency.setModel(new DefaultComboBoxModel(A.toArray()));
                endEfficiency.setModel(new DefaultComboBoxModel(A.toArray()));
                
            }
            if(evt.getItem().equals("CURVE B")){
                startEfficiency.setModel(new DefaultComboBoxModel(B.toArray()));
                endEfficiency.setModel(new DefaultComboBoxModel(B.toArray()));
            }
            if(evt.getItem().equals("CURVE C")){
                startEfficiency.setModel(new DefaultComboBoxModel(C.toArray()));
                endEfficiency.setModel(new DefaultComboBoxModel(C.toArray()));
            }
            model.setValueAt("0.0", critereTable.getSelectedRow(),6);
            model.setValueAt(endEfficiency.getItemAt(9), critereTable.getSelectedRow(),7);
            model.setValueAt("0.0", critereTable.getSelectedRow(),8);

        }
    }                                            
    private void startEfficiencyItemStateChanged(java.awt.event.ItemEvent evt) {                                             
        // TODO add your handling code here:
        double value=0;
        if(evt.getStateChange()==ItemEvent.SELECTED){
            value=efficiency.getOrDefault(model.getValueAt(critereTable.getSelectedRow(), 2).toString().trim(),0)*Double.parseDouble(evt.getItem().toString())/100;
            model.setValueAt(value, critereTable.getSelectedRow(), 8);
        }
    }                                            

    private void speedItemStateChanged(java.awt.event.ItemEvent evt) {                                       
        // TODO add your handling code here:

        boolean check=false;
        if(evt.getStateChange()==ItemEvent.SELECTED){

            if(evt.getItem().equals("RAMP UP")){
                check=true;
                curveChoice.setEnabled(check);
                startEfficiency.setEnabled(check);
                endEfficiency.setEnabled(check);
                model.setValueAt("CURVE A", critereTable.getSelectedRow(),5);
                model.setValueAt("0.0", critereTable.getSelectedRow(),6);
                model.setValueAt(endEfficiency.getItemAt(9), critereTable.getSelectedRow(),7);
                model.setValueAt("0.0", critereTable.getSelectedRow(),8);

                System.out.println(evt.getStateChange());
            }
            else{
                model.setValueAt(null, critereTable.getSelectedRow(),5);
                model.setValueAt(null, critereTable.getSelectedRow(),6);
                model.setValueAt(null, critereTable.getSelectedRow(),7);
                model.setValueAt(null, critereTable.getSelectedRow(),8);
            }
                critereTable.revalidate();
            }
    }
    private void fillTable(String style){
        model.setRowCount(0);
        String requete="select shipdate,styfam,brand,sum(pieces) pieces from styleplan where styfam=? group by shipdate,styfam,brand";
        ResultSet rs=conn.select(requete, style);
        try{
            while(rs.next()){
                model.addRow(new Object[]{rs.getString("shipdate"),rs.getString("brand"),rs.getString("Styfam"),rs.getInt("pieces"),"STABLE"});
            }
        }catch(SQLException e){
            
        }
    }
    
    private void initMainTable(){
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable critereTable;
    private javax.swing.JSpinner days;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable mainTable;
    private javax.swing.JTextField style;
    // End of variables declaration//GEN-END:variables

    @Override
    public void executer(Object... obs) {
        if(obs[0].equals("select-style")){
            System.out.println(obs);
            Vector v=(Vector)obs[1];
            fillTable(v.get(0).toString());
            style.setText(v.get(0).toString());
        }
    }
    
}
