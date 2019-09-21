
package com.quantum.modelos;

/**
 *
 * @author QUANTUM
 */
public class Selectequivalencias {
    
    private int posicion;
    
    private String Id;
    
    private String Campo;

    

    public Selectequivalencias() {
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCampo() {
        return Campo;
    }

    public void setCampo(String Campo) {
        this.Campo = Campo;
    }

    public Selectequivalencias(int Posicion, String Id, String Campo) {
        this.posicion = Posicion;
        this.Id = Id;
        this.Campo = Campo;
    }

    @Override
    public String toString() {
        return "Selectequivalencias{" + "Id=" + Id + ", Campo=" + Campo + '}';
    }

    

    
    
    
    
}
