/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Padovano
 */
public class iconRenderer extends DefaultTableCellRenderer{
    private final ImageIcon VERIFY_IMAGE = new ImageIcon(getClass().getResource("/view/icon/icons8_Verified_Account_20px.png"));
    private final ImageIcon ALERT_IMAGE = new ImageIcon(getClass().getResource("/view/icon/icons8_Clock_20px.png"));
    private final ImageIcon ALERT_out = new ImageIcon(getClass().getResource("/view/icon/icons8_Expired_20px_2.png"));
    private final ImageIcon ALERT_PROCESSING = new ImageIcon(getClass().getResource("/view/icon/icons8_Sync_24px_1.png"));
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Object[] val=new Object[16];
        for(int i=0;i<16;i++){
            val[i]=table.getValueAt(row, i);
        }
        JLabel label=new JLabel(){
            
            private JToolTip createTooltip(){
                JToolTip tip=new JToolTip(){
            @Override
            public void paintComponent(Graphics gd){
                Graphics2D g=(Graphics2D)gd;
                g.setColor(new java.awt.Color(74,144,248));
                
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                g.setColor(Color.black);
                g.setFont(new Font("Arial black",Font.BOLD,11));
                g.drawString("Adresse:", 10 , 18);
                g.drawString("Departement:", 10 , 51);
                g.drawString("Arrondissement:", 10 , 88);
                g.drawString("Commune:", 10 , 121);
                g.drawString("Section Communale:", 10 , 154);
                g.setFont(new Font("sansSerif",Font.BOLD,12));
                g.drawString(val[10].toString(), 10 , 33);
                
            }
        }; //To change body of generated methods, choose Tools | Templates.
        tip.setForeground(Color.red);
        tip.setPreferredSize(new Dimension(200,200));
        tip.setBackground(Color.BLUE);
        tip.setOpaque(true);
        
        return tip;
            }
        };
        
        if(value.equals("Completed"))
            label.setIcon(VERIFY_IMAGE);
        if(value.equals("deadline soon")){
            label.setIcon(ALERT_IMAGE);
        }
        if(value.equals("past due")){
            label.setIcon(ALERT_out);
            
        }
        if(value.equals("In Process")){
            label.setIcon(ALERT_PROCESSING);
            
        }
        
        return label;
    }
    
}
