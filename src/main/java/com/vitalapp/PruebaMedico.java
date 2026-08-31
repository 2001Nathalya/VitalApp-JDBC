package com.vitalapp;

import com.vitalapp.dao.MedicoDAO;
import com.vitalapp.modelo.Medico;

import java.util.List;

public class PruebaMedico {

    public static void main(String[] args) {

        MedicoDAO medicoDAO = new MedicoDAO();

        System.out.println("======================================");
        System.out.println("   PRUEBA CRUD MEDICOS - VITALAPP");
        System.out.println("======================================");

        // 1. INSERTAR
        System.out.println("\n--- 1. INSERTAR MEDICO ---");

        Medico medico = new Medico(
                0,
                1,
                "Medicina General"
        );

        boolean registrado = medicoDAO.insertar(medico);

        if (!registrado) {
            System.out.println("ERROR: No se pudo insertar el medico.");
            return;
        }

        System.out.println("ID generado: " + medico.getIdMedico());

        // 2. LISTAR
        System.out.println("\n--- 2. LISTAR MEDICOS ---");

        List<Medico> medicos = medicoDAO.listar();

        for (Medico m : medicos) {
            System.out.println(m);
        }

        // 3. BUSCAR
        System.out.println("\n--- 3. BUSCAR MEDICO ---");

        Medico encontrado =
                medicoDAO.buscarPorId(medico.getIdMedico());

        if (encontrado != null) {
            System.out.println("Medico encontrado:");
            System.out.println(encontrado);
        }

        // 4. ACTUALIZAR
        System.out.println("\n--- 4. ACTUALIZAR MEDICO ---");

        medico.setEspecialidad("Medicina Interna");

        boolean actualizado =
                medicoDAO.actualizar(medico);

        if (actualizado) {
            System.out.println("Medico actualizado correctamente.");
        }

        // 5. COMPROBAR ACTUALIZACION
        System.out.println("\n--- COMPROBAR ACTUALIZACION ---");

        Medico actualizadoEncontrado =
                medicoDAO.buscarPorId(medico.getIdMedico());

        if (actualizadoEncontrado != null) {
            System.out.println(actualizadoEncontrado);
        }

        // 6. ELIMINAR
        System.out.println("\n--- 5. ELIMINAR MEDICO ---");

        boolean eliminado =
                medicoDAO.eliminar(medico.getIdMedico());

        if (eliminado) {
            System.out.println("Medico eliminado correctamente.");
        }

        System.out.println("\n======================================");
        System.out.println("   PRUEBA CRUD MEDICOS FINALIZADA");
        System.out.println("======================================");
    }
}