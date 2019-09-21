
package com.quantum.modelos;

/**
 *
 * @author QUANTUM
 */
public class Origenes {
    private String codigo;
    private String origen;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Origenes(String codigo, String origen) {
        this.codigo = codigo;
        this.origen = origen;
    }

    @Override
    public String toString() {
        return "Origenes{" + "codigo=" + codigo + ", origen=" + origen + '}';
    }

   

    public Origenes() {
    }

   
    
    
}
