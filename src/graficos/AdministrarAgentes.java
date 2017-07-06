/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;
import database.DBQuery;
import java.awt.*;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Carlos
 */
public class AdministrarAgentes extends JFrame{
        private Toolkit T1= Toolkit.getDefaultToolkit();
        private DBQuery DBase= new DBQuery();
    public AdministrarAgentes(){
        JPanel jpTable = new JPanel();
        DefaultTableModel t = new DefaultTableModel(null, getColums());
        Container cp = getContentPane();
        FlowLayout fl = new FlowLayout();
        cp.setLayout(fl);
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("AGREGAR");
        this.setSize(500, 225);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String[] getColums() {
        String columna[] = new String[]{"Codigo","Categoria","Nombre","Apellido","Constraseña"};
        return columna;
    }
}
