package com.quantum.dao;

import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Menu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author QUANTUM
 */
public class MenuDAO extends DAO {

    public Mensaje registrar(Menu menu) throws Exception {
        Mensaje validosesion;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO menu (NOMBRE, DESCRIPCION, ICO, LINK, TYPE, CODIGO, PADRE, CHECKED) VALUES (?,?,?,?,?,?,?,?)");
            declaracion.setString(1, menu.getNombre());
            declaracion.setString(2, menu.getDescripcion());
            declaracion.setString(3, menu.getIco());
            declaracion.setString(4, menu.getLink());
            declaracion.setString(5, menu.getType());
            declaracion.setString(6, this.consultacodigoMenu(menu.getType(), menu.getPadre()));
            declaracion.setString(7, menu.getPadre());
            declaracion.setString(8, menu.getCheck());
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

    public List<Menu> listarMenu() throws Exception {
        List<Menu> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT NOMBRE, DESCRIPCION, ICO, LINK, TYPE, CODIGO, PADRE, CHECKED FROM menu"
                    + " WHERE TYPE in  ('M','I')");
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Menu item = new Menu();
                item.setNombre(resultado.getString("NOMBRE"));
                item.setDescripcion(resultado.getString("DESCRIPCION"));
                item.setIco(resultado.getString("ICO"));
                item.setLink(resultado.getString("LINK"));
                item.setType(resultado.getString("TYPE"));
                item.setCodigo(resultado.getString("CODIGO"));
                item.setPadre(resultado.getString("PADRE"));
                item.setCheck(resultado.getString("CHECKED"));
                lista.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Menu> listaMenu() throws Exception {
        List<Menu> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT NOMBRE, DESCRIPCION, ICO, LINK, TYPE, CODIGO, PADRE, CHECKED FROM menu"
                    + " WHERE TYPE in  ('M')");
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Menu item = new Menu();
                item.setNombre(resultado.getString("NOMBRE"));
                item.setDescripcion(resultado.getString("DESCRIPCION"));
                item.setIco(resultado.getString("ICO"));
                item.setLink(resultado.getString("LINK"));
                item.setType(resultado.getString("TYPE"));
                item.setCodigo(resultado.getString("CODIGO"));
                item.setPadre(resultado.getString("PADRE"));
                item.setCheck(resultado.getString("CHECKED"));
                lista.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Menu> listarSubmenu() throws Exception {
        List<Menu> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT NOMBRE, DESCRIPCION, ICO, LINK, TYPE, CODIGO, PADRE, CHECKED FROM menu"
                    + " WHERE TYPE not in  ('M','I')");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Menu item = new Menu();
                item.setNombre(resultado.getString("NOMBRE"));
                item.setDescripcion(resultado.getString("DESCRIPCION"));
                item.setIco(resultado.getString("ICO"));
                item.setLink(resultado.getString("LINK"));
                item.setType(resultado.getString("TYPE"));
                item.setCodigo(resultado.getString("CODIGO"));
                item.setPadre(resultado.getString("PADRE"));
                item.setCheck(resultado.getString("CHECKED"));
                lista.add(item);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public void borrar(Menu menu) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "DELETE FROM menu WHERE NOMBRE = ?");
            declaracion.setString(1, menu.getNombre());
            declaracion.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            this.Cancelar();
        }

    }

    public List<Menu> listarMenuAsesor(List<String> permisos) throws Exception {
        List<Menu> lista;
        ResultSet resultado;
        ;
        try {
            this.Conectar();
            StringBuilder query = new StringBuilder("SELECT NOMBRE, DESCRIPCION, ICO, LINK, TYPE, CODIGO, PADRE, CHECKED FROM menu"
                    + " WHERE TYPE in  ('M','I')"
                    + " AND CODIGO IN (");

            for (int i = 0; i < permisos.size(); i++) {
                query = i < (permisos.size() - 1) ? query.append("?,") : query.append("?)");
            }

            PreparedStatement declaracion = this.getConexion().prepareStatement(query.toString());

            int i = 1;
            for (String campos : permisos) {
                declaracion.setString(i++, campos.trim());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Menu item = new Menu();
                item.setNombre(resultado.getString("NOMBRE"));
                item.setDescripcion(resultado.getString("DESCRIPCION"));
                item.setIco(resultado.getString("ICO"));
                item.setLink(/*FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath() +*/ resultado.getString("LINK"));
                item.setType(resultado.getString("TYPE"));
                item.setCodigo(resultado.getString("CODIGO"));
                item.setPadre(resultado.getString("PADRE"));
                item.setCheck(resultado.getString("CHECKED"));
                lista.add(item);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public List<Menu> listarSubmenuAsesor(List<String> permisos) throws Exception {
        List<Menu> lista;
        ResultSet resultado;
        try {
            this.Conectar();

            StringBuilder query = new StringBuilder("SELECT NOMBRE, DESCRIPCION, ICO, LINK, TYPE, CODIGO, PADRE, CHECKED FROM menu"
                    + " WHERE TYPE not in  ('M','I')"
                    + " AND CODIGO IN (");

            for (int i = 0; i < permisos.size(); i++) {
                query = i < (permisos.size() - 1) ? query.append("?,") : query.append("?)");
            }

            PreparedStatement declaracion = this.getConexion().prepareStatement(query.toString());

            int i = 1;
            for (String campos : permisos) {
                declaracion.setString(i++, campos.trim());
            }

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Menu item = new Menu();
                item.setNombre(resultado.getString("NOMBRE"));
                item.setDescripcion(resultado.getString("DESCRIPCION"));
                item.setIco(resultado.getString("ICO"));
                item.setLink(resultado.getString("LINK"));
                item.setType(resultado.getString("TYPE"));
                item.setCodigo(resultado.getString("CODIGO"));
                item.setPadre(resultado.getString("PADRE"));
                item.setCheck(resultado.getString("CHECKED"));
                lista.add(item);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public String consultacodigoMenu(String tipo, String padre) throws Exception {
        System.out.println(tipo + " - " + padre);
        ResultSet resultado;
        String nextcod;
        String codigoanterior = null;
        try {
            PreparedStatement declaracion;
            this.Conectar();
            if (tipo.equals("S")) {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT CODIGO FROM menu "
                        + "WHERE TYPE NOT IN ('M','I') "
                        + "AND PADRE = ?"
                        + "ORDER BY CODIGO DESC");
                declaracion.setString(1, padre);
            } else {
                declaracion = this.getConexion().prepareStatement(""
                        + "SELECT CODIGO FROM menu "
                        + "WHERE TYPE IN ('M','I') "
                        + "ORDER BY CODIGO DESC");
            }
            resultado = declaracion.executeQuery();
            while (resultado.next()) {
                if (resultado.getRow() == 1) {
                    codigoanterior = resultado.getString("CODIGO");
                }
            }
            if(codigoanterior == null){
                codigoanterior=padre;
            }
            nextcod = this.codigo(tipo, codigoanterior);

        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return nextcod;

    }

    public String codigo(String tipo, String ultimocodigo) {
        String codigo = null;
        int tamano = ultimocodigo.length();
        String letras = "";
        String numeros;
        int i = 0;

        if (tipo.equals("S")) {
            if (tamano == 1) {
                String formatString = String.format("%%0%dd", 5);
                String digitos = String.format(formatString,  1);
                codigo = ultimocodigo+ digitos;
            } else {
                boolean bandera;
                do {
                    if (Character.isDigit(ultimocodigo.charAt(i)) == false) {
                        bandera = false;
                        letras += ultimocodigo.charAt(i);
                        i++;
                    } else {
                        bandera = true;
                    }
                } while (bandera == false);
                numeros = ultimocodigo.substring(i, ultimocodigo.length());
                String formatString = String.format("%%0%dd", 5);
                String digitos = String.format(formatString, Integer.parseInt(numeros) + 1);
                codigo = letras + digitos;
            }
        } else {
            codigo = Character.toString((char) (ultimocodigo.charAt(0) + 1));
        }

        return codigo;
    }

}
