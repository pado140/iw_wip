/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Padovano
 */
public class AQL extends javax.swing.JInternalFrame {

    private ConnectionDb conn = ConnectionDb.instance();
    private Map<Integer,Map<String , Integer>> defts;
    private List<String> defectName;
    private String[] columnName;
    private Set<Object[]> listeData;
    private DefaultTableModel tbm;
    private fillData loader;
    
    class fillData extends SwingWorker<Void,Object[]>{
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
            defect_name();
            defects();
            title(defectName);
            listeData=new HashSet<>();
            String requete="select * from audit_report";
            ResultSet rs = conn.select(requete);
            int ij=0;
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
                    int i=0;
                    Map<String,Integer> v=defts.get(auditId);
                    for(String d:v.keySet()){
                        if(v.get(d)!=null)
                            data[14+i]=v.get(d);
                        i++;
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
        protected void process(List<Object[]> chunks) {
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
    public AQL() {
        initComponents();
        init();
    }
    
    private void init(){
        
        
//        grid.addPropertyChangeListener(new PropertyChangeListener() {
//
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                System.out.println(evt.getNewValue());
//            }
//        });
        loader=new fillData();
        progression.setIndeterminate(true);
            progression.setString("initiating");
            progression.setStringPainted(true);
        loader.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            if(evt.getPropertyName().equals("progress")){
                System.err.println(evt.getPropertyName());
                System.err.println(evt.getNewValue());
                //if((Integer)evt.getNewValue()%20==0)
                    //alerter("reload",data);
                progress.setValue((Integer)evt.getNewValue());
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
        String req="select distinct DEFECT_CODE from audit_defect_summary";
        ResultSet rs=conn.select(req);
        try {
            while(rs.next()){
                defectName.add(rs.getString(1));
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
        grid_data = new javax.swing.JScrollPane();
        grid = new javax.swing.JTable();
        status4 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        state4 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        progression = new javax.swing.JProgressBar();
        jLabel39 = new javax.swing.JLabel();
        count4 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        first4 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        secondlabel4 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        except4 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        orderlabel4 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("AQL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );

        grid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
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

        jLabel39.setText("Count:");

        count4.setText("0");

        jLabel40.setText("Sum First:");

        first4.setText("0 Pieces");

        jLabel41.setText("Sum Second:");

        secondlabel4.setText("0 Pieces");

        jLabel42.setText("Sum Exception:");

        except4.setText("0 Pieces");

        jLabel43.setText("Sum Order:");

        orderlabel4.setText("0 Pieces");

        javax.swing.GroupLayout status4Layout = new javax.swing.GroupLayout(status4);
        status4.setLayout(status4Layout);
        status4Layout.setHorizontalGroup(
            status4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(status4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(count4)
                .addGap(74, 74, 74)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(first4)
                .addGap(46, 46, 46)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(secondlabel4)
                .addGap(40, 40, 40)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(except4)
                .addGap(41, 41, 41)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orderlabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
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
            .addGroup(status4Layout.createSequentialGroup()
                .addGroup(status4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(status4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel43)
                        .addComponent(orderlabel4))
                    .addGroup(status4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(except4))
                    .addGroup(status4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel39)
                        .addComponent(count4)
                        .addComponent(jLabel40)
                        .addComponent(first4)
                        .addComponent(jLabel41)
                        .addComponent(secondlabel4)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(grid_data)
            .addComponent(status4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(grid_data, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(status4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void progressionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_progressionPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_progressionPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel count;
    private javax.swing.JLabel count1;
    private javax.swing.JLabel count2;
    private javax.swing.JLabel count3;
    private javax.swing.JLabel count4;
    private javax.swing.JLabel except;
    private javax.swing.JLabel except1;
    private javax.swing.JLabel except2;
    private javax.swing.JLabel except3;
    private javax.swing.JLabel except4;
    private javax.swing.JLabel first;
    private javax.swing.JLabel first1;
    private javax.swing.JLabel first2;
    private javax.swing.JLabel first3;
    private javax.swing.JLabel first4;
    private javax.swing.JTable grid;
    private javax.swing.JScrollPane grid_data;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel orderlabel;
    private javax.swing.JLabel orderlabel1;
    private javax.swing.JLabel orderlabel2;
    private javax.swing.JLabel orderlabel3;
    private javax.swing.JLabel orderlabel4;
    private javax.swing.JProgressBar progress;
    private javax.swing.JProgressBar progress1;
    private javax.swing.JProgressBar progress2;
    private javax.swing.JProgressBar progress3;
    private javax.swing.JProgressBar progression;
    private javax.swing.JLabel secondlabel;
    private javax.swing.JLabel secondlabel1;
    private javax.swing.JLabel secondlabel2;
    private javax.swing.JLabel secondlabel3;
    private javax.swing.JLabel secondlabel4;
    private javax.swing.JLabel state;
    private javax.swing.JLabel state1;
    private javax.swing.JLabel state2;
    private javax.swing.JLabel state3;
    private javax.swing.JLabel state4;
    private javax.swing.JPanel status;
    private javax.swing.JPanel status1;
    private javax.swing.JPanel status2;
    private javax.swing.JPanel status3;
    private javax.swing.JPanel status4;
    // End of variables declaration//GEN-END:variables

    private String[] title(List<String> title){
        String[] titre=new String[]{"Audit No","Date Audit","Batch No","PO","STYLE","COLOR","CUSTOMER","Box count","Pieces Count","Cartons pulled","Pieces reviewed","Result","Auditor","Defects"};
        List<String> list=Arrays.asList(titre);
        list.addAll(title);
        columnName=list.toArray(new String[list.size()]);
        return columnName;
    }
}
