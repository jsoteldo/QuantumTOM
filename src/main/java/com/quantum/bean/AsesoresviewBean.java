package com.quantum.bean;

import com.quantum.dao.AsesoresDAO;
import com.quantum.dao.ProspectosDAO;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Prospectos;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@ViewScoped
public class AsesoresviewBean implements Serializable {
    
    private org.slf4j.Logger log = LoggerFactory.getLogger(AsesoresviewBean.class);      
    
    
    private Mensaje message = new Mensaje(false, "none !important", "");

    private Asesores asesores = new Asesores();
    private List<Asesores> lstAsesores;

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

    public void limpiar(){
        asesores.setApellidos("");
        asesores.setContrasena("");
        asesores.setCorreo("");
        asesores.setEdad("");
        asesores.setNombres("");
        asesores.setSexo("");
        asesores.setTelefono("");
        asesores.setTelefono_asig("");
    }
    
    public void registrar() throws Exception {
        AsesoresDAO dao;
        Mensaje mensaje;
        try {
            dao = new AsesoresDAO();
            mensaje = dao.registrar(asesores);
            this.limpiar();
            message = mensaje;
            
        } catch (Exception e) {
            log.info( e.getMessage());
            message = new Mensaje("", e.getMessage(),"mdi-close-circle-outline","danger");
        }
    }

    public void listar() throws Exception {
        AsesoresDAO dao;

        try {
            dao = new AsesoresDAO();
            lstAsesores = dao.listar();
        } catch (Exception e) {
            log.info( e.getMessage());
            throw e;
        }
    }

    public void leerID(Asesores asesor) throws Exception, IOException {
        ProspectosDAO dao;
        Prospectos temporal;
        asesores=asesor;
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/asesores.xhtml");

        /*
        try{
            dao = new ProspectosDAO();
            temporal = dao.leerId(prospectos);
            if(temporal != null){
                this.prospectos = temporal; 
            }
        } catch (Exception e){
            throw e;
        }*/
    }
    
    public void borrar(Asesores asesor) throws Exception {
       // System.out.println(asesor);
        
    }
}
