package com.quantum.bean;

import com.quantum.dao.AsesoresDAO;
import com.quantum.dao.DistribucionDAO;
import com.quantum.dao.GestionDAO;
import com.quantum.dao.MenuDAO;
import com.quantum.dao.ProspectosDAO;
import com.quantum.dao.RolesDAO;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Gestion;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Menu;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@SessionScoped
public class UsuariosBean implements Serializable {

    private Logger log = LoggerFactory.getLogger(UsuariosBean.class);

    private Asesores asesores = new Asesores();
    private Asesores asesorVali = new Asesores();
    private List<Gestion> lstpendientes;
    private Mensaje message = new Mensaje(false, "none !important", "");
    private List<Menu> lstMenu;
    private List<Menu> lstSubmenu;
    private int todos;
    private int pen;
    private int llamar;
    private int vc;
    private int vh;
    private int pa;
    private int repeat;

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public Asesores getAsesores() {
        return asesores;
    }

    public void setAsesores(Asesores asesores) {
        this.asesores = asesores;
    }

    public Asesores getAsesorVali() {
        return asesorVali;
    }

    public void setAsesorVali(Asesores asesorVali) {
        this.asesorVali = asesorVali;
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

    public List<Gestion> getLstpendientes() {
        return lstpendientes;
    }

    public void setLstpendientes(List<Gestion> lstpendientes) {
        this.lstpendientes = lstpendientes;
    }

    public int getTodos() {
        return todos;
    }

    public void setTodos(int todos) {
        this.todos = todos;
    }

    public int getPen() {
        return pen;
    }

    public void setPen(int pen) {
        this.pen = pen;
    }

    public int getLlamar() {
        return llamar;
    }

    public void setLlamar(int llamar) {
        this.llamar = llamar;
    }

    public int getVc() {
        return vc;
    }

    public void setVc(int vc) {
        this.vc = vc;
    }

    public int getVh() {
        return vh;
    }

    public void setVh(int vh) {
        this.vh = vh;
    }

    public int getPa() {
        return pa;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public void limpiar() {
        asesores.setApellidos("");
        asesores.setContrasena("");
        asesores.setCorreo("");
        asesores.setEdad("");
        asesores.setNombres("");
        asesores.setSexo("");
        asesores.setTelefono("");
        asesores.setTelefono_asig("");
    }

    public void cambiasession(HttpSession session) {
        session.setAttribute("viene", "inicio");
    }

    public void registrar() throws Exception {
        AsesoresDAO dao;

        try {
            dao = new AsesoresDAO();
            dao.registrar(asesores);
            this.limpiar();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }

    public void inicioSession(HttpSession session) throws Exception {
        AsesoresDAO dao;
        GestionDAO daogestion;
        Mensaje mensaje;
        log.info("Estamnos aqui");
        try {
            dao = new AsesoresDAO();
            daogestion = new GestionDAO();
            mensaje = dao.inicio(asesores);
            //
            if (mensaje.isValida() == false) {
                FacesContext contex = FacesContext.getCurrentInstance();
                this.setAsesorVali(mensaje.getAsesor());
                this.setLstpendientes(daogestion.listarPendientes(mensaje.getAsesor().getCorreo()));

                contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/inicio.xhtml");
            } else {
                message = mensaje;
                log.info(message.toString());
            }

        } catch (Exception e) {
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().invalidateSession();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/500.xhtml");
            log.info(e.getMessage());
            throw e;
        }

    }

    public void listar() throws Exception {
        MenuDAO dao;
        RolesDAO daorol;
        MenuDAO daomenu;
        GestionDAO daogestion;
        DistribucionDAO daodistribucion;

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new MenuDAO();
                daomenu = new MenuDAO();
                daorol = new RolesDAO();
                daogestion = new GestionDAO();
                daodistribucion = new DistribucionDAO();
                lstMenu = daomenu.listarMenuAsesor(daorol.consultar(this.asesorVali.getRol()).getPermiso());
                lstSubmenu = daomenu.listarSubmenuAsesor(daorol.consultar(this.asesorVali.getRol()).getPermiso());
                todos = daogestion.todos(asesorVali);
                pen = daogestion.pen(asesorVali);
                llamar = daogestion.llamar(asesorVali);
                vc = daogestion.vc(asesorVali);
                vh = daogestion.vh(asesorVali);
                pa = Integer.parseInt(daodistribucion.cantprospectos(usuariosBean.asesorVali));//usuariosBean
                repeat = daodistribucion.repeat();
            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void finalSession() throws Exception {
        FacesContext contex = FacesContext.getCurrentInstance();
        log.info(contex.getExternalContext().getApplicationContextPath().toString());
        contex.getExternalContext().redirect("../Login.xhtml");
        contex.getExternalContext().invalidateSession();

        /*FacesContext context = FacesContext.getCurrentInstance();

        ExternalContext externalContext = context.getExternalContext();

        Object session = externalContext.getSession(false);

        HttpSession httpSession = (HttpSession) session;

        httpSession.invalidate();
         */
    }

    public void cambiocontrasena() {
        AsesoresDAO dao;
        Mensaje mensaje;

        try {
            dao = new AsesoresDAO();
            asesores.setCorreo(asesorVali.getCorreo());
            mensaje = dao.cambiopass(asesores);
            this.limpiar();
            message = mensaje;
        } catch (Exception e) {
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void abrirlistado(HttpSession session) {
        ProspectosDAO dao;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map params = facesContext.getExternalContext().getRequestParameterMap();
        String param = (String) params.get("estado");
        try {
            dao = new ProspectosDAO();
            switch (param) {
                case "Todos":
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/template/listaProspectos.xhtml");
                    break;
                case "Visitas para Hoy":
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/template/listaProspectosPH.xhtml");
                    break;
                case "Por Llamar":
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/template/listaProspectosPorLlamar.xhtml");
                    break;
                case "Ventas Cerradas":
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/template/listaProspectosVC.xhtml");
                    break;
                case "Pendientes":
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/template/listaProspectosPorContactar.xhtml");
                    break;
                default:
                    facesContext.getExternalContext().redirect(facesContext.getExternalContext().getApplicationContextPath() + "/template/asignaprospectos.xhtml");
                    break;
            }

        } catch (IOException e) {
           log.info(e.getMessage());

        } catch (Exception e) {
            log.info(e.getMessage());

        }
    }

}
