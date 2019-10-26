package com.quantum.bean;

import com.quantum.dao.AsesoresDAO;
import com.quantum.dao.EstatusventasDAO;
import com.quantum.dao.ProcesosestatusDAO;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Estatusventas;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Procesosestatus;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@ViewScoped
public class EstatusventasBean implements Serializable {

    private Mensaje message = new Mensaje(false, "none !important", "");

    private Estatusventas estatus = new Estatusventas();
    private List<Estatusventas> lstEstatus;

    private Procesosestatus procesos = new Procesosestatus();
    private List<Procesosestatus> lstProcesos;

    private formatoDeFechas fechas = new formatoDeFechas();
    private String boton;

    public Estatusventas getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatusventas estatus) {
        this.estatus = estatus;
    }

    public List<Estatusventas> getLstEstatus() {
        return lstEstatus;
    }

    public void setLstEstatus(List<Estatusventas> lstEstatus) {
        this.lstEstatus = lstEstatus;
    }

    public Procesosestatus getProcesos() {
        return procesos;
    }

    public void setProcesos(Procesosestatus procesos) {
        this.procesos = procesos;
    }

    public List<Procesosestatus> getLstProcesos() {
        return lstProcesos;
    }

    public void setLstProcesos(List<Procesosestatus> lstProcesos) {
        this.lstProcesos = lstProcesos;
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
    
    public void operarproceos() {
        try {
            if (boton.equals("Agregar")) {
                this.registrarProceso();
            } else {
                this.modificarproceso();
            }
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    
    public void limpiar() {
        estatus.setCodigo("");
        estatus.setEstatus("");
        procesos.setCodpro("");
        procesos.setEstatus("");
        procesos.setProceso("");
    }

    public void registrar() throws Exception {
        EstatusventasDAO dao;
        Mensaje mensaje;

        try {
            dao = new EstatusventasDAO();
            mensaje = dao.registrar(estatus);
            this.limpiar();
            message = mensaje;

        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        }
    }

    public void registrarProceso() throws Exception {
        ProcesosestatusDAO dao;
        Mensaje mensaje;

        try {
            dao = new ProcesosestatusDAO();
            mensaje = dao.registrar(procesos);
            message = mensaje;

        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        }
    }

    public void listar() throws Exception {
        EstatusventasDAO dao;
        ProcesosestatusDAO daopro;

        try {
            dao = new EstatusventasDAO();
            daopro = new ProcesosestatusDAO();
            lstEstatus = dao.listar();
            lstProcesos = daopro.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarEstatus() throws Exception {
        EstatusventasDAO dao;
        try {
            dao = new EstatusventasDAO();
            lstEstatus = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(Estatusventas estatus, HttpSession session) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("estatusparam", estatus);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/estatus.xhtml");
    }
    
    public void leerIDproceso(Procesosestatus proceso, HttpSession session) throws IOException {

        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("procesoparam", proceso);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/procesos.xhtml");
    }


    public void modificar() throws Exception {
        EstatusventasDAO dao;
        Mensaje mensaje;

        try {
            dao = new EstatusventasDAO();
            mensaje = dao.modificar(estatus);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaEstatus.xhtml");
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    public void modificarproceso() throws Exception {
        ProcesosestatusDAO dao;
        Mensaje mensaje;

        try {
            dao = new ProcesosestatusDAO();
            mensaje = dao.modificar(procesos);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaEstatus.xhtml");
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void borrar(Estatusventas estatus) throws Exception {
        try {
            EstatusventasDAO dao;

            dao = new EstatusventasDAO();
            dao.borrar(estatus);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void borrarproceso(Procesosestatus proceso) throws Exception {
        try {
            ProcesosestatusDAO dao;

            dao = new ProcesosestatusDAO();
            dao.borrar(proceso);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void capturarestatus(HttpSession session) {
        if (session.getAttribute("estatusparam") != null) {
            estatus = (Estatusventas) session.getAttribute("estatusparam");
            session.removeAttribute("estatusparam");
            boton = "Actualizar";
        } else {
            estatus = new Estatusventas();
            boton = "Agregar";
        }
    }
    
    public void capturarproceso(HttpSession session) {
        if (session.getAttribute("procesoparam") != null) {
            procesos = (Procesosestatus) session.getAttribute("procesoparam");
            session.removeAttribute("procesoparam");
            boton = "Actualizar";
        } else {
            procesos = new Procesosestatus();
            boton = "Agregar";
        }
    }

}
