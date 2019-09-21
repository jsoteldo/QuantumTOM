
package com.quantum.modelos;

/**
 *
 * @author QUANTUM
 */
public class Prospectos {
    
    private String codigo;
    private String codigoGestion;
    private String fecha_prospecto;
    private String fecha_ingreso;
    private String nombreapellido;
    private String telefono1;
    private String telefono2;
    private String correo;
    private String correo_sec;
    private String origen;
    private String cargo;
    private String link;
    private String fecharetiro;
    private String edad;
    private String sexo;
    private String distrito;
    private String comentario;
    
    private String estado;
    private String motivo;
    private String colorestado;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoGestion() {
        return codigoGestion;
    }

    public void setCodigoGestion(String codigoGestion) {
        this.codigoGestion = codigoGestion;
    }

    public String getFecha_prospecto() {
        return fecha_prospecto;
    }

    public void setFecha_prospecto(String fecha_prospecto) {
        this.fecha_prospecto = fecha_prospecto;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getNombreapellido() {
        return nombreapellido;
    }

    public void setNombreapellido(String nombreapellido) {
        this.nombreapellido = nombreapellido;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFecharetiro() {
        return fecharetiro;
    }

    public void setFecharetiro(String fecharetiro) {
        this.fecharetiro = fecharetiro;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getColorestado() {
        return colorestado;
    }

    public void setColorestado(String colorestado) {
        this.colorestado = colorestado;
    }

    public String getCorreo_sec() {
        return correo_sec;
    }

    public void setCorreo_sec(String correo_sec) {
        this.correo_sec = correo_sec;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    
    
    public Prospectos() {
    }

    public Prospectos(String codigo, String codigoGestion, String fecha_prospecto, String fecha_ingreso, String nombreapellido, String telefono1, String telefono2, String correo, String correo_sec, String origen, String cargo, String link, String fecharetiro, String edad, String sexo, String distrito, String comentario, String estado, String motivo, String colorestado) {
        this.codigo = codigo;
        this.codigoGestion = codigoGestion;
        this.fecha_prospecto = fecha_prospecto;
        this.fecha_ingreso = fecha_ingreso;
        this.nombreapellido = nombreapellido;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.correo = correo;
        this.correo_sec = correo_sec;
        this.origen = origen;
        this.cargo = cargo;
        this.link = link;
        this.fecharetiro = fecharetiro;
        this.edad = edad;
        this.sexo = sexo;
        this.distrito = distrito;
        this.comentario = comentario;
        this.estado = estado;
        this.motivo = motivo;
        this.colorestado = colorestado;
    }

    @Override
    public String toString() {
        return "Prospectos{" + "codigo=" + codigo + ", codigoGestion=" + codigoGestion + ", fecha_prospecto=" + fecha_prospecto + ", fecha_ingreso=" + fecha_ingreso + ", nombreapellido=" + nombreapellido + ", telefono1=" + telefono1 + ", telefono2=" + telefono2 + ", correo=" + correo + ", correo_sec=" + correo_sec + ", origen=" + origen + ", cargo=" + cargo + ", link=" + link + ", fecharetiro=" + fecharetiro + ", edad=" + edad + ", sexo=" + sexo + ", distrito=" + distrito + ", comentario=" + comentario + ", estado=" + estado + ", motivo=" + motivo + ", colorestado=" + colorestado + '}';
    }

    

    
}
