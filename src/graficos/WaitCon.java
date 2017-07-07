/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Dougl
 */
public class WaitCon extends JFrame{
    private Toolkit T1= Toolkit.getDefaultToolkit();
    public WaitCon WA;

    public WaitCon(){
        
        JPanel Panel= new JPanel();
        GridLayout GL= new GridLayout(3, 1, 2, 2);
        Panel.setLayout(GL);
        
        Panel.add(new JLabel(""));
        Panel.add(new JLabel(""));
        Panel.add(new JLabel("Esperando Peticiones..."));
        
        Container cp = getContentPane();
        FlowLayout FL= new FlowLayout();
        cp.setLayout(FL);
        cp.add(Panel, BorderLayout.CENTER); 
        
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)+135,(int)(T1.getScreenSize().getHeight()/2)-110);
        this.setTitle("ScktServer");
        this.setSize(270, 120);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                      
    }

    
    
}
