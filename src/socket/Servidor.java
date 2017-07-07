/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import graficos.WaitCon;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Dougl
 */
public class Servidor {
    ServerSocket SerSckt;
    OutputStream Sout;
    InputStream Sin;
    Socket Sckt1;
    DataOutputStream Salida;
    DataInputStream Entrada;
    private Thread T1;
    
    public Servidor() throws IOException {
        SerSckt = new ServerSocket(1254);
    }
    
    public void waitCon() throws IOException{
        //WaitCon WC= new WaitCon();

        int Resp;
        System.out.println("esperando conexion...");
        Sckt1=SerSckt.accept(); // Wait and accept a connection
        //canalaes de lectura y escritura
        System.out.println("Aceptado");
        Sout = Sckt1.getOutputStream();
        Sin = Sckt1.getInputStream();
        Salida = new DataOutputStream (Sout);
        Entrada = new DataInputStream(Sin);
        Resp=JOptionPane.showConfirmDialog(null, "Desea Aceptar la conexion?", "Conexion encontrada", JOptionPane.YES_NO_OPTION);
        if(Resp == 0){
            escribir("GO");
        }else{
            escribir("NOT");
        }
        closeCon();
    }
    
    public void closeCon() throws IOException{
        Entrada.close();
        Salida.close();
        Sout.close();
        Sckt1.close();
    }
    
    public String leer(String msj) throws InterruptedException, IOException{
        System.out.println(msj);
        while(Entrada.available() == 0){
            Thread.sleep(10);
        }
        String cadenaRecibida = new String (Entrada.readUTF());
        return cadenaRecibida;
    }
    
    public void escribir(String cadena) throws IOException{
        Salida.writeUTF(cadena);
    }
}
