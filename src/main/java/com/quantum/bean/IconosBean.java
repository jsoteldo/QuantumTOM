package com.quantum.bean;

import com.quantum.dao.IconosDAO;
import com.quantum.modelos.Iconos;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author QUANTUM
 */
@Named("iconosBean")
@ViewScoped
public class IconosBean implements Serializable {

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
            throw e;
        }
    }

    
}
