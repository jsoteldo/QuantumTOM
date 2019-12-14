
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Objeciones;
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
public class ObjecionesDAO extends DAO {
    
    private Logger log = LoggerFactory.getLogger(ObjecionesDAO.class);  
    
    public Mensaje registrar(Objeciones objeciones) throws Exception {
        Mensaje validosesion;
        
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO objeciones (TIPOBJECION, CODIGO, OBJECION) VALUES (?,?,?)");
            declaracion.setString(1,objeciones.getTipobjecion());
            declaracion.setString(2,objeciones.getCodigo());
            declaracion.setString(3,objeciones.getObjecion());
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
    
    public List<Objeciones> listar() throws Exception {
        List<Objeciones> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT TIPOBJECION, CODIGO, OBJECION FROM objeciones");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Objeciones objecion = new Objeciones();
                objecion.setTipobjecion(resultado.getString("TIPOBJECION"));
                objecion.setCodigo(resultado.getString("CODIGO"));
                objecion.setObjecion(resultado.getString("OBJECION"));
                lista.add(objecion);
            }
            
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    public void borrar(Objeciones objecion) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM objeciones WHERE CODIGO = ? and TIPOBJECION = ?");
            declaracion.setString(1, objecion.getCodigo());
            declaracion.setString(2, objecion.getTipobjecion());
            declaracion.executeUpdate();
        } catch (Exception e) {
           log.info(e.getMessage());
            
        } finally {
            this.Cancelar();
        }

    }
    
    public Mensaje modificar(Objeciones objecion) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE objeciones set OBJECION = ? WHERE CODIGO = ? and TIPOBJECION = ?");

            declaracion.setString(1, objecion.getObjecion());
            declaracion.setString(3, objecion.getTipobjecion());
            declaracion.setString(2, objecion.getCodigo());
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
    
    public List<Objeciones> tipoObjeciones(String opcion) throws Exception {
        List<Objeciones> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT TIPOBJECION, CODIGO, OBJECION FROM objeciones " 
                    + "WHERE TIPOBJECION = ? ");
            declaracion.setString(1, opcion);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Objeciones objeciones = new Objeciones();
                objeciones.setTipobjecion(resultado.getString("TIPOBJECION"));
                objeciones.setCodigo(resultado.getString("CODIGO"));
                objeciones.setObjecion(resultado.getString("OBJECION"));
                lista.add(objeciones);
            }
            
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }       
}
