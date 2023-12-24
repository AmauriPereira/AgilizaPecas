
package br.com.minaspecas.irmaospereira.agilizapecas.negocios;

import br.com.minaspecas.irmaospereira.agilizapecas.dados.LoginDAO;
import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoLogin;
import java.sql.SQLException;

/**
 *
 * @author AMAURI PEREIRA
 */
public class LoginBO {
    
    public Usuario logar(String usuario, String senha) throws SQLException, excecaoLogin{
        
        Usuario usuarioLogado = new Usuario();
        
        LoginDAO LoginDAO = new LoginDAO();
        
        usuarioLogado = LoginDAO.selectUsuario(usuario, senha);
        
        if (usuarioLogado == null) {
            throw new excecaoLogin();
        } else {
            return usuarioLogado;
        }
    }
    
}
