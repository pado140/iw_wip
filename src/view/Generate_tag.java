 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Padovano
 */
public class Generate_tag extends javax.swing.JInternalFrame {

    private ConnectionDb conn = ConnectionDb.instance();
    private Set<Object[]> liste;
    /**
     * Creates new form Generate_tag
     */
    public Generate_tag() {
        initComponents();
        jTextField1.setText("");
        liste=new LinkedHashSet<>();
        mostrardatos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupmenu = new javax.swing.JPopupMenu();
        separation = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grid_data = new javax.swing.JTable();

        separation.setText("Generate Ticket");
        separation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                separationActionPerformed(evt);
            }
        });
        popupmenu.add(separation);

        setClosable(true);
        setIconifiable(true);

        jTextField1.setText("jTextField1");
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grid_data.setAutoCreateRowSorter(true);
        grid_data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Client", "PO", "Markename", "PLYS", "cut_id", "marker"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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
        if (grid_data.getColumnModel().getColumnCount() > 0) {
            grid_data.getColumnModel().getColumn(0).setMinWidth(0);
            grid_data.getColumnModel().getColumn(0).setMaxWidth(0);
            grid_data.getColumnModel().getColumn(1).setMinWidth(200);
            grid_data.getColumnModel().getColumn(1).setMaxWidth(200);
            grid_data.getColumnModel().getColumn(2).setMinWidth(100);
            grid_data.getColumnModel().getColumn(2).setMaxWidth(180);
            grid_data.getColumnModel().getColumn(4).setMinWidth(50);
            grid_data.getColumnModel().getColumn(4).setMaxWidth(80);
            grid_data.getColumnModel().getColumn(5).setMinWidth(0);
            grid_data.getColumnModel().getColumn(5).setMaxWidth(0);
            grid_data.getColumnModel().getColumn(6).setMinWidth(0);
            grid_data.getColumnModel().getColumn(6).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void grid_dataMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grid_dataMouseReleased
        // TODO add your handling code here:
        if(evt.getButton()==MouseEvent.BUTTON3){
            if(grid_data.getSelectedRow()<0){
                JOptionPane.showInternalMessageDialog(this, "please select a row");
                return;
            }
                
            popupmenu.show(grid_data,evt.getX(),evt.getY());
        }
    }//GEN-LAST:event_grid_dataMouseReleased

    private void separationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_separationActionPerformed
        // TODO add your handling code here:
        separation_dyelot dyelot=new separation_dyelot(new JFrame(), false,(Integer)grid_data.getValueAt(grid_data.getSelectedRow(), 5),(Integer)grid_data.getValueAt(grid_data.getSelectedRow(), 4)
                ,(Integer)grid_data.getValueAt(grid_data.getSelectedRow(), 6));
        dyelot.setModal(true);
        dyelot.setVisible(true);
    }//GEN-LAST:event_separationActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        DefaultTableModel tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setRowCount(0);
        for(Object[] o:liste){
            if(o[2].toString().toLowerCase().contains(jTextField1.getText().trim().toLowerCase())||o[3].toString().toLowerCase().contains(jTextField1.getText().trim().toLowerCase()))
                tbm.addRow(o);
                
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void mostrardatos(){
        liste.clear();
               DefaultTableModel tbm = (DefaultTableModel) grid_data.getModel();
        tbm.setRowCount(0);
               String query = "SELECT * from cut_simple";
                try {
               ResultSet rs = conn.select(query);
               
               while (rs.next()){ 
                       Object[] ob=new Object[7];
                       ob[0]=rs.getInt("mrk_id");
                       ob[1]=rs.getString("brand");
                       ob[2]=rs.getString("po");
                       ob[3]=rs.getString("mrkname");
                       ob[4]=rs.getInt("mrkpused");
                       ob[5]=rs.getInt("cut_id");
                       ob[6]=rs.getInt("marker");
                       liste.add(ob);
                       tbm.addRow(ob);
               }
           } catch (SQLException ex) {
               Logger.getLogger(Bundle.class.getName()).log(Level.SEVERE, null, ex);
           }
          
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable grid_data;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPopupMenu popupmenu;
    private javax.swing.JMenuItem separation;
    // End of variables declaration//GEN-END:variables
}