package com.quantum.bean;

import com.quantum.dao.AsesoresDAO;
import com.quantum.dao.DistribucionDAO;
import com.quantum.dao.DistritosDAO;
import com.quantum.dao.GestionDAO;
import com.quantum.dao.OrigenesDAO;
import com.quantum.dao.ProspectosDAO;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Distritos;
import com.quantum.modelos.Gestion;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Origenes;
import com.quantum.modelos.Prospectos;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
@ManagedBean
@ViewScoped
public class ProspectosBean implements Serializable {

    UsuariosBean user;
    Asesores ase;
    private org.slf4j.Logger log = LoggerFactory.getLogger(ProspectosBean.class);

    private Mensaje message = new Mensaje(false, "none !important", "");
    private formatoDeFechas fechas = new formatoDeFechas();

    private Prospectos prospectos = new Prospectos();
    private Asesores asesores = new Asesores();
    private List<Gestion> lstProspectos;
    //private List<Prospectos> lstProspectos;
    /*private List<Prospectos> lstProspectospendientes;
    private List<Prospectos> lstProspectosporasignar;
    private List<Prospectos> lstProspectosdescar;
    private List<Prospectos> lstProspectosgestion;*/
    List<Gestion> lstTest = new ArrayList<>();
    private String stateProspecto;

    private List<Gestion> lstProspectospendientes;
    private List<Prospectos> lstProspectosporasignar;
    private List<Gestion> lstProspectosdescar;
    private List<Gestion> lstProspectosgestion;
    private List<Gestion> lstresultados;

    private OrigenesDAO origenesdao;
    private List<Origenes> lstorigenes;
    private List<Distritos> lstdistritos;
    private List<Asesores> lstasesores;

    private String boton;
    private String estado;
    private String tipo;
    private String cache;
    private String restringe;
    private String asesorprospecto;

    public Prospectos getProspectos() {
        return prospectos;
    }

    public void setProspectos(Prospectos prospectos) {
        this.prospectos = prospectos;
    }

    public List<Gestion> getLstProspectos() {
        return lstProspectos;
    }

    public void setLstProspectos(List<Gestion> lstProspectos) {
        this.lstProspectos = lstProspectos;
    }

    public List<Origenes> getLstorigenes() {
        return lstorigenes;
    }

    public void setLstorigenes(List<Origenes> lstorigenes) {
        this.lstorigenes = lstorigenes;
    }

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public OrigenesDAO getOrigenesdao() {
        return origenesdao;
    }

