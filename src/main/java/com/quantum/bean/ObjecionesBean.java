package com.quantum.bean;

import com.quantum.dao.ObjecionesDAO;
import com.quantum.dao.TipobjecionesDAO;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Objeciones;
import com.quantum.modelos.TipObjeciones;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author QUANTUM
 */
@Named("objecionesBean")
@ViewScoped
public class ObjecionesBean implements Serializable {

    private Mensaje message = new Mensaje(false, "none !important", "a");

    private Objeciones objeciones = new Objeciones();
    private List<Objeciones> lstObjeciones;

    private TipObjeciones tipobjecion = new TipObjeciones();
    private List<TipObjeciones> lstTipobjecion;

    private String boton;

    private String script;

    public void notificacion(Mensaje message) {
       //"notify","error","success","warning":
        script = "runNotify({\n" +
"                                                        type: '"+message.getClase()+"',\n" +
"                                                        message: '"+message.getMensaje()+"',\n" +
"                                                        timer: 6000\n" +
"                                                    });";
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public Objeciones getObjeciones() {
        return objeciones;
    }

    public void setObjeciones(Objeciones objeciones) {
        this.objeciones = objeciones;
    }

    public List<Objeciones> getLstObjeciones() {
        return lstObjeciones;
    }

    public void setLstObjeciones(List<Objeciones> lstObjeciones) {
        this.lstObjeciones = lstObjeciones;
    }

    public TipObjeciones getTipobjecion() {
        return tipobjecion;
    }

    public void setTipobjecion(TipObjeciones tipobjecion) {
        this.tipobjecion = tipobjecion;
    }

    public List<TipObjeciones> getLstTipobjecion() {
        return lstTipobjecion;
    }

    public void setLstTipobjecion(List<TipObjeciones> lstTipobjecion) {
        this.lstTipobjecion = lstTipobjecion;
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

    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
    }

    public void operar() {
        try {
            if (boton.equals("Agregar")) {
                this.registrar();
            } else {
                this.modificarobjecion();
            }
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void operartipo() {
        try {
            if (boton.equals("Agregar")) {
                this.registrartipo();
            } else {
                this.modificartipo();

            }
            this.limpiar();
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void limpiar() {
        boton = "Agregar";
        tipobjecion.setCodigo("");
        tipobjecion.setTipo("");
        objeciones.setCodigo("");
        objeciones.setObjecion("");
        objeciones.setTipobjecion("");
    }

    public void registrartipo() throws Exception {
        TipobjecionesDAO dao;
        Mensaje mensaje;

        try {
            dao = new TipobjecionesDAO();
            mensaje = dao.registrar(tipobjecion);
            message = mensaje;
            this.notificacion(message);
            this.limpiar();

        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void registrar() throws Exception {
        ObjecionesDAO dao;
        Mensaje mensaje;

        try {
            dao = new ObjecionesDAO();
            mensaje = dao.registrar(objeciones);
            message = mensaje;
            this.limpiar();
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void listar() throws Exception {

        ObjecionesDAO dao;
        TipobjecionesDAO tipdao;
        if (boton != null) {

        } else {
            boton = "Agregar";
        }
        try {
            dao = new ObjecionesDAO();
            tipdao = new TipobjecionesDAO();

            lstObjeciones = dao.listar();
            lstTipobjecion = tipdao.listar();
            //boton = "Agregar";
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarTipos() throws Exception {
        TipobjecionesDAO dao;
        try {
            dao = new TipobjecionesDAO();
            lstTipobjecion = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(TipObjeciones tipo, HttpSession session) throws IOException {
        tipobjecion = tipo;
        boton = "Actualizar";
        /*FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("tipoparam", tipo);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/tipobjecion.xhtml");*/
    }

    public void leerIDobjeciones(Objeciones objecion, HttpSession session) throws IOException {

        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("objecionparam", objecion);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/objecion.xhtml");
    }

    public void modificartipo() throws Exception {
        TipobjecionesDAO dao;
        Mensaje mensaje;

        try {
            dao = new TipobjecionesDAO();
            mensaje = dao.modificar(tipobjecion);
            this.limpiar();
            message = mensaje;
            this.notificacion(message);
            // FacesContext contex = FacesContext.getCurrentInstance();
            // contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaObjeciones.xhtml");
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void modificarobjecion() throws Exception {
        ObjecionesDAO dao;
        Mensaje mensaje;

        try {
            dao = new ObjecionesDAO();
            mensaje = dao.modificar(objeciones);
            this.limpiar();
            message = mensaje;
            
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/Quantum/template/listaObjeciones.xhtml");
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void borrar() {
        FacesMessages.info("idForm:idBorrar", "Se boró correctamente el servicio ", "Nunca va a volver. ¡Nunca!");
    }

    public void borrar(TipObjeciones tipo) throws Exception {
        Mensaje mensaje;
        try {
            TipobjecionesDAO dao;

            dao = new TipobjecionesDAO();
            mensaje = dao.borrar(tipo);
            message = mensaje;
            this.notificacion(message);
            this.limpiar();
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void borrarobjecion(Objeciones objecion) throws Exception {
        try {
            ObjecionesDAO dao;

            dao = new ObjecionesDAO();
            dao.borrar(objecion);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void capturartipo(HttpSession session) {
        if (session.getAttribute("tipoparam") != null) {
            tipobjecion = (TipObjeciones) session.getAttribute("tipoparam");
            session.removeAttribute("tipoparam");
            boton = "Actualizar";
        } else {
            tipobjecion = new TipObjeciones();
            boton = "Agregar";
        }
    }

    public void capturarobjecion(HttpSession session) {
        if (session.getAttribute("objecionparam") != null) {
            objeciones = (Objeciones) session.getAttribute("objecionparam");
            session.removeAttribute("objecionparam");
            boton = "Actualizar";
        } else {
            objeciones = new Objeciones();
            boton = "Agregar";
        }
    }
}
