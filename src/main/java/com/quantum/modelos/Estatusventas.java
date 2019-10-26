
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author QUANTUM
 */
public class Estatusventas implements Serializable{
    
    private String codigo;
    
    private String estatus;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Estatusventas(String codigo, String estatus) {
        this.codigo = codigo;
        this.estatus = estatus;
    }

    public Estatusventas() {
    }

    @Override
    public String toString() {
        return "Estatusventas{" + "codigo=" + codigo + ", estatus=" + estatus + '}';
    }
    
    
    
}
