package com.quantum.bean;

import com.quantum.dao.IconosDAO;
import com.quantum.modelos.Iconos;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@ViewScoped
public class IconosBean implements Serializable {

    private org.slf4j.Logger log = LoggerFactory.getLogger(IconosBean.class);   
    
    private Iconos iconos = new Iconos();
    private List<Iconos> lstIconos;

    public Iconos getIconos() {
        return iconos;
    }

    public void setIconos(Iconos iconos) {
        this.iconos = iconos;
    }

    public List<Iconos> getLstIconos() {
        return lstIconos;
    }

    public void setLstIconos(List<Iconos> lstIconos) {
        this.lstIconos = lstIconos;
    }

    public void listar() throws Exception {
        IconosDAO dao;

        try {
            dao = new IconosDAO();
            lstIconos = dao.listar();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    
}
