
package com.quantum.dao;

import com.quantum.modelos.Estado;
import com.quantum.modelos.Mensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class EstadosDAO extends DAO{
    
    public Mensaje registrar(Estado estado) throws Exception {
        Mensaje validosesion;
        
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO estados (CODIGO, ESTADO) VALUES (?,?)");
            declaracion.setString(1,estado.getCodigo());
            declaracion.setString(2,estado.getEstado());
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
    
    
    public List<Estado> listar() throws Exception {
        List<Estado> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, ESTADO, PERMITELLAMAR, MOTIVOS, CERRADA FROM estados");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Estado estado = new Estado();
                estado.setCodigo(resultado.getString("CODIGO"));
                estado.setEstado(resultado.getString("ESTADO"));
                estado.setPermitellamar(resultado.getString("PERMITELLAMAR"));
                estado.setMotivo(resultado.getString("MOTIVOS"));
                estado.setCerrada(resultado.getString("CERRADA"));
                lista.add(estado);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    
    public Estado estado(String codigo) throws Exception {
        ResultSet resultado;
        Estado estado = new Estado();
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, ESTADO, PERMITELLAMAR, MOTIVOS, CERRADA FROM estados WHERE CODIGO = ?");
            declaracion.setString(1, codigo);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                
                estado.setCodigo(resultado.getString("CODIGO"));
                estado.setEstado(resultado.getString("ESTADO"));
                estado.setPermitellamar(resultado.getString("PERMITELLAMAR"));
                estado.setMotivo(resultado.getString("MOTIVOS"));
                estado.setCerrada(resultado.getString("CERRADA"));
                
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return estado;
    }
    
    public void borrar(Estado estado) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM estados WHERE CODIGO = ?");
            declaracion.setString(1, estado.getCodigo());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(Estado estado) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE estados set  ESTADO = ? WHERE CODIGO = ?");

            declaracion.setString(1, estado.getEstado());
            declaracion.setString(2, estado.getCodigo());
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
    
    public String colorestado(String estado) throws Exception {
        String color;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT COLOR FROM estados WHERE ESTADO = ?");
            declaracion.setString(1, estado);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                
                color = resultado.getString("COLOR");
                return color;
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return null;
        
    }
    
}   
