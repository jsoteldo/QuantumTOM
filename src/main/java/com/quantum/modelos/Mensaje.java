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
public class Mensaje implements Serializable{
    private boolean valida;
    private String display = "none !important"; 
    private String mensaje ;
    private Asesores asesor;
    private String ico;
    private String clase;
    private String text;

    public boolean isValida() {
        return valida;
    }

    public void setValida(boolean valida) {
        this.valida = valida;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Asesores getAsesor() {
        return asesor;
    }

    public void setAsesor(Asesores asesor) {
        this.asesor = asesor;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Mensaje{" + "valida=" + valida + ", display=" + display + ", mensaje=" + mensaje + ", asesor=" + asesor + ", ico=" + ico + ", clase=" + clase + ", text=" + text + '}';
    }

    
    
    public Mensaje() {
    }

    
    public Mensaje(boolean valida, String mensaje) {
        this.valida = valida;
        this.mensaje = mensaje;
    }

    public Mensaje( String display, String mensaje, String ico, String clase) {
        this.mensaje = mensaje;
        this.display = display;
        this.ico = ico;
        this.clase = clase;
    }

    
    public Mensaje(boolean valida, String display, String mensaje) {
        this.valida = valida;
        this.display = display;
        this.mensaje = mensaje;
    }

    public Mensaje(boolean valida, String display, String mensaje, Asesores asesor) {
        this.valida = valida;
        this.mensaje = mensaje;
        this.display = display;
        this.asesor = asesor;
    }
    
    
    
}
