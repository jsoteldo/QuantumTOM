package com.quantum.dao;

import com.quantum.modelos.Campanas;
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
public class CampanasDAO extends DAO {

    private org.slf4j.Logger log = LoggerFactory.getLogger(CampanasDAO.class);
    
    public Mensaje registrar(Campanas campanas) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO campanas (CODIGO, NOMBRE, DESCRIPCION) VALUES (?,?,?)");
            declaracion.setString(1, campanas.getCodigo());
            declaracion.setString(2, campanas.getNombre());
            declaracion.setString(3, campanas.getDescripcion());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Registrado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            log.info(e.getMessage());
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }

    }

    public List<Campanas> listar() throws Exception {
        List<Campanas> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, NOMBRE, DESCRIPCION FROM campanas");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Campanas campana = new Campanas();
                campana.setCodigo(resultado.getString("CODIGO"));
                campana.setNombre(resultado.getString("NOMBRE"));
                campana.setDescripcion(resultado.getString("DESCRIPCION"));
                lista.add(campana);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Campanas consultacampana(String idcampana) throws Exception {
        Campanas campanaconsultado = new Campanas();
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, NOMBRE, DESCRIPCION FROM campanas "
                    + "WHERE CODIGO = ?");
            declaracion.setString(1, idcampana);

            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                campanaconsultado.setCodigo(resultado.getString("CODIGO"));
                campanaconsultado.setNombre(resultado.getString("NOMBRE"));
                campanaconsultado.setDescripcion(resultado.getString("DESCRIPCION"));
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return campanaconsultado;
    }

    public void borrar(Campanas campanas) throws Exception {
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM campanas WHERE CODIGO = ?");
            declaracion.setString(1, campanas.getCodigo());
            declaracion.executeUpdate();
        } catch (Exception e) {
            log.info(e.getMessage());
            
        } finally {
            this.Cancelar();
        }

    }

    public Mensaje modificar(Campanas campanas) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE campanas set DESCRIPCION = ?, NOMBRE = ?  WHERE CODIGO = ?");

            declaracion.setString(1, campanas.getDescripcion());
            declaracion.setString(2, campanas.getNombre());
            declaracion.setString(3, campanas.getCodigo());
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

    public Campanas campanadeprospecto(String idconjunto) throws Exception {
        Campanas campanaconsultado = new Campanas();
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select campanas.codigo, campanas.nombre, campanas.descripcion from fbleads \n"
                    + "inner join campanas on fbleads.campaign_id = campanas.codigo \n"
                    + "inner join anuncios on fbleads.ad_name = anuncios.codigo \n"
                    + "inner join conjuanuncios on fbleads.adset_name = conjuanuncios.id\n"
                    + "where fbleads.id = ?");
            declaracion.setString(1, idconjunto);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                campanaconsultado.setCodigo(resultado.getString(1));
                campanaconsultado.setNombre(resultado.getString(2));
                campanaconsultado.setDescripcion(resultado.getString(3));
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return campanaconsultado;
    }

}
