package com.quantum.dao;

import com.quantum.modelos.Asesores;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Prospectos;
import com.quantum.servicios.BCrypt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class AsignaProspectosDAO extends DAO {

    private org.slf4j.Logger log = LoggerFactory.getLogger(AsignaProspectosDAO.class);
    
    
    public Mensaje validaUsuario(Asesores asesores) throws Exception {
        Mensaje validosesion = null;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("SELECT CORREO, NOMBRES, APELLIDOS, CONTRASENA, EDAD, SEXO, TELEFONO, TELEFONO_ASIG, FECHARETIRO, ROL) VALUES (?,?,?,?,?,?,?,?,?,?)");
            declaracion.setString(1, asesores.getCorreo());
            resultado = declaracion.executeQuery();
            if (resultado.next()) {
                validosesion = new Mensaje("", "El usuario ya Existe", "mdi-close-circle-outline", "danger");
                return validosesion;
            } else {
                return validosesion;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }

    }

    public Mensaje registrar(Asesores asesores) throws Exception {
        Mensaje validosesion;
        try {

            if (this.validaUsuario(asesores).getMensaje().equals("El usuario ya Existe")) {
                return this.validaUsuario(asesores);
            } else {
                this.Conectar();
                PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO asesores (CORREO, NOMBRES, APELLIDOS, CONTRASENA, EDAD, SEXO, TELEFONO, TELEFONO_ASIG, ROL) VALUES (?,?,?,?,?,?,?,?,?)");
                declaracion.setString(1, asesores.getCorreo());
                declaracion.setString(2, this.hashPassword(asesores.getNombres()));
                declaracion.setString(3, asesores.getApellidos());
                declaracion.setString(4, asesores.getContrasena());
                declaracion.setString(5, asesores.getEdad());
                declaracion.setString(6, asesores.getSexo());
                declaracion.setString(7, asesores.getTelefono());
                declaracion.setString(8, asesores.getTelefono_asig());
                declaracion.setString(9, asesores.getRol());
                declaracion.executeUpdate();
                validosesion = new Mensaje("", "Registrado Exitosamente.", "mdi-checkbox-marked-circle-outline", "success");
                return validosesion;
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            validosesion = new Mensaje("", e.getMessage(), "mdi-close-circle-outline", "danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
    }

    public Mensaje inicio(Asesores asesores) throws Exception {
        Asesores asesorconsultado = null;
        ResultSet resultado;
        Mensaje validosesion;
        Prospectos prospectoconsultado = null;
        FacesContext contex = FacesContext.getCurrentInstance();

        this.Conectar();
        PreparedStatement declaracion = this.getConexion().prepareStatement(""
                + "SELECT CORREO, NOMBRES, APELLIDOS, CONTRASENA, EDAD, SEXO, TELEFONO, TELEFONO_ASIG, FECHARETIRO, ROL "
                + "FROM asesores "
                + "WHERE CORREO like ? "
                + "AND FECHARETIRO is null");
        declaracion.setString(1, asesores.getCorreo());
        resultado = declaracion.executeQuery();
        if (resultado.next()) {
            if (this.checkPassword(this.hashPassword(asesores.getContrasena()), resultado.getString("CONTRASENA"))) {
                validosesion = new Mensaje(true, "", "Constraseña Invalida");
                return validosesion;
            } else {
                asesorconsultado = new Asesores();
                asesorconsultado.setCorreo(resultado.getString("CORREO"));
                asesorconsultado.setContrasena(resultado.getString("CONTRASENA"));
                asesorconsultado.setNombres(resultado.getString("NOMBRES"));
                asesorconsultado.setApellidos(resultado.getString("APELLIDOS"));
                asesorconsultado.setEdad(resultado.getString("EDAD"));
                asesorconsultado.setSexo(resultado.getString("SEXO"));
                asesorconsultado.setTelefono(resultado.getString("TELEFONO"));
                asesorconsultado.setTelefono_asig(resultado.getString("TELEFONO_ASIG"));
                asesorconsultado.setRol(resultado.getString("ROL"));
                validosesion = new Mensaje(false, "none", "", asesorconsultado);
                return validosesion;
            }

        } else {
            validosesion = new Mensaje(true, "", "El Usuario No Existe ");
            return validosesion;
        }

    }

    public List<Asesores> listar() throws Exception {
        List<Asesores> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CORREO, NOMBRES, APELLIDOS, CONTRASENA, EDAD, SEXO, TELEFONO, TELEFONO_ASIG, ROL FROM asesores"
                    + " WHERE FECHARETIRO is NULL");

            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while (resultado.next()) {
                Asesores asesores = new Asesores();
                asesores.setCorreo(resultado.getString("CORREO"));
                asesores.setNombres(resultado.getString("NOMBRES"));
                asesores.setApellidos(resultado.getString("APELLIDOS"));
                asesores.setContrasena(resultado.getString("CONTRASENA"));
                asesores.setEdad(resultado.getString("EDAD"));
                asesores.setSexo(resultado.getString("SEXO"));
                asesores.setTelefono(resultado.getString("TELEFONO"));
                asesores.setTelefono_asig(resultado.getString("TELEFONO_ASIG"));
                asesores.setRol(resultado.getString("ROL"));
                lista.add(asesores);
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }

    public void borrar(Asesores asesor) throws Exception {
        try {

            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "UPDATE asesores set FECHARETIRO = ? WHERE CORREO = ?");
            declaracion.setString(1, asesor.getFecharetiro());
            declaracion.setString(2, asesor.getCorreo());
            declaracion.executeUpdate();
            
        } catch (Exception e) {
            log.info(e.getMessage());
            
        } finally {
            this.Cancelar();
        }

    }

    public Mensaje modificar(Asesores asesores) throws Exception {
        Mensaje validosesion;

        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("UPDATE"
                    + " asesores set NOMBRES = ?, APELLIDOS = ?, "
                    + " EDAD = ?, SEXO = ?, TELEFONO = ?, TELEFONO_ASIG = ?, ROL = ? WHERE CORREO = ?");

            declaracion.setString(1, asesores.getNombres());
            declaracion.setString(2, asesores.getApellidos());
            declaracion.setString(3, asesores.getEdad());
            declaracion.setString(4, asesores.getSexo());
            declaracion.setString(5, asesores.getTelefono());
            declaracion.setString(6, asesores.getTelefono_asig());
            declaracion.setString(7, asesores.getRol());
            declaracion.setString(8, asesores.getCorreo());
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

    public static String hashPassword(String password) {
        int workload = 12;
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password, salt);

        return (hashed_password);
    }

    public static boolean checkPassword(String password, String stored_hash) {
        boolean password_verified = false;

        if (null == stored_hash || !stored_hash.startsWith("$2a$")) {
            throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
        }

        password_verified = BCrypt.checkpw(password, stored_hash);

        return (password_verified);
    }

}
