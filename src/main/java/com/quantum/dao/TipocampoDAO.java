
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Tipocampo;
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
public class TipocampoDAO extends DAO {
    
     private Logger log = LoggerFactory.getLogger(RolesDAO.class);  
    
    
    public Mensaje registrar(Tipocampo tipo) throws Exception {
         Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO tipocampo (TIPO, VALOR) VALUES (?,?)");
            declaracion.setString(1,tipo.getTipo());
            declaracion.setString(2,tipo.getValor());
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
    
    public List<Tipocampo> listar() throws Exception {
        List<Tipocampo> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT TIPO, VALOR FROM tipocampo");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Tipocampo tipo = new Tipocampo();
                tipo.setTipo(resultado.getString("TIPO"));
                tipo.setValor(resultado.getString("VALOR"));
                lista.add(tipo);
            }
            
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    public Mensaje borrar(Tipocampo tipo) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM tipocampo WHERE TIPO = ?");
            declaracion.setString(1, tipo.getTipo());
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
    
    public Mensaje modificar(Tipocampo tipo) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE tipocampo set VALOR = ? WHERE TIPO = ?");

            declaracion.setString(1, tipo.getValor());
            declaracion.setString(2, tipo.getTipo());
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
