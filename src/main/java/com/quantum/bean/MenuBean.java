package com.quantum.bean;

import com.quantum.dao.MenuDAO;
import com.quantum.dao.OrigenesDAO;
import com.quantum.dao.ProspectosDAO;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Menu;
import com.quantum.modelos.Prospectos;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author QUANTUM
 */
@Named("menuBean")
@ViewScoped
public class MenuBean implements Serializable {

    private Mensaje message = new Mensaje(false, "none !important", "");
    private formatoDeFechas fechas = new formatoDeFechas();

    private Menu menu = new Menu();
    private List<Menu> lstMenu;
    private List<Menu> lstSubmenu;

    private String boton;

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

    public void limpiar() {
        menu.setNombre("");
        menu.setDescripcion("");
        menu.setIco("");
        menu.setLink("");
        menu.setType("");
        menu.setCodigo("");
        menu.setPadre("");
        menu.setCheck("");
    }

    public void operar() {
        try {
            if (boton.equals("Agregar")) {
                this.registrar();
            } else {

            }
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void registrar() throws Exception {
        MenuDAO dao;
        Mensaje mensaje;
        String descrip = "";
        FacesContext contex = FacesContext.getCurrentInstance();
        try {
            dao = new MenuDAO();
            menu.setCheck("false");

            for (int x = 0; x < menu.getNombre().toLowerCase().length(); x++) {
                if (menu.getNombre().toLowerCase().charAt(x) != ' ') {
                    descrip += menu.getNombre().toLowerCase().charAt(x);
                }
            }
            menu.setDescripcion(descrip);
            
            if (menu.getType().equals("S") || menu.getType().equals("I")) {
                menu.setLink(contex.getExternalContext().getApplicationContextPath()+"/template/" + menu.getLink().trim().toLowerCase() + ".xhtml");
            } else {
                menu.setLink("#ui-" + menu.getDescripcion());
            }

            mensaje = dao.registrar(menu);
            this.limpiar();
            message = mensaje;
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void listar() throws Exception {
        MenuDAO dao;
        try {
            dao = new MenuDAO();
            lstMenu = dao.listarMenu();
            lstSubmenu = dao.listarSubmenu();
        } catch (Exception e) {
            throw e;
        }
    }

    public void muestramenus(ValueChangeEvent e) throws Exception {
        String opcion = e.getNewValue().toString();
        this.limpiarcampos();
        MenuDAO dao;

        if (opcion.equals("S")) {
            try {
                dao = new MenuDAO();
                lstMenu = dao.listaMenu();
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    public void limpiarcampos() {
        menu.setNombre("");
        menu.setDescripcion("");
        menu.setIco("");
        menu.setLink("");
        menu.setCodigo("");
        menu.setPadre("");
        menu.setCheck("");
    }

    public void leerID(Menu menu, HttpSession session) throws Exception, IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("menuparam", menu);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath()+"/template/menu.xhtml");
    }

    public void borrar(Menu menu) throws Exception {
        try {
            MenuDAO dao;

            dao = new MenuDAO();
            dao.borrar(menu);
            this.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void capturarmenu(HttpSession session) {
        if (session.getAttribute("menuparam") != null) {
            menu = (Menu) session.getAttribute("menuparam");
            session.removeAttribute("menuparam");
            boton = "Actualizar";
        } else {
            //menu = new Menu();
            boton = "Agregar";
        }
    }

}
