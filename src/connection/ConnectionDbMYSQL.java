/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe gerant toutes les requetes sql
 * @author Padovano
 */
public class ConnectionDbMYSQL {
    private static ConnectionDbMYSQL con=null;    
    private String user="iw";
    private String pass="iw@password";//DESKTOP-V6D55MO
    private String Url="jdbc:mysql://192.168.90.161/aga_prdb_iw";
    private Statement state=null;
    private ResultSet resultat;
    private Connection connection=null;
    private int lastinsert=0;
    private String erreur;
    
    private ConnectionDbMYSQL(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
    Url, user, pass);
            
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.OFF, "succes");
            System.out.println("Success");
            System.out.println("status:"+connection.getClientInfo().toString());
    }catch(SQLException | ClassNotFoundException ex){
        System.out.println(ex.getMessage());
        setErreur(ex.getMessage());
    }
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }
    /*
    *on utilise le Pattern singleton
    *
    */
    public static synchronized ConnectionDbMYSQL instance(){
        if(con==null||con.closed())
            con=new ConnectionDbMYSQL();
        
        return con;
    }
    
    public boolean closed(){
        try {
            
            return connection.isClosed();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void close(){
        try {
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized ResultSet select(String query,Object... critere){
        try {
            PreparedStatement prepare=connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int i=1;
            for(Object ob:critere){
                prepare.setObject(i, ob);
                i++;
            }
            resultat=prepare.executeQuery();
            prepare.closeOnCompletion();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getErrorCode()+ex.getSQLState());
            this.setErreur(ex.getMessage());
            instance();
        }
       return resultat; 
    }
    
    public synchronized boolean Update(String query,int auto,Object... critere){
        boolean check=false;
        try {
            PreparedStatement prepare=connection.prepareStatement(query, auto);
            int i=1;
            for(Object ob:critere){
                prepare.setObject(i, ob);
                i++;
            }
            if(prepare.executeUpdate()>0){
                check=true;
                if(auto==1){
                    ResultSet rs=prepare.getGeneratedKeys();
                    while(rs.next())
                    lastinsert=(int)rs.getInt(1);
                }
                    
            }
            prepare.closeOnCompletion();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            this.setErreur(ex.getMessage());
            instance();
        }
       return check; 
    }
    
    public synchronized ResultSet lirecst(String procedure,Object... params){
        try {
            CallableStatement c=connection.prepareCall(procedure, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            for(int i=0;i<params.length;i++)
                c.setObject(i, params[i]);
            
            ResultSet rs=c.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
            this.setErreur(ex.getMessage());
        }
        return null;
    }
    
    public synchronized boolean savecst(String procedure,Object... params){
        CallableStatement c=null;
        try {
            c=connection.prepareCall(procedure);
            for(int i=0;i<params.length;i++)
                c.setObject(i+1, params[i]);
            
            c.executeUpdate();
            //System.err.println("result:");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
            this.setErreur(ex.getMessage());
        }finally{
            try {
                if(c!=null)
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
                this.setErreur(ex.getMessage());
            }
            
        }
        return false;
    }
    public int getLast(){
        return lastinsert;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
}
    
