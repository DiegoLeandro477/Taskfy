/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Diego Leandro
 */
public class Task {

    private Integer id, idProject;
    private String name, description, nodes;
    private Date createDate, updateDate, deadline;
    private Boolean isCompleted;
    
    public Task(){
        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public Task(Integer id, Integer idProject, String name, String description, String nodes, Boolean isCompleted, Date deadline, Date createDate, Date updateDate) {
        this.id = id;
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.nodes = nodes;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    public Task(Integer idProject, String name, String description, String deadline, String nodes) {
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.deadline = new Date(deadline);
        this.nodes = nodes;
        this.createDate = new Date();
        this.updateDate = new Date();
        this.isCompleted = false;
        // falta   id, idProject
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProject() {
        return idProject;
    }
    
    public void setIdProject(Integer idProject) {
        this.idProject = idProject;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", idProject=" + idProject + ", name=" + name + ", description=" + description + ", nodes=" + nodes + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deadline=" + deadline + ", isCompleted=" + isCompleted + '}';
    }

    

}
