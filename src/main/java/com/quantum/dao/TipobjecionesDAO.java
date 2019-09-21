package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Objeciones;
import com.quantum.modelos.TipObjeciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class TipobjecionesDAO extends DAO {

    public Mensaje registrar(TipObjeciones tipobjecion) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO TIPOBJECION (CODIGO, TIPO) VALUES (?,?)");
            declaracion.setString(1, tipobjecion.getCodigo());
            declaracion.setString(2, tipobjecion.getTipo());
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

    public List<TipObjeciones> listar() throws Exception {
        List<TipObjeciones> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, TIPO FROM TIPOBJECION");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                TipObjeciones tipobjecion = new TipObjeciones();
                tipobjecion.setCodigo(resultado.getString("CODIGO"));
                tipobjecion.setTipo(resultado.getString("TIPO"));
                lista.add(tipobjecion);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    public Mensaje borrar(TipObjeciones tipobjecion) throws Exception {
        
        Mensaje validosesion;
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM TIPOBJECION WHERE CODIGO = ? ");
            declaracion.setString(1, tipobjecion.getCodigo());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Eliminado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(TipObjeciones tipobjecion) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE TIPOBJECION set TIPO = ? WHERE CODIGO = ?");

            declaracion.setString(1, tipobjecion.getTipo());
            declaracion.setString(2, tipobjecion.getCodigo());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Actualizado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
    }
    
     
}
