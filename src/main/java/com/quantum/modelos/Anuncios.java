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
public class Anuncios {

    private String codigo;
    private String descripcion;
    private String urlimg;
    private String urlfb;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }

    public String getUrlfb() {
        return urlfb;
    }

    public void setUrlfb(String urlfb) {
        this.urlfb = urlfb;
    }

    public Anuncios(String codigo, String descripcion, String urlimg, String urlfb) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.urlimg = urlimg;
        this.urlfb = urlfb;
    }

    @Override
    public String toString() {
        return "Anuncios{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", urlimg=" + urlimg + ", urlfb=" + urlfb + '}';
    }

    public Anuncios() {
    }

    
}
