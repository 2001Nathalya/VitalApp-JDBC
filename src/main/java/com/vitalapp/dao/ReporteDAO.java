package com.vitalapp.dao;

import com.vitalapp.conexion.ConexionBD;
import com.vitalapp.modelo.Reporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReporteDAO {

    // INSERTAR REPORTE
    public boolean insertar(Reporte reporte) {

        String sql = "INSERT INTO reportes "
                   + "(id_paciente, fecha_generacion, descripcion) "
                   + "VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, reporte.getIdPaciente());
            sentencia.setTimestamp(
                    2,
                    Timestamp.valueOf(reporte.getFechaGeneracion())
            );
            sentencia.setString(3, reporte.getDescripcion());

            sentencia.executeUpdate();

            try (ResultSet clavesGeneradas =
                         sentencia.getGeneratedKeys()) {

                if (clavesGeneradas.next()) {
                    reporte.setIdReporte(
                            clavesGeneradas.getInt(1)
                    );
                }
            }

            System.out.println("Reporte registrado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar reporte.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR REPORTES
    public List<Reporte> listar() {

        List<Reporte> reportes = new ArrayList<>();

        String sql = "SELECT id_reporte, id_paciente, "
                   + "fecha_generacion, descripcion "
                   + "FROM reportes";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Reporte reporte = new Reporte();

                reporte.setIdReporte(
                        resultado.getInt("id_reporte")
                );

                reporte.setIdPaciente(
                        resultado.getInt("id_paciente")
                );

                reporte.setFechaGeneracion(
                        resultado.getTimestamp("fecha_generacion")
                                .toLocalDateTime()
                );

                reporte.setDescripcion(
                        resultado.getString("descripcion")
                );

                reportes.add(reporte);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar reportes.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return reportes;
    }

    // BUSCAR REPORTE POR ID
    public Reporte buscarPorId(int idReporte) {

        String sql = "SELECT id_reporte, id_paciente, "
                   + "fecha_generacion, descripcion "
                   + "FROM reportes "
                   + "WHERE id_reporte = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idReporte);

            try (ResultSet resultado = sentencia.executeQuery()) {

                if (resultado.next()) {

                    Reporte reporte = new Reporte();

                    reporte.setIdReporte(
                            resultado.getInt("id_reporte")
                    );

                    reporte.setIdPaciente(
                            resultado.getInt("id_paciente")
                    );

                    reporte.setFechaGeneracion(
                            resultado.getTimestamp("fecha_generacion")
                                    .toLocalDateTime()
                    );

                    reporte.setDescripcion(
                            resultado.getString("descripcion")
                    );

                    System.out.println(
                            "Reporte encontrado correctamente."
                    );

                    return reporte;
                }
            }

            System.out.println("No se encontró el reporte.");
            return null;

        } catch (SQLException e) {
            System.out.println("Error al buscar reporte.");
            System.out.println("Detalle: " + e.getMessage());
            return null;
        }
    }

    // ACTUALIZAR REPORTE
    public boolean actualizar(Reporte reporte) {

        String sql = "UPDATE reportes "
                   + "SET id_paciente = ?, "
                   + "fecha_generacion = ?, "
                   + "descripcion = ? "
                   + "WHERE id_reporte = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, reporte.getIdPaciente());

            sentencia.setTimestamp(
                    2,
                    Timestamp.valueOf(reporte.getFechaGeneracion())
            );

            sentencia.setString(
                    3,
                    reporte.getDescripcion()
            );

            sentencia.setInt(
                    4,
                    reporte.getIdReporte()
            );

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println(
                        "Reporte actualizado correctamente."
                );
                return true;
            }

            System.out.println("No se encontró el reporte.");
            return false;

        } catch (SQLException e) {
            System.out.println("Error al actualizar reporte.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR REPORTE
    public boolean eliminar(int idReporte) {

        String sql =
                "DELETE FROM reportes WHERE id_reporte = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia =
                     conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idReporte);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println(
                        "Reporte eliminado correctamente."
                );
                return true;
            }

            System.out.println("No se encontró el reporte.");
            return false;

        } catch (SQLException e) {
            System.out.println("Error al eliminar reporte.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }
}