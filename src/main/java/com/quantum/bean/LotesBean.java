package com.quantum.bean;

import com.quantum.dao.LotesDAO;
import com.quantum.dao.ParcelasDAO;
import com.quantum.modelos.Lotes;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Parcelas;
import com.quantum.servicios.formatoDeFechas;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author QUANTUM
 */
@Named("lotesBean")
@ViewScoped
public class LotesBean implements Serializable {

    private Mensaje message = new Mensaje(false, "none !important", "");

    private Lotes lotes = new Lotes();
    private List<Lotes> lstLotes;

    private Parcelas parcelas = new Parcelas();
    private List<Parcelas> lstParcelas;

    private formatoDeFechas fechas = new formatoDeFechas();
    private String boton;

    public Lotes getLotes() {
        return lotes;
    }

    public void setLotes(Lotes lotes) {
        this.lotes = lotes;
    }

    public List<Lotes> getLstLotes() {
        return lstLotes;
    }

    public void setLstLotes(List<Lotes> lstLotes) {
        this.lstLotes = lstLotes;
    }

    public Parcelas getParcelas() {
        return parcelas;
    }

    public void setParcelas(Parcelas parcelas) {
        this.parcelas = parcelas;
    }

    public List<Parcelas> getLstParcelas() {
        return lstParcelas;
    }

    public void setLstParcelas(List<Parcelas> lstParcelas) {
        this.lstParcelas = lstParcelas;
    }

    public formatoDeFechas getFechas() {
        return fechas;
    }

    public void setFechas(formatoDeFechas fechas) {
        this.fechas = fechas;
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

    public void operar() {
        try {
            if (boton.equals("Agregar")) {
                this.registrarparcela();
            } else {
                this.modificar();
            }
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void operarlotes() {
        try {
            if (boton.equals("Agregar")) {
                this.registrarlotes();
            } else {
                this.modificar();
            }
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void limpiar() {
        lotes.setParcela("");
        lotes.setLote("");
        lotes.setAcomun(null);
        lotes.setDerecha(null);
        lotes.setExclusiva(null);
        lotes.setFondo(null);
        lotes.setFrontal(null);
        lotes.setIzquierda(null);
        lotes.setPrecio(null);
        lotes.setTotal(null);
        parcelas.setParcela("");
    }

    public void registrarlotes() throws Exception {
        LotesDAO dao;
        Mensaje mensaje;

        try {
            dao = new LotesDAO();
            mensaje = dao.registrar(lotes);
            this.limpiar();
            message = mensaje;

        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        }
    }

    public void registrarparcela() throws Exception {
        ParcelasDAO dao;
        Mensaje mensaje;

        try {
            dao = new ParcelasDAO();
            mensaje = dao.registrar(parcelas);
            message = mensaje;

        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        }
    }

    public void listarparcela() throws Exception {
        LotesDAO dao;
        ParcelasDAO daopro;

        try {
            dao = new LotesDAO();
            daopro = new ParcelasDAO();
            lstLotes = dao.listarTodo();
            lstParcelas = daopro.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarlotes() throws Exception {
        LotesDAO dao;
        try {
            dao = new LotesDAO();
            lstLotes = dao.listar();
        } catch (Exception e) {
            throw e;
        }
    }

    public void leerIDlotes(Lotes lote, HttpSession session) throws IOException {
        FacesContext contex = FacesContext.getCurrentInstance();
        session.setAttribute("lotesparam", lote);
        contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/lotes.xhtml");
    }

    public void modificar() throws Exception {
        LotesDAO dao;
        Mensaje mensaje;
        try {
            dao = new LotesDAO();
            mensaje = dao.modificar(lotes);
            this.limpiar();
            message = mensaje;
            FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(contex.getExternalContext().getApplicationContextPath() + "/template/listalotesparcelas.xhtml");
        } catch (Exception e) {
            message = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
        }
    }

    public void borrar(Lotes lotes) throws Exception {
        try {
            LotesDAO dao;

            dao = new LotesDAO();
            dao.borrar(lotes);
            this.listarparcela();
        } catch (Exception e) {
            throw e;
        }
    }

    public void borrarparcela(Parcelas parcela) throws Exception {
        try {
            ParcelasDAO dao;

            dao = new ParcelasDAO();
            dao.borrar(parcela);
            this.listarparcela();
        } catch (Exception e) {
            throw e;
        }
    }

    public void capturarparcela(HttpSession session) {
        if (session.getAttribute("estadosparam") != null) {
            parcelas = (Parcelas) session.getAttribute("parcelaparam");
            session.removeAttribute("estadosparam");
            boton = "Actualizar";
        } else {
            parcelas = new Parcelas();
            boton = "Agregar";
        }
    }

    public void capturarlotes(HttpSession session) throws Exception {
        ParcelasDAO daopro;

        try {

            daopro = new ParcelasDAO();

            if (session.getAttribute("lotesparam") != null) {
                lotes = (Lotes) session.getAttribute("lotesparam");
                lstParcelas = daopro.listar();
                session.removeAttribute("lotesparam");
                boton = "Actualizar";
            } else {
                lotes = new Lotes();
                boton = "Agregar";
                lstParcelas = daopro.listar();
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
