
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
    
   
}
