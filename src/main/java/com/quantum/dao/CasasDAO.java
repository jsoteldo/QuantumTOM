
package com.quantum.dao;

import com.quantum.modelos.Casas;
import com.quantum.modelos.Lotes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author QUANTUM
 */
public class CasasDAO extends DAO{
    
    public List<Casas> listar() throws Exception {
        List<Casas> lista;
        ResultSet resultado;
        try {
            this.Conectar();
            PreparedStatement declaracion = this.getConexion().prepareStatement(""
                    + "SELECT CODIGO, MODELO FROM CASAS");
            
            resultado = declaracion.executeQuery();
            lista = new ArrayList<>();
            while(resultado.next()){
                Casas casas = new Casas();
                casas.setCodigo(resultado.getString("CODIGO"));
                casas.setModelo(resultado.getString("MODELO"));
                lista.add(casas);
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cancelar();
        }
        return lista;
    }
    
}   
