
package database;

import datos.Investigador;
import datos.LoginFail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DBQuery {
    
    private Connection con;
    private PostgresqlConexion conexion;
    
    
    public DBQuery(){
        conexion = new PostgresqlConexion();
    }

    public boolean VerificarAdmin(String Nick, String Pass){
        boolean Confirmacion=true;
        try{
            con= conexion.abrirConexion();
            
            String Query="SELECT * FROM Administrador";
            PreparedStatement PQuery= con.prepareStatement(Query);
            ResultSet RS=PQuery.executeQuery();
            
            
            /*System.out.println(RS.getString("nickadmin"));
            System.out.println(RS.getString("pass"));
            System.out.println(Nick);
            System.out.println(Pass);*/
            
            while(RS.next()){
                if(RS.getString("nickadmin").equals(Nick) && RS.getString("pass").equals(Pass)){
                    Confirmacion =true;
                    break;
                }else{
                    Confirmacion= false;
                    break;
                }
            }
            
            
            
            PQuery.close();
            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
            return false;
        }
        
        return Confirmacion;
    }
    
    public Investigador BuscarInv(String Cod){
        Investigador InvAux=null;
        try{
            con= conexion.abrirConexion();
                       
            String Query="SELECT R.IdResearcher, R.nombres,R.apellidos, R.pass, C.CategoryName FROM "
                    + "RESEARCHER as R, Category as C WHERE R.IdResearcher = ? AND R.IdCat= C.IdCat";
            PreparedStatement PQuery= con.prepareStatement(Query);
            PQuery.setString(1, Cod);
            ResultSet RS=PQuery.executeQuery(); 
                        
            while (RS.next()){
                if(RS.getString("IdResearcher").equals(Cod)){
                
                    InvAux= new Investigador(RS.getString("IdResearcher"),RS.getString("nombres"),RS.getString("apellidos"),
                            RS.getString("pass"), RS.getString("CategoryName"));
                    PQuery.close();
                    conexion.cerrarConexion(con);
                    return InvAux;
                }else{
                    PQuery.close();
                    conexion.cerrarConexion(con);
                    return null;
                }
            }
            
            
            
           } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
            return null;
        }
        
        
        return InvAux;
    }
    
    public void AgregarInv(Investigador Inv){
        try{
            con=conexion.abrirConexion();
            String Query="INSERT INTO RESEARCHER (idresearcher, idcat, nombres, apellidos, pass) values (?,?,?,?,?);";
            PreparedStatement PS= con.prepareStatement(Query);
            PS.setString(1, Inv.getCod());
            PS.setInt(2, Inv.getCatId());
            PS.setString(3, Inv.getNombre());
            PS.setString(4, Inv.getApellido());
            PS.setString(5, Inv.getPass());
            PS.executeUpdate();
            PS.close();
            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
            
        }
    }
    
    public void ModifyInv(Investigador Inv){
        try{
            con=conexion.abrirConexion();
            String Query="UPDATE RESEARCHER SET idcat = ?, nombres = ?, apellidos=?, pass = ? WHERE idresearcher= ?";
            PreparedStatement PS= con.prepareStatement(Query);
            PS.setString(5, Inv.getCod());
            PS.setInt(1, Inv.getCatId());
            PS.setString(2, Inv.getNombre());
            PS.setString(3, Inv.getApellido());
            PS.setString(4, Inv.getPass());
            PS.executeUpdate();
            PS.close();
            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
            
        }
    }
    
    public boolean EliminarInv(String Cod){
        boolean Confirmacion;
        try{
            con=conexion.abrirConexion();
            String Query="DELETE FROM RESEARCHER WHERE idresearcher = ?";
            PreparedStatement PS= con.prepareStatement(Query);
            PS.setString(1, Cod);
            PS.executeUpdate();
            PS.close();
            Confirmacion= true;
            conexion.cerrarConexion(con);
        } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
            return false;
        }
        
        return Confirmacion;
        
        
    }
    
    public ArrayList<Investigador> GetInvestigadores(){
        ArrayList <Investigador> Lista= new ArrayList<>();
        Investigador InvAux;
        try{
            con= conexion.abrirConexion();
                       
            String Query="SELECT R.IdResearcher, R.nombres,R.apellidos, R.pass, C.CategoryName FROM "
                    + "RESEARCHER as R, Category as C WHERE R.IdCat= C.IdCat";
            PreparedStatement PQuery= con.prepareStatement(Query);
            ResultSet RS=PQuery.executeQuery(); 
                        
            while (RS.next()){
                InvAux= new Investigador(RS.getString("IdResearcher"),RS.getString("nombres"),RS.getString("apellidos"),
                            RS.getString("pass"), RS.getString("CategoryName"));
                Lista.add(InvAux);
            }
            
            PQuery.close();
            conexion.cerrarConexion(con);
            
           } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
            return null;
        }
        return Lista;
    }
    public ArrayList<LoginFail> GetLoginFail(){
        ArrayList <LoginFail> Lista= new ArrayList<>();
        LoginFail InvAux;
        try{
            con= conexion.abrirConexion();
                       
            String Query="SELECT * FROM failedloginreg";
            PreparedStatement PQuery= con.prepareStatement(Query);
            ResultSet RS=PQuery.executeQuery(); 
                        
            while (RS.next()){
                InvAux= new LoginFail(RS.getString("idfailedlogin"),RS.getString("usedNick"),RS.getString("usedpass"),
                            RS.getTimestamp("hora"));
                Lista.add(InvAux);
            }
            
            PQuery.close();
            conexion.cerrarConexion(con);
            
           } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
            return null;
        }
        return Lista;
    }
    
    public boolean insertJugador(String Nick, int Puntaje){
        try{
           con = conexion.abrirConexion();
           
           String Query="INSERT INTO jugador(nick,puntaje) VALUES(?,?)";
           PreparedStatement PQuery = con.prepareStatement( Query );
           PQuery.setString(1, Nick);
           PQuery.setInt(2,Puntaje );

           
           
           PQuery.executeUpdate();
           PQuery.close();
           conexion.cerrarConexion(con);
        }catch(SQLException e){
            System.out.println("Aqui esta el error");
            System.out.println("ERROR: "+e.getMessage());
            return false;
        }
        return true;
    }
    
 
    
    
    
}
