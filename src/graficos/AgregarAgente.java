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
public class AgregarAgente extends JFrame{
    private Toolkit T1= Toolkit.getDefaultToolkit();
    private DBQuery DBase= new DBQuery();
    public AgregarAgente(){
        JPanel jpDelete = new JPanel();
        GridLayout gl = new GridLayout(5,2,10,10); 
        JTextField txtCod = new JTextField(12);
        JTextField txtNombre = new JTextField(12);
        JTextField txtApellido = new JTextField(12);
        JTextField txtPass = new JTextField(12);
        JComboBox cbxCategoria = new JComboBox();
        cbxCategoria.addItem("Seleccione uno ...");
        cbxCategoria.addItem("A");
        cbxCategoria.addItem("B");
        cbxCategoria.addItem("C");
        cbxCategoria.addItem("D");
        cbxCategoria.addItem("E");
        
        
        jpDelete.setLayout(gl);
        jpDelete.add(new JLabel("Codigo: "));
        jpDelete.add(txtCod);
        jpDelete.add(new JLabel("Nombre: "));
        jpDelete.add(txtNombre);
        jpDelete.add(new JLabel("Apellido: "));
        jpDelete.add(txtApellido);
        jpDelete.add(new JLabel("Categoria: "));
        jpDelete.add(cbxCategoria);
        jpDelete.add(new JLabel("Contaseña : "));
        jpDelete.add(txtPass);
    
        JPanel jpBotones = new JPanel();
        GridLayout glB = new GridLayout(1,2,5,5);
        JButton btnCancel = new JButton("Cancelar");
        JButton btnAgregar = new JButton("Agregar");
        jpBotones.setLayout(glB);
        jpBotones.add(btnCancel);
        jpBotones.add(btnAgregar);
                
        Container cp = getContentPane();
        FlowLayout fl = new FlowLayout();
        cp.setLayout(fl);
        cp.add(jpDelete);
        cp.add(jpBotones);
        
        btnAgregar.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
                 if("".equals(txtCod.getText()) || txtApellido.getText().equals("")|| txtNombre.getText().equals("")
                         || txtPass.getText().equals("") || cbxCategoria.getSelectedIndex() == 0){
                         JOptionPane.showMessageDialog(null, "FALTAN CAMPOS A LLENAR","ERROR", JOptionPane.ERROR_MESSAGE);
                 }else{
                     if(DBase.BuscarInv(txtCod.getText())== null){
                         Investigador Inv= new Investigador(txtCod.getText(), txtNombre.getText(), txtApellido.getText(), txtPass.getText(), (String) cbxCategoria.getSelectedItem());
                         DBase.AgregarInv(Inv);
                         JOptionPane.showMessageDialog(null, "El investigador ha sido agregado con exito","Informacion", JOptionPane.INFORMATION_MESSAGE);
                         dispose();
                         AdminPanel AP= new AdminPanel();
                     }else{
                         JOptionPane.showMessageDialog(null, "EL CODIGO DE ESE INVESTIGADOR YA EXISTE","ERROR", JOptionPane.ERROR_MESSAGE);
                         txtCod.setText("");
                         txtNombre.setText("");
                         txtApellido.setText("");
                         txtPass.setText("");
                         cbxCategoria.setSelectedIndex(0);
                     }
                 }
             }
         });
        
        btnCancel.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent ae) {
                 dispose();
                 AdminPanel AD= new AdminPanel();
             }
         });
        
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("AGREGAR");
        this.setSize(325, 240);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
}
