/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author Diego Abreu
 */
public class TaskControllerDAO {
    
    public void save(Task task) throws SQLException {
        
        String sql = "INSERT INTO task ("
                + "idProject,"
                + "name,"
                + "description,"
                + "completed,"
                + "nodes,"
                + "deadline,"
                + " createDate,"
                + "updateDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getIsCompleted());
            statement.setString(5, task.getNodes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreateDate().getTime()));
            statement.setDate(8, new Date(task.getUpdateDate().getTime()));
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar a tarefa no BD" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void update(Task task) {
        
        String sql = "UPDATE task SET "
                + "idProject = ?,"
                + "name = ?,"
                + "description = ?,"
                + "nodes = ?,"
                + "completed = ?,"
                + "deadline = ?,"
                + "createDate = ?,"
                + "updateDate = ? "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNodes());
            statement.setBoolean(5, task.getIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreateDate().getTime()));
            statement.setDate(8, new Date(task.getUpdateDate().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar tarefa no BD" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);   // fechando conexões e statement
        }
        
    }
    
    public void removeById(Integer taskId) throws SQLException {
        String sql = "DELETE FROM task WHERE id = ?";   // código sql para deletar a tarefa do banco
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();   // instanciando a conexão do BD
            statement = connection.prepareStatement(sql);  // prepara o comando a ser lançado no BD para evitar "sql injection"
            statement.setInt(1, taskId);    // Setando um valor no primeiro "?" do sql cmo o taskId
            statement.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar a tarefa " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);   // fechando a conexão e o statement
        }
    }
    
    public List<Task> getAll(Integer idProject) {
        String sql = "SELECT * FROM task WHERE idProject = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        // Lista de tarefas que será devolvida quando a chamada do metodo acontecer
        List<Task> tasks = new ArrayList<>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);     // setando o valor do id do projeto no filtro de busca do SQL
            resultSet = statement.executeQuery();   // guarda o registro dos dodos que vem do BD
            
            while (resultSet.next()){   // enquanto ouverem valores a serem percorrido no resultSet
                
                        tasks.add(
                                new Task(
                                    resultSet.getInt("id"),
                                    resultSet.getInt("idProject"),
                                    resultSet.getString("name"), 
                                    resultSet.getString("description"), 
                                    resultSet.getString("nodes"), 
                                    resultSet.getBoolean("completed"),
                                    resultSet.getDate("deadline"),
                                    resultSet.getDate("createDate"),
                                    resultSet.getDate("updateDate")
                                )
                        );
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar as tarefas do BD" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        // Devolvendo list de tasks do BD
        return tasks;
    }
    
}
