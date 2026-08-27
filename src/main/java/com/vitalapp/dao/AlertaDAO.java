package com.vitalapp.dao;

import com.vitalapp.conexion.ConexionBD;
import com.vitalapp.modelo.Alerta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AlertaDAO {

    // INSERTAR ALERTA
    public boolean insertar(Alerta alerta) {

        String sql = "INSERT INTO alertas "
                + "(id_paciente, tipo_alerta, descripcion, fecha_alerta) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, alerta.getIdPaciente());
            sentencia.setString(2, alerta.getTipoAlerta());
            sentencia.setString(3, alerta.getDescripcion());
            sentencia.setTimestamp(
                    4, Timestamp.valueOf(alerta.getFechaAlerta()));

            sentencia.executeUpdate();

            try (ResultSet clavesGeneradas = sentencia.getGeneratedKeys()) {
                if (clavesGeneradas.next()) {
                    alerta.setIdAlerta(clavesGeneradas.getInt(1));
                }
            }

            System.out.println("Alerta registrada correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar alerta.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR ALERTAS
    public List<Alerta> listar() {

        List<Alerta> alertas = new ArrayList<>();

        String sql = "SELECT id_alerta, id_paciente, tipo_alerta, "
                + "descripcion, fecha_alerta "
                + "FROM alertas";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Alerta alerta = new Alerta();

                alerta.setIdAlerta(
                        resultado.getInt("id_alerta"));

                alerta.setIdPaciente(
                        resultado.getInt("id_paciente"));

                alerta.setTipoAlerta(
                        resultado.getString("tipo_alerta"));

                alerta.setDescripcion(
                        resultado.getString("descripcion"));

                alerta.setFechaAlerta(
                        resultado.getTimestamp("fecha_alerta")
                                .toLocalDateTime());

                alertas.add(alerta);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar alertas.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return alertas;
    }

    // BUSCAR ALERTA POR ID
    public Alerta buscarPorId(int idAlerta) {

        String sql = "SELECT id_alerta, id_paciente, tipo_alerta, "
                + "descripcion, fecha_alerta "
                + "FROM alertas "
                + "WHERE id_alerta = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idAlerta);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    Alerta alerta = new Alerta();

                    alerta.setIdAlerta(
                            resultado.getInt("id_alerta"));

                    alerta.setIdPaciente(
                            resultado.getInt("id_paciente"));

                    alerta.setTipoAlerta(
                            resultado.getString("tipo_alerta"));

                    alerta.setDescripcion(
                            resultado.getString("descripcion"));

                    alerta.setFechaAlerta(
                            resultado.getTimestamp("fecha_alerta")
                                    .toLocalDateTime());

                    System.out.println("Alerta encontrada correctamente.");

                    return alerta;
                }
            }

            System.out.println("No se encontró la alerta.");

        } catch (SQLException e) {
            System.out.println("Error al buscar alerta.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return null;
    }

    // ACTUALIZAR ALERTA
    public boolean actualizar(Alerta alerta) {

        String sql = "UPDATE alertas "
                + "SET id_paciente = ?, tipo_alerta = ?, "
                + "descripcion = ?, fecha_alerta = ? "
                + "WHERE id_alerta = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, alerta.getIdPaciente());
            sentencia.setString(2, alerta.getTipoAlerta());
            sentencia.setString(3, alerta.getDescripcion());
            sentencia.setTimestamp(
                    4, Timestamp.valueOf(alerta.getFechaAlerta()));
            sentencia.setInt(5, alerta.getIdAlerta());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Alerta actualizada correctamente.");
                return true;
            }

            System.out.println("No se encontró la alerta.");
            return false;

        } catch (SQLException e) {
            System.out.println("Error al actualizar alerta.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR ALERTA
    public boolean eliminar(int idAlerta) {

        String sql = "DELETE FROM alertas WHERE id_alerta = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idAlerta);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Alerta eliminada correctamente.");
                return true;
            }

            System.out.println("No se encontró la alerta.");
            return false;

        } catch (SQLException e) {
            System.out.println("Error al eliminar alerta.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }
}