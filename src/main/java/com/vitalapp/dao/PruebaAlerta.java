package com.vitalapp;

import com.vitalapp.dao.AlertaDAO;
import com.vitalapp.modelo.Alerta;

import java.time.LocalDateTime;
import java.util.List;

public class PruebaAlerta {

    public static void main(String[] args) {

        AlertaDAO alertaDAO = new AlertaDAO();

        System.out.println("======================================");
        System.out.println("   PRUEBA CRUD ALERTAS - VITALAPP");
        System.out.println("======================================");

        // 1. INSERTAR
        System.out.println("\n--- 1. INSERTAR ALERTA ---");

        Alerta alerta = new Alerta(
                0,
                1,
                "Presión arterial",
                "Presión arterial fuera del rango normal.",
                LocalDateTime.now()
        );

        boolean registrada = alertaDAO.insertar(alerta);

        if (!registrada) {
            System.out.println("No se pudo registrar la alerta.");
            return;
        }

        System.out.println("ID generado: " + alerta.getIdAlerta());

        // 2. LISTAR
        System.out.println("\n--- 2. LISTAR ALERTAS ---");

        List<Alerta> alertas = alertaDAO.listar();

        for (Alerta a : alertas) {
            System.out.println(a);
        }

        // 3. BUSCAR
        System.out.println("\n--- 3. BUSCAR ALERTA ---");

        Alerta alertaEncontrada =
                alertaDAO.buscarPorId(alerta.getIdAlerta());

        if (alertaEncontrada != null) {
            System.out.println("Alerta encontrada:");
            System.out.println(alertaEncontrada);
        }

        // 4. ACTUALIZAR
        System.out.println("\n--- 4. ACTUALIZAR ALERTA ---");

        alerta.setDescripcion(
                "Descripción actualizada de la alerta."
        );

        boolean actualizada =
                alertaDAO.actualizar(alerta);

        if (actualizada) {
            System.out.println("Alerta actualizada correctamente.");
        }

        // COMPROBAR ACTUALIZACIÓN
        System.out.println("\n--- COMPROBAR ACTUALIZACION ---");

        Alerta alertaActualizada =
                alertaDAO.buscarPorId(alerta.getIdAlerta());

        if (alertaActualizada != null) {
            System.out.println(alertaActualizada);
        }

        // 5. ELIMINAR
        System.out.println("\n--- 5. ELIMINAR ALERTA ---");

        boolean eliminada =
                alertaDAO.eliminar(alerta.getIdAlerta());

        if (eliminada) {
            System.out.println("Alerta eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la alerta.");
        }

        System.out.println("\n======================================");
        System.out.println("   PRUEBA CRUD ALERTAS FINALIZADA");
        System.out.println("======================================");
    }
}