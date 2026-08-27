package com.vitalapp.dao;

import com.vitalapp.conexion.ConexionBD;
import com.vitalapp.modelo.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    // INSERTAR MÉDICO
    public boolean insertar(Medico medico) {

        String sql = "INSERT INTO medicos (id_usuario, especialidad) "
                   + "VALUES (?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, medico.getIdUsuario());
            sentencia.setString(2, medico.getEspecialidad());

            sentencia.executeUpdate();

            try (ResultSet clavesGeneradas = sentencia.getGeneratedKeys()) {
                if (clavesGeneradas.next()) {
                    medico.setIdMedico(clavesGeneradas.getInt(1));
                }
            }

            System.out.println("Médico registrado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar médico.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR MÉDICOS
    public List<Medico> listar() {

        List<Medico> medicos = new ArrayList<>();

        String sql = "SELECT id_medico, id_usuario, especialidad "
                   + "FROM medicos";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Medico medico = new Medico();

                medico.setIdMedico(resultado.getInt("id_medico"));
                medico.setIdUsuario(resultado.getInt("id_usuario"));
                medico.setEspecialidad(
                        resultado.getString("especialidad")
                );

                medicos.add(medico);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar médicos.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return medicos;
    }

    // BUSCAR MÉDICO POR ID
    public Medico buscarPorId(int idMedico) {

        String sql = "SELECT id_medico, id_usuario, especialidad "
                   + "FROM medicos "
                   + "WHERE id_medico = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idMedico);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    Medico medico = new Medico();

                    medico.setIdMedico(
                            resultado.getInt("id_medico")
                    );

                    medico.setIdUsuario(
                            resultado.getInt("id_usuario")
                    );

                    medico.setEspecialidad(
                            resultado.getString("especialidad")
                    );

                    System.out.println("Médico encontrado correctamente.");
                    return medico;
                }
            }

            System.out.println("No se encontró el médico.");

        } catch (SQLException e) {
            System.out.println("Error al buscar médico.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR MÉDICO
    public boolean actualizar(Medico medico) {

        String sql = "UPDATE medicos "
                   + "SET id_usuario = ?, especialidad = ? "
                   + "WHERE id_medico = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, medico.getIdUsuario());
            sentencia.setString(2, medico.getEspecialidad());
            sentencia.setInt(3, medico.getIdMedico());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Médico actualizado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el médico.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar médico.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR MÉDICO
    public boolean eliminar(int idMedico) {

        String sql = "DELETE FROM medicos WHERE id_medico = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idMedico);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Médico eliminado correctamente.");
                return true;
            } else {
                System.out.println("No se encontró el médico.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar médico.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }
}