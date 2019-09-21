
package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Roles;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class MenuRolesDAO extends DAO{
    
  /*  public Mensaje registrar(MenuRoles  menurol) throws Exception {
        Mensaje validosesion;
        System.out.println(menurol);
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO MENUROLES (ROL, PERMISO) VALUES (?,?)");
            declaracion.setString(1,menurol.getRol());
            declaracion.setString(2, menurol.getPermiso().toString());
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
    
    
    public List<Roles> listar() throws Exception {
        List<Roles> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ROL, DESCRIPCION FROM ROLES");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Roles rol = new Roles();
                rol.setRol(resultado.getString("ROL"));
                rol.setDescripcion(resultado.getString("DESCRIPCION"));
                lista.add(rol);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
    public void borrar(MenuRoles menurol) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM ROLES WHERE ROL = ?");
            declaracion.setString(1, menurol.getRol());
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
                    + "UPDATE ROLES set DESCRIPCION = ? WHERE ROL = ?");

            declaracion.setString(1, rol.getDescripcion());
            declaracion.setString(2, rol.getRol());
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
    */
}   
