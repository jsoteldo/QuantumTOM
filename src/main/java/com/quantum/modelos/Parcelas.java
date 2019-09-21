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
public class Parcelas {
    
    private String parcela;

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public Parcelas() {
    }

    public Parcelas(String parcela) {
        this.parcela = parcela;
    }

    @Override
    public String toString() {
        return "Parcelas{" + "parcela=" + parcela + '}';
    }
    
    
}
