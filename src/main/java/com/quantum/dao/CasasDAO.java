
package com.quantum.dao;

import com.quantum.modelos.Casas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;

/**
 *
 * @author QUANTUM
 */
public class CasasDAO extends DAO{
     private org.slf4j.Logger log = LoggerFactory.getLogger(CasasDAO.class);
    
    public List<Casas> listar() throws Exception {
        List<Casas> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, MODELO FROM casas");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Casas casas = new Casas();
                casas.setCodigo(resultado.getString("CODIGO"));
                casas.setModelo(resultado.getString("MODELO"));
                lista.add(casas);
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
