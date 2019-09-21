
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Tipocampo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class TipocampoDAO extends DAO {
    
    public Mensaje registrar(Tipocampo tipo) throws Exception {
         Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO TIPOCAMPO (TIPO, VALOR) VALUES (?,?)");
            declaracion.setString(1,tipo.getTipo());
            declaracion.setString(2,tipo.getValor());
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
    
    public List<Tipocampo> listar() throws Exception {
        List<Tipocampo> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT TIPO, VALOR FROM TIPOCAMPO");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Tipocampo tipo = new Tipocampo();
                tipo.setTipo(resultado.getString("TIPO"));
                tipo.setValor(resultado.getString("VALOR"));
                lista.add(tipo);
            }
            
        } catch (Exception e) {
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
                    + "DELETE FROM TIPOCAMPO WHERE TIPO = ?");
            declaracion.setString(1, tipo.getTipo());
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
    
    public Mensaje modificar(Tipocampo tipo) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE TIPOCAMPO set VALOR = ? WHERE TIPO = ?");

            declaracion.setString(1, tipo.getValor());
            declaracion.setString(2, tipo.getTipo());
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
