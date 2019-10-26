package com.quantum.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author QUANTUM
 */
public class Prospectocrystal implements Serializable{

    private String codigo;
    private String nombres;
    private String apellidos;
    private String ocupacion;
    private String edad;
    private String edociv;
    private String distrito;
    private String telefono;
    private String telefono2;
    private String horadesde;
    private String horahasta;
    
    private String nombrespareja;
    private String apellidospareja;
    private String ocupacionpareja;
    private String edadpareja;
    private String telefonopareja;
    private String horadesdepareja;
    private String horahastapareja;
    
    private List<String> tarjetas;
    
    private String master;
    private String visa;
    private String diner;
    private String american;
    
    private String fecinsert;
    private String user;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEdociv() {
        return edociv;
    }

    public void setEdociv(String edociv) {
        this.edociv = edociv;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getHoradesde() {
        return horadesde;
    }

    public void setHoradesde(String horadesde) {
        this.horadesde = horadesde;
    }

    public String getHorahasta() {
        return horahasta;
    }

    public void setHorahasta(String horahasta) {
        this.horahasta = horahasta;
    }

    public String getNombrespareja() {
        return nombrespareja;
    }

    public void setNombrespareja(String nombrespareja) {
        this.nombrespareja = nombrespareja;
    }

    public String getApellidospareja() {
        return apellidospareja;
    }

    public void setApellidospareja(String apellidospareja) {
        this.apellidospareja = apellidospareja;
    }

    public String getOcupacionpareja() {
        return ocupacionpareja;
    }

    public void setOcupacionpareja(String ocupacionpareja) {
        this.ocupacionpareja = ocupacionpareja;
    }

    public String getEdadpareja() {
        return edadpareja;
    }

    public void setEdadpareja(String edadpareja) {
        this.edadpareja = edadpareja;
    }

    public String getTelefonopareja() {
        return telefonopareja;
    }

    public void setTelefonopareja(String telefonopareja) {
        this.telefonopareja = telefonopareja;
    }

    public String getHoradesdepareja() {
        return horadesdepareja;
    }

    public void setHoradesdepareja(String horadesdepareja) {
        this.horadesdepareja = horadesdepareja;
    }

    public String getHorahastapareja() {
        return horahastapareja;
    }

    public void setHorahastapareja(String horahastapareja) {
        this.horahastapareja = horahastapareja;
    }

    public List<String> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<String> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public String getFecinsert() {
        return fecinsert;
    }

    public void setFecinsert(String fecinsert) {
        this.fecinsert = fecinsert;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getVisa() {
        return visa;
    }

    public void setVisa(String visa) {
        this.visa = visa;
    }

    public String getDiner() {
        return diner;
    }

    public void setDiner(String diner) {
        this.diner = diner;
    }

    public String getAmerican() {
        return american;
    }

    public void setAmerican(String american) {
        this.american = american;
    }
    
    
    

    public List<String> convertClob(String columClob) {
        List<String> lista = new ArrayList<>();
        if (columClob != null) {
          
            String[] elementos = columClob.substring(1, columClob.length() - 1).split(",");

            for (String posicion : elementos) {
                lista.add(posicion);
            }
            System.out.println(lista.toString());
        }
        return lista;
    }

    public Prospectocrystal() {
    }

    public Prospectocrystal(String codigo, String nombres, String apellidos, String ocupacion, String edad, String edociv, String distrito, String telefono, String telefono2, String horadesde, String horahasta, String nombrespareja, String apellidospareja, String ocupacionpareja, String edadpareja, String telefonopareja, String horadesdepareja, String horahastapareja, List<String> tarjetas, String master, String visa, String diner, String american, String fecinsert, String user) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.ocupacion = ocupacion;
        this.edad = edad;
        this.edociv = edociv;
        this.distrito = distrito;
        this.telefono = telefono;
        this.telefono2 = telefono2;
        this.horadesde = horadesde;
        this.horahasta = horahasta;
        this.nombrespareja = nombrespareja;
        this.apellidospareja = apellidospareja;
        this.ocupacionpareja = ocupacionpareja;
        this.edadpareja = edadpareja;
        this.telefonopareja = telefonopareja;
        this.horadesdepareja = horadesdepareja;
        this.horahastapareja = horahastapareja;
        this.tarjetas = tarjetas;
        this.master = master;
        this.visa = visa;
        this.diner = diner;
        this.american = american;
        this.fecinsert = fecinsert;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Prospectocrystal{" + "codigo=" + codigo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", ocupacion=" + ocupacion + ", edad=" + edad + ", edociv=" + edociv + ", distrito=" + distrito + ", telefono=" + telefono + ", telefono2=" + telefono2 + ", horadesde=" + horadesde + ", horahasta=" + horahasta + ", nombrespareja=" + nombrespareja + ", apellidospareja=" + apellidospareja + ", ocupacionpareja=" + ocupacionpareja + ", edadpareja=" + edadpareja + ", telefonopareja=" + telefonopareja + ", horadesdepareja=" + horadesdepareja + ", horahastapareja=" + horahastapareja + ", tarjetas=" + tarjetas + ", master=" + master + ", visa=" + visa + ", diner=" + diner + ", american=" + american + ", fecinsert=" + fecinsert + ", user=" + user + '}';
    }

    

    

}
