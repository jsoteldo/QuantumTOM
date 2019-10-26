
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author QUANTUM
 */
public class Distritos implements Serializable{
    private String codigo;
    private String distrito;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Distritos(String codigo, String distrito) {
        this.codigo = codigo;
        this.distrito = distrito;
    }

    @Override
    public String toString() {
        return "Distritos{" + "codigo=" + codigo + ", distrito=" + distrito + '}';
    }

    public Distritos() {
    }

   
    
    
}
