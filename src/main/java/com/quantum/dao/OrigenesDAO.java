
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Origenes;
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
public class OrigenesDAO extends DAO {
    
    private Logger log = LoggerFactory.getLogger(OrigenesDAO.class);  
    
    public Mensaje registrar(Origenes origenes) throws Exception {
         Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO origenes (CODIGO, ORIGEN) VALUES (?,?)");
            declaracion.setString(1,origenes.getCodigo());
            declaracion.setString(2,origenes.getOrigen());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Registrado Exitosamente.","mdi-checkbox-marked-circle-outline","success");
            return validosesion;
        } catch (Exception e) {
            log.info(e.getMessage());
            validosesion = new Mensaje("", e.getMessage(),"mdi-close-circle-outline","danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
        
    }
    
    public List<Origenes> listar() throws Exception {
        List<Origenes> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, ORIGEN FROM origenes");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Origenes origen = new Origenes();
                origen.setCodigo(resultado.getString("CODIGO"));
                origen.setOrigen(resultado.getString("ORIGEN"));
                lista.add(origen);
            }
            
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    public void borrar(Origenes origen) throws Exception {
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM origenes WHERE CODIGO = ?");
            declaracion.setString(1, origen.getCodigo());
            declaracion.executeUpdate();
        } catch (Exception e) {
            log.info(e.getMessage());
            
        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(Origenes origen) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE origenes set ORIGEN = ? WHERE CODIGO = ?");

            declaracion.setString(1, origen.getOrigen());
            declaracion.setString(2, origen.getCodigo());
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
