package com.vitalapp.dao;

import com.vitalapp.conexion.ConexionBD;
import com.vitalapp.modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    // INSERTAR PACIENTE
    public boolean insertar(Paciente paciente) {

        String sql = "INSERT INTO pacientes (id_usuario, fecha_nacimiento, sexo) "
                   + "VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, paciente.getIdUsuario());
            sentencia.setDate(2, Date.valueOf(paciente.getFechaNacimiento()));
            sentencia.setString(3, paciente.getSexo());

            sentencia.executeUpdate();

            System.out.println("Paciente registrado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar paciente.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR PACIENTES
    public List<Paciente> listar() {

        List<Paciente> pacientes = new ArrayList<>();

        String sql = "SELECT id_paciente, id_usuario, fecha_nacimiento, sexo "
                   + "FROM pacientes";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Paciente paciente = new Paciente();

                paciente.setIdPaciente(resultado.getInt("id_paciente"));
                paciente.setIdUsuario(resultado.getInt("id_usuario"));
                paciente.setFechaNacimiento(
                        resultado.getDate("fecha_nacimiento").toLocalDate()
                );
                paciente.setSexo(resultado.getString("sexo"));

                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar pacientes.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return pacientes;
    }

    // ACTUALIZAR PACIENTE
    public boolean actualizar(Paciente paciente) {

        String sql = "UPDATE pacientes "
                   + "SET id_usuario = ?, fecha_nacimiento = ?, sexo = ? "
                   + "WHERE id_paciente = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, paciente.getIdUsuario());
            sentencia.setDate(2, Date.valueOf(paciente.getFechaNacimiento()));
            sentencia.setString(3, paciente.getSexo());
            sentencia.setInt(4, paciente.getIdPaciente());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Paciente actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el paciente.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar paciente.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }
    // ELIMINAR PACIENTE
public boolean eliminar(int idPaciente) {

    String sql = "DELETE FROM pacientes WHERE id_paciente = ?";

    try (Connection conexion = ConexionBD.conectar();
         PreparedStatement sentencia = conexion.prepareStatement(sql)) {

        sentencia.setInt(1, idPaciente);

        int filasAfectadas = sentencia.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Paciente eliminado correctamente.");
            return true;
        } else {
            System.out.println("No se encontró el paciente.");
            return false;
        }

    } catch (SQLException e) {
        System.out.println("Error al eliminar paciente.");
        System.out.println("Detalle: " + e.getMessage());
        return false;
    }
}
}