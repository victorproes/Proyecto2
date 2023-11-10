package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conexion.Conexion;
import com.model.User;

public class UserDAO {
    private List<User> users;

    public UserDAO() {
        users = new ArrayList<>();
    }

 // Método para verificar si un usuario ya existe en la base de datos
    public boolean isUserExists(String usuario) {
        try {
            Connection conn = Conexion.getConnection(); // Utiliza la conexión de la clase Conexion
            String sql = "SELECT usuario FROM usuarios WHERE usuario = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);
            ResultSet resultSet = pstmt.executeQuery();
            boolean resultado = resultSet.next();
            conn.close();
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    
    public void registrarUsuario(String usuario, String contrasena) {
        // Insertar el usuario y la contraseña en la tabla de usuarios
        try {
            Connection conn = Conexion.getConnection(); // Utilizamos la conexión desde la clase Conexion
            String sql = "INSERT INTO usuarios (usuario, contrasena) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena);
            pstmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar la excepción, por ejemplo, mostrar un mensaje de error
        }
    }
    
    public boolean validarCredenciales(String usuario, String contrasena) {
        try {
            Connection conn = Conexion.getConnection(); // Utilizamos la conexión desde la clase Conexion
            String sql = "SELECT usuario, contrasena FROM usuarios WHERE usuario = ? AND contrasena = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, usuario);
            pstmt.setString(2, contrasena);
            ResultSet resultSet = pstmt.executeQuery();
            boolean resultado = resultSet.next();
            conn.close();
            return resultado;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
