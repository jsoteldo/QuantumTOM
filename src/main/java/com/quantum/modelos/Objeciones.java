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
public class Objeciones implements Serializable{
    
    private String tipobjecion;
    private String codigo;
    private String objecion;

    public String getTipobjecion() {
        return tipobjecion;
    }

    public void setTipobjecion(String tipobjecion) {
        this.tipobjecion = tipobjecion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getObjecion() {
        return objecion;
    }

    public void setObjecion(String objecion) {
        this.objecion = objecion;
    }

    public Objeciones() {
    }

    public Objeciones(String tipobjecion, String codigo, String objecion) {
        this.tipobjecion = tipobjecion;
        this.codigo = codigo;
        this.objecion = objecion;
    }

    @Override
    public String toString() {
        return "Objeciones{" + "tipoobjecion=" + tipobjecion + ", codigo=" + codigo + ", objecion=" + objecion + '}';
    }
    
    

}
