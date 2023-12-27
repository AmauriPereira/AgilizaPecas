
package br.com.minaspecas.irmaospereira.agilizapecas.dados;

import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Amauri Pereira
 */
public class LoginDAO {
    
    private static final String SQL_SELECT_ACESSO = "SELECT ID_USUARIO, USUARIO, SENHA, ATIVO FROM ACESSO WHERE USUARIO LIKE ? AND SENHA LIKE ? AND ATIVO = 'S'";
    private static final String SQL_SELECT_USUARIO = "SELECT * FROM ACESSO WHERE USUARIO LIKE ?";
    private static final String SQL_INSERT_NOVO_USUARIO = "INSERT INTO USUARIO( ID_USUARIO,USUARIO, SENHA, ATIVO)VALUES (?,?,?,?)";
    private static  final String SQL_ULTIMO_ID_USUARIO = "SELECT MAX(ID_USUARIO) FROM USUARIO";
    
    
    public Usuario selectUsuario (String usuario, String senha) throws SQLException{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;
        
        try {
            
            conexao = BancoDadosUtil.getConnection();
            
            conexao.setAutoCommit(false);

            comando = conexao.prepareStatement(SQL_SELECT_ACESSO);
            comando.setString(1, usuario);
            comando.setString(2, senha);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                user = new Usuario();

                user.setIdUsuario(resultado.getInt("ID_USUARIO"));
                user.setUsuario(resultado.getString("USUARIO"));
                user.setSenha(resultado.getString("SENHA"));
                user.setAtivo(resultado.getString("ATIVO"));

            }
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
        return user;
    }
    
    public void insert_novo_usuario (Usuario user) throws SQLException{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        Integer ultimoId = null;
        ResultSet resultado = null;
        
        try {
            conexao = BancoDadosUtil.getConnection();
            comando = conexao.prepareStatement(SQL_ULTIMO_ID_USUARIO);
            resultado = comando.executeQuery();
            
            comando.setString(1, resultado.getString("ID_USUARIO"));
            comando.setString(2, user.getUsuario());
            comando.setString(3, user.getSenha());
            comando.setString(4, user.getAtivo());
            
            comando = conexao.prepareStatement(SQL_INSERT_NOVO_USUARIO);

            comando.execute();
            conexao.commit();
            
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
        
    } 
    
     public Usuario selectBuscaUsuario (String usuario) throws SQLException{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Usuario user = null;
        
        try {
            
            conexao = BancoDadosUtil.getConnection();
            
            conexao.setAutoCommit(false);

            comando = conexao.prepareStatement(SQL_SELECT_USUARIO);
            comando.setString(1, usuario);

            resultado = comando.executeQuery();

            if (resultado.next()) {
                user = new Usuario();

                user.setIdUsuario(resultado.getInt("ID_USUARIO"));
                user.setUsuario(resultado.getString("USUARIO"));
                user.setSenha(resultado.getString("SENHA"));
                user.setAtivo(resultado.getString("ATIVO"));

            }
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
        return user;
    }
    
   
}
