package com.vitalapp;

import com.vitalapp.dao.PacienteDAO;

public class App {

    public static void main(String[] args) {

        PacienteDAO pacienteDAO = new PacienteDAO();

        // Eliminar paciente de prueba
        int idPaciente = 4;

        boolean eliminado = pacienteDAO.eliminar(idPaciente);

        if (eliminado) {
            System.out.println("Prueba de eliminación completada correctamente.");
        } else {
            System.out.println("No se pudo eliminar el paciente.");
        }
    }
}