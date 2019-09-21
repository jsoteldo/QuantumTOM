package com.quantum.bean;

import com.quantum.dao.AsesoresDAO;
import com.quantum.dao.RolesDAO;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Roles;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author QUANTUM
 */
@Named("asesoresBean")
@ViewScoped
public class AsesoresBean implements Serializable {

    private Mensaje message = new Mensaje(false, "none !important", "");
    private formatoDeFechas fechas = new formatoDeFechas();

    private Asesores asesores = new Asesores();
    private List<Asesores> lstAsesores;
    private List<Asesores> lstsupervisores;
    private List<Asesores> lstasesor;
    
    private Map<String, Boolean> asesoresChecked = new HashMap<>();
    private Map<String, Boolean> supervisoresChecked = new HashMap<>();
    
    private List<Roles> lstRoles;

    private String boton;

    public Asesores getAsesores() {
        return asesores;
    }

    public void setAsesores(Asesores asesores) {
        this.asesores = asesores;
    }

    public List<Asesores> getLstAsesores() {
        return lstAsesores;
    }

    public void setLstAsesores(List<Asesores> lstAsesores) {
        this.lstAsesores = lstAsesores;
    }

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public List<Roles> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<Roles> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public List<Asesores> getLstsupervisores() {
        return lstsupervisores;
    }

    public void setLstsupervisores(List<Asesores> lstsupervisores) {
        this.lstsupervisores = lstsupervisores;
    }

    public List<Asesores> getLstasesor() {
        return lstasesor;
    }

    public void setLstasesor(List<Asesores> lstasesor) {
        this.lstasesor = lstasesor;
    }

    public Map<String, Boolean> getAsesoresChecked() {
        return asesoresChecked;
    }

    public void setAsesoresChecked(Map<String, Boolean> asesoresChecked) {
        this.asesoresChecked = asesoresChecked;
    }

    public Map<String, Boolean> getSupervisoresChecked() {
        return supervisoresChecked;
    }

    public void setSupervisoresChecked(Map<String, Boolean> supervisoresChecked) {
        this.supervisoresChecked = supervisoresChecked;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public void limpiar() {
        asesores.setApellidos("");
        asesores.setContrasena("");
        asesores.setCorreo("");
        asesores.setEdad("");
        asesores.setNombres("");
        asesores.setSexo("");
        asesores.setTelefono("");
        asesores.setTelefono_asig("");
        asesores.setFecharetiro("");
        asesores.setRol("");
        asesores.setNew_contra("");
        asesores.setRepite_contra("");
    }

    public void operar() {
        try {
            if (boton.equals("Agregar")) {
                this.registrar();
            } else {
                this.modificar();
            }
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void registrar() throws Exception {
        AsesoresDAO dao;
        Mensaje mensaje;

        try {
            
            List<String> supervisores = new ArrayList<String>();

            for (Map.Entry<String, Boolean> entry : supervisoresChecked.entrySet()) {
                if (entry.getValue() == true) {
                    supervisores.add(entry.getKey());
                    if (entry.getKey().length() > 0) {
                        if (!supervisores.contains(entry.getKey().substring(0, 1))) {
                            supervisores.add(entry.getKey().substring(0, 1));
                        }
                    }
                }
            }
            
            List<String> asesorescargo = new ArrayList<String>();

            for (Map.Entry<String, Boolean> entry : asesoresChecked.entrySet()) {
                if (entry.getValue() == true) {
                    asesorescargo.add(entry.getKey());
                    if (entry.getKey().length() > 0) {
                        if (!asesorescargo.contains(entry.getKey().substring(0, 1))) {
                            asesorescargo.add(entry.getKey().substring(0, 1));
                        }
                    }
                }
            }
            asesores.setEmpl_cargo(asesorescargo);
            
            dao = new AsesoresDAO();
            mensaje = dao.registrar(asesores);
            this.limpiar();
            message = mensaje;

        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void listar() throws Exception {
        AsesoresDAO dao;

        try {
            dao = new AsesoresDAO();
            lstAsesores = dao.listar();
            
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(Asesores asesor, HttpSession session) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("asesorparam", asesor);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/asesores.xhtml");
    }

    public void modificar() throws Exception {
        AsesoresDAO dao;
        Mensaje mensaje;

        try {
            dao = new AsesoresDAO();
            mensaje = dao.modificar(asesores);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaAsesores.xhtml");
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void borrar(Asesores asesor) throws Exception {
        try {
            AsesoresDAO dao;

            asesor.setFecharetiro(fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA));
            dao = new AsesoresDAO();
            dao.borrar(asesor);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void capturarasesor(HttpSession session) {
        RolesDAO dao;
        AsesoresDAO daoase;
        try {
            dao = new RolesDAO();
            daoase = new AsesoresDAO()
                    ;
            if (session.getAttribute("asesorparam") != null) {
                asesores = (Asesores) session.getAttribute("asesorparam");
                session.removeAttribute("asesorparam");
                boton = "Actualizar";
                lstRoles = dao.listar();
                lstasesor = daoase.listarAsesores();
                lstsupervisores = daoase.listarSupervisores();
            } else {
                asesores = new Asesores();
                lstRoles = dao.listar();
                boton = "Agregar";
                lstasesor = daoase.listarAsesores();
                lstsupervisores = daoase.listarSupervisores();

            }
        } catch (Exception ex) {
            Logger.getLogger(AsesoresBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verprospectos(Asesores asesor, HttpSession session) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("prospectodeasesor", asesor);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectosAsesor.xhtml");
    }
    
    public void muestradependientes(AjaxBehaviorEvent e) throws Exception {
        String opcion = (String) ((UIOutput) e.getSource()).getValue();
        AsesoresDAO dao;
        /*if (opcion != null) {
            try {
                dao = new AsesoresDAO();
                //lstparcelas = daoparcelas.listar();
                if (estado.getMotivo().equals("FALSE")) {
                    muestramotivos = false;
                } else {
                    muestramotivos = true;
                }
                if (estado.getPermitellamar().equals("FALSE")) {
                    muestravll = false;
                } else {
                    muestravll = true;
                }
                if (estado.getCerrada().equals("FALSE")) {
                    muestralote = false;
                } else {
                    muestralote = true;
                }

            } catch (Exception ex) {
                throw ex;
            }
        }*/
    }
}
