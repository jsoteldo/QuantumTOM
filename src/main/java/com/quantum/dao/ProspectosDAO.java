package com.quantum.dao;

import com.quantum.modelos.Asesores;
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
public class ProspectosDAO extends DAO {

    formatoDeFechas formatos = new formatoDeFechas();

    public String maxcodigo(String origen) {
        String codigo;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("select MAX(CAST(SUBSTRING(codigo, LENGTH(origen)+1) AS SIGNED ))+1  from prospectos "
                    + "where codigo like ?");
            declaracion.setString(1, origen + "%");
            resultado = declaracion.executeQuery();
            String ultimocodigo = null;
            int numero;
            while (resultado.next()) {
                ultimocodigo = resultado.getString(1);
            }
            if (ultimocodigo == null) {
                codigo = origen + "0";
            } else {
                numero = Integer.parseInt(ultimocodigo);
                codigo = origen + numero;

            }

            return codigo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Mensaje registrar(Prospectos prospectos) throws Exception {
        Mensaje validosesion;
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO prospectos ( CODIGO, FECHA_PROSPECTO, FECHA_INGRESO, NOMBREAPELLIDO, TELEFONO1, "
                    + "TELEFONO2, CORREO, ORIGEN, CARGO, FECHARETIRO, ENCOLA, REITERASOLICITUD, CORREO_SEC, EDAD, SEXO, DISTRITO, COMENTARIO ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            declaracion.setString(1, this.maxcodigo(prospectos.getOrigen()));
            declaracion.setString(2, prospectos.getFecha_prospecto());
            declaracion.setString(3, prospectos.getFecha_ingreso());
            declaracion.setString(4, prospectos.getNombreapellido());
            declaracion.setString(5, prospectos.getTelefono1());
            declaracion.setString(6, prospectos.getTelefono2());
            declaracion.setString(7, prospectos.getCorreo());
            declaracion.setString(8, prospectos.getOrigen());
            declaracion.setString(9, prospectos.getCargo());
            declaracion.setString(10, prospectos.getFecharetiro());
            declaracion.setString(11, "FALSE");
            declaracion.setString(12, "FALSE");
            declaracion.setString(13, prospectos.getCorreo_sec());
            declaracion.setString(14, prospectos.getEdad());
            declaracion.setString(15, prospectos.getSexo());
            declaracion.setString(16, prospectos.getDistrito());
            declaracion.setString(17, prospectos.getComentario());
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Registrado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;

        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            
            this.Cancelar();
            
        }
    }

    public Mensaje validaprospecto(Prospectos prospecto) throws Exception {
        Mensaje validoprospecto = null;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select codigo from prospectos  "
                    + "where telefono1  like ? "
                    + "or correo like ?");
            declaracion.setString(2, "%"+prospecto.getCorreo()+"%");
            declaracion.setString(1, "%"+prospecto.getTelefono1()+"%");
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                validoprospecto = new Mensaje("", "El prospecto ya se encuentra registrado bajo el codigo: " +resultado.getString(1) , "mdi-close-circle-outline", "danger");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return validoprospecto;
    }

    public String retornacodigo(String correoprospecto) throws Exception {
        String codigoprospecto = null;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select codigo from prospectos  "
                    + "where correo = ?");
            declaracion.setString(1, correoprospecto);
            resultado = declaracion.executeQuery();
            
            while (resultado.next()) {
                codigoprospecto = resultado.getString(1);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return codigoprospecto;
    }
    
