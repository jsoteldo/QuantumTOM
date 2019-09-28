package com.quantum.dao;

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
public class ConjuntoDAO extends DAO {

    public Mensaje registrar(Conjunto conjunto) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO conjuanuncios (ID, DESCRIPCION, MASCULINO, FEMENINO, EDADINI, EDADFIN, BD, ADICIONAL,ZNGEO) VALUES (?,?,?,?,?,?,?,?,?)");
            declaracion.setString(1, conjunto.getId());
            declaracion.setString(2, conjunto.getDescripcion());
            declaracion.setString(3, conjunto.getMasculino());
            declaracion.setString(4, conjunto.getFemenino());
            declaracion.setString(5, conjunto.getEdadini());
            declaracion.setString(6, conjunto.getEdadfin());
            declaracion.setString(7, conjunto.getBd());
            declaracion.setString(8, conjunto.getAdicional());
            declaracion.setString(9, conjunto.getZngeo());
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

    public List<Conjunto> listar() throws Exception {
        List<Conjunto> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ID, DESCRIPCION, MASCULINO, FEMENINO, EDADINI, EDADFIN, BD, ADICIONAL, ZNGEO FROM conjuanuncios");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Conjunto conjunto = new Conjunto();
                conjunto.setId(resultado.getString("ID"));
                conjunto.setDescripcion(resultado.getString("DESCRIPCION"));
                conjunto.setMasculino(resultado.getString("MASCULINO"));
                conjunto.setFemenino(resultado.getString("FEMENINO"));
                conjunto.setEdadini(resultado.getString("EDADINI"));
                conjunto.setEdadfin(resultado.getString("EDADFIN"));
                conjunto.setBd(resultado.getString("BD"));
                conjunto.setAdicional(resultado.getString("ADICIONAL"));
                conjunto.setZngeo(resultado.getString("ZNGEO"));
                lista.add(conjunto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Conjunto consultaconjunto(String idconjunto) throws Exception {
        Conjunto conjuntoconsultado = new Conjunto();
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ID, DESCRIPCION, MASCULINO, FEMENINO, EDADINI, EDADFIN, BD, ADICIONAL, ZNGEO FROM conjuanuncios "
                    + "WHERE ID = ?");
            declaracion.setString(1, idconjunto);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                conjuntoconsultado.setId(resultado.getString("ID"));
                conjuntoconsultado.setDescripcion(resultado.getString("DESCRIPCION"));
                conjuntoconsultado.setMasculino(resultado.getString("MASCULINO"));
                conjuntoconsultado.setFemenino(resultado.getString("FEMENINO"));
                conjuntoconsultado.setEdadini(resultado.getString("EDADINI"));
                conjuntoconsultado.setEdadfin(resultado.getString("EDADFIN"));
                conjuntoconsultado.setBd(resultado.getString("BD"));
                conjuntoconsultado.setAdicional(resultado.getString("ADICIONAL"));
                conjuntoconsultado.setZngeo(resultado.getString("ZNGEO"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return conjuntoconsultado;
    }

    public void borrar(Conjunto conjunto) throws Exception {
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM conjuanuncios WHERE ID = ?");
            declaracion.setString(1, conjunto.getId());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }

    public Mensaje modificar(Conjunto conjunto) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE conjuanuncios set MASCULINO = ?, FEMENINO = ?, EDADINI = ?, EDADFIN = ?, BD = ?, ADICIONAL = ?, ZNGEO = ? WHERE ID = ?");

            declaracion.setString(1, conjunto.getMasculino());
            declaracion.setString(2, conjunto.getFemenino());
            declaracion.setString(3, conjunto.getEdadini());
            declaracion.setString(4, conjunto.getEdadfin());
            declaracion.setString(5, conjunto.getBd());
            declaracion.setString(6, conjunto.getAdicional());
            declaracion.setString(7, conjunto.getZngeo());
            declaracion.setString(8, conjunto.getId());
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

    public Conjunto conjuntodeprospecto(String idconjunto) throws Exception {
        Conjunto conjuntoconsultado = new Conjunto();
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "Select conjuanuncios.id, conjuanuncios.descripcion, conjuanuncios.masculino, conjuanuncios.femenino,\n"
                    + "conjuanuncios.edadini, conjuanuncios.edadfin, conjuanuncios.bd, conjuanuncios.adicional, conjuanuncios.zngeo from fbleads inner join anuncios on fbleads.ad_name = anuncios.codigo \n"
                    + "inner join conjuanuncios on fbleads.adset_name = conjuanuncios.id\n"
                    + "where fbleads.id = ?");
            declaracion.setString(1, idconjunto);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                conjuntoconsultado.setId(resultado.getString(1));
                conjuntoconsultado.setDescripcion(resultado.getString(2));
                conjuntoconsultado.setMasculino(resultado.getString(3));
                conjuntoconsultado.setFemenino(resultado.getString(4));
                conjuntoconsultado.setEdadini(resultado.getString(5));
                conjuntoconsultado.setEdadfin(resultado.getString(6));
                conjuntoconsultado.setBd(resultado.getString(7));
                conjuntoconsultado.setAdicional(resultado.getString(8));
                conjuntoconsultado.setZngeo(resultado.getString(9));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return conjuntoconsultado;
    }

}
