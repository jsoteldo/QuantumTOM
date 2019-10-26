/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author RIO CASMA
 */
public class Lotes implements Serializable{
    
    private String parcela;
    private String lote;
    private Float frontal;
    private Float derecha;
    private Float izquierda;
    private Float fondo;
    private Float exclusiva;
    private Float acomun;
    private Float total;
    private Float precio;
    private String img;

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Float getFrontal() {
        return frontal;
    }

    public void setFrontal(Float frontal) {
        this.frontal = frontal;
    }

    public Float getDerecha() {
        return derecha;
    }

    public void setDerecha(Float derecha) {
        this.derecha = derecha;
    }

    public Float getIzquierda() {
        return izquierda;
    }

    public void setIzquierda(Float izquierda) {
        this.izquierda = izquierda;
    }

    public Float getFondo() {
        return fondo;
    }

    public void setFondo(Float fondo) {
        this.fondo = fondo;
    }

    public Float getExclusiva() {
        return exclusiva;
    }

    public void setExclusiva(Float exclusiva) {
        this.exclusiva = exclusiva;
    }

    public Float getAcomun() {
        return acomun;
    }

    public void setAcomun(Float acomun) {
        this.acomun = acomun;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    public Lotes() {
    }

    public Lotes(String parcela, String lote) {
        this.parcela = parcela;
        this.lote = lote;
    }

    public Lotes(String parcela, String lote, Float frontal, Float derecha, Float izquierda, Float fondo, Float exclusiva, Float acomun, Float total, Float precio, String img) {
        this.parcela = parcela;
        this.lote = lote;
        this.frontal = frontal;
        this.derecha = derecha;
        this.izquierda = izquierda;
        this.fondo = fondo;
        this.exclusiva = exclusiva;
        this.acomun = acomun;
        this.total = total;
        this.precio = precio;
        this.img = img;
    }

    @Override
    public String toString() {
        return "Lotes{" + "parcela=" + parcela + ", lote=" + lote + ", frontal=" + frontal + ", derecha=" + derecha + ", izquierda=" + izquierda + ", fondo=" + fondo + ", exclusiva=" + exclusiva + ", acomun=" + acomun + ", total=" + total + ", precio=" + precio + ", img=" + img + '}';
    }

    
}
