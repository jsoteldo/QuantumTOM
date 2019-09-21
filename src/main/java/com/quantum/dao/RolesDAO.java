package com.quantum.dao;

import com.quantum.modelos.Estatusventas;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Roles;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class RolesDAO extends DAO {

    public Mensaje registrar(Roles rol) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO ROLES (ROL, DESCRIPCION, PERMISOS) VALUES (?,?,?)");
            declaracion.setString(1, rol.getRol());
            declaracion.setString(2, rol.getDescripcion());
            declaracion.setString(3, rol.getPermiso().toString());
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

    public List<Roles> listar() throws Exception {
        List<Roles> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ROL, DESCRIPCION, PERMISOS FROM ROLES");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Roles rol = new Roles();
                rol.setRol(resultado.getString("ROL"));
                rol.setDescripcion(resultado.getString("DESCRIPCION"));
                rol.setPermiso(rol.convertClob(resultado.getString("PERMISOS")));
                lista.add(rol);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public void borrar(Roles rol) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM ROLES WHERE ROL = ?");
            declaracion.setString(1, rol.getRol());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }

    public Mensaje modificar(Roles rol) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE ROLES set DESCRIPCION = ?, PERMISOS = ? WHERE ROL = ?");

            declaracion.setString(1, rol.getDescripcion());
            declaracion.setString(2, rol.getPermiso().toString());
            declaracion.setString(3, rol.getRol());
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

    public Roles consultar(String rol) throws Exception {
        Mensaje validosesion;
        ResultSet resultado;
        Roles rolconsultado = new Roles();
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ROL, DESCRIPCION, PERMISOS FROM ROLES "
                    + "WHERE ROL = ?");
            declaracion.setString(1, rol);
            resultado = declaracion.executeQuery();
            if (resultado.next()) {

                rolconsultado.setRol(resultado.getString("ROL"));
                rolconsultado.setDescripcion(resultado.getString("DESCRIPCION"));
                rolconsultado.setPermiso(rolconsultado.convertClob(resultado.getString("PERMISOS")));
                
            } else {
                return null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return rolconsultado;
    }

}
