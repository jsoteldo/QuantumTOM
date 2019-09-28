
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Motivos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class MotivosDAO extends DAO {
    
    public Mensaje registrar(Motivos motivos) throws Exception {
        Mensaje validosesion;
        
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO motivos (ESTADO, CODMOT, MOTIVO, PERMITELLAMAR, REUNIONOFICINA, VISITAPROYECTO) VALUES (?,?,?,?,?,?)");
            declaracion.setString(1,motivos.getEstado());
            declaracion.setString(2,motivos.getCodmot());
            declaracion.setString(3,motivos.getMotivo());
            declaracion.setString(4,motivos.getPermitellamar());
            declaracion.setString(5,motivos.getReunionoficina());
            declaracion.setString(6,motivos.getVisitaproyecto());
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
    
    public List<Motivos> listar() throws Exception {
        List<Motivos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ESTADO, CODMOT, MOTIVO, PERMITELLAMAR, REUNIONOFICINA, VISITAPROYECTO FROM motivos");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Motivos motivos = new Motivos();
                motivos.setEstado(resultado.getString("ESTADO"));
                motivos.setCodmot(resultado.getString("CODMOT"));
                motivos.setMotivo(resultado.getString("MOTIVO"));
                motivos.setPermitellamar(resultado.getString("PERMITELLAMAR"));
                motivos.setReunionoficina(resultado.getString("REUNIONOFICINA"));
                motivos.setVisitaproyecto(resultado.getString("VISITAPROYECTO"));
                lista.add(motivos);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    public List<Motivos> procesosEstados(String opcion) throws Exception {
        List<Motivos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ESTADO, CODMOT, MOTIVO, PERMITELLAMAR, REUNIONOFICINA, VISITAPROYECTO FROM motivos "
                    + "WHERE ESTADO = ? ");
            declaracion.setString(1, opcion);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Motivos motivos = new Motivos();
                motivos.setEstado(resultado.getString("ESTADO"));
                motivos.setCodmot(resultado.getString("CODMOT"));
                motivos.setMotivo(resultado.getString("MOTIVO"));
                motivos.setPermitellamar(resultado.getString("PERMITELLAMAR"));
                motivos.setReunionoficina(resultado.getString("REUNIONOFICINA"));
                motivos.setVisitaproyecto(resultado.getString("VISITAPROYECTO"));
                lista.add(motivos);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }        
    
    public void borrar(Motivos proceso) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM motivos WHERE CODMOT = ? and ESTADO = ? ");
            declaracion.setString(1, proceso.getCodmot());
            declaracion.setString(2, proceso.getEstado());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(Motivos proceso) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE motivos set MOTIVO = ?, PERMITELLAMAR = ?, REUNIONOFICINA = ?"
                    + ", VISITAPROYECTO = ? WHERE CODMOT = ? and ESTADO = ?");

            declaracion.setString(1, proceso.getMotivo());
            declaracion.setString(2, proceso.getPermitellamar());
            declaracion.setString(3, proceso.getReunionoficina());
            declaracion.setString(4, proceso.getVisitaproyecto());
            declaracion.setString(5, proceso.getCodmot());
            declaracion.setString(6, proceso.getEstado());
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
    
    public Motivos motivo(String codigo, String estado) throws Exception {
        ResultSet resultado;
        Motivos motivo = new Motivos();
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ESTADO, CODMOT, MOTIVO, PERMITELLAMAR, REUNIONOFICINA, VISITAPROYECTO FROM motivos "
                    + " WHERE CODMOT = ? "
                    + " AND ESTADO = ? ");
            declaracion.setString(1, codigo);
            declaracion.setString(2, estado);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                
                motivo.setCodmot(resultado.getString("CODMOT"));
                motivo.setEstado(resultado.getString("ESTADO"));
                motivo.setMotivo(resultado.getString("MOTIVO"));
                motivo.setPermitellamar(resultado.getString("PERMITELLAMAR"));
                motivo.setReunionoficina(resultado.getString("REUNIONOFICINA"));
                motivo.setVisitaproyecto(resultado.getString("VISITAPROYECTO"));
                
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return motivo;
    }
}
