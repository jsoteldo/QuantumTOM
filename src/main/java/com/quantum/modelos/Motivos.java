
package com.quantum.modelos;

/**
 *
 * @author QUANTUM
 */
public class Motivos {
    
    private String estado;
    private String codmot;
    private String motivo;
    private String permitellamar;
    private String reunionoficina;
    private String visitaproyecto;
    
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

    public String getCodmot() {
        return codmot;
    }

    public void setCodmot(String codmot) {
        this.codmot = codmot;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReunionoficina() {
        return reunionoficina;
    }

    public void setReunionoficina(String reunionoficina) {
        this.reunionoficina = reunionoficina;
    }

    public String getVisitaproyecto() {
        return visitaproyecto;
    }

    public void setVisitaproyecto(String visitaproyecto) {
        this.visitaproyecto = visitaproyecto;
    }

    
    public Motivos(String estado, String codmot, String motivo, String permitellamar, String reunionoficina, String visitaproyecto) {
        this.estado = estado;
        this.codmot = codmot;
        this.motivo = motivo;
        this.permitellamar = permitellamar;
        this.reunionoficina = reunionoficina;
        this.visitaproyecto = visitaproyecto;
    }

    @Override
    public String toString() {
        return "Motivos{" + "estado=" + estado + ", codmot=" + codmot + ", motivo=" + motivo + ", permitellamar=" + permitellamar + ", reunionoficina=" + reunionoficina + ", visitaproyecto=" + visitaproyecto + '}';
    }

    public Motivos(String motivo) {
        
        this.motivo = motivo;
        
    }

    public Motivos() {
    }

    
}
