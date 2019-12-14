package com.quantum.bean;

import com.quantum.dao.MenuDAO;
import com.quantum.dao.MenuRolesDAO;
import com.quantum.dao.RolesDAO;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Menu;
import com.quantum.modelos.Roles;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.slf4j.LoggerFactory;

/**
 *
 * @author RIO CASMA
 */
@ManagedBean
@ViewScoped
public class MenurolesBean implements Serializable {

    private org.slf4j.Logger log = LoggerFactory.getLogger(MenurolesBean.class);   
    
    
    private Mensaje message = new Mensaje(false, "none !important", "");

    //private MenuRoles menurol = new MenuRoles();

    private Roles rol = new Roles();
    private List<Roles> lstRoles;

    private Menu menu = new Menu();
    private List<Menu> lstMenu;
    private List<Menu> lstSubmenu;

    private Map<String, Boolean> permisoChecked = new HashMap<>();

    private String boton;

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

    public Roles getRol() {
        return rol;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public List<Roles> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(List<Roles> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getLstMenu() {
        return lstMenu;
    }

    public void setLstMenu(List<Menu> lstMenu) {
        this.lstMenu = lstMenu;
    }

    public List<Menu> getLstSubmenu() {
        return lstSubmenu;
    }

    public void setLstSubmenu(List<Menu> lstSubmenu) {
        this.lstSubmenu = lstSubmenu;
    }

    public Map<String, Boolean> getPermisoChecked() {
        return permisoChecked;
    }

    public void setPermisoChecked(Map<String, Boolean> permisoChecked) {
        this.permisoChecked = permisoChecked;
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

    public void limpiar() {
     
    }

    public void registrar() throws Exception {
        MenuRolesDAO dao;
        Mensaje mensaje;
        try {
            List<String> permiso = new ArrayList<String>() ;

            for (Entry<String, Boolean> entry : permisoChecked.entrySet()) {
                if (entry.getValue() == true) {
                    permiso.add(entry.getKey());
                    if (entry.getKey().length() > 0) {
                        if (!permiso.contains(entry.getKey().substring(0, 1))) {
                            permiso.add(entry.getKey().substring(0, 1));
                        }
                    }
                }
            }

         //   menurol.setPermiso(permiso);

            dao = new MenuRolesDAO();
          //  mensaje = dao.registrar(menurol);
            this.limpiar();
          //  message = mensaje;
        } catch (Exception e) {
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        }
    }

    public void listar() throws Exception {
        RolesDAO dao;

        try {
            /*dao = new RolesDAO();
            lstRoles = dao.listar();*/

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    public void listarRoles() throws Exception {
        RolesDAO dao;
        MenuDAO daomenu;

        try {
            dao = new RolesDAO();
            daomenu = new MenuDAO();

            lstRoles = dao.listar();
            lstMenu = daomenu.listarMenu();
            lstSubmenu = daomenu.listarSubmenu();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    public void modificar() throws Exception {
        RolesDAO dao;
        Mensaje mensaje;

        try {
            /*   dao = new RolesDAO();
            mensaje = dao.modificar(rol);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect("/Quantum/template/listaRoles.xhtml");*/
        } catch (Exception e) {
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

}
