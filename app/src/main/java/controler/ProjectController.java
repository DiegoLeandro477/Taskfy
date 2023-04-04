/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import DAO.ProjectControllerDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Project;
import view.AddProjectScreen;

/**
 *
 * @author diego
 */
public class ProjectController {
    
    private AddProjectScreen view;
    private Project project;
    private ProjectControllerDAO pDAO;

    private MainController mc;
    
    public ProjectController(AddProjectScreen view) {
        this.mc = mc;
        this.view = view;
    }

    public ProjectController(AddProjectScreen view, MainController mc) {
        this.view = view;
        this.mc = mc;
    }

    public void createProject(String name, String description) throws Exception {
        try {
            if (name.length() > 0) {
                if (isThereProject(name)) {
                    view.Message("Esse nome já existe");
                } else {
                    project = new Project(name, description);
                    pDAO = new ProjectControllerDAO();
                    pDAO.save(project);
                    view.Message("Projeto Criado");
                    view.dispose();
                    mc.setProjects();
                }
            }else{
                view.Message("Defina o nome do Projeto");
            }
        } catch (Exception e) {
            Logger.getLogger(AddProjectScreen.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean isThereProject(String text) {
        if (!mc.getProjects().isEmpty()) {
            for (Project p : mc.getProjects()) {
                if (text.equals(p.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
