package com.vitalapp;

import com.vitalapp.dao.PacienteDAO;
import com.vitalapp.modelo.Paciente;

import java.time.LocalDate;
import java.util.List;

public class App {

    public static void main(String[] args) {

        PacienteDAO pacienteDAO = new PacienteDAO();

        System.out.println("=== PRUEBA CRUD VITALAPP ===");

        // 1. INSERTAR
        Paciente paciente = new Paciente(
                0,
                1,
                LocalDate.of(2000, 5, 15),
                "Femenino"
        );

        boolean registrado = pacienteDAO.insertar(paciente);

        if (!registrado) {
            System.out.println("No se pudo registrar el paciente.");
            return;
        }

        System.out.println("ID del paciente registrado: "
                + paciente.getIdPaciente());

        // 2. CONSULTAR
        System.out.println("\n--- LISTA DE PACIENTES ---");

        List<Paciente> pacientes = pacienteDAO.listar();

        for (Paciente p : pacientes) {
            System.out.println(p);
        }
        // 3. BUSCAR PACIENTE POR ID
System.out.println("\n--- BUSCAR PACIENTE POR ID ---");

Paciente pacienteEncontrado = pacienteDAO.buscarPorId(paciente.getIdPaciente());

if (pacienteEncontrado != null) {
    System.out.println(pacienteEncontrado);
} else {
    System.out.println("No se encontró el paciente.");
}

        // 4. ACTUALIZAR
        paciente.setSexo("Masculino");

        boolean actualizado = pacienteDAO.actualizar(paciente);

        if (actualizado) {
            System.out.println("Prueba de actualización completada correctamente.");
        }

        // 5. ELIMINAR
        boolean eliminado = pacienteDAO.eliminar(paciente.getIdPaciente());

        if (eliminado) {
            System.out.println("Prueba de eliminación completada correctamente.");
        } else {
            System.out.println("No se pudo eliminar el paciente.");
        }

        System.out.println("\n=== PRUEBA CRUD FINALIZADA ===");
    }
}

