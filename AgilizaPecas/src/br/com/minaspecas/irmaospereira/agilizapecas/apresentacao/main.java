package br.com.minaspecas.irmaospereira.agilizapecas.apresentacao;

import br.com.minaspecas.irmaospereira.agilizapecas.dados.BancoDadosUtil;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class main {
   
    public static void main(String[] variavel){ 
    executarSelect();
    //System.out.println(System.getProperty("user.dir"));
    //File ARQUIVO = new File("database/AGILIZA.FBD");
    //System.out.println(ARQUIVO.getAbsolutePath());
    }
    
    private static final String SQL_SELECT_ACESSO = "SELECT * FROM ACESSO";
    
    public static void executarSelect() {
        // Configurações para a conexão com o banco de dados Firebird
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection conexao =null;

        try {
            conexao = BancoDadosUtil.getConnection();

            // Cria um statement preparado
            statement = conexao.prepareStatement(SQL_SELECT_ACESSO);

            // Executa a consulta e obtém o resultado
            resultSet = statement.executeQuery();

            // Processa os resultados
            while (resultSet.next()) {
                // Exemplo: Obtendo valores das colunas
                int id = resultSet.getInt("ID_USUARIO");
                String nome = resultSet.getString("USUARIO");
                String senha = resultSet.getString("SENHA");
                String ativo = resultSet.getString("ATIVO");

                // Faça o que for necessário com os dados obtidos
                System.out.println("ID: " + id + ", Nome: " + nome +", Senha: " + senha+", Ativo: " + ativo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fecha os recursos no bloco finally para garantir que sejam fechados, independentemente do que aconteça
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                }
            }
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                }
            }
        }
    }
}

