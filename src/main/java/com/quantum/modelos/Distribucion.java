
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author QUANTUM
 */
public class Distribucion implements Serializable{
    private String correo;
    private String nombre;
    private String apellido;
    private String cantidad;
    private String porcentaje;
    private String baseasig;
    private String mes;
    private String ano;
    private String fecha_asignada;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getBaseasig() {
        return baseasig;
    }

    public void setBaseasig(String baseasig) {
        this.baseasig = baseasig;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getFecha_asignada() {
        return fecha_asignada;
    }

    public void setFecha_asignada(String fecha_asignada) {
        this.fecha_asignada = fecha_asignada;
    }

    public Distribucion(String correo, String nombre, String apellido, String cantidad, String porcentaje) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cantidad = cantidad;
        this.porcentaje = porcentaje;
    }

    public Distribucion(String correo, String nombre, String apellido, String cantidad, String porcentaje, String baseasig, String mes, String ano, String fecha_asignada) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cantidad = cantidad;
        this.porcentaje = porcentaje;
        this.baseasig = baseasig;
        this.mes = mes;
        this.ano = ano;
        this.fecha_asignada = fecha_asignada;
    }

    @Override
    public String toString() {
        return "Distribucion{" + "correo=" + correo + ", nombre=" + nombre + ", apellido=" + apellido + ", cantidad=" + cantidad + ", porcentaje=" + porcentaje + ", baseasig=" + baseasig + ", mes=" + mes + ", ano=" + ano + ", fecha_asignada=" + fecha_asignada + '}';
    }

    public Distribucion() {
    }

   
    
    
}
