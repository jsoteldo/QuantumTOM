
package com.quantum.dao;

import com.quantum.modelos.Asesores;
import com.quantum.modelos.Mensaje;
import com.quantum.modelos.Prospectocrystal;
import com.quantum.servicios.formatoDeFechas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author QUANTUM
 */
public class ProspectocrystalDAO extends DAO {
    
    private formatoDeFechas fechas = new formatoDeFechas();
    
    public Mensaje registrar(Prospectocrystal prospecto, Asesores asesor) throws Exception {
         Mensaje validosesion;
         String fechaactual = fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_HORA);
         
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement("INSERT INTO crystalprospec (codigo, nombres, apellidos, ocupacion, "
                    + "edad, edociv, distrito, telefono1, telefono2, horades, horahas, nombrepareja, apellidopareja, ocupareja, edadpareja, "
                    + "telefonopareja, horadespareja, horahaspareja, master, visa, diner, american, fecinsert, user) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            String codigo = this.cantcrystal() + fechas.convertirFechaString(new Date(), fechas.FORMATO_FECHA_MUESTRA).replace("-", "").substring(0, 4);
            declaracion.setString(1,codigo);
            declaracion.setString(2,prospecto.getNombres());
            declaracion.setString(3,prospecto.getApellidos());
            declaracion.setString(4,prospecto.getOcupacion());
            declaracion.setString(5,prospecto.getEdad());
            declaracion.setString(6,prospecto.getEdociv());
            declaracion.setString(7,prospecto.getDistrito());
            declaracion.setString(8,prospecto.getTelefono());
            declaracion.setString(9,prospecto.getTelefono2());
            declaracion.setString(10,prospecto.getHoradesde());
            declaracion.setString(11,prospecto.getHorahasta());
            declaracion.setString(12,prospecto.getNombrespareja());
            declaracion.setString(13,prospecto.getApellidospareja());
            declaracion.setString(14,prospecto.getOcupacionpareja());
            declaracion.setString(15,prospecto.getEdadpareja());
            declaracion.setString(16,prospecto.getTelefonopareja());
            declaracion.setString(17,prospecto.getHoradesdepareja());
            declaracion.setString(18,prospecto.getHorahastapareja());
            declaracion.setString(19,prospecto.getMaster());
            declaracion.setString(20,prospecto.getVisa());
            declaracion.setString(21,prospecto.getDiner());
            declaracion.setString(22,prospecto.getAmerican());
            declaracion.setString(23,fechaactual);
            declaracion.setString(24,asesor.getCorreo());
            
            declaracion.executeUpdate();
            validosesion = new Mensaje("", "Registrado Exitosamente.","mdi-checkbox-marked-circle-outline","success");
            return validosesion;
        } catch (Exception e) {
            validosesion = new Mensaje("", e.getMessage(),"mdi-close-circle-outline","danger");
            return validosesion;
        } finally {
            this.Cancelar();
        }
        
    }
    
    public int cantcrystal() throws Exception {
        int cant = 0;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "select count(*) + 1 from crystalprospec");
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
    
    
    
}
