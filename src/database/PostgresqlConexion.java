
package database;

import java.sql.*;


public class PostgresqlConexion {   
    

    public void cerrarConexion(Connection con){
        try{
            con.close();
        }
        catch(SQLException e){
            System.out.println("ERROR: "+e.getMessage());
        }
    }
    //pg_hda.conf

    public Connection abrirConexion() {
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://192.168.1.2:5432/parcialfinalPOO17";
        String user = "postgres";
        String password = "root";
        Connection con = null;
        try{
            con = DriverManager.getConnection(connectString, user, password);
        }   
        catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
        }
        finally{
            if(con != null)
                return con;
            else
                return null;
        }
    }
    

}
