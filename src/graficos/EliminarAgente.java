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

/**
 *
 * @author Carlos
 */
public class EliminarAgente extends JFrame{
    private Toolkit T1= Toolkit.getDefaultToolkit();
    private DBQuery DBase= new DBQuery();
    public EliminarAgente(){
        JPanel jpDelete = new JPanel();
        GridLayout gl = new GridLayout(5,0,10,10); 
        Container cp = getContentPane();
        FlowLayout fl = new FlowLayout();
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("ELIMINAR");
        this.setSize(250, 375);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
