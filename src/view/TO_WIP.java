/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connection.ConnectionDb;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import observateurs.Observateurs;
import observateurs.Observe;

/**
 *
 * @author Padovano
 */
public class TO_WIP extends javax.swing.JDialog implements Observateurs,Observe{

    private ConnectionDb conn = ConnectionDb.instance();
    private Object[][] o;
    private Observateurs observa=new lot(null,false);
    /**
     * Creates new form TO_WIP
     */
    public TO_WIP(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.ajouterObservateur(observa);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mod = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        mod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please choose module", "MOD 1", "MOD 1A", "MOD 1B", "MOD 2", "MOD 2A", "MOD 2B", "MOD 3", "MOD 4", "MOD 5", "MOD 6", "MOD 7", "MOD 8", "MOD 9", "MOD 10", "MOD 11", "MOD 12", "MOD 13", "MOD 14", "MOD 15", "MOD 16", "MOD 17", "MOD 18", "MOD 19", "MOD 20", "MOD 21", "MOD 22", "MOD 23", "MOD 24", "MOD 25", "MOD 26", "MOD 27", "MOD 28", "MOD 29", "MOD 30", "MOD 31", "MOD 32", "MOD 33", "MOD 34", "MOD 35", "MOD 36", "MOD 37", "MOD 38", "MOD 39", "MOD 40", "MOD 41", "MOD 42", "MOD 43", "MOD 44", "MOD 45", "MOD 46", "MOD 47", "MOD 48", "MOD 49", "MOD 50", "SAMPLE ROOM" }));

        jButton1.setText("SEND");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose Module");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(mod, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mod, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int nb(String ordernum){
        String requete="select count(*) n from sew_start where order_num=?";
        ResultSet rs=conn.select(requete, ordernum);
        try {
            while(rs.next()){
                return rs.getInt("n")+1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TO_WIP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
         String requete="insert into sew_start(order_num,SEWING_TRAVElLER,stickers,QTY,module,user_id) values(?,?,?,?,?,?)";
        String requete1="insert into TRANSAC(TRANSACT,ITEM,QTY,ACT_TYPE,ACT_NAME,user_id) values ('at mod',?,?,2,?,?)";
        
        boolean check=true;
        if(mod.getSelectedIndex()>0){
            int confirm=JOptionPane.showConfirmDialog(this, "are you sure to enter this lot in the "+mod.getSelectedItem().toString().trim(), 
                    "confirmation alert", JOptionPane.YES_NO_OPTION);
            if(confirm==JOptionPane.YES_OPTION){
            for(int i=0;i<o.length;i++){
            //int no=nb(o[i][6].toString().trim());
            if(this.conn.Update(requete, 1, new Object[]{o[i][6].toString().trim(),o[i][7],o[i][8].toString().trim(),
                o[i][5],mod.getSelectedItem().toString().trim(),Principal.user_id
            })){
                //o[i][6]=o[i][6].toString().trim()+no;
                o[i][7]=this.conn.getLast();
                if(!this.conn.Update(requete1, 1, new Object[]{o[i][8],o[i][5],mod.getSelectedItem().toString().trim(),Principal.user_id})){
                    System.err.println(conn.getErreur());
                }
                
        }else{
                JOptionPane.showMessageDialog(null, "error");
            check=false;
            }
            }
            }
            else
                check=false;
                this.dispose();
            //}
                if(check){
                JOptionPane.showMessageDialog(null, "Lot issued to module :"+mod.getSelectedItem().toString().trim());
            alerter("generate lot",o);
        ((JDialog)observa).setModal(true);
        ((JDialog)observa).setVisible(true);
                }   
            
        }else
            JOptionPane.showMessageDialog(null, "please choose a valid value");
                                           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        mod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Please choose module", "MOD 1","MOD 2", "MOD 3", "MOD 4", "MOD 5", "MOD 6", "MOD 7", "MOD 8", "MOD 9", "MOD 10", "MOD 11", "MOD 12", "MOD 13", "MOD 14", "MOD 15", "MOD 16", "MOD 17", "MOD 18", "MOD 19", "MOD 20", "MOD 21"
                ,"MOD 22", "MOD 23", "MOD 24", "MOD 25", "MOD 26","MOD 27","MOD 28","MOD 29","MOD 30","MOD 31","MOD 32","MOD 33","MOD 34","MOD 35","MOD 36","MOD 37",
                "MOD 38","MOD 39","MOD 40","MOD 41","MOD 42","MOD 43","MOD 44","MOD 45","MOD 46","MOD 47","MOD 48","MOD 49","MOD 50","SAMPLE ROOM" }));
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(TO_WIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TO_WIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TO_WIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TO_WIP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TO_WIP dialog = new TO_WIP(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox mod;
    // End of variables declaration//GEN-END:variables

    @Override
    public void executer(Object... obs) {
        if(obs[0].toString().equals("issued"))
            o=(Object[][])obs[1];
        
    }

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
}
