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
public class Archivo {
    
    private String name;
    private String fecha;
    private String dir;
    private String procesado;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getProcesado() {
        return procesado;
    }

    public void setProcesado(String procesado) {
        this.procesado = procesado;
    }

    
    public Archivo() {
    }

    public Archivo(String name, String fecha, String dir, String procesado) {
        this.name = name;
        this.fecha = fecha;
        this.dir = dir;
        this.procesado = procesado;
    }

    @Override
    public String toString() {
        return "Archivo{" + "name=" + name + ", fecha=" + fecha + ", dir=" + dir + ", procesado=" + procesado + '}';
    }


    
    
    
}
