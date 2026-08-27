package com.vitalapp;

import com.vitalapp.dao.AlertaDAO;
import com.vitalapp.modelo.Alerta;

import java.time.LocalDateTime;
import java.util.List;

public class App {

    public static void main(String[] args) {

        AlertaDAO alertaDAO = new AlertaDAO();

        System.out.println("=== PRUEBA CRUD ALERTAS VITALAPP ===");

        // 1. INSERTAR
        Alerta alerta = new Alerta(
                0,
                1,
                "Temperatura alta",
                "Temperatura del paciente por encima del valor normal.",
                LocalDateTime.now()
        );

        boolean registrada = alertaDAO.insertar(alerta);

        if (!registrada) {
            System.out.println("No se pudo registrar la alerta.");
            return;
        }

        System.out.println("ID de la alerta registrada: "
                + alerta.getIdAlerta());

        // 2. CONSULTAR / LISTAR
        System.out.println("\n--- LISTA DE ALERTAS ---");

        List<Alerta> alertas = alertaDAO.listar();

        for (Alerta a : alertas) {
            System.out.println(a);
        }

        // 3. BUSCAR POR ID
        System.out.println("\n--- BUSCAR ALERTA POR ID ---");

        Alerta alertaEncontrada =
                alertaDAO.buscarPorId(alerta.getIdAlerta());

        if (alertaEncontrada != null) {
            System.out.println(alertaEncontrada);
        }

        // 4. ACTUALIZAR
        System.out.println("\n--- ACTUALIZAR ALERTA ---");

        alerta.setTipoAlerta("Frecuencia cardiaca alta");
        alerta.setDescripcion(
                "La frecuencia cardiaca del paciente requiere atención."
        );

        boolean actualizada = alertaDAO.actualizar(alerta);

        if (actualizada) {
            System.out.println(
                    "Prueba de actualización completada correctamente."
            );
        }

        // 5. ELIMINAR
        System.out.println("\n--- ELIMINAR ALERTA ---");

        boolean eliminada =
                alertaDAO.eliminar(alerta.getIdAlerta());

        if (eliminada) {
            System.out.println(
                    "Prueba de eliminación completada correctamente."
            );
        } else {
            System.out.println("No se pudo eliminar la alerta.");
        }

        System.out.println("\n=== PRUEBA CRUD ALERTAS FINALIZADA ===");
    }
}