
package com.quantum.dao;

import com.quantum.modelos.Iconos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class IconosDAO extends DAO {
    
    private Logger log = LoggerFactory.getLogger(IconosDAO.class); 
    public List<Iconos> listar() throws Exception {
        List<Iconos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ICON FROM icons");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Iconos iconos = new Iconos();
                iconos.setIcon(resultado.getString("ICON"));
                lista.add(iconos);
            }
            
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
}
