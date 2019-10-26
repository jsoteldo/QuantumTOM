package com.quantum.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class Asesores implements Serializable{

    private String correo;
    private String nombres;
    private String apellidos;
    private String contrasena;
    private String edad;
    private String sexo;
    private String telefono;
    private String telefono_asig;
    private String rol;
    private String roldescripcion;
    private String fecharetiro;
    private String img;
    private List<String> empl_cargo;
    private List<String> sup_cargo;
    private String sup_encargado;
    private String new_contra;
    private String repite_contra;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono_asig() {
        return telefono_asig;
    }

    public void setTelefono_asig(String telefono_asig) {
        this.telefono_asig = telefono_asig;
    }

    public String getFecharetiro() {
        return fecharetiro;
    }

    public void setFecharetiro(String fecharetiro) {
        this.fecharetiro = fecharetiro;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRoldescripcion() {
        return roldescripcion;
    }

    public void setRoldescripcion(String roldescripcion) {
        this.roldescripcion = roldescripcion;
    }

    public String getNew_contra() {
        return new_contra;
    }

    public void setNew_contra(String new_contra) {
        this.new_contra = new_contra;
    }

    public String getRepite_contra() {
        return repite_contra;
    }

    public void setRepite_contra(String repite_contra) {
        this.repite_contra = repite_contra;
    }

    public List<String> getEmpl_cargo() {
        return empl_cargo;
    }

    public void setEmpl_cargo(List<String> empl_cargo) {
        this.empl_cargo = empl_cargo;
    }

    public List<String> getSup_cargo() {
        return sup_cargo;
    }

    public void setSup_cargo(List<String> sup_cargo) {
        this.sup_cargo = sup_cargo;
    }

    public String getSup_encargado() {
        return sup_encargado;
    }

    public void setSup_encargado(String sup_encargado) {
        this.sup_encargado = sup_encargado;
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

    public Asesores() {
    }

    public Asesores(String correo, String nombres, String apellidos, String contrasena, String edad, String sexo, String telefono, String telefono_asig, String rol, String roldescripcion, String fecharetiro, String img, List<String> empl_cargo, List<String> sup_cargo, String sup_encargado, String new_contra, String repite_contra) {
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        this.edad = edad;
        this.sexo = sexo;
        this.telefono = telefono;
        this.telefono_asig = telefono_asig;
        this.rol = rol;
        this.roldescripcion = roldescripcion;
        this.fecharetiro = fecharetiro;
        this.img = img;
        this.empl_cargo = empl_cargo;
        this.sup_cargo = sup_cargo;
        this.sup_encargado = sup_encargado;
        this.new_contra = new_contra;
        this.repite_contra = repite_contra;
    }

    @Override
    public String toString() {
        return "Asesores{" + "correo=" + correo + ", nombres=" + nombres + ", apellidos=" + apellidos + ", contrasena=" + contrasena + ", edad=" + edad + ", sexo=" + sexo + ", telefono=" + telefono + ", telefono_asig=" + telefono_asig + ", rol=" + rol + ", roldescripcion=" + roldescripcion + ", fecharetiro=" + fecharetiro + ", img=" + img + ", empl_cargo=" + empl_cargo + ", sup_cargo=" + sup_cargo + ", sup_encargado=" + sup_encargado + ", new_contra=" + new_contra + ", repite_contra=" + repite_contra + '}';
    }
 

}
