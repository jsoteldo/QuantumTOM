package com.quantum.dao;

import com.quantum.modelos.Campos;
import com.quantum.modelos.Mensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author QUANTUM
 */
public class CamposDAO extends DAO {

    public Mensaje registrar(Campos campo) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO CAMPOS (NOMBRE, DESCRIPCION, TIPO, CAPACIDAD, PRIORIDAD) VALUES (?,?,?,?,?)");
            declaracion.setString(1, campo.getNombre());
            declaracion.setString(2, campo.getDescripcion());
            declaracion.setString(3, campo.getTipo());
            declaracion.setString(4, campo.getCapacidad());
            declaracion.setString(5, campo.getPrioridad());
            declaracion.executeUpdate();
            this.editfblead("crear", campo, null);
            validosesion = new Mensaje("", "Registrado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }

    }

    public List<Campos> listar() throws Exception {
        List<Campos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT NOMBRE, DESCRIPCION, TIPO, CAPACIDAD, PRIORIDAD FROM CAMPOS "
                    + "ORDER BY PRIORIDAD");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Campos campo = new Campos();
                campo.setNombre(resultado.getString("NOMBRE"));
                campo.setDescripcion(resultado.getString("DESCRIPCION"));
                campo.setTipo(resultado.getString("TIPO"));
                campo.setCapacidad(resultado.getString("CAPACIDAD"));
                campo.setPrioridad(resultado.getString("PRIORIDAD"));
                lista.add(campo);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    public List<String> lstcampos() throws Exception {
        List<String> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT NOMBRE, DESCRIPCION, TIPO, CAPACIDAD, PRIORIDAD FROM CAMPOS "
                    + "WHERE PRIORIDAD = 'ALTA' "
                    + "ORDER BY PRIORIDAD");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                lista.add("\""+resultado.getString("NOMBRE")+"\"");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Mensaje borrar(Campos campo) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM CAMPOS WHERE NOMBRE = ?");
            declaracion.setString(1, campo.getNombre());
            declaracion.executeUpdate();
            this.editfblead("borrar", campo, null);
            validosesion = new Mensaje("", "Registrado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }

    }

    public Mensaje modificar(Campos campo, String campOLD) throws Exception {
        Mensaje validosesion;
        System.out.println("AQui");
        System.out.println(campo);
        System.out.println(campOLD);
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE CAMPOS set NOMBRE = ?, DESCRIPCION = ?, TIPO = ?, CAPACIDAD = ?, PRIORIDAD = ?  WHERE NOMBRE = ?");

            declaracion.setString(1, campo.getNombre());
            declaracion.setString(2, campo.getDescripcion());
            declaracion.setString(3, campo.getTipo());
            declaracion.setString(4, campo.getCapacidad());
            declaracion.setString(5, campo.getPrioridad());
            declaracion.setString(6, campOLD);
            System.out.println(declaracion.toString());
            declaracion.executeUpdate();
            
            validosesion = new Mensaje("", "Actualizado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.editfblead("modificar", campo, campOLD);
            this.Cancelar();
        }
    }

    public void editfblead(String accion, Campos campo, String campOLD) {
        System.out.println("entrando");
        String consulta = "";

        switch (accion) {

            case "crear":
                consulta = "ALTER TABLE fbleads ADD " + campo.getNombre() + " " + campo.getTipo() + "(" + campo.getCapacidad() + ")";
                System.out.println(consulta);
                break;
            case "borrar":
                consulta = "ALTER TABLE fbleads DROP COLUMN " + campo.getNombre();
                System.out.println(consulta);
                break;
            case "modificar":
                consulta = "ALTER TABLE fbleads MODIFY COLUMN " + campOLD + " " + campo.getTipo() + "(" + campo.getCapacidad() + ")";
                System.out.println(consulta);
                break;
        }

        PreparedStatement alter;
        try {
            alter = this.getConexion().prepareStatement(consulta);
            alter.execute();
            System.out.println("Saliendo");
        } catch (SQLException ex) {
            Logger.getLogger(CamposDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
}
