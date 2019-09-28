package com.quantum.dao;

import com.quantum.modelos.Anuncios;
import com.quantum.modelos.Conjunto;
import com.quantum.modelos.Mensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class AnunciosDAO extends DAO {

    public Mensaje registrar(Anuncios anuncios) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO anuncios (CODIGO, DESCRIPCION, URLIMG, URLFB) VALUES (?,?,?,?)");
            declaracion.setString(1, anuncios.getCodigo());
            declaracion.setString(2, anuncios.getDescripcion());
            declaracion.setString(3, anuncios.getUrlimg());
            declaracion.setString(4, anuncios.getUrlfb());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Registrado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }

    }

    public List<Anuncios> listar() throws Exception {
        List<Anuncios> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, DESCRIPCION, URLIMG, URLFB FROM anuncios");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Anuncios anuncio = new Anuncios();
                anuncio.setCodigo(resultado.getString("CODIGO"));
                anuncio.setDescripcion(resultado.getString("DESCRIPCION"));
                anuncio.setUrlimg(resultado.getString("URLIMG"));
                anuncio.setUrlfb(resultado.getString("URLFB"));
                lista.add(anuncio);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Anuncios consultaanuncio(String idanuncio) throws Exception {
        Anuncios anuncioconsultado = new Anuncios();;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, DESCRIPCION, URLIMG, URLFB FROM anuncios "
                    + "WHERE CODIGO = ?");
            declaracion.setString(1, idanuncio);

            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                anuncioconsultado.setCodigo(resultado.getString("CODIGO"));
                anuncioconsultado.setDescripcion(resultado.getString("DESCRIPCION"));
                anuncioconsultado.setUrlimg(resultado.getString("URLIMG"));
                anuncioconsultado.setUrlfb(resultado.getString("URLFB"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return anuncioconsultado;
    }

    public void borrar(Anuncios anuncios) throws Exception {
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM anuncios WHERE CODIGO = ?");
            declaracion.setString(1, anuncios.getCodigo());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }

    public Mensaje modificar(Anuncios anuncios) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE anuncios set URLIMG = ?, URLFB = ? WHERE CODIGO = ?");

            declaracion.setString(1, anuncios.getUrlimg());
            declaracion.setString(2, anuncios.getUrlfb());
            declaracion.setString(3, anuncios.getCodigo());
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

    public Anuncios anunciodeprospecto(String idconjunto) throws Exception {
        Anuncios anuncioconsultado = new Anuncios();
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select anuncios.codigo, anuncios.descripcion, anuncios.urlimg, anuncios.urlfb from fbleads \n"
                    + "inner join anuncios on fbleads.ad_name = anuncios.codigo \n"
                    + "inner join conjuanuncios on fbleads.adset_name = conjuanuncios.id\n"
                    + "where fbleads.id = ?");
            declaracion.setString(1, idconjunto);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                anuncioconsultado.setCodigo(resultado.getString(1));
                anuncioconsultado.setDescripcion(resultado.getString(2));
                anuncioconsultado.setUrlimg(resultado.getString(3));
                anuncioconsultado.setUrlfb(resultado.getString(4));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return anuncioconsultado;
    }

}
