
package com.quantum.modelos;

import java.io.Serializable;

/**
 *
 * @author QUANTUM
 */
public class Estado implements Serializable{
    private String codigo;
    private String estado;
    private String permitellamar;
    private String motivo;
    private String cerrada;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPermitellamar() {
        return permitellamar;
    }

    public void setPermitellamar(String permitellamar) {
        this.permitellamar = permitellamar;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCerrada() {
        return cerrada;
    }

    public void setCerrada(String cerrada) {
        this.cerrada = cerrada;
    }

    public Estado(String codigo, String estado, String permitellamar, String motivo, String cerrada) {
        this.codigo = codigo;
        this.estado = estado;
        this.permitellamar = permitellamar;
        this.motivo = motivo;
        this.cerrada = cerrada;
    }

    public Estado() {
    }

    @Override
    public String toString() {
        return "Estado{" + "codigo=" + codigo + ", estado=" + estado + ", permitellamar=" + permitellamar + ", motivo=" + motivo + ", cerrada=" + cerrada + '}';
    }

}
