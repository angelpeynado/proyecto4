
package com.manejadorusarios;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    private final String instancia = "proyecto_usuarios";
    private final String usuario = "root";
    private final String password = "123456789";
    private final String url = "jdbc:mysql://localhost:3306/" + instancia;
    private Connection connection = null;
    
    public Connection getConexion ()
    {
        try {
            if(connection == null) {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, usuario, password);    
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }         
 
}
