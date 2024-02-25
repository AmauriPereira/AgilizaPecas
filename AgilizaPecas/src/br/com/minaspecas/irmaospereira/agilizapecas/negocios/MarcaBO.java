/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.minaspecas.irmaospereira.agilizapecas.negocios;

import br.com.minaspecas.irmaospereira.agilizapecas.dados.MarcaDAO;
import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Marca;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excessaoMarcaNaoEncontrada;
import java.sql.SQLException;

/**
 *
 * @author AMAURI PEREIRA
 */
public class MarcaBO {
    
    public Marca pesquisarMarca (String marca) throws SQLException{
        
        Marca marcaAux = new Marca();
        
        MarcaDAO marcaDAO = new MarcaDAO();
        
        marcaAux = marcaDAO.pesquisaMarca(marca);
        
        if (marcaAux == null) {
            throw new SQLException();
        } else {
            return marcaAux;
        }
    }
    
    public void incluirMarca (Marca marca) throws SQLException{

        Marca marcaAux = new Marca();
        MarcaDAO marcaDAO = new MarcaDAO();

        //marcaAux = marcaDAO.pesquisaMarca(marca.getDescricao());


        marcaDAO.incluiMarca(marca);
    }
    
}
