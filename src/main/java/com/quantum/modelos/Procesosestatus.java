/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quantum.modelos;

/**
 *
 * @author QUANTUM
 */
public class Procesosestatus {

    private String estatus;
    private String codpro;
    private String proceso;

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCodpro() {
        return codpro;
    }

    public void setCodpro(String codpro) {
        this.codpro = codpro;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public Procesosestatus(String estatus, String codpro, String proceso) {
        this.estatus = estatus;
        this.codpro = codpro;
        this.proceso = proceso;
    }

    public Procesosestatus() {
    }

    @Override
    public String toString() {
        return "Procesosestatus{" + "estatus=" + estatus + ", codpro=" + codpro + ", proceso=" + proceso + '}';
    }
    
    
}
