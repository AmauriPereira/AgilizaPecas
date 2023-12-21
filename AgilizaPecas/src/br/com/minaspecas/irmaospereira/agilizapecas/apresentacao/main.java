/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.minaspecas.irmaospereira.agilizapecas.apresentacao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
    // ... outros métodos da classe

    public void executarSelect() {
        // Configurações para a conexão com o banco de dados Firebird
        String url = "jdbc:firebirdsql://localhost/C:/Users/Dinopc/Documents/NetBeansProjects/AgilizaPecas/AgilizaPecas/database/AGILIZA.fdb";
        String usuario = "SYSDBA";
        String senha = "masterkey";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Carrega o driver JDBC
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection(url, usuario, senha);

            // Query SQL de exemplo (substitua pela sua consulta real)
            String sql = "SELECT * FROM ACESSO";

            // Cria um statement preparado
            statement = conexao.prepareStatement(sql);

            // Executa a consulta e obtém o resultado
            resultSet = statement.executeQuery();

            // Processa os resultados
            while (resultSet.next()) {
                // Exemplo: Obtendo valores das colunas
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");

                // Faça o que for necessário com os dados obtidos
                System.out.println("ID: " + id + ", Nome: " + nome);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha os recursos no bloco finally para garantir que sejam fechados, independentemente do que aconteça
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

