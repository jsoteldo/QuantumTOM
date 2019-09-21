/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quantum.modelos;

/**
 *
 * @author RIO CASMA
 */
public class Casas {
    
    private String codigo;
    private String modelo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Casas() {
    }

    public Casas(String codigo, String modelo) {
        this.codigo = codigo;
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Casas{" + "codigo=" + codigo + ", modelo=" + modelo + '}';
    }
    
    
}
