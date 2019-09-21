package com.quantum.dao;

import com.quantum.modelos.Asesores;
import com.quantum.modelos.Distribucion;
import com.quantum.modelos.Gestion;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Prospectos;
import com.quantum.servicios.formatoDeFechas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class DistribucionDAO extends DAO {

    formatoDeFechas formatos = new formatoDeFechas();

    public String cantporasignar;

    public String getCantporasignar() {
        return cantporasignar;
    }

    public void setCantporasignar(String cantporasignar) {
        this.cantporasignar = cantporasignar;
    }

    /* public String cantporasignar() throws Exception {
        double asesores, prospectos, porasignar, porcenasig;
        asesores = Double.parseDouble(this.cantasesores());

        prospectos = Double.parseDouble(this.cantprospectos());

        porasignar = prospectos / asesores;

        return String.valueOf((int) porasignar);
    }

    public String porcenporasignar() throws Exception {
        int prospectos;
        double porcentaje, porcenasig;
        prospectos = Integer.parseInt(this.cantprospectos());
        porcentaje = (double) (Integer.parseInt(this.cantporasignar()) * 100) / prospectos;
        porcenasig = (double) Math.round(porcentaje * 100d) / 100d;
        return String.valueOf(porcenasig);
    }
     */
    public List<Distribucion> listar(Asesores ase) throws Exception {
        List<Distribucion> lista;
        ResultSet resultado;
        try {
            this.Conectar();

            if (ase.getRol().equals("ADM")) {
                PreparedStatement declaracion = this.getConexion().prepareStatement(""
                        + "SELECT CORREO, NOMBRES, APELLIDOS FROM ASESORES "
                        + "WHERE ROL = 'ASE' "
                        + "AND FECHARETIRO IS NULL");

                resultado = declaracion.executeQuery();
                lista = new ArrayList<>();

                while (resultado.next()) {
                    Distribucion distribucion = new Distribucion();
                    distribucion.setCorreo(resultado.getString("CORREO"));
                    distribucion.setNombre(resultado.getString("NOMBRES"));
                    distribucion.setApellido(resultado.getString("APELLIDOS"));
                    distribucion.setCantidad("0");
                    distribucion.setPorcentaje("0");
                    lista.add(distribucion);
                }

                return lista;

            } else if (ase.getRol().equals("SUP")) {
                StringBuilder queryvariables = new StringBuilder("");
                StringBuilder queryconsulta = new StringBuilder("SELECT CORREO, NOMBRES, APELLIDOS FROM ASESORES "
                        + "WHERE ROL = 'ASE' "
                        + "AND FECHARETIRO IS NULL "
                        + "AND CORREO IN (");

                for (int i = 0; i < ase.getEmpl_cargo().size(); i++) {
                    queryvariables = i < (ase.getEmpl_cargo().size() - 1) ? queryvariables.append("?,") : queryvariables.append("?)");
                }

                queryconsulta.append(queryvariables);
                PreparedStatement actualizaleads = this.getConexion().prepareStatement(queryconsulta.toString());

                int i = 1;
                for (String asesores : ase.getEmpl_cargo()) {
                    actualizaleads.setString(i, asesores);
                    i++;
                }

                resultado = actualizaleads.executeQuery();

                lista = new ArrayList<>();

                while (resultado.next()) {
                    Distribucion distribucion = new Distribucion();
                    distribucion.setCorreo(resultado.getString("CORREO"));
                    distribucion.setNombre(resultado.getString("NOMBRES"));
                    distribucion.setApellido(resultado.getString("APELLIDOS"));
                    distribucion.setCantidad("0");
                    distribucion.setPorcentaje("0");
                    lista.add(distribucion);
                }

                return lista;
            } else {
                lista = null;
                return lista;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public List<Distribucion> listarsup() throws Exception {
        List<Distribucion> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CORREO, NOMBRES, APELLIDOS FROM ASESORES "
                    + "WHERE ROL in ('SUP','ADM') "
                    + "AND FECHARETIRO IS NULL "
            );

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();

            while (resultado.next()) {
                Distribucion distribucion = new Distribucion();
                distribucion.setCorreo(resultado.getString("CORREO"));
                distribucion.setNombre(resultado.getString("NOMBRES"));
                distribucion.setApellido(resultado.getString("APELLIDOS"));
                distribucion.setCantidad("0");
                distribucion.setPorcentaje("0");
                lista.add(distribucion);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Gestion> listarrepetidos() throws Exception {
        List<Gestion> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select prospectos.codigo, gestion.CODIGO, fecha_prospecto, fechareitera, nombreapellido, asesores.NOMBRES, asesores.APELLIDOS, asesores.IMG "
                    + "from prospectos inner join gestion on prospectos.CODIGO = gestion.COD_PROSPECTO "
                    + "inner join asesores on asesores.CORREO = gestion.COD_ASESOR "
                    + "where prospectos.reiterasolicitud = 'TRUE'");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();

            while (resultado.next()) {
                Gestion prospecto = new Gestion();
                prospecto.setCod_prospecto(resultado.getString("prospectos.codigo"));
                prospecto.setCodigo(resultado.getString("gestion.CODIGO"));
                prospecto.setFecha_prospecto(resultado.getString("fecha_prospecto"));
                prospecto.setFecharetiro(resultado.getString("fechareitera"));
                prospecto.setNombreapellido(resultado.getString("nombreapellido"));
                prospecto.setAsesor(resultado.getString("asesores.NOMBRES") + " " + resultado.getString("asesores.APELLIDOS"));
                prospecto.setImgasesor(resultado.getString("asesores.IMG"));
                lista.add(prospecto);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public String cantasesores() throws Exception {
        ResultSet resultado;
        int total = 0;
        try {

            this.Conectar();

            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CORREO FROM ASESORES "
                    + "WHERE ROL = 'ASE' "
                    + "AND FECHARETIRO IS NULL");

            resultado = declaracion.executeQuery();

            while (resultado.next()) {
                total++;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return String.valueOf(total);
    }

    public String cantprospectos(Asesores ase) throws Exception {
        ResultSet resultado;
        int total = 0;
        try {
            this.Conectar();

            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO FROM PROSPECTOS "
                    + "INNER JOIN FBLEADS ON PROSPECTOS.CODIGO = FBLEADS.ID "
                    + "WHERE ENCOLA = 'FALSE' "
                    + "AND FECHARETIRO IS NULL "
                    + "AND FBLEADS.SUP_ENCARGADO = ?");

            declaracion.setString(1, ase.getCorreo());

            resultado = declaracion.executeQuery();

            while (resultado.next()) {
                total++;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return String.valueOf(total);
    }

    public String cantprospectossa(Asesores ase) throws Exception {
        ResultSet resultado;
        int total = 0;
        try {
            this.Conectar();

            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select id from fbleads "
                    + "where FBLEADS.SUP_ENCARGADO is null "
                    + "and fbleads.repite ='FALSE'");

            resultado = declaracion.executeQuery();

            while (resultado.next()) {
                total++;
            }

            return String.valueOf(total);
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }

    }

    public int repeat() throws Exception {
        int cant = 0;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select count(*) from prospectos "
                    + "where REITERASOLICITUD = 'TRUE' ");
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

    public Mensaje distribuirsup(List<Distribucion> lstDistribucion) throws Exception {
        Mensaje msg = null;

        List<String> lstProspectos = new ArrayList<>();

        Calendar fecha = Calendar.getInstance();
        try {

            this.Conectar();

            lstProspectos = this.buscaprospectos(new Asesores());

            int i = 0;

            for (Distribucion distribuir : lstDistribucion) {

                PreparedStatement declaraciondistribucion = this.getConexion().prepareStatement(
                        "UPDATE FBLEADS "
                        + "SET SUP_ENCARGADO= ?  "
                        + "WHERE ID IN (SELECT ID FROM FBLEADS LIMIT " + distribuir.getCantidad() + ") "
                        + "AND SUP_ENCARGADO IS NULL"
                );

                declaraciondistribucion.setString(1, distribuir.getCorreo());
                declaraciondistribucion.execute();

            }

            return msg;

        } catch (Exception e) {
            msg = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return msg;
        } finally {
            this.Cancelar();
        }

    }

    public Mensaje distribuir(List<Distribucion> lstDistribucion, Asesores ase) throws Exception {
        Mensaje msg = null;

        List<String> lstProspectos = new ArrayList<>();

        Calendar fecha = Calendar.getInstance();
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(
                    "INSERT INTO GESTION (COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, ESTADO, MOTIVO) VALUES (?,?,?,?,?,?,?)");

            PreparedStatement declaraciondistribucion = this.getConexion().prepareStatement(
                    "INSERT INTO DISTRIBUCION (ASESOR, CANT_ASIGNADA, PORC_ASIGNADA,"
                    + " BASEASIG, MES, ANO, FECH_ASIGNADA) VALUES (?,?,?,?,?,?,?)");

            lstProspectos = this.buscaprospectos(ase);

            int i = 0;

            for (Distribucion distribuir : lstDistribucion) {
                if (!distribuir.getCantidad().equals("0")) {
                    int limite = Integer.parseInt(distribuir.getCantidad());

                    declaraciondistribucion.setString(1, distribuir.getCorreo());
                    declaraciondistribucion.setString(2, distribuir.getCantidad());
                    declaraciondistribucion.setString(3, distribuir.getPorcentaje());
                    declaraciondistribucion.setString(4, this.cantprospectos(ase));
                    declaraciondistribucion.setString(5, String.valueOf(fecha.get(Calendar.MONTH) + 1));
                    declaraciondistribucion.setString(6, String.valueOf(fecha.get(Calendar.YEAR)));
                    declaraciondistribucion.setString(7, formatos.convertirFechaString(new Date(), formatos.FORMATO_FECHA));
                    declaraciondistribucion.execute();

                    for (int contador = 0; contador < limite; contador++) {

                        declaracion.setString(1, lstProspectos.get(i));
                        declaracion.setString(2, distribuir.getCorreo());
                        declaracion.setString(3, "PEN");
                        declaracion.setString(4, "PPC");
                        declaracion.setString(5, formatos.convertirFechaString(new Date(), formatos.FORMATO_FECHA));
                        declaracion.setString(6, "PEN");
                        declaracion.setString(7, "PPC");
                        declaracion.execute();
                        msg = this.modificar(lstProspectos.get(i));
                        i++;
                    }
                }
            }
            return msg;

        } catch (Exception e) {
            msg = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return msg;
        } finally {
            this.Cancelar();
        }

    }

    public Mensaje distribuirdeprospecto(Prospectos prospecto, Asesores ase) throws Exception {
        Mensaje msg = null;
        ProspectosDAO daoprospect = new ProspectosDAO();
        Calendar fecha = Calendar.getInstance();
        String codprospecto = daoprospect.retornacodigo(prospecto.getCorreo());
            
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(
                    "INSERT INTO GESTION (COD_PROSPECTO, COD_ASESOR, ESTATUS_VENTA, "
                    + "PROCESO_ESTATUS, FECHA_ASIGNACION, ESTADO, MOTIVO) VALUES (?,?,?,?,?,?,?)");

            PreparedStatement declaraciondistribucion = this.getConexion().prepareStatement(
                    "INSERT INTO DISTRIBUCION (ASESOR, CANT_ASIGNADA, PORC_ASIGNADA,"
                    + " BASEASIG, MES, ANO, FECH_ASIGNADA) VALUES (?,?,?,?,?,?,?)");

            declaraciondistribucion.setString(1, ase.getCorreo());
            declaraciondistribucion.setString(2, "1");
            declaraciondistribucion.setString(3, "100");
            declaraciondistribucion.setString(4, "0");
            declaraciondistribucion.setString(5, String.valueOf(fecha.get(Calendar.MONTH) + 1));
            declaraciondistribucion.setString(6, String.valueOf(fecha.get(Calendar.YEAR)));
            declaraciondistribucion.setString(7, formatos.convertirFechaString(new Date(), formatos.FORMATO_FECHA));
            declaraciondistribucion.execute();

            declaracion.setString(1, codprospecto);
            declaracion.setString(2, ase.getCorreo());
            declaracion.setString(3, "PEN");
            declaracion.setString(4, "PPC");
            declaracion.setString(5, formatos.convertirFechaString(new Date(), formatos.FORMATO_FECHA));
            declaracion.setString(6, "PEN");
            declaracion.setString(7, "PPC");
            declaracion.execute();
            msg = this.modificar(prospecto.getCorreo());

            return msg;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return msg;
        } finally {
            this.Cancelar();
        }

    }

    public List<String> buscaprospectos(Asesores ase) throws Exception {
        List<String> lista;
        ResultSet resultado;
        try {
            this.Conectar();

            PreparedStatement declaracion;
            if (ase.getCorreo().isEmpty()) {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT CODIGO, FECHA_PROSPECTO, FECHA_INGRESO, NOMBREAPELLIDO, TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, LINK "
                        + "FROM PROSPECTOS "
                        + "WHERE ENCOLA = 'FALSE' "
                        + "AND FECHARETIRO IS NULL");
            } else {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT CODIGO, FECHA_PROSPECTO, FECHA_INGRESO, NOMBREAPELLIDO, TELEFONO1, TELEFONO2, CORREO, ORIGEN, CARGO, LINK "
                        + "FROM PROSPECTOS INNER JOIN fbleads on prospectos.CODIGO = fbleads.id "
                        + "WHERE ENCOLA = 'FALSE' "
                        + "AND FECHARETIRO IS NULL "
                        + "AND fbleads.sup_encargado = ?");
                declaracion.setString(1, ase.getCorreo());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                lista.add(resultado.getString("CODIGO"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public Mensaje modificar(String asesores) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("UPDATE"
                    + " PROSPECTOS set ENCOLA = 'TRUE' WHERE ENCOLA = 'FALSE'"
                    + " AND CODIGO = ?");

            declaracion.setString(1, asesores);
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Asignados Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
    }

    /*public void actualizarprospectos() throws Exception {
        System.out.println("LLEGANDO");
        List<String> prospectos = new ArrayList<>();
        try {

            this.Conectar();
            prospectos = this.buscaprospectos();
            for (String campos : prospectos) {
                System.out.println(campos);
            }
            StringBuilder queryUpdate = new StringBuilder("UPDATE PROSPECTOS SET ENCOLA ='TRUE'"
                    + " WHERE CODIGO IN (");

            for (int a = 0; a < prospectos.size(); a++) {
                queryUpdate = a < (prospectos.size() - 1) ? queryUpdate.append("?,") : queryUpdate.append("?)");
            }
            System.out.println(queryUpdate);
            PreparedStatement declaracionUpdate = this.getConexion().prepare(queryUpdate.toString());

            int itera = 1;
            
            for (String campos : prospectos) {
                System.out.println(campos);
                declaracionUpdate.setString(itera++, campos);
            }
            
            declaracionUpdate.executeUpdate();
        } catch (Exception e) {
        } finally {
            this.Cancelar();
        }*/
    public Mensaje asignasuper(List<Distribucion> lstDistribucion) throws Exception {
        Mensaje msg = null;
        String baseasignada = this.cantprospectossa(new Asesores());
        List<String> lstProspectos = new ArrayList<>();

        Calendar fecha = Calendar.getInstance();
        try {

            this.Conectar();

            PreparedStatement declaracion = this.getConexion().prepareStatement("update fbleads set sup_encargado = ? "
                    + "where sup_encargado is null "
                    + "and repite = 'FALSE' "
                    + "limit ?");

            PreparedStatement declaraciondistribucion = this.getConexion().prepareStatement(
                    "INSERT INTO DISTRIBUCION (ASESOR, CANT_ASIGNADA, PORC_ASIGNADA,"
                    + " BASEASIG, MES, ANO, FECH_ASIGNADA) VALUES (?,?,?,?,?,?,?)");

            for (Distribucion distribuir : lstDistribucion) {
                if (!distribuir.getCantidad().equals("0")) {
                    int limite = Integer.parseInt(distribuir.getCantidad());

                    declaracion.setString(1, distribuir.getCorreo());
                    declaracion.setInt(2, limite);
                    declaracion.execute();

                    declaraciondistribucion.setString(1, distribuir.getCorreo());
                    declaraciondistribucion.setString(2, distribuir.getCantidad());
                    declaraciondistribucion.setString(3, distribuir.getPorcentaje());
                    declaraciondistribucion.setString(4, baseasignada);
                    declaraciondistribucion.setString(5, String.valueOf(fecha.get(Calendar.MONTH) + 1));
                    declaraciondistribucion.setString(6, String.valueOf(fecha.get(Calendar.YEAR)));
                    declaraciondistribucion.setString(7, formatos.convertirFechaString(new Date(), formatos.FORMATO_FECHA));
                    declaraciondistribucion.execute();
                }
            }

            msg = new Mensaje("", "Asignados Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
            return msg;

        } catch (Exception e) {
            msg = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return msg;
        } finally {
            this.Cancelar();
        }

    }
}