    public void setOrigenesdao(OrigenesDAO origenesdao) {
        this.origenesdao = origenesdao;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public UsuariosBean getUser() {
        return user;
    }

    public void setUser(UsuariosBean user) {
        this.user = user;
    }

    public List<Gestion> getLstProspectospendientes() {
        return lstProspectospendientes;
    }

    public void setLstProspectospendientes(List<Gestion> lstProspectospendientes) {
        this.lstProspectospendientes = lstProspectospendientes;
    }

    public List<Prospectos> getLstProspectosporasignar() {
        return lstProspectosporasignar;
    }

    public void setLstProspectosporasignar(List<Prospectos> lstProspectosporasignar) {
        this.lstProspectosporasignar = lstProspectosporasignar;
    }

    public List<Gestion> getLstProspectosdescar() {
        return lstProspectosdescar;
    }

    public void setLstProspectosdescar(List<Gestion> lstProspectosdescar) {
        this.lstProspectosdescar = lstProspectosdescar;
    }

    public List<Gestion> getLstProspectosgestion() {
        return lstProspectosgestion;
    }

    public void setLstProspectosgestion(List<Gestion> lstProspectosgestion) {
        this.lstProspectosgestion = lstProspectosgestion;
    }

    public List<Distritos> getLstdistritos() {
        return lstdistritos;
    }

    public void setLstdistritos(List<Distritos> lstdistritos) {
        this.lstdistritos = lstdistritos;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public Asesores getAse() {
        return ase;
    }

    public void setAse(Asesores ase) {
        this.ase = ase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRestringe() {
        return restringe;
    }

    public void setRestringe(String restringe) {
        this.restringe = restringe;
    }

    public List<Gestion> getLstTest() {
        return lstTest;
    }

    public void setLstTest(List<Gestion> lstTest) {
        this.lstTest = lstTest;
    }

    public String getStateProspecto() {
        return stateProspecto;
    }

    public void setStateProspecto(String stateProspecto) {
        this.stateProspecto = stateProspecto;
    }

    public String getAsesorprospecto() {
        return asesorprospecto;
    }

    public void setAsesorprospecto(String asesorprospecto) {
        this.asesorprospecto = asesorprospecto;
    }

    public Asesores getAsesores() {
        return asesores;
    }

    public void setAsesores(Asesores asesores) {
        this.asesores = asesores;
    }

    public List<Asesores> getLstasesores() {
        return lstasesores;
    }

    public void setLstasesores(List<Asesores> lstasesores) {
        this.lstasesores = lstasesores;
    }

    public List<Gestion> getLstresultados() {
        return lstresultados;
    }

    public void setLstresultados(List<Gestion> lstresultados) {
        this.lstresultados = lstresultados;
    }

    public void limpiar() {
        prospectos.setCargo("");
        prospectos.setCodigo("");
        prospectos.setCorreo("");
        prospectos.setFecha_ingreso("");
        prospectos.setFecha_prospecto("");
        prospectos.setLink("");
        prospectos.setNombreapellido("");
        prospectos.setOrigen("");
        prospectos.setTelefono1("");
        prospectos.setTelefono2("");
        prospectos.setFecharetiro("");
        asesores = null;
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

    public void registrar() throws Exception {
        ProspectosDAO dao;
        DistribucionDAO daodistribucion;
        Mensaje mensaje;
        Mensaje mensajeasignacion;

        try {
            daodistribucion = new DistribucionDAO();
            dao = new ProspectosDAO();
            mensaje = dao.validaprospecto(prospectos);

            if (mensaje == null) {
                mensaje = dao.registrar(prospectos);
                if (asesores.getCorreo() != null) {
                    mensajeasignacion = daodistribucion.distribuirdeprospecto(prospectos, asesores);
                    if (mensajeasignacion.getClase().equals("danger")) {
                        message = mensajeasignacion;
                    }
                }
            } else {
                message = mensaje;
            }
            message = mensaje;
            this.limpiar();

        } catch (Exception e) {
            log.info(e.getMessage());
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void listar(HttpSession session) throws Exception {

        GestionDAO dao;
        ProspectosDAO daoasigna;
        OrigenesDAO daori;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new GestionDAO();
                daori = new OrigenesDAO();
                daoasigna = new ProspectosDAO();
                //        lstProspectos = dao.listar(usuariosBean.getAsesorVali());
                if (session.getAttribute("stateprospecto") != null) {
                    estado = session.getAttribute("stateprospecto").toString();
                    session.removeAttribute("stateprospecto");
                } else {
                    estado = "todos";
                }

                lstProspectos = dao.listar(usuariosBean.getAsesorVali());
                /*lstProspectosdescar = dao.listardescartados(usuariosBean.getAsesorVali());
                lstProspectospendientes = dao.listarpendientes(usuariosBean.getAsesorVali());
                lstProspectosporasignar = daoasigna.listarporasignar();
                lstProspectosgestion = dao.listargestion(usuariosBean.getAsesorVali());
                lstorigenes = daori.listar();*/

            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void listarPPC(HttpSession session) throws Exception {

        GestionDAO dao;

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new GestionDAO();
                //        lstProspectos = dao.listar(usuariosBean.getAsesorVali());
                if (session.getAttribute("stateprospecto") != null) {
                    estado = session.getAttribute("stateprospecto").toString();
                    session.removeAttribute("stateprospecto");
                } else {
                    estado = "todos";
                }

                lstProspectospendientes = dao.listarpendientes(usuariosBean.getAsesorVali());

            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void listarVentascaidas(HttpSession session) throws Exception {

        GestionDAO dao;

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new GestionDAO();
                if (session.getAttribute("stateprospecto") != null) {
                    estado = session.getAttribute("stateprospecto").toString();
                    session.removeAttribute("stateprospecto");
                } else {
                    estado = "todos";
                }

                lstProspectosdescar = dao.listardescartados(usuariosBean.getAsesorVali());

            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void listarporAsignar(HttpSession session) throws Exception {

        ProspectosDAO daoasigna;

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                daoasigna = new ProspectosDAO();

                if (session.getAttribute("stateprospecto") != null) {
                    estado = session.getAttribute("stateprospecto").toString();
                    session.removeAttribute("stateprospecto");
                } else {
                    estado = "todos";
                }
                lstProspectosporasignar = daoasigna.listarporasignar();
            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void listarporAsignar2(HttpSession session) throws Exception {

        GestionDAO daoasigna;

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                daoasigna = new GestionDAO();

                if (session.getAttribute("stateprospecto") != null) {
                    estado = session.getAttribute("stateprospecto").toString();
                    session.removeAttribute("stateprospecto");
                } else {
                    estado = "todos";
                }
                lstresultados = daoasigna.lstvh2();
            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void listargestionados(HttpSession session) throws Exception {

        GestionDAO dao;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new GestionDAO();
                //        lstProspectos = dao.listar(usuariosBean.getAsesorVali());
                if (session.getAttribute("stateprospecto") != null) {
                    estado = session.getAttribute("stateprospecto").toString();
                    session.removeAttribute("stateprospecto");
                } else {
                    estado = "todos";
                }

                lstProspectosgestion = dao.listargestion(usuariosBean.getAsesorVali());

            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }
        } else {
            this.finalSession();
        }

    }

    public void listardeAsesor(HttpSession session) throws Exception {
        /*ProspectosDAO dao;
        Asesores asesor;

        if (session.getAttribute("prospectodeasesor") != null) {
            asesor = (Asesores) session.getAttribute("prospectodeasesor");
            ase = asesor;
            session.removeAttribute("prospectodeasesor");
            try {
                dao = new ProspectosDAO();
                lstProspectos = dao.listarprospectosAsesor(asesor);
            } catch (Exception e) {
                throw e;
            }

        }*/
        GestionDAO dao;
        Asesores asesor;

        if (session.getAttribute("prospectodeasesor") != null) {
            asesor = (Asesores) session.getAttribute("prospectodeasesor");
            ase = asesor;
            session.removeAttribute("prospectodeasesor");
            try {
                dao = new GestionDAO();
                lstProspectos = dao.listarprospectosAsesor(asesor);
            } catch (Exception e) {
                log.info(e.getMessage());
                throw e;
            }

        }
    }

    public void listardeInicioPH(HttpSession session) {
        GestionDAO dao;
        dao = new GestionDAO();

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = (UsuariosBean) objeto;

        try {

            lstProspectos = dao.lstvh(usuariosBean.getAsesorVali());
            estado = "Visitas para Hoy";
            session.removeAttribute("estado");

        } catch (Exception ex) {
            log.info(ex.getMessage());
            Logger.getLogger(ProspectosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listardeInicioPendiente(HttpSession session) {
        GestionDAO dao;
        dao = new GestionDAO();

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = (UsuariosBean) objeto;
        System.out.println(usuariosBean.getAsesorVali());

        try {

            lstProspectos = dao.listarpendientes(usuariosBean.getAsesorVali());
            estado = "Pendientes";
            session.removeAttribute("estado");

        } catch (Exception ex) {
            log.info(ex.getMessage());
            Logger.getLogger(ProspectosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listardeInicioPC(HttpSession session) {
        GestionDAO dao;
        dao = new GestionDAO();

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = (UsuariosBean) objeto;
        System.out.println(usuariosBean.getAsesorVali());

        try {

            lstProspectos = dao.lstllamar(usuariosBean.getAsesorVali());
            estado = "Por Llamar";
            session.removeAttribute("estado");

        } catch (Exception ex) {
            log.info(ex.getMessage());
            Logger.getLogger(ProspectosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listardeInicioVC(HttpSession session) {
        GestionDAO dao;
        dao = new GestionDAO();

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = (UsuariosBean) objeto;

        try {

            lstProspectos = dao.lstvc(usuariosBean.getAsesorVali());
            estado = "Ventas Cerradas";
            session.removeAttribute("estado");

        } catch (Exception ex) {
            log.info(ex.getMessage());
            Logger.getLogger(ProspectosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listardeInicioRepeat(HttpSession session) {
        GestionDAO dao;
        dao = new GestionDAO();

        try {

            lstProspectos = dao.lstrepeat();
            estado = "Repetidos";
            session.removeAttribute("estado");

        } catch (Exception ex) {
            log.info(ex.getMessage());
            Logger.getLogger(ProspectosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listarmisProspectos() throws Exception {
        /*ProspectosDAO dao;
        OrigenesDAO daori;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new ProspectosDAO();
                daori = new OrigenesDAO();
                lstProspectos = dao.listarmisprospectos(usuariosBean.getAsesorVali().getCorreo());
                lstorigenes = daori.listar();
            } catch (Exception e) {
                throw e;
            }
        } else {
            this.finalSession();
        }*/
        GestionDAO dao;
        OrigenesDAO daori;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new GestionDAO();
                daori = new OrigenesDAO();
                lstProspectos = dao.listarmisprospectos(usuariosBean.getAsesorVali().getCorreo());
                lstorigenes = daori.listar();
            } catch (Exception e) {
                log.info(e.getMessage());

                throw e;
            }
        } else {
            this.finalSession();
        }
    }

    public void leerID(Gestion prospecto, HttpSession session, String origen) throws Exception, IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        try {
            switch (origen) {

                case "todos":
                    session.setAttribute("lstcaptura", lstProspectos);
                    session.setAttribute("viene", "listProspectos");
                    break;
                case "descartados":
                    session.setAttribute("lstcaptura", lstProspectosdescar);
                    session.setAttribute("viene", "listaProsVentaCaida");
                    break;
                case "pendientes":
                    session.setAttribute("lstcaptura", lstProspectospendientes);
                    session.setAttribute("viene", "listaProsPPC");
                    break;
                case "gestion":
                    session.setAttribute("lstcaptura", lstProspectosgestion);
                    session.setAttribute("viene", "listaProsGestion");
                    break;
                case "prospectoAsesor":
                    session.setAttribute("prospectodeasesor", ase);
                    session.setAttribute("lstcaptura", lstProspectos);
                    session.setAttribute("viene", "listProspectosAsesor");
                    session.setAttribute("estado", this.estado);
                case "buscaprospectos":
                    session.setAttribute("viene", "buscaprospectos");
                default:
                    break;
            }
            session.setAttribute("stateprospecto", origen);
            session.setAttribute("prospectoparam", prospecto);
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/prospectos.xhtml");
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }

    }

    public void leerIdtoGestion(Gestion prospecto, HttpSession session, String origen) throws Exception, IOException {

        FacesContext contex = FacesContext.getCurrentInstance();
        try {
            switch (origen) {
                case "todos":
                    session.setAttribute("lstcaptura", lstProspectos);
                    session.setAttribute("viene", "listProspectos");
                    break;
                case "descartados":
                    session.setAttribute("lstcaptura", lstProspectosdescar);
                    session.setAttribute("viene", "listaProsVentaCaida");
                    break;
                case "pendientes":
                    session.setAttribute("lstcaptura", lstProspectospendientes);
                    session.setAttribute("viene", "listaProsPPC");
                    break;
                case "gestion":
                    session.setAttribute("lstcaptura", lstProspectosgestion);
                    session.setAttribute("viene", "listaProsGestion");
                    break;
                default:
                    break;
            }
            session.setAttribute("stateprospecto", origen);
            //session.setAttribute("gestionparam", dao.consultagestion(prospecto.getCodigoGestion()));
            session.setAttribute("gestionparam", prospecto);

            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/gestion.xhtml");
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }

    }

    public void leerIdtoGestionAsesor(Gestion prospecto, HttpSession session, String estado) throws Exception, IOException {
        GestionDAO dao;
        FacesContext contex = FacesContext.getCurrentInstance();

        try {
            dao = new GestionDAO();
            session.setAttribute("prospectodeasesor", ase);
            session.setAttribute("lstcaptura", lstProspectos);
            session.setAttribute("estado", this.estado);
            session.setAttribute("gestionparam", prospecto);
            switch (this.estado) {
                case "Pendientes":
                    session.setAttribute("viene", "listProspectosPorContactar");
                    break;
                case "Ventas Cerradas":
                    session.setAttribute("viene", "listProspectosVC");
                    break;
                case "Por Llamar":
                    session.setAttribute("viene", "listProspectosPorLlamar");
                    break;
                default:
                    session.setAttribute("viene", "listProspectosPH");
                    break;
            }

            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/gestion.xhtml");
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }

    }

    public void leerIdtoGestionAsesor(Gestion prospecto, HttpSession session) throws Exception, IOException {
        FacesContext contex = FacesContext.getCurrentInstance();

        try {
            session.setAttribute("prospectodeasesor", ase);

            session.setAttribute("lstcaptura", lstProspectos);
            session.setAttribute("estado", this.estado);
            session.setAttribute("gestionparam", prospecto);
            session.setAttribute("viene", "listProspectosAsesor");

            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/gestion.xhtml");
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }

    }

    public void verprospectos(Asesores asesor, HttpSession session) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("prospectodeasesor", asesor);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectosAsesor.xhtml");
    }

    public void modificar() throws Exception {
        ProspectosDAO dao;
        Mensaje mensaje;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        if (objeto != null) {
            usuariosBean = (UsuariosBean) objeto;
            try {
                dao = new ProspectosDAO();
                mensaje = dao.modificar(prospectos, usuariosBean.getAsesorVali().getRol());
                this.limpiar();
                message = mensaje;
                contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectos.xhtml");
            } catch (Exception e) {
                log.info(e.getMessage());

                message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            }
        } else {
            this.finalSession();
        }

    }

    public void borrar(Prospectos prospecto, HttpSession session) throws Exception {
        try {
            ProspectosDAO dao;

            prospecto.setFecharetiro(fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA));
            dao = new ProspectosDAO();
            dao.borrar(prospecto);
            this.listar(session);
        } catch (Exception e) {
            log.info(e.getMessage());

            throw e;
        }
    }

    public void capturarprospectos(HttpSession session) {
        OrigenesDAO daori;
        ProspectosDAO dao;
        DistritosDAO daodis;
        AsesoresDAO daoase;

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;

        try {
            if (objeto != null) {
                usuariosBean = (UsuariosBean) objeto;
                daori = new OrigenesDAO();
                dao = new ProspectosDAO();
                daodis = new DistritosDAO();
                daoase = new AsesoresDAO();

                lstorigenes = daori.listar();
                lstdistritos = daodis.listar();
                lstasesores = daoase.listarparaasignar(usuariosBean.getAsesorVali());

                if (session.getAttribute("prospectoparam") != null) {

                    lstTest = (List<Gestion>) session.getAttribute("lstcaptura");

                    if (session.getAttribute("stateprospecto") != null) {
                        this.stateProspecto = session.getAttribute("stateprospecto").toString();
                    }

                    Gestion prospecto = (Gestion) session.getAttribute("prospectoparam");
                    asesorprospecto = prospecto.getCod_asesor();
                    prospectos = dao.prospecto(prospecto.getCod_prospecto());
                    session.removeAttribute("prospectoparam");
                    boton = "Actualizar";
                } else {
                    prospectos = new Prospectos();
                    boton = "Agregar";
                }
            } else {
                this.finalSession();
            }
        } catch (Exception e) {
            log.info(e.getMessage());

        }

    }

    public void finalSession() throws Exception {
        FacesContext contex = FacesContext.getCurrentInstance();
        contex.getExternalContext().invalidateSession();
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath());
    }

    public void retornalista(HttpSession session) {
        Asesores asesor;
        AsesoresDAO dao;
        String origen;
        try {
            dao = new AsesoresDAO();
            FacesContext contex = FacesContext.getCurrentInstance();
            asesor = dao.buscasesor(asesorprospecto);
            session.setAttribute("prospectodeasesor", asesor);
            origen = session.getAttribute("viene").toString();
            switch (origen) {
                case "listProspectos":
                    session.setAttribute("stateprospecto", this.stateProspecto);
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectos.xhtml");
                    break;
                case "listaProsVentaCaida":
                    session.setAttribute("stateprospecto", this.stateProspecto);
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProsVentaCaida.xhtml");
                    break;
                case "listaProsPPC":
                    session.setAttribute("stateprospecto", this.stateProspecto);
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProsPPC.xhtml");
                    break;
                case "listaProsGestion":
                    session.setAttribute("stateprospecto", this.stateProspecto);
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProsGestion.xhtml");
                    break;
                case "listProspectosPorContactar":
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectosPorContactar.xhtml");
                    break;
                case "listProspectosVC":
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectosVC.xhtml");
                    break;
                case "listProspectosPorLlamar":
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectosPorLlamar.xhtml");
                    break;
                case "listProspectosIni":
                    session.setAttribute("estado", session.getAttribute("estado"));
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectosIni.xhtml");
                    break;
                case "listProspectosAsesor":
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectosAsesor.xhtml");
                    break;
                case "subeprospectos":
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/subeprospectos.xhtml");
                    break;
                case "buscaprospectos":
                    contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/buscaprospectos.xhtml");
                    break;
                default:
                    break;
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void buscaprospecto(ValueChangeEvent event) throws Exception, IOException {
        String opcion = event.getNewValue().toString();
        lstresultados = null;
        if (prospectos.getCorreo() == null) {
            prospectos.setCorreo("");
        }

        if (prospectos.getTelefono1() == null) {
            prospectos.setTelefono1("");
        }

        prospectos.setNombreapellido(opcion);
        GestionDAO dao;
        System.out.println(opcion);
        if (!opcion.equals("")) {
            try {
                dao = new GestionDAO();
                lstresultados = dao.buscapropspecto(prospectos);

            } catch (Exception ex) {
                log.info(ex.getMessage());
            
                throw ex;
            }
        }

    }

    public void buscaprospectomail(ValueChangeEvent event) throws Exception, IOException {
        String opcion = event.getNewValue().toString();
        lstresultados = null;
        if (prospectos.getNombreapellido() == null) {
            prospectos.setNombreapellido("");
        }

        if (prospectos.getTelefono1() == null) {
            prospectos.setTelefono1("");
        }

        prospectos.setCorreo(opcion);
        GestionDAO dao;

        if (!opcion.equals("")) {
            try {
                dao = new GestionDAO();
                lstresultados = dao.buscapropspecto(prospectos);

            } catch (Exception ex) {
                log.info(ex.getMessage());
            
                throw ex;
            }
        }
    }

    public void buscaprospectophone(ValueChangeEvent event) throws Exception, IOException {
        String opcion = event.getNewValue().toString();
        lstresultados = null;
        if (prospectos.getNombreapellido() == null) {
            prospectos.setNombreapellido("");
        }

        if (prospectos.getCorreo() == null) {
            prospectos.setCorreo("");
        }

        prospectos.setTelefono1(opcion);
        GestionDAO dao;
        if (!opcion.equals("")) {
            try {
                dao = new GestionDAO();
                lstresultados = dao.buscapropspecto(prospectos);

            } catch (Exception ex) {
                log.info(ex.getMessage());
            
                throw ex;
            }
        }

    }

}
