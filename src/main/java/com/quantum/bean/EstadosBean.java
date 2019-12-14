package com.quantum.bean;

import com.quantum.dao.EstadosDAO;
import com.quantum.dao.MotivosDAO;
import com.quantum.modelos.Estado;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Motivos;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@ViewScoped
public class EstadosBean implements Serializable {

    private org.slf4j.Logger log = LoggerFactory.getLogger(EstadosBean.class);      
    
    
    private Mensaje message = new Mensaje(false, "none !important", "");

    private Estado estados = new Estado();
    private List<Estado> lstEstados;

    private Motivos motivos = new Motivos();
    private List<Motivos> lstMotivos;

    private formatoDeFechas fechas = new formatoDeFechas();
    private String boton;

    public Estado getEstados() {
        return estados;
    }

    public void setEstados(Estado estados) {
        this.estados = estados;
    }

    public List<Estado> getLstEstados() {
        return lstEstados;
    }

    public void setLstEstados(List<Estado> lstEstados) {
        this.lstEstados = lstEstados;
    }

    public Motivos getMotivos() {
        return motivos;
    }

    public void setMotivos(Motivos motivos) {
        this.motivos = motivos;
    }

    public List<Motivos> getLstMotivos() {
        return lstMotivos;
    }

    public void setLstMotivos(List<Motivos> lstMotivos) {
        this.lstMotivos = lstMotivos;
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
            
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    public void operarmotivos() {
        try {
            if (boton.equals("Agregar")) {
                this.registrarMotivo();
            } else {
                this.modificarmotivo();
            }
        } catch (Exception e) {
            
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    
    public void limpiar() {
        estados.setCodigo("");
        estados.setEstado("");
        estados.setPermitellamar("");
        estados.setMotivo("");
        estados.setCerrada("");
        motivos.setCodmot("");
        motivos.setEstado("");
        motivos.setMotivo("");
        motivos.setPermitellamar("");
        motivos.setReunionoficina("");
        motivos.setVisitaproyecto("");
    }

    public void registrar() throws Exception {
        EstadosDAO dao;
        Mensaje mensaje;

        try {
            dao = new EstadosDAO();
            mensaje = dao.registrar(estados);
            this.limpiar();
            message = mensaje;

        } catch (Exception e) {
            
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        }
    }

    public void registrarMotivo() throws Exception {
        MotivosDAO dao;
        Mensaje mensaje;

        try {
            dao = new MotivosDAO();
            mensaje = dao.registrar(motivos);
            message = mensaje;

        } catch (Exception e) {
            
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        }
    }

    public void listar() throws Exception {
        EstadosDAO dao;
        MotivosDAO daopro;

        try {
            dao = new EstadosDAO();
            daopro = new MotivosDAO();
            lstEstados = dao.listar();
            lstMotivos = daopro.listar();
        } catch (Exception e) {
            
            log.info(e.getMessage());
            throw e;
        }
    }

    public void listarEstados() throws Exception {
        EstadosDAO dao;
        try {
            dao = new EstadosDAO();
            lstEstados = dao.listar();
        } catch (Exception e) {
            
            log.info(e.getMessage());
            throw e;
        }
    }

    public void leerID(Estado estados, HttpSession session) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("estadosparam", estados);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/estados.xhtml");
    }
    
    public void leerIDmotivo(Motivos motivo, HttpSession session) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("motivoparam", motivo);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/motivos.xhtml");
    }


    public void modificar() throws Exception {
        EstadosDAO dao;
        Mensaje mensaje;
        try {
            dao = new EstadosDAO();
            mensaje = dao.modificar(estados);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaEstados.xhtml");
        } catch (Exception e) {
            
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    public void modificarmotivo() throws Exception {
        MotivosDAO dao;
        Mensaje mensaje;

        try {
            dao = new MotivosDAO();
            mensaje = dao.modificar(motivos);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaMotivos.xhtml");
        } catch (Exception e) {
            
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void borrar(Estado estados) throws Exception {
        try {
            EstadosDAO dao;

            dao = new EstadosDAO();
            dao.borrar(estados);
            this.listar();
        } catch (Exception e) {
            
            log.info(e.getMessage());
            throw e;
        }
    }
    
    public void borrarmotivo(Motivos motivo) throws Exception {
        try {
            MotivosDAO dao;

            dao = new MotivosDAO();
            dao.borrar(motivo);
            this.listar();
        } catch (Exception e) {
            
            log.info(e.getMessage());
            throw e;
        }
    }

    public void capturarestdos(HttpSession session) {
        if (session.getAttribute("estadosparam") != null) {
            estados = (Estado) session.getAttribute("estadosparam");
            session.removeAttribute("estadosparam");
            boton = "Actualizar";
        } else {
            estados = new Estado();
            boton = "Agregar";
        }
    }
    
    public void capturarmotivo(HttpSession session) {
        if (session.getAttribute("motivoparam") != null) {
            motivos = (Motivos) session.getAttribute("motivoparam");
            session.removeAttribute("motivoparam");
            boton = "Actualizar";
        } else {
            motivos = new Motivos();
            boton = "Agregar";
        }
    }

}
