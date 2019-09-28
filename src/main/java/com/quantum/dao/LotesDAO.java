package com.quantum.dao;

import com.quantum.modelos.Estado;
import com.quantum.modelos.Lotes;
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
public class LotesDAO extends DAO {

    public List<Lotes> listar() throws Exception {
        List<Lotes> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT PARCELA, LOTE FROM lotes");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Lotes lote = new Lotes();
                lote.setLote(resultado.getString("LOTE"));
                lote.setParcela(resultado.getString("PARCELA"));
                lista.add(lote);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Lotes> lotesporparcelas(String opcion) throws Exception {
        List<Lotes> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT PARCELA, LOTE FROM lotes "
                    + "WHERE PARCELA = ? ");
            declaracion.setString(1, opcion);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Lotes lote = new Lotes();
                lote.setParcela(resultado.getString("PARCELA"));
                lote.setLote(resultado.getString("LOTE"));
                lista.add(lote);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Mensaje modificar(Lotes lotes) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("UPDATE"
                    + " lotes set FRONTAL = ?, DERECHA = ?, IZQUIERDA = ?, "
                    + " FONDO = ?, EXCLUSIVA = ?, ACOMUN = ?, TOTAL = ?, PRECIO = ?, IMG = ? WHERE PARCELA = ? AND LOTE = ?");

            declaracion.setFloat(1, lotes.getFrontal());
            declaracion.setFloat(2, lotes.getDerecha());
            declaracion.setFloat(3, lotes.getIzquierda());
            declaracion.setFloat(4, lotes.getFondo());
            declaracion.setFloat(5, lotes.getExclusiva());
            declaracion.setFloat(6, lotes.getAcomun());
            declaracion.setFloat(7, lotes.getTotal());
            declaracion.setFloat(8, lotes.getPrecio());
            declaracion.setString(9, lotes.getImg());
            declaracion.setString(10, lotes.getParcela());
            declaracion.setString(11, lotes.getLote());
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

    public List<Lotes> listarTodo() throws Exception {
        List<Lotes> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT PARCELA, LOTE, FRONTAL, DERECHA, IZQUIERDA, FONDO, EXCLUSIVA, ACOMUN, TOTAL, PRECIO, IMG FROM lotes");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Lotes lote = new Lotes();
                lote.setLote(resultado.getString("LOTE"));
                lote.setParcela(resultado.getString("PARCELA"));
                lote.setFrontal(resultado.getFloat("FRONTAL"));
                lote.setDerecha(resultado.getFloat("DERECHA"));
                lote.setIzquierda(resultado.getFloat("IZQUIERDA"));
                lote.setFondo(resultado.getFloat("FONDO"));
                lote.setExclusiva(resultado.getFloat("EXCLUSIVA"));
                lote.setAcomun(resultado.getFloat("ACOMUN"));
                lote.setTotal(resultado.getFloat("TOTAL"));
                lote.setPrecio(resultado.getFloat("PRECIO"));
                lote.setImg(resultado.getString("IMG"));
                lista.add(lote);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Lotes> todolotesporparcelas(String opcion) throws Exception {
        List<Lotes> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT PARCELA, LOTE , FRONTAL, DERECHA, IZQUIERDA, FONDO, EXCLUSIVA, ACOMUN, TOTAL, PRECIO, IMG FROM lotes "
                    + "WHERE PARCELA = ? ");
            declaracion.setString(1, opcion);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Lotes lote = new Lotes();
                lote.setParcela(resultado.getString("PARCELA"));
                lote.setLote(resultado.getString("LOTE"));
                lote.setFrontal(resultado.getFloat("FRONTAL"));
                lote.setDerecha(resultado.getFloat("DERECHA"));
                lote.setIzquierda(resultado.getFloat("IZQUIERDA"));
                lote.setFondo(resultado.getFloat("FONDO"));
                lote.setExclusiva(resultado.getFloat("EXCLUSIVA"));
                lote.setAcomun(resultado.getFloat("ACOMUN"));
                lote.setTotal(resultado.getFloat("TOTAL"));
                lote.setPrecio(resultado.getFloat("PRECIO"));
                lote.setImg(resultado.getString("IMG"));
                lista.add(lote);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Mensaje registrar(Lotes lotes) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO lotes "
                    + "(PARCELA, LOTE , FRONTAL, DERECHA, IZQUIERDA, FONDO, EXCLUSIVA, ACOMUN, TOTAL, PRECIO, IMG) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            declaracion.setString(1, lotes.getParcela());
            declaracion.setString(2, lotes.getLote());
            declaracion.setFloat(3, lotes.getFrontal());
            declaracion.setFloat(4, lotes.getDerecha());
            declaracion.setFloat(5, lotes.getIzquierda());
            declaracion.setFloat(6, lotes.getFondo());
            declaracion.setFloat(7, lotes.getExclusiva());
            declaracion.setFloat(8, lotes.getAcomun());
            declaracion.setFloat(9, lotes.getTotal());
            declaracion.setFloat(10, lotes.getPrecio());
            declaracion.setString(11, lotes.getImg());
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

    public void borrar(Lotes lotes) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM lotes WHERE PARCELA = ? AND LOTE = ?");
            declaracion.setString(1, lotes.getParcela());
            declaracion.setString(2, lotes.getLote());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }

}
