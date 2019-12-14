
package com.quantum.dao;

import com.quantum.modelos.Estatusventas;
import com.quantum.modelos.Mensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class EstatusventasDAO extends DAO{
    
    private org.slf4j.Logger log = LoggerFactory.getLogger(EstatusventasDAO.class);
    
    public Mensaje registrar(Estatusventas estatus) throws Exception {
        Mensaje validosesion;
        
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO estatusventas (CODIGO, ESTATUS) VALUES (?,?)");
            declaracion.setString(1,estatus.getCodigo());
            declaracion.setString(2,estatus.getEstatus());
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
    
    
    public List<Estatusventas> listar() throws Exception {
        List<Estatusventas> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, ESTATUS FROM estatusventas");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Estatusventas estatus = new Estatusventas();
                estatus.setCodigo(resultado.getString("CODIGO"));
                estatus.setEstatus(resultado.getString("ESTATUS"));
                lista.add(estatus);
            }
            
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    public void borrar(Estatusventas estatus) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM estatusventas WHERE CODIGO = ?");
            declaracion.setString(1, estatus.getCodigo());
            declaracion.executeUpdate();
        } catch (Exception e) {
            log.info(e.getMessage());
            
        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(Estatusventas estatus) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE estatusventas set ESTATUS = ? WHERE CODIGO = ?");

            declaracion.setString(1, estatus.getEstatus());
            declaracion.setString(2, estatus.getCodigo());
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
