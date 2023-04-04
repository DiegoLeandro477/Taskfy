/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author Diego Leandro
 */
public class ProjectControllerDAO {
    
    public void save(Project project) {
        String sql = "INSERT INTO project ("        // o id é incrementado automaticamente
                + "name,"
                + "description,"
                + "createDate,"
                + "updateDate) "
                + "VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreateDate().getTime()));
            statement.setDate(4, new Date(project.getUpdateDate().getTime()));
            
            // Lançando para o banco o sql modificado corretamente
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar projeto :" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void update(Project project) {
        String sql = "UPDATE project SET "
                + "name = ?,"
                + "description = ?,"
                + "createDate = ?,"
                + "updateDate = ? "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreateDate().getTime()));
            statement.setDate(4, new Date(project.getUpdateDate().getTime()));
            statement.setInt(5, project.getId());
            
            // Executa a sql inserção de dados
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar projeto: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public void removeByid(Integer idProject) {
        String sql = "DELETE FROM project WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProject);
            
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar o projeto " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Project> getAll() {
        String sql = "SELECT * FROM project";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        // Armazena o resultado que vem do banco de dados
        ResultSet resultSet = null;
        
        List<Project> projects = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                projects.add(
                        new Project(
                                resultSet.getInt("id"), 
                                resultSet.getString("name"), 
                                resultSet.getString("description"), 
                                resultSet.getDate("createDate"), 
                                resultSet.getDate("updateDate")
                        )
                );

            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar projetos no banco de dados: " + e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        
        return projects;
    }
    
}