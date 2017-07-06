
package database;

import datos.Investigador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DBQuery {
    
    private Connection con;
    private PostgresqlConexion conexion;
    
    
    public DBQuery(){
        conexion = new PostgresqlConexion();
    }

    public boolean VerificarAdmin(String Nick, String Pass){
        boolean Confirmacion;
        try{
            con= conexion.abrirConexion();
            
            String Query="SELECT * FROM Administrador";
            PreparedStatement PQuery= con.prepareStatement(Query);
            ResultSet RS=PQuery.executeQuery();
            
            RS.next();
            /*System.out.println(RS.getString("nickadmin"));
            System.out.println(RS.getString("pass"));
            System.out.println(Nick);
            System.out.println(Pass);*/
            
            if(RS.getString("nickadmin").equals(Nick) && RS.getString("pass").equals(Pass)){
                Confirmacion =true;
            }else{
                Confirmacion= false;
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
        try{
            con= conexion.abrirConexion();
            
            Investigador InvAux;
            
            String Query="SELECT R.IdResearcher, R.nombres,R.apellidos, R.pass, C.CategoryName FROM "
                    + "RESEARCHER as R, Category as C WHERE R.IdResearcher = ? AND R.IdCat= C.IdCat";
            PreparedStatement PQuery= con.prepareStatement(Query);
            PQuery.setString(1, Cod);
            ResultSet RS=PQuery.executeQuery(); 
            
            RS.next();
            
            if(RS.getString("IdResearcher").equals(Cod)){
                
                InvAux= new Investigador(RS.getString("R.IdResearcher"),RS.getString("R.nombres"),RS.getString("R.apellidos"),
                        RS.getString("R.pass"), RS.getString("C.CategoryName"));
                PQuery.close();
                conexion.cerrarConexion(con);
                return InvAux;
            }else{
                PQuery.close();
                conexion.cerrarConexion(con);
                return null;
            }
            
           } catch (SQLException ex) {
            System.out.println("ERROR: "+ex.getMessage());
            return null;
        }
        
        

    }
    
    public boolean EliminarInv(String Cod){
        boolean Confirmacion;
        try{
            
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
