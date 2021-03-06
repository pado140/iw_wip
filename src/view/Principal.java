/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import Load_data.ChsOrder;
import Load_data.CloseOrder;
import Load_data.UpdateOrder;
import Load_data.GBG.loadOrderFishman;
import Load_data.loadWest;
import Load_data.GBG.Transform_file_to_lpn;
import Load_data.GBG.load_gbg_lpn_file;
import Load_data.loadlpn;
import Load_data.EDG.loadlpnEDS;
import Load_data.loadOrder;
import Load_data.loadOtherOrder;
import admin.util.PrefManager;
import connection.ConnectionDb;
import connection.ConnectionDbMYSQL;
import iw_wip.threadexemple;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import observateurs.Observateurs;
import observateurs.Observe;
import view.report.sewing;

/**
 *
 * @author Duvers
 */
public class Principal extends javax.swing.JFrame implements Observateurs,Observe{

    private JInternalFrame summary,bundle_t,sewing_prod,cansew,report_r,wip_r,TRAVEL,packing,packed,packed_mix,nStyle
            ,at_pp,dreport,CONSO,at_soabar,lpnHol,lpnEd,lpnChs,ORD_WEST,workStyle,sewing,daily_soa,daily_pad,updatepo,oponly,delDaily,close,user,ord_gbg,
            update_production,lpn_gb,batch,packin_list_batch,audit,lpngbg_convert,sewing_ajust,update_workcenter,unscan,update_mod,tag,fab,lpn_update,
            upc_scan,sum_mod,post_sewing,wash,match_book,press,ready_pack,stylenew,washreport,pressreport,matchreport,close_travel,follow_up,second_post,ajust_post,
            upload,packajust,secondupload,reaudit,ajustboxqty,auditReport,auditsituation,scorecard,warehouse,addInShipment,packingList,agabox,packing_list_warehouse
            ,updatepack,pack_list,shiplist,planification;
    private AddColorStyleFabric colorsettup;
    public Connection_user auth;
    private lock_fen lockframe;
    public static int user_id=0,staff_id;
    public static String group;
    private String username;
    private daemon damon;
    private JPanel Glass;
    private boolean active, connecter; 
    private int unactive_sec=0,wlabel,wheight,ppmenu;
    private Map<String,Component> menulist;
    private Image img;
    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private threadexemple te=new threadexemple();
    private ConnectionDb conn=ConnectionDb.instance();
    private ConnectionDbMYSQL conn2=ConnectionDbMYSQL.instance();
    private PrefManager pref=PrefManager.getInstance();
    public static final ArrayList<String> except=new ArrayList<>();
        //te.ajouterObservateur(new Principal());
        private Thread t;
        private Executors exec;
            /**
     * Creates new form Principal
     */
    public Principal() {
        t=new Thread(te);
        t.setDaemon(true);
        menulist=new HashMap<>();
        initComponents();
        img=new javax.swing.ImageIcon(getClass().getResource("/view/icon/wiplogo1.png")).getImage();
        this.setIconImage(img); 
        System.out.println(getMenuBar());
        
        auth=new Connection_user(this, true);
        auth.setLocationRelativeTo(this);
        auth.ajouterObservateur(this);
        lockframe=new lock_fen(this, true);
        this.ajouterObservateur(lockframe);
        Executors.newWorkStealingPool();
        Executors.callable(t);
        t.start();
        init();
        fullscreen();
        setVisible(true);
        if(pref.getInt("iduser",0)==0)
            auth.setVisible(true);
        else
            initUser();
        except.add("FM12");
        except.add("FM15");
        except.add("FM15K");
        except.add("FM15Y");
        except.add("FM12IW");
        except.add("FM9");
        except.add("FM10");
        except.add("FM11");
        except.add("856821");
        except.add("856822");
        
    }

    private void initUser(){
        this.ajouterObservateur(this);
        alerter(new Object[]{"Connected",pref.getInt("iduser",0),pref.getString("username"),pref.getString("fname"),pref.getString("lname"),
        pref.getString("nivel"),pref.getString("departement")});
    }
    
