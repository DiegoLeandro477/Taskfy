/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Diego Abreu
 */
public class Project {
    
    private Integer id;
    private String name, description;
    private Date createDate, updateDate;
    
    public Project(){
        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public Project(Integer id, String name, String description, Date createDate, Date updateDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.createDate = new Date();
        this.updateDate = new Date();
        
        // falta id, 
    }

    public Integer getId() {
        return id;
    }
    
    public void setSet(int id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", description=" + description + ", createDate=" + createDate + ", updateDate=" + updateDate + '}';
    }

    
    
    
}
