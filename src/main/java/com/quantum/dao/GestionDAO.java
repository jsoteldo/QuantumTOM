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

/**
 *
 * @author QUANTUM
 */
public class GestionDAO extends DAO {

    private formatoDeFechas fechas = new formatoDeFechas();

    public Mensaje modificar(Gestion gestion) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE GESTION set COD_PROSPECTO = ?, COD_ASESOR = ?, ESTATUS_VENTA = ?, COMENTARIO = ?,"
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
            System.out.println(e.getMessage());
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
                    + "GESTION ( COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, COMENTARIO, AGENDO_CITA, "
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADO, MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO,FECHA_PROSPECTO, "
                    + "FECHA_INGRESO, NOMBREAPELLIDO, TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, FECHARETIRO, ENCOLA "
                    + "FROM GESTION inner join PROSPECTOS on GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                    + "WHERE GESTION.COD_ASESOR = ? "
                    + "AND GESTION.ESTATUS_VENTA='PEN'"
                    + "AND GESTION.PROCESO_ESTATUS='PPC'");
            declaracion.setString(1, correoasesor);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion gestion = new Gestion();
                gestion.setCodigo(resultado.getString(1));
                gestion.setCod_prospecto(resultado.getString("COD_PROSPECTO"));
                gestion.setCod_asesor(resultado.getString("COD_ASESOR"));
                gestion.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                gestion.setComentario(resultado.getString("GESTION.COMENTARIO"));
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO, GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                    + "FROM GESTION inner join PROSPECTOS on GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                    + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                    + "INNER JOIN MOTIVOS ON GESTION.ESTADO = MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                    + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
                    + "WHERE GESTION.CODIGO = ? ");
            declaracion.setString(1, codigo);
            resultado = declaracion.executeQuery();
            prospecto = new Gestion();
            while (resultado.next()) {

                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
            }
        } catch (Exception e) {
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
                        + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO, GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                        + "FROM GESTION inner join PROSPECTOS on GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                        + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                        + "INNER JOIN MOTIVOS ON GESTION.ESTADO = MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                        + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
                        + "WHERE PROSPECTOS.FECHARETIRO is null"
                        + " AND GESTION.PROCESO_ESTATUS not in ('PPC')"
                        + " AND GESTION.ESTATUS_VENTA not in ('VCAI')"
                        + " ORDER BY 8");
            } else {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                        + "FROM PROSPECTOS inner join GESTION ON GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                        + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                        + "INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                        + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
                        + " WHERE PROSPECTOS.FECHARETIRO is null"
                        + " AND GESTION.COD_ASESOR = ?"
                        + " AND FECHA_DESCARTE is null"
                        + " AND GESTION.PROCESO_ESTATUS not in ('PPC')"
                        + " AND GESTION.ESTATUS_VENTA not in ('VCAI')"
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));

                lista.add(prospecto);
            }

        } catch (Exception e) {
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
                        + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                        + " FROM GESTION INNER JOIN PROSPECTOS ON GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                        + " LEFT JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                        + " LEFT JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                        + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
                        + " WHERE ESTATUS_VENTA = 'VCAI'"
                        + " ORDER BY 8");
            } else {

                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                        + " FROM GESTION INNER JOIN PROSPECTOS ON GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                        + " LEFT JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                        + " LEFT JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                        + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
                        + " WHERE ESTATUS_VENTA = 'VCAI'"
                        + " AND GESTION.COD_ASESOR = ?"
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
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
                        + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                        + " FROM GESTION INNER JOIN PROSPECTOS ON GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                        + " LEFT JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                        + " LEFT JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                        + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
                        + " WHERE ESTATUS_VENTA = 'PEN'"
                        + " AND PROCESO_ESTATUS = 'PPC'"
                        + " ORDER BY 14");
            } else {

                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                        + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                        + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                        + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                        + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                        + " FROM GESTION INNER JOIN PROSPECTOS ON GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                        + " LEFT JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                        + " LEFT JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                        + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
                        + " WHERE ESTATUS_VENTA = 'PEN'"
                        + " AND PROCESO_ESTATUS = 'PPC' "
                        + " AND GESTION.COD_ASESOR = ?"
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                    + "FROM GESTION inner join PROSPECTOS on GESTION.cod_prospecto = PROSPECTOS.codigo "
                    + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                    + "INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                    + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
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
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                    + " from gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                    + "INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                    + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
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
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                    + "FROM GESTION inner join PROSPECTOS on GESTION.cod_prospecto = PROSPECTOS.codigo "
                    + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                    + "INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                    + "INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
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
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, \n"
                    + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, \n"
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, \n"
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), \n"
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG \n"
                    + "FROM GESTION inner join PROSPECTOS on GESTION.cod_prospecto = PROSPECTOS.codigo \n"
                    + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO \n"
                    + "INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT \n"
                    + "INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO \n"
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                    + "substr(FECHA_CONTACTO,1,10), substr(GESTION.FECHA_ULTIMO_CONTAC,1,10), FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, NOMBRES, IMG "
                    + " FROM GESTION INNER JOIN PROSPECTOS ON GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                    + "INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
                    + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                    + "INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                    + " WHERE PROCESO_ESTATUS IS NOT NULL");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString(2));
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCod_asesor(resultado.getString("COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
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
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("NOMBRES"));
                prospecto.setImgasesor(resultado.getString("IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                    + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                    + " FROM GESTION INNER JOIN PROSPECTOS ON GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                    + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                    + "INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                    + "INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO"
                    + " WHERE PROSPECTOS.FECHARETIRO is null"
                    + " AND GESTION.COD_ASESOR = ?"
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
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
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(17));
                prospecto.setMotivo(resultado.getString(18));
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, "
                    + "substr(FECHA_CONTACTO,1,10), substr(GESTION.FECHA_ULTIMO_CONTAC,1,10), FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, "
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG "
                    + " FROM GESTION INNER JOIN PROSPECTOS ON GESTION.COD_PROSPECTO = PROSPECTOS.CODIGO "
                    + " INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO "
                    + " INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT "
                    + " INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO "
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
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
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
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
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
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
                    + "UPDATE GESTION set COD_ASESOR = ? WHERE CODIGO = ? AND COD_ASESOR = ?");

            declaracion.setString(1, asesor);
            declaracion.setString(2, gestion.getCodigo());
            declaracion.setString(3, gestion.getCod_asesor());

            declaracion.executeUpdate();

            validosesion = this.checkokasesor(gestion);
            return validosesion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                    + "UPDATE PROSPECTOS set REITERASOLICITUD = 'FALSE' WHERE CODIGO = ?");

            actulalizaprospecto.setString(1, gestion.getCod_prospecto());

            actulalizaprospecto.executeUpdate();
            validosesion = new Mensaje("", "Actualizado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
                    + "SELECT GESTION.CODIGO, GESTION.COD_PROSPECTO, GESTION.COD_ASESOR, GESTION.ESTATUS_VENTA, GESTION.COMENTARIO, GESTION.AGENDO_CITA, "
                    + "GESTION.FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, GESTION.FEC_VISITA_OFIC, GESTION.FEC_VISITA_PROYEC, GESTION.FEC_RESERVA, GESTION.FEC_CONTRATO, "
                    + "GESTION.PROCESO_ESTATUS, GESTION.FECHA_ASIGNACION, GESTION.FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , "
                    + "GESTION.ESTADO, GESTION.MOTIVO, GESTION.PARCELA, GESTION.LOTE, GESTION.DIAFPAGO, "
                    + "GESTION.MESFPAGO, GESTION.MONTOFPAGO, GESTION.PRECIOLOTE, GESTION.MONTOINILOTE, GESTION.NCUOLOTE, GESTION.MONTOCUO, GESTION.CONDIPAGO, SUBSTR(PROSPECTOS.FECHA_PROSPECTO,1,10), "
                    + "SUBSTR(PROSPECTOS.FECHA_INGRESO,1,10), PROSPECTOS.NOMBREAPELLIDO, PROSPECTOS.TELEFONO1, PROSPECTOS.TELEFONO2, PROSPECTOS.CORREO, PROSPECTOS.ORIGEN, PROSPECTOS.CARGO, PROSPECTOS.FECHARETIRO, "
                    + "PROSPECTOS.ENCOLA, concat(b.NOMBRES,' ',b.APELLIDOS), b.IMG, asesores.NOMBRES  FROM PROSPECTOS "
                    + "LEFT JOIN  fbleads on  PROSPECTOS.codigo = fbleads.id "
                    + "left join  GESTION on  PROSPECTOS.codigo = GESTION.cod_prospecto "
                    + "left join estados on GESTION.estado = estados.codigo "
                    + "left join motivos on GESTION.estado = motivos.ESTADO and GESTION.motivo = motivos.codmot "
                    + "left join asesores on fbleads.sup_encargado = asesores.correo "
                    + "left join asesores b on GESTION.cod_asesor = b.correo "
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
                prospecto.setCodigo(resultado.getString("GESTION.CODIGO"));
                prospecto.setCod_prospecto(resultado.getString("GESTION.COD_PROSPECTO"));
                prospecto.setCod_asesor(resultado.getString("GESTION.COD_ASESOR"));
                prospecto.setEstatus_venta(resultado.getString("GESTION.ESTATUS_VENTA"));
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("GESTION.AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("GESTION.FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
                prospecto.setFec_visita_ofic(resultado.getString("GESTION.FEC_VISITA_OFIC"));
                prospecto.setFec_visita_proyec(resultado.getString("GESTION.FEC_VISITA_PROYEC"));
                prospecto.setFec_reserva(resultado.getString("GESTION.FEC_RESERVA"));
                prospecto.setFec_contrato(resultado.getString("GESTION.FEC_CONTRATO"));
                prospecto.setProceso_estatus(resultado.getString("GESTION.PROCESO_ESTATUS"));
                prospecto.setFecha_asignacion(resultado.getString("GESTION.FECHA_ASIGNACION"));
                prospecto.setFecha_descarte(resultado.getString("GESTION.FECHA_DESCARTE"));
                prospecto.setTipobjecion(resultado.getString("TIPOBJECION"));
                prospecto.setEstado(resultado.getString("ESTADOS.ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVOS.MOTIVO"));
                prospecto.setParcela(resultado.getString("GESTION.PARCELA"));
                prospecto.setLote(resultado.getString("GESTION.LOTE"));
                prospecto.setDiafpago(resultado.getString("GESTION.DIAFPAGO"));
                prospecto.setMesfpago(resultado.getString("GESTION.MESFPAGO"));
                prospecto.setMontofpago(resultado.getString("GESTION.MONTOFPAGO"));
                prospecto.setPreciolote(resultado.getString("GESTION.PRECIOLOTE"));
                prospecto.setMontoinilote(resultado.getString("GESTION.MONTOINILOTE"));
                prospecto.setNcuolote(resultado.getString("GESTION.NCUOLOTE"));
                prospecto.setMontocuo(resultado.getString("GESTION.MONTOCUO"));
                prospecto.setFecha_prospecto(resultado.getString("SUBSTR(PROSPECTOS.FECHA_PROSPECTO,1,10)"));
                prospecto.setFecha_ingreso(resultado.getString("SUBSTR(PROSPECTOS.FECHA_INGRESO,1,10)"));
                prospecto.setNombreapellido(resultado.getString("PROSPECTOS.NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("PROSPECTOS.TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("PROSPECTOS.TELEFONO2"));
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("PROSPECTOS.ORIGEN"));
                prospecto.setCargo(resultado.getString("PROSPECTOS.CARGO"));
                prospecto.setEstado(resultado.getString("ESTADOS.ESTADO"));
                prospecto.setMotivo(resultado.getString("MOTIVOS.MOTIVO"));
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("PROSPECTOS.ENCOLA"));
                prospecto.setCondipago(resultado.getString("GESTION.CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(b.NOMBRES,' ',b.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("b.IMG"));
                prospecto.setSup_encargado(resultado.getString("asesores.NOMBRES"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
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
                    + "SELECT GESTION.CODIGO, COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, GESTION.COMENTARIO, AGENDO_CITA, \n"
                    + "FECHA_CONTACTO, GESTION.FECHA_ULTIMO_CONTAC, FEC_VISITA_OFIC, FEC_VISITA_PROYEC, FEC_RESERVA, FEC_CONTRATO, \n"
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, FECHA_DESCARTE, TIPOBJECION, ESTADOS.ESTADO, MOTIVOS.MOTIVO , GESTION.ESTADO, GESTION.MOTIVO, PARCELA, LOTE, DIAFPAGO, \n"
                    + "MESFPAGO, MONTOFPAGO, PRECIOLOTE, MONTOINILOTE, NCUOLOTE, MONTOCUO, CONDIPAGO, SUBSTR(FECHA_PROSPECTO,1,10), \n"
                    + "SUBSTR(FECHA_INGRESO,1,10), NOMBREAPELLIDO, TELEFONO1, TELEFONO2, PROSPECTOS.CORREO, ORIGEN, CARGO, PROSPECTOS.FECHARETIRO, ENCOLA, concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS), ASESORES.IMG \n"
                    + "FROM GESTION inner join PROSPECTOS on GESTION.cod_prospecto = PROSPECTOS.codigo \n"
                    + "INNER JOIN ESTADOS ON GESTION.ESTADO = ESTADOS.CODIGO \n"
                    + "INNER JOIN MOTIVOS ON GESTION.ESTADO =  MOTIVOS.ESTADO AND GESTION.MOTIVO = MOTIVOS.CODMOT \n"
                    + "INNER JOIN ASESORES ON GESTION.COD_ASESOR = ASESORES.CORREO \n"
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
                prospecto.setComentario(resultado.getString("GESTION.COMENTARIO"));
                prospecto.setAgendo_cita(resultado.getString("AGENDO_CITA"));
                prospecto.setFecha_contacto(resultado.getString("FECHA_CONTACTO"));
                prospecto.setFecha_ultimo_contac(resultado.getString("GESTION.FECHA_ULTIMO_CONTAC"));
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
                prospecto.setCodestado(resultado.getString("GESTION.ESTADO"));
                prospecto.setCodmotivo(resultado.getString("GESTION.MOTIVO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("PROSPECTOS.CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setFecharetiro(resultado.getString("PROSPECTOS.FECHARETIRO"));
                prospecto.setEncola(resultado.getString("ENCOLA"));
                prospecto.setCondipago(resultado.getString("CONDIPAGO"));
                prospecto.setAsesor(resultado.getString("concat(ASESORES.NOMBRES,' ',ASESORES.APELLIDOS)"));
                prospecto.setImgasesor(resultado.getString("ASESORES.IMG"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }

    }
}
