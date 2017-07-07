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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import socket.Servidor;

/**
 *
 * @author Dougl
 */
public class AdminPanel extends JFrame{
    
    private Toolkit T1= Toolkit.getDefaultToolkit();
    private DBQuery DBase= new DBQuery();
    private Thread Th1;
    protected WaitCon WC;
    protected static boolean isWait=false;
    protected JButton Escuchar;
    
    public AdminPanel(){
              
        JPanel Panel= new JPanel();
        GridLayout GL= new GridLayout(12, 1, 2, 2);
        Panel.setLayout(GL);
        
        JButton AdminInv= new JButton("Administrar Investigadores");
        JButton Eliminar= new JButton("Eliminar Investigador");
        JButton Agregar= new JButton("Agregar Investigador");
        JButton Reg= new JButton("Registros Fallidos");
        Escuchar= new JButton("Escuchar Peticiones");
        JButton CerrarS= new JButton("Cerrar Sesion");
        
        if(isWait){
            Escuchar.setEnabled(false);
        }else{
            Escuchar.setEnabled(true);
        }
        
        Panel.add(new JLabel(""));
        Panel.add(AdminInv);
        Panel.add(new JLabel(""));
        Panel.add(Eliminar);
        Panel.add(new JLabel(""));
        Panel.add(Agregar);
        Panel.add(new JLabel(""));
        Panel.add(Reg);
        Panel.add(new JLabel(""));
        Panel.add(Escuchar);
        Panel.add(new JLabel(""));
        Panel.add(CerrarS);
        
        Container cp = getContentPane();
        FlowLayout FL= new FlowLayout();
        cp.setLayout(FL);
        cp.add(Panel, BorderLayout.CENTER);    
        
        AdminInv.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                AdministrarAgentes EA= new AdministrarAgentes();
            }
        });
        CerrarS.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                Login EA= new Login();
            }
        });
        Eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                EliminarAgente EA= new EliminarAgente();
            }
        });
        
        Agregar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                AgregarAgente EA= new AgregarAgente();
            }
        });
        
        Reg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                LoginFallido EA= new LoginFallido();
            }
        });
        
        Escuchar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                Escuchar.setEnabled(false);
                WC= new WaitCon();
                Th1= new Thread(new Esperar());
                Th1.start();
                
            }
        });
        
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("ADMIN VIEW");
        this.setSize(250, 400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public class Esperar implements Runnable{
        
        private Servidor S1;
        
        
        public Esperar(){
            S1=new Servidor();
        }
        
        @Override
        public void run() {
            isWait=true;
            try {
                
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                S1.waitCon();
                
            } catch (IOException ex) {
                Logger.getLogger(AdminPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            WC.dispose();
            isWait=false;
            Escuchar.setEnabled(true);
        }
        
    }
}
