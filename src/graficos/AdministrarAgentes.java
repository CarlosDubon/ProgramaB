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
import java.util.ArrayList;
import java.util.Iterator;
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
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(450, 70));
        table.setEnabled(false);
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
        cbxCategoria.setEnabled(false);
        
        
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
        
        Repoblar();
        
        Container cp = getContentPane();
        FlowLayout fl = new FlowLayout();
        cp.setLayout(fl);
        cp.add(scrollPane);
        cp.add(jpDelete);
        cp.add(jpBotones);
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
                AdminPanel AP= new AdminPanel();
            }
        });
        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(!txtCod.getText().isEmpty()){
                    Investigador inv= DBase.BuscarInv(txtCod.getText());
                    if(inv != null){
                        txtCod.setEnabled(false);
                        txtNombre.setEnabled(true);
                        txtApellido.setEnabled(true);
                        txtPass.setEnabled(true);
                        cbxCategoria.setEnabled(true);
                        btnBuscar.setEnabled(false);
                        btnEditar.setEnabled(true);
                        
                        txtNombre.setText(inv.getNombre());
                        txtApellido.setText(inv.getApellido());
                        cbxCategoria.setSelectedIndex(inv.getCatId());
                    }else{
                        JOptionPane.showMessageDialog(null, "NO EXISTE EL INVESTIGADOR","ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }else
                    JOptionPane.showMessageDialog(null, "FALTAN CAMPOS A LLENAR","ERROR", JOptionPane.ERROR_MESSAGE);
                
                
            }
        });
        
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                if("".equals(txtCod.getText()) || txtApellido.getText().equals("")|| txtNombre.getText().equals("")
                         || cbxCategoria.getSelectedIndex() == 0){
                         JOptionPane.showMessageDialog(null, "FALTAN CAMPOS A LLENAR","ERROR", JOptionPane.ERROR_MESSAGE);
                 }else{
                    
                    Investigador Inv= new Investigador(txtCod.getText(), txtNombre.getText(), txtApellido.getText(), txtPass.getText(), (String) cbxCategoria.getSelectedItem());
                    DBase.ModifyInv(Inv);
                    JOptionPane.showMessageDialog(null, "El investigador ha sido modificado con exito","Informacion", JOptionPane.INFORMATION_MESSAGE);
                    Repoblar();
                    txtCod.setEnabled(true);
                    txtNombre.setEnabled(false);
                    txtApellido.setEnabled(false);
                    txtPass.setEnabled(false);
                    cbxCategoria.setEnabled(false);
                    btnBuscar.setEnabled(true);
                    btnEditar.setEnabled(false);

                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtPass.setText("");
                    cbxCategoria.setSelectedIndex(0);
                    AdministrarAgentes AG= new AdministrarAgentes();
                    dispose();
                }

            }
        });
        
        this.setLocation(((int)T1.getScreenSize().getWidth()/2)-125,(int)(T1.getScreenSize().getHeight()/2)-200);
        this.setTitle("ADMINISTRAR");
        this.setSize(500, 400);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void Repoblar(){
        ArrayList<Investigador> Lista= DBase.GetInvestigadores();
        Investigador Inv;
        Iterator I= Lista.iterator();
        int filas=table.getRowCount()-1;
        
        
        while(I.hasNext()){
            
            Inv= (Investigador)I.next();
            
            model.addRow(new Object[]{
                Inv.getCod(),
                Inv.getCat(),
                Inv.getNombre(),
                Inv.getApellido(),
                Inv.getPass()
            });
        }
    }



}
