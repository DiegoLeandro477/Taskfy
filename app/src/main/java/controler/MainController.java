/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import DAO.ProjectControllerDAO;
import DAO.TaskControllerDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import model.Project;
import model.Task;
import util.TaskTableModel;
import view.MainScreen;

/**
 *
 * @author diego
 */
public class MainController extends DefaultListModel<Project> {

    ProjectControllerDAO pDAO = new ProjectControllerDAO();
    TaskControllerDAO tDAO = new TaskControllerDAO();

    private List<Project> projects = new ArrayList<>();
    List<Task> tasks = new ArrayList<>();
    public Integer projectId;

    MainScreen view;

    public MainController(MainScreen view) {
        this.view = view;
        setProjects();
    }
    
    // carregar projetos do BD
    
    public void loadProjects(){
        projects = pDAO.getAll();
    }
    public void loadTasks() {
        tasks = tDAO.getAll(projectId);
    }

    /*public MainController(MainScreen main) {
    this.view = main;
    pc = new ProjectController(this);
    }*/
    public List<Project> getProjects() {
        return projects;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String[] updateListProjects() {
        loadProjects();
        String[] list = new String[projects.size()];

        for (int i = 0; i < projects.size(); i++) {
            list[i] = projects.get(i).getName();
        }

        return list;
    }

    public List<Task> updateListTask() {
        return tasks;
    }

    public void setTasks() {
        loadTasks();
        view.loadTasks(tasks);
    }

    void setProjects() {
        view.loadProjects(updateListProjects());
    }
    


    public boolean isThereSelectProject() {
        
        if (projectId != null)
            return true;
        return false;
    }

    public void setProjectId(int selectedIndex) {
        projectId = projects.get(selectedIndex).getId();
    }
    
    public void update(int rowIndex, int columnIndex, TaskTableModel tasksModel) {

        switch (columnIndex) {
            case 0:
                Task task = tasksModel.getTasks().get(rowIndex);    // Coloca em uma task o objeto da posição 
                tDAO.update(task);
                break;

            case 5:
                break;
        }

    }

    public void delete(int rowAtPoint, int columnAtPoint, TaskTableModel tasksModel) throws SQLException {
        Task task = tasksModel.getTasks().get(rowAtPoint);
        tDAO.removeById(task.getId());
        setTasks();
    }



}
