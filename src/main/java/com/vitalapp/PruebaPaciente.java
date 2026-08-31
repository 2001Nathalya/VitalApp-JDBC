package com.vitalapp;

import com.vitalapp.dao.PacienteDAO;
import com.vitalapp.modelo.Paciente;

import java.time.LocalDate;
import java.util.List;

public class PruebaPaciente {

    public static void main(String[] args) {

        PacienteDAO pacienteDAO = new PacienteDAO();

        System.out.println("======================================");
        System.out.println("   PRUEBA CRUD PACIENTES - VITALAPP");
        System.out.println("======================================");

        // 1. INSERTAR
        System.out.println("\n--- 1. INSERTAR PACIENTE ---");

        Paciente paciente = new Paciente(
                0,
                1,
                LocalDate.of(1998, 10, 20),
                "Femenino"
        );

        boolean registrado = pacienteDAO.insertar(paciente);

        if (!registrado) {
            System.out.println("ERROR: No se pudo insertar el paciente.");
            return;
        }

        System.out.println("ID generado: " + paciente.getIdPaciente());

        // 2. LISTAR
        System.out.println("\n--- 2. LISTAR PACIENTES ---");

        List<Paciente> pacientes = pacienteDAO.listar();

        for (Paciente p : pacientes) {
            System.out.println(p);
        }

        // 3. BUSCAR
        System.out.println("\n--- 3. BUSCAR PACIENTE ---");

        Paciente encontrado =
                pacienteDAO.buscarPorId(paciente.getIdPaciente());

        if (encontrado != null) {
            System.out.println("Paciente encontrado:");
            System.out.println(encontrado);
        }

        // 4. ACTUALIZAR
        System.out.println("\n--- 4. ACTUALIZAR PACIENTE ---");

        paciente.setSexo("Masculino");

        boolean actualizado =
                pacienteDAO.actualizar(paciente);

        if (actualizado) {
            System.out.println("Paciente actualizado correctamente.");
        }

        // 5. COMPROBAR ACTUALIZACION
        System.out.println("\n--- COMPROBAR ACTUALIZACION ---");

        Paciente actualizadoEncontrado =
                pacienteDAO.buscarPorId(paciente.getIdPaciente());

        if (actualizadoEncontrado != null) {
            System.out.println(actualizadoEncontrado);
        }

        // 6. ELIMINAR
        System.out.println("\n--- 5. ELIMINAR PACIENTE ---");

        boolean eliminado =
                pacienteDAO.eliminar(paciente.getIdPaciente());

        if (eliminado) {
            System.out.println("Paciente eliminado correctamente.");
        }

        System.out.println("\n======================================");
        System.out.println("   PRUEBA CRUD PACIENTES FINALIZADA");
        System.out.println("======================================");
    }
}