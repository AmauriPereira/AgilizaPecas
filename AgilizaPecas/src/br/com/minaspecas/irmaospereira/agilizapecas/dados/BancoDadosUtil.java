package br.com.minaspecas.irmaospereira.agilizapecas.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BancoDadosUtil {

    public void testarConexao() {
        // Configurações para a conexão com o banco de dados Firebird
        String url = "jdbc:firebirdsql://localhost/C:/Users/Dinopc/Documents/NetBeansProjects/AgilizaPecas/AgilizaPecas/database/AGILIZA.fdb";
        String usuario = "SYSDBA";
        String senha = "masterkey";

        Connection conexao = null;

        try {
            // Carrega o driver JDBC
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(url, usuario, senha);

            // Se a conexão for bem-sucedida, exibe uma mensagem
            System.out.println("Conexão com o Firebird estabelecida com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha a conexão no bloco finally para garantir que seja fechada, independentemente do que aconteça
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}