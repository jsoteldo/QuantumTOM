
package com.quantum.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class Roles {
    
    private String rol;
    private String descripcion;
    private List<String> permiso;
            
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getPermiso() {
        return permiso;
    }

    public void setPermiso(List<String> permiso) {
        this.permiso = permiso;
    }
    
    
    public Roles() {
        
    }
    
    public Roles(String rol, String descripcion) {
        this.rol = rol;
        this.descripcion = descripcion;
    }

    public Roles(String rol, String descripcion, List<String> permiso) {
        this.rol = rol;
        this.descripcion = descripcion;
        this.permiso = permiso;
    }

    public List<String> convertClob(String columClob){
        List<String> lista = new ArrayList<String>();
        String[] elementos = columClob.substring(1, columClob.length()-1).split(",");
        
        for(String posicion : elementos){
            lista.add(posicion);
        }
        
        return lista;
    }
    
    @Override
    public String toString() {
        return "Roles{" + "rol=" + rol + ", descripcion=" + descripcion + ", permiso=" + permiso + '}';
    }

}
