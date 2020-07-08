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
import java.util.stream.Collectors;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import observateurs.Observateurs;
import observateurs.Observe;

/**
 *
 * @author Padovano
 */
public class AddColorStyleFabric extends javax.swing.JDialog implements Observe,Observateurs{

    private final ConnectionDb conn = ConnectionDb.instance();
    List<Observateurs> observateurs;
    private Vector color,fcolor;
    private HashMap<Integer,Vector> style;
    private final Map<String,Integer> typeselect=new HashMap<>();
    
    /**
     * Creates new form AddColorStyleFabric
     */
    public AddColorStyleFabric(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        observateurs=new ArrayList<>();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stylefield = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        stylesearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        colorfield = new javax.swing.JTextField();
        colorsearch = new javax.swing.JButton();
        type = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fabricfield = new javax.swing.JTextField();
        fcolorsearch = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CONFIGURATION COLOR");

        jLabel1.setText("Select style");

        stylesearch.setText("jButton1");
        stylesearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stylesearchActionPerformed(evt);
            }
        });

        jLabel2.setText("Select Color");

        colorsearch.setText("jButton1");
        colorsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorsearchActionPerformed(evt);
            }
        });

        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Select Type");

        jLabel4.setText("Select Fabric Color");

        fcolorsearch.setText("jButton1");
        fcolorsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fcolorsearchActionPerformed(evt);
            }
        });

        jButton4.setText("SAVE COLOR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(stylefield, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(stylesearch, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(colorfield, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(colorsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(fabricfield, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(fcolorsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stylefield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stylesearch)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(colorfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colorsearch))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fabricfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fcolorsearch))))
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String erreur="";
        if(!style.isEmpty()&&!color.isEmpty()&&!fcolor.isEmpty()&&type.getSelectedIndex()>0){
            String query="insert into color_master(proto_id,type_id,col_id,ref_id) values(?,?,?,?)";
            style.forEach((key,value)->{
                conn.Update(query, 1, key,typeselect.get(type.getSelectedItem().toString()),color.get(0),fcolor.get(0));
            });
        }
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void stylesearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stylesearchActionPerformed
        // TODO add your handling code here
        style=null;
        Style_info as=new Style_info(new JFrame(), true);
        as.ajouterObservateur(this);
        as.setVisible(true);
        
    }//GEN-LAST:event_stylesearchActionPerformed

    private void colorsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorsearchActionPerformed
        // TODO add your handling code here:
        color=null;
        Color col=new Color(new JFrame(), true);
        col.ajouterObservateur(this);
        this.ajouterObservateur(col);
        alerter("color");
        col.setVisible(true);
    }//GEN-LAST:event_colorsearchActionPerformed

    private void fcolorsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fcolorsearchActionPerformed
        // TODO add your handling code here:
        fcolor=null;
        Color col=new Color(new JFrame(), true);
        col.ajouterObservateur(this);
        this.ajouterObservateur(col);
        alerter("fcolor");
        col.setVisible(true);
    }//GEN-LAST:event_fcolorsearchActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField colorfield;
    private javax.swing.JButton colorsearch;
    private javax.swing.JTextField fabricfield;
    private javax.swing.JButton fcolorsearch;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField stylefield;
    private javax.swing.JButton stylesearch;
    private javax.swing.JComboBox<String> type;
    // End of variables declaration//GEN-END:variables

    @Override
    public void ajouterObservateur(Observateurs ob) {
        observateurs.add(ob);
    }

    @Override
    public void retirerObservateur(Observateurs ob) {
        observateurs.remove(ob);
    }

    @Override
    public void alerter(Object... ob) {
        observateurs.forEach(obs->{
            obs.executer(ob);
        });
    }

    @Override
    public void executer(Object... obs) {
        if(obs[0].equals("style-select")){
            style=(HashMap)obs[1];
            List<String> st=style.values().stream().map(v->{return "'"+v.get(1).toString()+"'";}).collect(Collectors.toList());
            stylefield.setText(String.join("/", st).replace("'", ""));
            type.setModel(new DefaultComboBoxModel<String>( loadType(String.join(",", st))));
            System.out.println("style:"+style);
        }
        if(obs[0].equals("color-create")){
            color=(Vector)obs[1];
            colorfield.setText(String.join("-", color.subList(1, color.size())));
            System.out.println("color:"+color);
        }
        if(obs[0].equals("fcolor-create")){
            fcolor=(Vector)obs[1];
            fabricfield.setText(String.join("-", fcolor.subList(1, fcolor.size())));
            System.out.println("fcolor:"+fcolor);
        }
    }
    
    private String[] loadType(String style){
        typeselect.clear();
        Set<String> liste=new HashSet<>();
        try{
        
          String query = "select DISTINCT type_id, type from stylemaster where STYLE  in("+style+")";
              System.out.println("okkokk");
        
          ResultSet rs = conn.select(query);
          
          int i=0;
          
          liste.add("--");
           while (rs.next()){
              liste.add(rs.getString("type"));
              typeselect.put(rs.getString("type"),rs.getInt("type_id"));
           }   
    
    }catch (SQLException e){
        System.out.println(e); 
        
        }
        return liste.toArray(new String[liste.size()]);
    }
}