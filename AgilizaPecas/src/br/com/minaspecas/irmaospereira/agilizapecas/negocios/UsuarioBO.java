
package br.com.minaspecas.irmaospereira.agilizapecas.negocios;

import br.com.minaspecas.irmaospereira.agilizapecas.dados.UsuarioDAO;
import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoDeletarElemento;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoLogin;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoUsuarioCadastrado;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excessaoUsuarioNaoEncontrado;
import java.sql.SQLException;

/**
 *
 * @author AMAURI PEREIRA
 */
public class UsuarioBO {
    
    public Usuario pesquisarUsuario (String usuario,String codigo) throws SQLException, excessaoUsuarioNaoEncontrado{
        
        Usuario user = new Usuario();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        user = usuarioDAO.pesquisaUsuario(usuario, codigo);
        
        if (user == null) {
            throw new excessaoUsuarioNaoEncontrado();
        } else {
            return user;
        }
    }
    
    public void incluirUsuario (Usuario usuario) throws SQLException, excecaoLogin, excessaoUsuarioNaoEncontrado, excecaoUsuarioCadastrado{
        
        Usuario user = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        user = usuarioDAO.pesquisaUsuario(usuario.getUsuario(),null);
        
        if (user == null) {
            usuarioDAO.incluiUsuario(usuario);
            
        } else {
            throw new excecaoUsuarioCadastrado();
        }
        
    }
    
    public Usuario alterarUsuario (Usuario usuario) throws SQLException, excecaoLogin{
        
        Usuario user = new Usuario();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        usuarioDAO.alteraUsuario(usuario);
        
        if (user == null) {
            throw new excecaoLogin();
        } else {
            return user;
        }
    }
    
    public Usuario excluirUsuario (Usuario usuario) throws SQLException, excecaoLogin, excecaoDeletarElemento{
        
        Usuario user = new Usuario();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        usuarioDAO.deleteUsuario(usuario);
        
        if (user == null) {
            throw new excecaoLogin();
        } else {
            return user;
        }
    }
    
}
