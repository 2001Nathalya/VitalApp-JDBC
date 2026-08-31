package com.vitalapp;

import com.vitalapp.dao.UsuarioDAO;
import com.vitalapp.modelo.Usuario;

import com.vitalapp.dao.MedicoDAO;
import com.vitalapp.modelo.Medico;

import com.vitalapp.dao.ReporteDAO;
import com.vitalapp.modelo.Reporte;

import com.vitalapp.dao.PacienteDAO;
import com.vitalapp.modelo.Paciente;

import com.vitalapp.dao.SignoVitalDAO;
import com.vitalapp.modelo.SignoVital;

import com.vitalapp.dao.AlertaDAO;
import com.vitalapp.modelo.Alerta;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // =====================================================
        // PRUEBA CRUD USUARIOS
        // =====================================================

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.println("=== PRUEBA CRUD USUARIOS VITALAPP ===");

        Usuario usuario = new Usuario(
                0,
                "Maria Lopez",
                "maria.lopez@gmail.com",
                "123456",
                "Paciente"
        );

        boolean registrado = usuarioDAO.insertar(usuario);

        if (!registrado) {
            System.out.println("No se pudo registrar el usuario.");
            return;
        }

        System.out.println("ID del usuario registrado: "
                + usuario.getIdUsuario());

        System.out.println("\n--- LISTA DE USUARIOS ---");

        List<Usuario> usuarios = usuarioDAO.listar();

        for (Usuario u : usuarios) {
            System.out.println(u);
        }

        System.out.println("\n--- BUSCAR USUARIO POR ID ---");

        Usuario usuarioEncontrado =
                usuarioDAO.buscarPorId(usuario.getIdUsuario());

        if (usuarioEncontrado != null) {
            System.out.println(usuarioEncontrado);
        }

        System.out.println("\n--- ACTUALIZAR USUARIO ---");

        usuario.setNombre("Maria Lopez Actualizada");
        usuario.setRol("Administrador");

        boolean actualizado =
                usuarioDAO.actualizar(usuario);

        if (actualizado) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        System.out.println("\n--- ELIMINAR USUARIO ---");

        boolean eliminado =
                usuarioDAO.eliminar(usuario.getIdUsuario());

        if (eliminado) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar el usuario.");
        }

        System.out.println("\n=== PRUEBA CRUD USUARIOS FINALIZADA ===");


        // =====================================================
        // PRUEBA CRUD MÉDICOS
        // =====================================================

        MedicoDAO medicoDAO = new MedicoDAO();

        System.out.println("\n=== PRUEBA CRUD MÉDICOS VITALAPP ===");

        Medico medico = new Medico(
                0,
                1,
                "Medicina General"
        );

        boolean registradoMedico =
                medicoDAO.insertar(medico);

        if (!registradoMedico) {
            System.out.println("No se pudo registrar el médico.");
            return;
        }

        System.out.println("ID del médico registrado: "
                + medico.getIdMedico());

        System.out.println("\n--- LISTA DE MÉDICOS ---");

        List<Medico> medicos = medicoDAO.listar();

        for (Medico m : medicos) {
            System.out.println(m);
        }

        System.out.println("\n--- BUSCAR MÉDICO POR ID ---");

        Medico medicoEncontrado =
                medicoDAO.buscarPorId(medico.getIdMedico());

        if (medicoEncontrado != null) {
            System.out.println(medicoEncontrado);
        }

        System.out.println("\n--- ACTUALIZAR MÉDICO ---");

        medico.setEspecialidad("Medicina Interna");

        boolean actualizadoMedico =
                medicoDAO.actualizar(medico);

        if (actualizadoMedico) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        System.out.println("\n--- ELIMINAR MÉDICO ---");

        boolean eliminadoMedico =
                medicoDAO.eliminar(medico.getIdMedico());

        if (eliminadoMedico) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar el médico.");
        }

        System.out.println("\n=== PRUEBA CRUD MÉDICOS FINALIZADA ===");


        // =====================================================
        // PRUEBA CRUD REPORTES
        // =====================================================

        ReporteDAO reporteDAO = new ReporteDAO();

        System.out.println("\n=== PRUEBA CRUD REPORTES VITALAPP ===");

        Reporte reporte = new Reporte(
                0,
                1,
                LocalDateTime.now(),
                "Reporte de evolución del paciente generado correctamente."
        );

        boolean registradoReporte =
                reporteDAO.insertar(reporte);

        if (!registradoReporte) {
            System.out.println("No se pudo registrar el reporte.");
            return;
        }

        System.out.println("ID del reporte registrado: "
                + reporte.getIdReporte());

        System.out.println("\n--- LISTA DE REPORTES ---");

        List<Reporte> reportes = reporteDAO.listar();

        for (Reporte r : reportes) {
            System.out.println(r);
        }

        System.out.println("\n--- BUSCAR REPORTE POR ID ---");

        Reporte reporteEncontrado =
                reporteDAO.buscarPorId(reporte.getIdReporte());

        if (reporteEncontrado != null) {
            System.out.println(reporteEncontrado);
        }

        System.out.println("\n--- ACTUALIZAR REPORTE ---");

        reporte.setDescripcion(
                "Reporte actualizado de evolución del paciente."
        );

        boolean actualizadoReporte =
                reporteDAO.actualizar(reporte);

        if (actualizadoReporte) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        System.out.println("\n--- ELIMINAR REPORTE ---");

        boolean eliminadoReporte =
                reporteDAO.eliminar(reporte.getIdReporte());

        if (eliminadoReporte) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar el reporte.");
        }

        System.out.println("\n=== PRUEBA CRUD REPORTES FINALIZADA ===");


        // =====================================================
        // PRUEBA CRUD PACIENTES
        // =====================================================

        PacienteDAO pacienteDAO = new PacienteDAO();

        System.out.println("\n=== PRUEBA CRUD PACIENTES VITALAPP ===");

        Paciente paciente = new Paciente(
                0,
                1,
                LocalDate.of(2000, 5, 15),
                "Masculino"
        );

        boolean registradoPaciente =
                pacienteDAO.insertar(paciente);

        if (!registradoPaciente) {
            System.out.println("No se pudo registrar el paciente.");
            return;
        }

        System.out.println("ID del paciente registrado: "
                + paciente.getIdPaciente());

        System.out.println("\n--- LISTA DE PACIENTES ---");

        List<Paciente> pacientes = pacienteDAO.listar();

        for (Paciente p : pacientes) {
            System.out.println(p);
        }

        System.out.println("\n--- BUSCAR PACIENTE POR ID ---");

        Paciente pacienteEncontrado =
                pacienteDAO.buscarPorId(paciente.getIdPaciente());

        if (pacienteEncontrado != null) {
            System.out.println(pacienteEncontrado);
        }

        System.out.println("\n--- ACTUALIZAR PACIENTE ---");

        paciente.setSexo("Femenino");

        boolean actualizadoPaciente =
                pacienteDAO.actualizar(paciente);

        if (actualizadoPaciente) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        System.out.println("\n--- ELIMINAR PACIENTE ---");

        boolean eliminadoPaciente =
                pacienteDAO.eliminar(paciente.getIdPaciente());

        if (eliminadoPaciente) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar el paciente.");
        }

        System.out.println("\n=== PRUEBA CRUD PACIENTES FINALIZADA ===");


        // =====================================================
        // PRUEBA CRUD SIGNOS VITALES
        // =====================================================

        SignoVitalDAO signoVitalDAO = new SignoVitalDAO();

        System.out.println("\n=== PRUEBA CRUD SIGNOS VITALES VITALAPP ===");

        SignoVital signoVital = new SignoVital(
                0,
                1,
                LocalDateTime.now(),
                75,
                18,
                "120/80",
                36.5,
                98.0,
                90.0
        );

        boolean registradoSigno =
                signoVitalDAO.insertar(signoVital);

        if (!registradoSigno) {
            System.out.println("No se pudo registrar el signo vital.");
            return;
        }

        System.out.println("ID del signo vital registrado: "
                + signoVital.getIdSigno());

        System.out.println("\n--- LISTA DE SIGNOS VITALES ---");

        List<SignoVital> signosVitales =
                signoVitalDAO.listar();

        for (SignoVital s : signosVitales) {
            System.out.println(s);
        }

        System.out.println("\n--- ACTUALIZAR SIGNO VITAL ---");

        signoVital.setFrecuenciaCardiaca(80);
        signoVital.setTemperatura(36.8);

        boolean actualizadoSigno =
                signoVitalDAO.actualizar(signoVital);

        if (actualizadoSigno) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        System.out.println("\n--- ELIMINAR SIGNO VITAL ---");

        boolean eliminadoSigno =
                signoVitalDAO.eliminar(signoVital.getIdSigno());

        if (eliminadoSigno) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar el signo vital.");
        }

        System.out.println(
                "\n=== PRUEBA CRUD SIGNOS VITALES FINALIZADA ==="
        );


        // =====================================================
        // PRUEBA CRUD ALERTAS
        // =====================================================

        AlertaDAO alertaDAO = new AlertaDAO();

        System.out.println("\n=== PRUEBA CRUD ALERTAS VITALAPP ===");

        Alerta alerta = new Alerta(
                0,
                1,
                "Presión arterial",
                "Presión arterial fuera del rango normal.",
                LocalDateTime.now()
        );

        boolean registradaAlerta =
                alertaDAO.insertar(alerta);

        if (!registradaAlerta) {
            System.out.println("No se pudo registrar la alerta.");
            return;
        }

        System.out.println("ID de la alerta registrada: "
                + alerta.getIdAlerta());

        System.out.println("\n--- LISTA DE ALERTAS ---");

        List<Alerta> alertas = alertaDAO.listar();

        for (Alerta a : alertas) {
            System.out.println(a);
        }

        System.out.println("\n--- BUSCAR ALERTA POR ID ---");

        Alerta alertaEncontrada =
                alertaDAO.buscarPorId(alerta.getIdAlerta());

        if (alertaEncontrada != null) {
            System.out.println(alertaEncontrada);
        }

        System.out.println("\n--- ACTUALIZAR ALERTA ---");

        alerta.setDescripcion(
                "Descripción actualizada de la alerta."
        );

        boolean actualizadaAlerta =
                alertaDAO.actualizar(alerta);

        if (actualizadaAlerta) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        System.out.println("\n--- ELIMINAR ALERTA ---");

        boolean eliminadaAlerta =
                alertaDAO.eliminar(alerta.getIdAlerta());

        if (eliminadaAlerta) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar la alerta.");
        }

        System.out.println("\n=== PRUEBA CRUD ALERTAS FINALIZADA ===");

        System.out.println("\n======================================");
        System.out.println(" TODAS LAS PRUEBAS CRUD FINALIZADAS");
        System.out.println("======================================");
    }
}