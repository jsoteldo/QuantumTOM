package com.quantum.dao;

import java.sql.Connection;
import java.sql.DriverManager;

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
            Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "flamencos", "$flamencosQuantum");
        } catch (Exception e) {
            throw e;
        }
    }

    public void Cancelar() throws Exception {
        try {
            if (conexion != null){
                if(conexion.isClosed() == false){
                    conexion.close();
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
