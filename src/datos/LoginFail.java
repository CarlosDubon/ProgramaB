/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Timestamp;

/**
 *
 * @author Carlos
 */
public class LoginFail {
    private String IDFail;
    private String UserNick;
    private String UserPass;
    private Timestamp Fecha; 

    public LoginFail(String IDFail,String UserNick, String UserPass, Timestamp Fecha) {
        this.IDFail = IDFail;
        this.UserNick = UserNick;
        this.UserPass = UserPass;
        this.Fecha = Fecha;
    }

    public String getUserNick() {
        return UserNick;
    }

    public String getUserPass() {
        return UserPass;
    }

    public String getIDFail() {
        return IDFail;
    }
    



    /* public Timestamp getFecha() {
    return Fecha;
    }*/
    public String getFecha(){
        String date = ""+Fecha;
        String sdate[] = date.split(" ");
        date = sdate[0];
        return date;
    }
    public String getHora(){
        String date = ""+Fecha;
        String sdate[] = date.split(" ");
        date = sdate[1];
        return date;
    }

    public void setUserNick(String UserNick) {
        this.UserNick = UserNick;
    }

    public void setUserPass(String UserPass) {
        this.UserPass = UserPass;
    }

    public void setFecha(Timestamp Fecha) {
        this.Fecha = Fecha;
    }
    
    
    
}
