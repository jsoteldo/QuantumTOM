
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Parcelas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class ParcelasDAO extends DAO{
    
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
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }
    
    
}   
