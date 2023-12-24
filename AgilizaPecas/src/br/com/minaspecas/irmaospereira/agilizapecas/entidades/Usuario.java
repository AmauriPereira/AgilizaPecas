
package br.com.minaspecas.irmaospereira.agilizapecas.entidades;

/**
 *
 * @author Amauri Pereira
 */
public class Usuario {
    private int idUsuario;
    private String usuario;
    private String senha;
    private String ativo;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
      
}
