/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connection;

import alert.Alert;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe gerant toutes les requetes sql
 * @author Padovano
 */
public class ConnectionDb {
    private static ConnectionDb con=null;    
//    private String user="sa";
//    private String pass="that'sme";//IW2012R2-DC\\SQLEXPRESSIW
    //private String Url="jdbc:sqlserver://localhost;databaseName=wip_archive";
    private String user="iw";
    private String pass="passwordiw";//IW2012R2-DC\\SQLEXPRESSIW
    private String Url="jdbc:sqlserver://192.168.90.161\\SQLEXPRESSIW;databaseName=wip_archive";
    private Statement state=null;
    private ResultSet resultat;
    private Connection connection=null;
    private int lastinsert=0;
    private String erreur;
    
    private ConnectionDb(){
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
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
    public static synchronized ConnectionDb instance(){
        if(con==null||con.closed())
            con=new ConnectionDb();
        
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
            Alert.error(null, ex.getMessage());
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
            Alert.error(null, ex.getMessage());
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
            Alert.error(null, ex.getMessage());
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
            return true;
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
                Alert.error(null, ex.getMessage());
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
    
    public synchronized List<Map<String,Object>> selectlist(String query, Object... critere){

        List<Map<String,Object>> list= new ArrayList<>();
        try {
            PreparedStatement prepare=connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            int i=1;
            for(Object ob:critere){
                prepare.setObject(i, ob);
                i++;
            }
            ResultSetMetaData metadata=prepare.getMetaData();

            resultat=prepare.executeQuery();
            while(resultat.next()){
                //Object ob=clas.newInstance();
                Map<String,Object> obj=new LinkedHashMap<>();
                for(int j=1;j<=metadata.getColumnCount();j++){
                    //utility.hydrated(ob, metadata.getColumnName(j), resultat.getObject(j,Class.forName(metadata.getColumnClassName(j))));
                    if(resultat.getObject(j)!=null)
                        obj.put(metadata.getColumnName(j),resultat.getObject(metadata.getColumnName(j)));
                }
                list.add(obj);
            }
            resultat.close();
            prepare.closeOnCompletion();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, ex);
            this.setErreur(ex.getMessage());
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(ConnectionDb.class.getName()).log(Level.SEVERE, null, e);
            }
            Alert.error(null,ex.getMessage());
        }finally{
            
            instance();
        }
//        catch (ClassNotFoundException ex) {
//            Logger.getLogger(ConnectionSql.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return list;
    }

    
}
    
