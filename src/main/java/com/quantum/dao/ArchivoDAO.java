package com.quantum.dao;

import com.quantum.modelos.Archivo;
import com.quantum.modelos.Mensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class ArchivoDAO extends DAO {

    public Mensaje registrar(Archivo archivo) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO ARCHIVO (NAME, DIR, FECHA, PROCESADO) VALUES (?,?,?,?)");
            declaracion.setString(1, archivo.getName());
            declaracion.setString(2, archivo.getDir());
            declaracion.setString(3, archivo.getFecha());
            declaracion.setString(4, archivo.getProcesado());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Registrado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }

    }

    public Archivo consultaarchivo() throws Exception {
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT NAME, DIR, FECHA, PROCESADO FROM ARCHIVO "
                    + "WHERE FECHA in (SELECT MAX(STR_TO_DATE(FECHA,'%Y-%m-%d %H:%i:%s')) FROM ARCHIVO )");
            resultado = declaracion.executeQuery();
            Archivo arch = new Archivo();
            while (resultado.next()) {

                arch.setName(resultado.getString("NAME"));
                arch.setDir(resultado.getString("DIR"));
                arch.setFecha(resultado.getString("FECHA"));
                arch.setProcesado(resultado.getString("PROCESADO"));
            }

            return arch;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public void borrar(Archivo archivo) throws Exception {
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM ARCHIVO WHERE NAME = ?");
            declaracion.setString(1, archivo.getName());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }

    public Mensaje procesar() throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE ARCHIVO set ARCHIVO.PROCESADO = ? WHERE ARCHIVO.FECHA in (SELECT MAX(STR_TO_DATE(m2.FECHA,'%Y-%m-%d %H:%i:%s')) FROM (select * from ARCHIVO) AS m2 )");

            declaracion.setString(1, "true");
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Archivo Procesado.", "mdi-checkbox-marked-circle-outline", "success");
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            
        } finally {
            this.Cancelar();
        }
        return validosesion;
    }

}
