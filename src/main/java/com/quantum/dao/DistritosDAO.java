
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Distritos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class DistritosDAO extends DAO {
    private org.slf4j.Logger log = LoggerFactory.getLogger(DistritosDAO.class);
    
    public Mensaje registrar(Distritos distritos) throws Exception {
         Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO distritos (CODIGO, DISTRITO) VALUES (?,?)");
            declaracion.setString(1,distritos.getCodigo());
            declaracion.setString(2,distritos.getDistrito());
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
    
    public List<Distritos> listar() throws Exception {
        List<Distritos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, DISTRITO FROM distritos");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Distritos origen = new Distritos();
                origen.setCodigo(resultado.getString("CODIGO"));
                origen.setDistrito(resultado.getString("DISTRITO"));
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
    
    public void borrar(Distritos origen) throws Exception {
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM distritos WHERE CODIGO = ?");
            declaracion.setString(1, origen.getCodigo());
            declaracion.executeUpdate();
        } catch (Exception e) {
            log.info(e.getMessage());
            
        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(Distritos origen) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE distritos set DISTRITO = ? WHERE CODIGO = ?");

            declaracion.setString(1, origen.getDistrito());
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
