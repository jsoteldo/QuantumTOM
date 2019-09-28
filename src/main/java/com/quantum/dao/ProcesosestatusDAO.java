
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Procesosestatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class ProcesosestatusDAO extends DAO {
    
    public Mensaje registrar(Procesosestatus procesosestatus) throws Exception {
        Mensaje validosesion;
        
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO procesoestatus (ESTATUS, CODPRO, PROCESO) VALUES (?,?,?)");
            declaracion.setString(1,procesosestatus.getEstatus());
            declaracion.setString(2,procesosestatus.getCodpro());
            declaracion.setString(3,procesosestatus.getProceso());
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
    
    public List<Procesosestatus> listar() throws Exception {
        List<Procesosestatus> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ESTATUS, CODPRO, PROCESO FROM procesoestatus");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Procesosestatus procesosestatus = new Procesosestatus();
                procesosestatus.setEstatus(resultado.getString("ESTATUS"));
                procesosestatus.setCodpro(resultado.getString("CODPRO"));
                procesosestatus.setProceso(resultado.getString("PROCESO"));
                lista.add(procesosestatus);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    public List<Procesosestatus> procesosEstatus(String opcion) throws Exception {
        List<Procesosestatus> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ESTATUS, CODPRO, PROCESO FROM procesoestatus "
                    + "WHERE ESTATUS = ? ");
            declaracion.setString(1, opcion);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Procesosestatus procesosestatus = new Procesosestatus();
                procesosestatus.setEstatus(resultado.getString("ESTATUS"));
                procesosestatus.setCodpro(resultado.getString("CODPRO"));
                procesosestatus.setProceso(resultado.getString("PROCESO"));
                lista.add(procesosestatus);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }        
    
    public void borrar(Procesosestatus proceso) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM procesoestatus WHERE CODPRO = ? and ESTATUS = ? ");
            declaracion.setString(1, proceso.getCodpro());
            declaracion.setString(2, proceso.getEstatus());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(Procesosestatus proceso) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE procesoestatus set PROCESO = ? WHERE CODPRO = ? and ESTATUS = ?");

            declaracion.setString(1, proceso.getProceso());
            declaracion.setString(2, proceso.getCodpro());
            declaracion.setString(3, proceso.getEstatus());
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
