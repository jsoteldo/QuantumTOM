package com.quantum.dao;

import com.quantum.bean.ObjecionesBean;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class DAO {

    private Connection conexion;
    private org.slf4j.Logger log = LoggerFactory.getLogger(DAO.class);   
    

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void Conectar() throws IOException, Exception {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
             conexion = DriverManager.getConnection("jdbc:mariadb://173.82.238.24:3306/test", "flamencos", "$flamencosQuantum");
        
            //conexion = DriverManager.getConnection("jdbc:mariadb://192.168.3.17:3306/test", "flamencos", "$flamencosQuantum");
            //conexion = DriverManager.getConnection("jdbc:mariadb://node48103-env-3675608.jl.serv.net.mx/test", "flamencos", "$flamencosQuantum");

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
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
            log.info(e.getMessage());
            throw e;
        }
    }
}
