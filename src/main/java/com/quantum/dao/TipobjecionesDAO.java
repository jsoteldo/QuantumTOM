package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Objeciones;
import com.quantum.modelos.TipObjeciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class TipobjecionesDAO extends DAO {

     private Logger log = LoggerFactory.getLogger(TipobjecionesDAO.class);  
     
    public Mensaje registrar(TipObjeciones tipobjecion) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO tipobjecion (CODIGO, TIPO) VALUES (?,?)");
            declaracion.setString(1, tipobjecion.getCodigo());
            declaracion.setString(2, tipobjecion.getTipo());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Registrado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            log.info(e.getMessage());
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
                    + "SELECT CODIGO, TIPO FROM tipobjecion");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                TipObjeciones tipobjecion = new TipObjeciones();
                tipobjecion.setCodigo(resultado.getString("CODIGO"));
                tipobjecion.setTipo(resultado.getString("TIPO"));
                lista.add(tipobjecion);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
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
                    + "DELETE FROM tipobjecion WHERE CODIGO = ? ");
            declaracion.setString(1, tipobjecion.getCodigo());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Eliminado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            log.info(e.getMessage());
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
                    + "UPDATE tipobjecion set TIPO = ? WHERE CODIGO = ?");

            declaracion.setString(1, tipobjecion.getTipo());
            declaracion.setString(2, tipobjecion.getCodigo());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Actualizado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            log.info(e.getMessage());
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
    }
    
     
}
