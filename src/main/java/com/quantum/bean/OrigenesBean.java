package com.quantum.bean;

import com.quantum.dao.OrigenesDAO;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Origenes;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author QUANTUM
 */
@Named("origenesBean")
@ViewScoped
public class OrigenesBean implements Serializable {

    private Origenes origenes = new Origenes();
    private List<Origenes> lstOrigenes;

    private Mensaje message = new Mensaje(false, "none !important", "");
    private formatoDeFechas fechas = new formatoDeFechas();
    private String boton;
    
    public Origenes getOrigenes() {
        return origenes;
    }

    public void setOrigenes(Origenes origenes) {
        this.origenes = origenes;
    }

    public List<Origenes> getLstOrigenes() {
        return lstOrigenes;
    }

    public void setLstOrigenes(List<Origenes> lstOrigenes) {
        this.lstOrigenes = lstOrigenes;
    }

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    
    public void limpiar(){
        origenes.setCodigo("");
        origenes.setOrigen("");
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
        OrigenesDAO dao;
        Mensaje mensaje;
        
        try {
            dao = new OrigenesDAO();
            mensaje = dao.registrar(origenes);
            message = mensaje;
            this.limpiar();
           
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(),"mdi-close-circle-outline","danger");
            
        }
    }

    public void listar() throws Exception {
        OrigenesDAO dao;

        try {
            dao = new OrigenesDAO();
            lstOrigenes = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(Origenes origen, HttpSession session) throws Exception, IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("origenparam", origen);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/origenes.xhtml");
    }
     
    public void modificar() throws Exception {
        OrigenesDAO dao;
        Mensaje mensaje;

        try {
            dao = new OrigenesDAO();
            mensaje = dao.modificar(origenes);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaOrigenes.xhtml");
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    public void borrar(Origenes origen) throws Exception {
        try {
            OrigenesDAO dao;

            dao = new OrigenesDAO();
            dao.borrar(origen);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void capturarorigen(HttpSession session) {
        if (session.getAttribute("origenparam") != null) {
            origenes = (Origenes) session.getAttribute("origenparam");
            session.removeAttribute("origenparam");
            boton = "Actualizar";
        } else {
            origenes = new Origenes();
            boton = "Agregar";
        }
    }
}
