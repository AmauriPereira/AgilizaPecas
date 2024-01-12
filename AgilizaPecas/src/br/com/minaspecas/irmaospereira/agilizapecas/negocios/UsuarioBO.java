/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.minaspecas.irmaospereira.agilizapecas.negocios;

import br.com.minaspecas.irmaospereira.agilizapecas.dados.UsuarioDAO;
import br.com.minaspecas.irmaospereira.agilizapecas.entidades.Usuario;
import br.com.minaspecas.irmaospereira.agilizapecas.excessoes.excecaoLogin;
import java.sql.SQLException;

/**
 *
 * @author AMAURI PEREIRA
 */
public class UsuarioBO {
    
    public Usuario pesquisarUsuario (String usuario) throws SQLException, excecaoLogin{
        
        Usuario usuarioLogado = new Usuario();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        usuarioLogado = usuarioDAO.pesquisaUsuario(usuario);
        
        if (usuarioLogado == null) {
            throw new excecaoLogin();
        } else {
            return usuarioLogado;
        }
    }
    
    public Usuario incluirUsuario (Usuario usuario) throws SQLException, excecaoLogin{
        
        Usuario user = new Usuario();
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        usuarioDAO.incluiUsuario(usuario);
        
        if (user == null) {
            throw new excecaoLogin();
        } else {
            return user;
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
    
}
