
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Parcelas;
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
public class ParcelasDAO extends DAO{
    
    private Logger log = LoggerFactory.getLogger(ParcelasDAO.class);  
    
    public List<Parcelas> listar() throws Exception {
        List<Parcelas> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT PARCELA FROM parcelas");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Parcelas parcela = new Parcelas();
                parcela.setParcela(resultado.getString("PARCELA"));
                lista.add(parcela);
            }
            
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
   public Mensaje registrar(Parcelas parcela) throws Exception {
        Mensaje validosesion;
        
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO parcelas (PARCELA) VALUES (?)");
            declaracion.setString(1,parcela.getParcela());
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
    
    public void borrar(Parcelas parcela) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM parcelas WHERE PARCELA = ?");
            declaracion.setString(1, parcela.getParcela());
            declaracion.executeUpdate();
        } catch (Exception e) {
            log.info(e.getMessage());
            
        } finally {
            this.Cancelar();
        }

    }
    
    
}   
