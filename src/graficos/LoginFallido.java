/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;
import database.DBQuery;
import datos.Investigador;
import datos.LoginFail;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
/**
 *
 * @author Carlos
 */
public class LoginFallido extends JFrame {
    private Toolkit T1= Toolkit.getDefaultToolkit();
    private DBQuery DBase= new DBQuery();
    private JTable table;
    private DefaultTableModel model;
    public LoginFallido(){
        model = new DefaultTableModel();
        model.addColumn("ID Fallo");
        model.addColumn("Usuario");
        model.addColumn("Contraseña");
        model.addColumn("Fecha");
        model.addColumn("Hora");
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(450, 140));
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel jpB = new JPanel();
        JButton btnCancel = new JButton("Salir");
        JButton btnActualizar = new JButton("Actualizar");
        jpB.add(btnCancel);
        jpB.add(btnActualizar);
        
        Container cp = getContentPane();
        FlowLayout fl = new FlowLayout();
        cp.setLayout(fl);
        cp.add(scrollPane);
        cp.add(jpB, Container.CENTER_ALIGNMENT);
        
        Repoblar();
        
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("FAILS");
        this.setSize(500, 300);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        btnCancel.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
                 dispose();
                 AdminPanel AD= new AdminPanel();
             }
         });
        
        btnActualizar.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
                 LoginFallido AD= new LoginFallido();
                 dispose();
                 
             }
         });
        
        
    }
    
    private void Repoblar(){
        ArrayList<LoginFail> Lista= DBase.GetLoginFail();
        LoginFail Inv;
        Iterator I= Lista.iterator();
        int filas=table.getRowCount()-1;
        
        
        while(I.hasNext()){
            
            Inv= (LoginFail)I.next();
            model.addRow(new Object[]{
                Inv.getIDFail(),
                Inv.getUserNick(),
                Inv.getUserPass(),
                Inv.getFecha(),
                Inv.getHora()
            });
        }
    }
    
}
