/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import connection.ConnectionDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import observateurs.Observateurs;
import observateurs.Observe;

/**
 *
 * @author Padovano
 */
public class Style_info extends javax.swing.JDialog implements Observateurs,Observe{
    private final ConnectionDb conn = ConnectionDb.instance();
    private final List<Observateurs> observateurs=new ArrayList<>();
    private final Map<Integer,Vector> selected=new HashMap<>();
    private final Map<String,Vector> datas=new HashMap<>();
    private DefaultTableModel tbm ;
    
    public Style_info(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tbm = (DefaultTableModel) grid_style.getModel();
        mostrarDatos();
        txt_search.setText(null);
    }

    
    
    
    public void mostrarDatos(){
       tbm.setRowCount(0);
      try{
        
          String query = "select DISTINCT proto_id, style,DESCRIPTion,brand,proto from proto_style";   
          
          ResultSet rs = conn.select(query);
         
           while (rs.next()){
            tbm.addRow(new Object[]{ rs.getInt("proto_id"),rs.getString("style"), rs.getString("DESCRIPTion"), rs.getString("proto"),false});
             datas.put(rs.getString("style"),(Vector)tbm.getDataVector().get(tbm.getRowCount()-1));
           }   
    
    }catch (SQLException e){
        System.out.println(e); 
        
        }
    }
    
    
    public void buscarStyle(String txtbox){
        tbm.setRowCount(0);
        String buscar = txtbox.trim();
        for(String key:datas.keySet()){
            if(key.toLowerCase().contains(buscar.toLowerCase()))
                tbm.addRow(datas.get(key));
        }
       }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_style = new javax.swing.JTable();
        txt_search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("List of Style");

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        grid_style.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "STYLE", "DESCRIPTION", "proto", "SELECT", "BRAND"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        grid_style.getTableHeader().setReorderingAllowed(false);
        grid_style.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                grid_styleMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(grid_style);
        if (grid_style.getColumnModel().getColumnCount() > 0) {
            grid_style.getColumnModel().getColumn(0).setMinWidth(0);
            grid_style.getColumnModel().getColumn(0).setMaxWidth(0);
            grid_style.getColumnModel().getColumn(1).setMinWidth(100);
            grid_style.getColumnModel().getColumn(1).setMaxWidth(100);
            grid_style.getColumnModel().getColumn(2).setMinWidth(200);
            grid_style.getColumnModel().getColumn(5).setMinWidth(0);
            grid_style.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        txt_search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_search.setText("jTextField1");
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Search Style");

        jButton1.setText("SELECT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        
        buscarStyle(txt_search.getText());
    }//GEN-LAST:event_txt_searchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
            alerter("style-select",selected);
            this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void grid_styleMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grid_styleMouseReleased
        // TODO add your handling code here:
        int rowselected=grid_style.getSelectedRow();
        int colselected=grid_style.getSelectedColumn();
        if(grid_style.isCellEditable(rowselected, colselected)){
            Object data=grid_style.getValueAt(rowselected, colselected);
            boolean val=Boolean.valueOf(data.toString());
            System.out.println(val);
            datas.replace(grid_style.getValueAt(rowselected, 1).toString(), (Vector)tbm.getDataVector().get(rowselected));
            if(val){
                selected.put(Integer.parseInt(grid_style.getValueAt(rowselected, 0).toString()), (Vector)tbm.getDataVector().get(rowselected));
            }else
                selected.remove(Integer.parseInt(grid_style.getValueAt(rowselected, 0).toString()));
        }
    }//GEN-LAST:event_grid_styleMouseReleased

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Style_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Style_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Style_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Style_info.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Style_info dialog = new Style_info(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable grid_style;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables

    @Override
    public void executer(Object... obs) {
        
    }

    @Override
    public void ajouterObservateur(Observateurs ob) {
        obs.add(ob);
    }

    @Override
    public void retirerObservateur(Observateurs ob) {
        obs.remove(ob);
    }

    @Override
    public void alerter(Object... ob) {
        for(Observateurs o:obs){
            o.executer(ob);
        }
    }
}
