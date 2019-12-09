package com.quantum.bean;

import com.quantum.dao.AsesoresDAO;
import com.quantum.dao.DistribucionDAO;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Distribucion;
import com.quantum.modelos.Mensaje;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@ViewScoped
public class AsignaProspectosBean implements Serializable {

    private org.slf4j.Logger log = LoggerFactory.getLogger(AsignaProspectosBean.class);      
    
    
    private Mensaje message = new Mensaje(false, "none !important", "");
    private formatoDeFechas fechas = new formatoDeFechas();

    private Distribucion distribucion = new Distribucion();
    private List<Distribucion> lstDistribucion;

    private String boton;
    private String porcentajepAsignar;
    private String prospectospAsignar;

    public String getProspectospAsignar() {
        return prospectospAsignar;
    }

    public void setProspectospAsignar(String prospectospAsignar) {
        this.prospectospAsignar = prospectospAsignar;
    }

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public formatoDeFechas getFechas() {
        return fechas;
    }

    public void setFechas(formatoDeFechas fechas) {
        this.fechas = fechas;
    }

    public Distribucion getDistribucion() {
        return distribucion;
    }

    public void setDistribucion(Distribucion distribucion) {
        this.distribucion = distribucion;
    }

    public List<Distribucion> getLstDistribucion() {
        return lstDistribucion;
    }

    public void setLstDistribucion(List<Distribucion> lstDistribucion) {
        this.lstDistribucion = lstDistribucion;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public void limpiar() {
        this.lstDistribucion = null;
    }

    public void operar() throws Exception {
        DistribucionDAO dao;
        Mensaje mensaje;

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new DistribucionDAO();
                mensaje = dao.distribuir(lstDistribucion,usuariosBean.getAsesorVali());
                message = mensaje;
                //dao.actualizarprospectos();
            } catch (Exception e) {
                message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void listar() throws Exception {
        DistribucionDAO dao;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new DistribucionDAO();

                lstDistribucion = dao.listar(usuariosBean.getAsesorVali());
                prospectospAsignar = dao.cantprospectos(usuariosBean.getAsesorVali());

            } catch (Exception e) {
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void leerID(Asesores asesor, HttpSession session) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("asesorparam", asesor);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/asesores.xhtml");
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

    public void finalSession() throws Exception {
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().invalidateSession();
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath());
    }

}
