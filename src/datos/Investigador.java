/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Dougl
 */
public class Investigador {
    private String Cod;
    private String Nombre;
    private String Apellido;
    private String Pass;

    public Investigador(String Cod, String Nombre, String Apellido, String Pass) {
        this.Cod = Cod;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Pass = Pass;
    }

    public String getCod() {
        return Cod;
    }

    public void setCod(String Cod) {
        this.Cod = Cod;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
    
    
}
