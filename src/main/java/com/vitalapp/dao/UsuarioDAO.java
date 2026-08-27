package com.vitalapp.dao;

import com.vitalapp.conexion.ConexionBD;
import com.vitalapp.modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // INSERTAR USUARIO
    public boolean insertar(Usuario usuario) {

        String sql = "INSERT INTO usuarios "
                   + "(nombre, correo, contrasena, rol) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setString(1, usuario.getNombre());
            sentencia.setString(2, usuario.getCorreo());
            sentencia.setString(3, usuario.getContrasena());
            sentencia.setString(4, usuario.getRol());

            sentencia.executeUpdate();

            try (ResultSet clavesGeneradas = sentencia.getGeneratedKeys()) {
                if (clavesGeneradas.next()) {
                    usuario.setIdUsuario(clavesGeneradas.getInt(1));
                }
            }

            System.out.println("Usuario registrado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR USUARIOS
    public List<Usuario> listar() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT id_usuario, nombre, correo, contrasena, rol "
                   + "FROM usuarios";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Usuario usuario = new Usuario();

                usuario.setIdUsuario(resultado.getInt("id_usuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setContrasena(resultado.getString("contrasena"));
                usuario.setRol(resultado.getString("rol"));

                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return usuarios;
    }

    // BUSCAR USUARIO POR ID
    public Usuario buscarPorId(int idUsuario) {

        String sql = "SELECT id_usuario, nombre, correo, contrasena, rol "
                   + "FROM usuarios "
                   + "WHERE id_usuario = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idUsuario);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(resultado.getInt("id_usuario"));
                    usuario.setNombre(resultado.getString("nombre"));
                    usuario.setCorreo(resultado.getString("correo"));
                    usuario.setContrasena(resultado.getString("contrasena"));
                    usuario.setRol(resultado.getString("rol"));

                    System.out.println("Usuario encontrado correctamente.");
                    return usuario;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar usuario.");
            System.out.println("Detalle: " + e.getMessage());
        }

        System.out.println("No se encontró el usuario.");
        return null;
    }

    // ACTUALIZAR USUARIO
    public boolean actualizar(Usuario usuario) {

        String sql = "UPDATE usuarios "
                   + "SET nombre = ?, correo = ?, contrasena = ?, rol = ? "
                   + "WHERE id_usuario = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, usuario.getNombre());
            sentencia.setString(2, usuario.getCorreo());
            sentencia.setString(3, usuario.getContrasena());
            sentencia.setString(4, usuario.getRol());
            sentencia.setInt(5, usuario.getIdUsuario());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el usuario.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR USUARIO
    public boolean eliminar(int idUsuario) {

        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idUsuario);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el usuario.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }
}