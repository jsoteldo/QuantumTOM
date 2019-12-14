package com.quantum.dao;

import com.quantum.modelos.Asesores;
import com.quantum.modelos.Gestion;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Prospectos;
import com.quantum.servicios.formatoDeFechas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class GestionDAO extends DAO {
    private Logger log = LoggerFactory.getLogger(GestionDAO.class); 
    
    private formatoDeFechas fechas = new formatoDeFechas();

    public Mensaje modificar(Gestion gestion) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE gestion set COD_PROSPECTO = ?, COD_ASESOR = ?, ESTATUS_VENTA = ?, COMENTARIO = ?,"
                    + " AGENDO_CITA = ?, FECHA_CONTACTO = ?, FECHA_ULTIMO_CONTAC = ?, FEC_VISITA_OFIC = ?, "
                    + "FEC_VISITA_PROYEC = ?, FEC_RESERVA = ?, FEC_CONTRATO = ?, PROCESO_ESTATUS = ?, "
                    + "FECHA_ASIGNACION = ?, FECHA_DESCARTE = ?, TIPOBJECION = ?, ESTADO = ?, MOTIVO = ?, "
                    + "PARCELA = ?, LOTE = ?, DIAFPAGO = ?, MESFPAGO = ?, MONTOFPAGO = ?,  PRECIOLOTE = ?, "
                    + " MONTOINILOTE = ?, NCUOLOTE = ?, MONTOCUO = ?, CONDIPAGO = ? WHERE CODIGO = ?");

            declaracion.setString(1, gestion.getCod_prospecto());
            declaracion.setString(2, gestion.getCod_asesor());
            declaracion.setString(3, gestion.getCodestado());
            declaracion.setString(4, gestion.getComentario());
            declaracion.setString(5, gestion.getAgendo_cita());
            declaracion.setString(6, gestion.getFecha_contacto());
            declaracion.setString(7, fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_HORA_T));
            declaracion.setString(8, gestion.getFec_visita_ofic());
            declaracion.setString(9, gestion.getFec_visita_proyec());
            declaracion.setString(10, gestion.getFec_reserva());
            declaracion.setString(11, gestion.getFec_contrato());
            declaracion.setString(12, gestion.getCodmotivo());
            declaracion.setString(13, gestion.getFecha_asignacion());
            declaracion.setString(14, gestion.getFecha_descarte());
            declaracion.setString(15, gestion.getTipobjecion());
            declaracion.setString(16, gestion.getCodestado());
            declaracion.setString(17, gestion.getCodmotivo());
            declaracion.setString(18, gestion.getParcela());
            declaracion.setString(19, gestion.getLote());
            declaracion.setString(20, gestion.getDiafpago());
            declaracion.setString(21, gestion.getMesfpago());
            declaracion.setString(22, gestion.getMontofpago());
            declaracion.setString(23, gestion.getPreciolote());
            declaracion.setString(24, gestion.getMontoinilote());
            declaracion.setString(25, gestion.getNcuolote());
            declaracion.setString(26, gestion.getMontocuo());
            declaracion.setString(28, gestion.getCodigo());
            declaracion.setString(27, gestion.getCondipago());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Actualizado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            log.info(e.getMessage());
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
    }

    public void registrar(Gestion gestion) throws Exception {

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO "
                    + "gestion ( COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, "
                    + "FEC_CONTRATO, PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADO, "
                    + "MOTIVO, PARCELA, LOTE, DIAFPAGO, MESFPAGO, MONTOFPAGO, PRECIOLOTE, "
                    + "MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            //declaracion.setString(1, gestion.getCodigo());
            declaracion.setString(1, gestion.getCod_prospecto());
            declaracion.setString(2, gestion.getCod_asesor());
            declaracion.setString(3, gestion.getEstatus_venta());
            declaracion.setString(4, gestion.getComentario());
            declaracion.setString(5, gestion.getAgendo_cita());
            declaracion.setString(6, gestion.getFecha_contacto());
            declaracion.setString(7, fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_HORA_T));
            declaracion.setString(8, gestion.getFec_visita_ofic());
            declaracion.setString(9, gestion.getFec_visita_proyec());
            declaracion.setString(10, gestion.getFec_reserva());
            declaracion.setString(11, gestion.getFec_contrato());
            declaracion.setString(12, gestion.getProceso_estatus());
            declaracion.setString(13, gestion.getFecha_asignacion());
            declaracion.setString(14, gestion.getFecha_descarte());
            declaracion.setString(15, gestion.getTipobjecion());
            declaracion.setString(16, gestion.getEstado());
            declaracion.setString(17, gestion.getMotivo());
            declaracion.setString(18, gestion.getParcela());
            declaracion.setString(19, gestion.getLote());
            declaracion.setString(20, gestion.getDiafpago());
            declaracion.setString(21, gestion.getMesfpago());
            declaracion.setString(22, gestion.getMontofpago());
            declaracion.setString(23, gestion.getPreciolote());
            declaracion.setString(24, gestion.getMontoinilote());
            declaracion.setString(25, gestion.getNcuolote());
            declaracion.setString(26, gestion.getMontocuo());
            declaracion.setString(27, gestion.getCondipago());
            declaracion.executeUpdate();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
    }

    public List<Gestion> listarPendientes(String correoasesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADO, MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO,FECHA_PROSPECTO, "
                    + "FECHA_INGRESO, NOMBREAPELLIDO, TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, FECHARETIRO, ENCOLA "
                    + "FROM gestion inner join prospectos on gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + "WHERE gestion.COD_ASESOR = ? "
                    + "AND gestion.ESTATUS_VENTA='PEN'"
                    + "AND gestion.PROCESO_ESTATUS='PPC'");
            declaracion.setString(1, correoasesor);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion gestion = new Gestion();
                gestion.setCodigo(resultado.getString(1));
                gestion.setCod_prospecto(resultado.getString("COD_PROSPECTO"));
                gestion.setCod_asesor(resultado.getString("COD_ASESOR"));
                gestion.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                gestion.setComentario(resultado.getString("gestion.COMENTARIO"));
                gestion.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                gestion.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                gestion.setFecha_ultimo_contac(resultado.getString("FECHA_ULTIMO_CONTAC"));
                gestion.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                gestion.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                gestion.setFec_reserva(resultado.getString("FEC_RESERVA"));
                gestion.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                gestion.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                gestion.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                gestion.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                gestion.setTipobjecion(resultado.getString("TIPOBJECION"));
                gestion.setEstado(resultado.getString("ESTADO"));
                gestion.setMotivo(resultado.getString("MOTIVO"));
                gestion.setParcela(resultado.getString("PARCELA"));
                gestion.setLote(resultado.getString("LOTE"));
                gestion.setDiafpago(resultado.getString("DIAFPAGO"));
                gestion.setMesfpago(resultado.getString("MESFPAGO"));
                gestion.setMontofpago(resultado.getString("MONTOFPAGO"));
                gestion.setPreciolote(resultado.getString("PRECIOLOTE"));
                gestion.setMontoinilote(resultado.getString("MONTOINILOTE"));
                gestion.setNcuolote(resultado.getString("NCUOLOTE"));
                gestion.setMontocuo(resultado.getString("MONTOCUO"));
                gestion.setFecha_prospecto(resultado.getString("FECHA_PROSPECTO"));
                gestion.setFecha_ingreso(resultado.getString("FECHA_INGRESO"));
                gestion.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                gestion.setTelefono1(resultado.getString("TELEFONO1"));
                gestion.setTelefono2(resultado.getString("TELEFONO2"));
                gestion.setCorreo(resultado.getString("CORREO"));
                gestion.setOrigen(resultado.getString("ORIGEN"));
                gestion.setCargo(resultado.getString("CARGO"));
                gestion.setFecharetiro(resultado.getString("FECHARETIRO"));
                gestion.setEncola(resultado.getString("ENCOLA"));
                gestion.setCondipago(resultado.getString("CONDIPAGO"));
                lista.add(gestion);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Gestion consultagestion(String codigo) throws Exception {
        Gestion prospecto;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO, gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                    + "FROM gestion inner join prospectos on gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                    + "INNER JOIN motivos ON gestion.ESTADO = motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                    + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                    + "WHERE gestion.CODIGO = ? ");
            declaracion.setString(1, codigo);
            resultado = declaracion.executeQuery();
            prospecto = new Gestion();
            while (resultado.next()) {

                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
        return prospecto;
    }

    public int todos(Asesores asesor) throws Exception {
        int cant = 0;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select count(*) from gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + " where cod_asesor = ? "
                    + " and estatus_venta not in ('VCAI')"
                    + " and prospectos.fecharetiro is null ");
            declaracion.setString(1, asesor.getCorreo());
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                cant = resultado.getInt(1);
            }
            return cant;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public int pen(Asesores asesor) throws Exception {
        int cant = 0;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select count(*) from gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and gestion.ESTATUS_VENTA = 'PEN' "
                    + "and gestion.PROCESO_ESTATUS = 'PPC' ");
            declaracion.setString(1, asesor.getCorreo());
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                cant = resultado.getInt(1);
            }
            return cant;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public int llamar(Asesores asesor) throws Exception {
        int cant = 0;
        ResultSet resultado;
        String fechaactual = fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA);
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select count(*) from gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and substr(gestion.fecha_contacto,1,10) <=  ?"
                    + "and estatus_venta not in ('VCAI','VC')");
            declaracion.setString(1, asesor.getCorreo());
            declaracion.setString(2, fechaactual);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                cant = resultado.getInt(1);
            }
            return cant;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public int vc(Asesores asesor) throws Exception {
        int cant = 0;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select count(*) from gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and gestion.estatus_venta = 'VC'");
            declaracion.setString(1, asesor.getCorreo());
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                cant = resultado.getInt(1);
            }
            return cant;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public int vh(Asesores asesor) throws Exception {
        int cant = 0;
        ResultSet resultado;
        String fechaactual = fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA);
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select count(*) from gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and estatus_venta in ('PRO')  "
                    + "and gestion.motivo in ('VP','RO')  "
                    + "and (substr(gestion.FEC_VISITA_OFIC ,1,10) = ? "
                    + "or substr(gestion.FEC_VISITA_PROYEC ,1,10) = ? "
                    + ")");
            declaracion.setString(1, asesor.getCorreo());
            declaracion.setString(2, fechaactual);
            declaracion.setString(3, fechaactual);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                cant = resultado.getInt(1);
            }
            return cant;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public List<Gestion> listar(Asesores asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            if (asesor.getRol().equals("ADM") || asesor.getRol().equals("SUP") || asesor.getRol().equals("mk")) {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO, gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                        + "FROM gestion inner join prospectos on gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + "INNER JOIN motivos ON gestion.ESTADO = motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                        + "WHERE prospectos.FECHARETIRO is null"
                        + " AND gestion.PROCESO_ESTATUS not in ('PPC')"
                        + " AND gestion.ESTATUS_VENTA not in ('VCAI')"
                        + " ORDER BY 8");
            } else {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                        + "FROM prospectos inner join gestion ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                        + " WHERE prospectos.FECHARETIRO is null"
                        + " AND gestion.COD_ASESOR = ?"
                        + " AND FECHA_DESCARTE is null"
                        + " AND gestion.PROCESO_ESTATUS not in ('PPC')"
                        + " AND gestion.ESTATUS_VENTA not in ('VCAI')"
                        + " ORDER BY 8");
                declaracion.setString(1, asesor.getCorreo());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));

                lista.add(prospecto);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

        return lista;
    }

    public List<Gestion> listardescartados(Asesores asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();

            if (asesor.getRol().equals("ADM") || asesor.getRol().equals("SUP") || asesor.getRol().equals("mk")) {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                        + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + " LEFT JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + " LEFT JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                        + " WHERE ESTATUS_VENTA = 'VCAI'"
                        + " ORDER BY 8");
            } else {

                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                        + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + " LEFT JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + " LEFT JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                        + " WHERE ESTATUS_VENTA = 'VCAI'"
                        + " AND gestion.COD_ASESOR = ?"
                        + " ORDER BY 8 ");
                declaracion.setString(1, asesor.getCorreo());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Gestion> listarpendientes(Asesores asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();

            if (asesor.getRol().equals("ADM") || asesor.getRol().equals("SUP") || asesor.getRol().equals("mk")) {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                        + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + " LEFT JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + " LEFT JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                        + " WHERE ESTATUS_VENTA = 'PEN'"
                        + " AND PROCESO_ESTATUS = 'PPC'"
                        + " ORDER BY 14");
            } else {

                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                        + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + " LEFT JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + " LEFT JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                        + " WHERE ESTATUS_VENTA = 'PEN'"
                        + " AND PROCESO_ESTATUS = 'PPC' "
                        + " AND gestion.COD_ASESOR = ?"
                        + " ORDER BY 14 ");
                declaracion.setString(1, asesor.getCorreo());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Gestion> lstllamar(Asesores asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        EstadosDAO dao;
        String fechaactual = fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA);
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                    + "FROM gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                    + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                    + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and substr(gestion.fecha_contacto,1,10) <=  ? "
                    + "and estatus_venta not in ('VCAI','VC') "
                    + "ORDER BY 7");
            declaracion.setString(1, asesor.getCorreo());
            declaracion.setString(2, fechaactual);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public List<Gestion> lstvc(Asesores asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                    + " from gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                    + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                    + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and gestion.estatus_venta = 'VC'"
                    + "order by 7");
            declaracion.setString(1, asesor.getCorreo());
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public List<Gestion> lstrepeat() throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                    + "FROM gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                    + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                    + "INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                    + "where prospectos.fecharetiro is null "
                    + "and prospectos.reiterasolicitud = 'TRUE' "
                    + "order by 7");
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public List<Gestion> lstvh(Asesores asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        String fechaactual = fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA);
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, \n"
                    + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, \n"
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, \n"
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), \n"
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG \n"
                    + "FROM gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo \n"
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO \n"
                    + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT \n"
                    + "INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO \n"
                    + "where cod_asesor = ?\n"
                    + "and prospectos.fecharetiro is null \n"
                    + "and estatus_venta = 'PRO' \n"
                    + "and gestion.motivo in ('VP','RO') \n"
                    + "and (substr(gestion.FEC_VISITA_OFIC ,1,10) = ? \n"
                    + "or substr(gestion.FEC_VISITA_PROYEC ,1,10) = ? ) \n"
                    + "ORDER BY 7");
            declaracion.setString(1, asesor.getCorreo());
            declaracion.setString(2, fechaactual);
            declaracion.setString(3, fechaactual);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public List<Gestion> listargestion(Asesores asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                    + "substr(FECHA_CONTACTO,1,10), substr(gestion.FECHA_ULTIMO_CONTAC,1,10), FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, NOMBRES, IMG "
                    + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + "INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                    + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                    + " WHERE PROCESO_ESTATUS IS NOT NULL");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString(7));
                prospecto.setFecha_ultimo_contac(resultado.getString(8));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(29));
                prospecto.setFecha_ingreso(resultado.getString(30));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("NOMBRES"));
                prospecto.setImgasesor(resultado.getString("IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Gestion> listarprospectosAsesor(Asesores asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                    + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                    + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                    + "INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO"
                    + " WHERE prospectos.FECHARETIRO is null"
                    + " AND gestion.COD_ASESOR = ?"
                    + " AND FECHA_DESCARTE is null"
                    + " ORDER BY 14");
            declaracion.setString(1, asesor.getCorreo());
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString(7));
                prospecto.setFecha_ultimo_contac(resultado.getString(8));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(29));
                prospecto.setFecha_ingreso(resultado.getString(30));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Gestion> listarmisprospectos(String asesor) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, "
                    + "substr(FECHA_CONTACTO,1,10), substr(gestion.FECHA_ULTIMO_CONTAC,1,10), FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG "
                    + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + " INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                    + " INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                    + " INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO "
                    + " WHERE COD_ASESOR = ? "
                    + " AND ESTATUS_VENTA <> 'PEN'");
            declaracion.setString(1, asesor);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString(7));
                prospecto.setFecha_ultimo_contac(resultado.getString(8));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(29));
                prospecto.setFecha_ingreso(resultado.getString(30));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Mensaje actualizasesor(Gestion gestion, String asesor) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE gestion set COD_ASESOR = ? WHERE CODIGO = ? AND COD_ASESOR = ?");

            declaracion.setString(1, asesor);
            declaracion.setString(2, gestion.getCodigo());
            declaracion.setString(3, gestion.getCod_asesor());

            declaracion.executeUpdate();

            validosesion = this.checkokasesor(gestion);
            return validosesion;
        } catch (Exception e) {
            log.info(e.getMessage());
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
    }

    public Mensaje checkokasesor(Gestion gestion) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();

            PreparedStatement actulalizaprospecto = this.getConexion().prepareStatement(""
                    + "UPDATE prospectos set REITERASOLICITUD = 'FALSE' WHERE CODIGO = ?");

            actulalizaprospecto.setString(1, gestion.getCod_prospecto());

            actulalizaprospecto.executeUpdate();
            validosesion = new Mensaje("", "Actualizado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            log.info(e.getMessage());
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
    }

    public List<Gestion> buscapropspecto(Prospectos datos) throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();

            declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, gestion.COD_PROSPECTO, gestion.COD_ASESOR, gestion.ESTATUS_VENTA, gestion.COMENTARIO, gestion.AGENDO_CITA, "
                    + "gestion.FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, gestion.FEC_VISITA_OFIC, gestion.FEC_VISITA_PROYEC, gestion.FEC_RESERVA, gestion.FEC_CONTRATO, "
                    + "gestion.PROCESO_ESTATUS, gestion.FECHA_ASIGNACION, gestion.FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , "
                    + "gestion.ESTADO, gestion.MOTIVO, gestion.PARCELA, gestion.LOTE, gestion.DIAFPAGO, "
                    + "gestion.MESFPAGO, gestion.MONTOFPAGO, gestion.PRECIOLOTE, gestion.MONTOINILOTE, gestion.NCUOLOTE, gestion.MONTOCUO, gestion.CONDIPAGO, SUBSTR(prospectos.FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(prospectos.FECHA_INGRESO,1,10), prospectos.NOMBREAPELLIDO, prospectos.TELEFONO1, prospectos.TELEFONO2, prospectos.CORREO, prospectos.ORIGEN, prospectos.CARGO, prospectos.FECHARETIRO, "
                    + "prospectos.ENCOLA, concat(b.NOMBRES,' ',b.APELLIDOS), b.IMG, asesores.NOMBRES  FROM prospectos "
                    + "LEFT JOIN  fbleads on  prospectos.codigo = fbleads.id "
                    + "left join  gestion on  prospectos.codigo = gestion.cod_prospecto "
                    + "left join estados on gestion.estado = estados.codigo "
                    + "left join motivos on gestion.estado = motivos.ESTADO and gestion.motivo = motivos.codmot "
                    + "left join asesores on fbleads.sup_encargado = asesores.correo "
                    + "left join asesores b on gestion.cod_asesor = b.correo "
                    + "where NOMBREAPELLIDO like ? "
                    + "and TELEFONO1 like ? "
                    + "and prospectos.CORREO like ? ");

            declaracion.setString(1, "%"+datos.getNombreapellido()+"%");
            declaracion.setString(2, "%"+datos.getTelefono1()+"%");
            declaracion.setString(3, "%"+datos.getCorreo()+"%");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCodigo(resultado.getString("gestion.CODIGO"));
                prospecto.setCod_prospecto(resultado.getString("gestion.COD_PROSPECTO"));
                prospecto.setCod_asesor(resultado.getString("gestion.COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("gestion.ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("gestion.AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("gestion.FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("gestion.FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("gestion.FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("gestion.FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("gestion.FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("gestion.PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("gestion.FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("gestion.FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("estados.ESTADO"));
                prospecto.setMotivo(resultado.getString("motivos.MOTIVO"));
                prospecto.setParcela(resultado.getString("gestion.PARCELA"));
                prospecto.setLote(resultado.getString("gestion.LOTE"));
                prospecto.setDiafpago(resultado.getString("gestion.DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("gestion.MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("gestion.MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("gestion.PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("gestion.MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("gestion.NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("gestion.MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString("SUBSTR(prospectos.FECHA_PROSPECTO,1,10)"));
                prospecto.setFecha_ingreso(resultado.getString("SUBSTR(prospectos.FECHA_INGRESO,1,10)"));
                prospecto.setNombreapellido(resultado.getString("prospectos.NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("prospectos.TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("prospectos.TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("prospectos.ORIGEN"));
                prospecto.setCargo(resultado.getString("prospectos.CARGO"));
                prospecto.setEstado(resultado.getString("estados.ESTADO"));
                prospecto.setMotivo(resultado.getString("motivos.MOTIVO"));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("prospectos.ENCOLA"));
                prospecto.setCondipago(resultado.getString("gestion.CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(b.NOMBRES,' ',b.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("b.IMG"));
                prospecto.setSup_encargado(resultado.getString("asesores.NOMBRES"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Gestion> lstvh2() throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        String fechaactual = fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA);
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT gestion.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, gestion.COMENTARIO, AGENDO_CITA, \n"
                    + "FECHA_CONTACTO, gestion.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, \n"
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, estados.ESTADO, motivos.MOTIVO , gestion.ESTADO, gestion.MOTIVO, PARCELA, LOTE, DIAFPAGO, \n"
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), \n"
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, prospectos.CORREO, ORIGEN, CARGO, prospectos.FECHARETIRO, ENCOLA, concat(asesores.NOMBRES,' ',asesores.APELLIDOS), asesores.IMG \n"
                    + "FROM gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo \n"
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO \n"
                    + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT \n"
                    + "INNER JOIN asesores ON gestion.COD_ASESOR = asesores.CORREO \n"
                    + "where cod_asesor = ?\n"
                    + "and prospectos.fecharetiro is null \n"
                    + "ORDER BY 7");
            declaracion.setString(1, "eugenio2@flamencos.com.pe");
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("gestion.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("gestion.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVO"));
                prospecto.setParcela(resultado.getString("PARCELA"));
                prospecto.setLote(resultado.getString("LOTE"));
                prospecto.setDiafpago(resultado.getString("DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString(31));
                prospecto.setFecha_ingreso(resultado.getString(32));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("gestion.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("gestion.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("prospectos.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("prospectos.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(asesores.NOMBRES,' ',asesores.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }

    }
}
