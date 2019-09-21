package com.quantum.bean;

import com.quantum.dao.AnunciosDAO;
import com.quantum.dao.AsesoresDAO;
import com.quantum.dao.CampanasDAO;
import com.quantum.dao.ConjuntoDAO;
import com.quantum.dao.EstadosDAO;
import com.quantum.dao.GestionDAO;
import com.quantum.dao.LotesDAO;
import com.quantum.dao.MotivosDAO;
import com.quantum.dao.OrigenesDAO;
import com.quantum.dao.ParcelasDAO;
import com.quantum.modelos.Anuncios;
import com.quantum.modelos.Asesores;
import com.quantum.modelos.Campanas;
import com.quantum.modelos.Casas;
import com.quantum.modelos.Conjunto;
import com.quantum.modelos.Estado;
import com.quantum.modelos.Estatusventas;
import com.quantum.modelos.Gestion;
import com.quantum.modelos.Lotes;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Motivos;
import com.quantum.modelos.Objeciones;
import com.quantum.modelos.Origenes;
import com.quantum.modelos.Parcelas;
import com.quantum.modelos.Procesosestatus;
import com.quantum.modelos.TipObjeciones;
import com.quantum.servicios.formatoDeFechas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author QUANTUM
 */
@Named("gestionBean")
@ViewScoped
public class GestionBean implements Serializable {

    private Mensaje message = new Mensaje(false, "none !important", "");
    private formatoDeFechas fechas = new formatoDeFechas();
    private boolean retorno = false;
    private boolean muestravll = false;
    private boolean muestramotivos = false;
    private boolean muestravofi = false;
    private boolean muestravproyec = false;
    private boolean muestralote = false;
    private boolean muestrafpago = false;
    private boolean muestracasa = false;
    private String fpago = "none";
    private Gestion gestion = new Gestion();
    private Estado estado;
    private Motivos motivo;
    private Conjunto conjunto;
    private Campanas campana;
    private Anuncios anuncio;
    private String tooltipo;
    private String toolanuncio;
    private String toolcampana;
    private String boton;
    private String stateProspecto;
    //private String showhide = "none";

    private List<Estatusventas> lstestatus;
    private List<Procesosestatus> lstproceso;

    private List<Gestion> lstpendiente;

    private List<Estado> lstestados;
    private List<Motivos> lstmotivos;

    private List<Objeciones> lstobjeciones;
    private List<TipObjeciones> lsttipobjeciones;
    private List<Origenes> lstorigenes;
    private List<Parcelas> lstparcelas;
    private List<Lotes> lstlotes;
    private List<Casas> lstcasas;

    List<Gestion> lstTest = new ArrayList<>();

    public List<Gestion> getLstTest() {
        return lstTest;
    }

    public void setLstTest(List<Gestion> lstTest) {
        this.lstTest = lstTest;
    }

    public String getFpago() {
        return fpago;
    }

    public void setFpago(String fpago) {
        this.fpago = fpago;
    }

    public Gestion getGestion() {
        return gestion;
    }

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    public String getBoton() {
        return boton;
    }

    public void setBoton(String boton) {
        this.boton = boton;
    }

    public List<Estatusventas> getLstestatus() {
        return lstestatus;
    }

    public void setLstEstatus(List<Estatusventas> lstestatus) {
        this.lstestatus = lstestatus;
    }

    public Mensaje getMessage() {
        return message;
    }

    public void setMessage(Mensaje message) {
        this.message = message;
    }

    public List<Procesosestatus> getLstproceso() {
        return lstproceso;
    }

    public void setLstproceso(List<Procesosestatus> lstproceso) {
        this.lstproceso = lstproceso;
    }

    public List<Objeciones> getLstobjeciones() {
        return lstobjeciones;
    }

    public void setLstobjeciones(List<Objeciones> lstobjeciones) {
        this.lstobjeciones = lstobjeciones;
    }

    public List<TipObjeciones> getLsttipobjeciones() {
        return lsttipobjeciones;
    }

    public void setLsttipobjeciones(List<TipObjeciones> lsttipobjeciones) {
        this.lsttipobjeciones = lsttipobjeciones;
    }

    public List<Estado> getLstestados() {
        return lstestados;
    }

    public void setLstestados(List<Estado> lstestados) {
        this.lstestados = lstestados;
    }

    public List<Motivos> getLstmotivos() {
        return lstmotivos;
    }

    public void setLstmotivos(List<Motivos> lstmotivos) {
        this.lstmotivos = lstmotivos;
    }

