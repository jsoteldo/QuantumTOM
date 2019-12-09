package com.quantum.bean;

import com.quantum.dao.AnunciosDAO;
import com.quantum.dao.CampanasDAO;
import com.quantum.dao.ConjuntoDAO;
import com.quantum.modelos.Anuncios;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Campanas;
import com.quantum.modelos.Conjunto;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Roles;
import com.quantum.servicios.formatoDeFechas;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIOutput;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@ViewScoped
public class MarketingBean implements Serializable {

    private org.slf4j.Logger log = LoggerFactory.getLogger(MarketingBean.class);   
    
    
    private Mensaje message = new Mensaje(false, "none !important", "");
    private formatoDeFechas fechas = new formatoDeFechas();

    private Conjunto conjunto = new Conjunto();
    private Anuncios anuncio = new Anuncios();
    private Campanas campana = new Campanas();
    private List<Asesores> lstAsesores;
    private List<Campanas> lstCampanas;
    private List<Conjunto> lstConjunto;
    private List<Anuncios> lstAnuncios;
    private List<Asesores> lstsupervisores;
    private List<Asesores> lstasesor;
    
    private Map<String, Boolean> asesoresChecked = new HashMap<>();
    private Map<String, Boolean> supervisoresChecked = new HashMap<>();
    
    private List<Roles> lstRoles;

    private String boton;

    public Conjunto getConjunto() {
        return conjunto;
    }

    public void setConjunto(Conjunto conjunto) {
        this.conjunto = conjunto;
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

    public List<Conjunto> getLstConjunto() {
        return lstConjunto;
    }

    public void setLstConjunto(List<Conjunto> lstConjunto) {
        this.lstConjunto = lstConjunto;
    }

    public List<Anuncios> getLstAnuncios() {
        return lstAnuncios;
    }

    public void setLstAnuncios(List<Anuncios> lstAnuncios) {
        this.lstAnuncios = lstAnuncios;
    }

    public Anuncios getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncios anuncio) {
        this.anuncio = anuncio;
    }

    public List<Campanas> getLstCampanas() {
        return lstCampanas;
    }

    public void setLstCampanas(List<Campanas> lstCampanas) {
        this.lstCampanas = lstCampanas;
    }

    public Campanas getCampana() {
        return campana;
    }

    public void setCampana(Campanas campana) {
        this.campana = campana;
    }

    public void listar() throws Exception {
        ConjuntoDAO dao;
        AnunciosDAO daoanun;
        CampanasDAO daocampa;
        try {
            dao = new ConjuntoDAO();
            daoanun = new AnunciosDAO();
            daocampa = new CampanasDAO();
            lstConjunto = dao.listar();
            lstAnuncios = daoanun.listar();
            lstCampanas = daocampa.listar();
            
            boton= "Guardar";
            
        } catch (Exception e) {
            throw e;
        }
    }

    public void operarConjunto() {
        Mensaje mensaje;
        try {
            ConjuntoDAO dao = new ConjuntoDAO();
            mensaje = dao.modificar(conjunto);
            message = mensaje;
            this.limpiar();
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    public void operarAnuncio() {
        Mensaje mensaje;
        try {
            AnunciosDAO dao = new AnunciosDAO();
            mensaje = dao.modificar(anuncio);
            message = mensaje;
            this.limpiar();
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
    
    public void operarCampana() {
        Mensaje mensaje;
        try {
            CampanasDAO dao = new CampanasDAO();
            mensaje = dao.modificar(campana);
            message = mensaje;
            this.limpiar();
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }
 
     public void limpiar() {
        conjunto.setId("");
        conjunto.setDescripcion("");
        conjunto.setMasculino("");
        conjunto.setFemenino("");
        conjunto.setEdadini("");
        conjunto.setEdadfin("");
        conjunto.setBd("");
        conjunto.setAdicional("");
        conjunto.setZngeo("");
        anuncio.setCodigo("");
        anuncio.setDescripcion("");
        anuncio.setUrlfb("");
        anuncio.setUrlimg("");
        campana.setCodigo("");
        campana.setNombre("");
        campana.setDescripcion("");
        
    }
    
     
    public void limpiarConjunto() {
        conjunto.setMasculino("");
        conjunto.setFemenino("");
        conjunto.setEdadini("");
        conjunto.setEdadfin("");
        conjunto.setBd("");
        conjunto.setAdicional("");
        conjunto.setZngeo("");
    }
    
    public void limpiarAnuncio() {
        anuncio.setUrlfb("");
        anuncio.setUrlimg("");
    }
    
    public void limpiarCampanas() {
        campana.setDescripcion("");
    }
    
    
    public void muestraCampanas(AjaxBehaviorEvent e) throws Exception {
        String opcion = (String) ((UIOutput) e.getSource()).getValue();
        CampanasDAO dao; 
        Campanas campanaajax; 
        this.limpiarConjunto();
        if (opcion != null) {
            try {
                dao = new CampanasDAO();
                campanaajax = dao.consultacampana(opcion);
                campana = campanaajax;

            } catch (Exception ex) {
                throw ex;
            }
        }
    }
    
    public void muestraConjunto(AjaxBehaviorEvent e) throws Exception {
        String opcion = (String) ((UIOutput) e.getSource()).getValue();
        ConjuntoDAO dao; 
        Conjunto conjuntoajax; 
        this.limpiarConjunto();
        if (opcion != null) {
            try {
                dao = new ConjuntoDAO();
                conjuntoajax = dao.consultaconjunto(opcion);
                conjunto = conjuntoajax;

            } catch (Exception ex) {
                throw ex;
            }
        }
    }
    
    public void muestraAnuncio(AjaxBehaviorEvent e) throws Exception {
        String opcion = (String) ((UIOutput) e.getSource()).getValue();
        AnunciosDAO dao; 
        Anuncios anuncioajax; 
        this.limpiarConjunto();
        if (opcion != null) {
            try {
                dao = new AnunciosDAO();
                anuncioajax = dao.consultaanuncio(opcion);
                anuncio = anuncioajax;

            } catch (Exception ex) {
                throw ex;
            }
        }
    }
}
