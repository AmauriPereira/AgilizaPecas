/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.minaspecas.irmaospereira.agilizapecas.dados;

import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Marca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMAURI PEREIRA
 */
public class MarcaDAO {
    
    private static final String SQL_SELECT_MARCA = "SELECT * FROM MARCA WHERE NOME LIKE ?";
    private static final String SQL_INSERT_NOVA_MARCA = "INSERT INTO MARCA ( ID_MARCA, NOME) VALUES (?,?)";
    private static final String SQL_UPDATE_MARCA = "UPDATE MARCA SET NOME = ? WHERE ID_MARCA = ?";
    private static final String SQL_GET_GENERATOR_ID_MARCA = "SELECT GEN_ID(GEN_MARCA_ID, 1) FROM RDB$DATABASE";
    
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
                marcaAux.setDescricao(resultado.getString("NOME"));

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
    
    public void incluiMarca (Marca marcaAux) throws SQLException {
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            
            conexao = BancoDadosUtil.getConnection();            
            conexao.setAutoCommit(false);
            comando = conexao.prepareStatement(SQL_INSERT_NOVA_MARCA);
            
            comando.setInt(1,getGeneratorIdMarca());
            comando.setString(2, marcaAux.getDescricao());

            comando.executeUpdate();
            conexao.setAutoCommit(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar marca: "+e, "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
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
    }
    
    private int getGeneratorIdMarca () throws SQLException{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Integer ultimoId = null;
                
        try {
            
            conexao = BancoDadosUtil.getConnection();            
            conexao.setAutoCommit(false);
            comando = conexao.prepareStatement(SQL_GET_GENERATOR_ID_MARCA);

            resultado = comando.executeQuery();
            
            resultado.next();
            ultimoId = resultado.getInt(1);
           
            conexao.commit();
            conexao.setAutoCommit(true);

        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
        return ultimoId;
    }
    
}
