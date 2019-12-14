package com.quantum.dao;

import com.quantum.modelos.Fbleads;
import com.quantum.modelos.Mensaje;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class FbleadsDAO extends DAO {

    private org.slf4j.Logger log = LoggerFactory.getLogger(FbleadsDAO.class);

    public Mensaje registrar(String campos, StringBuilder query) throws Exception {

        Mensaje validoregistrro;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("insert into fbleads (" + campos + ") values " + query.toString());
            declaracion.executeUpdate();
            //declaracion.executeQuery(query.toString());
            validoregistrro = new Mensaje("", "Leads Insertados.", "mdi-checkbox-marked-circle-outline", "success");
            return validoregistrro;
        } catch (Exception e) {
            log.info(e.getMessage());
            validoregistrro = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validoregistrro;
        } finally {
            this.Cancelar();
        }
    }

    public void actualizafbleads(String fecha) throws Exception {
        try {
            this.Conectar();

            PreparedStatement actualizaadset = this.getConexion().prepareStatement("update fbleads set adset_name = 'OT', adset_id= 'OT' where adset_id in ('', null,'null', 'NULL', 'Null') and fecha_insert = ?");
            actualizaadset.setString(1, fecha);

            PreparedStatement actualizaad = this.getConexion().prepareStatement("update fbleads set ad_name = 'OT', ad_id = 'OT' where ad_id in ('', null,'null', 'NULL', 'Null') and fecha_insert = ?");
            actualizaad.setString(1, fecha);

            actualizaadset.executeUpdate();
            actualizaad.executeUpdate();

        } catch (SQLException ex) {
            log.info(ex.getMessage());
            Logger.getLogger(FbleadsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            log.info(ex.getMessage());
            Logger.getLogger(FbleadsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.Cancelar();
        }

    }

    public Mensaje anunciosfaltantes() throws Exception {
        List<String> lista;
        ResultSet resultado;
        Mensaje mensaje = null;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select distinct ad_name from fbleads "
                    + "where ad_name not in (select codigo from anuncios)");
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();

            while (resultado.next()) {
                lista.add(resultado.getString("ad_name"));
            }
            if (lista.size() > 0) {
                mensaje = this.insertanuncios(lista);

            } else {
                mensaje = new Mensaje("", "No hay anuncios nuevos.", "mdi-checkbox-marked-circle-outline", "success");
            }

        } catch (SQLException ex) {
            log.info(ex.getMessage());
            mensaje = new Mensaje("", ex.getMessage(), "mdi-close-circle-outline", "danger");
            return mensaje;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            mensaje = new Mensaje("", ex.getMessage(), "mdi-close-circle-outline", "danger");
            return mensaje;
        } finally {
            this.Cancelar();
        }
        return mensaje;
    }

    public Mensaje insertanuncios(List<String> codigosanuncios) {
        Mensaje mensaje = null;
        try {
            PreparedStatement declaracion = this.getConexion().prepareStatement("insert into anuncios (codigo, descripcion) values (?,?)");
            int contador = 0;
            for (String codigo : codigosanuncios) {
                if (!codigo.equals(" ") || !codigo.equals("") || !codigo.equals(null)) {
                    declaracion.setString(1, codigo);
                    declaracion.setString(2, codigo);
                    declaracion.executeUpdate();
                    contador++;
                }
            }
            if (contador == 0) {
                mensaje = new Mensaje("", "No hay anuncios nuevos.", "mdi-checkbox-marked-circle-outline", "success");
            } else {
                mensaje = new Mensaje("", "Se insertaron <b>" + contador + "</b> anuncios.", "mdi-checkbox-marked-circle-outline", "success");
            }

        } catch (SQLException ex) {
            log.info(ex.getMessage());
            mensaje = new Mensaje("", ex.getMessage(), "mdi-close-circle-outline", "danger");

        }
        return mensaje;
    }

    public Mensaje conjuntosfaltantes() {
        List<String> lista;
        ResultSet resultado;
        Mensaje mensaje = null;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select distinct adset_name from fbleads "
                    + "where adset_name not in (select id from conjuanuncios)");
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();

            while (resultado.next()) {
                lista.add(resultado.getString("adset_name"));
            }

            if (lista.size() > 0) {
                mensaje = this.insertaconjuntos(lista);
            } else {
                mensaje = new Mensaje("", "No hay conjuntos nuevos.", "mdi-checkbox-marked-circle-outline", "success");
            }

        } catch (SQLException ex) {
            log.info(ex.getMessage());
            mensaje = new Mensaje("", ex.getMessage(), "mdi-close-circle-outline", "danger");
        } catch (Exception ex) {
            log.info(ex.getMessage());
            mensaje = new Mensaje("", ex.getMessage(), "mdi-close-circle-outline", "danger");
        }
        return mensaje;
    }

    public Mensaje insertaconjuntos(List<String> idsconjuntos) {
        Mensaje mensaje = null;
        try {
            PreparedStatement declaracion = this.getConexion().prepareStatement("insert into conjuanuncios (id, descripcion) values (?,?)");
            int contador = 0;
            for (String codigo : idsconjuntos) {
                if (!codigo.equals(" ") || !codigo.equals("") || !codigo.equals(null)) {
                    declaracion.setString(1, codigo);
                    declaracion.setString(2, codigo);
                    declaracion.executeUpdate();
                    contador++;
                }

            }

            if (contador == 0) {
                mensaje = new Mensaje("", "No hay conjuntos nuevos.", "mdi-checkbox-marked-circle-outline", "success");
            } else {
                mensaje = new Mensaje("", "Se insertaron <b>" + contador + "</b> conjuntos.", "mdi-checkbox-marked-circle-outline", "success");
            }

        } catch (SQLException ex) {
            log.info(ex.getMessage());
            mensaje = new Mensaje("", ex.getMessage(), "mdi-close-circle-outline", "danger");
        }
        return mensaje;
    }

    public Mensaje insertaraprospectos(String fecha) throws Exception {

        Mensaje validoinsercion;
        try {
            this.actualizafbleads(fecha);
            this.validafbleads(fecha);
            this.validaprospectos(fecha);

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("insert into prospectos(\n"
                    + "CODIGO, FECHA_PROSPECTO, FECHA_INGRESO, NOMBREAPELLIDO, TELEFONO1, CORREO, ORIGEN, REITERASOLICITUD, ENCOLA) \n"
                    + "select id, created_time, fecha_insert, full_name, phone_number, email, \n"
                    + "'fb', repite, 'FALSE' from fbleads \n"
                    + "where fecha_insert = '" + fecha + "'\n"
                    + "and repite = 'FALSE'");
            declaracion.executeUpdate();
            validoinsercion = new Mensaje("", "Prospectos Insertados.", "mdi-checkbox-marked-circle-outline", "success");

        } catch (Exception e) {
            log.info(e.getMessage());
            validoinsercion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");

        } finally {
            this.Cancelar();
        }
        return validoinsercion;
    }

    public void validaprospectos(String fecha) throws Exception {
        List<Fbleads> lista;
        ResultSet resultado;
        ResultSet resultado2;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT created_time, id, email, phone_number FROM fbleads "
                    + "WHERE email in (SELECT correo FROM prospectos) "
                    + "and email is not null "
                    + "and fecha_insert ='" + fecha + "'");

            PreparedStatement declaracion2 = this.getConexion().prepareStatement(""
                    + "SELECT created_time, id, email, phone_number FROM fbleads "
                    + "WHERE RIGHT(phone_number,9) in (SELECT RIGHT(telefono1,9) from prospectos) "
                    + "and phone_number is not null "
                    + "and fecha_insert ='" + fecha + "'");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Fbleads solicitantes = new Fbleads();
                solicitantes.setCreated_time(resultado.getString("created_time"));
                solicitantes.setId(resultado.getString("id"));
                solicitantes.setEmail(resultado.getString("email"));
                solicitantes.setPhone_number(resultado.getString("phone_number"));
                solicitantes.setIs_organic("FALSE");
                lista.add(solicitantes);
            }

            resultado2 = declaracion2.executeQuery();
            while (resultado2.next()) {
                Fbleads solicitantes = new Fbleads();
                solicitantes.setCreated_time(resultado2.getString("created_time"));
                solicitantes.setId(resultado2.getString("id"));
                solicitantes.setEmail(resultado2.getString("email"));
                solicitantes.setPhone_number(resultado2.getString("phone_number"));
                solicitantes.setIs_organic("FALSE");

                if (lista.contains(solicitantes) == false) {
                    lista.add(solicitantes);
                }

            }

            if (!lista.isEmpty()) {
                // this.reiterasolicitudleads(lista, fecha);
                this.reiterasolicitudprospectosmail(lista, fecha);
                this.reiterasolicitudprospectosphone(lista, fecha);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        // return lista;
    }

    public void validafbleads(String fecha) throws Exception {
        // this.validafbleadsemailold(fecha);
        // this.validafbleadsphoneold(fecha);
        this.validafbleadsemailnew(fecha);
        this.validafbleadsphonenew(fecha);
    }

    /*  
    public void validafbleadsemailold(String fecha) throws Exception {
        List<Fbleads> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT created_time, id, email, phone_number FROM fbleads "
                    + "where fecha_insert not like '" + fecha + "' "
                    + "and email in (select email from fbleads where fecha_insert like '" + fecha + "')");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Fbleads solicitantes = new Fbleads();
                solicitantes.setCreated_time(resultado.getString("created_time"));
                solicitantes.setId(resultado.getString("id"));
                solicitantes.setEmail(resultado.getString("email"));
                solicitantes.setIs_organic("FALSE");
                lista.add(solicitantes);
            }

            if (!lista.isEmpty()) {
                this.reiterasolicitudleads(lista, fecha);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
    }
    
    public void validafbleadsphoneold(String fecha) throws Exception {
        List<Fbleads> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT created_time, id, email, phone_number FROM fbleads "
                    + "where fecha_insert not like '" + fecha + "' "
                    + "and phone_number in (select phone_number from fbleads where fecha_insert like '" + fecha + "')");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Fbleads solicitantes = new Fbleads();
                solicitantes.setCreated_time(resultado.getString("created_time"));
                solicitantes.setId(resultado.getString("id"));
                solicitantes.setEmail(resultado.getString("email"));
                solicitantes.setIs_organic("FALSE");
                lista.add(solicitantes);
            }

            if (!lista.isEmpty()) {
                this.reiterasolicitudleads(lista, fecha);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        // return lista;
    }    */
    public void validafbleadsemailnew(String fecha) throws Exception {
        List<Fbleads> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT created_time, id, email, phone_number FROM fbleads "
                    + "where fecha_insert like '" + fecha + "' "
                    + "and email in (select email from fbleads where fecha_insert not like '" + fecha + "')");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Fbleads solicitantes = new Fbleads();
                solicitantes.setCreated_time(resultado.getString("created_time"));
                solicitantes.setId(resultado.getString("id"));
                solicitantes.setEmail(resultado.getString("email"));
                solicitantes.setIs_organic("FALSE");
                lista.add(solicitantes);
            }

            if (!lista.isEmpty()) {
                this.reiterasolicitudleadsemail(lista, fecha);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        // return lista;
    }

    public void validafbleadsphonenew(String fecha) throws Exception {
        List<Fbleads> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT created_time, id, email, phone_number FROM fbleads "
                    + "where fecha_insert like '" + fecha + "' "
                    + "and RIGHT(phone_number,9) in (select RIGHT(phone_number,9) from fbleads where fecha_insert not like '" + fecha + "')");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Fbleads solicitantes = new Fbleads();
                solicitantes.setCreated_time(resultado.getString("created_time"));
                solicitantes.setId(resultado.getString("id"));
                solicitantes.setEmail(resultado.getString("email"));
                solicitantes.setIs_organic("FALSE");
                lista.add(solicitantes);
            }

            if (!lista.isEmpty()) {
                this.reiterasolicitudleadstelefono(lista, fecha);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        // return lista;
    }

    public void prospectoscargados() throws Exception {
        List<Fbleads> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT created_time, id, email, phone_number FROM fbleads ");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Fbleads solicitantes = new Fbleads();
                solicitantes.setCreated_time(resultado.getString("created_time"));
                solicitantes.setId(resultado.getString("id"));
                solicitantes.setEmail(resultado.getString("email"));
                solicitantes.setIs_organic("FALSE");
                lista.add(solicitantes);
            }
            /*
            if (!lista.isEmpty()) {
                this.reiterasolicitudleadstelefono(lista, fecha);
            }
             */
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        // return lista;
    }

    public void reiterasolicitudleadsemail(List<Fbleads> leads, String fecha) throws Exception {

        StringBuilder queryvariables = new StringBuilder("");

        StringBuilder queryleads = new StringBuilder("UPDATE fbleads set repite = ?  WHERE email in (");

        for (int i = 0; i < leads.size(); i++) {
            queryvariables = i < (leads.size() - 1) ? queryvariables.append("?,") : queryvariables.append("?)");
        }

        queryleads.append(queryvariables);
        PreparedStatement actualizaleads = this.getConexion().prepareStatement(queryleads.toString());

        int i = 2;
        actualizaleads.setString(1, "TRUE");
        for (Fbleads lead : leads) {
            actualizaleads.setString(i, lead.getEmail());
            i++;
        }

        actualizaleads.executeUpdate();

    }

    public void reiterasolicitudleadstelefono(List<Fbleads> leads, String fecha) throws Exception {

        StringBuilder queryvariables = new StringBuilder("");

        StringBuilder queryleads = new StringBuilder("UPDATE fbleads set repite = ?  WHERE phone_number in (");

        for (int i = 0; i < leads.size(); i++) {
            queryvariables = i < (leads.size() - 1) ? queryvariables.append("?,") : queryvariables.append("?)");
        }

        queryleads.append(queryvariables);
        PreparedStatement actualizaleads = this.getConexion().prepareStatement(queryleads.toString());

        int i = 2;
        actualizaleads.setString(1, "TRUE");
        for (Fbleads lead : leads) {
            actualizaleads.setString(i, lead.getPhone_number());
            i++;
        }

        actualizaleads.executeUpdate();

    }

    public void reiterasolicitudprospectosmail(List<Fbleads> leads, String fecha) throws Exception {

        PreparedStatement actualizaprospectos = this.getConexion().prepareStatement("UPDATE prospectos set REITERASOLICITUD = ?,  FECHAREITERA = ? WHERE CORREO = ?");
        for (Fbleads lead : leads) {
            if (lead.getEmail().isEmpty() == false) {
                actualizaprospectos.setString(1, "TRUE");
                actualizaprospectos.setString(2, lead.getCreated_time());
                actualizaprospectos.setString(3, lead.getEmail());
                actualizaprospectos.executeUpdate();
            }
        }

    }

    public void reiterasolicitudprospectosphone(List<Fbleads> leads, String fecha) throws Exception {
        try {
            this.Conectar();
            PreparedStatement actualizaprospectosnumber = this.getConexion().prepareStatement("UPDATE prospectos set REITERASOLICITUD = ?,  FECHAREITERA = ? WHERE TELEFONO1 = ?");
            for (Fbleads lead : leads) {
                if (lead.getPhone_number().isEmpty() == false) {
                    actualizaprospectosnumber.setString(1, "TRUE");
                    actualizaprospectosnumber.setString(2, lead.getCreated_time());
                    actualizaprospectosnumber.setString(3, lead.getPhone_number());
                    actualizaprospectosnumber.executeUpdate();
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            this.Cancelar();
        }
    }
    /*
    public void reiterarsolicitud(List<Fbleads> leads, String fecha) throws Exception {
        
            StringBuilder queryvariables = new StringBuilder("");
            //StringBuilder querygestion = new StringBuilder("UPDATE gestion set FECHA_DESCARTE = ? WHERE COD_PROSPECTO in (");

            StringBuilder queryleads = new StringBuilder("UPDATE fbleads set repite = ?  WHERE id in (");
            // StringBuilder queryleadsphone = new StringBuilder("UPDATE fbleads set repite = ?  WHERE phone_number in (");

            for (int i = 0; i < leads.size(); i++) {
                queryvariables = i < (leads.size() - 1) ? queryvariables.append("?,") : queryvariables.append("?)");
            }

            // querygestion.append(queryvariables);
            queryleads.append(queryvariables);
            PreparedStatement actualizaleads = this.getConexion().prepareStatement(queryleads.toString());

            int i = 2;
            //actualizagestion.setString(1, fecha);
            actualizaleads.setString(1, "TRUE");
            //actualizaleadsphone.setString(1, "TRUE");
            for (Fbleads lead : leads) {
                //actualizagestion.setString(i, lead.getId());
                actualizaleads.setString(i, lead.getId());
                //actualizaleadsphone.setString(i,lead.getPhone_number());
                i++;
            }

            //actualizagestion.executeUpdate();
            actualizaleads.executeUpdate();
            //actualizaleadsphone.executeUpdate();

            //queryleadsphone.append(queryvariables);
            PreparedStatement actualizaprospectos = this.getConexion().prepareStatement("UPDATE prospectos set REITERASOLICITUD = ?,  FECHAREITERA = ? WHERE CORREO = ?");
            PreparedStatement actualizaprospectosnumber = this.getConexion().prepareStatement("UPDATE prospectos set REITERASOLICITUD = ?,  FECHAREITERA = ? WHERE TELEFONO1 = ?");
            //PreparedStatement actualizagestion = this.getConexion().prepareStatement(querygestion.toString());

            //PreparedStatement actualizaleadsphone = this.getConexion().prepareStatement(queryleadsphone.toString());
            for (Fbleads lead : leads) {

                if (lead.getEmail().isEmpty() == false) {
                    actualizaprospectos.setString(1, "TRUE");
                    actualizaprospectos.setString(2, lead.getCreated_time());
                    actualizaprospectos.setString(3, lead.getEmail());
                    actualizaprospectos.executeUpdate();
                }

                if (lead.getPhone_number().isEmpty() == false) {
                    actualizaprospectosnumber.setString(1, "TRUE");
                    actualizaprospectosnumber.setString(2, lead.getCreated_time());
                    actualizaprospectosnumber.setString(3, lead.getPhone_number());
                    actualizaprospectosnumber.executeUpdate();
                }

            }

       
    }*/

}