    public List<Prospectos> listardescartados(Asesores asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();

            if (asesor.getRol().equals("ADM") || asesor.getRol().equals("SUP")) {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT prospectos.CODIGO, gestion.CODIGO, substr(FECHA_PROSPECTO,1,10), substr(FECHA_INGRESO,1,10), NOMBREAPELLIDO, "
                        + " TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, estados.ESTADO, motivos.MOTIVO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                        + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + " LEFT JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + " LEFT JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " WHERE ESTATUS_VENTA = 'VCAI'"
                        + " ORDER BY 4,3");
            } else {

                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT prospectos.CODIGO, gestion.CODIGO, substr(FECHA_PROSPECTO,1,10), substr(FECHA_INGRESO,1,10), NOMBREAPELLIDO, "
                        + " TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, estados.ESTADO, motivos.MOTIVO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                        + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + " LEFT JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + " LEFT JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " WHERE ESTATUS_VENTA = 'VCAI'"
                        + " AND gestion.COD_ASESOR = ?"
                        + " ORDER BY 4,3 ");
                declaracion.setString(1, asesor.getCorreo());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCodigoGestion(resultado.getString(2));
                prospecto.setFecha_prospecto(resultado.getString(3));
                prospecto.setFecha_ingreso(resultado.getString(4));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(11));
                prospecto.setMotivo(resultado.getString(12));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Prospectos> listarpendientes(Asesores asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();

            if (asesor.getRol().equals("ADM") || asesor.getRol().equals("SUP")) {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT prospectos.CODIGO, gestion.CODIGO, substr(FECHA_PROSPECTO,1,10), substr(FECHA_INGRESO,1,10), NOMBREAPELLIDO, "
                        + " TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, estados.ESTADO, motivos.MOTIVO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                        + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + " LEFT JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + " LEFT JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " WHERE ESTATUS_VENTA = 'PEN'"
                        + " AND PROCESO_ESTATUS = 'PPC'"
                        + " ORDER BY 4,3");
            } else {

                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT prospectos.CODIGO, gestion.CODIGO, substr(FECHA_PROSPECTO,1,10), substr(FECHA_INGRESO,1,10), NOMBREAPELLIDO, "
                        + " TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, estados.ESTADO, motivos.MOTIVO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO"
                        + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + " LEFT JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + " LEFT JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " WHERE ESTATUS_VENTA = 'PEN'"
                        + " AND PROCESO_ESTATUS = 'PPC' "
                        + " AND gestion.COD_ASESOR = ?"
                        + " ORDER BY 4,3 ");
                declaracion.setString(1, asesor.getCorreo());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCodigoGestion(resultado.getString(2));
                prospecto.setFecha_prospecto(resultado.getString(3));
                prospecto.setFecha_ingreso(resultado.getString(4));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setLink(resultado.getString("LINK"));
                prospecto.setEstado(resultado.getString(11));
                prospecto.setMotivo(resultado.getString(12));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Prospectos> listargestion(Asesores asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        try {
            this.Conectar();

            declaracion = this.getConexion().prepareStatement(""
                    + "SELECT prospectos.CODIGO, gestion.CODIGO, FECHA_PROSPECTO, FECHA_INGRESO, NOMBREAPELLIDO, "
                    + " TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO"
                    + " FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + " WHERE PROCESO_ESTATUS IS NOT NULL");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCodigoGestion(resultado.getString(2));
                prospecto.setFecha_prospecto(resultado.getString("FECHA_PROSPECTO"));
                prospecto.setFecha_ingreso(resultado.getString("FECHA_INGRESO"));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Prospectos> listarporasignar() throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, FECHA_PROSPECTO, FECHA_INGRESO, NOMBREAPELLIDO,"
                    + " TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                    + " FROM prospectos INNER JOIN FBLEADS ON prospectos.CODIGO = FBLEADS.ID "
                    + " WHERE ENCOLA = 'FALSE' "
                    + " AND FECHARETIRO IS NULL "
                    + " AND REPITE= 'FALSE' "
                    + " AND FBLEADS.SUP_ENCARGADO is null");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString("CODIGO"));
                prospecto.setFecha_prospecto(resultado.getString("FECHA_PROSPECTO"));
                prospecto.setFecha_ingreso(resultado.getString("FECHA_INGRESO"));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Prospectos> listar(Asesores asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        PreparedStatement declaracion = null;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            if (asesor.getRol().equals("ADM") || asesor.getRol().equals("SUP")) {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT prospectos.CODIGO, gestion.CODIGO, FECHA_PROSPECTO, substr(gestion.FECHA_ULTIMO_CONTAC,1,10), NOMBREAPELLIDO, "
                        + "TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, estados.ESTADO, motivos.MOTIVO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                        + "FROM prospectos left join gestion on prospectos.codigo = gestion.cod_prospecto "
                        + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + "INNER JOIN motivos ON gestion.ESTADO = motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + "WHERE FECHARETIRO is null"
                        + " AND gestion.PROCESO_ESTATUS not in ('PPC')"
                        + " AND gestion.ESTATUS_VENTA not in ('VCAI')"
                        + " ORDER BY 4");
            } else {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT prospectos.CODIGO, gestion.CODIGO, FECHA_PROSPECTO, substr(gestion.FECHA_ULTIMO_CONTAC,1,10), NOMBREAPELLIDO,"
                        + " TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, estados.ESTADO, motivos.MOTIVO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                        + "FROM prospectos inner join gestion ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                        + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                        + "INNER JOIN motivos ON gestion.ESTADO =  motivos.ESTADO AND gestion.MOTIVO = motivos.CODMOT "
                        + " WHERE FECHARETIRO is null"
                        + " AND gestion.COD_ASESOR = ?"
                        + " AND FECHA_DESCARTE is null"
                        + " AND gestion.PROCESO_ESTATUS not in ('PPC')"
                        + " AND gestion.ESTATUS_VENTA not in ('VCAI')"
                        + " ORDER BY 4");
                declaracion.setString(1, asesor.getCorreo());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCodigoGestion(resultado.getString(2));
                prospecto.setFecha_prospecto(resultado.getString("FECHA_PROSPECTO"));
                prospecto.setFecha_ingreso(resultado.getString(4));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(11));
                prospecto.setMotivo(resultado.getString(12));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }

        return lista;
    }

    public List<Prospectos> listarmisprospectospendientes(String asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT prospectos.CODIGO, FECHA_PROSPECTO, FECHA_INGRESO, NOMBREAPELLIDO, "
                    + "TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                    + "FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + "WHERE COD_ASESOR = ? "
                    + "AND ESTATUS_VENTA = 'PEN'");
            declaracion.setString(1, asesor);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setFecha_prospecto(resultado.getString("FECHA_PROSPECTO"));
                prospecto.setFecha_ingreso(resultado.getString("FECHA_INGRESO"));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Prospectos> listarmisprospectos(String asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT prospectos.CODIGO, FECHA_PROSPECTO, FECHA_INGRESO, NOMBREAPELLIDO, "
                    + "TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                    + "FROM gestion INNER JOIN prospectos ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + "WHERE COD_ASESOR = ? "
                    + "AND ESTATUS_VENTA <> 'PEN'");
            declaracion.setString(1, asesor);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setFecha_prospecto(resultado.getString("FECHA_PROSPECTO"));
                prospecto.setFecha_ingreso(resultado.getString("FECHA_INGRESO"));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public void borrar(Prospectos prospecto) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE prospectos set FECHARETIRO = ? WHERE CODIGO = ?");
            declaracion.setString(1, prospecto.getFecharetiro());
            declaracion.setString(2, prospecto.getCodigo());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }

    public Mensaje modificar(Prospectos prospecto, String rol) throws Exception {
        Mensaje validosesion;
        PreparedStatement declaracion;

        try {
            this.Conectar();
            if (rol.equals("ASE")) {
                declaracion = this.getConexion().prepareStatement("UPDATE"
                        + " prospectos set  TELEFONO2 = ?,  ORIGEN = ?, CARGO = ?, EDAD = ?, SEXO = ?, DISTRITO = ?, CORREO_SEC = ?, COMENTARIO = ?"
                        + " WHERE CODIGO = ?");
                declaracion.setString(1, prospecto.getTelefono2());
                declaracion.setString(2, prospecto.getOrigen());
                declaracion.setString(3, prospecto.getCargo());
                declaracion.setString(4, prospecto.getEdad());
                declaracion.setString(5, prospecto.getSexo());
                declaracion.setString(6, prospecto.getDistrito());
                declaracion.setString(7, prospecto.getCorreo_sec());
                declaracion.setString(8, prospecto.getComentario());
                declaracion.setString(9, prospecto.getCodigo());

                validosesion = new Mensaje("", "S Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
                declaracion.executeUpdate();

                return validosesion;
            } else {
                declaracion = this.getConexion().prepareStatement("UPDATE"
                        + " prospectos set FECHA_PROSPECTO = ?, FECHA_INGRESO = ?, NOMBREAPELLIDO = ?,"
                        + " TELEFONO1 = ?, TELEFONO2 = ?, CORREO = ?, CORREO_SEC = ?, ORIGEN = ?, CARGO = ?, EDAD = ?, SEXO = ?, DISTRITO = ?, COMENTARIO = ?"
                        + " WHERE CODIGO = ?");
                declaracion.setString(1, prospecto.getFecha_prospecto());
                declaracion.setString(2, prospecto.getFecha_ingreso());
                declaracion.setString(3, prospecto.getNombreapellido());
                declaracion.setString(4, prospecto.getTelefono1());
                declaracion.setString(5, prospecto.getTelefono2());
                declaracion.setString(6, prospecto.getCorreo());
                declaracion.setString(7, prospecto.getCorreo_sec());
                declaracion.setString(8, prospecto.getOrigen());
                declaracion.setString(9, prospecto.getCargo());
                declaracion.setString(10, prospecto.getEdad());
                declaracion.setString(11, prospecto.getSexo());
                declaracion.setString(12, prospecto.getDistrito());
                declaracion.setString(13, prospecto.getComentario());
                declaracion.setString(14, prospecto.getCodigo());
                validosesion = new Mensaje("", "Actualizado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
                declaracion.executeUpdate();

                return validosesion;
            }

        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
    }

    public Prospectos prospecto(String cod_prospecto) throws Exception {
        Prospectos prospecto = null;
        ResultSet resultado;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select codigo, substr(fecha_prospecto,1,10), substr(fecha_ingreso,1,10), nombreapellido, telefono1, telefono2, correo, correo_sec, origen, "
                    + "cargo, edad, sexo, distrito, comentario from prospectos "
                    + "where codigo = ? "
                    + "and FECHARETIRO is null ");
            declaracion.setString(1, cod_prospecto);
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setFecha_prospecto(resultado.getString(2));
                prospecto.setFecha_ingreso(resultado.getString(3));
                prospecto.setNombreapellido(resultado.getString("nombreapellido"));
                prospecto.setTelefono1(resultado.getString("telefono1"));
                prospecto.setTelefono2(resultado.getString("telefono2"));
                prospecto.setCorreo(resultado.getString("correo"));
                prospecto.setCorreo_sec(resultado.getString("correo_sec"));
                prospecto.setOrigen(resultado.getString("origen"));
                prospecto.setCargo(resultado.getString("cargo"));
                prospecto.setEdad(resultado.getString("edad"));
                prospecto.setSexo(resultado.getString("sexo"));
                prospecto.setDistrito(resultado.getString("distrito"));
                prospecto.setComentario(resultado.getString("comentario"));

            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return prospecto;
    }

    public List<Prospectos> listarprospectosAsesor(Asesores asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        EstadosDAO dao;
        try {
            this.Conectar();
            dao = new EstadosDAO();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT prospectos.CODIGO, gestion.CODIGO, SUBSTR(FECHA_PROSPECTO,1,10), FECHA_INGRESO, NOMBREAPELLIDO,"
                    + " TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, estados.ESTADO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                    + "FROM prospectos inner join gestion ON gestion.COD_PROSPECTO = prospectos.CODIGO "
                    + "INNER JOIN estados ON gestion.ESTADO = estados.CODIGO "
                    + " WHERE FECHARETIRO is null"
                    + " AND gestion.COD_ASESOR = ?"
                    + " AND FECHA_DESCARTE is null");
            declaracion.setString(1, asesor.getCorreo());
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCodigoGestion(resultado.getString(2));
                prospecto.setFecha_prospecto(resultado.getString(3));
                prospecto.setFecha_ingreso(resultado.getString("FECHA_INGRESO"));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setEstado(resultado.getString(11));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                prospecto.setColorestado(dao.colorestado(prospecto.getEstado()));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Prospectos> lstllamar(Asesores asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        String fechaactual = formatos.convertirFechaString(new Date(), formatos.FORMATO_FECHA);
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT prospectos.CODIGO, gestion.CODIGO, substr(FECHA_PROSPECTO,1,10), substr(FECHA_CONTACTO,1,10), NOMBREAPELLIDO, "
                    + "TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, gestion.FECHA_CONTACTO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                    + "FROM gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and substr(gestion.fecha_contacto,1,10) <=  ? "
                    + "and estatus_venta not in ('VCAI') "
                    + "ORDER BY 12");
            declaracion.setString(1, asesor.getCorreo());
            declaracion.setString(2, fechaactual);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCodigoGestion(resultado.getString(2));
                prospecto.setFecha_prospecto(resultado.getString(3));
                prospecto.setFecha_ingreso(resultado.getString(4));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public List<Prospectos> lstvc(Asesores asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select prospectos.CODIGO, gestion.CODIGO, substr(FECHA_PROSPECTO,1,10), substr(FECHA_INGRESO,1,10), NOMBREAPELLIDO, "
                    + "TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                    + " from gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and gestion.estatus_venta = 'VC'"
                    + "");
            declaracion.setString(1, asesor.getCorreo());
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCodigoGestion(resultado.getString(2));
                prospecto.setFecha_prospecto(resultado.getString(3));
                prospecto.setFecha_ingreso(resultado.getString(4));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
                lista.add(prospecto);
            }
            return lista;
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public List<Prospectos> lstvh(Asesores asesor) throws Exception {
        List<Prospectos> lista;
        ResultSet resultado;
        String fechaactual = formatos.convertirFechaString(new Date(), formatos.FORMATO_FECHA);
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT prospectos.CODIGO, gestion.CODIGO, substr(FECHA_PROSPECTO,1,10), substr(FECHA_INGRESO,1,10), NOMBREAPELLIDO, "
                    + "TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, gestion.FECHA_CONTACTO,CORREO_SEC, EDAD, SEXO, DISTRITO, prospectos.COMENTARIO "
                    + "FROM gestion inner join prospectos on gestion.cod_prospecto = prospectos.codigo "
                    + "where cod_asesor = ? "
                    + "and prospectos.fecharetiro is null "
                    + "and estatus_venta = 'PRO' "
                    + "and gestion.motivo in ('VP','RO') "
                    + "and substr(gestion.FEC_VISITA_OFIC ,1,10) = ? "
                    + "or substr(gestion.FEC_VISITA_PROYEC,1,10) = ? "
                    + "ORDER BY 12");
            declaracion.setString(1, asesor.getCorreo());
            declaracion.setString(2, fechaactual);
            declaracion.setString(3, fechaactual);
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Prospectos prospecto = new Prospectos();
                prospecto.setCodigo(resultado.getString(1));
                prospecto.setCodigoGestion(resultado.getString(2));
                prospecto.setFecha_prospecto(resultado.getString(3));
                prospecto.setFecha_ingreso(resultado.getString(4));
                prospecto.setNombreapellido(resultado.getString("NOMBREAPELLIDO"));
                prospecto.setTelefono1(resultado.getString("TELEFONO1"));
                prospecto.setTelefono2(resultado.getString("TELEFONO2"));
                prospecto.setCorreo(resultado.getString("CORREO"));
                prospecto.setOrigen(resultado.getString("ORIGEN"));
                prospecto.setCargo(resultado.getString("CARGO"));
                prospecto.setCorreo_sec(resultado.getString("CORREO_SEC"));
                prospecto.setEdad(resultado.getString("EDAD"));
                prospecto.setSexo(resultado.getString("SEXO"));
                prospecto.setDistrito(resultado.getString("DISTRITO"));
                prospecto.setComentario(resultado.getString("prospectos.COMENTARIO"));
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
