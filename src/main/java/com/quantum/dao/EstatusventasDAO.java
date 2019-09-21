
package com.quantum.dao;

import com.quantum.modelos.Estatusventas;
import com.quantum.modelos.Mensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class EstatusventasDAO extends DAO{
    
    public Mensaje registrar(Estatusventas estatus) throws Exception {
        Mensaje validosesion;
        
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO ESTATUSVENTAS (CODIGO, ESTATUS) VALUES (?,?)");
            declaracion.setString(1,estatus.getCodigo());
            declaracion.setString(2,estatus.getEstatus());
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
    
    
    public List<Estatusventas> listar() throws Exception {
        List<Estatusventas> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, ESTATUS FROM ESTATUSVENTAS");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Estatusventas estatus = new Estatusventas();
                estatus.setCodigo(resultado.getString("CODIGO"));
                estatus.setEstatus(resultado.getString("ESTATUS"));
                lista.add(estatus);
            }
            
        } catch (Exception e) {
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
                    + "DELETE FROM ESTATUSVENTAS WHERE CODIGO = ?");
            declaracion.setString(1, estatus.getCodigo());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(Estatusventas estatus) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE ESTATUSVENTAS set ESTATUS = ? WHERE CODIGO = ?");

            declaracion.setString(1, estatus.getEstatus());
            declaracion.setString(2, estatus.getCodigo());
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
