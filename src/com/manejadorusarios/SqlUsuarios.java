
package com.manejadorusarios;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SqlUsuarios extends Conexion {
    
    public boolean registrarUsuario(Usuario usuario) {
        
        PreparedStatement ps = null;
        Connection connection = getConexion();
        
       String sql = "INSERT INTO usuarios "
               + "    ( usuario, password, nombre, apellido, email, telefono) " +
               " VALUES (?, ?, ?, ?, ?, ?);";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellido());
            ps.setString(5, usuario.getCorreo());
            ps.setString(6, usuario.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarStatement(ps);
        }
       
        return false;
    }
    
    private void cerrarStatement( PreparedStatement ps) {
          try {
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public ArrayList listaUsuarios(){
         
        ArrayList lista = new ArrayList();
                   
           PreparedStatement ps = null;
        Connection connection = getConexion();
        
       String sql = "SELECT id, usuario, password, nombre, "
               + " apellido, telefono, email"
               + " FROM usuarios;";
       
        try {
            ps = connection.prepareStatement(sql);
            ps.execute();
            
            ResultSet resultSet = ps.getResultSet();
            while(resultSet.next()){
                Usuario usuario = new Usuario();
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setApellido(resultSet.getString("apellido"));
                usuario.setTelefono(resultSet.getString("telefono"));
                usuario.setCorreo(resultSet.getString("email"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setUsuario(resultSet.getString("usuario"));
                usuario.setId(resultSet.getInt("id"));
                
                lista.add(usuario); 
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarStatement(ps);
        }
       
        return lista;
    }
    
     public boolean validarPassword(Usuario usuario) {
        
        PreparedStatement ps = null;
        Connection connection = getConexion();
        
       String sql = "SELECT usuario, nombre, password FROM usuarios "
               + "  WHERE usuario = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.execute();
            
            ResultSet resultSet = ps.getResultSet();
            if(resultSet.next()){
                String password = resultSet.getString("password");
                return password.equals(usuario.getPassword());
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarStatement(ps);
        }
       
        return false;
    }
     
    public boolean usuarioExiste(Usuario usuario) {
        
        PreparedStatement ps = null;
        Connection connection = getConexion();
        
       String sql = "SELECT id, usuario, nombre, password FROM usuarios "
               + "  WHERE usuario = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.execute();
            
            ResultSet resultSet = ps.getResultSet();
            return resultSet.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarStatement(ps);
        }
       
        return false;
    } 
    
     public boolean emailExiste(Usuario usuario) {
        
        PreparedStatement ps = null;
        Connection connection = getConexion();
        
       String sql = "SELECT id, usuario, nombre, password FROM usuarios "
               + "  WHERE email = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getCorreo());
            ps.execute();
            
            ResultSet resultSet = ps.getResultSet();
            return resultSet.next();
            
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarStatement(ps);
        }
       
        return false;
    } 
      
    public boolean eliminarUsuario(Usuario usuario) {
        
        PreparedStatement ps = null;
        Connection connection = getConexion();
        
       String sql = "DELETE FROM usuarios "
               + "  WHERE id = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, usuario.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarStatement(ps);
        }
       
        return false;
    } 
      
    public boolean actualizarUsuario(Usuario usuario) {
        
        PreparedStatement ps = null;
        Connection connection = getConexion();
        
       String sql = "UPDATE usuarios "
               + " set usuario = ?, nombre = ?, apellido = ?, email = ?, telefono = ? " +
               " WHERE id = ?;";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getCorreo());
            ps.setString(5, usuario.getTelefono());
            ps.setInt(6, usuario.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarStatement(ps);
        }
       
        return false;
    }
    
}
