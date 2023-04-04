/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Diego Abreu
 */
public class ConnectionFactory {
        
    public static final String
            DRIVER = "com.mysql.cj.jdbc.Driver",
            URL = "jdbc:mysql://localhost:3306/taskfy",
            USER = "root",
            PASS = "";
    
    // Criar 3 metodos, 1 para abrir coneção com banco de dados e 2 para fehcar
    
    public static Connection getConnection() {
        try{
            Class.forName(DRIVER);  // Carrega o drive do DB para dentro da aplicação
            return DriverManager.getConnection(URL, USER, PASS);    // tentando fazer uma conexão com o DB
        } catch (Exception e){
            throw new RuntimeException("Erro na conexão com o banco de dados ", e);
        }
    }
    
    public static void closeConnection(Connection connection) {
        try{
            if (connection != null)
                connection.close();     // fechando a conexão com o BD
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados ", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement) {
        try{
            if (connection != null)
                connection.close();     // fechando a conexão com o BD
            
            if (statement != null)
                statement.close();      // fechando conexão com formatador de sql
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados ", e);
        }
    }
    
    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try{
            if (connection != null)
                connection.close();     // fechando a conexão com o BD
            
            if (statement != null)
                statement.close();      // fechando conexão com formatador de sql
            
            if (resultSet != null)      // fechando o result com os dados do BD
                resultSet.close();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados ", e);
        }
    }
}
