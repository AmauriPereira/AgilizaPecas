package br.com.minaspecas.irmaospereira.agilizapecas.dados;

import java.sql.Connection;
import java.sql.DriverManager;

public class BancoDadosUtil {
    
    private static final String ARQUIVO = "\\database\\AGILIZA.FDB";
    private static final String DRIVER = "org.firebirdsql.jdbc.FBDriver";

    private static final String URL = "jdbc:firebirdsql://localhost/" + System.getProperty("user.dir") + ARQUIVO;
    private static final String USUARIO = "SYSDBA";
    private static final String SENHA = "masterkey";
    
    public static Connection getConnection() {
        // Configurações para a conexão com o banco de dados Firebird        
        Connection connection = null;
        
        try {

            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USUARIO,SENHA);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        return connection;

    }
}