    public Motivos getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivos motivo) {
        this.motivo = motivo;
    }

    public List<Gestion> getLstpendiente() {
        return lstpendiente;
    }

    public void setLstpendiente(List<Gestion> lstpendiente) {
        this.lstpendiente = lstpendiente;
    }

    public List<Origenes> getLstorigenes() {
        return lstorigenes;
    }

    public void setLstorigenes(List<Origenes> lstorigenes) {
        this.lstorigenes = lstorigenes;
    }

    public boolean isMuestralote() {
        return muestralote;
    }

    public void setMuestralote(boolean muestralote) {
        this.muestralote = muestralote;
    }

    public boolean isMuestrafpago() {
        return muestrafpago;
    }

    public void setMuestrafpago(boolean muestrafpago) {
        this.muestrafpago = muestrafpago;
    }

    public boolean isMuestravll() {
        return muestravll;
    }

    public void setMuestravll(boolean muestravll) {
        this.muestravll = muestravll;
    }

    public boolean isMuestramotivos() {
        return muestramotivos;
    }

    public void setMuestramotivos(boolean muestramotivos) {
        this.muestramotivos = muestramotivos;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean isMuestravofi() {
        return muestravofi;
    }

    public void setMuestravofi(boolean muestravofi) {
        this.muestravofi = muestravofi;
    }

    public boolean isMuestravproyec() {
        return muestravproyec;
    }

    public void setMuestravproyec(boolean muestravproyec) {
        this.muestravproyec = muestravproyec;
    }

    public List<Parcelas> getLstparcelas() {
        return lstparcelas;
    }

    public void setLstparcelas(List<Parcelas> lstparcelas) {
        this.lstparcelas = lstparcelas;
    }

    public List<Lotes> getLstlotes() {
        return lstlotes;
    }

    public void setLstlotes(List<Lotes> lstlotes) {
        this.lstlotes = lstlotes;
    }

    public boolean isMuestracasa() {
        return muestracasa;
    }

    public void setMuestracasa(boolean muestracasa) {
        this.muestracasa = muestracasa;
    }

    public List<Casas> getLstcasas() {
        return lstcasas;
    }

    public void setLstcasas(List<Casas> lstcasas) {
        this.lstcasas = lstcasas;
    }

    private boolean isPostBack() {
        boolean rpta;
        rpta = FacesContext.getCurrentInstance().isPostback();
        return rpta;

    }

    public boolean isRetorno() {
        return retorno;
    }

    public void setRetorno(boolean retorno) {
        this.retorno = retorno;
    }

    public String getStateProspecto() {
        return stateProspecto;
    }

    public void setStateProspecto(String stateProspecto) {
        this.stateProspecto = stateProspecto;
    }

    public Conjunto getConjunto() {
        return conjunto;
    }

    public void setConjunto(Conjunto conjunto) {
        this.conjunto = conjunto;
    }

    public String getTooltipo() {
        return tooltipo;
    }

    public void setTooltipo(String tooltipo) {
        this.tooltipo = tooltipo;
    }

    public String getToolanuncio() {
        return toolanuncio;
    }

    public void setToolanuncio(String toolanuncio) {
        this.toolanuncio = toolanuncio;
    }

    public Anuncios getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncios anuncio) {
        this.anuncio = anuncio;
    }

    public Campanas getCampana() {
        return campana;
    }

    public void setCampana(Campanas campana) {
        this.campana = campana;
    }

    public String getToolcampana() {
        return toolcampana;
    }

    public void setToolcampana(String toolcampana) {
        this.toolcampana = toolcampana;
    }

    public void registrar() throws Exception {
        GestionDAO dao;
        Mensaje mensaje;
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        UsuariosBean usuariosBean = null;
        usuariosBean = (UsuariosBean) objeto;

        try {
            dao = new GestionDAO();
            mensaje = dao.modificar(gestion);

            if (lstpendiente != null) {
                for (Gestion nodolista : lstpendiente) {
                    if (nodolista.getCodigo().equals(gestion.getCodigo())) {
                        lstpendiente.remove(nodolista);
                        usuariosBean.setLstpendientes(lstpendiente);
                    }
                }
            }
            //this.limpiar();
            message = mensaje;
        } catch (Exception e) {
            System.out.println("aqui " + e.getLocalizedMessage());
            throw e;
        }
    }

    public void limpiar() {
        gestion.setEstado("");
        gestion.setMotivo("");
        gestion.setComentario("");
        gestion.setFec_visita_proyec("");
        gestion.setFec_visita_ofic("");
        gestion.setFec_contrato("");
        muestravll = false;
        muestramotivos = false;
        muestravofi = false;
        muestravproyec = false;
        this.estado = null;
        this.motivo = null;
    }

    public void operar() {
        try {
            this.registrar();
            //this.limpiarAjx();
            //this.pedirnuevo();

        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void next(AjaxBehaviorEvent e) {
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        int numerolista = 0;
        UsuariosBean usuariosBean = (UsuariosBean) objeto;
        String genero;
        ConjuntoDAO daoconjun;
        AnunciosDAO daoanuncio;
        CampanasDAO daocampa;
        // lstpendiente = usuariosBean.getLstpendientes();

        /*if (lstpendiente.indexOf(gestion) + 1 == lstpendiente.size()) {

        } else {
            numerolista = lstpendiente.indexOf(gestion) + 1;
            if (numerolista <= lstpendiente.size()) {
                gestion = lstpendiente.get(numerolista);
            }
            try {
                this.buscamotivos(gestion.getEstado());
            } catch (Exception ex) {
                Logger.getLogger(GestionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        try {
            daoconjun = new ConjuntoDAO();
            daoanuncio = new AnunciosDAO();
            daocampa = new CampanasDAO();

            if (lstTest.indexOf(gestion) + 1 == lstTest.size()) {

            } else {
                numerolista = lstTest.indexOf(gestion) + 1;
                if (numerolista <= lstTest.size()) {
                    gestion = lstTest.get(numerolista);
                    campana = daocampa.campanadeprospecto(gestion.getCod_prospecto());

                    toolcampana = "<span  align='left' style='font-size: 12px;' >\n"
                            + "<strong>Nombre: </strong>" + campana.getNombre() + "<br/>\n"
                            + "<strong>Descripción: </strong>" + campana.getDescripcion() + "<br/>\n"
                            + "</span>";

                    conjunto = daoconjun.conjuntodeprospecto(gestion.getCod_prospecto());

                    if (conjunto.getMasculino() == null && conjunto.getFemenino() == null) {
                        genero = "No definido";
                    } else {
                        if (conjunto.getMasculino().equals("true") && conjunto.getMasculino().equals("true")) {
                            genero = "Masculino y Femenino";
                        } else if (conjunto.getMasculino().equals("true") && conjunto.getMasculino().equals("false")) {
                            genero = "Masculino";
                        } else if (conjunto.getMasculino().equals("false") && conjunto.getMasculino().equals("true")) {
                            genero = "Femenino";
                        } else {
                            genero = "No definido";
                        }
                    }

                    toolanuncio = "<span  align='left' style='font-size: 12px;' ><p style='text-align:left;'>\n"
                            + "<strong>Conjunto Anuncios: </strong>" + conjunto.getDescripcion() + "<br/>\n"
                            + "<strong>Sexo: </strong>" + genero + "<br/>\n"
                            + "<strong>Edad: </strong>" + conjunto.getEdadini() + " a " + conjunto.getEdadfin() + "<br/>\n"
                            + "<strong>Bases de Datos: </strong>" + conjunto.getBd() + "<br/>\n"
                            + "<strong>Zona Geográfica: </strong>" + conjunto.getZngeo() + "<br/>\n"
                            + "</p></span>";

                    anuncio = daoanuncio.anunciodeprospecto(gestion.getCod_prospecto());
                    tooltipo = "<img src='" + anuncio.getUrlimg() + "' style='width:100%' />";
                }

                this.buscamotivos(gestion.getCodestado());
                this.muestraelementos(gestion);

            }
        } catch (Exception ex) {
            Logger.getLogger(GestionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void back(AjaxBehaviorEvent e) {

        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        int numerolista = 0;
        UsuariosBean usuariosBean = (UsuariosBean) objeto;
        String genero;
        ConjuntoDAO daoconjun;
        AnunciosDAO daoanuncio;
        CampanasDAO daocampa;
        try {
            
            daoconjun = new ConjuntoDAO();
            daoanuncio = new AnunciosDAO();
            daocampa = new CampanasDAO();
            if (lstTest.indexOf(gestion) == 0) {
                
            } else {
                numerolista = lstTest.indexOf(gestion) - 1;
                if (numerolista >= 0) {
                    gestion = lstTest.get(numerolista);
                    campana = daocampa.campanadeprospecto(gestion.getCod_prospecto());

                    toolcampana = "<span  align='left' style='font-size: 12px;' >\n"
                            + "<strong>Nombre: </strong>" + campana.getNombre() + "<br/>\n"
                            + "<strong>Descripción: </strong>" + campana.getDescripcion() + "<br/>\n"
                            + "</span>";

                    conjunto = daoconjun.conjuntodeprospecto(gestion.getCod_prospecto());

                    if (conjunto.getMasculino() == null && conjunto.getFemenino() == null) {
                        genero = "No definido";
                    } else {
                        if (conjunto.getMasculino().equals("true") && conjunto.getMasculino().equals("true")) {
                            genero = "Masculino y Femenino";
                        } else if (conjunto.getMasculino().equals("true") && conjunto.getMasculino().equals("false")) {
                            genero = "Masculino";
                        } else if (conjunto.getMasculino().equals("false") && conjunto.getMasculino().equals("true")) {
                            genero = "Femenino";
                        } else {
                            genero = "No definido";
                        }
                    }

                    toolanuncio = "<span  align='left' style='font-size: 12px;' ><p style='text-align:left;'>\n"
                            + "<strong>Conjunto Anuncios: </strong>" + conjunto.getDescripcion() + "<br/>\n"
                            + "<strong>Sexo: </strong>" + genero + "<br/>\n"
                            + "<strong>Edad: </strong>" + conjunto.getEdadini() + " a " + conjunto.getEdadfin() + "<br/>\n"
                            + "<strong>Bases de Datos: </strong>" + conjunto.getBd() + "<br/>\n"
                            + "<strong>Zona Geográfica: </strong>" + conjunto.getZngeo() + "<br/>\n"
                            + "</p></span>";

                    anuncio = daoanuncio.anunciodeprospecto(gestion.getCod_prospecto());
                    tooltipo = "<img src='" + anuncio.getUrlimg() + "' />";
                }

                this.buscamotivos(gestion.getCodestado());
                this.muestraelementos(gestion);

            }
        } catch (Exception ex) {
            Logger.getLogger(GestionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* if (lstpendiente.indexOf(gestion) == 0) {
            System.out.println(lstpendiente.indexOf(gestion));
        } else {
            numerolista = lstpendiente.indexOf(gestion) - 1;
            if (numerolista >= 0) {
                gestion = lstpendiente.get(numerolista);
            }
            try {
                this.buscamotivos(gestion.getEstado());
            } catch (Exception ex) {
                Logger.getLogger(GestionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    }

    public void capturargestion(HttpSession session) {
        /*
        Asesores retornar = (Asesores) session.getAttribute("prospectodeasesor");
        
        if (retornar != null) {
            retorno = true;
        } else {
            retorno = false;
        }
         */
        lstTest = (List<Gestion>) session.getAttribute("lstcaptura");
        String genero;

        if (session.getAttribute("stateprospecto") != null) {
            this.stateProspecto = session.getAttribute("stateprospecto").toString();
        }
        /*gestion = (Gestion) session.getAttribute("gestionparam");
        
        
        System.out.println(gestion);
        session.removeAttribute("gestionparam");*/
        EstadosDAO dao;
        MotivosDAO daomotivos;
        OrigenesDAO daoorigenes;
        ConjuntoDAO daoconjun;
        AnunciosDAO daoanuncio;
        CampanasDAO daocampa;
        /*this.stateProspecto = session.getAttribute("estado").toString();
        System.out.println(stateProspecto);
         */
        try {

            dao = new EstadosDAO();
            daomotivos = new MotivosDAO();
            daoorigenes = new OrigenesDAO();
            daoconjun = new ConjuntoDAO();
            daoanuncio = new AnunciosDAO();
            daocampa = new CampanasDAO();

            lstorigenes = daoorigenes.listar();
            if (isPostBack() == false) {
                if (session.getAttribute("gestionparam") != null) {
                    gestion = (Gestion) session.getAttribute("gestionparam");
                    session.removeAttribute("gestionparam");
                    boton = "Actualizar";
                    campana = daocampa.campanadeprospecto(gestion.getCod_prospecto());

                    toolcampana = "<span  align='left' style='font-size: 12px;' >\n"
                            + "<strong>Nombre: </strong>" + campana.getNombre() + "<br/>\n"
                            + "<strong>Descripción: </strong>" + campana.getDescripcion() + "<br/>\n"
                            + "</span>";

                    conjunto = daoconjun.conjuntodeprospecto(gestion.getCod_prospecto());

                    if (conjunto.getMasculino() == null && conjunto.getFemenino() == null) {
                        genero = "No definido";
                    } else {
                        if (conjunto.getMasculino().equals("true") && conjunto.getMasculino().equals("true")) {
                            genero = "Masculino y Femenino";
                        } else if (conjunto.getMasculino().equals("true") && conjunto.getMasculino().equals("false")) {
                            genero = "Masculino";
                        } else if (conjunto.getMasculino().equals("false") && conjunto.getMasculino().equals("true")) {
                            genero = "Femenino";
                        } else {
                            genero = "No definido";
                        }
                    }

                    toolanuncio = "<span  align='left' style='font-size: 12px;' ><p style='text-align:left;'>\n"
                            + "<strong>Conjunto Anuncios: </strong>" + conjunto.getDescripcion() + "<br/>\n"
                            + "<strong>Sexo: </strong>" + genero + "<br/>\n"
                            + "<strong>Edad: </strong>" + conjunto.getEdadini() + " a " + conjunto.getEdadfin() + "<br/>\n"
                            + "<strong>Bases de Datos: </strong>" + conjunto.getBd() + "<br/>\n"
                            + "<strong>Adicionales: </strong>" + conjunto.getAdicional()+ "<br/>\n"
                            + "<strong>Zona Geográfica: </strong>" + conjunto.getZngeo() + "<br/>\n"
                            + "</p></span>";

                    anuncio = daoanuncio.anunciodeprospecto(gestion.getCod_prospecto());
                    tooltipo = "<img src='" + anuncio.getUrlimg() + "' />";
                    lstestados = dao.listar();
                    lstmotivos = daomotivos.procesosEstados(gestion.getCodestado());
                    this.muestraelementos(gestion);

                } else {
                    this.pedirnuevo();
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(AsesoresBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void muestraelementos(Gestion gestion) {

        MotivosDAO daomotivos;
        ParcelasDAO daoparcelas;
        LotesDAO daolotes;

        try {

            daomotivos = new MotivosDAO();
            daolotes = new LotesDAO();

            if (gestion.getEstado().equals("VC") || gestion.getEstatus_venta().equals("VC")) {
                muestralote = true;
                daoparcelas = new ParcelasDAO();
                lstparcelas = daoparcelas.listar();
                daolotes = new LotesDAO();
                lstlotes = daolotes.lotesporparcelas(gestion.getParcela());
                if (gestion.getCondipago().equals("FINAN")) {
                    fpago = "";
                }
            }

            if (gestion.getMotivo() != null && !gestion.getMotivo().equals("")) {

                motivo = daomotivos.motivo(gestion.getCodmotivo(), gestion.getCodestado());
                if (motivo.getPermitellamar().equals("FALSE")) {
                    muestravll = false;
                } else {
                    muestravll = true;
                }

                if (motivo.getReunionoficina().equals("FALSE")) {
                    muestravofi = false;
                } else {
                    muestravofi = true;
                }

                if (motivo.getVisitaproyecto().equals("FALSE")) {
                    muestravproyec = false;
                } else {
                    muestravproyec = true;
                }
                muestramotivos = true;
            } else if (gestion.getMotivo().equals("")) {
                muestralote = true;
                muestramotivos = false;
                daoparcelas = new ParcelasDAO();
                lstparcelas = daoparcelas.listar();
                daolotes = new LotesDAO();
                lstlotes = daolotes.lotesporparcelas(gestion.getParcela());
                if (gestion.getCondipago().equals("FINAN")) {
                    fpago = "";
                }
            } else {
                muestramotivos = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(AsesoresBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pedirnuevo() {
        FacesContext contex = FacesContext.getCurrentInstance();
        Object objeto = contex.getExternalContext().getSessionMap().get("usuariosBean");
        Object prospectoasesor = contex.getExternalContext().getSessionMap().get("prospectodeasesor");
        UsuariosBean usuariosBean = null;
        Asesores asesor = null;
        EstadosDAO dao;
        OrigenesDAO daori;
        int numerolista;
        try {
            dao = new EstadosDAO();
            daori = new OrigenesDAO();

            if (objeto != null) {
                usuariosBean = (UsuariosBean) objeto;
                asesor = (Asesores) prospectoasesor;
                if (usuariosBean.getLstpendientes().isEmpty()) {
                    if (asesor != null) {
                        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectosAsesor.xhtml");
                    } else {
                        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listaProspectos.xhtml");
                    }
                } else {
                    lstpendiente = usuariosBean.getLstpendientes();
                    if (lstpendiente.indexOf(gestion) != -1) {
                        numerolista = lstpendiente.indexOf(gestion);
                        gestion = lstpendiente.get(numerolista + 1);
                    } else {
                        gestion = lstpendiente.get(1);
                    }
                }
            }

            this.gestion.setMotivo(null);
            this.gestion.setEstado(null);
            this.gestion.setEstado(null);
            this.gestion.setComentario(null);
            this.gestion.setFec_visita_proyec(null);
            this.gestion.setFec_visita_ofic(null);
            this.gestion.setFec_contrato(null);
            this.lstestados = dao.listar();
            boton = "Guardar";
            lstorigenes = daori.listar();

        } catch (Exception ex) {
            Logger.getLogger(AsesoresBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarAjx() {
        lstmotivos = null;
        motivo = null;
        muestravll = false;
        muestravofi = false;
        muestravproyec = false;
        muestralote = false;
        lstparcelas = null;
        muestrafpago = false;
        muestracasa = false;
        lstcasas = null;
        fpago = "none";
    }

    public void buscamotivos(String edo) throws Exception {
        MotivosDAO dao;
        EstadosDAO daoes;
        ParcelasDAO daoparcelas;
        this.limpiarAjx();
        try {
            dao = new MotivosDAO();
            daoes = new EstadosDAO();
            daoparcelas = new ParcelasDAO();
            lstparcelas = daoparcelas.listar();
            lstmotivos = dao.procesosEstados(edo);
            estado = daoes.estado(edo);
            if (estado.getMotivo().equals("FALSE")) {
                muestramotivos = false;
            } else {
                muestramotivos = true;
            }
            if (estado.getPermitellamar().equals("FALSE")) {
                muestravll = false;
            } else {
                muestravll = true;
            }
            if (estado.getCerrada().equals("FALSE")) {
                muestralote = false;
            } else {
                muestralote = true;
            }

        } catch (Exception ex) {
            throw ex;
        }

    }

    public void muestraestados(AjaxBehaviorEvent e) throws Exception {
        String opcion = (String) ((UIOutput) e.getSource()).getValue();
        MotivosDAO dao;
        EstadosDAO daoes;
        ParcelasDAO daoparcelas;
        this.limpiarAjx();
        if (opcion != null) {
            try {
                dao = new MotivosDAO();
                daoes = new EstadosDAO();
                daoparcelas = new ParcelasDAO();
                lstparcelas = daoparcelas.listar();
                lstmotivos = dao.procesosEstados(opcion);
                estado = daoes.estado(opcion);
                if (estado.getMotivo().equals("FALSE")) {
                    muestramotivos = false;
                } else {
                    muestramotivos = true;
                }
                if (estado.getPermitellamar().equals("FALSE")) {
                    muestravll = false;
                } else {
                    muestravll = true;
                }
                if (estado.getCerrada().equals("FALSE")) {
                    muestralote = false;
                } else {
                    muestralote = true;
                }

            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    public void muestrafechas(AjaxBehaviorEvent e) throws Exception {
        String opcion = (String) ((UIOutput) e.getSource()).getValue();
        MotivosDAO dao;
        String estado = this.gestion.getCodestado();
        if (opcion != null) {
            try {
                dao = new MotivosDAO();
                motivo = dao.motivo(opcion, estado);
                if (motivo.getPermitellamar().equals("FALSE")) {
                    muestravll = false;
                } else {
                    muestravll = true;
                }

                if (motivo.getReunionoficina().equals("FALSE")) {
                    muestravofi = false;
                } else {
                    muestravofi = true;
                }

                if (motivo.getVisitaproyecto().equals("FALSE")) {
                    muestravproyec = false;
                } else {
                    muestravproyec = true;
                }

            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    public void muestralotes(AjaxBehaviorEvent e) throws Exception {
        String opcion = (String) ((UIOutput) e.getSource()).getValue();
        LotesDAO dao;
        if (opcion != null) {
            try {
                dao = new LotesDAO();
                lstlotes = dao.lotesporparcelas(opcion);
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    public void retornalista(HttpSession session) {
        Asesores asesor;
        AsesoresDAO dao;
        String origen;
        try {
            dao = new AsesoresDAO();
            FacesContext contex = FacesContext.getCurrentInstance();
            asesor = dao.buscasesor(gestion.getCod_asesor());
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
            System.out.println(e.getMessage());
        }
    }
}
