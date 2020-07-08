/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;
import observateurs.Observateurs;

/**
 *
 * @author Padovano
 */
public class productionPlan extends javax.swing.JInternalFrame implements Observateurs{

    /**
     * Creates new form productionPlan
     */
    private final ConnectionDb conn=ConnectionDb.instance();
    private final DefaultTableModel model,mainModel;
    private JComboBox<String> speed,curveChoice;
    private JComboBox<Double> startEfficiency,endEfficiency;
    private JComboBox<Integer> workdays;
    private String poselected;
    
    public productionPlan() {
        initComponents();
        model=(DefaultTableModel) modcritere.getModel();
        mainModel=(DefaultTableModel) modcritere.getModel();
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
        modcritere.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(speed));
        modcritere.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(curveChoice));
        modcritere.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(startEfficiency));
        modcritere.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(endEfficiency));
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pofield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        modules = new javax.swing.JSpinner();
        searchPo = new javax.swing.JButton();
        family = new javax.swing.JComboBox<>();
        xfact = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        modcritere = new javax.swing.JTable();

        setBackground(new java.awt.Color(250, 250, 250));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Create Production Plan");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("MAIN DETAILS"));

        jLabel1.setText("PO NUM");

        jLabel2.setText("FAMILY");

        jLabel3.setText("X-FACT");

        jLabel4.setText("MODULE(S)");

        modules.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        modules.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                modulesStateChanged(evt);
            }
        });

        searchPo.setText("jButton1");
        searchPo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPoActionPerformed(evt);
            }
        });

        family.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        xfact.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pofield, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(searchPo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(family, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(xfact, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(modules, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(pofield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchPo)
                                .addComponent(modules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(family, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(xfact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("PRODUCTION SPEED"));

        modcritere.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "MOD", "SPEED", "CURVE", "START", "END", "DAYS WORKED", "MODULE CAPACITY"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            boolean[] canEdit = new boolean [] {
                true, true, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                boolean check=true;
                if(columnIndex>2 && columnIndex<=5){
                    check=false;
                    if(!getValueAt(rowIndex, 2).equals("STABLE"))
                    check=true;
                }
                return canEdit [columnIndex]&&check;
            }
        });
        jScrollPane1.setViewportView(modcritere);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(232, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchPoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPoActionPerformed
        // TODO add your handling code here:
        poselected=null;
        PoPlan po=new PoPlan(new JFrame(), true);
        po.ajouterObservateur(this);
        po.setVisible(true);
    }//GEN-LAST:event_searchPoActionPerformed

    private void modulesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_modulesStateChanged
        // TODO add your handling code here:
        model.setRowCount(Integer.parseInt(modules.getValue().toString()));
    }//GEN-LAST:event_modulesStateChanged

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
            model.setValueAt("0.0", modcritere.getSelectedRow(),3);
            model.setValueAt(endEfficiency.getItemAt(9), modcritere.getSelectedRow(),4);
            model.setValueAt("0.0", modcritere.getSelectedRow(),6);

        }
    }                                            
    private void startEfficiencyItemStateChanged(java.awt.event.ItemEvent evt) {                                             
        // TODO add your handling code here:
        double value=0;
        if(evt.getStateChange()==ItemEvent.SELECTED){
            value=efficiency.getOrDefault("style",0)*Double.parseDouble(evt.getItem().toString())/100;
            model.setValueAt(value, modcritere.getSelectedRow(), 6);
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
                model.setValueAt("CURVE A", modcritere.getSelectedRow(),2);
                model.setValueAt("0.0", modcritere.getSelectedRow(),3);
                model.setValueAt(endEfficiency.getItemAt(9), modcritere.getSelectedRow(),4);
                model.setValueAt("0.0", modcritere.getSelectedRow(),6);

                System.out.println(evt.getStateChange());
            }
            else{
                model.setValueAt(null, modcritere.getSelectedRow(),2);
                model.setValueAt(null, modcritere.getSelectedRow(),3);
                model.setValueAt(null, modcritere.getSelectedRow(),4);
                model.setValueAt(null, modcritere.getSelectedRow(),6);
            }
                modcritere.revalidate();
            }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> family;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable modcritere;
    private javax.swing.JSpinner modules;
    private javax.swing.JTextField pofield;
    private javax.swing.JButton searchPo;
    private javax.swing.JComboBox<String> xfact;
    // End of variables declaration//GEN-END:variables

    private class render extends JLabel implements ListCellRenderer{

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    @Override
    public void executer(Object ... data){
        if(data[0].equals("poplan")){
            pofield.setText(data[1].toString());
            poselected=data[1].toString();
            Set<String> dataStyle=(Set)data[3];
            family.setModel(new DefaultComboBoxModel<String>(dataStyle.toArray(new String[dataStyle.size()])));
        }
    }
}
