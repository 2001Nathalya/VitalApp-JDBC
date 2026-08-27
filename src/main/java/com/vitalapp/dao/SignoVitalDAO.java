package com.vitalapp.dao;

import com.vitalapp.conexion.ConexionBD;
import com.vitalapp.modelo.SignoVital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class SignoVitalDAO {

    // INSERTAR SIGNO VITAL
    public boolean insertar(SignoVital signoVital) {

        String sql = "INSERT INTO signos_vitales "
                + "(id_paciente, fecha_registro, frecuencia_cardiaca, "
                + "frecuencia_respiratoria, presion_arterial, temperatura, "
                + "saturacion_oxigeno, glucemia) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            sentencia.setInt(1, signoVital.getIdPaciente());
            sentencia.setTimestamp(
                    2, Timestamp.valueOf(signoVital.getFechaRegistro()));
            sentencia.setInt(3, signoVital.getFrecuenciaCardiaca());
            sentencia.setInt(4, signoVital.getFrecuenciaRespiratoria());
            sentencia.setString(5, signoVital.getPresionArterial());
            sentencia.setDouble(6, signoVital.getTemperatura());
            sentencia.setDouble(7, signoVital.getSaturacionOxigeno());
            sentencia.setDouble(8, signoVital.getGlucemia());

            sentencia.executeUpdate();

            try (ResultSet clavesGeneradas = sentencia.getGeneratedKeys()) {
                if (clavesGeneradas.next()) {
                    signoVital.setIdSigno(clavesGeneradas.getInt(1));
                }
            }

            System.out.println("Signo vital registrado correctamente.");
            return true;

        } catch (SQLException e) {
            System.out.println("Error al registrar signo vital.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // LISTAR SIGNOS VITALES
    public List<SignoVital> listar() {

        List<SignoVital> signosVitales = new ArrayList<>();

        String sql = "SELECT id_signo, id_paciente, fecha_registro, "
                + "frecuencia_cardiaca, frecuencia_respiratoria, "
                + "presion_arterial, temperatura, saturacion_oxigeno, glucemia "
                + "FROM signos_vitales";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                SignoVital signoVital = new SignoVital();

                signoVital.setIdSigno(resultado.getInt("id_signo"));
                signoVital.setIdPaciente(resultado.getInt("id_paciente"));

                signoVital.setFechaRegistro(
                        resultado.getTimestamp("fecha_registro")
                                .toLocalDateTime());

                signoVital.setFrecuenciaCardiaca(
                        resultado.getInt("frecuencia_cardiaca"));

                signoVital.setFrecuenciaRespiratoria(
                        resultado.getInt("frecuencia_respiratoria"));

                signoVital.setPresionArterial(
                        resultado.getString("presion_arterial"));

                signoVital.setTemperatura(
                        resultado.getDouble("temperatura"));

                signoVital.setSaturacionOxigeno(
                        resultado.getDouble("saturacion_oxigeno"));

                signoVital.setGlucemia(
                        resultado.getDouble("glucemia"));

                signosVitales.add(signoVital);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar signos vitales.");
            System.out.println("Detalle: " + e.getMessage());
        }

        return signosVitales;
    }

    // ACTUALIZAR SIGNO VITAL
    public boolean actualizar(SignoVital signoVital) {

        String sql = "UPDATE signos_vitales "
                + "SET id_paciente = ?, fecha_registro = ?, "
                + "frecuencia_cardiaca = ?, frecuencia_respiratoria = ?, "
                + "presion_arterial = ?, temperatura = ?, "
                + "saturacion_oxigeno = ?, glucemia = ? "
                + "WHERE id_signo = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, signoVital.getIdPaciente());

            sentencia.setTimestamp(
                    2, Timestamp.valueOf(signoVital.getFechaRegistro()));

            sentencia.setInt(3, signoVital.getFrecuenciaCardiaca());
            sentencia.setInt(4, signoVital.getFrecuenciaRespiratoria());
            sentencia.setString(5, signoVital.getPresionArterial());
            sentencia.setDouble(6, signoVital.getTemperatura());
            sentencia.setDouble(7, signoVital.getSaturacionOxigeno());
            sentencia.setDouble(8, signoVital.getGlucemia());
            sentencia.setInt(9, signoVital.getIdSigno());

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Signo vital actualizado correctamente.");
                return true;
            }

            System.out.println("No se encontró el signo vital.");
            return false;

        } catch (SQLException e) {
            System.out.println("Error al actualizar signo vital.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }

    // ELIMINAR SIGNO VITAL
    public boolean eliminar(int idSigno) {

        String sql = "DELETE FROM signos_vitales WHERE id_signo = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idSigno);

            int filasAfectadas = sentencia.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Signo vital eliminado correctamente.");
                return true;
            }

            System.out.println("No se encontró el signo vital.");
            return false;

        } catch (SQLException e) {
            System.out.println("Error al eliminar signo vital.");
            System.out.println("Detalle: " + e.getMessage());
            return false;
        }
    }
}