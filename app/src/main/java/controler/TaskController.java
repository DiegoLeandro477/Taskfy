/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import DAO.TaskControllerDAO;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Task;
import view.AddTaskScreen;
import view.MainScreen;

/**
 *
 * @author Diego Abreu
 */
public class TaskController {

    private MainController mc;
    private AddTaskScreen view;

    private Task task;
    private TaskControllerDAO tDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public TaskController(AddTaskScreen view, MainController mc) {
        tDAO = new TaskControllerDAO();
        this.mc = mc;
        this.view = view;
    }


    public boolean isThereTask(String text) {

        mc.loadTasks();
        if (mc.tasks.equals(null) && !mc.tasks.isEmpty()) {
            for (Task t : mc.tasks) {
                if (text.equals(t.getName())) {
                    return true;
                }
            }
        }

        return false;
    }

    public void update(Task task,String name, String description, Date deadline, String nodes) {
        task.setName(name);
        task.setDescription(description);
        task.setDeadline(deadline);
        task.setNodes(nodes);
        task.setUpdateDate(new Date());
        
        tDAO.update(task);
        mc.setTasks();
        view.dispose();

    }

    public void createTask(String name, String description, String deadline, String nodes) {
        try {
            if (name.length() > 0) {
                if (isThereTask(name)) {
                    view.Message("Esse nome já existe");
                } else {
                    task = new Task(mc.projectId, name, description, deadline, nodes);
                    tDAO.save(task);
                    mc.setTasks();
                    view.dispose();
                }
            } else {
                view.Message("Defina o nome da Tarefa");
            }
        } catch (Exception ex) {
            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
