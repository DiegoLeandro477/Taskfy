/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;
import model.Task;

/**
 *
 * @author diego
 */
public class TaskTableModel extends AbstractTableModel {

    String[] columns = {"v", "Nome", "Descrição", "Prazo", "Editar", "Excluir"};
    List<Task> tasks = new ArrayList();

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int calumnIndex) {
        tasks.get(rowIndex).setIsCompleted((boolean) aValue);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (tasks.isEmpty()) {
            return Object.class;
        }

        return this.getValueAt(0, columnIndex).getClass();
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public int getRowCount() {
        // vai retornar a quantidade de linhas que a tabela deve ter
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        // Retorna a quantidades de colunas que vai ter
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return tasks.get(rowIndex).getIsCompleted();

            case 1:
                return tasks.get(rowIndex).getName();

            case 2:
                return tasks.get(rowIndex).getDescription();

            case 3:
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(tasks.get(rowIndex).getDeadline());

            case 4:
                return "";

            case 5:
                return "";

            default:
        }

        return null;
    }

}
