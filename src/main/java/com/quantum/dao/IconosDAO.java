
package com.quantum.dao;

import com.quantum.modelos.Iconos;
import com.quantum.modelos.Origenes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class IconosDAO extends DAO {
    
    public List<Iconos> listar() throws Exception {
        List<Iconos> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT ICON FROM ICONS");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Iconos iconos = new Iconos();
                iconos.setIcon(resultado.getString("ICON"));
                lista.add(iconos);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
}
