/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import database.DBQuery;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.PasswordField;
import javax.swing.*;

/**
 *
 * @author Dougl
 */
public class Login extends JFrame{
    private Toolkit T1= Toolkit.getDefaultToolkit();
    private DBQuery DBase= new DBQuery();
    
    public Login(){
        
        //Panel Principal
        JPanel Panel= new JPanel();
        GridLayout GL= new GridLayout(10, 1, 2, 2);
        Panel.setLayout(GL);
        Panel.add(new JLabel(""));
        Panel.add(new JLabel("USERNAME:"));
        JTextField NickAdmin= new JTextField(10); 
        Panel.add(NickAdmin);
        Panel.add(new JLabel(""));
        Panel.add(new JLabel("PASSWORD:"));
        JPasswordField Pass= new JPasswordField(10);
        Panel.add(Pass);
        Panel.add(new JLabel(""));
        Panel.add(new JLabel(""));
        
        //Boton
        JButton Verificar= new JButton("Login");
        JButton Salir= new JButton("Exit");
        Panel.add(Verificar);
        Panel.add(Salir);
       
        Container cp = getContentPane();
        FlowLayout FL= new FlowLayout();
        cp.setLayout(FL);
        cp.add(Panel, BorderLayout.CENTER);
        
        Verificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String Nick = NickAdmin.getText();
                String PassAux = Pass.getText();
                
                if(DBase.VerificarAdmin(Nick, PassAux)){
                    AdminPanel Ad= new AdminPanel();
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR EN LOS DATOS");
                    NickAdmin.setText("");
                    Pass.setText("");
                }
            }
        });
        
        Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("LOGIN ADMIN");
        this.setSize(250, 375);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
    
}