    private void fullscreen(){
    this.ajouterObservateur(auth);
         Toolkit tk = Toolkit.getDefaultToolkit();
      
      int xsize = (int) tk.getScreenSize().getWidth() ;
      int ysize = (int) tk.getScreenSize().getHeight() - 40;
     
      this.setSize(xsize, ysize);
      label_principal.setSize(xsize, ysize);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        con_menu = new javax.swing.JPopupMenu();
        profil = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        logout = new javax.swing.JMenuItem();
        lock = new javax.swing.JMenuItem();
        jButton8 = new javax.swing.JButton();
        label_principal = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        WIPMenu = new javax.swing.JMenu();
        wp_only = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        heat = new javax.swing.JMenuItem();
        emb = new javax.swing.JMenuItem();
        sew = new javax.swing.JMenuItem();
        ready = new javax.swing.JMenuItem();
        bundle = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        pack1 = new javax.swing.JMenuItem();
        psp = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem30 = new javax.swing.JMenuItem();
        create_shipment = new javax.swing.JMenuItem();
        scan_box = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        packinglist = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        list_shipment = new javax.swing.JMenuItem();
        MisMenu = new javax.swing.JMenu();
        user_manager = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        transact = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        reprt = new javax.swing.JMenu();
        receive = new javax.swing.JMenuItem();
        wip = new javax.swing.JMenuItem();
        in_proces_sum = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem29 = new javax.swing.JMenuItem();
        settup = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        Consolidation = new javax.swing.JMenu();
        createbox = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        sewing_help = new javax.swing.JMenuItem();
        closeorder = new javax.swing.JMenuItem();
        Progress = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        fab_menu = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        color_ajust = new javax.swing.JMenuItem();
        UPLOAD = new javax.swing.JMenu();
        asg_upload_manager = new javax.swing.JMenu();
        upload_asg_order = new javax.swing.JMenuItem();
        update_asg_order = new javax.swing.JMenuItem();
        chs_upload_manager = new javax.swing.JMenu();
        upload_chs_order = new javax.swing.JMenuItem();
        eds_upload_manger = new javax.swing.JMenu();
        upload_eds_order = new javax.swing.JMenuItem();
        wcs_upload_manager = new javax.swing.JMenu();
        upload_wcs_order = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        lpn_gbg = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        reports = new javax.swing.JMenu();
        report_daily_so = new javax.swing.JMenuItem();
        report_daily_pad = new javax.swing.JMenuItem();
        report_daily_sew = new javax.swing.JMenuItem();
        travel = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        WASHDREPORT = new javax.swing.JMenuItem();
        PRESSDREPORT = new javax.swing.JMenuItem();
        MATCHBOOKREPORT = new javax.swing.JMenuItem();
        pack = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        PACKLIST = new javax.swing.JMenuItem();
        ajust = new javax.swing.JMenu();
        update_sewing_transfer = new javax.swing.JMenuItem();
        update_mod_transfer = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        prod_ajust_sew = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        packing_ajust = new javax.swing.JMenuItem();
        second = new javax.swing.JMenuItem();
        ajusbox = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        plan = new javax.swing.JMenuItem();

        profil.setText("profil");
        con_menu.add(profil);
        con_menu.add(jSeparator4);

        logout.setText("Sign out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        con_menu.add(logout);

        lock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/icons8_Keepass_24px.png"))); // NOI18N
        lock.setText("lock");
        lock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockActionPerformed(evt);
            }
        });
        con_menu.add(lock);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WORK IN PROGRESS");
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        jButton8.setText("Sign out");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jPanel1.setVisible(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        label_principal.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout label_principalLayout = new javax.swing.GroupLayout(label_principal);
        label_principal.setLayout(label_principalLayout);
        label_principalLayout.setHorizontalGroup(
            label_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, label_principalLayout.createSequentialGroup()
                .addGap(0, 570, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        label_principalLayout.setVerticalGroup(
            label_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, label_principalLayout.createSequentialGroup()
                .addGap(0, 267, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setText("jLabel1");

        jMenuBar1.setName("menuBar"); // NOI18N

        WIPMenu.setBackground(new java.awt.Color(102, 102, 102));
        WIPMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/wip.png"))); // NOI18N
        WIPMenu.setMnemonic('W');
        WIPMenu.setText("WIP MANAGEMENT");
        WIPMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        WIPMenu.setName("Wip"); // NOI18N

        wp_only.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        wp_only.setText("Work Progress");
        wp_only.setName("work_progress"); // NOI18N
        wp_only.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wp_onlyActionPerformed(evt);
            }
        });
        WIPMenu.add(wp_only);

        jMenuItem10.setText("Post Sewing");
        jMenuItem10.setName("postSewing"); // NOI18N
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        WIPMenu.add(jMenuItem10);
        WIPMenu.add(jSeparator3);

        heat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        heat.setText("Tranfer to HT/PP");
        heat.setToolTipText("heat transfer / pad print");
        heat.setName("HT_transfer"); // NOI18N
        heat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heatActionPerformed(evt);
            }
        });
        WIPMenu.add(heat);

        emb.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        emb.setText("transfer to sewing");
        emb.setName("Sewing_transfer"); // NOI18N
        emb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                embActionPerformed(evt);
            }
        });
        WIPMenu.add(emb);

        sew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        sew.setText("Scan Production");
        sew.setName("production"); // NOI18N
        sew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sewActionPerformed(evt);
            }
        });
        WIPMenu.add(sew);

        ready.setText("transfer to soa bar");
        ready.setName("Sobar_transfer "); // NOI18N
        ready.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                readyActionPerformed(evt);
            }
        });
        WIPMenu.add(ready);

        bundle.setText("Send to Module");
        bundle.setName("send_to_module"); // NOI18N
        bundle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bundleActionPerformed(evt);
            }
        });
        WIPMenu.add(bundle);

        jMenuItem11.setText("transfer_to_wash");
        jMenuItem11.setName("washing"); // NOI18N
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        WIPMenu.add(jMenuItem11);

        jMenuItem12.setText("transfer_to_matchbook");
        jMenuItem12.setName("matchbook"); // NOI18N
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        WIPMenu.add(jMenuItem12);

        jMenuItem13.setText("transfer to press");
        jMenuItem13.setName("pressroom"); // NOI18N
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        WIPMenu.add(jMenuItem13);

        pack1.setText("Packed");
        pack1.setName("Packed"); // NOI18N
        pack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pack1ActionPerformed(evt);
            }
        });
        WIPMenu.add(pack1);

        psp.setText("PO STYLE PROGRESS");
        psp.setName("PSP"); // NOI18N
        psp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pspActionPerformed(evt);
            }
        });
        WIPMenu.add(psp);

        jMenuItem6.setText("generate Tag");
        jMenuItem6.setName("new_tag"); // NOI18N
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        WIPMenu.add(jMenuItem6);

        jMenuItem5.setLabel("packing_mixed");
        jMenuItem5.setName("packing_mixed"); // NOI18N
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        WIPMenu.add(jMenuItem5);

        jMenu2.setText("Audit");
        jMenu2.setName("Audit_menu"); // NOI18N

        jMenuItem15.setText("Ready to Audit");
        jMenuItem15.setName("create_batch"); // NOI18N
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem17.setText("Audited/Ready to ship");
        jMenuItem17.setName("Audit_batch"); // NOI18N
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenuItem16.setText("packing list batch");
        jMenuItem16.setName("list_batch"); // NOI18N
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem16);

        jMenuItem26.setText("Reaudit");
        jMenuItem26.setName("reaudit"); // NOI18N
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem26);

        WIPMenu.add(jMenu2);

        jMenuItem22.setText("Travel card follow");
        jMenuItem22.setName("followup"); // NOI18N
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        WIPMenu.add(jMenuItem22);

        jMenu4.setText("SHIPPING");
        jMenu4.setName("shipping"); // NOI18N

        jMenuItem30.setText("Ready to ship/to WareHouse");
        jMenuItem30.setName("warehouse"); // NOI18N
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem30);

        create_shipment.setText("Create Shipment");
        create_shipment.setName("create_shipment"); // NOI18N
        create_shipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_shipmentActionPerformed(evt);
            }
        });
        jMenu4.add(create_shipment);

        scan_box.setText("Scan Boxes in Shipment");
        scan_box.setName("box_in_shipment"); // NOI18N
        scan_box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scan_boxActionPerformed(evt);
            }
        });
        jMenu4.add(scan_box);

        jMenu5.setText("Report");
        jMenu5.setName("ship_report"); // NOI18N

        packinglist.setText("Packing list");
        packinglist.setName("packinglist"); // NOI18N
        packinglist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packinglistActionPerformed(evt);
            }
        });
        jMenu5.add(packinglist);

        jMenuItem32.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem32.setText("In Shipping");
        jMenuItem32.setName("in shipping"); // NOI18N
        jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem32);

        list_shipment.setText("PACKING LIST DETAILED");
        list_shipment.setName("shiplist"); // NOI18N
        list_shipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_shipmentActionPerformed(evt);
            }
        });
        jMenu5.add(list_shipment);

        jMenu4.add(jMenu5);

        WIPMenu.add(jMenu4);

        jMenuBar1.add(WIPMenu);

        MisMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/management.png"))); // NOI18N
        MisMenu.setText("MIS CONTROL");
        MisMenu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        MisMenu.setName("MIS"); // NOI18N

        user_manager.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        user_manager.setText("User");
        user_manager.setName("User"); // NOI18N
        user_manager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_managerActionPerformed(evt);
            }
        });
        MisMenu.add(user_manager);
        MisMenu.add(jSeparator2);

        transact.setText("Transaction History");
        transact.setName("History"); // NOI18N
        MisMenu.add(transact);
        MisMenu.add(jSeparator1);

        jMenuBar1.add(MisMenu);

        reprt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/report.png"))); // NOI18N
        reprt.setText("REPORT");
        reprt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        reprt.setName("Report"); // NOI18N

        receive.setText("Received report");
        receive.setName("Recieved_report"); // NOI18N
        receive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiveActionPerformed(evt);
            }
        });
        reprt.add(receive);

        wip.setText("SUMMARY REPORT");
        wip.setName("Summary"); // NOI18N
        wip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wipActionPerformed(evt);
            }
        });
        reprt.add(wip);

        in_proces_sum.setText("In process summary");
        in_proces_sum.setName("in_proces_sum"); // NOI18N
        in_proces_sum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_proces_sumActionPerformed(evt);
            }
        });
        reprt.add(in_proces_sum);

        jMenu3.setText("QUALITY");
        jMenu3.setName("quality_report"); // NOI18N

        jMenuItem29.setText("AQL report");
        jMenuItem29.setName("aql"); // NOI18N
        jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem29);

        reprt.add(jMenu3);

        jMenuBar1.add(reprt);

        settup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/setting.png"))); // NOI18N
        settup.setText("Setting");
        settup.setName("Setting"); // NOI18N

        jMenuItem14.setText("New Style");
        jMenuItem14.setName("initStyle"); // NOI18N
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        settup.add(jMenuItem14);

        Consolidation.setText("CONSOLIDATION");
        Consolidation.setName("Consolidation"); // NOI18N

        createbox.setText("CREATE MIX BOX");
        createbox.setName("create_box"); // NOI18N
        createbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createboxActionPerformed(evt);
            }
        });
        Consolidation.add(createbox);

        jMenuItem3.setText("CREATE LPN DASH");
        jMenuItem3.setName("dash"); // NOI18N
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        Consolidation.add(jMenuItem3);

        settup.add(Consolidation);

        sewing_help.setText("sewing");
        sewing_help.setName("essai"); // NOI18N
        sewing_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sewing_helpActionPerformed(evt);
            }
        });
        settup.add(sewing_help);

        closeorder.setText("Close order");
        closeorder.setName("Order_close"); // NOI18N
        closeorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeorderActionPerformed(evt);
            }
        });
        settup.add(closeorder);

        Progress.setText("All Progression(Closed included)");
        Progress.setName("Progress"); // NOI18N
        Progress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProgressActionPerformed(evt);
            }
        });
        settup.add(Progress);

        jMenuItem4.setText("key for update access");
        jMenuItem4.setName("key for update access"); // NOI18N
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        settup.add(jMenuItem4);

        fab_menu.setText("Fabrics");
        fab_menu.setName("fabric"); // NOI18N
        fab_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fab_menuActionPerformed(evt);
            }
        });
        settup.add(fab_menu);

        jMenuItem7.setText("Lpn Modifcation");
        jMenuItem7.setName("Lpn_change"); // NOI18N
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        settup.add(jMenuItem7);

        jMenuItem9.setText("scan upc");
        jMenuItem9.setName("upcScan"); // NOI18N
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        settup.add(jMenuItem9);

        jMenuItem31.setText("generate box for AGA");
        jMenuItem31.setName("AGABOX"); // NOI18N
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        settup.add(jMenuItem31);

        color_ajust.setText("COLOR AJUSTMENT");
        color_ajust.setName("colorSetting"); // NOI18N
        color_ajust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                color_ajustActionPerformed(evt);
            }
        });
        settup.add(color_ajust);

        jMenuBar1.add(settup);

        UPLOAD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/upload2.png"))); // NOI18N
        UPLOAD.setText("Upload Order");
        UPLOAD.setName("Upload"); // NOI18N

        asg_upload_manager.setText("HOLLOWAY & AUGUSTA");
        asg_upload_manager.setName("ASG_upload"); // NOI18N

        upload_asg_order.setText("UPLOAD ORDER/LPN");
        upload_asg_order.setName("asg_upload_order"); // NOI18N
        upload_asg_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_asg_orderActionPerformed(evt);
            }
        });
        asg_upload_manager.add(upload_asg_order);

        update_asg_order.setText("Update order");
        update_asg_order.setName("update_asg_order"); // NOI18N
        update_asg_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_asg_orderActionPerformed(evt);
            }
        });
        asg_upload_manager.add(update_asg_order);

        UPLOAD.add(asg_upload_manager);

        chs_upload_manager.setText("CHAMPRO");
        chs_upload_manager.setName("CHS_upload "); // NOI18N

        upload_chs_order.setText("LOAD ORDER");
        upload_chs_order.setName("chs_upload_order"); // NOI18N
        upload_chs_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_chs_orderActionPerformed(evt);
            }
        });
        chs_upload_manager.add(upload_chs_order);

        UPLOAD.add(chs_upload_manager);

        eds_upload_manger.setText("EDWARDS");
        eds_upload_manger.setName("EDG_upload "); // NOI18N

        upload_eds_order.setText("LOAD LPN");
        upload_eds_order.setName("load_edg_order"); // NOI18N
        upload_eds_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_eds_orderActionPerformed(evt);
            }
        });
        eds_upload_manger.add(upload_eds_order);

        UPLOAD.add(eds_upload_manger);

        wcs_upload_manager.setText("WESTCHESTER");
        wcs_upload_manager.setName("WCS"); // NOI18N

        upload_wcs_order.setText("LOAD ORDER");
        upload_wcs_order.setName("load_wcs_order"); // NOI18N
        upload_wcs_order.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_wcs_orderActionPerformed(evt);
            }
        });
        wcs_upload_manager.add(upload_wcs_order);

        UPLOAD.add(wcs_upload_manager);

        jMenu1.setText("GBG");
        jMenu1.setName("GBG"); // NOI18N

        jMenuItem1.setText("Load Order");
        jMenuItem1.setName("load_gbg"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        lpn_gbg.setText("Transform File to Lpn");
        lpn_gbg.setName("lpn_gbg"); // NOI18N
        lpn_gbg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lpn_gbgActionPerformed(evt);
            }
        });
        jMenu1.add(lpn_gbg);

        jMenuItem8.setText("Load Lpn");
        jMenuItem8.setName("lpnloader"); // NOI18N
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem8);

        UPLOAD.add(jMenu1);

        jMenuItem25.setText("UPLOAD ORDER");
        jMenuItem25.setName("uploadorder"); // NOI18N
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        UPLOAD.add(jMenuItem25);

        jMenuBar1.add(UPLOAD);

        reports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/rport_d.png"))); // NOI18N
        reports.setText("Daily reports");
        reports.setName("Daily_reports "); // NOI18N

        report_daily_so.setText("transfer to sobar daily report");
        report_daily_so.setName("Sobar_dreport"); // NOI18N
        report_daily_so.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report_daily_soActionPerformed(evt);
            }
        });
        reports.add(report_daily_so);

        report_daily_pad.setText("transfer to padprint report");
        report_daily_pad.setName("Padprint_dreport"); // NOI18N
        report_daily_pad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report_daily_padActionPerformed(evt);
            }
        });
        reports.add(report_daily_pad);

        report_daily_sew.setText("transfer to sewing daily report");
        report_daily_sew.setName("Sewing_dreport"); // NOI18N
        report_daily_sew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                report_daily_sewActionPerformed(evt);
            }
        });
        reports.add(report_daily_sew);

        travel.setText("transfer to module daily report");
        travel.setName("module_daily_report"); // NOI18N
        travel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travelActionPerformed(evt);
            }
        });
        reports.add(travel);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jMenuItem2.setText("Production Daily report");
        jMenuItem2.setName("Finish_goods_dreport"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        reports.add(jMenuItem2);

        WASHDREPORT.setText("WASHING DAILY REPORT");
        WASHDREPORT.setName("washdreport"); // NOI18N
        WASHDREPORT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WASHDREPORTActionPerformed(evt);
            }
        });
        reports.add(WASHDREPORT);

        PRESSDREPORT.setText("PRESS DAILY REPORT");
        PRESSDREPORT.setName("pressdreport"); // NOI18N
        PRESSDREPORT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PRESSDREPORTActionPerformed(evt);
            }
        });
        reports.add(PRESSDREPORT);

        MATCHBOOKREPORT.setText("MATCHBOOK REPORT");
        MATCHBOOKREPORT.setName("matchbookreport"); // NOI18N
        MATCHBOOKREPORT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MATCHBOOKREPORTActionPerformed(evt);
            }
        });
        reports.add(MATCHBOOKREPORT);

        pack.setText("Packing");
        pack.setName("Packing_report"); // NOI18N
        pack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packActionPerformed(evt);
            }
        });
        reports.add(pack);

        jMenuItem27.setText("Audit report");
        jMenuItem27.setName("audit_report"); // NOI18N
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        reports.add(jMenuItem27);

        jMenuItem28.setText("Batch Situation");
        jMenuItem28.setName("situation"); // NOI18N
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        reports.add(jMenuItem28);

        PACKLIST.setText("PACKING LIST PACKED BOXES");
        PACKLIST.setName("packlist"); // NOI18N
        PACKLIST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PACKLISTActionPerformed(evt);
            }
        });
        reports.add(PACKLIST);

        jMenuBar1.add(reports);

        ajust.setText("Ajustment");
        ajust.setName("Ajustment"); // NOI18N

        update_sewing_transfer.setText("Update Building tranfered to");
        update_sewing_transfer.setName("Sewing_ajust"); // NOI18N
        update_sewing_transfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_sewing_transferActionPerformed(evt);
            }
        });
        ajust.add(update_sewing_transfer);

        update_mod_transfer.setText("Update Module assign");
        update_mod_transfer.setName("Module_Ajust"); // NOI18N
        update_mod_transfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_mod_transferActionPerformed(evt);
            }
        });
        ajust.add(update_mod_transfer);

        jMenuItem19.setText("First/OTFQ");
        jMenuItem19.setName("production ajustment"); // NOI18N
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        ajust.add(jMenuItem19);

        jMenuItem20.setText("Undo stickers");
        jMenuItem20.setName("undo"); // NOI18N
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        ajust.add(jMenuItem20);

        prod_ajust_sew.setText("Production Ajustment");
        prod_ajust_sew.setName("production_ajust"); // NOI18N
        prod_ajust_sew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prod_ajust_sewActionPerformed(evt);
            }
        });
        ajust.add(prod_ajust_sew);

        jMenuItem18.setText("INIT POST SEWING");
        ajust.add(jMenuItem18);

        jMenuItem21.setText("Close sewing");
        jMenuItem21.setName("close_sewing"); // NOI18N
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        ajust.add(jMenuItem21);

        jMenuItem23.setText("Second Post sewing");
        jMenuItem23.setName("post_sewing_second"); // NOI18N
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        ajust.add(jMenuItem23);

        jMenuItem24.setText("post sewing ajustment");
        jMenuItem24.setName("upgrade_post_sewing"); // NOI18N
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        ajust.add(jMenuItem24);

        packing_ajust.setText("Packing ajustment");
        packing_ajust.setName("pack_ajust"); // NOI18N
        packing_ajust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packing_ajustActionPerformed(evt);
            }
        });
        ajust.add(packing_ajust);

        second.setText("upload second");
        second.setName("second_uploaded"); // NOI18N
        second.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                secondActionPerformed(evt);
            }
        });
        ajust.add(second);

        ajusbox.setText("Ajust box in pallet qty");
        ajusbox.setName("qty_box_pallet"); // NOI18N
        ajusbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajusboxActionPerformed(evt);
            }
        });
        ajust.add(ajusbox);

        jMenuItem33.setText("Update packing Box");
        jMenuItem33.setName("updatepacking"); // NOI18N
        jMenuItem33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem33ActionPerformed(evt);
            }
        });
        ajust.add(jMenuItem33);

        jMenuBar1.add(ajust);

        jMenu6.setMnemonic('P');
        jMenu6.setText("Planning");
        jMenu6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu6.setName("planning"); // NOI18N

        plan.setText("Planification");
        plan.setName("planification"); // NOI18N
        plan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                planActionPerformed(evt);
            }
        });
        jMenu6.add(plan);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_principal)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_principal)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProgressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProgressActionPerformed
        if(summary==null|| summary.isClosed()){
            summary=new Work_status();
            summary.setVisible(true);
            label_principal.add(summary);
            
            System.out.println("new");
        }
        if(summary.isIcon()){
            try {
                summary.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        summary.toFront();
    }//GEN-LAST:event_ProgressActionPerformed

    private void bundleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bundleActionPerformed
        // TODO add your handling code here:
        if(bundle_t==null|| bundle_t.isClosed()){
            bundle_t=new Bundle();
            bundle_t.setVisible(true);
            label_principal.add(bundle_t);
        }
        if(bundle_t.isIcon()){
            try {
                bundle_t.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //System.out.println("old");
        }
        bundle_t.moveToFront();
    }//GEN-LAST:event_bundleActionPerformed

    private void sewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sewActionPerformed
        // TODO add your handling code here:
        if(sewing_prod==null|| sewing_prod.isClosed()){
            sewing_prod=new Sewing_prod();
            sewing_prod.setVisible(true);
            label_principal.add(sewing_prod);
            
            //System.out.println("new");
        }
        if(sewing_prod.isIcon()){
            try {
                sewing_prod.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //System.out.println("old");
        }
        sewing_prod.toFront();
    }//GEN-LAST:event_sewActionPerformed

    private void readyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_readyActionPerformed
        // TODO add your handling code here:
        if(cansew==null|| cansew.isClosed()){
            cansew=new Ready_to_sew();
            cansew.setVisible(true);
            label_principal.add(cansew);
           
            //System.out.println("new");
        }
        if(cansew.isIcon()){
            try {
                cansew.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //System.out.println("old");
        }
        cansew.toFront();
    }//GEN-LAST:event_readyActionPerformed

    private void receiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiveActionPerformed
        // TODO add your handling code here:
        
        if(report_r==null|| report_r.isClosed()){
            report_r=new delivered_report();
            report_r.setVisible(true);
            label_principal.add(report_r);
            
            System.out.println("new");
        }
        if(report_r.isIcon()){
            try {
                report_r.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        report_r.toFront();
    }//GEN-LAST:event_receiveActionPerformed

    private void wipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wipActionPerformed
        // TODO add your handling code here:
        if(wip_r==null|| wip_r.isClosed()){
            wip_r=new WIPCONTROL();
            wip_r.setVisible(true);
            label_principal.add(wip_r);
            
            System.out.println("new");
        }
        if(wip_r.isIcon()){
            try {
                wip_r.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        wip_r.toFront();
    }//GEN-LAST:event_wipActionPerformed

    private void travelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travelActionPerformed
        // TODO add your handling code here:
        Thread t=new Thread(new Runnable(){

            @Override
            public void run() {
                if(TRAVEL==null|| TRAVEL.isClosed()){
            TRAVEL=new travel_cardM();
            TRAVEL.setVisible(true);
            label_principal.add(TRAVEL);
            
            System.out.println("new");
        }
            }
            
        });
        
        if(TRAVEL==null|| TRAVEL.isClosed()){
            TRAVEL=new travel_cardM();
            TRAVEL.setVisible(true);
            label_principal.add(TRAVEL);
            
            System.out.println("new");
        }
        if(TRAVEL.isIcon()){
            try {
                TRAVEL.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        TRAVEL.toFront();
        
    }//GEN-LAST:event_travelActionPerformed

    private void packActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packActionPerformed
        // TODO add your handling code here:
         if(packing==null|| packing.isClosed()){
            packing=new Details();
            packing.setVisible(true);
            label_principal.add(packing);
            
            System.out.println("new");
        }
        if(packing.isIcon()){
            try {
                packing.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        packing.toFront();
    }//GEN-LAST:event_packActionPerformed

    private void pack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pack1ActionPerformed
        // TODO add your handling code here:
        if(packed==null|| packed.isClosed()){
            packed=new packing();
            packed.setVisible(true);
            label_principal.add(packed);
            
            System.out.println("new");
        }
        if(packed.isIcon()){
            try {
                packed.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        packed.toFront();
    }//GEN-LAST:event_pack1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if(dreport==null|| dreport.isClosed()){
            dreport=new WIPDAILLY();
            dreport.setVisible(true);
            label_principal.add(dreport);
            
            System.out.println("new");
        }
        if(dreport.isIcon()){
            try {
                dreport.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        dreport.toFront();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void createboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createboxActionPerformed
        // TODO add your handling code here:
        if(CONSO==null|| CONSO.isClosed()){
            CONSO=new Mix_BOX();
            CONSO.setVisible(true);
            label_principal.add(CONSO);
            
            System.out.println("new");
        }
        if(CONSO.isIcon()){
            try {
                CONSO.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        CONSO.toFront();
    }//GEN-LAST:event_createboxActionPerformed

    private void heatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heatActionPerformed
        // TODO add your handling code here:
         if(at_soabar==null|| at_soabar.isClosed()){
            //at_soabar=new At_soabar_old1();
             at_soabar=new At_soabar();
            at_soabar.setVisible(true);
            label_principal.add(at_soabar);
            
            System.out.println("new");
        }
        if(at_soabar.isIcon()){
            try {
                at_soabar.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        at_soabar.toFront();
    }//GEN-LAST:event_heatActionPerformed

    private void embActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_embActionPerformed
        // TODO add your handling code here:
        if(at_pp==null|| at_pp.isClosed()){
            at_pp=new Heat_pad();
            at_pp.setVisible(true);
            label_principal.add(at_pp);
            
            System.out.println("new");
        }
        if(at_pp.isIcon()){
            try {
                at_pp.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        at_pp.toFront();
    }//GEN-LAST:event_embActionPerformed

    private void upload_chs_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_chs_orderActionPerformed
        // TODO add your handling code here:
        if(lpnChs==null|| lpnChs.isClosed()){
            lpnChs=new ChsOrder();
            lpnChs.setVisible(true);
            label_principal.add(lpnChs);
            
            System.out.println("new");
        }
        if(lpnChs.isIcon()){
            try {
                lpnChs.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        lpnChs.toFront();
    }//GEN-LAST:event_upload_chs_orderActionPerformed

    private void upload_asg_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_asg_orderActionPerformed
        // TODO add your handling code here:
        if(lpnHol==null|| lpnHol.isClosed()){
            lpnHol=new loadlpn();
            lpnHol.setVisible(true);
            label_principal.add(lpnHol);
            
            System.out.println("new");
        }
        if(lpnHol.isIcon()){
            try {
                lpnHol.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        lpnHol.toFront();
    }//GEN-LAST:event_upload_asg_orderActionPerformed

    private void upload_eds_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_eds_orderActionPerformed
        // TODO add your handling code here:
        if(lpnEd==null|| lpnEd.isClosed()){
            lpnEd=new loadlpnEDS();
            lpnEd.setVisible(true);
            label_principal.add(lpnEd);
            
            System.out.println("new");
        }
        if(lpnEd.isIcon()){
            try {
                lpnEd.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        lpnEd.toFront();
    }//GEN-LAST:event_upload_eds_orderActionPerformed

    private void user_managerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_managerActionPerformed
        // TODO add your handling code here:
        //Glass.setVisible(true);
        if(user==null|| user.isClosed()){
            user=new User();
            user.setVisible(true);
            label_principal.add(user);
            
            System.out.println("new");
        }
        if(user.isIcon()){
            try {
                user.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        user.toFront();
    }//GEN-LAST:event_user_managerActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void pspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pspActionPerformed
        // TODO add your handling code here:
        if(workStyle==null|| workStyle.isClosed()){
            workStyle=new Work_status_style();
            workStyle.setVisible(true);
            label_principal.add(workStyle);
            
            System.out.println("new");
        }
        if(workStyle.isIcon()){
            try {
                workStyle.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        workStyle.toFront();
    }//GEN-LAST:event_pspActionPerformed

    private void sewing_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sewing_helpActionPerformed
        // TODO add your handling code here:
        if(sewing==null|| sewing.isClosed()){
            sewing=new sewing();
            sewing.setVisible(true);
            label_principal.add(sewing);
            
            System.out.println("new");
        }
        if(sewing.isIcon()){
            try {
                sewing.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        sewing.toFront();
    }//GEN-LAST:event_sewing_helpActionPerformed

    private void report_daily_soActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_daily_soActionPerformed
        // TODO add your handling code here:
        if(daily_soa==null|| daily_soa.isClosed()){
            daily_soa=new SOABAR_report();
            daily_soa.setVisible(true);
            label_principal.add(daily_soa);
            
            System.out.println("new");
        }
        if(daily_soa.isIcon()){
            try {
                daily_soa.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        daily_soa.toFront();
        
    }//GEN-LAST:event_report_daily_soActionPerformed

    private void report_daily_padActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_daily_padActionPerformed
        // TODO add your handling code here:
        if(daily_pad==null|| daily_pad.isClosed()){
            daily_pad=new PADPRINT_report();
            daily_pad.setVisible(true);
            label_principal.add(daily_pad);
            
            System.out.println("new");
        }
        if(daily_pad.isIcon()){
            try {
                daily_pad.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        daily_pad.toFront();
    }//GEN-LAST:event_report_daily_padActionPerformed

    private void update_asg_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_asg_orderActionPerformed
        // TODO add your handling code here:
        
                if(updatepo==null|| updatepo.isClosed()){
            updatepo=new UpdateOrder();
            updatepo.setVisible(true);
            label_principal.add(updatepo);
            
            System.out.println("new");
        }
        if(updatepo.isIcon()){
            try {
                updatepo.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        updatepo.toFront();
        
    }//GEN-LAST:event_update_asg_orderActionPerformed

    private void wp_onlyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wp_onlyActionPerformed
        // TODO add your handling code here:
        if(oponly==null|| oponly.isClosed()){
            oponly=new Work_status_open();
            oponly.setVisible(true);
            label_principal.add(oponly);
            
            System.out.println("new");
        }
        if(oponly.isIcon()){
            try {
                oponly.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        oponly.toFront();
    }//GEN-LAST:event_wp_onlyActionPerformed

    private void report_daily_sewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_report_daily_sewActionPerformed
        // TODO add your handling code here:
        if(delDaily==null|| delDaily.isClosed()){
            delDaily=new Delivered_daily_report();
            delDaily.setVisible(true);
            label_principal.add(delDaily);
            
            System.out.println("new");
        }
        if(delDaily.isIcon()){
            try {
                delDaily.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        delDaily.toFront();
    }//GEN-LAST:event_report_daily_sewActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        init();
        alerter("Sign in");
        auth.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void upload_wcs_orderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_wcs_orderActionPerformed
        // TODO add your handling code here:
        if(ORD_WEST==null|| ORD_WEST.isClosed()){
            ORD_WEST=new loadWest();
            ORD_WEST.setVisible(true);
            label_principal.add(ORD_WEST);
            
            System.out.println("new");
        }
        if(ORD_WEST.isIcon()){
            try {
                ORD_WEST.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        ORD_WEST.toFront();
    }//GEN-LAST:event_upload_wcs_orderActionPerformed

    private void closeorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeorderActionPerformed
        // TODO add your handling code here:
        if(close==null|| close.isClosed()){
            close=new CloseOrder();
            close.setVisible(true);
            label_principal.add(close);
            
            System.out.println("new");
        }
        if(close.isIcon()){
            try {
                close.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        close.toFront();
    }//GEN-LAST:event_closeorderActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(ord_gbg==null|| ord_gbg.isClosed()){
            ord_gbg=new loadOrderFishman();
            ord_gbg.setVisible(true);
            label_principal.add(ord_gbg);
            
            System.out.println("new");
        }
        if(ord_gbg.isIcon()){
            try {
                ord_gbg.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        ord_gbg.toFront();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        if(update_production==null|| update_production.isClosed()){
            update_production=new update_production();
            update_production.setVisible(true);
            label_principal.add(update_production);
            
            System.out.println("new");
        }
        if(update_production.isIcon()){
            try {
                update_production.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        update_production.toFront();
        //sfdg
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        if(packed_mix==null|| packed_mix.isClosed()){
            packed_mix=new packing_mix();
            packed_mix.setVisible(true);
            label_principal.add(packed_mix);
           
            System.out.println("new");
        }
        if(packed_mix.isIcon()){
            try {
                packed_mix.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        packed_mix.toFront();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void update_mod_transferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_mod_transferActionPerformed
        // TODO add your handling code here:
        if(update_mod==null|| update_mod.isClosed()){
            update_mod=new transfer_to_mod();
            update_mod.setVisible(true);
            label_principal.add(update_mod);
            
            System.out.println("new");
        }
        if(update_mod.isIcon()){
            try {
                update_mod.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        update_mod.toFront();
    }//GEN-LAST:event_update_mod_transferActionPerformed

    private void lpn_gbgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lpn_gbgActionPerformed
        // TODO add your handling code here:
        if(lpngbg_convert==null|| lpngbg_convert.isClosed()){
            lpngbg_convert=new Transform_file_to_lpn();
            lpngbg_convert.setVisible(true);
            label_principal.add(lpngbg_convert);
            
            System.out.println("new");
        }
        if(lpngbg_convert.isIcon()){
            try {
                lpngbg_convert.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        lpngbg_convert.toFront();
    }//GEN-LAST:event_lpn_gbgActionPerformed

    private void prod_ajust_sewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prod_ajust_sewActionPerformed
         // TODO add your handling code here:
        if(sewing_ajust==null|| sewing_ajust.isClosed()){
            sewing_ajust=new Sewing_prod_before_eight();
            sewing_ajust.setVisible(true);
            label_principal.add(sewing_ajust);
            
            System.out.println("new");
        }
        if(sewing_ajust.isIcon()){
            try {
                sewing_ajust.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        sewing_ajust.toFront();
        this.ajouterObservateur((Observateurs)sewing_ajust);
    }//GEN-LAST:event_prod_ajust_sewActionPerformed

    private void update_sewing_transferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_sewing_transferActionPerformed
        // TODO add your handling code here:
        if(update_workcenter==null|| update_workcenter.isClosed()){
            update_workcenter=new transfer_to_build();
            update_workcenter.setVisible(true);
            label_principal.add(update_workcenter);
            
            System.out.println("new");
        }
        if(update_workcenter.isIcon()){
            try {
                update_workcenter.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        update_workcenter.toFront();
    }//GEN-LAST:event_update_sewing_transferActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        if(unscan==null|| unscan.isClosed()){
            unscan=new unscan();
            unscan.setVisible(true);
            label_principal.add(unscan);
            
            System.out.println("new");
        }
        if(unscan.isIcon()){
            try {
                unscan.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        unscan.toFront();
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        if(tag==null|| tag.isClosed()){
            tag=new Generate_tag();
            tag.setVisible(true);
            label_principal.add(tag);
            
            System.out.println("new");
        }
        if(tag.isIcon()){
            try {
                tag.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        tag.toFront();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void fab_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fab_menuActionPerformed
        // TODO add your handling code here:
        if(fab==null|| fab.isClosed()){
            fab=new Fabrics();
            fab.setVisible(true);
            label_principal.add(fab);
            
            System.out.println("new");
        }
        if(fab.isIcon()){
            try {
                fab.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        fab.toFront();
    }//GEN-LAST:event_fab_menuActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        init();
        alerter("Sign in");
        auth.setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

    private void lockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockActionPerformed
        // TODO add your handling code here:
        lock();
    }//GEN-LAST:event_lockActionPerformed

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowStateChanged

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        // TODO add your handling code here:
        active=false;
    }//GEN-LAST:event_formWindowLostFocus

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        active=true;
    }//GEN-LAST:event_formWindowGainedFocus

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        if(lpn_update==null|| lpn_update.isClosed()){
            lpn_update=new lpn_update();
            lpn_update.setVisible(true);
            label_principal.add(lpn_update);
            
            System.out.println("new");
        }
        if(lpn_update.isIcon()){
            try {
                lpn_update.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        lpn_update.toFront();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        if(lpn_gb==null|| lpn_gb.isClosed()){
            lpn_gb=new load_gbg_lpn_file();
            lpn_gb.setVisible(true);
            label_principal.add(lpn_gb);
            
            System.out.println("new");
        }
        if(lpn_gb.isIcon()){
            try {
                lpn_gb.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        lpn_gb.toFront();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        if(upc_scan==null|| upc_scan.isClosed()){
            upc_scan=new scanUpc();
            upc_scan.setVisible(true);
            label_principal.add(upc_scan);
            
            System.out.println("new");
        }
        if(upc_scan.isIcon()){
            try {
                upc_scan.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        upc_scan.toFront();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void in_proces_sumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_proces_sumActionPerformed
        // TODO add your handling code here:
        if(sum_mod==null|| sum_mod.isClosed()){
            sum_mod=new Module_summary();
            sum_mod.setVisible(true);
            label_principal.add(sum_mod);
            
            System.out.println("new");
        }
        if(sum_mod.isIcon()){
            try {
                sum_mod.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        sum_mod.toFront();
    }//GEN-LAST:event_in_proces_sumActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        if(post_sewing==null|| post_sewing.isClosed()){
            post_sewing=new post_sewing_operation();
            post_sewing.setVisible(true);
            label_principal.add(post_sewing);
            
            System.out.println("new");
        }
        if(post_sewing.isIcon()){
            try {
                post_sewing.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        post_sewing.toFront();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        if(wash==null|| wash.isClosed()){
            wash=new Washing();
            wash.setVisible(true);
            label_principal.add(wash);
            
            System.out.println("new");
        }
        if(wash.isIcon()){
            try {
                wash.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        wash.toFront();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        if(match_book==null|| match_book.isClosed()){
            match_book=new matchbook();
            match_book.setVisible(true);
            label_principal.add(match_book);
            
            System.out.println("new");
        }
        if(match_book.isIcon()){
            try {
                match_book.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        match_book.toFront();
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        if(press==null|| press.isClosed()){
            press=new press();
            press.setVisible(true);
            label_principal.add(press);
            
            System.out.println("new");
        }
        if(press.isIcon()){
            try {
                press.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        press.toFront();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        if(stylenew==null|| stylenew.isClosed()){
            stylenew=new initStyle();
            stylenew.setVisible(true);
            label_principal.add(stylenew);
            
            System.out.println("new");
        }
        if(stylenew.isIcon()){
            try {
                stylenew.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        stylenew.toFront();
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        if(batch==null|| batch.isClosed()){
            batch=new create_batch();
            batch.setVisible(true);
            label_principal.add(batch);
            
            System.out.println("new");
        }
        if(batch.isIcon()){
            try {
                batch.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        batch.toFront();
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        if(packin_list_batch==null|| packin_list_batch.isClosed()){
            packin_list_batch=new Pallet_packing();
            packin_list_batch.setVisible(true);
            label_principal.add(packin_list_batch);
            
            System.out.println("new");
        }
        if(packin_list_batch.isIcon()){
            try {
                packin_list_batch.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        packin_list_batch.toFront();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        if(audit==null|| audit.isClosed()){
            audit=new Audit_form();
            audit.setVisible(true);
            label_principal.add(audit);
            
            System.out.println("new");
        }
        if(audit.isIcon()){
            try {
                audit.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        audit.toFront();
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void WASHDREPORTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WASHDREPORTActionPerformed
        // TODO add your handling code here:
        if(washreport==null|| washreport.isClosed()){
            washreport=new WASHDAILLY();
            washreport.setVisible(true);
            label_principal.add(washreport);
            
            System.out.println("new");
        }
        if(washreport.isIcon()){
            try {
                washreport.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        washreport.toFront();
    }//GEN-LAST:event_WASHDREPORTActionPerformed

    private void PRESSDREPORTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PRESSDREPORTActionPerformed
        // TODO add your handling code here:
        if(pressreport==null|| pressreport.isClosed()){
            pressreport=new pressReport();
            pressreport.setVisible(true);
            label_principal.add(pressreport);
            
            System.out.println("new");
        }
        if(pressreport.isIcon()){
            try {
                pressreport.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        pressreport.toFront();
    }//GEN-LAST:event_PRESSDREPORTActionPerformed

    private void MATCHBOOKREPORTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MATCHBOOKREPORTActionPerformed
        // TODO add your handling code here:
        if(matchreport==null|| matchreport.isClosed()){
            matchreport=new matchbookreport();
            matchreport.setVisible(true);
            label_principal.add(matchreport);
            
            System.out.println("new");
        }
        if(matchreport.isIcon()){
            try {
                matchreport.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        matchreport.toFront();
    }//GEN-LAST:event_MATCHBOOKREPORTActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
        if(close_travel==null|| close_travel.isClosed()){
            close_travel=new Sewing_prod_close_sku();
            close_travel.setVisible(true);
            label_principal.add(close_travel);
            
            System.out.println("new");
        }
        if(close_travel.isIcon()){
            try {
                close_travel.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        close_travel.toFront();
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
       // TODO add your handling code here:
        if(follow_up==null|| follow_up.isClosed()){
            follow_up=new report_alert();
            follow_up.setVisible(true);
            label_principal.add(follow_up);
            
            System.out.println("new");
        }
        if(follow_up.isIcon()){
            try {
                follow_up.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        follow_up.toFront();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        if(second_post==null|| second_post.isClosed()){
            second_post=new post_sewing_scan();
            second_post.setVisible(true);
            label_principal.add(second_post);
            
            System.out.println("new");
        }
        if(second_post.isIcon()){
            try {
                second_post.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        second_post.toFront();
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        if(ajust_post==null|| ajust_post.isClosed()){
            ajust_post=new update_post_sewing();
            ajust_post.setVisible(true);
            label_principal.add(ajust_post);
            
            System.out.println("new");
        }
        if(ajust_post.isIcon()){
            try {
                ajust_post.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        ajust_post.toFront();
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        // TODO add your handling code here:
        if(upload==null|| upload.isClosed()){
            upload=new loadOrder();
            upload.setVisible(true);
            label_principal.add(upload);
            
            System.out.println("new");
        }
        if(upload.isIcon()){
            try {
                upload.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        upload.toFront();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void packing_ajustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packing_ajustActionPerformed
        // TODO add your handling code here:
        if(packajust==null|| packajust.isClosed()){
            packajust=new packing_mix_before_height();
            packajust.setVisible(true);
            label_principal.add(packajust);
            
            System.out.println("new");
        }
        if(packajust.isIcon()){
            try {
                packajust.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        packajust.toFront();
        this.ajouterObservateur((Observateurs)packajust);
    }//GEN-LAST:event_packing_ajustActionPerformed

    private void secondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_secondActionPerformed
        // TODO add your handling code here:
        if(secondupload==null|| secondupload.isClosed()){
            secondupload=new Upload_second();
            secondupload.setVisible(true);
            label_principal.add(secondupload);
            
            System.out.println("new");
        }
        if(secondupload.isIcon()){
            try {
                secondupload.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        secondupload.toFront();
    }//GEN-LAST:event_secondActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        // TODO add your handling code here:
        if(reaudit==null|| reaudit.isClosed()){
            reaudit=new Reaudit();
            reaudit.setVisible(true);
            label_principal.add(reaudit);
            
            System.out.println("new");
        }
        if(reaudit.isIcon()){
            try {
                reaudit.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        reaudit.toFront();
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void ajusboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajusboxActionPerformed
        // TODO add your handling code here:
        if(ajustboxqty==null|| ajustboxqty.isClosed()){
            ajustboxqty=new transferaudit();
            ajustboxqty.setVisible(true);
            label_principal.add(ajustboxqty);
            
            System.out.println("new");
        }
        if(ajustboxqty.isIcon()){
            try {
                ajustboxqty.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        ajustboxqty.toFront();
    }//GEN-LAST:event_ajusboxActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        // TODO add your handling code here:
        if(auditReport==null|| auditReport.isClosed()){
            auditReport=new AuditReport();
            auditReport.setVisible(true);
            label_principal.add(auditReport);
            
            System.out.println("new");
        }
        if(auditReport.isIcon()){
            try {
                auditReport.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        auditReport.toFront();
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        // TODO add your handling code here:
        if(auditsituation==null|| auditsituation.isClosed()){
            auditsituation=new batchSituation();
            auditsituation.setVisible(true);
            label_principal.add(auditsituation);
            
            System.out.println("new");
        }
        if(auditsituation.isIcon()){
            try {
                auditsituation.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            System.out.println("old");
        }
        auditsituation.toFront();
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem29ActionPerformed
        // TODO add your handling code here:
        if(scorecard==null|| scorecard.isClosed()){
            scorecard=new AQL_new();
            scorecard.setVisible(true);
            label_principal.add(scorecard);
            
            System.out.println("new");
        }
        if(scorecard.isIcon()){
            try {
                scorecard.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        scorecard.toFront();
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        // TODO add your handling code here:
        if(warehouse==null|| warehouse.isClosed()){
            warehouse=new Ready_to_ship();
            warehouse.setVisible(true);
            label_principal.add(warehouse);
            
            System.out.println("new");
        }
        if(warehouse.isIcon()){
            try {
                warehouse.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        warehouse.toFront();
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void create_shipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_shipmentActionPerformed
        // TODO add your handling code here:
        AsgShipment asg=new AsgShipment(this,true);
        asg.setLocationRelativeTo(this);
            asg.setVisible(true);
    }//GEN-LAST:event_create_shipmentActionPerformed

    private void scan_boxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scan_boxActionPerformed
        // TODO add your handling code here:
        if(addInShipment==null|| addInShipment.isClosed()){
            addInShipment=new New_shipment();
            addInShipment.setVisible(true);
            label_principal.add(addInShipment);
            
            System.out.println("new");
        }
        if(addInShipment.isIcon()){
            try {
                addInShipment.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        addInShipment.toFront();
    }//GEN-LAST:event_scan_boxActionPerformed

    private void packinglistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packinglistActionPerformed
        // TODO add your handling code here:
        if(packingList==null|| packingList.isClosed()){
            packingList=new Packing_list();
            packingList.setVisible(true);
            label_principal.add(packingList);
            
            System.out.println("new");
        }
        if(packingList.isIcon()){
            try {
                packingList.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        packingList.toFront();
    }//GEN-LAST:event_packinglistActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        // TODO add your handling code here:
        if(agabox==null|| agabox.isClosed()){
            agabox=new Packing_list();
            agabox.setVisible(true);
            label_principal.add(agabox);
            
            System.out.println("new");
        }
        if(agabox.isIcon()){
            try {
                agabox.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        agabox.toFront();
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem32ActionPerformed
        // TODO add your handling code here:
        if(packing_list_warehouse==null|| packing_list_warehouse.isClosed()){
            packing_list_warehouse=new WarehouseReport();
            packing_list_warehouse.setVisible(true);
            label_principal.add(packing_list_warehouse);
            
            System.out.println("new");
        }
        if(packing_list_warehouse.isIcon()){
            try {
                packing_list_warehouse.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        packing_list_warehouse.toFront();
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem33ActionPerformed
        // TODO add your handling code here:
        if(updatepack==null|| updatepack.isClosed()){
            updatepack=new UpdatePackingInfos();
            updatepack.setVisible(true);
            label_principal.add(updatepack);
            
            System.out.println("new");
        }
        if(updatepack.isIcon()){
            try {
                updatepack.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        updatepack.toFront();
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void PACKLISTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PACKLISTActionPerformed
        // TODO add your handling code here:
        if(pack_list==null|| pack_list.isClosed()){
            pack_list=new PackingList_packing();
            pack_list.setVisible(true);
            label_principal.add(pack_list);
            
            System.out.println("new");
        }
        if(pack_list.isIcon()){
            try {
                pack_list.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        pack_list.toFront();
    }//GEN-LAST:event_PACKLISTActionPerformed

    private void list_shipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_shipmentActionPerformed
        // TODO add your handling code here:
        if(shiplist==null|| shiplist.isClosed()){
            shiplist=new PackingList_shipinfos();
            shiplist.setVisible(true);
            label_principal.add(shiplist);
            
            System.out.println("new");
        }
        if(shiplist.isIcon()){
            try {
                shiplist.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        shiplist.toFront();
    }//GEN-LAST:event_list_shipmentActionPerformed

    private void planActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_planActionPerformed
        // TODO add your handling code here:
        if(planification==null|| planification.isClosed()){
            planification=new planification();
            planification.setVisible(true);
            label_principal.add(planification);
            
            System.out.println("new");
        }
        if(planification.isIcon()){
            try {
                planification.setIcon(false);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.println("old");
        }
        planification.toFront();
    }//GEN-LAST:event_planActionPerformed

    private void color_ajustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_color_ajustActionPerformed
        // TODO add your handling code here:
            colorsettup=new AddColorStyleFabric(this, true);
            colorsettup.setVisible(true);
        
    }//GEN-LAST:event_color_ajustActionPerformed

    private void init(){
        connecter=false;
        if(time==null)
        time=new JLabel();
        
        
        if(label==null){
            label=new JLabel();
            label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/icon/userwip.png")));
            
            ppmenu=(int)con_menu.getPreferredSize().getWidth(); 
            label.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
                if(e.getButton()==1){
                    if(con_menu.isShowing())
                        con_menu.setVisible(false);
                    else{
//                        System.out.println("label width:"+wlabel);
//                        System.out.println("label height:"+wheight);
//                        System.out.println("conmenu width:"+ppmenu);
                        con_menu.show(label,e.getX()-(wlabel-ppmenu),wheight);
                    }
                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                label.setBackground(Color.LIGHT_GRAY);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                label.setBackground(null);
            }
        }
        
    );
        }
        label.setOpaque(true);
        label.setVisible(false);
            System.out.println(jMenuBar1.getMenuCount());
            for(int i=0;i<jMenuBar1.getMenuCount();i++){
                JMenu menu=jMenuBar1.getMenu(i);
                //System.out.println(menu.getItemCount());
                if(menu!=null){
                    System.out.println(menu.getName());
                    menulist.put(menu.getName().trim().toLowerCase(),menu);
                    loadMenu(menu);
                menu.setVisible(false);
                    }
            }
            
        jMenuBar1.add(Box.createGlue());
        jMenuBar1.add(time);
        jMenuBar1.add(label);
        jButton8.setVisible(false);
        
        closeAll();
    }
    
    private void loadMenu(JMenu menu){
        for(int j=0;j<menu.getItemCount();j++){
                    JMenuItem comp=menu.getItem(j);
                    
                    if(comp!=null){
                        System.out.println(comp.getName());
                        comp.setVisible(false);
                        try{
                            if(comp instanceof JMenu){
                                
                                System.out.println("comp:"+comp.getName()+" - "+((JMenu)comp).getItemCount());
                                loadMenu(((JMenu)comp));
                            }
                            menulist.put(comp.getName().trim().toLowerCase(),comp);
                        }catch(NullPointerException e){
                            System.err.println("null");
                        }
                    }
                }
    }
    
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Principal prin=new Principal();
                
         //Thread t=new Thread(new threadexemple());
        //t.setDaemon(true);
        //t.start();
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Consolidation;
    private javax.swing.JMenuItem MATCHBOOKREPORT;
    private javax.swing.JMenu MisMenu;
    private javax.swing.JMenuItem PACKLIST;
    private javax.swing.JMenuItem PRESSDREPORT;
    private javax.swing.JMenuItem Progress;
    private javax.swing.JMenu UPLOAD;
    private javax.swing.JMenuItem WASHDREPORT;
    private javax.swing.JMenu WIPMenu;
    private javax.swing.JMenuItem ajusbox;
    private javax.swing.JMenu ajust;
    private javax.swing.JMenu asg_upload_manager;
    private javax.swing.JMenuItem bundle;
    private javax.swing.JMenu chs_upload_manager;
    private javax.swing.JMenuItem closeorder;
    private javax.swing.JMenuItem color_ajust;
    private javax.swing.JPopupMenu con_menu;
    private javax.swing.JMenuItem create_shipment;
    private javax.swing.JMenuItem createbox;
    private javax.swing.JMenu eds_upload_manger;
    private javax.swing.JMenuItem emb;
    private javax.swing.JMenuItem fab_menu;
    private javax.swing.JMenuItem heat;
    private javax.swing.JMenuItem in_proces_sum;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JDesktopPane label_principal;
    private javax.swing.JMenuItem list_shipment;
    private javax.swing.JMenuItem lock;
    private javax.swing.JMenuItem logout;
    private javax.swing.JMenuItem lpn_gbg;
    private javax.swing.JMenuItem pack;
    private javax.swing.JMenuItem pack1;
    private javax.swing.JMenuItem packing_ajust;
    private javax.swing.JMenuItem packinglist;
    private javax.swing.JMenuItem plan;
    private javax.swing.JMenuItem prod_ajust_sew;
    private javax.swing.JMenuItem profil;
    private javax.swing.JMenuItem psp;
    private javax.swing.JMenuItem ready;
    private javax.swing.JMenuItem receive;
    private javax.swing.JMenuItem report_daily_pad;
    private javax.swing.JMenuItem report_daily_sew;
    private javax.swing.JMenuItem report_daily_so;
    private javax.swing.JMenu reports;
    private javax.swing.JMenu reprt;
    private javax.swing.JMenuItem scan_box;
    private javax.swing.JMenuItem second;
    private javax.swing.JMenu settup;
    private javax.swing.JMenuItem sew;
    private javax.swing.JMenuItem sewing_help;
    private javax.swing.JMenuItem transact;
    private javax.swing.JMenuItem travel;
    private javax.swing.JMenuItem update_asg_order;
    private javax.swing.JMenuItem update_mod_transfer;
    private javax.swing.JMenuItem update_sewing_transfer;
    private javax.swing.JMenuItem upload_asg_order;
    private javax.swing.JMenuItem upload_chs_order;
    private javax.swing.JMenuItem upload_eds_order;
    private javax.swing.JMenuItem upload_wcs_order;
    private javax.swing.JMenuItem user_manager;
    private javax.swing.JMenu wcs_upload_manager;
    private javax.swing.JMenuItem wip;
    private javax.swing.JMenuItem wp_only;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JLabel label,time;
    @Override
    public void executer(Object... obs) {
        if(obs[0].equals("connection network")){
            //System.out.println("close");
            Boolean con=(Boolean)obs[1];
            
            if(con!=true){
                System.err.println(con);
                if(connecter)
                {
                    label.setText("Disconnected");
                    label.setForeground(Color.red);
                    closeAll();
                    lock();
                }
            }
            
        }
        if(obs[0].equals("Connected")){
            if(t.isInterrupted())
                t.start();
            
            try(ResultSet rs=conn2.select("select staff_id from aga_staffs where firstname=? and lastname=?", obs[3].toString(),obs[4].toString())){
                while(rs.next())
                    staff_id=rs.getInt("staff_id");
            }catch(SQLException e){
                e.printStackTrace();
            }
            label.setText(obs[3].toString()+" "+obs[4].toString());
            label.setVisible(true);
            user_id=Integer.parseInt(obs[1].toString());
            group=obs[6].toString();
            connecter=true;
            username=obs[2].toString();
            if(group.trim().equalsIgnoreCase("MIS")){
                menulist.forEach((text, u) -> {
                    System.out.println(text);
                    u.setVisible(true);
                });
            }else{
            Object[] Mainmenu=getDroits(user_id);
            for(int i=0;i<Mainmenu.length;i++){
                try{
                menulist.get(Mainmenu[i].toString().trim().toLowerCase()).setVisible(true);
                }catch(Exception e){
                    System.err.println("error:"+e.getMessage());
                }
            }
            }
            if(sew.isVisible()){
                sew.setEnabled(false);
                jMenuItem5.setEnabled(false);
            }
            auth.setModal(false);
            auth.setVisible(false);
            lockframe.setModal(false);
            lockframe.setVisible(false);
            this.requestFocus();
            wlabel=(int)label.getPreferredSize().getWidth();
            wheight=(int)label.getPreferredSize().getHeight();
            
            
        }
        
        if(obs[0].equals("closed")){
            //System.out.println("close");
            prod_ajust_sew.setVisible(false);
            packing_ajust.setVisible(false);
            try{
            if(sewing_ajust!=null || !sewing_ajust.isClosed()){
                sewing_ajust.dispose();
            }
            }catch(NullPointerException e){
                //System.out.println("closed");
            }
            try{
            if(packajust!=null || !packajust.isClosed()){
                packajust.dispose();
            }
            }catch(NullPointerException e){
                //System.out.println("closed");
            }
            sew.setEnabled(true);
            jMenuItem5.setEnabled(true);
            this.revalidate();
        }
        
        if(obs[0].equals("closed packing")){
            //System.out.println("close");
            packing_ajust.setVisible(false);
            try{
            if(packajust!=null || !packajust.isClosed()){
                packajust.dispose();
            }
            }catch(NullPointerException e){
                //System.out.println("closed");
            }
            jMenuItem5.setEnabled(true);
            this.revalidate();
        }
        
        if(obs[0].equals("stat")){
            if(connecter){
                if(active)
                    unactive_sec=0;
               unactive_sec++;
               if(unactive_sec==900){
                   connecter=false;
                    lock();
                    
               }
                
            }
        }
        if(obs[0].equals("change user")){
            logout.doClick();
            lockframe.setModal(false);
            lockframe.setVisible(false);
        }
        if(obs[0].equals("timer")){
            time.setText(timeFormat.format((Date)obs[1]));
        }
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
        for(Observateurs o:obs){
            o.executer(ob);
        }
    }
    private void closeAll(){
        JInternalFrame[] fr=label_principal.getAllFrames();
        for(int i=0;i<fr.length;i++){
            if(fr[i]!=null&& !fr[i].isClosed()){
                fr[i].dispose();
            }
        }
    }
    
    private void lock(){
        System.out.println("user:"+username);
        alerter("lock",username);
        lockframe.setModal(true);
        lockframe.setVisible(true);
        t.currentThread().run();
    }
    
    private Object[] getDroits(int user_id){
        Object [] droits=null;
        try{
        String sql="select * from roles_user where user_id=?";
        ResultSet rs1 = conn.select(sql,user_id);
                        rs1.last();
                        droits=new Object[rs1.getRow()];
                        rs1.beforeFirst();
                        int i=0;
                        while (rs1.next()) {
                            droits[i]=rs1.getString("field");
                            i++;
                        }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return droits;
    }
}
