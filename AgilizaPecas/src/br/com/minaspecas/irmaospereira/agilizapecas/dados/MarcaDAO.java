/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.minaspecas.irmaospereira.agilizapecas.dados;

import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Marca;
import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excessaoMarcaNaoEncontrada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author AMAURI PEREIRA
 */
public class MarcaDAO {
    
    private static final String SQL_SELECT_MARCA = "SELECT * FROM MARCA WHERE DESCRICAO LIKE ?";
    
    public Marca pesquisaMarca (String marca) throws SQLException{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Marca marcaAux = null;
        
        try {
            
            conexao = BancoDadosUtil.getConnection();
            
            conexao.setAutoCommit(false);

            comando = conexao.prepareStatement(SQL_SELECT_MARCA);
            comando.setString(1, marca);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                marcaAux = new Marca();

                marcaAux.setIdMarca(resultado.getInt("ID_MARCA"));
                marcaAux.setDescricao(resultado.getString("DESCRICAO"));

            }
            conexao.commit();
            conexao.setAutoCommit(true);

        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return marcaAux;
    }
    
}
