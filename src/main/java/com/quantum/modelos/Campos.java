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
public class Campos {

    private String nombre;
    private String descripcion;
    private String tipo;
    private String capacidad;
    private String prioridad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Campos(String nombre, String descripcion, String tipo, String capacidad, String prioridad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Campos{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo + ", capacidad=" + capacidad + ", prioridad=" + prioridad + '}';
    }

    public Campos() {
    }

    
}
