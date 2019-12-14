package com.quantum.bean;

import com.quantum.dao.CamposDAO;
import com.quantum.dao.TipocampoDAO;
import com.quantum.modelos.Campos;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Objeciones;
import com.quantum.modelos.Tipocampo;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import net.bootsfaces.utils.FacesMessages;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@ViewScoped
public class TipocampoBean implements Serializable {

    private org.slf4j.Logger log = LoggerFactory.getLogger(TipocampoBean.class);

    private Mensaje message = new Mensaje(false, "none !important", "a");

    private Campos campo = new Campos();
    private String campoOdl;
    private List<Campos> lstcampos;
    private String capacidad = "none";
    
    private Tipocampo tipocampo = new Tipocampo();
    private List<Tipocampo> lstTipocampo;

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

    public Campos getCampo() {
        return campo;
    }

    public void setCampo(Campos campo) {
        this.campo = campo;
    }

    public List<Campos> getLstcampos() {
        return lstcampos;
    }

    public void setLstcampos(List<Campos> lstcampos) {
        this.lstcampos = lstcampos;
    }

    public Tipocampo getTipocampo() {
        return tipocampo;
    }

    public void setTipocampo(Tipocampo tipocampo) {
        this.tipocampo = tipocampo;
    }

    public List<Tipocampo> getLstTipocampo() {
        return lstTipocampo;
    }

    public void setLstTipocampo(List<Tipocampo> lstTipocampo) {
        this.lstTipocampo = lstTipocampo;
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

    public String getCampoOdl() {
        return campoOdl;
    }

    public void setCampoOdl(String campoOdl) {
        this.campoOdl = campoOdl;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
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
                this.modificarcampo();
            }
        } catch (Exception e) {
            log.info(e.getMessage());

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
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void limpiar() {
        boton = "Agregar";
        tipocampo.setTipo("");
        tipocampo.setValor("");
        campo.setNombre("");
        campo.setDescripcion("");
        campo.setTipo("");
        campo.setCapacidad("");
        campo.setPrioridad("");
        capacidad = "none";
    }

    public void registrartipo() throws Exception {
        TipocampoDAO dao;
        Mensaje mensaje;

        try {
            dao = new TipocampoDAO();
            mensaje = dao.registrar(tipocampo);
            message = mensaje;
            this.notificacion(message);
            this.limpiar();

        } catch (Exception e) {
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void registrar() throws Exception {
        CamposDAO dao;
        Mensaje mensaje;

        try {
            dao = new CamposDAO();
            mensaje = dao.registrar(campo);
            message = mensaje;
            this.notificacion(message);
            this.limpiar();
        } catch (Exception e) {
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void listar() throws Exception {

        CamposDAO dao;
        TipocampoDAO tipdao;
        if (boton != null) {

        } else {
            boton = "Agregar";
        }
        try {
            dao = new CamposDAO();
            tipdao = new TipocampoDAO();

            lstcampos = dao.listar();
            lstTipocampo = tipdao.listar();
            //boton = "Agregar";
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }
    }

    public void listarTipos() throws Exception {
        TipocampoDAO dao;
        try {
            dao = new TipocampoDAO();
            lstTipocampo = dao.listar();
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }
    }

    public void leerID(Tipocampo tipo, HttpSession session) throws IOException {
        tipocampo = tipo;
        boton = "Actualizar";
        /*FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("tipoparam", tipo);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/tipobjecion.xhtml");*/
    }
    
    public void leerIDcampo(Campos campo, HttpSession session) throws IOException {
        System.out.println(campo);
        this.campo = campo;
        this.capacidad = "";
        this.setCampoOdl(campo.getNombre());
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
        TipocampoDAO dao;
        Mensaje mensaje;

        try {
            dao = new TipocampoDAO();
            mensaje = dao.modificar(tipocampo);
            this.limpiar();
            message = mensaje;
            this.notificacion(message);
            // FacesContext contex = FacesContext.getCurrentInstance();
            // contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaObjeciones.xhtml");
        } catch (Exception e) {
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    public void modificarcampo() throws Exception {
        CamposDAO dao;
        Mensaje mensaje;

        try {
            dao = new CamposDAO();
            mensaje = dao.modificar(campo, this.getCampoOdl());
            this.limpiar();
            message = mensaje;
            this.notificacion(message);
            // FacesContext contex = FacesContext.getCurrentInstance();
            // contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaObjeciones.xhtml");
        } catch (Exception e) {
            log.info(e.getMessage());

            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    
    public void borrar() {
        FacesMessages.info("idForm:idBorrar", "Se borró correctamente el servicio ", "Nunca va a volver. ¡Nunca!");
    }

    public void borrar(Tipocampo tipo) throws Exception {
        Mensaje mensaje;
        try {
            TipocampoDAO dao;

            dao = new TipocampoDAO();
            mensaje = dao.borrar(tipo);
            message = mensaje;
            this.notificacion(message);
            this.limpiar();
            this.listar();
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }
    }

    public void borrarcampo(Campos campo) throws Exception {
        Mensaje mensaje;
        try {
            CamposDAO dao;

            dao = new CamposDAO();
            mensaje = dao.borrar(campo);
            message = mensaje;
            this.notificacion(message);
            this.limpiar();
            this.listar();
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }
    }

    public void capturartipo(HttpSession session) {
        if (session.getAttribute("tipoparam") != null) {
            tipocampo = (Tipocampo) session.getAttribute("tipoparam");
            session.removeAttribute("tipoparam");
            boton = "Actualizar";
        } else {
            tipocampo = new Tipocampo();
            boton = "Agregar";
        }
    }

    public void capturarobjecion(HttpSession session) {
        if (session.getAttribute("objecionparam") != null) {
            campo = (Campos) session.getAttribute("objecionparam");
            session.removeAttribute("objecionparam");
            boton = "Actualizar";
        } else {
            campo = new Campos();
            boton = "Agregar";
        }
    }
}
