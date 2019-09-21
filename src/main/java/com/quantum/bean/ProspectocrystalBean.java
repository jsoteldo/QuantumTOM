package com.quantum.bean;

import com.quantum.dao.OrigenesDAO;
import com.quantum.dao.ProspectocrystalDAO;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Prospectocrystal;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author QUANTUM
 */
@Named("prospectocrystalBean")
@ViewScoped
public class ProspectocrystalBean implements Serializable {

    private Prospectocrystal prospecto = new Prospectocrystal();
    
    private Mensaje message = new Mensaje(false, "none !important", "");
    
    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public Prospectocrystal getProspecto() {
        return prospecto;
    }

    public void setProspecto(Prospectocrystal prospecto) {
        this.prospecto = prospecto;
    }

      
    public void limpiar(){
        prospecto = null;
    }
    
    
    public void registrar() throws Exception {
        
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = (UsuariosBean) objeto;
        ProspectocrystalDAO dao;
        Mensaje mensaje;
        
        try {
            dao = new ProspectocrystalDAO();
            mensaje = dao.registrar(prospecto,usuariosBean.getAsesorVali());
            message = mensaje;
            this.limpiar();
           
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(),"mdi-close-circle-outline","danger");
            
        }
    }

}
