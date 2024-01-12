
package br.com.minaspecas.irmaospereira.agilizapecas.dados;


import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author AMAURI PEREIRA
 */
public class UsuarioDAO {
    private static final String SQL_SELECT_USUARIO = "SELECT * FROM ACESSO WHERE USUARIO LIKE ?";
    private static final String SQL_INSERT_NOVO_USUARIO = "INSERT INTO ACESSO ( ID_USUARIO, USUARIO, SENHA, ATIVO, GERENTE) VALUES (?,?,?,?,?)";
    private static final String SQL_UPDATE_USUARIO_COMPLETO = "UPDATE ACESSO SET USUARIO = ?, SENHA = ?, ATIVO = ?, GERENTE = ? WHERE ID_USUARIO = ?";
    private static final String SQL_UPDATE_USUARIO_PARCIAL = "UPDATE ACESSO SET USUARIO = ?, ATIVO = ?, GERENTE = ? WHERE ID_USUARIO = ?";
    private static final String SQL_GET_GENERATOR_ID_USUARIO = "SELECT GEN_ID(GEN_ACESSO_ID, 1) FROM RDB$DATABASE";
    
    public Usuario pesquisaUsuario (String usuario) throws SQLException{
        
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
                user.setGerente(resultado.getString("GERENTE"));

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
    
    public void incluiUsuario (Usuario user) throws SQLException {
        
        Connection conexao = null;
        PreparedStatement comando = null;
        
        try {
            
            conexao = BancoDadosUtil.getConnection();            
            conexao.setAutoCommit(false);
            comando = conexao.prepareStatement(SQL_INSERT_NOVO_USUARIO);
            
            comando.setInt(1,getGeneratorIdUsuario());
            comando.setString(2, user.getUsuario());
            comando.setString(3, user.getSenha());
            comando.setString(4, user.getAtivo());
            comando.setString(5, user.getGerente());

            comando.executeUpdate();
            conexao.setAutoCommit(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar o dados do usuário: "+e, "Erro ao Atualizar", JOptionPane.ERROR_MESSAGE);
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
    
    public void alteraUsuario(Usuario user) throws SQLException {
        
        Connection conexao = null;
        PreparedStatement comando = null;

        try {
            
            conexao = BancoDadosUtil.getConnection();
            conexao.setAutoCommit(false);
            
            if ("".equals(user.getSenha())) {
                comando = conexao.prepareStatement(SQL_UPDATE_USUARIO_PARCIAL);
                comando.setString(1, user.getUsuario());
                comando.setString(2, user.getAtivo());
                comando.setString(3, user.getGerente());
                comando.setInt(4, user.getIdUsuario());
                
            }else{
                comando = conexao.prepareStatement(SQL_UPDATE_USUARIO_COMPLETO);
                comando.setString(1, user.getUsuario());
                comando.setString(2, user.getSenha());
                comando.setString(3, user.getAtivo());
                comando.setString(4, user.getGerente());
                comando.setInt(5, user.getIdUsuario());
            }            

            
            comando.executeUpdate();
            conexao.setAutoCommit(true);
            
        } catch (Exception e) {
            if (conexao != null) {
                conexao.rollback();
            }
            // throw new RuntimeException(e);

        } finally {
            if (comando != null && !comando.isClosed()) {
                comando.close();
            }
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        }
    }
        
    private int getGeneratorIdUsuario () throws SQLException{
        
        Connection conexao = null;
        PreparedStatement comando = null;
        ResultSet resultado = null;
        Integer ultimoId = null;
                
        try {
            
            conexao = BancoDadosUtil.getConnection();
            
            conexao.setAutoCommit(false);

            comando = conexao.prepareStatement(SQL_GET_GENERATOR_ID_USUARIO);

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
