package com.quantum.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author QUANTUM
 */
public class DAO {

    private Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void Conectar() throws Exception {
        try {
            Properties prop = new Properties();
            System.out.println("\n\n=======================\nJDBC Connector Test ");
            System.out.println("User home directory: " + System.getProperty("user.home"));
            
            prop.load(new java.io.FileInputStream(System.getProperty("user.home") + "/mydb.cfg"));
            
            
            Class.forName(prop.getProperty("driver").toString());
            
            conexion = DriverManager.getConnection(prop.getProperty("host").toString(), prop.getProperty("username").toString(), prop.getProperty("password").toString());

        
            //conexion = DriverManager.getConnection("jdbc:mariadb://192.168.3.17:3306/test", "flamencos", "$flamencosQuantum");
            //conexion = DriverManager.getConnection("jdbc:mariadb://node48103-env-3675608.jl.serv.net.mx/test", "flamencos", "$flamencosQuantum");

        } catch (Exception e) {
            throw e;
        }
        catch (IOException e) {
            System.out.println("Unable to find mydb.cfg in " + System.getProperty("user.home") + "\n Please make sure that configuration file created in this folder.");
             
        }
    }

    public void Cancelar() throws Exception {
        try {
            if (conexion != null) {
                if (conexion.isClosed() == false) {
                    conexion.close();
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
