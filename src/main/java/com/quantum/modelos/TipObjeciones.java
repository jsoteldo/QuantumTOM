/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author QUANTUM
 */
public class TipObjeciones implements Serializable{

    private String codigo;
    private String tipo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public TipObjeciones(String codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
    }

    public TipObjeciones() {
    }

    @Override
    public String toString() {
        return "TipoObjeciones{" + "codigo=" + codigo + ", tipo=" + tipo + '}';
    }
    
    
}
