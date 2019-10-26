
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author QUANTUM
 */
public class Menu implements Serializable{
    
    private String nombre;
    private String descripcion;
    private String ico;
    private String link;
    private String type;
    private String codigo;
    private String padre;
    private String check;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.trim().toLowerCase();
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPadre() {
        return padre;
    }

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
    
    
    
    public Menu() {
    }

    public Menu(String nombre, String descripcion, String ico, String link, String type, String codigo, String padre) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ico = ico;
        this.link = link;
        this.type = type;
        this.codigo = codigo;
        this.padre = padre;
    }

    public Menu(String nombre, String descripcion, String ico, String link, String type, String codigo, String padre, String check) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ico = ico;
        this.link = link;
        this.type = type;
        this.codigo = codigo;
        this.padre = padre;
        this.check = check;
    }

    @Override
    public String toString() {
        return "Menu{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", ico=" + ico + ", link=" + link + ", type=" + type + ", codigo=" + codigo + ", padre=" + padre + ", check=" + check + '}';
    }
    
    

    

    
    

    
    
}
