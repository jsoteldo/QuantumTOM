package com.quantum.bean;

import com.quantum.dao.MenuDAO;
import com.quantum.dao.RolesDAO;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Menu;
import com.quantum.modelos.Roles;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author QUANTUM
 */
@Named("rolesBean")
@ViewScoped
public class RolesBean implements Serializable {

    private Mensaje message = new Mensaje(false, "none !important", "");

    private Roles rol = new Roles();
    private List<Roles> lstRoles;

    private formatoDeFechas fechas = new formatoDeFechas();

    private Menu menu = new Menu();
    private List<Menu> lstMenu;
    private List<Menu> lstSubmenu;

    private Map<String, Boolean> permisoChecked = new HashMap<>();

    private String boton;

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
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void limpiar() {
        rol.setRol("");
        rol.setDescripcion("");
        rol.setPermiso(null);
        this.setPermisoChecked(null);
    }

    public void registrar() throws Exception {
        RolesDAO dao;
        Mensaje mensaje;

        try {
            List<String> permiso = new ArrayList<String>();

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
            rol.setPermiso(permiso);

            dao = new RolesDAO();
            mensaje = dao.registrar(rol);
            this.limpiar();
            message = mensaje;

        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        }

    }

    public void listar() throws Exception {
        RolesDAO dao= new RolesDAO();
        try {
            lstRoles = dao.listar();

        } catch (Exception e) {
            throw e;
        }
    }

    public void listarRoles() throws Exception {
        MenuDAO daomenu;

        try {
            daomenu = new MenuDAO();

            lstMenu = daomenu.listarMenu();
            lstSubmenu = daomenu.listarSubmenu();
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerID(Roles rol, HttpSession session) throws IOException {

        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("rolparam", rol);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/roles.xhtml");
    }

    public void modificar() throws Exception {
        RolesDAO dao;
        Mensaje mensaje;

        try {
            List<String> permiso = new ArrayList<String>();

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
            rol.setPermiso(permiso);
            dao = new RolesDAO();
            mensaje = dao.modificar(rol);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/listaRoles.xhtml");
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void borrar(Roles rol) throws Exception {
        try {
            RolesDAO dao;

            dao = new RolesDAO();
            dao.borrar(rol);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void capturarrol(HttpSession session) {
        MenuDAO daomenu;
        
        lstMenu = null;
        lstSubmenu = null;

        try {

            daomenu = new MenuDAO();

            if (session.getAttribute("rolparam") != null) {
                rol = (Roles) session.getAttribute("rolparam");
                session.removeAttribute("rolparam");

                lstMenu = daomenu.listarMenu();
                
                for(Menu item : lstMenu){
                    for(String permiso : rol.getPermiso()){
                        if(item.getCodigo().equals(permiso.trim())){
                            item.setCheck("true");
                        }
                    }
                }
                
                lstSubmenu= daomenu.listarSubmenu();
                
                for(Menu item : lstSubmenu){
                    for(String permiso : rol.getPermiso()){
                        if(item.getCodigo().equals(permiso.trim())){
                            item.setCheck("true");
                        }
                    }
                }
                
                
                boton = "Actualizar";
            } else {
                rol = new Roles();
                boton = "Agregar";
                lstMenu = daomenu.listarMenu();
                lstSubmenu = daomenu.listarSubmenu();
            }
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

}
