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
public class Conjunto implements Serializable{
    
    private String id;
    private String descripcion;
    private String masculino;
    private String femenino;
    private String edadini;
    private String edadfin;
    private String bd;
    private String adicional;
    private String zngeo;

    public Conjunto() {
    }

    public Conjunto(String id, String descripcion, String masculino, String femenino, String edadini, String edadfin, String bd, String adicional, String zngeo) {
        this.id = id;
        this.descripcion = descripcion;
        this.masculino = masculino;
        this.femenino = femenino;
        this.edadini = edadini;
        this.edadfin = edadfin;
        this.bd = bd;
        this.adicional = adicional;
        this.zngeo = zngeo;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMasculino() {
        return masculino;
    }

    public void setMasculino(String masculino) {
        this.masculino = masculino;
    }

    public String getFemenino() {
        return femenino;
    }

    public void setFemenino(String femenino) {
        this.femenino = femenino;
    }

    public String getEdadini() {
        return edadini;
    }

    public void setEdadini(String edadini) {
        this.edadini = edadini;
    }

    public String getEdadfin() {
        return edadfin;
    }

    public void setEdadfin(String edadfin) {
        this.edadfin = edadfin;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getZngeo() {
        return zngeo;
    }

    public void setZngeo(String zngeo) {
        this.zngeo = zngeo;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    @Override
    public String toString() {
        return "Conjunto{" + "id=" + id + ", descripcion=" + descripcion + ", masculino=" + masculino + ", femenino=" + femenino + ", edadini=" + edadini + ", edadfin=" + edadfin + ", bd=" + bd + ", adicional=" + adicional + ", zngeo=" + zngeo + '}';
    }

    
    
    
    
}
