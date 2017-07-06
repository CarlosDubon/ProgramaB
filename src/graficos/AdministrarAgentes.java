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
        private JTable table;
        private DefaultTableModel model;
    public AdministrarAgentes(){
        model = new DefaultTableModel();
        model.addColumn("Codigo");
        model.addColumn("Categoria");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Contraseña");
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(450, 70));
        JScrollPane scrollPane = new JScrollPane(table);
        
        JPanel jpDelete = new JPanel();
        GridLayout gl = new GridLayout(5,2,10,10); 
        JTextField txtCod = new JTextField(12);
        JTextField txtNombre = new JTextField(12);
        txtNombre.setEnabled(false);
        JTextField txtApellido = new JTextField(12);
        txtApellido.setEnabled(false);
        JTextField txtPass = new JTextField(12);
        txtPass.setEnabled(false);
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
        GridLayout glB = new GridLayout(1,3,5,5);
        JButton btnCancel = new JButton("Cancelar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);
        jpBotones.setLayout(glB);
        jpBotones.add(btnCancel);
        jpBotones.add(btnBuscar);
        jpBotones.add(btnEditar);
        
        Container cp = getContentPane();
        FlowLayout fl = new FlowLayout();
        cp.setLayout(fl);
        cp.add(scrollPane);
        cp.add(jpDelete);
        cp.add(jpBotones);
        
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("ADMINISTRAR");
        this.setSize(500, 400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void rellenarTabla() {
        
    }


}
