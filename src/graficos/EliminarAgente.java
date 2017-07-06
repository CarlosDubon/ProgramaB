/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import database.DBQuery;
import datos.Investigador;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Carlos
 */
public class EliminarAgente extends JFrame{
    private Toolkit T1= Toolkit.getDefaultToolkit();
    private DBQuery DBase= new DBQuery();
    private Investigador Inv;
    public EliminarAgente(){
        JPanel jpDelete = new JPanel();
        GridLayout gl = new GridLayout(6,2,10,10); 
        JTextField txtBuscar = new JTextField(12);
        JTextField txtNombre = new JTextField(12);
        txtNombre.setEnabled(false);
        JTextField txtApellido = new JTextField(12);
        txtApellido.setEnabled(false);
        JTextField txtPass = new JTextField(12);
        txtPass.setEnabled(false);
        JTextField txtCategoria = new JTextField(6);
        txtCategoria.setEnabled(false);
        
        jpDelete.setLayout(gl);
        jpDelete.add(new JLabel("Buscar: "));
        jpDelete.add(txtBuscar);
        jpDelete.add(new JLabel("Nombre: "));
        jpDelete.add(txtNombre);
        jpDelete.add(new JLabel("Apellido: "));
        jpDelete.add(txtApellido);
        jpDelete.add(new JLabel("Categoria: "));
        jpDelete.add(txtCategoria);
        jpDelete.add(new JLabel("Contaseña : "));
        jpDelete.add(txtPass);
        jpDelete.add(new JLabel(""));
    
        JPanel jpBotones = new JPanel();
        GridLayout glB = new GridLayout(1,3,5,5);
        JButton btnCancel = new JButton("Cancelar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        jpBotones.setLayout(glB);
        jpBotones.add(btnCancel);
        jpBotones.add(btnBuscar);
        jpBotones.add(btnEliminar);
                
        Container cp = getContentPane();
        FlowLayout fl = new FlowLayout();
        cp.setLayout(fl);
        cp.add(jpDelete);
        cp.add(jpBotones);
        
        btnCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                AdminPanel AD= new AdminPanel();
            }
            
        });
        
        btnBuscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                Inv= DBase.BuscarInv(txtBuscar.getText());
                if(Inv == null){
                    JOptionPane.showMessageDialog(null, "NO EXISTE EL INVESTIGADOR","ERROR", JOptionPane.ERROR_MESSAGE);
                    txtBuscar.setText("");
                }else{
                    txtNombre.setText(Inv.getNombre());
                    txtCategoria.setText(Inv.getCat());
                    txtApellido.setText(Inv.getApellido());
                    txtPass.setText(Inv.getPass());
                    txtBuscar.setEditable(false);
                    btnEliminar.setEnabled(true);
                    btnBuscar.setEnabled(false);
                }
            }
            
        });
        
        btnEliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                DBase.EliminarInv(Inv.getCod());
                JOptionPane.showMessageDialog(null, "Ha sido eliminado con exito","Informacion", JOptionPane.OK_OPTION);
                dispose();
                AdminPanel AD= new AdminPanel();
            }
            
        });
        
        
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("ELIMINAR");
        this.setSize(325, 250);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
